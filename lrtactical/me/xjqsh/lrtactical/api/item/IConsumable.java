package me.xjqsh.lrtactical.api.item;

import java.util.Objects;
import java.util.Optional;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IConsumable extends ICustomItem {
   String ID_TAG = "ConsumableId";
   String OVERRIDE_DISPLAY_ID = "DisplayId";
   ResourceLocation EMPTY = new ResourceLocation("lrtactical", "empty");

   static IConsumable of(ItemStack stack) {
      return stack.m_41720_() instanceof IConsumable item ? item : null;
   }

   default ResourceLocation getId(ItemStack stack) {
      CompoundTag nbt = stack.m_41784_();
      if (nbt.m_128425_("ConsumableId", 8)) {
         ResourceLocation rl = ResourceLocation.m_135820_(nbt.m_128461_("ConsumableId"));
         return (ResourceLocation)Objects.requireNonNullElse(rl, EMPTY);
      } else {
         return EMPTY;
      }
   }

   default ResourceLocation getDisplayId(ItemStack stack) {
      CompoundTag nbt = stack.m_41784_();
      if (nbt.m_128425_("DisplayId", 8)) {
         ResourceLocation rl = ResourceLocation.m_135820_(nbt.m_128461_("DisplayId"));
         return (ResourceLocation)Objects.requireNonNullElse(rl, EMPTY);
      } else {
         return this.getId(stack);
      }
   }

   default void setId(ItemStack stack, ResourceLocation id) {
      stack.m_41784_().m_128359_("ConsumableId", id.toString());
   }

   default boolean isSame(ItemStack i, ItemStack j) {
      IConsumable c1 = of(i);
      IConsumable c2 = of(j);
      if (c1 != null && c2 != null) {
         return c1.getId(i).equals(c2.getId(j));
      } else {
         return !i.m_41619_() && !j.m_41619_() ? false : i.m_41619_() && j.m_41619_();
      }
   }

   default Optional<ResourceLocation> getCoolDownId(ItemStack stack) {
      return this.getConsumableIndex(stack).map(index -> index.getData().getCooldownCategory());
   }

   default int getMaxUsingTick(ItemStack stack) {
      return (Integer)this.getConsumableIndex(stack).map(index -> index.getData().getUseDuration()).orElse(0);
   }

   default Optional<ConsumableIndex> getConsumableIndex(ItemStack stack) {
      return LrTacticalAPI.getConsumableIndex(stack);
   }

   default boolean spawnParticleOnBreak(ItemStack stack) {
      return false;
   }
}
