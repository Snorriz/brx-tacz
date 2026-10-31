package me.xjqsh.lrtactical.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.tacz.guns.api.item.IAnimationItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import me.xjqsh.lrtactical.api.collision.ITargetFilter;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.api.melee.AttackResult;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.client.renderer.item.MeleeItemRenderer;
import me.xjqsh.lrtactical.config.CommonConfig;
import me.xjqsh.lrtactical.inventory.tooltip.MeleeTooltip;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import me.xjqsh.lrtactical.item.melee.CombatData;
import me.xjqsh.lrtactical.item.melee.CombatData.MeleeAttackInfo;
import me.xjqsh.lrtactical.item.melee.CombatData.MeleeMovement;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

public class MeleeItem extends Item implements IAnimationItem, IMeleeWeapon {
   public MeleeItem() {
      super(new Properties().m_41487_(1).setNoRepair());
   }

   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
      return (Multimap<Attribute, AttributeModifier>)(slot == EquipmentSlot.MAINHAND
         ? (Multimap)this.getMeleeIndex(stack).map(MeleeWeaponIndex::getDefaultModifiers).orElse(ImmutableMultimap.of())
         : ImmutableMultimap.of());
   }

   public boolean isSame(ItemStack stack1, ItemStack stack2) {
      return super.isSame(stack1, stack2);
   }

   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
      consumer.accept(new IClientItemExtensions() {
         private MeleeItemRenderer renderer = null;

         public MeleeItemRenderer getCustomRenderer() {
            if (this.renderer == null) {
               this.renderer = new MeleeItemRenderer();
            }

            return this.renderer;
         }
      });
   }

   public boolean m_8120_(@NotNull ItemStack pStack) {
      return true;
   }

   public int getEnchantmentValue(ItemStack stack) {
      return 5;
   }

   @NotNull
   public String m_5671_(@NotNull ItemStack stack) {
      return (String)this.getMeleeIndex(stack).map(MeleeWeaponIndex::getDescriptionId).orElse(super.m_5671_(stack));
   }

   public Optional<TooltipComponent> m_142422_(ItemStack pStack) {
      return this.getMeleeIndex(pStack).isEmpty() ? Optional.empty() : Optional.of(new MeleeTooltip(pStack));
   }

   public int getAttackCoolDown(ItemStack stack, MeleeAction action, int cnt) {
      return (Integer)this.getMeleeIndex(stack)
         .map(index -> index.getData().getAttackInfo())
         .map(attackInfos -> attackInfos.getAttackInfo(action, cnt))
         .map(MeleeAttackInfo::getCooldown)
         .orElse(0);
   }

   public int getMaxDamage(ItemStack stack) {
      return (Integer)this.getMeleeIndex(stack).map(MeleeWeaponIndex::getMaxDurability).orElse(0);
   }

   public boolean isDamageable(ItemStack stack) {
      return this.getMaxDamage(stack) > 0;
   }

   public int getDrawTime(ItemStack stack) {
      return (Integer)this.getMeleeIndex(stack).map(index -> index.getData().getDrawTime()).orElse(0);
   }

   public int getPutAwayTime(ItemStack stack) {
      return (Integer)this.getMeleeIndex(stack).map(index -> index.getData().getPutAwayTime()).orElse(0);
   }

   public int getAttackDelay(Player attacker, ItemStack stack, MeleeAction action, int cnt) {
      return (Integer)this.getMeleeIndex(stack)
         .map(index -> index.getData().getAttackInfo())
         .map(attackInfos -> attackInfos.getAttackInfo(action, cnt))
         .map(MeleeAttackInfo::getDelay)
         .orElse(0);
   }

   public MeleeMovement getAttackMovement(Player entity, ItemStack stack, MeleeAction action) {
      return (MeleeMovement)this.getMeleeIndex(stack)
         .map(index -> index.getData().getAttackInfo())
         .map(attackInfos -> attackInfos.getAttackInfo(action))
         .map(MeleeAttackInfo::getMovement)
         .orElse(null);
   }

   public List<Entity> collectTargets(Player attacker, ItemStack stack, MeleeAction action, Vec3 origin, Vec3 direction) {
      List<Entity> entities = new ArrayList();
      this.getMeleeIndex(stack).ifPresent(index -> {
         CombatData combatData = index.getData().getAttackInfo();
         if (combatData != null) {
            MeleeAttackInfo attackInfo = combatData.getAttackInfo(action);
            if (attackInfo != null) {
               ITargetFilter filter = attackInfo.getHitbox();
               entities.addAll(filter.filterTargets(attacker, origin, direction));
            }
         }
      });
      return entities;
   }

   public void attack(Player attacker, ItemStack stack, MeleeAction action, List<Entity> targets, int combo) {
      float base = (float)attacker.m_21133_(Attributes.f_22281_);
      this.getMeleeIndex(stack).ifPresent(index -> {
         CombatData combatData = index.getData().getAttackInfo();
         if (combatData != null) {
            MeleeAttackInfo attackInfo = combatData.getAttackInfo(action, combo);
            if (attackInfo != null) {
               ITargetFilter filter = attackInfo.getHitbox();
               IMeleeWeapon.playMeleeSound(attacker, index.getId(), action.getId(), 2.0F, 1.0F, true);
               float damage = base * attackInfo.getFactor();
               float knockback = attackInfo.getKnockback();
               if (!(damage <= 0.0F)) {
                  boolean hit = false;
                  boolean crit = false;
                  boolean kill = false;

                  for (Entity livingentity : targets) {
                     boolean flag = !(livingentity instanceof ArmorStand armorStand && armorStand.m_31677_());
                     boolean inRange = livingentity.m_20280_(attacker) <= filter.getMaxRange() * filter.getMaxRange();
                     if (livingentity != attacker && flag && inRange) {
                        AttackResult result = this.performAttack(attacker, livingentity, stack, damage, knockback);
                        hit |= result.hit();
                        crit |= result.crit();
                        kill |= result.kill();
                     }
                  }

                  if (hit) {
                     if ((Boolean)CommonConfig.MELEE_ITEM_CONSUME_DURABILITY.get()) {
                        stack.m_41622_(attackInfo.getDurabilityDamage(), attacker, player -> player.m_21166_(EquipmentSlot.MAINHAND));
                     }

                     String soundKey;
                     if (kill) {
                        soundKey = "kill";
                     } else if (crit) {
                        soundKey = "crit";
                     } else {
                        soundKey = action.getId() + "_hit";
                     }

                     IMeleeWeapon.playMeleeSound(attacker, index.getId(), soundKey, 2.0F, 1.0F, true);
                     IMeleeWeapon.playMeleeSoundToAttacker(attacker, index.getId(), soundKey + "_feedback", 2.0F, 1.0F);
                  }
               }
            }
         }
      });
   }

   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
      return enchantment.f_44672_ == EnchantmentCategory.WEAPON;
   }

   public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
      return toolAction == ToolActions.SWORD_SWEEP;
   }
}
