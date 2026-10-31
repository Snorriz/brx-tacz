package me.xjqsh.lrtactical.item;

import com.tacz.guns.api.item.IAnimationItem;
import java.util.Optional;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import me.xjqsh.lrtactical.api.event.ConsumableUseEvent;
import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import me.xjqsh.lrtactical.capability.CustomItemCoolDownsProvider;
import me.xjqsh.lrtactical.client.renderer.item.ConsumableItemRenderer;
import me.xjqsh.lrtactical.inventory.tooltip.ConsumableTooltip;
import me.xjqsh.lrtactical.item.consumable.ConsumableData;
import me.xjqsh.lrtactical.item.consumable.ConsumableData.EffectData;
import me.xjqsh.lrtactical.item.consumable.ConsumableData.RemoveEffectSelector;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConsumableItem extends Item implements IAnimationItem, IConsumable {
   public ConsumableItem() {
      super(new Properties().m_41487_(1));
   }

   @Nullable
   public FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
      return (FoodProperties)this.getConsumableIndex(stack).map(ConsumableIndex::getFoodProperties).orElse(null);
   }

   public int getMaxStackSize(ItemStack stack) {
      return (Integer)this.getConsumableIndex(stack).map(ConsumableIndex::getMaxStackSize).orElse(1);
   }

   public int getMaxDamage(ItemStack stack) {
      return (Integer)this.getConsumableIndex(stack).map(index -> index.getData().getMaxDurability()).orElse(0);
   }

   public boolean isDamageable(ItemStack stack) {
      return this.getMaxDamage(stack) > 0;
   }

   public int m_8105_(ItemStack stack) {
      return (Integer)this.getConsumableIndex(stack).map(index -> index.getData().isToggleUse() ? 72000 : index.getData().getUseDuration()).orElse(0);
   }

   @NotNull
   public UseAnim m_6164_(@NotNull ItemStack stack) {
      return UseAnim.DRINK;
   }

   @NotNull
   public SoundEvent m_6023_() {
      return SoundEvents.f_271165_;
   }

   public int getDrawTime(ItemStack stack) {
      return (Integer)this.getConsumableIndex(stack).map(index -> index.getData().getDrawTime()).orElse(0);
   }

   public int getPutAwayTime(ItemStack stack) {
      return (Integer)this.getConsumableIndex(stack).map(index -> index.getData().getPutAwayTime()).orElse(0);
   }

   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
      consumer.accept(new IClientItemExtensions() {
         private ConsumableItemRenderer renderer = null;

         public ConsumableItemRenderer getCustomRenderer() {
            if (this.renderer == null) {
               this.renderer = new ConsumableItemRenderer();
            }

            return this.renderer;
         }
      });
   }

   @NotNull
   public String m_5671_(@NotNull ItemStack stack) {
      return (String)this.getConsumableIndex(stack).map(ConsumableIndex::getDescriptionId).orElse(super.m_5671_(stack));
   }

   @ParametersAreNonnullByDefault
   @NotNull
   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand usedHand) {
      if (usedHand == InteractionHand.OFF_HAND) {
         return InteractionResultHolder.m_19100_(player.m_21120_(usedHand));
      }

      ItemStack stack = player.m_21120_(usedHand);
      boolean drawing = (Boolean)player.getCapability(CombatPropertiesProvider.CAPABILITY).map(cap -> cap.getCoolDownTick() > 0).orElse(false);
      if (drawing) {
         return InteractionResultHolder.m_19100_(stack);
      }

      boolean onCooldown = (Boolean)this.getCoolDownId(stack)
         .map(id -> (Boolean)player.getCapability(CustomItemCoolDownsProvider.CAPABILITY).map(cap -> cap.isOnCooldown(id)).orElse(false))
         .orElse(false);
      if (onCooldown) {
         return InteractionResultHolder.m_19100_(stack);
      }

      player.m_6672_(usedHand);
      return InteractionResultHolder.m_19096_(stack);
   }

   @ParametersAreNonnullByDefault
   public ItemStack m_5922_(ItemStack stack, Level level, LivingEntity entity) {
      return this.finishConsumableUse(stack, level, entity);
   }

   @ParametersAreNonnullByDefault
   public void m_5929_(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
      this.getConsumableIndex(stack).ifPresent(index -> {
         if (index.getData().isToggleUse() && entity.m_21252_() >= index.getData().getUseDuration() && !level.m_5776_()) {
            this.finishConsumableUse(stack, level, entity);
            entity.m_5810_();
         }
      });
   }

   private ItemStack finishConsumableUse(ItemStack stack, Level level, LivingEntity entity) {
      this.getConsumableIndex(stack).ifPresent(index -> {
         if (!level.m_5776_()) {
            this.applyEffects(entity, stack, index);
            if (entity instanceof Player player) {
               player.m_36246_(Stats.f_12982_.m_12902_(this));
               if (!player.m_150110_().f_35937_) {
                  this.consumeAfterUse(stack, entity, index);
               }
            } else {
               this.consumeAfterUse(stack, entity, index);
            }
         }
      });
      return stack;
   }

   private void consumeAfterUse(ItemStack stack, LivingEntity entity, ConsumableIndex index) {
      ConsumableData data = index.getData();
      if (data.hasDurability()) {
         stack.m_41622_(data.getDurabilityDamage(), entity, living -> living.m_21166_(EquipmentSlot.MAINHAND));
      } else {
         stack.m_41774_(1);
      }
   }

   private void removeEffect(LivingEntity entity, RemoveEffectSelector selector) {
      if (selector.isCategory()) {
         this.removeEffectsByCategory(entity, selector.getCategory());
      } else {
         MobEffect effect = (MobEffect)ForgeRegistries.MOB_EFFECTS.getValue(selector.getEffect());
         if (effect != null) {
            entity.m_21195_(effect);
         }
      }
   }

   private void removeEffectsByCategory(LivingEntity entity, MobEffectCategory category) {
      for (MobEffect effect : entity.m_21220_().stream().map(MobEffectInstance::m_19544_).filter(effectx -> effectx.m_19483_() == category).toList()) {
         entity.m_21195_(effect);
      }
   }

   private void applyEffects(LivingEntity entity, ItemStack stack, ConsumableIndex index) {
      ConsumableData data = index.getData();
      if (data.getHeal() > 0.0F) {
         entity.m_5634_(data.getHeal());
      }

      for (RemoveEffectSelector selector : data.getRemoveEffects()) {
         this.removeEffect(entity, selector);
      }

      for (EffectData effectData : data.getEffects()) {
         if (!(entity.m_217043_().m_188501_() > effectData.getChance())) {
            MobEffectInstance effect = effectData.createInstance();
            if (effect != null) {
               entity.m_7292_(effect);
            }
         }
      }

      if (entity instanceof Player player && (data.getFood() > 0 || data.getSaturation() > 0.0F)) {
         FoodData foodData = player.m_36324_();
         foodData.m_38707_(data.getFood(), data.getSaturation());
      }

      MinecraftForge.EVENT_BUS.post(new ConsumableUseEvent(entity, stack.m_41777_(), index.getId(), index));
      ResourceLocation cooldownId = data.getCooldownCategory();
      if (cooldownId != null && data.getCooldown() > 0) {
         entity.getCapability(CustomItemCoolDownsProvider.CAPABILITY).ifPresent(cap -> cap.addCooldown(cooldownId, data.getCooldown()));
      }
   }

   public boolean isSame(ItemStack stack1, ItemStack stack2) {
      return super.isSame(stack1, stack2);
   }

   public Optional<TooltipComponent> m_142422_(ItemStack stack) {
      return this.getConsumableIndex(stack).isEmpty() ? Optional.empty() : Optional.of(new ConsumableTooltip(stack));
   }
}
