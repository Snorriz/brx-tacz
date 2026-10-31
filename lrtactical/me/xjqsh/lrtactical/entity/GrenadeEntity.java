package me.xjqsh.lrtactical.entity;

import me.xjqsh.lrtactical.config.CommonConfig;
import me.xjqsh.lrtactical.util.CustomExplosion;
import me.xjqsh.lrtactical.util.ParticleUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class GrenadeEntity extends ThrowableItemEntity {
   public static EntityType<GrenadeEntity> TYPE = Builder.m_20704_(GrenadeEntity::new, MobCategory.MISC)
      .setShouldReceiveVelocityUpdates(true)
      .setTrackingRange(64)
      .setUpdateInterval(1)
      .setCustomClientFactory(GrenadeEntity::new)
      .m_20699_(0.3F, 0.3F)
      .m_20716_()
      .m_20698_()
      .m_20719_()
      .m_20712_("grenade_entity");
   private double damage = 18.0;
   private float radius = 4.5F;
   private boolean destroyBlocks = false;
   private float destroyMultiplier = 1.0F;
   private double screenShakeTime = 20.0;
   private double screenShakeAmplitude = 50.0;
   private boolean triggerOnExplode = false;
   private boolean exploded = false;

   public GrenadeEntity(EntityType<? extends GrenadeEntity> type, LivingEntity entity, Level level, int lifeTime) {
      super(type, entity, level, lifeTime);
   }

   public GrenadeEntity(LivingEntity entity, Level level, int lifeTime) {
      super(TYPE, entity, level, lifeTime);
   }

   public GrenadeEntity(SpawnEntity spawnEntity, Level level) {
      super(TYPE, level);
   }

   public GrenadeEntity(EntityType<? extends GrenadeEntity> type, Level level) {
      super(type, level);
   }

   public void onDeath(HitResult hitResult) {
      this.exploded = true;
      Vec3 pos = hitResult == null ? this.m_20182_() : this.m_20182_().m_165921_(hitResult.m_82450_(), 0.8);
      if (!this.m_9236_().m_5776_()) {
         BlockInteraction type = this.isDestroyBlocks() && CommonConfig.GRENADE_EXPLOSION_BLOCK_DAMAGE.get() ? BlockInteraction.DESTROY : BlockInteraction.KEEP;
         CustomExplosion explosion = new CustomExplosion(this.m_9236_(), this, this.getDamage(), this.getRadius(), type);
         explosion.setScreenShakeAmplitude(this.screenShakeAmplitude);
         explosion.setScreenShakeTime(this.screenShakeTime);
         explosion.setDestroyMultiplier(this.destroyMultiplier);
         if (ForgeEventFactory.onExplosionStart(this.m_9236_(), explosion)) {
            return;
         }

         explosion.m_46061_();
         explosion.m_46075_(true);
         if (this.m_9236_() instanceof ServerLevel level) {
            double x = pos.m_7096_();
            double y = pos.m_7098_();
            double z = pos.m_7094_();
            ParticleUtil.sendParticle(level, ParticleTypes.f_123747_, x, y + 0.5, z, 50, 0.2, 0.2, 0.2, 20.0, true);
            ParticleUtil.sendParticle(level, ParticleTypes.f_123812_, x, y + 1.0, z, 5, 0.7, 0.7, 0.7, 1.0, true);
         }
      }

      super.onDeath(hitResult);
   }

   public boolean m_6469_(DamageSource pSource, float pAmount) {
      if (!this.m_9236_().m_5776_() && this.triggerOnExplode && pSource.m_269533_(DamageTypeTags.f_268415_) && !this.exploded) {
         this.setLife(this.f_19797_ + 3);
         return true;
      } else {
         return super.m_6469_(pSource, pAmount);
      }
   }

   public double getDamage() {
      return this.damage;
   }

   public void setDamage(double damage) {
      this.damage = damage;
   }

   public float getRadius() {
      return this.radius;
   }

   public void setRadius(float radius) {
      this.radius = radius;
   }

   public boolean isDestroyBlocks() {
      return this.destroyBlocks;
   }

   public void setDestroyBlocks(boolean destroyBlocks) {
      this.destroyBlocks = destroyBlocks;
   }

   public double getScreenShakeTime() {
      return this.screenShakeTime;
   }

   public void setScreenShakeTime(double screenShakeTime) {
      this.screenShakeTime = screenShakeTime;
   }

   public double getScreenShakeAmplitude() {
      return this.screenShakeAmplitude;
   }

   public void setScreenShakeAmplitude(double screenShakeAmplitude) {
      this.screenShakeAmplitude = screenShakeAmplitude;
   }

   public float getDestroyMultiplier() {
      return this.destroyMultiplier;
   }

   public void setExplodeDestroyMultiplier(float destroyMultiplier) {
      this.destroyMultiplier = destroyMultiplier;
   }

   public void setTriggerOnExplode(boolean triggerOnExplode) {
      this.triggerOnExplode = triggerOnExplode;
   }

   public boolean isTriggerOnExplode() {
      return this.triggerOnExplode;
   }
}
