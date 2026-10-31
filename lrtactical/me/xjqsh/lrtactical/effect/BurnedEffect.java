package me.xjqsh.lrtactical.effect;

import java.util.Collections;
import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class BurnedEffect extends MobEffect {
   public BurnedEffect(int pColor) {
      super(MobEffectCategory.HARMFUL, pColor);
   }

   public List<ItemStack> getCurativeItems() {
      return Collections.emptyList();
   }

   public void m_6742_(LivingEntity pLivingEntity, int pAmplifier) {
      if (pLivingEntity.m_6060_()) {
         int r = pLivingEntity.m_20094_();
         if (r < 40) {
            pLivingEntity.m_7311_(r + 40);
         }
      }
   }

   public boolean m_6584_(int duration, int amplifier) {
      return duration % 20 == 0;
   }
}
