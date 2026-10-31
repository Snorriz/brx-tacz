package me.xjqsh.lrtactical.init;

import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.api.item.IMeleeWeapon;
import me.xjqsh.lrtactical.api.item.IThrowable;
import me.xjqsh.lrtactical.item.ConsumableItem;
import me.xjqsh.lrtactical.item.DetonatorItem;
import me.xjqsh.lrtactical.item.FlashShieldItem;
import me.xjqsh.lrtactical.item.MeleeItem;
import me.xjqsh.lrtactical.item.ThrowableItem;
import me.xjqsh.lrtactical.item.index.ConsumableIndex;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(bus = Bus.MOD)
public class ModItems {
   public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.f_279569_, "lrtactical");
   public static final RegistryObject<CreativeModeTab> THROWABLE_TAB = TABS.register(
      "throwable",
      () -> CreativeModeTab.builder()
         .m_257941_(Component.m_237115_("item_group.lrtactical.throwable"))
         .m_257737_(ModItems::getThrowableIcon)
         .m_257501_(ModItems::fillThrowables)
         .m_257652_()
   );
   public static final RegistryObject<CreativeModeTab> CONSUMABLE_TAB = TABS.register(
      "consumable",
      () -> CreativeModeTab.builder()
         .m_257941_(Component.m_237115_("item_group.lrtactical.consumable"))
         .m_257737_(ModItems::getConsumableIcon)
         .m_257501_(ModItems::fillConsumables)
         .withTabsBefore(new ResourceLocation[]{THROWABLE_TAB.getId()})
         .m_257652_()
   );
   public static final RegistryObject<CreativeModeTab> MELEE_TAB = TABS.register(
      "melee",
      () -> CreativeModeTab.builder()
         .m_257941_(Component.m_237115_("item_group.lrtactical.melee"))
         .m_257737_(ModItems::getMeleeIcon)
         .m_257501_(ModItems::fillMeleeWeapons)
         .withTabsBefore(new ResourceLocation[]{CONSUMABLE_TAB.getId()})
         .m_257652_()
   );
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "lrtactical");
   public static RegistryObject<ConsumableItem> CONSUMABLE = ITEMS.register("consumable", ConsumableItem::new);
   public static RegistryObject<ThrowableItem> THROWABLE = ITEMS.register("throwable", ThrowableItem::new);
   public static RegistryObject<MeleeItem> MELEE = ITEMS.register("melee", MeleeItem::new);
   public static RegistryObject<FlashShieldItem> FLASH_SHIELD = ITEMS.register("flash_shield", FlashShieldItem::new);
   public static RegistryObject<DetonatorItem> DETONATOR = ITEMS.register("detonator", DetonatorItem::new);

   public static ItemStack getThrowableIcon() {
      ItemStack stack = new ItemStack((ItemLike)THROWABLE.get());
      IThrowable iThrowable = IThrowable.of(stack);
      if (iThrowable != null) {
         iThrowable.setId(stack, new ResourceLocation("lrtactical", "m67"));
      }

      return stack;
   }

   public static ItemStack getConsumableIcon() {
      ItemStack stack = new ItemStack((ItemLike)CONSUMABLE.get());
      IConsumable consumable = IConsumable.of(stack);
      if (consumable != null) {
         consumable.setId(stack, new ResourceLocation("lrtactical", "blood_pack"));
      }

      return stack;
   }

   public static ItemStack getMeleeIcon() {
      ItemStack stack = new ItemStack((ItemLike)MELEE.get());
      IMeleeWeapon iMeleeWeapon = IMeleeWeapon.of(stack);
      if (iMeleeWeapon != null) {
         iMeleeWeapon.setId(stack, new ResourceLocation("lrtactical", "karambit"));
      }

      return stack;
   }

   public static void fillConsumables(ItemDisplayParameters pParameters, Output pOutput) {
      for (ConsumableIndex index : LrTacticalAPI.getConsumableIndexes()) {
         ItemStack stack = index.createItemStack();
         pOutput.m_246342_(stack);
      }
   }

   public static void fillThrowables(ItemDisplayParameters pParameters, Output pOutput) {
      for (ThrowableIndex<?, ?> index : LrTacticalAPI.getThrowableIndexes()) {
         ItemStack stack = index.createItemStack();
         pOutput.m_246342_(stack);
      }

      pOutput.m_246342_(new ItemStack((ItemLike)DETONATOR.get()));
   }

   public static void fillMeleeWeapons(ItemDisplayParameters pParameters, Output pOutput) {
      for (MeleeWeaponIndex<?> index : LrTacticalAPI.getMeleeIndexes()) {
         ItemStack stack = index.createItemStack();
         pOutput.m_246342_(stack);
      }

      pOutput.m_246342_(new ItemStack((ItemLike)FLASH_SHIELD.get()));
   }
}
