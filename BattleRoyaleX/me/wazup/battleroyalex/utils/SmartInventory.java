package me.wazup.battleroyalex.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SmartInventory {
   private String mainName;
   private HashMap<Integer, Inventory> inventories = new HashMap();
   public static final int[] slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};

   public SmartInventory(String var1) {
      this.mainName = var1 + ": ";
   }

   public int addInventory(String var1) {
      int var2 = this.inventories.size();
      Inventory var3 = Bukkit.createInventory(null, 54, this.mainName + var1);
      Utils.cageInventory(var3, false);
      if (var2 != 0) {
         var3.setItem(18, (ItemStack)Customization.getInstance().items.get("Previous"));
         var3.setItem(27, (ItemStack)Customization.getInstance().items.get("Previous"));
         Inventory var4 = (Inventory)this.inventories.get(var2 - 1);
         var4.setItem(26, (ItemStack)Customization.getInstance().items.get("Next"));
         var4.setItem(35, (ItemStack)Customization.getInstance().items.get("Next"));
      }

      this.inventories.put(var2, var3);
      return var2;
   }

   public void setItem(int var1, int var2, ItemStack var3) {
      ((Inventory)this.inventories.get(var1)).setItem(var2, var3);
   }

   public boolean addItem(int var1, ItemStack var2) {
      Inventory var3 = (Inventory)this.inventories.get(var1);
      int var4 = this.getEmptySlot(var1);
      if (var4 == -1) {
         return false;
      }

      var3.setItem(var4, var2);
      return true;
   }

   public void removeItem(int var1, int var2) {
      this.setItem(var1, var2, new ItemStack(Material.AIR));
      this.organize(var1);
   }

   public ItemStack getItem(int var1, int var2) {
      return ((Inventory)this.inventories.get(var1)).getItem(var2);
   }

   public void clear(int var1) {
      Inventory var2 = (Inventory)this.inventories.get(var1);

      for (int var6 : slots) {
         var2.setItem(var6, new ItemStack(Material.AIR));
      }
   }

   public void organize(int var1) {
      Inventory var2 = (Inventory)this.inventories.get(var1);
      ArrayList<ItemStack> var3 = new ArrayList<>();

      for (int var7 : slots) {
         ItemStack var8 = var2.getItem(var7);
         if (var8 != null) {
            var3.add(var8);
            var2.setItem(var7, new ItemStack(Material.AIR));
         }
      }

      int var9 = 0;

      for (ItemStack var11 : var3) {
         var2.setItem(slots[var9], var11);
         var9++;
      }
   }

   public int getEmptySlot(int var1) {
      Inventory var2 = (Inventory)this.inventories.get(var1);

      for (int var6 : slots) {
         if (var2.getItem(var6) == null) {
            return var6;
         }
      }

      return -1;
   }

   public List<ItemStack> getContents(int var1) {
      ArrayList<ItemStack> var2 = new ArrayList<>();
      Inventory var3 = (Inventory)this.inventories.get(var1);

      for (int var7 : slots) {
         ItemStack var8 = var3.getItem(var7);
         if (var8 != null) {
            var2.add(var8);
         }
      }

      return var2;
   }

   public boolean handleClick(Player var1, ItemStack var2, Inventory var3) {
      if (!Utils.compareItem(var2, (ItemStack)Customization.getInstance().items.get("Next"))
         && !Utils.compareItem(var2, (ItemStack)Customization.getInstance().items.get("Previous"))) {
         return false;
      }

      int var4 = this.getId(var3);
      if (Utils.compareItem(var2, (ItemStack)Customization.getInstance().items.get("Next"))) {
         var1.openInventory((Inventory)this.inventories.get(var4 + 1));
      } else {
         var1.openInventory((Inventory)this.inventories.get(var4 - 1));
      }

      return true;
   }

   public int getId(Inventory var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < this.inventories.size(); var3++) {
         var2 = var3;
         if (((Inventory)this.inventories.get(var3)).equals(var1)) {
            break;
         }
      }

      return var2;
   }

   public int getSize() {
      return this.inventories.size();
   }

   public String getName() {
      return this.mainName;
   }

   public void open(Player var1) {
      var1.openInventory((Inventory)this.inventories.get(0));
   }
}
