package me.xjqsh.lrtactical.item;

import java.util.UUID;
import me.xjqsh.lrtactical.entity.GrenadeEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class DetonatorItem extends Item {
   public DetonatorItem() {
      super(new Properties().m_41487_(1));
   }

   public void recordEntity(Entity entity, ItemStack detonatorStack) {
      UUID entityId = entity.m_20148_();
      detonatorStack.m_41784_().m_128362_("linked_entity", entityId);
   }

   public UUID getLinkedEntityId(ItemStack detonatorStack) {
      return detonatorStack.m_41782_() && detonatorStack.m_41783_().m_128403_("linked_entity") ? detonatorStack.m_41783_().m_128342_("linked_entity") : null;
   }

   public boolean detonate(ItemStack detonatorStack, Entity detonator) {
      UUID linkedId = this.getLinkedEntityId(detonatorStack);
      if (linkedId == null) {
         return false;
      } else if (detonator.m_9236_() instanceof ServerLevel serverLevel && serverLevel.m_8791_(linkedId) instanceof GrenadeEntity grenadeEntity) {
         grenadeEntity.onDeath(null);
         return true;
      } else {
         return false;
      }
   }

   public InteractionResultHolder<ItemStack> m_7203_(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
      ItemStack detonatorStack = pPlayer.m_21120_(pUsedHand);
      if (this.detonate(detonatorStack, pPlayer)) {
         pPlayer.m_21008_(pUsedHand, ItemStack.f_41583_);
         if (pPlayer instanceof ServerPlayer serverPlayer) {
            serverPlayer.m_240418_(Component.m_237113_("BOOM!").m_130940_(ChatFormatting.RED), true);
         }

         return InteractionResultHolder.m_19092_(detonatorStack, pLevel.m_5776_());
      } else {
         if (pPlayer instanceof ServerPlayer serverPlayer) {
            serverPlayer.m_240418_(Component.m_237113_("- NO SIGNAL -").m_130940_(ChatFormatting.RED), true);
         }

         return InteractionResultHolder.m_19098_(detonatorStack);
      }
   }
}
