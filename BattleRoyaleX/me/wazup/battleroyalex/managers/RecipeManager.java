package me.wazup.battleroyalex.managers;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.SmartInventory;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class RecipeManager {
   public static LinkedHashMap<ItemStack, RecipeManager.RecipeData> customRecipes = new LinkedHashMap();
   public static SmartInventory recipesInventory;

   public RecipeManager() {
      customRecipes.clear();
      recipesInventory = new SmartInventory((String)Customization.getInstance().inventories.get("Recipes"));
      FileConfiguration var1 = FilesManager.getInstance().getConfig("custom-recipes.yml");
      if (var1.getBoolean("Regenerate-Default-Recipes")) {
         var1.addDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(BattleRoyaleX.getInstance().getResource("custom-recipes-defaults.yml"))));
         var1.options().copyDefaults(true);
         var1.set("Regenerate-Default-Recipes", false);
         FilesManager.getInstance().saveConfig("custom-recipes.yml");
      }

      if (!var1.getStringList("Clear-Default-Recipes-For").isEmpty()) {
         Bukkit.resetRecipes();
         ArrayList var2 = new ArrayList();
         List var3 = var1.getStringList("Clear-Default-Recipes-For");
         Iterator var4 = Bukkit.recipeIterator();

         while (var4.hasNext()) {
            Recipe var5 = (Recipe)var4.next();
            if (!var3.contains(var5.getResult().getType().name())) {
               var2.add(var5);
            }
         }

         Bukkit.clearRecipes();

         for (Recipe var6 : var2) {
            Bukkit.addRecipe(var6);
         }
      }

      char[] var21 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

      boolean var22;
      try {
         Class.forName("org.bukkit.NamespacedKey");
         var22 = true;
      } catch (ClassNotFoundException var19) {
         var22 = false;
      }

      if (!var1.getConfigurationSection("Recipes").getKeys(false).isEmpty()) {
         for (String var26 : var1.getConfigurationSection("Recipes").getKeys(false)) {
            try {
               String var28 = "Recipes." + var26 + ".";
               ItemStack var7 = new ItemStackBuilder(Utils.getItemStack(var1.getString(var28 + "Result"), true, true)).build();
               if (!var7.getItemMeta().hasDisplayName()) {
                  var7 = new ItemStackBuilder(var7).addLore(new String[]{ChatColor.GRAY + "-"}).build();
               }

               String[] var8 = new String[3];
               int var9 = 0;
               int var10 = 0;
               LinkedHashMap var11 = new LinkedHashMap();

               for (String var13 : var1.getStringList(var28 + "Recipe")) {
                  String var14 = "";
                  String[] var15 = var13.split(" : ");

                  for (int var16 = 0; var16 < 3; var16++) {
                     char var17 = var21[var10++];
                     var14 = var14 + var17;
                     ItemStack var18 = var15[var16].equals("AIR")
                        ? new ItemStack(Material.AIR)
                        : ((XMaterial)XMaterial.matchXMaterial(var15[var16]).get()).parseItem();
                     var11.put(var17, var18);
                  }

                  var8[var9++] = var14;
               }

               boolean var33 = var1.getBoolean(var28 + "Shapeless");
               Object var34;
               if (var33) {
                  if (var22) {
                     var34 = new ShapelessRecipe(new NamespacedKey(BattleRoyaleX.getInstance(), "Recipes-" + var26), var7);
                  } else {
                     var34 = new ShapelessRecipe(var7);
                  }

                  for (ItemStack var38 : var11.values()) {
                     if (!var38.getType().equals(Material.AIR)) {
                        ((ShapelessRecipe)var34).addIngredient(var38.getType(), var38.getDurability());
                     }
                  }
               } else {
                  if (var22) {
                     var34 = new ShapedRecipe(new NamespacedKey(BattleRoyaleX.getInstance(), "Recipes-" + var26), var7);
                  } else {
                     var34 = new ShapedRecipe(var7);
                  }

                  ((ShapedRecipe)var34).shape(var8);

                  for (char var39 : var11.keySet()) {
                     ItemStack var41 = (ItemStack)var11.get(var39);
                     ((ShapedRecipe)var34).setIngredient(var39, var41.getType(), var41.getDurability());
                  }
               }

               Inventory var37 = Utils.getCraftingInventory(var7, var11.values());
               if (var33) {
                  var37.setItem(18, new ItemStackBuilder(Material.REDSTONE).setName(ChatColor.RED + "Shapeless").build());
               }

               int var40 = var1.getInt(var28 + "Rank-Requirement");
               if (var40 > 0) {
                  var37.setItem(22, new ItemStackBuilder(Material.GOLD_INGOT).setName(ChatColor.YELLOW + "Requires rank: " + var40).build());
               }

               int var42 = var1.getInt(var28 + "Max-Crafts");
               var37.setItem(26, new ItemStackBuilder(Material.CHEST).setName(ChatColor.YELLOW + "Max Crafts: " + var42).build());
               RecipeManager.RecipeData var43 = new RecipeManager.RecipeData(var37, var40, var42);

               while (customRecipes.containsKey(var7)) {
                  var7 = new ItemStackBuilder(var7).addLore(new String[]{ChatColor.GRAY + "-"}).build();
               }

               customRecipes.put(var7, var43);
               Bukkit.addRecipe((Recipe)var34);
            } catch (Exception var20) {
               Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Battle Royal X] Could not load the custom recipe (custom-recipes.yml) named " + var26);
               var20.printStackTrace();
            }
         }
      }

      Iterator var24 = customRecipes.keySet().iterator();

      for (int var27 = 0; var27 < Math.ceil((double)customRecipes.size() / SmartInventory.slots.length); var27++) {
         recipesInventory.addInventory("List #" + (var27 + 1));

         for (int var32 : SmartInventory.slots) {
            if (!var24.hasNext()) {
               break;
            }

            recipesInventory.setItem(var27, var32, (ItemStack)var24.next());
         }
      }
   }

   public class RecipeData {
      public Inventory inventory;
      public int requiredRank;
      public int maxCrafts;

      public RecipeData(Inventory var2, int var3, int var4) {
         this.inventory = var2;
         this.requiredRank = var3;
         this.maxCrafts = var4;
      }
   }
}
