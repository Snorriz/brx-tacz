package me.xjqsh.lrtactical.entity;

import me.xjqsh.lrtactical.init.ModEffects;
import me.xjqsh.lrtactical.item.throwable.flash.StunThrowableData.StunData;
import me.xjqsh.lrtactical.util.SightTraceUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages.SpawnEntity;
import org.jetbrains.annotations.Nullable;

public class StunGrenadeEntity extends ThrowableItemEntity {
   public static EntityType<StunGrenadeEntity> TYPE = Builder.m_20704_(StunGrenadeEntity::new, MobCategory.MISC)
      .setShouldReceiveVelocityUpdates(true)
      .setTrackingRange(64)
      .setUpdateInterval(1)
      .setCustomClientFactory(StunGrenadeEntity::new)
      .m_20699_(0.3F, 0.3F)
      .m_20716_()
      .m_20698_()
      .m_20719_()
      .m_20712_("stun_grenade_entity");
   private StunData data = new StunData();

   public StunGrenadeEntity(LivingEntity entity, Level level, int lifeTime) {
      super(TYPE, entity, level, lifeTime);
   }

   public StunGrenadeEntity(SpawnEntity spawnEntity, Level level) {
      super(TYPE, level);
   }

   public StunGrenadeEntity(EntityType<StunGrenadeEntity> type, Level level) {
      super(type, level);
   }

   public StunData getData() {
      return this.data;
   }

   public void setStunData(StunData data) {
      this.data = data;
   }

   public void onDeath(@Nullable HitResult hitResult) {
      if (!this.m_9236_().m_5776_()) {
         double radius = this.getData().getRadius();
         AABB aabb = this.m_20191_().m_82400_(radius);

         for (Entity entity : this.m_9236_().m_6249_(this, aabb, EntitySelector.f_20408_)) {
            if (entity instanceof LivingEntity living) {
               calculateAndApplyEffect(this, living, this.getData());
            }
         }
      }

      super.onDeath(hitResult);
   }

   public static void calculateAndApplyEffect(Entity starter, LivingEntity target, StunData data) {
      Vec3 p = starter.m_20182_().m_82520_(0.0, 1.0, 0.0);
      Vec3 eyes = target.m_20299_(1.0F);
      Vec3 d1 = p.m_82546_(eyes);
      double distanceMax = data.getRadius();
      double distance = d1.m_82553_();
      if (!(distance > distanceMax)) {
         double a1 = Math.toDegrees(Math.acos(target.m_20252_(1.0F).m_82526_(d1.m_82541_())));
         double angleMax = data.getBlind().getMaxAngle();
         if (a1 > 0.0 && a1 < angleMax && SightTraceUtil.rayTraceOpaqueBlocks(starter, target.m_9236_(), eyes, p, false, false, false) == null) {
            int durationBlinded = data.calcBlindDuration(distance, a1);
            if (durationBlinded > 0) {
               target.m_7292_(new MobEffectInstance((MobEffect)ModEffects.BLIND.get(), durationBlinded, 0, false, false));
            }
         }

         int durationDeafened = data.calcDeafenedDuration(distance);
         if (durationDeafened > 0) {
            target.m_7292_(new MobEffectInstance((MobEffect)ModEffects.DEAFENED.get(), durationDeafened, 0, false, false));
         }
      }
   }
}
