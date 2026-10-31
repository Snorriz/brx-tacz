package me.xjqsh.lrtactical.handler;

import me.xjqsh.lrtactical.init.ModEffects;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = "lrtactical", bus = Bus.FORGE)
public class EntityHurtEventHandler {
   @SubscribeEvent
   public static void onEntityHurt(LivingHurtEvent event) {
      if (event.getSource().m_269533_(DamageTypeTags.f_268745_)) {
         MobEffectInstance effect = event.getEntity().m_21124_((MobEffect)ModEffects.FLAMMABLE.get());
         if (effect != null) {
            float additionalDamage = 2.0F + effect.m_19564_() * 0.5F;
            event.setAmount(event.getAmount() * additionalDamage);
         }
      }
   }
}
