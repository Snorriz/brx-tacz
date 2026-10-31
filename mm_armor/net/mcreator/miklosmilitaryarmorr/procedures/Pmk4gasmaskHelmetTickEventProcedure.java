package net.mcreator.miklosmilitaryarmorr.procedures;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class Pmk4gasmaskHelmetTickEventProcedure {
   public static void execute(Entity entity) {
      if (entity != null) {
         if (entity instanceof LivingEntity _entity) {
            _entity.m_21195_(MobEffects.f_19614_);
         }

         if (entity instanceof LivingEntity _entity) {
            _entity.m_21195_(MobEffects.f_19610_);
         }

         if (entity instanceof LivingEntity _entity) {
            _entity.m_21195_(MobEffects.f_19602_);
         }

         if (entity instanceof LivingEntity _entity) {
            _entity.m_21195_(MobEffects.f_19615_);
         }
      }
   }
}
