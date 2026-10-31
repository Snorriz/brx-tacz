package net.mcreator.miklosmilitaryarmorr.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.miklosmilitaryarmorr.MmArmorMod;
import net.mcreator.miklosmilitaryarmorr.network.FactionbookuconuniformButtonMessage;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookuconuniformMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FactionbookuconuniformScreen extends AbstractContainerScreen<FactionbookuconuniformMenu> {
   private static final HashMap<String, Object> guistate = FactionbookuconuniformMenu.guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   Button button_assault;
   Button button_sniper;

   public FactionbookuconuniformScreen(FactionbookuconuniformMenu container, Inventory inventory, Component text) {
      super(container, inventory, text);
      this.world = container.world;
      this.x = container.x;
      this.y = container.y;
      this.z = container.z;
      this.entity = container.entity;
      this.f_97726_ = 250;
      this.f_97727_ = 166;
   }

   public void m_88315_(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
      this.m_280273_(guiGraphics);
      super.m_88315_(guiGraphics, mouseX, mouseY, partialTicks);
      this.m_280072_(guiGraphics, mouseX, mouseY);
   }

   protected void m_7286_(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      guiGraphics.m_280163_(
         new ResourceLocation("mm_armor:textures/screens/faction_book_gui.png"), this.f_97735_ + 34, this.f_97736_ + -2, 0.0F, 0.0F, 176, 165, 176, 165
      );
      RenderSystem.disableBlend();
   }

   public boolean m_7933_(int key, int b, int c) {
      if (key == 256) {
         this.f_96541_.f_91074_.m_6915_();
         return true;
      } else {
         return super.m_7933_(key, b, c);
      }
   }

   protected void m_280003_(GuiGraphics guiGraphics, int mouseX, int mouseY) {
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.factionbookuconuniform.label_ucon"), 43, 7, -12829636, false);
   }

   public void m_7856_() {
      super.m_7856_();
      this.button_assault = Button.m_253074_(Component.m_237115_("gui.mm_armor.factionbookuconuniform.button_assault"), e -> {
         MmArmorMod.PACKET_HANDLER.sendToServer(new FactionbookuconuniformButtonMessage(0, this.x, this.y, this.z));
         FactionbookuconuniformButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
      }).m_252987_(this.f_97735_ + 52, this.f_97736_ + 34, 61, 20).m_253136_();
      guistate.put("button:button_assault", this.button_assault);
      this.m_142416_(this.button_assault);
      this.button_sniper = Button.m_253074_(Component.m_237115_("gui.mm_armor.factionbookuconuniform.button_sniper"), e -> {
         MmArmorMod.PACKET_HANDLER.sendToServer(new FactionbookuconuniformButtonMessage(1, this.x, this.y, this.z));
         FactionbookuconuniformButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
      }).m_252987_(this.f_97735_ + 133, this.f_97736_ + 34, 56, 20).m_253136_();
      guistate.put("button:button_sniper", this.button_sniper);
      this.m_142416_(this.button_sniper);
   }
}
