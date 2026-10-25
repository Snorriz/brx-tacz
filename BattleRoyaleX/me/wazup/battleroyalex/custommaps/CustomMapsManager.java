package me.wazup.battleroyalex.custommaps;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import me.wazup.battleroyalex.managers.FilesManager;
import me.wazup.battleroyalex.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class CustomMapsManager {
   private static CustomMapsManager instance;
   private HashMap<String, ItemStack> maps;
   private Method setMapId;
   private Method setMapView;
   public HashMap<String, Byte> colorPalette;

   public CustomMapsManager() {
      instance = this;
      this.maps = new HashMap();

      try {
         this.setMapView = MapMeta.class.getDeclaredMethod("setMapView", new Class[]{MapView.class});
      } catch (NoSuchMethodException | SecurityException var4) {
         try {
            this.setMapId = MapMeta.class.getDeclaredMethod("setMapId", new Class[]{int.class});
         } catch (NoSuchMethodException | SecurityException var3) {
         }
      }
   }

   public void clear() {
      this.maps.clear();
   }

   public static CustomMapsManager getInstance() {
      return instance;
   }

   public void registerMap(String var1, MapRenderer var2) {
      MapView var3 = Bukkit.createMap((World)Bukkit.getWorlds().get(0));

      for (MapRenderer var5 : var3.getRenderers()) {
         var3.removeRenderer(var5);
      }

      var3.addRenderer(var2);
      ItemStack var6 = this.generateItemStack(var3);
      this.maps.put(var1, var6);
   }

   public ItemStack generateItemStack(MapView var1) {
      ItemStack var2 = XMaterial.FILLED_MAP.parseItem();
      if (this.setMapView == null && this.setMapId == null) {
         var2.setDurability((short)this.getViewId(var1));
      } else {
         MapMeta var3 = (MapMeta)var2.getItemMeta();
         if (this.setMapView != null) {
            try {
               this.setMapView.invoke(var3, new Object[]{var1});
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException var6) {
               var6.printStackTrace();
            }
         } else {
            try {
               this.setMapId.invoke(var3, new Object[]{this.getViewId(var1)});
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException var5) {
               var5.printStackTrace();
            }
         }

         var2.setItemMeta(var3);
      }

      return var2;
   }

   private int getViewId(MapView var1) {
      try {
         String var2 = MapView.class.getMethod("getId", new Class[0]).invoke(var1, new Object[0]).toString();
         return Integer.valueOf(var2);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException var3) {
         var3.printStackTrace();
         return -1;
      }
   }

   public boolean isRegistered(String var1) {
      return this.maps.containsKey(var1);
   }

   public ItemStack getRegisteration(String var1) {
      return (ItemStack)this.maps.get(var1);
   }

   public void loadColorPalete() {
      this.colorPalette = new HashMap();
      FileConfiguration var1 = FilesManager.getInstance().getConfig("map-palette.yml");
      if (var1.getConfigurationSection("Materials") == null || var1.getConfigurationSection("Materials").getKeys(false).isEmpty()) {
         HashMap var2 = new HashMap();
         this.addMaterialColor(var2, XMaterial.GRASS, new Color(49, 101, 25));
         this.addMaterialColor(var2, XMaterial.TALL_GRASS, new Color(49, 101, 25));
         this.addMaterialColor(var2, XMaterial.COBBLESTONE, new Color(130, 130, 130));
         this.addMaterialColor(var2, XMaterial.COBBLESTONE_STAIRS, new Color(130, 130, 130));
         this.addMaterialColor(var2, XMaterial.COBBLESTONE_SLAB, new Color(130, 130, 130));
         this.addMaterialColor(var2, XMaterial.FURNACE, new Color(130, 130, 130));
         this.addMaterialColor(var2, XMaterial.STONE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.STONE_SLAB, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.IRON_ORE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.GOLD_ORE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.REDSTONE_ORE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.DIAMOND_ORE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.COAL_ORE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.EMERALD_ORE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.LAPIS_ORE, new Color(117, 117, 117));
         this.addMaterialColor(var2, XMaterial.IRON_BLOCK, new Color(236, 236, 236));
         this.addMaterialColor(var2, XMaterial.COBWEB, new Color(236, 236, 236));
         this.addMaterialColor(var2, XMaterial.GOLD_BLOCK, new Color(243, 223, 75));
         this.addMaterialColor(var2, XMaterial.REDSTONE_BLOCK, new Color(196, 25, 16));
         this.addMaterialColor(var2, XMaterial.RED_BED, new Color(196, 25, 16));
         this.addMaterialColor(var2, XMaterial.DIAMOND_BLOCK, new Color(95, 233, 217));
         this.addMaterialColor(var2, XMaterial.ICE, new Color(95, 233, 217));
         this.addMaterialColor(var2, XMaterial.COAL_BLOCK, new Color(19, 19, 19));
         this.addMaterialColor(var2, XMaterial.EMERALD_BLOCK, new Color(71, 213, 105));
         this.addMaterialColor(var2, XMaterial.LAPIS_BLOCK, new Color(42, 80, 139));
         this.addMaterialColor(var2, XMaterial.WATER, new Color(67, 101, 165));
         this.addMaterialColor(var2, XMaterial.SEAGRASS, new Color(67, 101, 165));
         this.addMaterialColor(var2, XMaterial.BUBBLE_COLUMN, new Color(67, 101, 165));
         this.addMaterialColor(var2, XMaterial.TALL_SEAGRASS, new Color(67, 101, 165));
         this.addMaterialColor(var2, XMaterial.KELP, new Color(67, 101, 165));
         this.addMaterialColor(var2, XMaterial.GRASS_BLOCK, new Color(82, 129, 69));
         this.addMaterialColor(var2, XMaterial.DIRT, new Color(168, 120, 83));
         this.addMaterialColor(var2, XMaterial.SAND, new Color(222, 215, 172));
         this.addMaterialColor(var2, XMaterial.SANDSTONE, new Color(213, 207, 162));
         this.addMaterialColor(var2, XMaterial.ACACIA_LEAVES, new Color(73, 181, 24));
         this.addMaterialColor(var2, XMaterial.BIRCH_LEAVES, new Color(114, 149, 76));
         this.addMaterialColor(var2, XMaterial.DARK_OAK_LEAVES, new Color(72, 186, 18));
         this.addMaterialColor(var2, XMaterial.JUNGLE_LEAVES, new Color(74, 185, 25));
         this.addMaterialColor(var2, XMaterial.OAK_LEAVES, new Color(73, 183, 24));
         this.addMaterialColor(var2, XMaterial.SPRUCE_LEAVES, new Color(55, 91, 56));
         this.addMaterialColor(var2, XMaterial.COARSE_DIRT, new Color(104, 75, 51));
         this.addMaterialColor(var2, XMaterial.ANDESITE, new Color(136, 136, 138));
         this.addMaterialColor(var2, XMaterial.DIORITE, new Color(181, 181, 181));
         this.addMaterialColor(var2, XMaterial.DEAD_BUSH, new Color(144, 97, 39));
         this.addMaterialColor(var2, XMaterial.CACTUS, new Color(76, 107, 35));
         this.addMaterialColor(var2, XMaterial.DANDELION, new Color(247, 229, 77));
         this.addMaterialColor(var2, XMaterial.POPPY, new Color(230, 47, 43));
         this.addMaterialColor(var2, XMaterial.CORNFLOWER, new Color(70, 106, 235));
         this.addMaterialColor(var2, XMaterial.AZURE_BLUET, new Color(210, 215, 223));
         this.addMaterialColor(var2, XMaterial.QUARTZ_BLOCK, new Color(210, 215, 223));
         this.addMaterialColor(var2, XMaterial.GLASS, new Color(210, 215, 223));
         this.addMaterialColor(var2, XMaterial.OXEYE_DAISY, new Color(187, 188, 189));
         this.addMaterialColor(var2, XMaterial.LAVA, new Color(211, 124, 40));
         this.addMaterialColor(var2, XMaterial.GRANITE, new Color(156, 111, 91));
         this.addMaterialColor(var2, XMaterial.REDSTONE_LAMP, new Color(123, 73, 33));
         this.addMaterialColor(var2, XMaterial.GRAVEL, new Color(139, 135, 134));
         this.addMaterialColor(var2, XMaterial.SPRUCE_LOG, new Color(48, 34, 25));
         this.addMaterialColor(var2, XMaterial.OAK_LOG, new Color(58, 35, 9));
         this.addMaterialColor(var2, XMaterial.BIRCH_LOG, new Color(196, 195, 193));
         this.addMaterialColor(var2, XMaterial.JUNGLE_LOG, new Color(89, 76, 37));
         this.addMaterialColor(var2, XMaterial.ACACIA_LOG, new Color(95, 95, 85));
         this.addMaterialColor(var2, XMaterial.DARK_OAK_LOG, new Color(35, 27, 16));
         this.addMaterialColor(var2, XMaterial.SPRUCE_PLANKS, new Color(100, 78, 47));
         this.addMaterialColor(var2, XMaterial.OAK_PLANKS, new Color(172, 140, 88));
         this.addMaterialColor(var2, XMaterial.BIRCH_PLANKS, new Color(202, 185, 131));
         this.addMaterialColor(var2, XMaterial.JUNGLE_PLANKS, new Color(172, 124, 89));
         this.addMaterialColor(var2, XMaterial.ACACIA_PLANKS, new Color(178, 102, 60));
         this.addMaterialColor(var2, XMaterial.DARK_OAK_PLANKS, new Color(62, 41, 18));
         this.addMaterialColor(var2, XMaterial.SPRUCE_FENCE, new Color(100, 78, 47));
         this.addMaterialColor(var2, XMaterial.OAK_FENCE, new Color(172, 140, 88));
         this.addMaterialColor(var2, XMaterial.BIRCH_FENCE, new Color(202, 185, 131));
         this.addMaterialColor(var2, XMaterial.JUNGLE_FENCE, new Color(172, 124, 89));
         this.addMaterialColor(var2, XMaterial.ACACIA_FENCE, new Color(178, 102, 60));
         this.addMaterialColor(var2, XMaterial.DARK_OAK_FENCE, new Color(62, 41, 18));
         this.addMaterialColor(var2, XMaterial.SPRUCE_STAIRS, new Color(100, 78, 47));
         this.addMaterialColor(var2, XMaterial.OAK_STAIRS, new Color(172, 140, 88));
         this.addMaterialColor(var2, XMaterial.BIRCH_STAIRS, new Color(202, 185, 131));
         this.addMaterialColor(var2, XMaterial.JUNGLE_STAIRS, new Color(172, 124, 89));
         this.addMaterialColor(var2, XMaterial.ACACIA_STAIRS, new Color(178, 102, 60));
         this.addMaterialColor(var2, XMaterial.DARK_OAK_STAIRS, new Color(62, 41, 18));
         this.addMaterialColor(var2, XMaterial.SPRUCE_SLAB, new Color(100, 78, 47));
         this.addMaterialColor(var2, XMaterial.OAK_SLAB, new Color(172, 140, 88));
         this.addMaterialColor(var2, XMaterial.BIRCH_SLAB, new Color(202, 185, 131));
         this.addMaterialColor(var2, XMaterial.JUNGLE_SLAB, new Color(172, 124, 89));
         this.addMaterialColor(var2, XMaterial.ACACIA_SLAB, new Color(178, 102, 60));
         this.addMaterialColor(var2, XMaterial.DARK_OAK_SLAB, new Color(62, 41, 18));
         this.addMaterialColor(var2, XMaterial.CRAFTING_TABLE, new Color(172, 140, 88));
         this.addMaterialColor(var2, XMaterial.BOOKSHELF, new Color(172, 140, 88));
         this.addMaterialColor(var2, XMaterial.SUGAR_CANE, new Color(71, 139, 42));
         this.addMaterialColor(var2, XMaterial.BEDROCK, new Color(47, 47, 47));
         this.addMaterialColor(var2, XMaterial.TORCH, new Color(206, 173, 26));
         this.addMaterialColor(var2, XMaterial.PUMPKIN, new Color(222, 141, 28));
         this.addMaterialColor(var2, XMaterial.CARVED_PUMPKIN, new Color(222, 141, 28));
         this.addMaterialColor(var2, XMaterial.JACK_O_LANTERN, new Color(222, 141, 28));
         this.addMaterialColor(var2, XMaterial.TNT, new Color(203, 49, 26));
         this.addMaterialColor(var2, XMaterial.BLACK_WOOL, new Color(6, 7, 12));
         this.addMaterialColor(var2, XMaterial.WHITE_WOOL, new Color(225, 226, 228));
         this.addMaterialColor(var2, XMaterial.BLUE_WOOL, new Color(45, 50, 145));
         this.addMaterialColor(var2, XMaterial.BROWN_WOOL, new Color(105, 70, 39));
         this.addMaterialColor(var2, XMaterial.CYAN_WOOL, new Color(21, 139, 145));
         this.addMaterialColor(var2, XMaterial.GRAY_WOOL, new Color(64, 67, 72));
         this.addMaterialColor(var2, XMaterial.GREEN_WOOL, new Color(83, 108, 20));
         this.addMaterialColor(var2, XMaterial.LIGHT_BLUE_WOOL, new Color(121, 148, 202));
         this.addMaterialColor(var2, XMaterial.LIGHT_GRAY_WOOL, new Color(164, 168, 169));
         this.addMaterialColor(var2, XMaterial.LIME_WOOL, new Color(122, 198, 38));
         this.addMaterialColor(var2, XMaterial.MAGENTA_WOOL, new Color(188, 66, 179));
         this.addMaterialColor(var2, XMaterial.ORANGE_WOOL, new Color(240, 125, 30));
         this.addMaterialColor(var2, XMaterial.PINK_WOOL, new Color(242, 148, 177));
         this.addMaterialColor(var2, XMaterial.PURPLE_WOOL, new Color(129, 65, 182));
         this.addMaterialColor(var2, XMaterial.RED_WOOL, new Color(155, 53, 49));
         this.addMaterialColor(var2, XMaterial.YELLOW_WOOL, new Color(195, 182, 47));
         this.addMaterialColor(var2, XMaterial.BLACK_BANNER, new Color(6, 7, 12));
         this.addMaterialColor(var2, XMaterial.WHITE_BANNER, new Color(225, 226, 228));
         this.addMaterialColor(var2, XMaterial.BLUE_BANNER, new Color(45, 50, 145));
         this.addMaterialColor(var2, XMaterial.BROWN_BANNER, new Color(105, 70, 39));
         this.addMaterialColor(var2, XMaterial.CYAN_BANNER, new Color(21, 139, 145));
         this.addMaterialColor(var2, XMaterial.GRAY_BANNER, new Color(64, 67, 72));
         this.addMaterialColor(var2, XMaterial.GREEN_BANNER, new Color(83, 108, 20));
         this.addMaterialColor(var2, XMaterial.LIGHT_BLUE_BANNER, new Color(121, 148, 202));
         this.addMaterialColor(var2, XMaterial.LIGHT_GRAY_BANNER, new Color(164, 168, 169));
         this.addMaterialColor(var2, XMaterial.LIME_BANNER, new Color(122, 198, 38));
         this.addMaterialColor(var2, XMaterial.MAGENTA_BANNER, new Color(188, 66, 179));
         this.addMaterialColor(var2, XMaterial.ORANGE_BANNER, new Color(240, 125, 30));
         this.addMaterialColor(var2, XMaterial.PINK_BANNER, new Color(242, 148, 177));
         this.addMaterialColor(var2, XMaterial.PURPLE_BANNER, new Color(129, 65, 182));
         this.addMaterialColor(var2, XMaterial.RED_BANNER, new Color(155, 53, 49));
         this.addMaterialColor(var2, XMaterial.YELLOW_BANNER, new Color(195, 182, 47));
         this.addMaterialColor(var2, XMaterial.BLACK_WALL_BANNER, new Color(6, 7, 12));
         this.addMaterialColor(var2, XMaterial.WHITE_WALL_BANNER, new Color(225, 226, 228));
         this.addMaterialColor(var2, XMaterial.BLUE_WALL_BANNER, new Color(45, 50, 145));
         this.addMaterialColor(var2, XMaterial.BROWN_WALL_BANNER, new Color(105, 70, 39));
         this.addMaterialColor(var2, XMaterial.CYAN_WALL_BANNER, new Color(21, 139, 145));
         this.addMaterialColor(var2, XMaterial.GRAY_WALL_BANNER, new Color(64, 67, 72));
         this.addMaterialColor(var2, XMaterial.GREEN_WALL_BANNER, new Color(83, 108, 20));
         this.addMaterialColor(var2, XMaterial.LIGHT_BLUE_WALL_BANNER, new Color(121, 148, 202));
         this.addMaterialColor(var2, XMaterial.LIGHT_GRAY_WALL_BANNER, new Color(164, 168, 169));
         this.addMaterialColor(var2, XMaterial.LIME_WALL_BANNER, new Color(122, 198, 38));
         this.addMaterialColor(var2, XMaterial.MAGENTA_WALL_BANNER, new Color(188, 66, 179));
         this.addMaterialColor(var2, XMaterial.ORANGE_WALL_BANNER, new Color(240, 125, 30));
         this.addMaterialColor(var2, XMaterial.PINK_WALL_BANNER, new Color(242, 148, 177));
         this.addMaterialColor(var2, XMaterial.PURPLE_WALL_BANNER, new Color(129, 65, 182));
         this.addMaterialColor(var2, XMaterial.RED_WALL_BANNER, new Color(155, 53, 49));
         this.addMaterialColor(var2, XMaterial.YELLOW_WALL_BANNER, new Color(195, 182, 47));
         this.addMaterialColor(var2, XMaterial.BLACK_CONCRETE, new Color(7, 9, 14));
         this.addMaterialColor(var2, XMaterial.WHITE_CONCRETE, new Color(199, 202, 207));
         this.addMaterialColor(var2, XMaterial.BLUE_CONCRETE, new Color(42, 44, 133));
         this.addMaterialColor(var2, XMaterial.BROWN_CONCRETE, new Color(91, 57, 30));
         this.addMaterialColor(var2, XMaterial.CYAN_CONCRETE, new Color(20, 113, 129));
         this.addMaterialColor(var2, XMaterial.GRAY_CONCRETE, new Color(118, 119, 110));
         this.addMaterialColor(var2, XMaterial.GREEN_CONCRETE, new Color(88, 156, 25));
         this.addMaterialColor(var2, XMaterial.LIGHT_BLUE_CONCRETE, new Color(33, 130, 190));
         this.addMaterialColor(var2, XMaterial.LIGHT_GRAY_CONCRETE, new Color(195, 203, 206));
         this.addMaterialColor(var2, XMaterial.LIME_CONCRETE, new Color(90, 162, 23));
         this.addMaterialColor(var2, XMaterial.MAGENTA_CONCRETE, new Color(162, 47, 152));
         this.addMaterialColor(var2, XMaterial.ORANGE_CONCRETE, new Color(207, 81, 1));
         this.addMaterialColor(var2, XMaterial.PINK_CONCRETE, new Color(205, 95, 138));
         this.addMaterialColor(var2, XMaterial.PURPLE_CONCRETE, new Color(155, 45, 145));
         this.addMaterialColor(var2, XMaterial.RED_CONCRETE, new Color(136, 30, 33));
         this.addMaterialColor(var2, XMaterial.YELLOW_CONCRETE, new Color(222, 162, 19));
         this.addMaterialColor(var2, XMaterial.CRAFTING_TABLE, new Color(33, 23, 57));
         this.addMaterialColor(var2, XMaterial.CHEST, new Color(33, 23, 57));
         this.addMaterialColor(var2, XMaterial.ENCHANTING_TABLE, new Color(33, 23, 57));
         this.addMaterialColor(var2, XMaterial.SNOW, new Color(232, 240, 239));
         this.addMaterialColor(var2, XMaterial.SNOW_BLOCK, new Color(232, 240, 239));
         this.addMaterialColor(var2, XMaterial.OBSIDIAN, new Color(33, 23, 57));

         for (String var4 : var2.keySet()) {
            if (var4 != null) {
               Color var5 = (Color)var2.get(var4);
               var1.set("Materials." + var4, var5.getRed() + "-" + var5.getGreen() + "-" + var5.getBlue());
            }
         }

         FilesManager.getInstance().saveConfig("map-palette.yml");
      }

      for (String var7 : var1.getConfigurationSection("Materials").getKeys(false)) {
         String[] var8 = var1.getString("Materials." + var7).split("-");
         this.colorPalette.put(var7, MapPalette.matchColor(Integer.parseInt(var8[0]), Integer.parseInt(var8[1]), Integer.parseInt(var8[2])));
      }
   }

   private void addMaterialColor(HashMap<String, Color> var1, XMaterial var2, Color var3) {
      Material var4 = var2.parseMaterial();
      if (var4 != null) {
         var1.put(var4.name(), var3);

         for (String var8 : var2.getLegacy()) {
            var1.put(var8, var3);
         }
      }
   }
}
