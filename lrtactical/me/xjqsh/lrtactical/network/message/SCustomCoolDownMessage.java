package me.xjqsh.lrtactical.network.message;

import java.util.function.Supplier;
import me.xjqsh.lrtactical.capability.CustomItemCoolDownsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;

public record SCustomCoolDownMessage(ResourceLocation id, int duration) {
   public static void encode(SCustomCoolDownMessage message, FriendlyByteBuf buf) {
      buf.m_130085_(message.id);
      buf.writeInt(message.duration);
   }

   public static SCustomCoolDownMessage decode(FriendlyByteBuf buf) {
      return new SCustomCoolDownMessage(buf.m_130281_(), buf.readInt());
   }

   public static void handle(SCustomCoolDownMessage message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isClient()) {
         context.enqueueWork(() -> handle(message));
      }

      context.setPacketHandled(true);
   }

   @OnlyIn(Dist.CLIENT)
   private static void handle(SCustomCoolDownMessage message) {
      Player player = Minecraft.m_91087_().f_91074_;
      if (player != null) {
         player.getCapability(CustomItemCoolDownsProvider.CAPABILITY).ifPresent(coolDownCapability -> {
            if (message.duration() == 0) {
               coolDownCapability.removeCooldown(message.id());
            } else {
               coolDownCapability.addCooldown(message.id(), message.duration());
            }
         });
      }
   }
}
