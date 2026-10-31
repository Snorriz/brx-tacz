package net.mcreator.miklosmilitaryarmorr.procedures;

import net.mcreator.miklosmilitaryarmorr.init.MmArmorModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public class ParachuteonOnKeyPressedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.CHEST) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.RUSSIANPARACHUTE_CHESTPLATE.get()) {
            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(2, new ItemStack((ItemLike)MmArmorModItems.RUSSIANPARACHUTEOPENED_CHESTPLATE.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.CHEST, new ItemStack((ItemLike)MmArmorModItems.RUSSIANPARACHUTEOPENED_CHESTPLATE.get()));
            }

            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.CHEST) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.EMRCHESTPLATEPARACHUTE_CHESTPLATE.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(2, new ItemStack((ItemLike)MmArmorModItems.EMRCHESTPLATEPARACHUTEOPENED_CHESTPLATE.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.CHEST, new ItemStack((ItemLike)MmArmorModItems.EMRCHESTPLATEPARACHUTEOPENED_CHESTPLATE.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.CHEST) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.WINTER_EMRCHESTPLATEPARACHUTE_CHESTPLATE.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(2, new ItemStack((ItemLike)MmArmorModItems.WINTER_EMRCHESTPLATEPARACHUTEOPENED_CHESTPLATE.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.CHEST, new ItemStack((ItemLike)MmArmorModItems.WINTER_EMRCHESTPLATEPARACHUTEOPENED_CHESTPLATE.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.CHEST) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.FLORACHESTPLATEPARACHUTE_CHESTPLATE.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(2, new ItemStack((ItemLike)MmArmorModItems.FLORACHESTPLATEPARACHUTEOPENED_CHESTPLATE.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.CHEST, new ItemStack((ItemLike)MmArmorModItems.FLORACHESTPLATEPARACHUTEOPENED_CHESTPLATE.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.CHEST) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.CHESTPLATEDUBOKPARACHUTE_CHESTPLATE.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(2, new ItemStack((ItemLike)MmArmorModItems.CHESTPLATEDUBOKPARACHUTEOPEN_CHESTPLATE.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.CHEST, new ItemStack((ItemLike)MmArmorModItems.CHESTPLATEDUBOKPARACHUTEOPEN_CHESTPLATE.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.CHEST) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.CHESTPLATETTSKOPARACHUTE_CHESTPLATE.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(2, new ItemStack((ItemLike)MmArmorModItems.CHESTPLATETTSKOPARACHUTEOPEN_CHESTPLATE.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.CHEST, new ItemStack((ItemLike)MmArmorModItems.CHESTPLATETTSKOPARACHUTEOPEN_CHESTPLATE.get()));
            }
         }
      }
   }
}
