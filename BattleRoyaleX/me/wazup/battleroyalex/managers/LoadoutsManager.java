package me.wazup.battleroyalex.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.LoadoutCategory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LoadoutsManager {
   public static LoadoutsManager instance;
   HashMap<Integer, LoadoutsManager.InventoryData> inventories;
   HashMap<ItemStack, LoadoutsManager.ItemData> itemData;
   ArrayList<ItemStack> defaults;

   public LoadoutsManager() {
      instance = this;
      FileConfiguration var1 = FilesManager.getInstance().getConfig("shop.yml");
      if (var1.getStringList("Loadout-Items.Main Weapons").isEmpty()) {
         DefaultsManager.createDefaultLoadoutsShop(var1);
      }

      this.inventories = new HashMap();
      this.itemData = new HashMap();
      this.defaults = new ArrayList();
      int var2 = 0;

      for (String var4 : var1.getConfigurationSection("Loadout-Items").getKeys(false)) {
         List<String> var5 = var1.getStringList("Loadout-Items." + var4);
         Inventory var6 = Bukkit.createInventory(null, Utils.getInventorySize(var5.size() + 1), ChatColor.BLUE + "Loadout: " + ChatColor.RED + var4);
         var6.setItem(var6.getSize() - 1, (ItemStack)Customization.getInstance().items.get("Back"));
         int var7 = 0;

         for (String var9 : var5) {
            try {
               int var10 = Integer.parseInt(var9.split(" :: ")[1]);
               ItemStack var11 = Utils.getItemStack(var9.split(" :: ")[0], true, true);
               if (var11 == null) {
                  Bukkit.getConsoleSender()
                     .sendMessage(
                        Customization.getInstance().prefix
                           + ChatColor.RED
                           + "Un item in shop.yml Loadout-Items non puo essere caricato (forse dipendenza mancante?), il formato e': "
                           + var9
                     );
               } else {
                  new ItemStackBuilder(var11).addLore(new String[]{ChatColor.GRAY + "Points: " + ChatColor.GREEN + var10}).build();
                  var6.addItem(new ItemStack[]{var11});
                  this.itemData.put(var11, new LoadoutsManager.ItemData(var10, var7));
                  var7++;
               }
            } catch (Exception var12) {
               Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + ChatColor.RED + "Impossibile caricare l'item loadout " + var9);
               var12.printStackTrace();
            }
         }

         this.inventories.put(var2++, new LoadoutsManager.InventoryData(var6, var7));
         this.defaults.add(var6.getItem(0));
      }
   }

   public static LoadoutsManager getInstance() {
      return instance;
   }

   public ArrayList<ItemStack> getDefaults() {
      return this.defaults;
   }

   public Inventory getInventory(int var1) {
      return !this.inventories.containsKey(var1) ? null : ((LoadoutsManager.InventoryData)this.inventories.get(var1)).inventory;
   }

   public int getPoints(ItemStack var1) {
      return var1 != null && this.itemData.containsKey(var1) ? ((LoadoutsManager.ItemData)this.itemData.get(var1)).points : 0;
   }

   public int getSlot(ItemStack var1) {
      return var1 != null && this.itemData.containsKey(var1) ? ((LoadoutsManager.ItemData)this.itemData.get(var1)).slot : -1;
   }

   public ItemStack getItem(LoadoutCategory var1, int var2) {
      LoadoutsManager.InventoryData var3 = (LoadoutsManager.InventoryData)this.inventories.get(var1.slot - 18);
      return var2 < var3.utilizedSlots && var2 >= 0 ? var3.inventory.getItem(var2) : null;
   }

   private class InventoryData {
      Inventory inventory;
      int utilizedSlots;

      public InventoryData(Inventory var2, int var3) {
         this.inventory = var2;
         this.utilizedSlots = var3;
      }
   }

   private class ItemData {
      int points;
      int slot;

      public ItemData(int var2, int var3) {
         this.points = var2;
         this.slot = var3;
      }
   }
}
