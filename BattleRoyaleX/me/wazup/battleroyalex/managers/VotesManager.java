package me.wazup.battleroyalex.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VotesManager {
   private HashMap<Integer, VotesManager.Entry> entries;
   public LinkedHashMap<String, Integer> timeOptions;
   public LinkedHashMap<String, Integer> healthOptions;

   public VotesManager() {
      FileConfiguration var1 = BattleRoyaleX.getInstance().getConfig();
      this.timeOptions = new LinkedHashMap();
      if (var1.getBoolean("Voting-Options.Time.enabled")) {
         for (String var3 : var1.getStringList("Voting-Options.Time.options")) {
            String[] var4 = var3.split(" : ");
            this.timeOptions.put(Utils.colorize(var4[0]), Integer.valueOf(var4[1]));
         }
      }

      this.healthOptions = new LinkedHashMap();
      if (var1.getBoolean("Voting-Options.Health.enabled")) {
         for (String var6 : var1.getStringList("Voting-Options.Health.options")) {
            String[] var7 = var6.split(" : ");
            this.healthOptions.put(Utils.colorize(var7[0]), Integer.parseInt(var7[1]) * 2);
         }
      }

      this.entries = new HashMap();
      this.entries.put(2, new VotesManager.Entry(XMaterial.CLOCK.parseItem(), new ArrayList(this.timeOptions.keySet())));
      this.entries.put(4, new VotesManager.Entry(new ItemStack(Material.CHEST), null));
      this.entries.put(6, new VotesManager.Entry(new ItemStack(Material.APPLE), new ArrayList(this.healthOptions.keySet())));
   }

   public void vote(Player var1, int var2, String var3) {
      ((VotesManager.Entry)this.entries.get(var2)).voters.put(var1.getName(), var3);
   }

   public void removeVotes(Player var1) {
      for (VotesManager.Entry var3 : this.entries.values()) {
         if (var3.voters.containsKey(var1.getName())) {
            var3.voters.remove(var1.getName());
         }
      }
   }

   public void openEntry(Player var1, int var2) {
      var1.openInventory(((VotesManager.Entry)this.entries.get(var2)).inventory);
   }

   public int getVotes(int var1, String var2) {
      int var3 = 0;
      HashMap<String, String> var4 = ((VotesManager.Entry)this.entries.get(var1)).voters;

      for (String var6 : var4.keySet()) {
         if (((String)var4.get(var6)).equals(var2)) {
            var3++;
         }
      }

      return var3;
   }

   public List<String> getAllOptions(int var1) {
      ArrayList<String> var2 = new ArrayList<>();

      for (String var4 : ((VotesManager.Entry)this.entries.get(var1)).voters.values()) {
         if (!var2.contains(var4)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public String getVoted(int var1) {
      VotesManager.Entry var2 = (VotesManager.Entry)this.entries.get(var1);
      String var3 = "Default";
      int var4 = 0;

      for (String var6 : this.getAllOptions(var1)) {
         int var7 = this.getVotes(var1, var6);
         if (var7 > var4) {
            var3 = var6;
            var4 = var7;
         }
      }

      return var3;
   }

   public void reset() {
      for (VotesManager.Entry var2 : this.entries.values()) {
         var2.voters.clear();
      }
   }

   private class Entry {
      Inventory inventory;
      HashMap<String, String> voters = new HashMap();

      private Entry(ItemStack var2, List<String> var3) {
         if (var3 != null) {
            this.inventory = Bukkit.createInventory(
               null, Utils.getInventorySize(var3.size() + 9), (String)Customization.getInstance().inventories.get("Arena-Settings")
            );
            Utils.cageInventory(this.inventory, true);

            for (int var4 = 0; var4 < var3.size(); var4++) {
               this.inventory.setItem(var4, new ItemStackBuilder(var2).setName((String)var3.get(var4)).build());
            }

            this.inventory.setItem(this.inventory.getSize() - 5, (ItemStack)Customization.getInstance().items.get("Back"));
         }
      }
   }
}
