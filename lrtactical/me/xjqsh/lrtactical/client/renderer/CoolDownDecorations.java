package me.xjqsh.lrtactical.client.renderer;

import me.xjqsh.lrtactical.api.item.ICustomItem;
import me.xjqsh.lrtactical.capability.CustomItemCoolDownsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemDecorator;

public class CoolDownDecorations implements IItemDecorator {
   public boolean render(GuiGraphics guiGraphics, Font font, ItemStack stack, int pX, int pY) {
      Player player = Minecraft.m_91087_().f_91074_;
      float partialTicks = Minecraft.m_91087_().m_91296_();
      if (player == null) {
         return false;
      }

      if (stack.m_41720_() instanceof ICustomItem item) {
         float f = (Float)item.getCoolDownId(stack)
            .map(id -> (Float)player.getCapability(CustomItemCoolDownsProvider.CAPABILITY).map(cap -> cap.getCooldownPercent(id, partialTicks)).orElse(0.0F))
            .orElse(0.0F);
         if (f > 0.0F) {
            int i1 = pY + Mth.m_14143_(16.0F * (1.0F - f));
            int j1 = i1 + Mth.m_14167_(16.0F * f);
            guiGraphics.m_285944_(RenderType.m_286086_(), pX, i1, pX + 16, j1, Integer.MAX_VALUE);
         }

         return true;
      } else {
         return false;
      }
   }
}
