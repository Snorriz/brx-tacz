package me.xjqsh.lrtactical.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;

public class SightTraceUtil {
   @Nullable
   public static HitResult rayTraceOpaqueBlocks(
      Entity caster, Level world, Vec3 start, Vec3 end, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock
   ) {
      if (Double.isNaN(start.f_82479_) || Double.isNaN(start.f_82480_) || Double.isNaN(start.f_82481_)) {
         return null;
      }

      if (!Double.isNaN(end.f_82479_) && !Double.isNaN(end.f_82480_) && !Double.isNaN(end.f_82481_)) {
         int endX = Mth.m_14107_(end.f_82479_);
         int endY = Mth.m_14107_(end.f_82480_);
         int endZ = Mth.m_14107_(end.f_82481_);
         int startX = Mth.m_14107_(start.f_82479_);
         int startY = Mth.m_14107_(start.f_82480_);
         int startZ = Mth.m_14107_(start.f_82481_);
         BlockPos pos = new BlockPos(startX, startY, startZ);
         BlockState stateInside = world.m_8055_(pos);
         if (stateInside.m_60739_(world, pos) == 0 || ignoreBlockWithoutBoundingBox && stateInside.m_60812_(world, pos) == Shapes.m_83040_()) {
            HitResult raytraceresult2 = null;
            int limit = 200;

            while (limit-- >= 0) {
               if (!Double.isNaN(start.f_82479_) && !Double.isNaN(start.f_82480_) && !Double.isNaN(start.f_82481_)) {
                  if (startX == endX && startY == endY && startZ == endZ) {
                     return null;
                  }

                  boolean completedX = true;
                  boolean completedY = true;
                  boolean completedZ = true;
                  double d0 = 999.0;
                  double d1 = 999.0;
                  double d2 = 999.0;
                  if (endX > startX) {
                     d0 = startX + 1;
                  } else if (endX < startX) {
                     d0 = startX;
                  } else {
                     completedX = false;
                  }

                  if (endY > startY) {
                     d1 = startY + 1;
                  } else if (endY < startY) {
                     d1 = startY;
                  } else {
                     completedY = false;
                  }

                  if (endZ > startZ) {
                     d2 = startZ + 1;
                  } else if (endZ < startZ) {
                     d2 = startZ;
                  } else {
                     completedZ = false;
                  }

                  double d3 = 999.0;
                  double d4 = 999.0;
                  double d5 = 999.0;
                  double d6 = end.f_82479_ - start.f_82479_;
                  double d7 = end.f_82480_ - start.f_82480_;
                  double d8 = end.f_82481_ - start.f_82481_;
                  if (completedX) {
                     d3 = (d0 - start.f_82479_) / d6;
                  }

                  if (completedY) {
                     d4 = (d1 - start.f_82480_) / d7;
                  }

                  if (completedZ) {
                     d5 = (d2 - start.f_82481_) / d8;
                  }

                  if (d3 == 0.0) {
                     d3 = -1.0E-4;
                  }

                  if (d4 == 0.0) {
                     d4 = -1.0E-4;
                  }

                  if (d5 == 0.0) {
                     d5 = -1.0E-4;
                  }

                  Direction direction;
                  if (d3 < d4 && d3 < d5) {
                     direction = endX > startX ? Direction.WEST : Direction.EAST;
                     start = new Vec3(d0, start.f_82480_ + d7 * d3, start.f_82481_ + d8 * d3);
                  } else if (d4 < d5) {
                     direction = endY > startY ? Direction.DOWN : Direction.UP;
                     start = new Vec3(start.f_82479_ + d6 * d4, d1, start.f_82481_ + d8 * d4);
                  } else {
                     direction = endZ > startZ ? Direction.NORTH : Direction.SOUTH;
                     start = new Vec3(start.f_82479_ + d6 * d5, start.f_82480_ + d7 * d5, d2);
                  }

                  startX = Mth.m_14107_(start.f_82479_) - (direction == Direction.EAST ? 1 : 0);
                  startY = Mth.m_14107_(start.f_82480_) - (direction == Direction.UP ? 1 : 0);
                  startZ = Mth.m_14107_(start.f_82481_) - (direction == Direction.SOUTH ? 1 : 0);
                  pos = new BlockPos(startX, startY, startZ);
                  BlockState state = world.m_8055_(pos);
                  if (state.m_60739_(world, pos) == 0 || ignoreBlockWithoutBoundingBox && state.m_60812_(world, pos) == Shapes.m_83040_()) {
                     continue;
                  }

                  return world.m_45547_(new ClipContext(start, end, Block.COLLIDER, Fluid.NONE, caster));
               }

               return null;
            }

            return null;
         } else {
            return world.m_45547_(new ClipContext(start, end, Block.COLLIDER, Fluid.NONE, caster));
         }
      } else {
         return null;
      }
   }
}
