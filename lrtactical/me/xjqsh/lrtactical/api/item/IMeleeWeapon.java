package me.xjqsh.lrtactical.api.item;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.melee.AttackResult;
import me.xjqsh.lrtactical.api.melee.MeleeAction;
import me.xjqsh.lrtactical.config.CommonConfig;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import me.xjqsh.lrtactical.item.melee.CombatData.MeleeMovement;
import me.xjqsh.lrtactical.network.NetworkHandler;
import me.xjqsh.lrtactical.network.message.SCustomSound;
import me.xjqsh.lrtactical.network.message.SCustomSound.SoundType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;
import org.jetbrains.annotations.Nullable;

public interface IMeleeWeapon extends ICustomItem {
   String ID_TAG = "MeleeWeaponId";
   String OVERRIDE_DISPLAY_ID = "DisplayId";
   ResourceLocation EMPTY = new ResourceLocation("lrtactical", "empty");

   static IMeleeWeapon of(ItemStack stack) {
      return stack.m_41720_() instanceof IMeleeWeapon item ? item : null;
   }

   default ResourceLocation getId(ItemStack stack) {
      CompoundTag nbt = stack.m_41784_();
      if (nbt.m_128425_("MeleeWeaponId", 8)) {
         ResourceLocation rl = ResourceLocation.m_135820_(nbt.m_128461_("MeleeWeaponId"));
         return (ResourceLocation)Objects.requireNonNullElse(rl, EMPTY);
      } else {
         return EMPTY;
      }
   }

   default ResourceLocation getDisplayId(ItemStack stack) {
      CompoundTag nbt = stack.m_41784_();
      if (nbt.m_128425_("DisplayId", 8)) {
         ResourceLocation rl = ResourceLocation.m_135820_(nbt.m_128461_("DisplayId"));
         return (ResourceLocation)Objects.requireNonNullElse(rl, EMPTY);
      } else {
         return this.getId(stack);
      }
   }

   default void setId(ItemStack stack, ResourceLocation id) {
      stack.m_41784_().m_128359_("MeleeWeaponId", id.toString());
   }

   default boolean shouldBlockAttack() {
      return true;
   }

   default boolean shouldBlockUse() {
      return true;
   }

   default int getAttackDelay(Player attacker, ItemStack stack, MeleeAction action) {
      return this.getAttackDelay(attacker, stack, action, 0);
   }

   default int getAttackDelay(Player attacker, ItemStack stack, MeleeAction action, int cnt) {
      return 0;
   }

   @Nullable
   default MeleeMovement getAttackMovement(Player entity, ItemStack stack, MeleeAction action) {
      return null;
   }

   default boolean canAttack(Player attacker, ItemStack stack, MeleeAction action) {
      return true;
   }

   default Optional<MeleeWeaponIndex<?>> getMeleeIndex(ItemStack stack) {
      return LrTacticalAPI.getMeleeIndex(stack);
   }

   List<Entity> collectTargets(Player var1, ItemStack var2, MeleeAction var3, Vec3 var4, Vec3 var5);

   default void attack(Player attacker, ItemStack stack, MeleeAction action, List<Entity> targets) {
      this.attack(attacker, stack, action, targets, 0);
   }

   void attack(Player var1, ItemStack var2, MeleeAction var3, List<Entity> var4, int var5);

   @Deprecated
   default void attack(Player attacker, ItemStack stack, MeleeAction action, Vec3 origin, Vec3 direction) {
   }

   default AttackResult performAttack(Player attacker, Entity target, ItemStack stack, float base, float knockback) {
      if (!ForgeHooks.onPlayerAttackTarget(attacker, target)) {
         return AttackResult.MISS;
      }

      if (!target.m_6097_()) {
         return AttackResult.MISS;
      }

      if (target.m_7313_(attacker)) {
         return AttackResult.MISS;
      }

      float modifier;
      if (target instanceof LivingEntity living) {
         modifier = EnchantmentHelper.m_44833_(stack, living.m_6336_());
      } else {
         modifier = EnchantmentHelper.m_44833_(stack, MobType.f_21640_);
      }

      boolean flag2 = attacker.f_19789_ > 0.0F
         && !attacker.m_20096_()
         && !attacker.m_6147_()
         && !attacker.m_20069_()
         && !attacker.m_21023_(MobEffects.f_19610_)
         && !attacker.m_20159_()
         && target instanceof LivingEntity;
      CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(attacker, target, flag2, flag2 ? 1.5F : 1.0F);
      if (hitResult != null) {
         base *= hitResult.getDamageModifier();
         flag2 = true;
      }

      int j = EnchantmentHelper.m_44914_(attacker);
      if (target instanceof LivingEntity living && j > 0) {
         living.m_20254_(j * 4);
      }

      if (target.f_19802_ < (Integer)CommonConfig.MELEE_IGNORE_INVULNERABLE_TICK_THRESHOLD.get()) {
         target.f_19802_ = 0;
      }

      boolean result = target.m_6469_(attacker.m_269291_().m_269075_(attacker), base + modifier);
      if (!result) {
         return AttackResult.MISS;
      }

      if (target instanceof LivingEntity living) {
         living.m_147240_(
            knockback, Mth.m_14031_(attacker.m_146908_() * (float) (Math.PI / 180.0)), -Mth.m_14089_(attacker.m_146908_() * (float) (Math.PI / 180.0))
         );
         EnchantmentHelper.m_44823_(living, attacker);
      }

      EnchantmentHelper.m_44896_(attacker, target);
      if (flag2) {
         attacker.m_5704_(target);
      }

      boolean killed = target instanceof LivingEntity living && living.m_21224_();
      if (killed) {
         return flag2 ? AttackResult.CRIT_KILL : AttackResult.KILL;
      } else {
         return flag2 ? AttackResult.CRIT : AttackResult.HIT;
      }
   }

   static void playMeleeSound(Player entity, ResourceLocation id, String key, float volume, float pitch) {
      playMeleeSound(entity, id, key, volume, pitch, false);
   }

   static void playMeleeSoundToAttacker(Player entity, ResourceLocation id, String key, float volume, float pitch) {
      if (entity instanceof ServerPlayer player) {
         NetworkHandler.sendToClientPlayer(new SCustomSound(SoundType.MELEE, id, key, entity.m_20182_(), volume, pitch), player);
      }
   }

   static void playMeleeSound(Player entity, ResourceLocation id, String key, float volume, float pitch, boolean exceptSelf) {
      SCustomSound packet = new SCustomSound(SoundType.MELEE, id, key, entity.m_20182_(), volume, pitch);
      ServerPlayer p;
      if (exceptSelf && entity instanceof ServerPlayer player) {
         p = player;
      } else {
         p = null;
      }

      NetworkHandler.CHANNEL
         .send(
            PacketDistributor.NEAR.with(() -> new TargetPoint(p, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), 64.0, entity.m_9236_().m_46472_())),
            packet
         );
   }

   default boolean canSprintingAttack() {
      return true;
   }

   default boolean isSame(ItemStack i, ItemStack j) {
      IMeleeWeapon w1 = of(i);
      IMeleeWeapon w2 = of(j);
      if (w1 != null && w2 != null) {
         return w1.getId(i).equals(w2.getId(j));
      } else {
         return !i.m_41619_() && !j.m_41619_() ? false : i.m_41619_() && j.m_41619_();
      }
   }
}
