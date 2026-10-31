package me.xjqsh.lrtactical.resource;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import me.xjqsh.lrtactical.network.DataType;
import me.xjqsh.lrtactical.resource.manager.ConsumableIndexManager;
import me.xjqsh.lrtactical.resource.manager.MeleeIndexManager;
import me.xjqsh.lrtactical.resource.manager.ThrowableIndexManager;
import net.minecraft.resources.ResourceLocation;

public enum CommonNetworkCache implements ICommonResourceProvider {
   INSTANCE;

   public Map<ResourceLocation, ConsumableIndex> consumableIndex = Maps.newHashMap();
   public Map<ResourceLocation, ThrowableIndex<?, ?>> throwableIndex = Maps.newHashMap();
   public Map<ResourceLocation, MeleeWeaponIndex<?>> meleeWeaponIndex = Maps.newHashMap();

   public ConsumableIndex getConsumableIndex(ResourceLocation id) {
      return (ConsumableIndex)this.consumableIndex.get(id);
   }

   public Collection<ConsumableIndex> getConsumableIndexes() {
      return this.consumableIndex.values();
   }

   public ThrowableIndex<?, ?> getThrowableIndex(ResourceLocation id) {
      return (ThrowableIndex<?, ?>)this.throwableIndex.get(id);
   }

   public Collection<ThrowableIndex<?, ?>> getThrowableIndexes() {
      return this.throwableIndex.values();
   }

   public MeleeWeaponIndex<?> getMeleeIndex(ResourceLocation id) {
      return (MeleeWeaponIndex<?>)this.meleeWeaponIndex.get(id);
   }

   public Collection<MeleeWeaponIndex<?>> getMeleeIndexes() {
      return this.meleeWeaponIndex.values();
   }

   public void parseConsumableIndex(Map<ResourceLocation, String> cache) {
      Builder<ResourceLocation, ConsumableIndex> builder = ImmutableMap.builder();

      for (Entry<ResourceLocation, String> consumableEntry : cache.entrySet()) {
         JsonObject jsonObject = (JsonObject)CommonAssetsManager.GSON.fromJson((String)consumableEntry.getValue(), JsonObject.class);
         ConsumableIndex index = ConsumableIndexManager.parse(jsonObject, (ResourceLocation)consumableEntry.getKey());
         if (index != null) {
            builder.put((ResourceLocation)consumableEntry.getKey(), index);
         }
      }

      this.consumableIndex = builder.build();
   }

   public void parseThrowableIndex(Map<ResourceLocation, String> cache) {
      Builder<ResourceLocation, ThrowableIndex<?, ?>> builder = ImmutableMap.builder();

      for (Entry<ResourceLocation, String> throwableEntry : cache.entrySet()) {
         JsonObject jsonObject = (JsonObject)CommonAssetsManager.GSON.fromJson((String)throwableEntry.getValue(), JsonObject.class);
         ThrowableIndex<?, ?> index = ThrowableIndexManager.parse(jsonObject, (ResourceLocation)throwableEntry.getKey());
         if (index != null) {
            builder.put((ResourceLocation)throwableEntry.getKey(), index);
         }
      }

      this.throwableIndex = builder.build();
   }

   public void parseMeleeIndex(Map<ResourceLocation, String> cache) {
      Builder<ResourceLocation, MeleeWeaponIndex<?>> builder = ImmutableMap.builder();

      for (Entry<ResourceLocation, String> meleeEntry : cache.entrySet()) {
         JsonObject jsonObject = (JsonObject)CommonAssetsManager.GSON.fromJson((String)meleeEntry.getValue(), JsonObject.class);
         MeleeWeaponIndex<?> index = MeleeIndexManager.parse(jsonObject, (ResourceLocation)meleeEntry.getKey());
         if (index != null) {
            builder.put((ResourceLocation)meleeEntry.getKey(), index);
         }
      }

      this.meleeWeaponIndex = builder.build();
   }

   public void fromNetwork(Map<DataType, Map<ResourceLocation, String>> cache) {
      for (Entry<DataType, Map<ResourceLocation, String>> entry : cache.entrySet()) {
         switch ((DataType)entry.getKey()) {
            case CONSUMABLE_INDEX:
               this.parseConsumableIndex((Map<ResourceLocation, String>)entry.getValue());
               break;
            case THROWABLE_INDEX:
               this.parseThrowableIndex((Map<ResourceLocation, String>)entry.getValue());
               break;
            case MELEE_INDEX:
               this.parseMeleeIndex((Map<ResourceLocation, String>)entry.getValue());
         }
      }
   }
}
