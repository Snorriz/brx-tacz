package me.xjqsh.lrtactical.network.message;

import com.tacz.guns.client.renderer.item.AnimateGeoItemRenderer;
import java.util.function.Supplier;
import me.xjqsh.lrtactical.item.FlashShieldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.NetworkEvent.Context;

public record SShieldShake() {
   public static void encode(SShieldShake message, FriendlyByteBuf buf) {
   }

   public static SShieldShake decode(FriendlyByteBuf buf) {
      return new SShieldShake();
   }

   public static void handle(SShieldShake message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isClient()) {
         context.enqueueWork(() -> handle(message));
      }

      context.setPacketHandled(true);
   }

   @OnlyIn(Dist.CLIENT)
   private static void handle(SShieldShake message) {
      Player player = Minecraft.m_91087_().f_91074_;
      ClientLevel level = Minecraft.m_91087_().f_91073_;
      if (player != null && level != null) {
         if (player.m_21205_().m_41720_() instanceof FlashShieldItem
            && IClientItemExtensions.of(player.m_21205_()).getCustomRenderer() instanceof AnimateGeoItemRenderer<?, ?> renderer) {
            renderer.triggerAnimation(player.m_21205_(), "normal_shake");
         }
      }
   }
}
