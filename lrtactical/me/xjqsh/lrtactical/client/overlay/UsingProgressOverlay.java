package me.xjqsh.lrtactical.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.api.item.ICustomItem;
import me.xjqsh.lrtactical.api.item.IThrowable;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import me.xjqsh.lrtactical.item.throwable.ThrowableData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class UsingProgressOverlay implements IGuiOverlay {
   public static final ResourceLocation ARROW_TEXTURE = new ResourceLocation("lrtactical", "textures/gui/arrow.png");

   public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
      Minecraft mc = Minecraft.m_91087_();
      Player player = mc.f_91074_;
      if (player != null) {
         ItemStack stack = player.m_21211_();
         if (stack.m_41720_() instanceof ICustomItem item) {
            float maxTick = item.getMaxUsingTick(stack);
            int usingTick = player.m_21252_();
            float progress = Math.min(1.0F, usingTick / maxTick);
            int x = screenWidth / 2 - 16;
            int y = screenHeight / 2 + 16;
            int alpha;
            if (progress == 1.0F) {
               alpha = (int)(80.0 + 80.0 * Math.sin(usingTick / 2.0F));
            } else {
               alpha = 128;
            }

            boolean isToggleMode = false;
            if (stack.m_41720_() instanceof IConsumable) {
               ConsumableIndex index = (ConsumableIndex)LrTacticalAPI.getConsumableIndex(stack).orElse(null);
               if (index != null && index.getData().isToggleUse()) {
                  isToggleMode = true;
               }
            }

            int barColor = isToggleMode ? 65280 : 16777215;
            guiGraphics.m_280509_(x, y, (int)(x + progress * 32.0F), y + 4, barColor | alpha << 24);
            if (stack.m_41720_() instanceof IThrowable throwable) {
               throwable.getThrowableIndex(stack).ifPresent(index -> {
                  ThrowableData data = index.getData();
                  if (data.isCookable() && player.m_21252_() >= data.getPrepareTime()) {
                     int cookTime = player.m_21252_() - data.getPrepareTime();
                     float cookProgress = Math.min(1.0F, (float)cookTime / data.getEntityData().getLifeTime());
                     guiGraphics.m_280509_(x, y, (int)(x + cookProgress * 32.0F), y + 4, 0xFF0000 | alpha << 24);
                  }
               });
               if (player.m_6047_()) {
                  RenderSystem.enableBlend();
                  RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.7F);
                  guiGraphics.m_280163_(ARROW_TEXTURE, x + 12, y + 6, 0.0F, 0.0F, 8, 4, 8, 4);
                  RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                  RenderSystem.disableBlend();
               }
            }

            if (isToggleMode) {
               Component hint = Component.m_237115_("overlay.lrtactical.consumable.toggle_hint");
               int textWidth = mc.f_91062_.m_92852_(hint);
               guiGraphics.m_280614_(mc.f_91062_, hint, screenWidth / 2 - textWidth / 2, y + 8, 16777215, true);
            }
         }

         player.getCapability(CombatPropertiesProvider.CAPABILITY).ifPresent(cap -> {
            if (cap.getCoolDownTick() > 0) {
               float maxTick = cap.getLastMaxTick();
               float progressx = 1.0F - Math.min(1.0F, cap.getCoolDownTick() / maxTick);
               int xx = screenWidth / 2 - 16;
               int yx = screenHeight / 2 + 16;
               int alphax = 128;
               if (progressx == 1.0F) {
                  alphax = (int)(80.0 + 80.0 * Math.sin(cap.getCoolDownTick() / 2.0F));
               }

               guiGraphics.m_280509_(xx, yx, (int)(xx + progressx * 32.0F), yx + 4, 16777215 | alphax << 24);
            }
         });
      }
   }
}
