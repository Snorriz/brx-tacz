package me.xjqsh.lrtactical.api.collision;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import me.xjqsh.lrtactical.util.VectorUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ConeFilter implements ITargetFilter {
   @SerializedName("max_range")
   private double maxRange = 2.5;
   @SerializedName("max_angle")
   private double maxAngle = 90.0;
   @SerializedName("exclude_self")
   private boolean excludeSelf = true;

   public ConeFilter(double maxDistance, double maxAngle) {
      this.maxRange = maxDistance;
      this.maxAngle = maxAngle;
   }

   public ConeFilter(double maxDistance, double maxAngle, boolean excludeSelf) {
      this.maxRange = maxDistance;
      this.maxAngle = maxAngle;
      this.excludeSelf = excludeSelf;
   }

   @NotNull
   public List<Entity> filterTargets(LivingEntity attacker, Vec3 origin, Vec3 direction) {
      List<Entity> targets = new ArrayList();
      AABB area = attacker.m_20191_().m_82377_(this.maxRange * 2.0, this.maxRange, this.maxRange * 2.0);
      double halfAngle = this.maxAngle / 2.0;

      for (Entity livingentity : attacker.m_9236_().m_45976_(Entity.class, area)) {
         boolean flag = !(livingentity instanceof ArmorStand armorStand && armorStand.m_31677_());
         boolean inAngle = VectorUtil.isInAngle(origin, direction, livingentity, halfAngle, this.maxRange);
         boolean self = this.excludeSelf && livingentity == attacker;
         boolean see = ITargetFilter.hasLineOfSight(attacker, livingentity);
         if (!self && flag && inAngle && see) {
            targets.add(livingentity);
         }
      }

      return targets;
   }

   public double getMaxRange() {
      return this.maxRange;
   }
}
