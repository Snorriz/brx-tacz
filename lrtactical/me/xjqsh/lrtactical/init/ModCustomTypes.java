package me.xjqsh.lrtactical.init;

import me.xjqsh.lrtactical.entity.EffectCloudGrenadeEntity;
import me.xjqsh.lrtactical.entity.GrenadeEntity;
import me.xjqsh.lrtactical.entity.SmokeGrenadeEntity;
import me.xjqsh.lrtactical.entity.StickyGrenadeEntity;
import me.xjqsh.lrtactical.entity.StunGrenadeEntity;
import me.xjqsh.lrtactical.item.melee.MeleeWeaponData;
import me.xjqsh.lrtactical.item.melee.MeleeWeaponType;
import me.xjqsh.lrtactical.item.throwable.ThrowableData;
import me.xjqsh.lrtactical.item.throwable.ThrowableType;
import me.xjqsh.lrtactical.item.throwable.area.CloudType;
import me.xjqsh.lrtactical.item.throwable.area.EffectCloudThrowableData;
import me.xjqsh.lrtactical.item.throwable.explode.ExplodeThrowableData;
import me.xjqsh.lrtactical.item.throwable.explode.ExplodeType;
import me.xjqsh.lrtactical.item.throwable.explode.StickyType;
import me.xjqsh.lrtactical.item.throwable.flash.StunThrowableData;
import me.xjqsh.lrtactical.item.throwable.flash.StunType;
import me.xjqsh.lrtactical.item.throwable.smoke.SmokeType;
import me.xjqsh.lrtactical.resource.CommonAssetsManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCustomTypes {
   public static final DeferredRegister<ThrowableType<?, ?>> THROWABLE_TYPES = DeferredRegister.create(ModRegistries.THROWABLE_TYPE, "lrtactical");
   public static RegistryObject<ThrowableType<ExplodeThrowableData, GrenadeEntity>> EXPLODE = THROWABLE_TYPES.register("explode", () -> ExplodeType.EXPLODE);
   public static RegistryObject<ThrowableType<ExplodeThrowableData, StickyGrenadeEntity>> STICKY = THROWABLE_TYPES.register("sticky", () -> StickyType.STICKY);
   public static RegistryObject<ThrowableType<ThrowableData, SmokeGrenadeEntity>> SMOKE = THROWABLE_TYPES.register("smoke", () -> SmokeType.SMOKE);
   public static RegistryObject<ThrowableType<StunThrowableData, StunGrenadeEntity>> STUN = THROWABLE_TYPES.register("stun", () -> StunType.STUN);
   public static RegistryObject<ThrowableType<EffectCloudThrowableData, EffectCloudGrenadeEntity>> EFFECT_CLOUD = THROWABLE_TYPES.register(
      "effect_cloud", () -> CloudType.CLOUD
   );
   public static final DeferredRegister<MeleeWeaponType<?>> MELEE_WEAPON_TYPES = DeferredRegister.create(ModRegistries.MELEE_WEAPON_TYPE, "lrtactical");
   public static RegistryObject<MeleeWeaponType<MeleeWeaponData>> NORMAL = MELEE_WEAPON_TYPES.register(
      "normal", () -> new MeleeWeaponType(jsonElement -> (MeleeWeaponData)CommonAssetsManager.GSON.fromJson(jsonElement, MeleeWeaponData.class))
   );
}
