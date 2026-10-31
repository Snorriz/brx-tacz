package me.xjqsh.lrtactical.compat.jei;

import me.xjqsh.lrtactical.init.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

@JeiPlugin
public class LrJeiPlugin implements IModPlugin {
   private static final ResourceLocation UID = new ResourceLocation("lrtactical", "jei");

   public void registerItemSubtypes(ISubtypeRegistration registration) {
      registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, (Item)ModItems.CONSUMABLE.get(), LrSubType.getConsumableSubtype());
      registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, (Item)ModItems.MELEE.get(), LrSubType.getMeleeSubtype());
      registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, (Item)ModItems.THROWABLE.get(), LrSubType.getThrowableSubtype());
   }

   public ResourceLocation getPluginUid() {
      return UID;
   }
}
