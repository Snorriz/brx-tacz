package me.xjqsh.lrtactical.util;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.ItemStack;

public final class PotionTooltipUtil {
   private PotionTooltipUtil() {
   }

   public static void addPotionTooltip(List<PotionTooltipUtil.EffectWithChance> effects, List<Component> tooltips, float durationFactor) {
      if (!effects.isEmpty()) {
         List<Pair<Attribute, AttributeModifier>> attributeModifiers = new ArrayList();

         for (PotionTooltipUtil.EffectWithChance effectWithChance : effects) {
            MobEffectInstance effectInstance = effectWithChance.effect();
            MutableComponent effectName = Component.m_237115_(effectInstance.m_19576_());
            MobEffect effect = effectInstance.m_19544_();

            for (Entry<Attribute, AttributeModifier> entry : effect.m_19485_().entrySet()) {
               AttributeModifier modifier = (AttributeModifier)entry.getValue();
               AttributeModifier scaledModifier = new AttributeModifier(
                  modifier.m_22214_(), effect.m_7048_(effectInstance.m_19564_(), modifier), modifier.m_22217_()
               );
               attributeModifiers.add(new Pair((Attribute)entry.getKey(), scaledModifier));
            }

            if (effectInstance.m_19564_() > 0) {
               effectName = Component.m_237110_(
                  "potion.withAmplifier", new Object[]{effectName, Component.m_237115_("potion.potency." + effectInstance.m_19564_())}
               );
            }

            boolean hasDuration = !effectInstance.m_267633_(20);
            boolean hasChance = effectWithChance.chance() < 1.0F;
            if (hasDuration && hasChance) {
               effectName = Component.m_237110_(
                  "tooltip.lrtactical.consumable.effect.with_duration_and_chance",
                  new Object[]{effectName, MobEffectUtil.m_267641_(effectInstance, durationFactor), formatChance(effectWithChance.chance())}
               );
            } else if (hasDuration) {
               effectName = Component.m_237110_("potion.withDuration", new Object[]{effectName, MobEffectUtil.m_267641_(effectInstance, durationFactor)});
            } else if (hasChance) {
               effectName = Component.m_237110_(
                  "tooltip.lrtactical.consumable.effect.with_chance", new Object[]{effectName, formatChance(effectWithChance.chance())}
               );
            }

            tooltips.add(effectName.m_130940_(effect.m_19483_().m_19497_()));
         }

         if (!attributeModifiers.isEmpty()) {
            tooltips.add(CommonComponents.f_237098_);
            tooltips.add(Component.m_237115_("potion.whenDrank").m_130940_(ChatFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> attributeModifier : attributeModifiers) {
               AttributeModifier modifier = (AttributeModifier)attributeModifier.getSecond();
               double amount = modifier.m_22218_();
               double displayAmount = amount;
               if (modifier.m_22217_() == Operation.MULTIPLY_BASE || modifier.m_22217_() == Operation.MULTIPLY_TOTAL) {
                  displayAmount *= 100.0;
               }

               if (amount > 0.0) {
                  tooltips.add(
                     Component.m_237110_(
                           "attribute.modifier.plus." + modifier.m_22217_().m_22235_(),
                           new Object[]{ItemStack.f_41584_.format(displayAmount), Component.m_237115_(((Attribute)attributeModifier.getFirst()).m_22087_())}
                        )
                        .m_130940_(ChatFormatting.BLUE)
                  );
               } else if (amount < 0.0) {
                  tooltips.add(
                     Component.m_237110_(
                           "attribute.modifier.take." + modifier.m_22217_().m_22235_(),
                           new Object[]{ItemStack.f_41584_.format(-displayAmount), Component.m_237115_(((Attribute)attributeModifier.getFirst()).m_22087_())}
                        )
                        .m_130940_(ChatFormatting.RED)
                  );
               }
            }
         }
      }
   }

   private static Component formatChance(float chance) {
      return Component.m_237113_(String.format(Locale.ROOT, "%.0f%%", new Object[]{chance * 100.0F}));
   }

   public record EffectWithChance(MobEffectInstance effect, float chance) {
   }
}
