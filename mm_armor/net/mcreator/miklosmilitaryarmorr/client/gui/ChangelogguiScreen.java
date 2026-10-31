package net.mcreator.miklosmilitaryarmorr.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.HashMap;
import net.mcreator.miklosmilitaryarmorr.world.inventory.ChangelogguiMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ChangelogguiScreen extends AbstractContainerScreen<ChangelogguiMenu> {
   private static final HashMap<String, Object> guistate = ChangelogguiMenu.guistate;
   private final Level world;
   private final int x;
   private final int y;
   private final int z;
   private final Player entity;

   public ChangelogguiScreen(ChangelogguiMenu container, Inventory inventory, Component text) {
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
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.changeloggui.label_change_log"), 70, 6, -12829636, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.changeloggui.label_bug_fixes"), 10, 24, -12829636, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.changeloggui.label_shield_position_fix"), 10, 42, -12829636, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.changeloggui.label_remaked_hats"), 10, 60, -12829636, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.changeloggui.label_added_6b45_vest_without_transmi"), 10, 78, -12829636, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.changeloggui.label_new_knifes"), 10, 105, -12829636, false);
      guiGraphics.m_280614_(this.f_96547_, Component.m_237115_("gui.mm_armor.changeloggui.label_transmitter"), 20, 87, -12829636, false);
   }

   public void m_7856_() {
      super.m_7856_();
   }
}
