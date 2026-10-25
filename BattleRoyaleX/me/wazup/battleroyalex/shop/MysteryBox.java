package me.wazup.battleroyalex.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class MysteryBox {
   private static MysteryBox instance;
   public Material type;
   int cost;
   HashMap<String, Integer> contents;
   HashMap<String, Integer> rarities;
   public Inventory inventory;
   ArrayList<ItemStack> allItems = new ArrayList();
   List<Integer> slots;
   List<Integer> backgroundSlots;
   int size = 45;
   int seconds = 60;
   int slowDownAt = 40;
   public boolean ready;

   public MysteryBox() {
      instance = this;
      FileConfiguration var1 = BattleRoyaleX.getInstance().getConfig();
      this.type = Material.valueOf(var1.getString("Mystery-Box.Block-Type").toUpperCase());
      this.cost = var1.getInt("Mystery-Box.Cost");
      this.contents = new HashMap();

      for (String var3 : var1.getStringList("Mystery-Box.Contents")) {
         this.contents.put(var3.split(" : ")[0], Integer.valueOf(var3.split(" : ")[1].replace("%", "")));
      }

      this.rarities = new HashMap();

      for (String var9 : var1.getConfigurationSection("Mystery-Box.Rarities").getKeys(false)) {
         this.rarities.put(var9, Integer.parseInt(var1.getString("Mystery-Box.Rarities." + var9).replace("%", "")));
      }

      ItemStackBuilder var8 = new ItemStackBuilder(this.type).setName(ChatColor.BLUE + "Mystery Box");
      var8.addLore(new String[]{ChatColor.YELLOW + "Cost: " + ChatColor.LIGHT_PURPLE + this.cost});
      var8.addLore(new String[]{" ", ChatColor.YELLOW + "Content: "});

      for (String var4 : this.contents.keySet()) {
         var8.addLore(new String[]{"- " + ChatColor.YELLOW + var4 + " " + ChatColor.LIGHT_PURPLE + this.contents.get(var4) + "%"});
         if (!var4.equalsIgnoreCase("kits") && !var4.equalsIgnoreCase("celebrations")) {
         }
      }

      var8.addLore(new String[]{" ", ChatColor.YELLOW + "Rarities: "});

      for (String var14 : this.rarities.keySet()) {
         var8.addLore(
            new String[]{"- " + ChatColor.YELLOW + Rarity.valueOf(var14.toUpperCase()) + " " + ChatColor.LIGHT_PURPLE + this.rarities.get(var14) + "%"}
         );
      }

      this.slots = Arrays.asList(new Integer[]{4, 13, 22, 31, 40});
      this.backgroundSlots = new ArrayList();

      for (int var12 = 0; var12 < this.size; var12++) {
         if (!this.slots.contains(var12) && var12 != 21 && var12 != 23) {
            this.backgroundSlots.add(var12);
         }
      }

      for (String var15 : this.contents.keySet()) {
         if (var15.equalsIgnoreCase("kits")) {
            for (Kit var17 : BattleRoyaleX.getInstance().kits.values()) {
               this.allItems.add(var17.item);
            }
         } else if (var15.equalsIgnoreCase("celebrations")) {
            for (Celebration var6 : BattleRoyaleX.getInstance().celebrations.values()) {
               this.allItems.add(var6.item);
            }
         }
      }

      this.inventory = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Mystery Box:");
      this.inventory.setItem(2, (ItemStack)Customization.getInstance().items.get("Confirm"));
      this.inventory.setItem(4, var8.build());
      this.inventory.setItem(6, (ItemStack)Customization.getInstance().items.get("Cancel"));
      this.ready = true;
   }

   public static MysteryBox getInstance() {
      return instance;
   }

   public void open(final Player var1) {
      final PlayerData var2 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName());
      if (!var2.hasCooldown(var1, "BOX_OPEN", this.seconds / 10)) {
         if (var2.getCoins(var1) < this.cost) {
            var1.sendMessage((String)Customization.getInstance().messages.get("Not-Enough-Coins"));
            var1.closeInventory();
         } else if (var2.ownedKits.size() == 54) {
            var1.sendMessage((String)Customization.getInstance().messages.get("Not-Enough-Space"));
         } else {
            ItemStack var3 = null;
            HashMap var4 = (HashMap)this.contents.clone();

            while (var3 == null && !var4.isEmpty()) {
               String var5 = Utils.randomizeList(var4);
               HashMap var6 = (HashMap)this.rarities.clone();

               while (var3 == null && !var6.isEmpty()) {
                  ArrayList var7 = new ArrayList();
                  if (var5.equalsIgnoreCase("kits")) {
                     var7.addAll(BattleRoyaleX.getInstance().kits.values());
                  } else if (var5.equalsIgnoreCase("celebrations")) {
                     var7.addAll(BattleRoyaleX.getInstance().celebrations.values());
                  }

                  String var8 = Utils.randomizeList(var6);
                  Rarity var9 = Rarity.valueOf(var8.toUpperCase());
                  ArrayList var10 = new ArrayList();

                  for (Sellable var12 : var7) {
                     if (var12.rarity != var9 || !var12.permission.isEmpty() && !var1.hasPermission(var12.permission)) {
                        var10.add(var12);
                     }
                  }

                  var7.removeAll(var10);
                  if (!var7.isEmpty()) {
                     var3 = ((Sellable)var7.get(Utils.random.nextInt(var7.size()))).item;
                  } else {
                     var6.remove(var8);
                     if (var6.isEmpty()) {
                        var4.remove(var5);
                     }
                  }
               }
            }

            if (var3 == null) {
               var1.sendMessage((String)Customization.getInstance().messages.get("No-Available-Items"));
               var1.closeInventory();
            } else {
               final ItemStack var13 = var3;
               final Inventory var14 = Bukkit.createInventory(var1, this.size, ChatColor.BLUE + "Unlocking item");
               ItemStack var15 = ((ItemStack)Customization.getInstance().items.get("Pane")).clone();

               for (int var16 = 0; var16 < this.size; var16++) {
                  var14.setItem(var16, var15);
               }

               ItemStack var17 = new ItemStackBuilder(XMaterial.BLACK_STAINED_GLASS.parseItem()).setName(" ").build();
               var14.setItem(21, var17);
               var14.setItem(23, var17);
               var2.removeCoins(var1, this.cost);
               if (var2.lobbyScoreboard != null) {
                  var2.lobbyScoreboard.update("%coins%", var2.getCoins(var1));
               }

               var1.openInventory(var14);
               (new BukkitRunnable() {
                     int runs = 0;
                     boolean run = true;

                     public void run() {
                        if (this.runs >= MysteryBox.this.slowDownAt) {
                           this.run = !this.run;
                        }

                        if (this.run) {
                           for (int var2x : MysteryBox.this.backgroundSlots) {
                              var14.getItem(var2x).setDurability((short)(Utils.random.nextInt(6) + 1));
                           }

                           for (int var3x = MysteryBox.this.slots.size() - 1; var3x > 0; var3x--) {
                              var14.setItem((Integer)MysteryBox.this.slots.get(var3x), var14.getItem((Integer)MysteryBox.this.slots.get(var3x - 1)));
                           }

                           var14.setItem(
                              (Integer)MysteryBox.this.slots.get(0),
                              (ItemStack)MysteryBox.this.allItems.get(Utils.random.nextInt(MysteryBox.this.allItems.size()))
                           );
                           var1.playSound(var1.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
                        }

                        this.runs++;
                        if (this.runs == MysteryBox.this.seconds) {
                           this.cancel();
                           var14.setItem(22, var13);
                           Utils.fireWorkEffect(var1.getLocation(), true);
                           String var4x = ChatColor.stripColor(var13.getItemMeta().getDisplayName()).toLowerCase();
                           if (BattleRoyaleX.getInstance().kits.containsKey(var4x)) {
                              var2.ownedKits.add(var4x);
                           } else {
                              var2.ownedCelebrations.add(var4x);
                           }

                           var1.sendMessage(
                              ((String)Customization.getInstance().messages.get("Mystery-Box-Item-Unlock"))
                                 .replace("%item%", var13.getItemMeta().getDisplayName())
                           );
                        }
                     }
                  })
                  .runTaskTimer(BattleRoyaleX.getInstance(), 0L, 2L);
            }
         }
      }
   }
}
