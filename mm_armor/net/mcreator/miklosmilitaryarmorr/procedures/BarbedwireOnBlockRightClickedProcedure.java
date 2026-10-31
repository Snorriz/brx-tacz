package net.mcreator.miklosmilitaryarmorr.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map.Entry;
import net.mcreator.miklosmilitaryarmorr.init.MmArmorModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class BarbedwireOnBlockRightClickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      BlockPos _bp = BlockPos.m_274561_(x, y, z);
      BlockState _bs = ((Block)MmArmorModBlocks.BARBEDWIREDESTROYED.get()).m_49966_();
      BlockState _bso = world.m_8055_(_bp);
      UnmodifiableIterator var10 = _bso.m_61148_().entrySet().iterator();

      while (var10.hasNext()) {
         Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var10.next();
         Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
         if (_property != null && _bs.m_61143_(_property) != null) {
            try {
               _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
            } catch (Exception var14) {
            }
         }
      }

      world.m_7731_(_bp, _bs, 3);
   }
}
