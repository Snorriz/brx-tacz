package me.xjqsh.lrtactical.api.collision;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class RayFilter implements ITargetFilter {
   @SerializedName("max_range")
   private double maxRange = 2.5;
   @SerializedName("penetration")
   private int penetration = 0;

   public RayFilter(double maxDistance, int penetration) {
      this.maxRange = maxDistance;
      this.penetration = penetration;
   }

   public RayFilter(double maxDistance, int penetration, boolean excludeSelf, boolean excludeAllies) {
      this.maxRange = maxDistance;
      this.penetration = penetration;
   }

   @NotNull
   public List<Entity> filterTargets(LivingEntity attacker, Vec3 origin, Vec3 direction) {
      Vec3 to = origin.m_82549_(direction.m_82541_().m_82490_(this.maxRange));
      List<RayFilter.RayEntityHitResult> targets = this.findEntitiesOnPath(attacker, origin, to);
      return targets.stream().limit(this.penetration + 1).map(EntityHitResult::m_82443_).toList();
   }

   @NotNull
   public List<RayFilter.RayEntityHitResult> findEntitiesOnPath(LivingEntity attacker, Vec3 startVec, Vec3 endVec) {
      List<RayFilter.RayEntityHitResult> hitEntities = new ArrayList();
      AABB area = attacker.m_20191_().m_82369_(attacker.m_20252_(1.0F).m_82490_(this.maxRange)).m_82400_(1.0);

      for (Entity entity : attacker.m_9236_().m_6249_(attacker, area, EntitySelector.f_20408_)) {
         if (!entity.equals(attacker) && !entity.equals(attacker.m_20202_()) && entity.m_6084_() && ITargetFilter.hasLineOfSight(attacker, entity)) {
            Optional<Vec3> optional = entity.m_20191_().m_82371_(startVec, endVec);
            if (optional.isPresent()) {
               RayFilter.RayEntityHitResult result = new RayFilter.RayEntityHitResult(entity, (Vec3)optional.get(), startVec);
               hitEntities.add(result);
            } else if (entity.m_20191_().m_82390_(startVec)) {
               RayFilter.RayEntityHitResult result = new RayFilter.RayEntityHitResult(entity, startVec, startVec);
               hitEntities.add(result);
            }
         }
      }

      hitEntities.sort((a, b) -> (int)(a.getDistanceSqr() - b.getDistanceSqr()));
      return hitEntities;
   }

   public double getMaxRange() {
      return this.maxRange;
   }

   public static class RayEntityHitResult extends EntityHitResult {
      private final Vec3 source;
      private final double distanceSqr;

      public RayEntityHitResult(Entity entity, Vec3 location, Vec3 source) {
         super(entity, location);
         this.source = source;
         this.distanceSqr = source.m_82557_(location);
      }

      public Vec3 getSource() {
         return this.source;
      }

      public double getDistanceSqr() {
         return this.distanceSqr;
      }
   }
}
