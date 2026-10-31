package net.mcreator.miklosmilitaryarmorr.procedures;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;

public class HolycrossRightclickedProcedure {
   public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
      if (entity != null) {
         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19617_, 6000, 5, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19606_, 6000, 3, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19605_, 1000, 2, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19618_, 3000, 3, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19607_, 6000, 2, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19608_, 6000, 2, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19600_, 6000, 3, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19596_, 6000, 2, false, false));
         }

         if (entity instanceof LivingEntity _entity && !_entity.m_9236_().m_5776_()) {
            _entity.m_7292_(new MobEffectInstance(MobEffects.f_19598_, 6000, 5, false, false));
         }

         ItemStack _ist = itemstack;
         if (_ist.m_220157_(50, RandomSource.m_216327_(), null)) {
            _ist.m_41774_(1);
            _ist.m_41721_(0);
         }

         if (entity instanceof Player _player) {
            _player.m_36335_().m_41524_(itemstack.m_41720_(), 6000);
         }
      }
   }
}
