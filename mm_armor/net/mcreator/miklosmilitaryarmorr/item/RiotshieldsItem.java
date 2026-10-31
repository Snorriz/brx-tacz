package net.mcreator.miklosmilitaryarmorr.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class RiotshieldsItem extends ShieldItem {
   public RiotshieldsItem() {
      super(new Properties().m_41503_(1000).m_41486_());
   }

   public boolean m_6832_(ItemStack itemstack, ItemStack repairitem) {
      return Ingredient.m_43927_(new ItemStack[]{new ItemStack(Items.f_42416_)}).test(repairitem);
   }
}
