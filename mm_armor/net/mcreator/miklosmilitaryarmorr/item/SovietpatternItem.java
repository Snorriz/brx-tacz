package net.mcreator.miklosmilitaryarmorr.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.state.BlockState;

public class SovietpatternItem extends Item {
   public SovietpatternItem() {
      super(new Properties().m_41503_(100).m_41497_(Rarity.COMMON));
   }

   public boolean m_41470_() {
      return true;
   }

   public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
      ItemStack retval = new ItemStack(this);
      retval.m_41721_(itemstack.m_41773_() + 1);
      return retval.m_41773_() >= retval.m_41776_() ? ItemStack.f_41583_ : retval;
   }

   public boolean isRepairable(ItemStack itemstack) {
      return false;
   }

   public int m_6473_() {
      return 5;
   }

   public float m_8102_(ItemStack par1ItemStack, BlockState par2Block) {
      return 3.0F;
   }

   public boolean m_8096_(BlockState state) {
      return true;
   }
}
