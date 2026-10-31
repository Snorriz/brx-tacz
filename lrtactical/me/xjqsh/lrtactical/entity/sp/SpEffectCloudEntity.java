package me.xjqsh.lrtactical.entity.sp;

import me.xjqsh.lrtactical.entity.SmokeGrenadeEntity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SpEffectCloudEntity extends AreaEffectCloud {
   public static EntityType<SpEffectCloudEntity> TYPE = Builder.m_20704_(SpEffectCloudEntity::new, MobCategory.MISC)
      .m_20719_()
      .m_20699_(6.0F, 0.5F)
      .m_20716_()
      .m_20698_()
      .m_20702_(10)
      .m_20717_(Integer.MAX_VALUE)
      .m_20712_("sp_effect_cloud");
   private boolean ignite = false;
   private int igniteTime = 2;
   private boolean extinguishBySmoke = false;

   public SpEffectCloudEntity(EntityType<? extends AreaEffectCloud> type, Level level) {
      super(type, level);
   }

   public SpEffectCloudEntity(Level level, double x, double y, double z) {
      this(TYPE, level);
      this.m_6034_(x, y, z);
   }

   public void m_8119_() {
      super.m_8119_();
      if (!this.m_9236_().m_5776_() && this.f_19797_ % 10 == 0) {
         for (Entity entity : this.m_9236_().m_45976_(Entity.class, this.m_20191_().m_82377_(0.0, 2.0, 0.0))) {
            if (this.isExtinguishBySmoke() && entity instanceof SmokeGrenadeEntity smokeGrenade) {
               if (this.shouldBeExtinguished(smokeGrenade)) {
                  this.m_146870_();
               }
            } else if (this.isIgnite() && !entity.m_5825_() && entity instanceof LivingEntity && this.m_20191_().m_82381_(entity.m_20191_())) {
               entity.m_20254_(this.getIgniteTime());
            }
         }
      }
   }

   public boolean shouldBeExtinguished(SmokeGrenadeEntity smokeGrenade) {
      return smokeGrenade.f_19797_ >= 40 && smokeGrenade.m_20182_().m_82557_(this.m_20182_()) < 25.0;
   }

   @NotNull
   public EntityDimensions m_6972_(@NotNull Pose pPose) {
      return EntityDimensions.m_20395_(this.m_19743_() * 2.0F, 0.5F);
   }

   public boolean isIgnite() {
      return this.ignite;
   }

   public void setIgnite(boolean ignite) {
      this.ignite = ignite;
   }

   public boolean isExtinguishBySmoke() {
      return this.extinguishBySmoke;
   }

   public void setExtinguishBySmoke(boolean extinguishBySmoke) {
      this.extinguishBySmoke = extinguishBySmoke;
   }

   public int getIgniteTime() {
      return this.igniteTime;
   }

   public void setIgniteTime(int igniteTime) {
      this.igniteTime = igniteTime;
   }
}
