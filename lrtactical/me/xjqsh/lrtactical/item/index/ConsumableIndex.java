package me.xjqsh.lrtactical.item.index;

import com.google.gson.JsonElement;
import me.xjqsh.lrtactical.api.index.ICustomItemIndex;
import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.item.consumable.ConsumableData;
import me.xjqsh.lrtactical.item.consumable.ConsumableData.EffectData;
import me.xjqsh.lrtactical.resource.CommonAssetsManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.Builder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ConsumableIndex implements ICustomItemIndex {
   private final Item baseItem;
   private final ConsumableData data;
   private final ResourceLocation id;
   private final String name;
   private final String tooltip;
   private final FoodProperties foodProperties;

   private ConsumableIndex(ConsumableData data, String name, String tooltip, ResourceLocation id, Item baseItem) {
      this.baseItem = baseItem;
      this.data = data;
      this.id = id;
      this.name = name;
      this.tooltip = tooltip;
      this.foodProperties = buildFoodProperties(data);
   }

   private static FoodProperties buildFoodProperties(ConsumableData data) {
      if (data.getFood() <= 0 && data.getSaturation() <= 0.0F && data.getEffects().isEmpty()) {
         return null;
      }

      Builder builder = new Builder().m_38760_(data.getFood()).m_38758_(data.getSaturation());

      for (EffectData effectData : data.getEffects()) {
         builder.effect(effectData::createInstance, effectData.getChance());
      }

      return builder.m_38767_();
   }

   @Nullable
   public static ConsumableIndex deserialize(JsonElement data, String name, String tooltip, ResourceLocation id, Item baseItem) {
      ConsumableData consumableData = (ConsumableData)CommonAssetsManager.GSON.fromJson(data, ConsumableData.class);
      return consumableData == null ? null : new ConsumableIndex(consumableData, name, tooltip, id, baseItem);
   }

   public ConsumableData getData() {
      return this.data;
   }

   @Nullable
   public String getTooltip() {
      return this.tooltip;
   }

   public ItemStack createItemStack() {
      ItemStack stack = new ItemStack(this.baseItem);
      if (stack.m_41720_() instanceof IConsumable consumable) {
         consumable.setId(stack, this.getId());
      }

      return stack;
   }

   public int getMaxStackSize() {
      return this.data.hasDurability() ? 1 : this.data.getStackSize();
   }

   public ResourceLocation getId() {
      return this.id;
   }

   public Item getBaseItem() {
      return this.baseItem;
   }

   public String getDescriptionId() {
      return this.name;
   }

   public FoodProperties getFoodProperties() {
      return this.foodProperties;
   }
}
