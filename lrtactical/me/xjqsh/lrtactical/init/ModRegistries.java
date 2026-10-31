package me.xjqsh.lrtactical.init;

import java.util.function.Supplier;
import me.xjqsh.lrtactical.item.melee.MeleeWeaponType;
import me.xjqsh.lrtactical.item.throwable.ThrowableType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = "lrtactical", bus = Bus.MOD)
public class ModRegistries {
   public static final ResourceKey<Registry<ThrowableType<?, ?>>> THROWABLE_TYPE = ResourceKey.m_135788_(new ResourceLocation("lrtactical", "throwable_type"));
   public static Supplier<IForgeRegistry<ThrowableType<?, ?>>> THROWABLE_TYPE_SUPPLIER;
   public static final ResourceKey<Registry<MeleeWeaponType<?>>> MELEE_WEAPON_TYPE = ResourceKey.m_135788_(new ResourceLocation("lrtactical", "melee_type"));
   public static Supplier<IForgeRegistry<MeleeWeaponType<?>>> MELEE_WEAPON_TYPE_SUPPLIER;

   @SubscribeEvent
   public static void registerRegistries(NewRegistryEvent event) {
      THROWABLE_TYPE_SUPPLIER = event.create(new RegistryBuilder().setName(THROWABLE_TYPE.m_135782_()));
      MELEE_WEAPON_TYPE_SUPPLIER = event.create(new RegistryBuilder().setName(MELEE_WEAPON_TYPE.m_135782_()));
   }
}
