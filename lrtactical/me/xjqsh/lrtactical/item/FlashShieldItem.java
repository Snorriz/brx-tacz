package me.xjqsh.lrtactical.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.tacz.guns.api.item.IAnimationItem;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import me.xjqsh.lrtactical.api.collision.ConeFilter;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import me.xjqsh.lrtactical.capability.CustomItemCoolDownsProvider;
import me.xjqsh.lrtactical.client.renderer.item.FlashShieldItemRenderer;
import me.xjqsh.lrtactical.config.ServerConfig;
import me.xjqsh.lrtactical.init.ModEffects;
import me.xjqsh.lrtactical.item.throwable.flash.StunThrowableData.StunData;
import me.xjqsh.lrtactical.util.SightTraceUtil;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class FlashShieldItem extends Item implements IMeleeWeapon, IAnimationItem {
   private final Multimap<Attribute, AttributeModifier> defaultModifiers;
   public static StunData data = new StunData();

   public FlashShieldItem() {
      super(new Properties().m_41487_(1).m_41503_(350));
      Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
      builder.put(Attributes.f_22279_, new AttributeModifier("Shield modifier", -0.25, Operation.MULTIPLY_BASE));
      this.defaultModifiers = builder.build();
   }

   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
      return (Multimap<Attribute, AttributeModifier>)(slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : ImmutableMultimap.of());
   }

   public int getMaxDamage(ItemStack stack) {
      return (Integer)ServerConfig.FLASH_SHIELD_MAX_DURABILITY.get();
   }

   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
      consumer.accept(new IClientItemExtensions() {
         private final FlashShieldItemRenderer renderer = new FlashShieldItemRenderer();

         public FlashShieldItemRenderer getCustomRenderer() {
            return this.renderer;
         }

         public ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
            return hand == InteractionHand.OFF_HAND ? ArmPose.EMPTY : ArmPose.CROSSBOW_HOLD;
         }
      });
   }

   public int getDrawTime(ItemStack stack) {
      return 10;
   }

   public void attack(Player attacker, ItemStack stack, MeleeAction action, List<Entity> targets, int cnt) {
      float base = (float)attacker.m_21133_(Attributes.f_22281_);

      for (Entity livingentity : targets) {
         boolean flag = !(livingentity instanceof ArmorStand armorStand && armorStand.m_31677_());
         boolean inRange = livingentity.m_20280_(attacker) <= 6.25;
         if (livingentity != attacker && flag && inRange) {
            this.performAttack(attacker, livingentity, stack, base, 1.2F);
         }
      }
   }

   public List<Entity> collectTargets(Player attacker, ItemStack stack, MeleeAction action, Vec3 origin, Vec3 direction) {
      ConeFilter filter = new ConeFilter(2.5, 105.0);
      return filter.filterTargets(attacker, origin, direction);
   }

   public boolean canSprintingAttack() {
      return false;
   }

   public int getAttackDelay(Player attacker, ItemStack stack, MeleeAction action, int cnt) {
      return 5;
   }

   public boolean canAttack(Player attacker, ItemStack stack, MeleeAction action) {
      boolean isDisabled = (Boolean)attacker.getCapability(CustomItemCoolDownsProvider.CAPABILITY)
         .map(cap -> cap.isOnCooldown(new ResourceLocation("shield_disabled")))
         .orElse(false);
      return action == MeleeAction.LEFT && !isDisabled;
   }

   public boolean shouldBlockUse() {
      return false;
   }

   public int getMaxUsingTick(ItemStack stack) {
      return 10;
   }

   @ParametersAreNonnullByDefault
   public int m_8105_(ItemStack pStack) {
      return 10;
   }

   @ParametersAreNonnullByDefault
   @NotNull
   public InteractionResultHolder<ItemStack> m_7203_(Level pLevel, Player player, InteractionHand pUsedHand) {
      if (pUsedHand == InteractionHand.OFF_HAND) {
         return InteractionResultHolder.m_19100_(player.m_21120_(pUsedHand));
      } else {
         boolean coolDown = (Boolean)player.getCapability(CombatPropertiesProvider.CAPABILITY).map(cap -> cap.getCoolDownTick() > 0).orElse(false);
         boolean isDisabled = (Boolean)player.getCapability(CustomItemCoolDownsProvider.CAPABILITY)
            .map(cap -> cap.isOnCooldown(new ResourceLocation("shield_disabled")))
            .orElse(false);
         if (!coolDown && !isDisabled) {
            ItemStack stack = player.m_21120_(pUsedHand);
            player.m_6672_(pUsedHand);
            return InteractionResultHolder.m_19092_(stack, pLevel.m_5776_());
         } else {
            return InteractionResultHolder.m_19100_(player.m_21120_(pUsedHand));
         }
      }
   }

   @ParametersAreNonnullByDefault
   public void m_5929_(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
   }

   public ItemStack m_5922_(ItemStack stack, Level world, LivingEntity entity) {
      if (entity.m_21252_() >= this.getMaxUsingTick(stack) && !world.m_5776_()) {
         if (entity instanceof Player player) {
            player.m_36335_().m_41524_(stack.m_41720_(), (Integer)ServerConfig.FLASH_SHIELD_COOLDOWN.get());
            player.m_7292_(new MobEffectInstance((MobEffect)ModEffects.BLIND.get(), 45, 0, false, false));
            player.m_7292_(new MobEffectInstance((MobEffect)ModEffects.DEAFENED.get(), 60, 0, false, false));
         }

         AABB aabb = entity.m_20191_().m_82400_(12.0);

         for (Entity target : world.m_6249_(entity, aabb, EntitySelector.f_20408_)) {
            if (target instanceof LivingEntity living) {
               calculateAndApplyEffect(entity, living, data);
            }
         }
      }

      return stack;
   }

   public static void calculateAndApplyEffect(Entity starter, LivingEntity target, StunData data) {
      Vec3 p = starter.m_20182_().m_82520_(0.0, 1.0, 0.0);
      Vec3 eyes = target.m_20299_(1.0F);
      Vec3 d1 = p.m_82546_(eyes);
      Vec3 d2 = eyes.m_82546_(p);
      double distanceMax = data.getRadius();
      double distance = d1.m_82553_();
      if (!(distance > distanceMax)) {
         double a1 = Math.toDegrees(Math.acos(target.m_20252_(1.0F).m_82526_(d1.m_82541_())));
         double a2 = Math.toDegrees(Math.acos(starter.m_20252_(1.0F).m_82526_(d2.m_82541_())));
         double angleMax = data.getBlind().getMaxAngle();
         if (a1 > 0.0
            && a1 < angleMax
            && a2 > 0.0
            && a2 <= 90.0
            && SightTraceUtil.rayTraceOpaqueBlocks(starter, target.m_9236_(), eyes, p, false, false, false) == null) {
            int durationBlinded = data.calcBlindDuration(distance, a1);
            if (durationBlinded > 0) {
               target.m_7292_(new MobEffectInstance((MobEffect)ModEffects.BLIND.get(), durationBlinded, 0, false, false, true));
            }
         }

         int durationDeafened = data.calcDeafenedDuration(distance);
         if (durationDeafened > 0) {
            target.m_7292_(new MobEffectInstance((MobEffect)ModEffects.DEAFENED.get(), durationDeafened, 0, false, false, true));
         }
      }
   }

   public boolean m_41463_(@NotNull ItemStack pStack) {
      return false;
   }

   public boolean isSame(ItemStack stack1, ItemStack stack2) {
      return ItemStack.m_41656_(stack1, stack2);
   }
}
