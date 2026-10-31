package me.xjqsh.lrtactical.client.input;

import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import me.xjqsh.lrtactical.network.NetworkHandler;
import me.xjqsh.lrtactical.network.message.CCancelToggleConsumableUse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.Key;
import net.minecraftforge.client.event.InputEvent.MouseButton.Pre;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT, modid = "lrtactical")
public class ConsumableInputHandler {
   @SubscribeEvent
   public static void onKeyInput(Key event) {
      if (event.getAction() == 1) {
         Minecraft mc = Minecraft.m_91087_();
         if (mc.f_91066_.f_92095_.m_90832_(event.getKey(), event.getScanCode())) {
            tryCancelToggleUse(mc);
         }
      }
   }

   @SubscribeEvent
   public static void onMouseInput(Pre event) {
      if (event.getAction() == 1) {
         Minecraft mc = Minecraft.m_91087_();
         if (mc.f_91066_.f_92095_.m_90830_(event.getButton()) && tryCancelToggleUse(mc)) {
            event.setCanceled(true);
         }
      }
   }

   private static boolean tryCancelToggleUse(Minecraft mc) {
      LocalPlayer player = mc.f_91074_;
      if (player != null && !player.m_5833_() && player.m_6117_()) {
         ItemStack useItem = player.m_21211_();
         if (!(useItem.m_41720_() instanceof IConsumable)) {
            return false;
         }

         ConsumableIndex index = (ConsumableIndex)LrTacticalAPI.getConsumableIndex(useItem).orElse(null);
         if (index != null && index.getData().isToggleUse()) {
            ItemStack mainHandItem = player.m_21120_(InteractionHand.MAIN_HAND);
            if (mainHandItem != useItem) {
               return false;
            }

            player.m_5810_();
            NetworkHandler.sendToServer(new CCancelToggleConsumableUse());

            while (mc.f_91066_.f_92095_.m_90859_()) {
            }

            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
