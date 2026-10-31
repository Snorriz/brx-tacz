package net.mcreator.miklosmilitaryarmorr.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.miklosmilitaryarmorr.MmArmorMod;
import net.mcreator.miklosmilitaryarmorr.network.FactionbookcsouButtonMessage;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookcsouMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FactionbookcsouScreen extends AbstractContainerScreen<FactionbookcsouMenu> {
   private static final HashMap<String, Object> guistate = FactionbookcsouMenu.guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   Button button_get_uniform;
   Button button_make_team;
   Button button_join_team;
   Button button_tools;

   public FactionbookcsouScreen(FactionbookcsouMenu container, Inventory inventory, Component text) {
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
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.factionbookcsou.label_ucon"), 43, 7, -12829636, false);
   }

   public void m_7856_() {
      super.m_7856_();
      this.button_get_uniform = Button.m_253074_(Component.m_237115_("gui.mm_armor.factionbookcsou.button_get_uniform"), e -> {
         MmArmorMod.PACKET_HANDLER.sendToServer(new FactionbookcsouButtonMessage(0, this.x, this.y, this.z));
         FactionbookcsouButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
      }).m_252987_(this.f_97735_ + 79, this.f_97736_ + 25, 82, 20).m_253136_();
      guistate.put("button:button_get_uniform", this.button_get_uniform);
      this.m_142416_(this.button_get_uniform);
      this.button_make_team = Button.m_253074_(Component.m_237115_("gui.mm_armor.factionbookcsou.button_make_team"), e -> {
         MmArmorMod.PACKET_HANDLER.sendToServer(new FactionbookcsouButtonMessage(1, this.x, this.y, this.z));
         FactionbookcsouButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
      }).m_252987_(this.f_97735_ + 82, this.f_97736_ + 86, 72, 20).m_253136_();
      guistate.put("button:button_make_team", this.button_make_team);
      this.m_142416_(this.button_make_team);
      this.button_join_team = Button.m_253074_(Component.m_237115_("gui.mm_armor.factionbookcsou.button_join_team"), e -> {
         MmArmorMod.PACKET_HANDLER.sendToServer(new FactionbookcsouButtonMessage(2, this.x, this.y, this.z));
         FactionbookcsouButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z);
      }).m_252987_(this.f_97735_ + 83, this.f_97736_ + 116, 72, 20).m_253136_();
      guistate.put("button:button_join_team", this.button_join_team);
      this.m_142416_(this.button_join_team);
      this.button_tools = Button.m_253074_(Component.m_237115_("gui.mm_armor.factionbookcsou.button_tools"), e -> {
         MmArmorMod.PACKET_HANDLER.sendToServer(new FactionbookcsouButtonMessage(3, this.x, this.y, this.z));
         FactionbookcsouButtonMessage.handleButtonAction(this.entity, 3, this.x, this.y, this.z);
      }).m_252987_(this.f_97735_ + 94, this.f_97736_ + 55, 51, 20).m_253136_();
      guistate.put("button:button_tools", this.button_tools);
      this.m_142416_(this.button_tools);
   }
}
