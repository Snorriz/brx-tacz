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

public class UcongetuniformProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         Entity _entity = entity;
         if (_entity instanceof Player _player) {
            _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.UCONUNIFORM_HELMET.get()));
            _player.m_150109_().m_6596_();
         } else if (_entity instanceof LivingEntity _living) {
            _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.UCONUNIFORM_HELMET.get()));
         }

         _entity = entity;
         if (_entity instanceof Player _player) {
            _player.m_150109_().f_35975_.set(2, new ItemStack((ItemLike)MmArmorModItems.UCONUNIFORM_CHESTPLATE.get()));
            _player.m_150109_().m_6596_();
         } else if (_entity instanceof LivingEntity _living) {
            _living.m_8061_(EquipmentSlot.CHEST, new ItemStack((ItemLike)MmArmorModItems.UCONUNIFORM_CHESTPLATE.get()));
         }

         _entity = entity;
         if (_entity instanceof Player _player) {
            _player.m_150109_().f_35975_.set(1, new ItemStack((ItemLike)MmArmorModItems.UCONUNIFORM_LEGGINGS.get()));
            _player.m_150109_().m_6596_();
         } else if (_entity instanceof LivingEntity _living) {
            _living.m_8061_(EquipmentSlot.LEGS, new ItemStack((ItemLike)MmArmorModItems.UCONUNIFORM_LEGGINGS.get()));
         }

         if (world instanceof Level _level) {
            if (!_level.m_5776_()) {
               _level.m_5594_(
                  null,
                  BlockPos.m_274561_(x, y, z),
                  (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_netherite")),
                  SoundSource.NEUTRAL,
                  1.0F,
                  1.0F
               );
            } else {
               _level.m_7785_(
                  x,
                  y,
                  z,
                  (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_netherite")),
                  SoundSource.NEUTRAL,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }
      }
   }
}
