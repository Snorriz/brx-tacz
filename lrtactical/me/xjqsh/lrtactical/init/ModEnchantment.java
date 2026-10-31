package me.xjqsh.lrtactical.init;

import me.xjqsh.lrtactical.enchantment.BackstabEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantment {
   public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "lrtactical");
   public static RegistryObject<Enchantment> BACKSTAB = ENCHANTMENTS.register("backstab", BackstabEnchantment::new);
}
