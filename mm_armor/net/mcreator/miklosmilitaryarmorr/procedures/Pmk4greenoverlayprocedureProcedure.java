package net.mcreator.miklosmilitaryarmorr.procedures;

import net.mcreator.miklosmilitaryarmorr.init.MmArmorModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Pmk4greenoverlayprocedureProcedure {
   public static boolean execute(Entity entity) {
      if (entity == null) {
         return false;
      } else {
         return (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
               == MmArmorModItems.PMK_4GASMASK_HELMET.get()
            ? true
            : (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
               == MmArmorModItems.PMK_4HAZMAT_HELMET.get();
      }
   }
}
