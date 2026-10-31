package me.xjqsh.lrtactical.api.collision;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class OBBFilter implements ITargetFilter {
   @SerializedName("max_range")
   private double maxRange = 4.0;
   @SerializedName("half_width")
   private double halfWidth = 0.5;
   @SerializedName("half_height")
   private double halfHeight = 0.5;
   @SerializedName("exclude_self")
   private boolean excludeSelf = true;
   @SerializedName("roll")
   private float roll = 0.0F;

   @NotNull
   public List<Entity> filterTargets(LivingEntity attacker, Vec3 origin, Vec3 direction) {
      Quaternionf rotation = new Quaternionf()
         .rotationYXZ(
            -attacker.m_146908_() * (float) (Math.PI / 180.0), attacker.m_146909_() * (float) (Math.PI / 180.0), this.roll * (float) (Math.PI / 180.0)
         );
      Vector3f extents = new Vec3(this.halfWidth, this.halfHeight, this.maxRange / 2.0).m_252839_();
      Vector3f center = origin.m_82549_(direction.m_82490_(this.maxRange / 2.0)).m_252839_();
      OBB obb = new OBB(center, extents, rotation);
      List<Entity> targets = new ArrayList();
      AABB area = attacker.m_20191_().m_82377_(this.maxRange * 2.0, this.maxRange, this.maxRange * 2.0);

      for (Entity livingentity : attacker.m_9236_().m_45976_(Entity.class, area)) {
         boolean flag = !(livingentity instanceof ArmorStand armorStand && armorStand.m_31677_());
         boolean see = ITargetFilter.hasLineOfSight(attacker, livingentity);
         boolean self = this.excludeSelf && livingentity == attacker;
         if (!self && flag && obb.interactsWithAABB(livingentity.m_20191_()) && see) {
            targets.add(livingentity);
         }
      }

      return targets;
   }

   public double getMaxRange() {
      return this.maxRange;
   }
}
