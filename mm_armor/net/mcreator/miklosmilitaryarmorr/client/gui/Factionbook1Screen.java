package net.mcreator.miklosmilitaryarmorr.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.miklosmilitaryarmorr.MmArmorMod;
import net.mcreator.miklosmilitaryarmorr.network.Factionbook1ButtonMessage;
import net.mcreator.miklosmilitaryarmorr.world.inventory.Factionbook1Menu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Factionbook1Screen extends AbstractContainerScreen<Factionbook1Menu> {
   private static final HashMap<String, Object> guistate = Factionbook1Menu.guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;
   ImageButton imagebutton_csoj_flag_test;
   ImageButton imagebutton_ucon_flag;

   public Factionbook1Screen(Factionbook1Menu container, Inventory inventory, Component text) {
      super(container, inventory, text);
      this.world = container.world;
      this.x = container.x;
      this.y = container.y;
      this.z = container.z;
      this.entity = container.entity;
      this.f_97726_ = 176;
      this.f_97727_ = 165;
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
         new ResourceLocation("mm_armor:textures/screens/faction_book_gui.png"), this.f_97735_ + 0, this.f_97736_ + -3, 0.0F, 0.0F, 176, 165, 176, 165
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
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.factionbook_1.label_ucon"), 28, 42, -12829636, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.factionbook_1.label_csou"), 124, 42, -12829636, false);
   }

   public void m_7856_() {
      super.m_7856_();
      this.imagebutton_csoj_flag_test = new ImageButton(
         this.f_97735_ + 104,
         this.f_97736_ + 60,
         60,
         40,
         0,
         0,
         40,
         new ResourceLocation("mm_armor:textures/screens/atlas/imagebutton_csoj_flag_test.png"),
         60,
         80,
         e -> {
            MmArmorMod.PACKET_HANDLER.sendToServer(new Factionbook1ButtonMessage(0, this.x, this.y, this.z));
            Factionbook1ButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_csoj_flag_test", this.imagebutton_csoj_flag_test);
      this.m_142416_(this.imagebutton_csoj_flag_test);
      this.imagebutton_ucon_flag = new ImageButton(
         this.f_97735_ + 10,
         this.f_97736_ + 60,
         60,
         40,
         0,
         0,
         40,
         new ResourceLocation("mm_armor:textures/screens/atlas/imagebutton_ucon_flag.png"),
         60,
         80,
         e -> {
            MmArmorMod.PACKET_HANDLER.sendToServer(new Factionbook1ButtonMessage(1, this.x, this.y, this.z));
            Factionbook1ButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
         }
      );
      guistate.put("button:imagebutton_ucon_flag", this.imagebutton_ucon_flag);
      this.m_142416_(this.imagebutton_ucon_flag);
   }
}
