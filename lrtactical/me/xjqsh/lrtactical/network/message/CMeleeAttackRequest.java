package me.xjqsh.lrtactical.network.message;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import me.xjqsh.lrtactical.EquipmentMod;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import me.xjqsh.lrtactical.config.ServerConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent.Context;

public record CMeleeAttackRequest(MeleeAction action, int actionCount, int[] entityIds) {
   public CMeleeAttackRequest(MeleeAction action, int actionCount, List<Entity> entities) {
      this(action, actionCount, toList(entities));
   }

   private static int[] toList(List<Entity> entities) {
      return entities.stream().mapToInt(Entity::m_19879_).limit(((Integer)ServerConfig.MELEE_MAX_TARGET_PER_PACKET.get()).intValue()).toArray();
   }

   public static void encode(CMeleeAttackRequest message, FriendlyByteBuf buf) {
      buf.m_130068_(message.action);
      buf.m_130130_(message.actionCount);
      buf.m_130089_(message.entityIds);
   }

   public static CMeleeAttackRequest decode(FriendlyByteBuf buf) {
      MeleeAction action = (MeleeAction)buf.m_130066_(MeleeAction.class);
      int actionCount = buf.m_130242_();
      int[] ids = buf.m_130100_();
      return new CMeleeAttackRequest(action, actionCount, ids);
   }

   public static void handle(CMeleeAttackRequest message, Supplier<Context> contextSupplier) {
      Context context = (Context)contextSupplier.get();
      if (context.getDirection().getReceptionSide().isServer()) {
         context.enqueueWork(
            () -> {
               ServerPlayer player = context.getSender();
               if (player != null) {
                  if (message.entityIds.length > (Integer)ServerConfig.MELEE_MAX_TARGET_PER_PACKET.get()) {
                     EquipmentMod.LOGGER
                        .info("Player {} tried to attack too many entities at once: {}! Ignoring.", player.m_7755_().getString(), message.entityIds.length);
                  } else {
                     List<Entity> entities = new ArrayList();

                     for (int entityId : message.entityIds()) {
                        Entity entity = player.m_9236_().m_6815_(entityId);
                        if (entity != null) {
                           entities.add(entity);
                        }
                     }

                     player.getCapability(CombatPropertiesProvider.CAPABILITY).ifPresent(cap -> cap.postAttack(message.action, message.actionCount, entities));
                  }
               }
            }
         );
      }

      context.setPacketHandled(true);
   }
}
