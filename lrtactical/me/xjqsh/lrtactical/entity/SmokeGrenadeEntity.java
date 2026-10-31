package me.xjqsh.lrtactical.entity;

import me.xjqsh.lrtactical.init.ModParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class SmokeGrenadeEntity extends ThrowableItemEntity {
   public static EntityType<SmokeGrenadeEntity> TYPE = Builder.m_20704_(SmokeGrenadeEntity::new, MobCategory.MISC)
      .setShouldReceiveVelocityUpdates(true)
      .setTrackingRange(64)
      .setUpdateInterval(1)
      .setCustomClientFactory(SmokeGrenadeEntity::new)
      .m_20699_(0.3F, 0.3F)
      .m_20716_()
      .m_20698_()
      .m_20719_()
      .m_20712_("smoke_grenade_entity");

   public SmokeGrenadeEntity(LivingEntity entity, Level level, int lifeTime) {
      super(TYPE, entity, level, lifeTime);
   }

   public SmokeGrenadeEntity(SpawnEntity spawnEntity, Level level) {
      super(TYPE, level);
   }

   public SmokeGrenadeEntity(EntityType<SmokeGrenadeEntity> type, Level level) {
      super(type, level);
   }

   public void m_8119_() {
      super.m_8119_();
      if (this.m_9236_().m_5776_()) {
         if (this.f_19797_ >= 40) {
            double x = this.m_20185_();
            double y = this.m_20186_();
            double z = this.m_20189_();

            for (int i = 0; i < 16; i++) {
               double offsetX = this.f_19796_.m_216328_(0.0, 5.5);
               double offsetY = this.f_19796_.m_216328_(0.0, 4.5);
               double offsetZ = this.f_19796_.m_216328_(0.0, 5.5);
               this.m_9236_().m_6493_((ParticleOptions)ModParticleTypes.SMOKE_CLOUD.get(), true, x + offsetX, y + offsetY, z + offsetZ, 0.0, 0.0, 0.0);
            }
         }
      } else if (this.f_19797_ == 40) {
         this.playThrowableSound("release", 16.0F, 1.0F);
      }
   }
}
