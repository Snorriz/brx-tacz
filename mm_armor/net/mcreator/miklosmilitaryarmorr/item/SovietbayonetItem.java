package net.mcreator.miklosmilitaryarmorr.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;

public class SovietbayonetItem extends SwordItem {
   public SovietbayonetItem() {
      super(new Tier() {
         public int m_6609_() {
            return 450;
         }

         public float m_6624_() {
            return 4.0F;
         }

         public float m_6631_() {
            return -0.0F;
         }

         public int m_6604_() {
            return 0;
         }

         public int m_6601_() {
            return 7;
         }

         public Ingredient m_6282_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack(Items.f_42416_)});
         }
      }, 3, -2.4F, new Properties());
   }

   public boolean hasCraftingRemainingItem(ItemStack stack) {
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
}
