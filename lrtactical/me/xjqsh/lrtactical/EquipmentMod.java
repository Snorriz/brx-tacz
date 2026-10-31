package me.xjqsh.lrtactical;

import me.xjqsh.lrtactical.config.ClientConfig;
import me.xjqsh.lrtactical.config.CommonConfig;
import me.xjqsh.lrtactical.config.ServerConfig;
import me.xjqsh.lrtactical.init.ModCustomTypes;
import me.xjqsh.lrtactical.init.ModEffects;
import me.xjqsh.lrtactical.init.ModEnchantment;
import me.xjqsh.lrtactical.init.ModEntities;
import me.xjqsh.lrtactical.init.ModItems;
import me.xjqsh.lrtactical.init.ModParticleTypes;
import me.xjqsh.lrtactical.init.ModSounds;
import me.xjqsh.lrtactical.network.NetworkHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("lrtactical")
public class EquipmentMod {
   public static final String MOD_ID = "lrtactical";
   public static final Logger LOGGER = LogManager.getLogger("lrtactical");

   public EquipmentMod() {
      ModLoadingContext.get().registerConfig(Type.CLIENT, ClientConfig.init());
      ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.init());
      ModLoadingContext.get().registerConfig(Type.SERVER, ServerConfig.init());
      IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
      ModItems.TABS.register(modEventBus);
      ModItems.ITEMS.register(modEventBus);
      ModEntities.ENTITY_TYPES.register(modEventBus);
      ModEnchantment.ENCHANTMENTS.register(modEventBus);
      ModParticleTypes.PARTICLE_TYPES.register(modEventBus);
      ModEffects.EFFECTS.register(modEventBus);
      ModCustomTypes.THROWABLE_TYPES.register(modEventBus);
      ModCustomTypes.MELEE_WEAPON_TYPES.register(modEventBus);
      ModSounds.SOUNDS.register(modEventBus);
      NetworkHandler.init();
   }
}
