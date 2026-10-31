package me.xjqsh.lrtactical.init;

import me.xjqsh.lrtactical.client.gui.ClothConfigScreen;
import me.xjqsh.lrtactical.compat.cloth.MenuIntegration;
import me.xjqsh.lrtactical.compat.player_animator.PlayerAnimatorHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;

@EventBusSubscriber(bus = Bus.MOD)
public class CompatRegistry {
   public static final String CLOTH_CONFIG = "cloth_config";
   public static final String PLAYER_ANIMATOR = "playeranimator";

   @SubscribeEvent
   public static void onEnqueue(InterModEnqueueEvent event) {
      event.enqueueWork(() -> checkModLoad("cloth_config", () -> DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> MenuIntegration::registerModsPage)));
      event.enqueueWork(() -> {
         if (FMLEnvironment.dist == Dist.CLIENT) {
            ClothConfigScreen.registerNoClothConfigPage();
         }
      });
   }

   @SubscribeEvent
   public static void onCommonSetup(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> checkModLoad("playeranimator", () -> DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> PlayerAnimatorHelper::initIntegration)));
   }

   public static void checkModLoad(String modId, Runnable runnable) {
      if (ModList.get().isLoaded(modId)) {
         runnable.run();
      }
   }
}
