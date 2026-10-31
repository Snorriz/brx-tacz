package net.mcreator.miklosmilitaryarmorr.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MmArmorModSounds {
   public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "mm_armor");
   public static final RegistryObject<SoundEvent> BOMB_FUZE = REGISTRY.register(
      "bomb_fuze", () -> SoundEvent.m_262824_(new ResourceLocation("mm_armor", "bomb_fuze"))
   );
   public static final RegistryObject<SoundEvent> EXPLOSION_SOUND = REGISTRY.register(
      "explosion_sound", () -> SoundEvent.m_262824_(new ResourceLocation("mm_armor", "explosion_sound"))
   );
   public static final RegistryObject<SoundEvent> NVG_ON = REGISTRY.register("nvg_on", () -> SoundEvent.m_262824_(new ResourceLocation("mm_armor", "nvg_on")));
   public static final RegistryObject<SoundEvent> CLICK = REGISTRY.register("click", () -> SoundEvent.m_262824_(new ResourceLocation("mm_armor", "click")));
   public static final RegistryObject<SoundEvent> CLOTHES_WEAR = REGISTRY.register(
      "clothes_wear", () -> SoundEvent.m_262824_(new ResourceLocation("mm_armor", "clothes_wear"))
   );
   public static final RegistryObject<SoundEvent> PARACHUTE_CLOSE = REGISTRY.register(
      "parachute_close", () -> SoundEvent.m_262824_(new ResourceLocation("mm_armor", "parachute_close"))
   );
}
