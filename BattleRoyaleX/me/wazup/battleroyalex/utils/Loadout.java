package me.wazup.battleroyalex.utils;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.LoadoutsManager;
import me.wazup.battleroyalex.utils.Enums.LoadoutCategory;
import me.zombie_striker.qg.ammo.Ammo;
import me.zombie_striker.qg.api.QualityArmory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Loadout {
   public Inventory inventory;
   int currentPoints;
   public int maxPoints = 10;

   public Loadout(int var1, String var2) {
      this.inventory = Bukkit.createInventory(null, 45, ChatColor.BLUE + "Loadout " + var1);
      Utils.cageInventory(this.inventory, true);
      this.inventory.setItem(this.inventory.getSize() - 1, (ItemStack)Customization.getInstance().items.get("Back"));
      String var3 = ChatColor.GRAY + "Click to customize!";
      this.inventory.setItem(0, new ItemStackBuilder(Material.LEATHER_HELMET).setName(ChatColor.GREEN + "Helmet:").addLore(new String[]{var3}).build());
      this.inventory.setItem(1, new ItemStackBuilder(Material.LEATHER_CHESTPLATE).setName(ChatColor.GREEN + "Chestplate:").addLore(new String[]{var3}).build());
      this.inventory.setItem(2, new ItemStackBuilder(Material.LEATHER_LEGGINGS).setName(ChatColor.GREEN + "Leggings:").addLore(new String[]{var3}).build());
      this.inventory.setItem(3, new ItemStackBuilder(Material.LEATHER_BOOTS).setName(ChatColor.GREEN + "Boots:").addLore(new String[]{var3}).build());
      this.inventory.setItem(4, new ItemStackBuilder(Material.IRON_SWORD).setName(ChatColor.GREEN + "Main weapon:").addLore(new String[]{var3}).build());
      this.inventory
         .setItem(
            5, new ItemStackBuilder(XMaterial.WOODEN_SWORD.parseMaterial()).setName(ChatColor.GREEN + "Secondary weapon:").addLore(new String[]{var3}).build()
         );
      this.inventory.setItem(6, new ItemStackBuilder(Material.CHEST).setName(ChatColor.GREEN + "Item:").addLore(new String[]{var3}).build());
      int var4 = 18;
      if (var2.isEmpty()) {
         for (ItemStack var6 : LoadoutsManager.getInstance().getDefaults()) {
            int var7 = LoadoutsManager.getInstance().getPoints(var6);
            if (this.currentPoints + var7 <= this.maxPoints) {
               this.inventory.setItem(var4++, var6);
               this.currentPoints += var7;
            }
         }
      } else {
         int var14 = 0;
         String[] var15 = var2.split(":");

         for (LoadoutCategory var10 : LoadoutCategory.values()) {
            int var11;
            if (var14 == var15.length) {
               var11 = -1;
            } else {
               var11 = Integer.parseInt(var15[var14++]);
            }

            ItemStack var12 = LoadoutsManager.getInstance().getItem(var10, var11);
            if (var12 != null) {
               int var13 = LoadoutsManager.getInstance().getPoints(var12);
               if (this.currentPoints + var13 <= this.maxPoints) {
                  this.inventory.setItem(var4++, var12);
                  this.currentPoints += var13;
               }
            } else {
               this.inventory.setItem(var4++, new ItemStack(Material.AIR));
            }
         }
      }

      this.updatePoints();
   }

   public boolean equipItem(LoadoutCategory var1, ItemStack var2) {
      int var3 = LoadoutsManager.getInstance().getPoints(var2);
      int var4 = LoadoutsManager.getInstance().getPoints(this.inventory.getItem(var1.slot));
      if (this.currentPoints + var3 - var4 <= this.maxPoints) {
         this.inventory.setItem(var1.slot, var2);
         this.currentPoints += var3 - var4;
         this.updatePoints();
         return true;
      } else {
         return false;
      }
   }

   public void updatePoints() {
      this.inventory
         .setItem(
            this.inventory.getSize() - 9,
            new ItemStackBuilder(Material.DIAMOND).setName(ChatColor.GREEN + "Points: " + this.currentPoints + "/" + this.maxPoints).build()
         );
   }

   public String toString() {
      String var1 = "";

      for (LoadoutCategory var5 : LoadoutCategory.values()) {
         ItemStack var6 = this.inventory.getItem(var5.slot);
         int var7 = LoadoutsManager.getInstance().getSlot(var6);
         if (var1.isEmpty()) {
            var1 = String.valueOf(var7);
         } else {
            var1 = var1 + ":" + var7;
         }
      }

      return var1;
   }

   public void apply(Player var1) {
      var1.getInventory().setHelmet(this.inventory.getItem(LoadoutCategory.Helmets.slot));
      var1.getInventory().setChestplate(this.inventory.getItem(LoadoutCategory.Chestplates.slot));
      var1.getInventory().setLeggings(this.inventory.getItem(LoadoutCategory.Leggings.slot));
      var1.getInventory().setBoots(this.inventory.getItem(LoadoutCategory.Boots.slot));
      ItemStack var2 = this.inventory.getItem(LoadoutCategory.Main_Weapon.slot);
      if (var2 != null) {
         var1.getInventory().addItem(new ItemStack[]{var2});
      }

      ItemStack var3 = this.inventory.getItem(LoadoutCategory.Secondary_Weapon.slot);
      if (var3 != null) {
         var1.getInventory().addItem(new ItemStack[]{var3});
      }

      ItemStack var4 = this.inventory.getItem(LoadoutCategory.Items.slot);
      if (var4 != null) {
         var1.getInventory().addItem(new ItemStack[]{new ItemStackBuilder(var4.clone()).setAmount(3).build()});
      }

      if (BattleRoyaleX.getInstance().QualityArmoryEnabled) {
         int var5 = 9;
         if (var2 != null && QualityArmory.isGun(var2)) {
            Ammo var6 = QualityArmory.getGun(var2).getAmmoType();
            int var7 = var6.getMaxItemStack() * 3;

            while (var7 > 0) {
               int var8 = Math.min(var7, 64);
               var7 -= var8;
               var1.getInventory().setItem(var5++, new ItemStackBuilder(var6.getItemStack()).setAmount(var8).build());
            }
         }

         if (var3 != null && QualityArmory.isGun(var3)) {
            Ammo var9 = QualityArmory.getGun(var3).getAmmoType();
            int var10 = var9.getMaxItemStack() * 3;

            while (var10 > 0) {
               int var11 = Math.min(var10, 64);
               var10 -= var11;
               var1.getInventory().setItem(var5++, new ItemStackBuilder(var9.getItemStack()).setAmount(var11).build());
            }
         }
      }
   }
}
