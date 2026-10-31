package net.mcreator.miklosmilitaryarmorr.init;

import net.mcreator.miklosmilitaryarmorr.client.gui.ChangelogguiScreen;
import net.mcreator.miklosmilitaryarmorr.client.gui.Factionbook1Screen;
import net.mcreator.miklosmilitaryarmorr.client.gui.FactionbookcsouScreen;
import net.mcreator.miklosmilitaryarmorr.client.gui.FactionbookcsoutoolsScreen;
import net.mcreator.miklosmilitaryarmorr.client.gui.FactionbookcsouuniformScreen;
import net.mcreator.miklosmilitaryarmorr.client.gui.FactionbookuconScreen;
import net.mcreator.miklosmilitaryarmorr.client.gui.FactionbookucontoolsScreen;
import net.mcreator.miklosmilitaryarmorr.client.gui.FactionbookuconuniformScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class MmArmorModScreens {
   @SubscribeEvent
   public static void clientLoad(FMLClientSetupEvent event) {
      event.enqueueWork(() -> {
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.FACTIONBOOK_1.get(), Factionbook1Screen::new);
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.FACTIONBOOKUCON.get(), FactionbookuconScreen::new);
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.FACTIONBOOKCSOU.get(), FactionbookcsouScreen::new);
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.FACTIONBOOKCSOUTOOLS.get(), FactionbookcsoutoolsScreen::new);
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.FACTIONBOOKUCONTOOLS.get(), FactionbookucontoolsScreen::new);
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.FACTIONBOOKCSOUUNIFORM.get(), FactionbookcsouuniformScreen::new);
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.FACTIONBOOKUCONUNIFORM.get(), FactionbookuconuniformScreen::new);
         MenuScreens.m_96206_((MenuType)MmArmorModMenus.CHANGELOGGUI.get(), ChangelogguiScreen::new);
      });
   }
}
