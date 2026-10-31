package net.mcreator.miklosmilitaryarmorr.procedures;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map.Entry;
import net.mcreator.miklosmilitaryarmorr.init.MmArmorModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class Sandbags2OnBlockRightClickedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if ((entity instanceof LivingEntity _livEnt ? _livEnt.m_21205_() : ItemStack.f_41583_).m_41720_()
            == ((Block)MmArmorModBlocks.SANDBAGS.get()).m_5456_()) {
            BlockPos _bp = BlockPos.m_274561_(x, y, z);
            BlockState _bs = ((Block)MmArmorModBlocks.SANDBAGS_3.get()).m_49966_();
            BlockState _bso = world.m_8055_(_bp);
            UnmodifiableIterator var12 = _bso.m_61148_().entrySet().iterator();

            while (var12.hasNext()) {
               Entry<Property<?>, Comparable<?>> entry = (Entry<Property<?>, Comparable<?>>)var12.next();
               Property _property = _bs.m_60734_().m_49965_().m_61081_(((Property)entry.getKey()).m_61708_());
               if (_property != null && _bs.m_61143_(_property) != null) {
                  try {
                     _bs = (BlockState)_bs.m_61124_(_property, (Comparable)entry.getValue());
                  } catch (Exception var16) {
                  }
               }
            }

            world.m_7731_(_bp, _bs, 3);
            if (entity instanceof Player _player) {
               ItemStack _stktoremove = new ItemStack((ItemLike)MmArmorModBlocks.SANDBAGS.get());
               _player.m_150109_().m_36022_(p -> _stktoremove.m_41720_() == p.m_41720_(), 1, _player.f_36095_.m_39730_());
            }
         }
      }
   }
}
