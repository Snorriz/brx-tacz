package net.mcreator.miklosmilitaryarmorr.init;

import net.mcreator.miklosmilitaryarmorr.world.inventory.ChangelogguiMenu;
import net.mcreator.miklosmilitaryarmorr.world.inventory.Factionbook1Menu;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookcsouMenu;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookcsoutoolsMenu;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookcsouuniformMenu;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookuconMenu;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookucontoolsMenu;
import net.mcreator.miklosmilitaryarmorr.world.inventory.FactionbookuconuniformMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MmArmorModMenus {
   public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, "mm_armor");
   public static final RegistryObject<MenuType<Factionbook1Menu>> FACTIONBOOK_1 = REGISTRY.register(
      "factionbook_1", () -> IForgeMenuType.create(Factionbook1Menu::new)
   );
   public static final RegistryObject<MenuType<FactionbookuconMenu>> FACTIONBOOKUCON = REGISTRY.register(
      "factionbookucon", () -> IForgeMenuType.create(FactionbookuconMenu::new)
   );
   public static final RegistryObject<MenuType<FactionbookcsouMenu>> FACTIONBOOKCSOU = REGISTRY.register(
      "factionbookcsou", () -> IForgeMenuType.create(FactionbookcsouMenu::new)
   );
   public static final RegistryObject<MenuType<FactionbookcsoutoolsMenu>> FACTIONBOOKCSOUTOOLS = REGISTRY.register(
      "factionbookcsoutools", () -> IForgeMenuType.create(FactionbookcsoutoolsMenu::new)
   );
   public static final RegistryObject<MenuType<FactionbookucontoolsMenu>> FACTIONBOOKUCONTOOLS = REGISTRY.register(
      "factionbookucontools", () -> IForgeMenuType.create(FactionbookucontoolsMenu::new)
   );
   public static final RegistryObject<MenuType<FactionbookcsouuniformMenu>> FACTIONBOOKCSOUUNIFORM = REGISTRY.register(
      "factionbookcsouuniform", () -> IForgeMenuType.create(FactionbookcsouuniformMenu::new)
   );
   public static final RegistryObject<MenuType<FactionbookuconuniformMenu>> FACTIONBOOKUCONUNIFORM = REGISTRY.register(
      "factionbookuconuniform", () -> IForgeMenuType.create(FactionbookuconuniformMenu::new)
   );
   public static final RegistryObject<MenuType<ChangelogguiMenu>> CHANGELOGGUI = REGISTRY.register(
      "changeloggui", () -> IForgeMenuType.create(ChangelogguiMenu::new)
   );
}
