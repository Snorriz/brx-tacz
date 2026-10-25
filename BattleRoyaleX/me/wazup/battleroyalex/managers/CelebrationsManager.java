package me.wazup.battleroyalex.managers;

import java.util.HashMap;
import java.util.Iterator;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.celebrations.FallingAnvils;
import me.wazup.battleroyalex.celebrations.Ghast;
import me.wazup.battleroyalex.celebrations.PartySpitter;
import me.wazup.battleroyalex.celebrations.Rocket;
import me.wazup.battleroyalex.celebrations.Zeus;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.SmartInventory;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CelebrationsManager {
   private static CelebrationsManager instance;
   private SmartInventory shop;

   public CelebrationsManager() {
      instance = this;
      BattleRoyaleX.getInstance().celebrations = new HashMap();
      FileConfiguration var1 = FilesManager.getInstance().getConfig("celebrations.yml");

      for (String var3 : var1.getConfigurationSection("Celebrations").getKeys(false)) {
         String var4 = "Celebrations." + var3 + ".";
         if (var1.getBoolean(var4 + "enabled")) {
            try {
               ItemStack var5 = Utils.getItemStack(var1.getString(var4 + "item"), false, false);
               String var6 = var1.getString(var4 + "permission");
               Rarity var7 = Rarity.valueOf(var1.getString(var4 + "rarity").toUpperCase());
               int var8 = var1.getInt(var4 + "value");
               Object var9;
               switch (var3) {
                  case "Party-Spitter":
                     var9 = new PartySpitter();
                     break;
                  case "Ghast":
                     var9 = new Ghast();
                     break;
                  case "Zeus":
                     var9 = new Zeus();
                     break;
                  case "Rocket":
                     var9 = new Rocket();
                     break;
                  default:
                     var9 = new FallingAnvils();
               }

               this.registerCelebration((Celebration)var9, var5, var6, var7, var8);
            } catch (Exception var12) {
               Bukkit.getConsoleSender()
                  .sendMessage(
                     Customization.getInstance().prefix + ChatColor.RED + "Could not load celebration " + var3 + ", please check file for incorrect input"
                  );
               var12.printStackTrace();
            }
         }
      }

      this.updateShopMenu();
   }

   public static CelebrationsManager getInstance() {
      return instance;
   }

   public void registerCelebration(Celebration var1, ItemStack var2, String var3, Rarity var4, int var5) {
      var1.name = var1.getName();
      var1.permission = var3.equalsIgnoreCase("none") ? "" : var3;
      var1.rarity = var4;
      var1.value = var5;
      Customization var6 = Customization.getInstance();
      ItemStackBuilder var7 = new ItemStackBuilder(var2).setName(var4.getColor() + var1.getName());
      var7.addLore(
         new String[]{
            " ",
            ((String)var6.lores.get("Rarity")).replace("%rarity%", var4.toString()),
            ((String)var6.lores.get("Value")).replace("%price%", String.valueOf(var5))
         }
      );
      if (!var1.permission.isEmpty()) {
         var7.addLore(new String[]{" ", (String)var6.lores.get("Permission-Needed")});
      }

      var7.addLore(new String[]{" ", ((String)var6.lores.get("Selling")).replace("%coins%", String.valueOf((int)(Config.getInstance().selling_value * var5)))});
      var1.item = var7.build();
      BattleRoyaleX.getInstance().celebrations.put(var1.getName().toLowerCase(), var1);
   }

   public void updateShopMenu() {
      this.shop = new SmartInventory((String)Customization.getInstance().inventories.get("Celebration-Options"));

      for (int var1 = 0; var1 < Math.ceil((double)BattleRoyaleX.getInstance().celebrations.size() / SmartInventory.slots.length); var1++) {
         if (var1 >= this.shop.getSize()) {
            this.shop.addInventory(ChatColor.RED + "List #" + (var1 + 1));
            this.shop.setItem(var1, 49, (ItemStack)Customization.getInstance().items.get("Back"));
         }

         Iterator var2 = BattleRoyaleX.getInstance().celebrations.values().iterator();

         for (int var6 : SmartInventory.slots) {
            if (!var2.hasNext()) {
               break;
            }

            Celebration var7 = (Celebration)var2.next();
            this.shop.setItem(var1, var6, var7.item);
         }
      }
   }

   public SmartInventory getShop() {
      return this.shop;
   }

   public void startCelebration(String var1, Player var2, Arena var3) {
      ((Celebration)BattleRoyaleX.getInstance().celebrations.get(var1)).start(var2, var3);
   }
}
