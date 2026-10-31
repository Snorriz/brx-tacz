package me.xjqsh.lrtactical.client.resource.manager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.tacz.guns.GunMod;
import com.tacz.guns.resource.manager.JsonDataManager;
import java.util.Map;
import java.util.Map.Entry;
import me.xjqsh.lrtactical.client.resource.display.ConsumableDisplayInstance;
import me.xjqsh.lrtactical.client.resource.display.ConsumableDisplayInstance.ConsumableDisplay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

public class ConsumableDisplayManager extends JsonDataManager<ConsumableDisplayInstance> {
   public ConsumableDisplayManager(Gson pGson) {
      super(null, pGson, "display/consumable", "ConsumableDisplay");
   }

   protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
      this.dataMap.clear();

      for (Entry<ResourceLocation, JsonElement> entry : pObject.entrySet()) {
         ResourceLocation id = (ResourceLocation)entry.getKey();
         JsonElement element = (JsonElement)entry.getValue();

         try {
            ConsumableDisplay pojo = (ConsumableDisplay)this.getGson().fromJson(element, ConsumableDisplay.class);
            ConsumableDisplayInstance data = ConsumableDisplayInstance.create(pojo, id);
            this.dataMap.put(id, data);
         } catch (JsonParseException | IllegalArgumentException e) {
            GunMod.LOGGER.error(this.getMarker(), "Failed to load display file {}", id, e);
         }
      }
   }
}
