package net.mcreator.miklosmilitaryarmorr.procedures;

import net.mcreator.miklosmilitaryarmorr.init.MmArmorModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Nightvissionlv1vissionprocedureProcedure {
   public static boolean execute(Entity entity) {
      if (entity == null) {
         return false;
      } else if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
         == MmArmorModItems.HELMET_6B_47EMRNVG_HELMET.get()) {
         return true;
      } else {
         return (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
               == MmArmorModItems.HELMETLSHZ_1EMRNVG_HELMET.get()
            ? true
            : (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
               == MmArmorModItems.HELMETFASTBLACKNVG_HELMET.get();
      }
   }
}
