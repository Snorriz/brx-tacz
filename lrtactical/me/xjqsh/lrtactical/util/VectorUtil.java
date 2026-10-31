package me.xjqsh.lrtactical.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VectorUtil {
   public static Vec3 distanceVector(Vec3 point, AABB box) {
      double dx = 0.0;
      if (box.f_82288_ > point.f_82479_) {
         dx = box.f_82288_ - point.f_82479_;
      } else if (box.f_82291_ < point.f_82479_) {
         dx = box.f_82291_ - point.f_82479_;
      }

      double dy = 0.0;
      if (box.f_82289_ > point.f_82480_) {
         dy = box.f_82289_ - point.f_82480_;
      } else if (box.f_82292_ < point.f_82480_) {
         dy = box.f_82292_ - point.f_82480_;
      }

      double dz = 0.0;
      if (box.f_82290_ > point.f_82481_) {
         dz = box.f_82290_ - point.f_82481_;
      } else if (box.f_82293_ < point.f_82481_) {
         dz = box.f_82293_ - point.f_82481_;
      }

      return new Vec3(dx, dy, dz);
   }

   public static double angleBetween(Vec3 v1, Vec3 v2) {
      return Math.toDegrees(Math.acos(v1.m_82541_().m_82526_(v2.m_82541_())));
   }

   public static boolean isInAngle(Vec3 origin, Vec3 view, Entity target, double maxAngle, double maxDistance) {
      Vec3 positionVector = target.m_20182_().m_82520_(0.0, target.m_20206_() / 2.0F, 0.0).m_82546_(origin);
      Vec3 distanceVector = distanceVector(origin, target.m_20191_());
      double distance = distanceVector.m_82553_();
      if (distance == 0.0) {
         return true;
      } else {
         return distance > maxDistance ? false : angleBetween(view, distanceVector) <= maxAngle || angleBetween(view, positionVector) <= maxAngle;
      }
   }
}
