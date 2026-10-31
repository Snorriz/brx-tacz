package net.mcreator.miklosmilitaryarmorr.procedures;

import net.mcreator.miklosmilitaryarmorr.MmArmorMod;
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

public class HelmetabilitiesOnKeyPressedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETK_63VISOR_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63VISOROPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63VISOROPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETK_63VISOROPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63VISOR_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63VISOR_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETK_63GRAYVISOR_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63GRAYVISOROPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63GRAYVISOROPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETK_63GRAYVISOROPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63GRAYVISOR_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETK_63GRAYVISOR_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.MASKA_1SHHELMETVISOR_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETVISOROPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETVISOROPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.MASKA_1SHHELMETVISOROPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETVISOR_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETVISOR_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.MASKA_1SHHELMETVISORSTRIPES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETSTRIPESVISOROPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETSTRIPESVISOROPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.MASKA_1SHHELMETSTRIPESVISOROPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETVISORSTRIPES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.MASKA_1SHHELMETVISORSTRIPES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47WINTERGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47WINTERGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47WINTERGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47WINTERGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47WINTERGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47WINTERGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47WINTERGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47WINTERGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47WINTERGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.RUSSIANUSHANKAGREEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAGREENCLOSED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAGREENCLOSED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.RUSSIANUSHANKAGREENCLOSED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAGREEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAGREEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.RUSSIANUSHANKAWINTER_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAWINTERCLOSED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAWINTERCLOSED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.RUSSIANUSHANKAWINTERCLOSED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAWINTER_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.RUSSIANUSHANKAWINTER_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47DESERTEMRGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.ZSH_1HELMETVISOR_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETVISOROPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETVISOROPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.ZSH_1HELMETVISOROPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETVISOR_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETVISOR_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRMCGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRMCGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRMCGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRMCGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRMCGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRMCGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRMCGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRMCGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRMCGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRNVGOPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            MmArmorMod.queueServerWork(
               10,
               () -> {
                  if (world instanceof Level _level) {
                     if (!_level.m_5776_()) {
                        _level.m_5594_(
                           null,
                           BlockPos.m_274561_(x, y, z),
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _level.m_7785_(
                           x,
                           y,
                           z,
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }

                  Entity _entityx = entity;
                  if (_entityx instanceof Player _playerx) {
                     _playerx.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRNVG_HELMET.get()));
                     _playerx.m_150109_().m_6596_();
                  } else if (_entityx instanceof LivingEntity _livingx) {
                     _livingx.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRNVG_HELMET.get()));
                  }
               }
            );
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMET_6B_47EMRNVG_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRNVGOPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMET_6B_47EMRNVGOPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETLSHZ_1EMRNVGOPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            MmArmorMod.queueServerWork(
               10,
               () -> {
                  if (world instanceof Level _level) {
                     if (!_level.m_5776_()) {
                        _level.m_5594_(
                           null,
                           BlockPos.m_274561_(x, y, z),
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _level.m_7785_(
                           x,
                           y,
                           z,
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }

                  Entity _entityx = entity;
                  if (_entityx instanceof Player _playerx) {
                     _playerx.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETLSHZ_1EMRNVG_HELMET.get()));
                     _playerx.m_150109_().m_6596_();
                  } else if (_entityx instanceof LivingEntity _livingx) {
                     _livingx.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETLSHZ_1EMRNVG_HELMET.get()));
                  }
               }
            );
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETLSHZ_1EMRNVG_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETLSHZ_1EMRNVGOPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETLSHZ_1EMRNVGOPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETFASTBLACKNVGOPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            MmArmorMod.queueServerWork(
               10,
               () -> {
                  if (world instanceof Level _level) {
                     if (!_level.m_5776_()) {
                        _level.m_5594_(
                           null,
                           BlockPos.m_274561_(x, y, z),
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _level.m_7785_(
                           x,
                           y,
                           z,
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }

                  Entity _entityx = entity;
                  if (_entityx instanceof Player _playerx) {
                     _playerx.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTBLACKNVG_HELMET.get()));
                     _playerx.m_150109_().m_6596_();
                  } else if (_entityx instanceof LivingEntity _livingx) {
                     _livingx.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTBLACKNVG_HELMET.get()));
                  }
               }
            );
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETFASTBLACKNVG_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTBLACKNVGOPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTBLACKNVGOPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETFASTDESERTNVGOPEN_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            MmArmorMod.queueServerWork(
               10,
               () -> {
                  if (world instanceof Level _level) {
                     if (!_level.m_5776_()) {
                        _level.m_5594_(
                           null,
                           BlockPos.m_274561_(x, y, z),
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F
                        );
                     } else {
                        _level.m_7785_(
                           x,
                           y,
                           z,
                           (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:nvg_on")),
                           SoundSource.NEUTRAL,
                           1.0F,
                           1.0F,
                           false
                        );
                     }
                  }

                  Entity _entityx = entity;
                  if (_entityx instanceof Player _playerx) {
                     _playerx.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTDESERTNVG_HELMET.get()));
                     _playerx.m_150109_().m_6596_();
                  } else if (_entityx instanceof LivingEntity _livingx) {
                     _livingx.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTDESERTNVG_HELMET.get()));
                  }
               }
            );
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETFASTDESERTNVG_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x, y, z, (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:click")), SoundSource.NEUTRAL, 1.0F, 1.0F, false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTDESERTNVGOPEN_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETFASTDESERTNVGOPEN_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHMULTICAMGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMULTICAMGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMULTICAMGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHMULTICAMGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMULTICAMGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMULTICAMGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHMULTICAMGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMULTICAMGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMULTICAMGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHMARPATGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMARPATGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMARPATGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHMARPATGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMARPATGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMARPATGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHMARPATGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMARPATGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHMARPATGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHDESERTMARPATGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHDESERTMARPATGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHDESERTMARPATGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHDESERTMARPATGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHDESERTMARPATGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHDESERTMARPATGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHDESERTMARPATGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHDESERTMARPATGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHDESERTMARPATGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHACUGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHACUGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHACUGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHACUGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHACUGOGGLESCOVERED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHACUGOGGLESCOVERED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETMICHACUGOGGLESCOVERED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHACUGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETMICHACUGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.SOVIETUSHANKA_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.SOVIETUSHANKACLOSED_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.SOVIETUSHANKACLOSED_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.SOVIETUSHANKACLOSED_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.SOVIETUSHANKA_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.SOVIETUSHANKA_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.ZSH_1HELMETGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.ZSH_1HELMETGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.ZSH_1HELMETGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.ZSH_1BLACKHELMETGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.ZSH_1BLACKHELMETGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.ZSH_1BLACKHELMETGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.ZSH_1BLACKHELMETGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.ZSH_1BLACKHELMETGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.ZSH_1BLACKHELMETGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETKASKA_1MDUBOKGOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MDUBOKGOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MDUBOKGOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETKASKA_1MDUBOKGOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MDUBOKGOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MDUBOKGOGGLES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETKASKA_1MMM_14GOGGLES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MMM_14GOGGLESONEYES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MMM_14GOGGLESONEYES_HELMET.get()));
            }
         } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.HELMETKASKA_1MMM_14GOGGLESONEYES_HELMET.get()) {
            if (world instanceof Level _level) {
               if (!_level.m_5776_()) {
                  _level.m_5594_(
                     null,
                     BlockPos.m_274561_(x, y, z),
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F
                  );
               } else {
                  _level.m_7785_(
                     x,
                     y,
                     z,
                     (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")),
                     SoundSource.NEUTRAL,
                     1.0F,
                     1.0F,
                     false
                  );
               }
            }

            Entity _entity = entity;
            if (_entity instanceof Player _player) {
               _player.m_150109_().f_35975_.set(3, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MMM_14GOGGLES_HELMET.get()));
               _player.m_150109_().m_6596_();
            } else if (_entity instanceof LivingEntity _living) {
               _living.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)MmArmorModItems.HELMETKASKA_1MMM_14GOGGLES_HELMET.get()));
            }
         }
      }
   }
}
