package me.xjqsh.lrtactical.api.item;

import java.util.Objects;
import java.util.Optional;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IThrowable extends ICustomItem {
   String ID_TAG = "ThrowableId";
   String OVERRIDE_DISPLAY_ID = "DisplayId";
   ResourceLocation EMPTY = new ResourceLocation("lrtactical", "empty");

   static IThrowable of(ItemStack stack) {
      return stack.m_41720_() instanceof IThrowable item ? item : null;
   }

   default ResourceLocation getId(ItemStack stack) {
      CompoundTag nbt = stack.m_41784_();
      if (nbt.m_128425_("ThrowableId", 8)) {
         ResourceLocation gunId = ResourceLocation.m_135820_(nbt.m_128461_("ThrowableId"));
         return (ResourceLocation)Objects.requireNonNullElse(gunId, EMPTY);
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
      stack.m_41784_().m_128359_("ThrowableId", id.toString());
   }

   default boolean isSame(ItemStack i, ItemStack j) {
      IThrowable iThrowable1 = of(i);
      IThrowable iThrowable2 = of(j);
      if (iThrowable1 != null && iThrowable2 != null) {
         return iThrowable1.getId(i).equals(iThrowable2.getId(j));
      } else {
         return !i.m_41619_() && !j.m_41619_() ? false : i.m_41619_() && j.m_41619_();
      }
   }

   default Optional<ResourceLocation> getCoolDownId(ItemStack stack) {
      return this.getThrowableIndex(stack).map(index -> index.getData().getCooldownCategory());
   }

   default int getMaxUsingTick(ItemStack stack) {
      return (Integer)this.getThrowableIndex(stack).map(index -> index.getData().getPrepareTime()).orElse(0);
   }

   default Optional<ThrowableIndex<?, ?>> getThrowableIndex(ItemStack stack) {
      return LrTacticalAPI.getThrowableIndex(stack);
   }
}
