package me.xjqsh.lrtactical.client.gui;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class ClothConfigScreen extends Screen {
   public static final String CLOTH_CONFIG_URL = "https://www.curseforge.com/minecraft/mc-mods/cloth-config";
   private final Screen lastScreen;
   private MultiLineLabel message = MultiLineLabel.f_94331_;

   protected ClothConfigScreen(Screen lastScreen) {
      super(Component.m_237113_("Cloth Config API"));
      this.lastScreen = lastScreen;
   }

   public static void registerNoClothConfigPage() {
      if (!ModList.get().isLoaded("cloth_config")) {
         ModLoadingContext.get()
            .registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((client, parent) -> new ClothConfigScreen(parent)));
      }
   }

   protected void m_7856_() {
      int posX = (this.f_96543_ - 200) / 2;
      int posY = this.f_96544_ / 2;
      this.message = MultiLineLabel.m_94341_(this.f_96547_, Component.m_237115_("gui.tacz.cloth_config_warning.tips"), 300);
      this.m_142416_(
         Button.m_253074_(
               Component.m_237115_("gui.tacz.cloth_config_warning.download"), b -> this.openUrl("https://www.curseforge.com/minecraft/mc-mods/cloth-config")
            )
            .m_252987_(posX, posY - 15, 200, 20)
            .m_253136_()
      );
      this.m_142416_(
         Button.m_253074_(CommonComponents.f_130660_, b -> Minecraft.m_91087_().m_91152_(this.lastScreen)).m_252987_(posX, posY + 50, 200, 20).m_253136_()
      );
   }

   public void m_88315_(@NotNull GuiGraphics gui, int pMouseX, int pMouseY, float pPartialTick) {
      this.m_280273_(gui);
      this.message.m_6276_(gui, this.f_96543_ / 2, 80);
      super.m_88315_(gui, pMouseX, pMouseY, pPartialTick);
   }

   private void openUrl(String url) {
      if (StringUtils.isNotBlank(url) && this.f_96541_ != null) {
         this.f_96541_.m_91152_(new ConfirmLinkScreen(yes -> {
            if (yes) {
               Util.m_137581_().m_137646_(url);
            }

            this.f_96541_.m_91152_(this);
         }, url, true));
      }
   }
}
