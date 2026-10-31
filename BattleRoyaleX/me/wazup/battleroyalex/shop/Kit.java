package me.wazup.battleroyalex.shop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Enums.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Kit extends Sellable {
   ArrayList<ItemStack> items;
   ItemStack[] armor;
   Collection<PotionEffect> effects;
   List<String> commands;

   public Kit(
      String var1,
      ItemStack var2,
      String var3,
      ArrayList<ItemStack> var4,
      ItemStack[] var5,
      Collection<PotionEffect> var6,
      List<String> var7,
      ArrayList<String> var8,
      Rarity var9,
      int var10
   ) {
      this.name = (String)var1;
      this.permission = var3.equalsIgnoreCase("none") ? "" : var3;
      // Rimuove gli item null (token non risolvibili: es. mod Forge assente) per evitare NPE a runtime
      if (var4 != null) {
         var4.removeIf(java.util.Objects::isNull);
      }
      this.items = var4;
      if (var5 == null) {
         var5 = new ItemStack[4];
      }
      for (int ai = 0; ai < var5.length; ai++) {
         if (var5[ai] == null) {
            var5[ai] = new ItemStack(Material.AIR);
         }
      }
      this.armor = var5;
      this.effects = var6;
      this.commands = var7;
      this.rarity = var9;
      this.value = var10;
      Customization var11 = Customization.getInstance();
      ItemStackBuilder var12 = new ItemStackBuilder(var2).setName(var9.getColor() + var1);
      var12.addLore(new String[]{" ", (String)var11.lores.get("Description")});

      for (String var14 : var8) {
         var12.addLore(new String[]{var14});
      }

      var12.addLore(
         new String[]{
            " ",
            ((String)var11.lores.get("Rarity")).replace("%rarity%", var9.toString()),
            ((String)var11.lores.get("Value")).replace("%price%", String.valueOf(var10))
         }
      );
      var12.addLore(new String[]{" ", (String)var11.lores.get("Armor")});
      String[] var16 = new String[]{"Boots", "Leggings", "Chestplate", "Helmet"};

      for (int var17 = var16.length - 1; var17 > -1; var17--) {
         ItemStack armorPiece = var17 < var5.length ? var5[var17] : null;
         String armorName = armorPiece == null || armorPiece.getType().equals(Material.AIR)
            ? (String)var11.lores.get("No-Items")
            : armorPiece.getType().name().replace("_", " ");
         var12.addLore(
            new String[]{
               ((String)var11.lores.get(var16[var17]))
                  .replace("%" + var16[var17].toLowerCase() + "%", (CharSequence)armorName)
            }
         );
      }

      var12.addLore(new String[]{" ", (String)var11.lores.get("Items")});

      for (ItemStack var15 : var4) {
         if (var15 != null) {
            var12.addLore(new String[]{((String)var11.lores.get("Item")).replace("%item%", var15.getType().name() + " x" + var15.getAmount())});
         }
      }

      if (var4.isEmpty()) {
         var12.addLore(new String[]{(String)var11.lores.get("No-Items")});
      }

      if (!this.permission.isEmpty()) {
         var12.addLore(new String[]{" ", (String)var11.lores.get("Permission-Needed")});
      }

      if (!var1.toLowerCase().equals(Config.getInstance().defaultKit)) {
         var12.addLore(
            new String[]{" ", ((String)var11.lores.get("Selling")).replace("%coins%", String.valueOf((int)(Config.getInstance().selling_value * var10)))}
         );
      }

      this.item = var12.build();
   }

   public void apply(Player var1) {
      for (ItemStack var3 : this.items) {
         var1.getInventory().addItem(new ItemStack[]{var3});
      }

      var1.getInventory().setArmorContents(this.armor);
      var1.addPotionEffects(this.effects);
      if (!this.commands.isEmpty()) {
         for (String var5 : this.commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var5.replace("%player%", var1.getName()));
         }
      }
   }
}
