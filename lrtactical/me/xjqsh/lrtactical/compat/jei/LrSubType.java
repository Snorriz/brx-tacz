package me.xjqsh.lrtactical.compat.jei;

import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.api.item.IThrowable;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import net.minecraft.world.item.ItemStack;

public class LrSubType {
   public static IIngredientSubtypeInterpreter<ItemStack> getConsumableSubtype() {
      return (stack, context) -> stack.m_41720_() instanceof IConsumable consumable ? consumable.getId(stack).toString() : "";
   }

   public static IIngredientSubtypeInterpreter<ItemStack> getMeleeSubtype() {
      return (stack, context) -> stack.m_41720_() instanceof IMeleeWeapon iMeleeWeapon ? iMeleeWeapon.getId(stack).toString() : "";
   }

   public static IIngredientSubtypeInterpreter<ItemStack> getThrowableSubtype() {
      return (stack, context) -> stack.m_41720_() instanceof IThrowable iThrowable ? iThrowable.getId(stack).toString() : "";
   }
}
