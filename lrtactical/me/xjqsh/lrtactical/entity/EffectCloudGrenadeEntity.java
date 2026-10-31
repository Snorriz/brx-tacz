package me.xjqsh.lrtactical.entity;

import java.util.List;
import me.xjqsh.lrtactical.entity.sp.SpEffectCloudEntity;
import me.xjqsh.lrtactical.item.throwable.area.EffectCloudThrowableData.CloudData;
import me.xjqsh.lrtactical.item.throwable.area.EffectCloudThrowableData.EffectData;
import me.xjqsh.lrtactical.network.NetworkHandler;
import me.xjqsh.lrtactical.network.message.SSplashParticle;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages.SpawnEntity;
import org.jetbrains.annotations.Nullable;

public class EffectCloudGrenadeEntity extends ThrowableItemEntity {
   public static EntityType<EffectCloudGrenadeEntity> TYPE = Builder.m_20704_(EffectCloudGrenadeEntity::new, MobCategory.MISC)
      .setShouldReceiveVelocityUpdates(true)
      .setTrackingRange(64)
      .setUpdateInterval(1)
      .setCustomClientFactory(EffectCloudGrenadeEntity::new)
      .m_20699_(0.3F, 0.3F)
      .m_20716_()
      .m_20698_()
      .m_20719_()
      .m_20712_("grenade_entity");
   private CloudData cloudData;

   public EffectCloudGrenadeEntity(LivingEntity entity, Level level, int lifeTime) {
      super(TYPE, entity, level, lifeTime);
   }

   public EffectCloudGrenadeEntity(SpawnEntity spawnEntity, Level level) {
      super(TYPE, level);
   }

   public EffectCloudGrenadeEntity(EntityType<EffectCloudGrenadeEntity> type, Level level) {
      super(type, level);
   }

   public void onDeath(@Nullable HitResult hitResult) {
      Vec3 pos = hitResult == null ? this.m_20182_() : hitResult.m_82450_();
      if (!this.m_9236_().m_5776_() && this.cloudData != null) {
         CloudData cloudData = this.getCloudData();
         if (cloudData.isAreaCloud()) {
            this.spawnEffectCloud(pos, cloudData);
         } else {
            List<MobEffectInstance> effects = cloudData.getEffectInstances();
            Entity target = hitResult instanceof EntityHitResult entityHitResult ? entityHitResult.m_82443_() : null;
            int color = PotionUtils.m_43564_(effects);
            this.applySplash(effects, cloudData.isIgnite(), cloudData.getIgniteTime(), target, cloudData.getRadius());
            NetworkHandler.sendToNearbyPlayers(new SSplashParticle(this.m_20183_(), color), this.m_9236_(), pos, 64.0);
         }
      }

      super.onDeath(hitResult);
   }

   private void spawnEffectCloud(Vec3 pos, CloudData cloudData) {
      SpEffectCloudEntity cloud = new SpEffectCloudEntity(this.m_9236_(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_());
      cloud.m_19712_(cloudData.getRadius());
      cloud.m_19738_(cloudData.getRadiusPerTick());
      cloud.m_19734_(cloudData.getDuration());
      cloud.m_19740_(cloudData.getWaitTime());
      cloud.m_19724_(cloudData.getParticles());
      cloud.setIgnite(cloudData.isIgnite());
      cloud.setExtinguishBySmoke(cloudData.isExtinguishBySmoke());

      for (EffectData effect : cloudData.getEffects()) {
         cloud.m_19716_(effect.toInstance());
      }

      if (this.m_19749_() instanceof LivingEntity livingEntity) {
         cloud.m_19718_(livingEntity);
      }

      this.m_9236_().m_7967_(cloud);
   }

   public void applySplash(List<MobEffectInstance> effectInstances, boolean ignite, int igniteTime, @Nullable Entity target, double radius) {
      if (!effectInstances.isEmpty() && !(radius <= 0.0)) {
         AABB area = this.m_20191_().m_82377_(radius, 2.0, radius);
         List<LivingEntity> entities = this.m_9236_().m_45976_(LivingEntity.class, area);
         if (!entities.isEmpty()) {
            Entity source = this.m_150173_();

            for (LivingEntity entity : entities) {
               if (entity.m_5801_()) {
                  double distanceSqr = this.m_20280_(entity);
                  if (distanceSqr < 16.0) {
                     double d = entity == target ? 1.0 : 1.0 - Math.sqrt(distanceSqr) / radius;
                     this.applyAllEffects(effectInstances, entity, d, source, ignite, igniteTime);
                  }
               }
            }
         }
      }
   }

   public void applyAllEffects(List<MobEffectInstance> effectInstances, LivingEntity entity, double d, Entity source, boolean ignite, int igniteTime) {
      for (MobEffectInstance effect : effectInstances) {
         MobEffect mobEffect = effect.m_19544_();
         if (mobEffect.m_8093_()) {
            mobEffect.m_19461_(this, this.m_19749_(), entity, effect.m_19564_(), d);
         } else {
            int adjustedDuration = effect.m_267696_(duration -> (int)(d * duration + 0.5));
            MobEffectInstance adjustedEffect = new MobEffectInstance(mobEffect, adjustedDuration, effect.m_19564_(), effect.m_19571_(), effect.m_19572_());
            if (!adjustedEffect.m_267633_(20)) {
               entity.m_147207_(adjustedEffect, source);
            }
         }
      }

      if (ignite && !entity.m_5825_()) {
         entity.m_20254_(igniteTime);
      }
   }

   public CloudData getCloudData() {
      return this.cloudData;
   }

   public void setCloudData(CloudData cloudData) {
      this.cloudData = cloudData;
   }
}
