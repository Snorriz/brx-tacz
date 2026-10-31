package me.xjqsh.lrtactical.resource.manager;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tacz.guns.GunMod;
import com.tacz.guns.resource.manager.JsonDataManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import me.xjqsh.lrtactical.init.ModRegistries;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import me.xjqsh.lrtactical.item.throwable.ThrowableType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ThrowableIndexManager extends JsonDataManager<ThrowableIndex<?, ?>> {
   private Map<ResourceLocation, String> networkCache = new HashMap();

   public ThrowableIndexManager(Gson pGson) {
      super(null, pGson, "index/throwable", "ThrowableIndex");
   }

   protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
      this.dataMap.clear();
      Builder<ResourceLocation, String> builder = ImmutableMap.builder();

      for (Entry<ResourceLocation, JsonElement> entry : pObject.entrySet()) {
         ResourceLocation id = (ResourceLocation)entry.getKey();
         JsonElement element = (JsonElement)entry.getValue();
         if (!element.isJsonObject()) {
            GunMod.LOGGER.error(this.getMarker(), "Failed to load index file {}: Expected object, got {} ", id, element);
         } else {
            JsonObject pJson = element.getAsJsonObject();

            try {
               ThrowableIndex<?, ?> index = parse(pJson, id);
               this.dataMap.put(id, index);
               builder.put(id, pJson.toString());
            } catch (JsonParseException | IllegalArgumentException e) {
               GunMod.LOGGER.error(this.getMarker(), "Failed to load index file {}", id, e);
            }
         }
      }

      this.networkCache = builder.build();
   }

   public Map<ResourceLocation, String> getCache() {
      return this.networkCache;
   }

   public static ThrowableIndex<?, ?> parse(JsonObject pJson, ResourceLocation id) throws JsonParseException {
      String name = GsonHelper.m_13851_(pJson, "name", "unknown.lrtactical.name");
      String tooltip = GsonHelper.m_13851_(pJson, "tooltip", null);
      String type_name = GsonHelper.m_13906_(pJson, "type");
      ThrowableType<?, ?> type = (ThrowableType<?, ?>)((IForgeRegistry)ModRegistries.THROWABLE_TYPE_SUPPLIER.get()).getValue(new ResourceLocation(type_name));
      if (type == null) {
         throw new JsonParseException("Unknown type name \"" + type_name + "\"");
      }

      String baseItem = GsonHelper.m_13851_(pJson, "base_item", "lrtactical:throwable");
      Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(baseItem));
      if (item == null) {
         throw new JsonParseException("Unknown item id \"" + type_name + "\"");
      }

      JsonObject data = GsonHelper.m_13930_(pJson, "data");
      return ThrowableIndex.deserialize(type, data, name, tooltip, id, item);
   }
}
