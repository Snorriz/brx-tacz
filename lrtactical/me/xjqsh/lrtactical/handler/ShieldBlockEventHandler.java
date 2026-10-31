package me.xjqsh.lrtactical.handler;

import me.xjqsh.lrtactical.item.FlashShieldItem;
import me.xjqsh.lrtactical.network.NetworkHandler;
import me.xjqsh.lrtactical.network.message.SShieldShake;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.network.PacketDistributor;

@EventBusSubscriber(modid = "lrtactical", bus = Bus.FORGE)
public class ShieldBlockEventHandler {
   @SubscribeEvent(priority = EventPriority.LOW)
   public static void onShieldBlock(ShieldBlockEvent event) {
      LivingEntity livingEntity = event.getEntity();
      ItemStack stack = livingEntity.m_21205_();
      if (stack.m_41720_() instanceof FlashShieldItem flashShieldItem) {
         int restDurability = stack.m_41776_() - stack.m_41773_();
         restDurability = Math.max(0, restDurability);
         float blockedDamage = Math.min(event.getBlockedDamage(), restDurability);
         event.setBlockedDamage(blockedDamage);
         stack.m_41622_((int)blockedDamage, livingEntity, e -> {});
         if (blockedDamage >= 1.0F && livingEntity instanceof ServerPlayer serverPlayer) {
            NetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new SShieldShake());
         }
      }
   }
}
