package me.xjqsh.lrtactical.resource;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import me.xjqsh.lrtactical.api.collision.ITargetFilter;
import me.xjqsh.lrtactical.item.consumable.ConsumableData.RemoveEffectSelector;
import me.xjqsh.lrtactical.item.consumable.ConsumableData.RemoveEffectSelector.Deserializer;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import me.xjqsh.lrtactical.item.melee.AttributeData;
import me.xjqsh.lrtactical.item.melee.CombatData;
import me.xjqsh.lrtactical.item.throwable.area.EffectCloudThrowableData.EffectData;
import me.xjqsh.lrtactical.item.throwable.area.EffectCloudThrowableData.EffectDataDeSerializer;
import me.xjqsh.lrtactical.network.DataType;
import me.xjqsh.lrtactical.network.NetworkHandler;
import me.xjqsh.lrtactical.network.message.SPackSyncMessage;
import me.xjqsh.lrtactical.resource.manager.ConsumableIndexManager;
import me.xjqsh.lrtactical.resource.manager.MeleeIndexManager;
import me.xjqsh.lrtactical.resource.manager.ThrowableIndexManager;
import me.xjqsh.lrtactical.resource.serializer.ParticleOptionsDeserializer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceLocation.Serializer;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CommonAssetsManager implements ICommonResourceProvider {
   public static CommonAssetsManager INSTANCE;
   public static Gson GSON = new GsonBuilder()
      .registerTypeAdapter(ResourceLocation.class, new Serializer())
      .registerTypeAdapter(RemoveEffectSelector.class, new Deserializer())
      .registerTypeAdapter(CombatData.class, new me.xjqsh.lrtactical.item.melee.CombatData.Deserializer())
      .registerTypeAdapter(ITargetFilter.class, new me.xjqsh.lrtactical.api.collision.ITargetFilter.Deserializer())
      .registerTypeAdapter(AttributeData.class, new me.xjqsh.lrtactical.item.melee.AttributeData.Deserializer())
      .registerTypeAdapter(ParticleOptions.class, new ParticleOptionsDeserializer())
      .registerTypeAdapter(EffectData.class, new EffectDataDeSerializer())
      .create();
   public ThrowableIndexManager throwableIndexManager;
   public MeleeIndexManager meleeIndexManager;
   public ConsumableIndexManager consumableIndexManager;

   private CommonAssetsManager() {
   }

   public static ICommonResourceProvider get() {
      return (ICommonResourceProvider)(INSTANCE == null ? CommonNetworkCache.INSTANCE : INSTANCE);
   }

   private void reloadAndRegister(Consumer<PreparableReloadListener> register) {
      this.consumableIndexManager = new ConsumableIndexManager(GSON);
      this.throwableIndexManager = new ThrowableIndexManager(GSON);
      this.meleeIndexManager = new MeleeIndexManager(GSON);
      register.accept(this.consumableIndexManager);
      register.accept(this.throwableIndexManager);
      register.accept(this.meleeIndexManager);
   }

   public Map<DataType, Map<ResourceLocation, String>> toNetwork() {
      Builder<DataType, Map<ResourceLocation, String>> builder = ImmutableMap.builder();
      builder.put(DataType.CONSUMABLE_INDEX, this.consumableIndexManager.getCache());
      builder.put(DataType.THROWABLE_INDEX, this.throwableIndexManager.getCache());
      builder.put(DataType.MELEE_INDEX, this.meleeIndexManager.getCache());
      return builder.build();
   }

   public ConsumableIndex getConsumableIndex(ResourceLocation id) {
      return (ConsumableIndex)this.consumableIndexManager.getData(id);
   }

   public Collection<ConsumableIndex> getConsumableIndexes() {
      return this.consumableIndexManager.getAllData().values();
   }

   public ThrowableIndex<?, ?> getThrowableIndex(ResourceLocation id) {
      return (ThrowableIndex<?, ?>)this.throwableIndexManager.getData(id);
   }

   public Collection<ThrowableIndex<?, ?>> getThrowableIndexes() {
      return this.throwableIndexManager.getAllData().values();
   }

   public MeleeWeaponIndex<?> getMeleeIndex(ResourceLocation id) {
      return (MeleeWeaponIndex<?>)this.meleeIndexManager.getData(id);
   }

   public Collection<MeleeWeaponIndex<?>> getMeleeIndexes() {
      return this.meleeIndexManager.getAllData().values();
   }

   @SubscribeEvent
   public static void onReload(AddReloadListenerEvent event) {
      CommonAssetsManager commonAssetsManager = new CommonAssetsManager();
      commonAssetsManager.reloadAndRegister(event::addListener);
      INSTANCE = commonAssetsManager;
   }

   @SubscribeEvent
   public static void onServerStopped(ServerStoppedEvent event) {
      INSTANCE = null;
   }

   @SubscribeEvent
   public static void OnDatapackSync(OnDatapackSyncEvent event) {
      if (INSTANCE != null) {
         SPackSyncMessage msg = new SPackSyncMessage(INSTANCE.toNetwork());
         if (event.getPlayer() != null) {
            NetworkHandler.sendToClientPlayer(msg, event.getPlayer());
         } else {
            event.getPlayerList().m_11314_().forEach(player -> NetworkHandler.sendToClientPlayer(msg, player));
         }
      }
   }
}
