package me.xjqsh.lrtactical.init;

import me.xjqsh.lrtactical.capability.CombatProperties;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import me.xjqsh.lrtactical.capability.CustomItemCoolDowns;
import me.xjqsh.lrtactical.capability.CustomItemCoolDownsProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "lrtactical")
public class CommonSetupHandler {
   @SubscribeEvent
   public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
      if (event.getObject() instanceof Player player) {
         event.addCapability(CustomItemCoolDowns.ID, new CustomItemCoolDownsProvider(player));
         event.addCapability(CombatProperties.ID, new CombatPropertiesProvider(player));
      }
   }
}
