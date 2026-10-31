package me.xjqsh.lrtactical.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.item.IConsumable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {
   @WrapWithCondition(
      method = "handleKeybinds",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;releaseUsingItem(Lnet/minecraft/world/entity/player/Player;)V"
      )
   )
   private boolean lrtactical$shouldReleaseUsingItem(MultiPlayerGameMode gameMode, Player player) {
      if (player != null && player.m_6117_()) {
         ItemStack useItem = player.m_21211_();
         return !(useItem.m_41720_() instanceof IConsumable)
            ? true
            : (Boolean)LrTacticalAPI.getConsumableIndex(useItem).map(index -> !index.getData().isToggleUse()).orElse(true);
      } else {
         return true;
      }
   }
}
