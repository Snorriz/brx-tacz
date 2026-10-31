package me.xjqsh.lrtactical.network.message;

import java.util.function.Supplier;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import me.xjqsh.lrtactical.network.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent.Context;

public record CPrepareMeleeAttack(MeleeAction action, Vec3 origin, Vec3 direction) {
   public static void encode(CPrepareMeleeAttack message, FriendlyByteBuf buf) {
      buf.m_130068_(message.action);
      buf.writeDouble(message.origin.f_82479_);
      buf.writeDouble(message.origin.f_82480_);
      buf.writeDouble(message.origin.f_82481_);
      buf.writeDouble(message.direction.f_82479_);
      buf.writeDouble(message.direction.f_82480_);
      buf.writeDouble(message.direction.f_82481_);
   }

   public static CPrepareMeleeAttack decode(FriendlyByteBuf buf) {
      return new CPrepareMeleeAttack(
         (MeleeAction)buf.m_130066_(MeleeAction.class),
         new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble()),
         new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble())
      );
   }

   public static void handle(CPrepareMeleeAttack message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isServer()) {
         context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
               player.getCapability(CombatPropertiesProvider.CAPABILITY).ifPresent(cap -> {
                  if (cap.preAttack(message.action, message.origin, message.direction) && player.m_21205_().m_41720_() instanceof IMeleeWeapon weapon) {
                     ResourceLocation animationId = weapon.getId(player.m_21205_());
                     int actionCount = cap.getActionCount(message.action);
                     NetworkHandler.sendToTrackingEntityAndSelf(player, new SMeleeAnimationSync(player.m_19879_(), message.action, actionCount, animationId));
                  }
               });
            }
         });
      }

      context.setPacketHandled(true);
   }
}
