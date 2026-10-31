package me.xjqsh.lrtactical.api;

import java.util.Collection;
import java.util.Optional;
import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.api.item.IThrowable;
import me.xjqsh.lrtactical.client.resource.LrClientAssetsManager;
import me.xjqsh.lrtactical.client.resource.display.ConsumableDisplayInstance;
import me.xjqsh.lrtactical.client.resource.display.MeleeDisplayInstance;
import me.xjqsh.lrtactical.client.resource.display.ThrowableDisplayInstance;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import me.xjqsh.lrtactical.resource.CommonAssetsManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LrTacticalAPI {
   @OnlyIn(Dist.CLIENT)
   public static Optional<ConsumableDisplayInstance> getConsumableDisplay(ItemStack stack) {
      if (stack.m_41720_() instanceof IConsumable item) {
         ConsumableDisplayInstance display = LrClientAssetsManager.INSTANCE.getConsumableDisplay(item.getDisplayId(stack));
         return display != null ? Optional.of(display) : Optional.ofNullable(LrClientAssetsManager.INSTANCE.getConsumableDisplay(item.getId(stack)));
      } else {
         return Optional.empty();
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static Optional<ConsumableDisplayInstance> getConsumableDisplay(ResourceLocation id) {
      return Optional.ofNullable(LrClientAssetsManager.INSTANCE.getConsumableDisplay(id));
   }

   @OnlyIn(Dist.CLIENT)
   public static Optional<ThrowableDisplayInstance> getThrowableDisplay(ItemStack stack) {
      if (stack.m_41720_() instanceof IThrowable item) {
         ThrowableDisplayInstance display = LrClientAssetsManager.INSTANCE.getThrowableDisplay(item.getDisplayId(stack));
         return display != null ? Optional.of(display) : Optional.ofNullable(LrClientAssetsManager.INSTANCE.getThrowableDisplay(item.getId(stack)));
      } else {
         return Optional.empty();
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static Optional<ThrowableDisplayInstance> getThrowableDisplay(ResourceLocation id) {
      return Optional.ofNullable(LrClientAssetsManager.INSTANCE.getThrowableDisplay(id));
   }

   @OnlyIn(Dist.CLIENT)
   public static Optional<MeleeDisplayInstance> getMeleeDisplay(ItemStack stack) {
      if (stack.m_41720_() instanceof IMeleeWeapon item) {
         MeleeDisplayInstance display = LrClientAssetsManager.INSTANCE.getMeleeDisplay(item.getDisplayId(stack));
         return display != null ? Optional.of(display) : Optional.ofNullable(LrClientAssetsManager.INSTANCE.getMeleeDisplay(item.getId(stack)));
      } else {
         return Optional.empty();
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static Optional<MeleeDisplayInstance> getMeleeDisplay(ResourceLocation index) {
      return Optional.ofNullable(LrClientAssetsManager.INSTANCE.getMeleeDisplay(index));
   }

   public static Optional<ThrowableIndex<?, ?>> getThrowableIndex(ItemStack stack) {
      return stack.m_41720_() instanceof IThrowable item
         ? Optional.ofNullable(CommonAssetsManager.get().getThrowableIndex(item.getId(stack)))
         : Optional.empty();
   }

   public static Collection<ThrowableIndex<?, ?>> getThrowableIndexes() {
      return CommonAssetsManager.get().getThrowableIndexes();
   }

   public static Optional<ConsumableIndex> getConsumableIndex(ItemStack stack) {
      return stack.m_41720_() instanceof IConsumable item
         ? Optional.ofNullable(CommonAssetsManager.get().getConsumableIndex(item.getId(stack)))
         : Optional.empty();
   }

   public static Collection<ConsumableIndex> getConsumableIndexes() {
      return CommonAssetsManager.get().getConsumableIndexes();
   }

   public static Optional<MeleeWeaponIndex<?>> getMeleeIndex(ItemStack stack) {
      return stack.m_41720_() instanceof IMeleeWeapon item ? Optional.ofNullable(CommonAssetsManager.get().getMeleeIndex(item.getId(stack))) : Optional.empty();
   }

   public static Collection<MeleeWeaponIndex<?>> getMeleeIndexes() {
      return CommonAssetsManager.get().getMeleeIndexes();
   }
}
