package me.xjqsh.lrtactical.handler;

import me.xjqsh.lrtactical.init.ModEnchantment;
import me.xjqsh.lrtactical.util.VectorUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = "lrtactical", bus = Bus.FORGE)
public class CriticalHitEventHandler {
   @SubscribeEvent(priority = EventPriority.HIGH)
   public static void onCriticalHit(CriticalHitEvent event) {
      Player player = event.getEntity();
      int level = player.m_21205_().getEnchantmentLevel((Enchantment)ModEnchantment.BACKSTAB.get());
      if (level > 0) {
         Entity target = event.getTarget();
         Vec3 origin = player.m_146892_();
         Vec3 positionVector = target.m_20182_().m_82520_(0.0, target.m_20206_() / 2.0F, 0.0).m_82546_(origin);
         positionVector = new Vec3(positionVector.f_82479_, 0.0, positionVector.f_82481_).m_82541_();
         Vec3 targetForward = target.m_20156_();
         double angle = VectorUtil.angleBetween(positionVector, targetForward);
         if (angle <= 45.0) {
            event.setResult(Result.ALLOW);
            event.setDamageModifier(event.getOldDamageModifier() + level * 0.25F);
         }
      }
   }
}
