package net.mcreator.miklosmilitaryarmorr.procedures;

import net.mcreator.miklosmilitaryarmorr.init.MmArmorModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Maska1shgreenoverlayprocedureProcedure {
   public static boolean execute(Entity entity) {
      return entity == null
         ? false
         : (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.m_6844_(EquipmentSlot.HEAD) : ItemStack.f_41583_).m_41720_()
            == MmArmorModItems.MASKA_1SHHELMETVISOR_HELMET.get();
   }
}
