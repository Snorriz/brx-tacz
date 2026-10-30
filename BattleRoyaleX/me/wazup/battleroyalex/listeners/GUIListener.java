package me.wazup.battleroyalex.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.Party;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.Arena.CustomTeam;
import me.wazup.battleroyalex.Arena.PAData;
import me.wazup.battleroyalex.managers.CelebrationsManager;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.LoadoutsManager;
import me.wazup.battleroyalex.managers.RecipeManager;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.managers.RecipeManager.RecipeData;
import me.wazup.battleroyalex.scenarios.ScenarioManager;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.shop.Kit;
import me.wazup.battleroyalex.shop.MysteryBox;
import me.wazup.battleroyalex.shop.Sellable;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Loadout;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import me.wazup.battleroyalex.utils.Enums.LoadoutCategory;
import me.wazup.battleroyalex.utils.Enums.PartyPrivacy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIListener implements Listener {
   @EventHandler
   public void onInventoryClick(InventoryClickEvent var1) {
      Player var2 = (Player)var1.getWhoClicked();
      Inventory var3 = var1.getInventory();
      String var4 = var1.getView().getTitle();
      ItemStack var5 = var1.getCurrentItem();
      int var6 = var1.getRawSlot();
      if (var4.contains(ChatColor.DARK_RED + "BB-Editing: ")) {
         var1.setCancelled(true);
         if (var6 >= 0
            && var6 <= 53
            && var5 != null
            && !var5.getType().equals(Material.AIR)
            && !Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Pane"))) {
            Arena var21 = (Arena)BattleRoyaleX.getInstance().arenas.get(ChatColor.stripColor(var4.split(": ")[1].toLowerCase()));
            if (var21 == null) {
               var2.closeInventory();
            } else if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Plus"))
               || Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Minus"))) {
               boolean var44 = Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Plus"));
               ItemStack var61 = var3.getItem(var44 ? var1.getSlot() + 9 : var1.getSlot() - 9);
               String var71 = ChatColor.stripColor(var61.getItemMeta().getDisplayName()).split(": ")[0];
               int var76 = var1.getAction().equals(InventoryAction.PICKUP_HALF) ? 20 : 1;
               int var82 = Integer.valueOf(ChatColor.stripColor(var61.getItemMeta().getDisplayName()).split(": ")[1]) + (var44 ? var76 : -var76);
               if (var82 >= 1) {
                  new ItemStackBuilder(var61).setName(ChatColor.YELLOW + var71 + ": " + ChatColor.GOLD + var82).build();
               }
            } else if (!XMaterial.LIME_DYE.isSimilar(var5) && !XMaterial.GRAY_DYE.isSimilar(var5)) {
               if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Save"))) {
                  long var43 = System.currentTimeMillis();
                  HashMap var70 = new HashMap();
                  byte var75 = 7;

                  for (int var80 = 9; var80 < 9 + var75; var80++) {
                     var70.put(
                        ChatColor.stripColor(var3.getItem(var80).getItemMeta().getDisplayName()).split(": ")[0].toLowerCase().replace(" ", "-"),
                        Integer.valueOf(ChatColor.stripColor(var3.getItem(var80).getItemMeta().getDisplayName()).split(": ")[1])
                     );
                  }

                  if ((Integer)var70.get("min-teams") < 2) {
                     var2.sendMessage(Customization.getInstance().prefix + "Min players cannot be less than 2!");
                     return;
                  }

                  if ((Integer)var70.get("max-teams") < 2) {
                     var2.sendMessage(Customization.getInstance().prefix + "Max players cannot be less than 2!");
                     return;
                  }

                  if ((Integer)var70.get("team-size") < 1) {
                     var2.sendMessage(Customization.getInstance().prefix + "Team size cannot be less than 1!");
                     return;
                  }

                  File var81 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/arenas/" + var21.name, "settings.yml");
                  YamlConfiguration var84 = YamlConfiguration.loadConfiguration(var81);
                  int var14 = var84.getInt("world-border.total-number-of-shrinks");
                  boolean var15 = ChatColor.stripColor(var3.getItem(40).getItemMeta().getDisplayName().toLowerCase()).equals("enabled");
                  var21.enabled = var15;
                  var21.minTeams = (Integer)var70.get("min-teams");
                  var21.maxTeams = (Integer)var70.get("max-teams");
                  var21.teamSize = (Integer)var70.get("team-size");
                  var21.minPlayers = var21.minTeams * var21.teamSize;
                  var21.maxPlayers = var21.maxTeams * var21.teamSize;
                  var21.lobbyCountdown = (Integer)var70.get("lobby-countdown");
                  var21.gameLengthInSeconds = (Integer)var70.get("game-length-in-minutes") * 60;
                  var21.worldBorderInitialSize = (Integer)var70.get("world-border.initial-size");
                  var21.worldBorderFinalSize = (Integer)var70.get("world-border.final-size");
                  var21.eachBorderShrinkageAmount = (var21.worldBorderInitialSize - var21.worldBorderFinalSize) / var14;
                  var21.createBorder(Bukkit.getWorld(var21.worldName), var21.mapCenterX, var21.mapCenterZ, var21.worldBorderInitialSize);

                  for (Object var17x : var70.keySet()) {
                     String var17 = (String)var17x;
                     var84.set(var17, var70.get(var17));
                  }

                  var84.set("enabled", var15);

                  try {
                     var84.save(var81);
                  } catch (IOException var18) {
                     var18.printStackTrace();
                  }

                  var21.stop();
                  var2.sendMessage(
                     Customization.getInstance().prefix
                        + "Your settings have been saved & applied! took "
                        + ChatColor.LIGHT_PURPLE
                        + (System.currentTimeMillis() - var43)
                        + "ms!"
                  );
                  var2.closeInventory();
               }
            } else {
               boolean var42 = !ChatColor.stripColor(var3.getItem(40).getItemMeta().getDisplayName().toLowerCase()).equals("enabled");
               var3.setItem(
                  40,
                  new ItemStackBuilder(((XMaterial)XMaterial.matchXMaterial("INK_SACK:" + (var42 ? 10 : 8)).get()).parseItem())
                     .setName(var42 ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled")
                     .setDurability(var42 ? 10 : 8)
                     .build()
               );
            }
         }
      } else {
         if (BattleRoyaleX.getInstance().players.contains(var2.getName())) {
            if (var4.equals(Customization.getInstance().inventories.get("Recipe"))) {
               var1.setCancelled(true);
               if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                  RecipeManager.recipesInventory.open(var2);
                  return;
               }

               return;
            }

            if (var4.startsWith((String)Customization.getInstance().inventories.get("Recipes"))) {
               var1.setCancelled(true);
               if (var6 >= 0
                  && var6 <= var1.getInventory().getSize() - 1
                  && var5 != null
                  && !var5.getType().equals(Material.AIR)
                  && !Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Pane"))) {
                  if (RecipeManager.recipesInventory.handleClick(var2, var5, var3)) {
                     return;
                  }

                  var2.openInventory(((RecipeData)RecipeManager.customRecipes.get(var5)).inventory);
                  return;
               }

               return;
            }

            if (BattleRoyaleX.getInstance().protectedPlayers.contains(var2.getName())) {
               var1.setCancelled(true);
               if (var6 >= 0
                  && var6 <= var1.getInventory().getSize() - 1
                  && var5 != null
                  && !var5.getType().equals(Material.AIR)
                  && !Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Pane"))) {
                  if (var4.equals(Customization.getInstance().inventories.get("Quit-Inventory"))) {
                     if (var6 == 2) {
                        BattleRoyaleX.getInstance().leave(var2, false);
                     } else if (var6 == 6) {
                        var2.closeInventory();
                     }

                     return;
                  }

                  PlayerData var20 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
                  if (var4.equals(Customization.getInstance().inventories.get("Profile-Inventory"))) {
                     if (var20.hasCooldown(var2, "PROFILE_CLICK", 1)) {
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Stats"))) {
                        var2.openInventory(var20.getStatsInventory(var2));
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Kits"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().kitOptionsInventory);
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Loadouts"))) {
                        var2.openInventory(var20.getLoadoutsInventory());
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Achievements"))) {
                        var20.achievements.open(var2);
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Celebrations"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().celebrationOptionsInventory);
                        return;
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Kit-Options"))) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().profileInventory);
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Owned-Kits"))) {
                        var2.openInventory(var20.getKitsInventory());
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Shop"))) {
                        BattleRoyaleX.getInstance().kitsShop.open(var2);
                        return;
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Owned-Kits"))) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().kitOptionsInventory);
                        return;
                     }

                     String var41 = ChatColor.stripColor(var5.getItemMeta().getDisplayName().toLowerCase());
                     if (var1.getAction().equals(InventoryAction.PICKUP_HALF)) {
                        if (var41.equals(Config.getInstance().defaultKit)) {
                           return;
                        }

                        Inventory var59 = Bukkit.createInventory(var2, 9, (String)Customization.getInstance().inventories.get("Confirm-Sell"));
                        var59.setItem(2, (ItemStack)Customization.getInstance().items.get("Confirm"));
                        var59.setItem(4, var5);
                        var59.setItem(6, (ItemStack)Customization.getInstance().items.get("Cancel"));
                        var2.openInventory(var59);
                     } else {
                        if (var20.hasCooldown(var2, "ITEM_EQUIP", 1)) {
                           return;
                        }

                        var20.selectedKit = var41;
                        Kit var60 = (Kit)BattleRoyaleX.getInstance().kits.get(var41);
                        if (var20.lobbyScoreboard != null) {
                           var20.lobbyScoreboard.update("%selected_kit%", var60.name);
                        }

                        var2.sendMessage(((String)Customization.getInstance().messages.get("Item-Select")).replace("%item%", var60.name));
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Celebration-Options"))) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().profileInventory);
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Owned-Celebrations"))) {
                        var2.openInventory(var20.getCelebrationsInventory());
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Shop"))) {
                        CelebrationsManager.getInstance().getShop().open(var2);
                        return;
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Owned-Celebrations"))) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().celebrationOptionsInventory);
                        return;
                     }

                     String var40 = ChatColor.stripColor(var5.getItemMeta().getDisplayName().toLowerCase());
                     if (var1.getAction().equals(InventoryAction.PICKUP_HALF)) {
                        Inventory var57 = Bukkit.createInventory(var2, 9, (String)Customization.getInstance().inventories.get("Confirm-Sell"));
                        var57.setItem(2, (ItemStack)Customization.getInstance().items.get("Confirm"));
                        var57.setItem(4, var5);
                        var57.setItem(6, (ItemStack)Customization.getInstance().items.get("Cancel"));
                        var2.openInventory(var57);
                     } else {
                        if (var20.hasCooldown(var2, "ITEM_EQUIP", 1)) {
                           return;
                        }

                        var20.selectedCelebration = var40;
                        Celebration var58 = (Celebration)BattleRoyaleX.getInstance().celebrations.get(var40);
                        if (var20.lobbyScoreboard != null) {
                           var20.lobbyScoreboard.update("%selected_celebration%", var58.name);
                        }

                        var2.sendMessage(((String)Customization.getInstance().messages.get("Item-Select")).replace("%item%", var58.name));
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Confirm-Sell"))) {
                     String var39 = ChatColor.stripColor(var3.getItem(4).getItemMeta().getDisplayName().toLowerCase());
                     Sellable var56 = BattleRoyaleX.getInstance().kits.containsKey(var39)
                        ? (Sellable)BattleRoyaleX.getInstance().kits.get(var39)
                        : (Sellable)BattleRoyaleX.getInstance().celebrations.get(var39);
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Cancel"))) {
                        if (var56 instanceof Kit) {
                           var2.openInventory(var20.getKitsInventory());
                        } else {
                           var2.openInventory(var20.getCelebrationsInventory());
                        }

                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Confirm"))) {
                        if (var56 instanceof Kit) {
                           var20.ownedKits.remove(var39);
                           if (var20.selectedKit.equals(var39)) {
                              var20.selectedKit = "";
                           }

                           var2.openInventory(var20.getKitsInventory());
                        } else {
                           var20.ownedCelebrations.remove(var39);
                           if (var20.selectedCelebration.equals(var39)) {
                              var20.selectedCelebration = "";
                           }

                           var2.openInventory(var20.getCelebrationsInventory());
                        }

                        int var69 = (int)(var56.value * Config.getInstance().selling_value);
                        var20.addCoins(var2, var69);
                        if (var20.lobbyScoreboard != null) {
                           var20.createScoreboard(var2);
                        }

                        var2.sendMessage(((String)Customization.getInstance().messages.get("Item-Sell")).replace("%value%", String.valueOf(var69)));
                        return;
                     }

                     return;
                  }

                  if (var4.contains(BattleRoyaleX.getInstance().kitsShop.getName())) {
                     if (BattleRoyaleX.getInstance().kitsShop.handleClick(var2, var5, var3)) {
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().kitOptionsInventory);
                        return;
                     }

                     Inventory var38 = Bukkit.createInventory(var2, 9, (String)Customization.getInstance().inventories.get("Confirm-Purchase"));
                     var38.setItem(1, (ItemStack)Customization.getInstance().items.get("Confirm"));
                     var38.setItem(4, var5);
                     var38.setItem(7, (ItemStack)Customization.getInstance().items.get("Cancel"));
                     var2.openInventory(var38);
                     return;
                  }

                  if (var4.contains(CelebrationsManager.getInstance().getShop().getName())) {
                     if (CelebrationsManager.getInstance().getShop().handleClick(var2, var5, var3)) {
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().celebrationOptionsInventory);
                        return;
                     }

                     Inventory var37 = Bukkit.createInventory(var2, 9, (String)Customization.getInstance().inventories.get("Confirm-Purchase"));
                     var37.setItem(1, (ItemStack)Customization.getInstance().items.get("Confirm"));
                     var37.setItem(4, var5);
                     var37.setItem(7, (ItemStack)Customization.getInstance().items.get("Cancel"));
                     var2.openInventory(var37);
                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Confirm-Purchase"))) {
                     ItemStack var36 = var3.getItem(4);
                     String var55 = ChatColor.stripColor(var36.getItemMeta().getDisplayName().toLowerCase());
                     Sellable var68 = BattleRoyaleX.getInstance().kits.containsKey(var55)
                        ? (Sellable)BattleRoyaleX.getInstance().kits.get(var55)
                        : (Sellable)BattleRoyaleX.getInstance().celebrations.get(var55);
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Confirm"))) {
                        int var74 = var68.value;
                        String var79 = var68.permission;
                        if (!var79.isEmpty() && !var2.hasPermission(var79)) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("No-Permission"));
                           return;
                        }

                        if (var20.getCoins(var2) < var74) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Not-Enough-Coins"));
                           var2.closeInventory();
                           return;
                        }

                        ArrayList var83 = var68 instanceof Kit ? var20.ownedKits : var20.ownedCelebrations;
                        if (var83.contains(var55)) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Item-Owned"));
                           var2.closeInventory();
                           return;
                        }

                        if (var83.size() == 54) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Not-Enough-Space"));
                           var2.closeInventory();
                           return;
                        }

                        var20.removeCoins(var2, var74);
                        if (var20.lobbyScoreboard != null) {
                           var20.lobbyScoreboard.update("%coins%", var20.getCoins(var2));
                        }

                        if (var68 instanceof Kit) {
                           var20.ownedKits.add(var55);
                           var2.openInventory(var20.getKitsInventory());
                        } else {
                           var20.ownedCelebrations.add(var55);
                           var2.openInventory(var20.getCelebrationsInventory());
                        }

                        var2.sendMessage((String)Customization.getInstance().messages.get("Item-Purchase"));
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Cancel"))) {
                        if (var68 instanceof Kit) {
                           BattleRoyaleX.getInstance().kitsShop.open(var2);
                        } else {
                           CelebrationsManager.getInstance().getShop().open(var2);
                        }
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Loadouts"))) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().profileInventory);
                        return;
                     }

                     boolean var35 = var1.getAction().equals(InventoryAction.PICKUP_HALF);
                     if (var35) {
                        var2.openInventory(((Loadout)var20.loadouts.get(var6)).inventory);
                        var20.loadoutEditing = var6;
                     } else {
                        var20.selectedLoadout = var6;
                        var2.openInventory(var20.getLoadoutsInventory());
                     }

                     return;
                  }

                  if (var4.startsWith(ChatColor.BLUE + "Loadout ")) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(var20.getLoadoutsInventory());
                        return;
                     }

                     Inventory var34 = LoadoutsManager.getInstance().getInventory(var6);
                     if (var34 != null) {
                        var2.openInventory(var34);
                     }

                     return;
                  }

                  if (var4.startsWith(ChatColor.BLUE + "Loadout: ")) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(((Loadout)var20.loadouts.get(var20.loadoutEditing)).inventory);
                        return;
                     }

                     Loadout var33 = (Loadout)var20.loadouts.get(var20.loadoutEditing);
                     String var54 = ChatColor.stripColor(var4).split(": ")[1].replace(" ", "_");
                     LoadoutCategory var67 = LoadoutCategory.valueOf(var54);
                     if (var33.equipItem(var67, var5)) {
                        var2.openInventory(var33.inventory);
                        var2.playSound(var2.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
                     } else {
                        var2.playSound(var2.getLocation(), SoundsManager.LAVA_POP, 1.0F, 1.0F);
                     }

                     return;
                  }

                  if (var4.contains(var20.achievements.getName())) {
                     if (var20.achievements.handleClick(var2, var5, var3)) {
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().profileInventory);
                        return;
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Stats-Inventory"))) {
                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                        var2.openInventory(BattleRoyaleX.getInstance().profileInventory);
                        return;
                     }

                     return;
                  }

                  if (var4.contains(BattleRoyaleX.getInstance().arenaSelector.getName())) {
                     if (BattleRoyaleX.getInstance().arenaSelector.handleClick(var2, var5, var3)) {
                        return;
                     }

                     if (var20.hasCooldown(var2, "ARENA_SELECT", 2)) {
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Autojoin"))) {
                        BattleRoyaleX.getInstance().autojoin(var2, null);
                        return;
                     }

                     String var32 = ChatColor.stripColor(var5.getItemMeta().getDisplayName().toLowerCase());
                     if (BattleRoyaleX.getInstance().arenas.containsKey(var32)) {
                        ((Arena)BattleRoyaleX.getInstance().arenas.get(var32)).join(var2);
                     } else {
                        var2.sendMessage((String)Customization.getInstance().messages.get("Invalid-Arena"));
                        var2.closeInventory();
                     }

                     return;
                  }

                  if (var4.equals(ChatColor.BLUE + "Mystery Box:")) {
                     if (var20.arena != null && !var20.arena.state.AVAILABLE()) {
                        var2.closeInventory();
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Cancel"))) {
                        var2.closeInventory();
                        return;
                     }

                     if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Confirm"))) {
                        MysteryBox.getInstance().open(var2);
                        return;
                     }

                     return;
                  }

                  if (BattleRoyaleX.getInstance().lobbyPlayers.contains(var2.getName())) {
                     if (var4.equals(ChatColor.RED + "Select your option!")) {
                        if (var5.getType().equals(Material.BEACON)) {
                           if (var20.hasCooldown(var2, "PARTY_CREATE", 15)) {
                              return;
                           }

                           int var31 = Config.getInstance().party_default_slots;

                           for (int var66 : Config.getInstance().party_custom_slots.keySet()) {
                              if (var66 > var31 && var2.hasPermission((String)Config.getInstance().party_custom_slots.get(var66))) {
                                 var31 = var66;
                              }
                           }

                           // TaxBattle: sistema party rimosso
                           var2.sendMessage(org.bukkit.ChatColor.RED + "Il sistema dei party è stato rimosso da TaxBattle.");
                           return;
                        }

                        if (var5.getType().equals(Material.CHEST)) {
                           // TaxBattle: sistema party rimosso
                           var2.sendMessage(org.bukkit.ChatColor.RED + "Il sistema dei party è stato rimosso da TaxBattle.");
                           return;
                        }

                        return;
                     }

                     if (var4.equals(Customization.getInstance().inventories.get("Party-Settings"))) {
                        if (var20.hasCooldown(var2, "PARTY_MODIFY", 2)) {
                           return;
                        }

                        if (XMaterial.ENDER_EYE.isSimilar(var5)) {
                           if (var20.party.leaderName.equals(var2.getName())) {
                              var20.party.setPrivacy(var20.party.privacy == PartyPrivacy.INVITE ? PartyPrivacy.PUBLIC : PartyPrivacy.INVITE);
                              var20.party.updateItem();
                           } else {
                              var2.sendMessage((String)Customization.getInstance().messages.get("Party-Must-Be-Leader"));
                           }

                           return;
                        }

                        if (var5.getType().equals(Material.CHEST)) {
                           var2.openInventory(var20.party.playersInventory);
                           return;
                        }

                        if (var5.getType().equals(Material.ENDER_CHEST)) {
                           if (var20.party.leaderName.equals(var2.getName())) {
                              BattleRoyaleX.getInstance().playerInviter.open(var2);
                           } else {
                              var2.sendMessage((String)Customization.getInstance().messages.get("Party-Must-Be-Leader"));
                           }

                           return;
                        }

                        if (var5.getType().equals(Material.TNT)) {
                           var20.party.leave(var2);
                           return;
                        }

                        return;
                     }

                     if (var4.equals(Customization.getInstance().inventories.get("Party-Players"))) {
                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                           var2.openInventory(var20.party.gui);
                           return;
                        }

                        if (!var20.party.leaderName.equals(var2.getName())) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Party-Must-Be-Leader"));
                           return;
                        }

                        String var30 = ChatColor.stripColor(var5.getItemMeta().getDisplayName());
                        if (var30.equals(var2.getName())) {
                           return;
                        }

                        Player var52 = Bukkit.getPlayer(var30);
                        if (var52 == null) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Unknown-Player"));
                           return;
                        }

                        if (var1.getAction().equals(InventoryAction.PICKUP_HALF)) {
                           if (var20.party.players.contains(var30)) {
                              var20.party.kick(var52);
                           } else {
                              var2.sendMessage((String)Customization.getInstance().messages.get("Party-Unknown-Player"));
                           }
                        } else {
                           var20.party.setLeader(var52);
                           var20.party.sendMessage(((String)Customization.getInstance().messages.get("Party-New-Leader")).replace("%player%", var52.getName()));
                           var20.party.updateItem();
                           var20.party.updatePlayers();
                        }

                        return;
                     }

                     if (var4.contains(BattleRoyaleX.getInstance().partySelector.getName())) {
                        if (BattleRoyaleX.getInstance().partySelector.handleClick(var2, var5, var3)) {
                           return;
                        }

                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                           var2.openInventory(BattleRoyaleX.getInstance().partyMenu);
                           return;
                        }

                        if (var20.hasCooldown(var2, "PARTY_JOIN", 2)) {
                           return;
                        }

                        String var29 = ChatColor.stripColor(var5.getItemMeta().getDisplayName().replace("'s party", ""));

                        for (Party var65 : BattleRoyaleX.getInstance().parties) {
                           if (var65.leaderName.equals(var29)) {
                              var65.join(var2);
                              return;
                           }
                        }

                        var2.closeInventory();
                        var2.sendMessage((String)Customization.getInstance().messages.get("Invalid-Party"));
                        return;
                     }

                     if (var4.contains(BattleRoyaleX.getInstance().playerInviter.getName())) {
                        if (BattleRoyaleX.getInstance().playerInviter.handleClick(var2, var5, var3)) {
                           return;
                        }

                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                           var2.openInventory(var20.party.gui);
                           return;
                        }

                        if (var20.hasCooldown(var2, "PARTY_INVITE", 2)) {
                           return;
                        }

                        String var28 = ChatColor.stripColor(var5.getItemMeta().getDisplayName());
                        Player var50 = Bukkit.getPlayer(var28);
                        if (var50 == null) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Unknown-Player"));
                           return;
                        }

                        if (var50.getName().equals(var2.getName())) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Party-Self-Invite"));
                           return;
                        }

                        if (!var20.party.invited.contains(var50.getName()) && !var20.party.players.contains(var50.getName())) {
                           var20.party.invite(var50);
                           return;
                        }

                        var2.sendMessage((String)Customization.getInstance().messages.get("Player-Already-Invited"));
                        return;
                     }

                     return;
                  }

                  if (var4.contains((CharSequence)Customization.getInstance().inventories.get("Arena-Settings"))) {
                     PAData var27 = (PAData)var20.arena.players.get(var2.getName());
                     if (var27.votingCategory != -1) {
                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Back"))) {
                           var27.votingCategory = -1;
                           var2.openInventory(BattleRoyaleX.getInstance().votingInventory);
                           return;
                        }

                        if (var20.hasCooldown(var2, "SETTINGS_VOTE", 3)) {
                           return;
                        }

                        String var49 = var5.getItemMeta().getDisplayName();
                        var20.arena.votesManager.vote(var2, var27.votingCategory, var49);
                        String var64 = ((String)Customization.getInstance().messages.get("Player-Vote"))
                           .replace("%player%", var2.getName())
                           .replace("%option%", var49)
                           .replace("%votes%", String.valueOf(var20.arena.votesManager.getVotes(var27.votingCategory, var49)));

                        for (Player var78 : var20.arena.getPlayers()) {
                           var78.sendMessage(var64);
                        }
                     } else if ((var6 != 2 || var2.hasPermission("battleroyalex.vote.time"))
                        && (var6 != 6 || var2.hasPermission("battleroyalex.vote.health"))
                        && (var6 != 4 || var2.hasPermission("battleroyalex.vote.scenarios"))) {
                        if (var6 == 4) {
                           ScenarioManager.getInstance().showGUI(var2);
                        } else {
                           var20.arena.votesManager.openEntry(var2, var6);
                        }

                        var27.votingCategory = var6;
                     } else {
                        var2.sendMessage((String)Customization.getInstance().messages.get("No-Permission"));
                     }

                     return;
                  }

                  if (var4.startsWith((String)Customization.getInstance().inventories.get("Team-Selector"))) {
                     if (var20.arena != null && var20.arena.state.AVAILABLE()) {
                        if (var20.arena.teamSelector.handleClick(var2, var5, var3)) {
                           return;
                        }

                        if (var20.hasCooldown(var2, "TEAM_SELECT", 3)) {
                           return;
                        }

                        int var26 = var20.arena.teamSelector.getId(var3);

                        for (CustomTeam var63 : var20.arena.aliveTeams) {
                           if (var63.slot == var6 && var63.id == var26 && var63.players.size() < var20.arena.teamSize) {
                              PAData var72 = (PAData)var20.arena.players.get(var2.getName());
                              CustomTeam var77 = var72.team;
                              var77.players.remove(var2.getName());
                              var77.updateItem(var20.arena.teamSelector);
                              var72.team = var63;
                              var63.players.add(var2.getName());
                              var63.updateItem(var20.arena.teamSelector);
                           }
                        }
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Teleporter"))) {
                     if (var20.arena != null && var20.arena.spectators.contains(var2.getName())) {
                        String var25 = ChatColor.stripColor(var5.getItemMeta().getDisplayName());
                        Player var47 = Bukkit.getPlayer(var25);
                        if (var47 == null) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Unknown-Player"));
                           var2.closeInventory();
                           return;
                        }

                        if (!var20.arena.players.containsKey(var47.getName())) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Player-Inactive"));
                           var2.closeInventory();
                           return;
                        }

                        var2.teleport(var47.getLocation().add(0.0, 3.0, 0.0));
                     } else {
                        var2.closeInventory();
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Spectator-Menu"))) {
                     if (var20.arena != null && var20.arena.spectators.contains(var2.getName())) {
                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Arena-Selector"))) {
                           BattleRoyaleX.getInstance().arenaSelector.open(var2);
                           return;
                        }

                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Teleporter"))) {
                           if (var20.hasCooldown(var2, "TELEPORT_GUI", 5)) {
                              return;
                           }

                           var2.openInventory(var20.arena.getPlayersInventory((String)Customization.getInstance().inventories.get("Teleporter")));
                           return;
                        }

                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Profile"))) {
                           var2.openInventory(BattleRoyaleX.getInstance().profileInventory);
                           return;
                        }

                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Quit"))) {
                           var2.openInventory(BattleRoyaleX.getInstance().quitInventory);
                           return;
                        }

                        if (Utils.compareItem(var5, (ItemStack)Customization.getInstance().items.get("Bounties"))) {
                           var2.openInventory(var20.arena.getPlayersInventory((String)Customization.getInstance().inventories.get("Bounty-Target")));
                           return;
                        }
                     } else {
                        var2.closeInventory();
                     }

                     return;
                  }

                  if (var4.equals(Customization.getInstance().inventories.get("Bounty-Target"))) {
                     if (var20.arena != null && var20.arena.spectators.contains(var2.getName())) {
                        String var24 = ChatColor.stripColor(var5.getItemMeta().getDisplayName());
                        Player var46 = Bukkit.getPlayer(var24);
                        if (var46 == null) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Unknown-Player"));
                           var2.closeInventory();
                           return;
                        }

                        if (!var20.arena.players.containsKey(var46.getName()) || var20.arena.spectators.contains(var46.getName())) {
                           var2.sendMessage((String)Customization.getInstance().messages.get("Player-Inactive"));
                           var2.closeInventory();
                           return;
                        }

                        var20.bountyTarget_temp = var24;
                        var2.openInventory(BattleRoyaleX.getInstance().bountiesInventory);
                     } else {
                        var2.closeInventory();
                     }

                     return;
                  }

                  if (!var4.equals(Customization.getInstance().inventories.get("Bounty-Options"))) {
                     return;
                  }

                  if (var20.arena != null && var20.arena.spectators.contains(var2.getName())) {
                     if (!var20.arena.state.equals(ArenaState.INGAME)) {
                        var2.sendMessage((String)Customization.getInstance().messages.get("Bounty-Deny"));
                        var2.closeInventory();
                        return;
                     }

                     Player var23 = Bukkit.getPlayer(var20.bountyTarget_temp);
                     if (var23 == null) {
                        var2.sendMessage((String)Customization.getInstance().messages.get("Unknown-Player"));
                        var2.closeInventory();
                        return;
                     }

                     if (!var20.arena.players.containsKey(var23.getName()) || var20.arena.spectators.contains(var23.getName())) {
                        var2.sendMessage((String)Customization.getInstance().messages.get("Player-Inactive"));
                        var2.closeInventory();
                        return;
                     }

                     int var45 = Integer.parseInt(ChatColor.stripColor(var5.getItemMeta().getDisplayName()));
                     if (var20.getCoins(var2) < var45) {
                        var2.sendMessage((String)Customization.getInstance().messages.get("Not-Enough-Coins"));
                        return;
                     }

                     var20.removeCoins(var2, var45);
                     PlayerData var62 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var23.getName());
                     var62.bounties
                        .put(var2.getName(), var62.bounties.containsKey(var2.getName()) ? (Integer)var62.bounties.get(var2.getName()) + var45 : var45);
                     String var11 = ((String)Customization.getInstance().messages.get("Bounty-Set"))
                        .replace("%target%", var23.getName())
                        .replace("%bounty%", String.valueOf(var45))
                        .replace("%total_bounty%", String.valueOf(var62.getTotalBounty()));

                     for (Player var13 : var20.arena.getPlayers()) {
                        var13.sendMessage(var11);
                        var13.playSound(var13.getLocation(), SoundsManager.NOTE_PLING, 1.0F, 1.0F);
                     }

                     var20.bountyTarget_temp = null;
                     var2.closeInventory();
                  } else {
                     var2.closeInventory();
                  }

                  return;
               }

               return;
            }

            if (var4.equals(Customization.getInstance().inventories.get("Shopping-Station"))) {
               var1.setCancelled(true);
               if (!BattleRoyaleX.getInstance().shoppingStationItems.containsKey(var6)) {
                  return;
               }

               int var19 = (Integer)BattleRoyaleX.getInstance().shoppingStationItems.get(var6);
               PlayerData var22 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
               PAData var9 = (PAData)var22.arena.players.get(var2.getName());
               if (var9.cash >= var19) {
                  var2.playSound(var2.getLocation(), SoundsManager.ITEM_PICKUP, 1.0F, 1.0F);
                  ItemStack var10 = Config.getInstance().clearShoppingStationItemLoreOnPurchase ? new ItemStackBuilder(var5.clone()).clearLore().build() : var5;
                  var2.getInventory().addItem(new ItemStack[]{var10});
                  var9.cash -= var19;
                  var9.updateDataString();
               } else {
                  var2.playSound(var2.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
               }

               return;
            }

            if (var5 != null && var5.getType().equals(Material.GOLD_INGOT)) {
               PlayerData var7 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var2.getName());
               PAData var8 = (PAData)var7.arena.players.get(var2.getName());
               if (BattleRoyaleX.getInstance().claimCash(var2, var8, var5)) {
                  var1.setCancelled(true);
                  var3.remove(var5);
                  return;
               }
            }
         }
      }
   }
}
