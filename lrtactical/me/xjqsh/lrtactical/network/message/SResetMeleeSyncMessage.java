package me.xjqsh.lrtactical.network.message;

import java.util.function.Supplier;
import me.xjqsh.lrtactical.capability.CombatProperties;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent.Context;

public record SResetMeleeSyncMessage() {
   public static void encode(SResetMeleeSyncMessage message, FriendlyByteBuf buf) {
   }

   public static SResetMeleeSyncMessage decode(FriendlyByteBuf buf) {
      return new SResetMeleeSyncMessage();
   }

   public static void handle(SResetMeleeSyncMessage message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isClient()) {
         context.enqueueWork(SResetMeleeSyncMessage::handleClient);
      }

      context.setPacketHandled(true);
   }

   @OnlyIn(Dist.CLIENT)
   private static void handleClient() {
      Player player = Minecraft.m_91087_().f_91074_;
      if (player != null) {
         player.getCapability(CombatPropertiesProvider.CAPABILITY).ifPresent(CombatProperties::resetMeleeSync);
      }
   }
}
