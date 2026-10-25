package me.wazup.battleroyalex.listeners;

import java.io.File;
import java.io.IOException;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.FilesManager;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.Stat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

public class SignsListener implements Listener {
   @EventHandler
   public void onSignChange(SignChangeEvent var1) {
      if (var1.getLine(0).equalsIgnoreCase("[brx]")) {
         Player var2 = var1.getPlayer();
         if (!var2.hasPermission("battleroyalex.createsigns")) {
            var2.sendMessage((String)Customization.getInstance().messages.get("No-Permission"));
            return;
         }

         var1.setLine(0, Customization.getInstance().signs_title);
         String var3 = var1.getLine(1).toLowerCase();
         if (var3.equals("join")) {
            var1.setLine(1, Customization.getInstance().signs_join);
            String var12 = var1.getLine(2);
            if (var12.isEmpty()) {
               FileConfiguration var13 = FilesManager.getInstance().getConfig("signs.yml");
               Location var18 = var1.getBlock().getLocation();
               int var22 = 1;
               if (var13.getConfigurationSection("Signs.Join") != null && !var13.getConfigurationSection("Signs.Join").getKeys(false).isEmpty()) {
                  var22 = var13.getConfigurationSection("Signs.Join").getKeys(false).size() + 1;
                  if (var13.contains("Signs.Join." + var22)) {
                     var22 = 1;
                  }

                  while (var13.contains("Signs.Join." + var22)) {
                     var22++;
                  }
               }

               var13.set("Signs.Join." + var22, Utils.getStringFromLocation(var18, false));
               FilesManager.getInstance().saveConfig("signs.yml");
               BattleRoyaleX.getInstance().joinSigns.put(var18, var22);
               var1.setLine(2, "Players:");
               var1.setLine(3, String.valueOf(BattleRoyaleX.getInstance().players.size()));
               var2.sendMessage(
                  Customization.getInstance().prefix
                     + "You have "
                     + ChatColor.GREEN
                     + "successfully"
                     + ChatColor.GRAY
                     + " created a "
                     + ChatColor.AQUA
                     + "join"
                     + ChatColor.GRAY
                     + " sign with the id of "
                     + ChatColor.LIGHT_PURPLE
                     + "#"
                     + var22
               );
            } else {
               final Arena var14 = (Arena)BattleRoyaleX.getInstance().arenas.get(var12.toLowerCase());
               if (var14 == null) {
                  var1.setLine(2, ChatColor.DARK_RED + "Invalid");
                  var2.sendMessage(Customization.getInstance().prefix + "Couldn't find an arena with that name!");
                  return;
               }

               File var19 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/arenas/" + var14.name, "locations.dat");
               YamlConfiguration var23 = YamlConfiguration.loadConfiguration(var19);
               Location var25 = var1.getBlock().getLocation();
               int var27 = 1;
               if (var23.getConfigurationSection("Signs") != null && !var23.getConfigurationSection("Signs").getKeys(false).isEmpty()) {
                  var27 = var23.getConfigurationSection("Signs").getKeys(false).size() + 1;
                  if (var23.contains("Signs." + var27)) {
                     var27 = 1;
                  }

                  while (var23.contains("Signs." + var27)) {
                     var27++;
                  }
               }

               var23.set("Signs." + var27, Utils.getStringFromLocation(var25, false));

               try {
                  var23.save(var19);
               } catch (IOException var11) {
                  var11.printStackTrace();
               }

               var14.signs.put(var25, var27);
               var1.setLine(2, Customization.getInstance().signs_arena_color + var14.name);
               Bukkit.getScheduler().scheduleSyncDelayedTask(BattleRoyaleX.getInstance(), new Runnable() {
                  public void run() {
                     var14.updateItem();
                  }
               }, 5L);
               var2.sendMessage(
                  Customization.getInstance().prefix
                     + "You have "
                     + ChatColor.GREEN
                     + "successfully"
                     + ChatColor.GRAY
                     + " created a "
                     + ChatColor.AQUA
                     + "join"
                     + ChatColor.GRAY
                     + " sign with the id of "
                     + ChatColor.LIGHT_PURPLE
                     + "#"
                     + var27
               );
            }

            return;
         }

         if (var3.equals("top")) {
            String var4 = var1.getLine(2).toUpperCase();
            boolean var5 = false;

            for (Stat var9 : Stat.values()) {
               if (var9.name().equals(var4)) {
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               var1.setLine(2, ChatColor.DARK_RED + "Invalid");
               String var17 = ChatColor.GREEN + Stat.values()[0].name();

               for (int var21 = 1; var21 < Stat.values().length; var21++) {
                  var17 = var17 + ChatColor.YELLOW + ", " + ChatColor.GREEN + Stat.values()[var21].name();
               }

               var2.sendMessage(Customization.getInstance().prefix + "Invalid stat! use one of the following stats: " + ChatColor.YELLOW + var17);
               return;
            }

            int var15 = 0;
            if (Utils.checkNumbers(new String[]{var1.getLine(3)}) && (var15 = Integer.valueOf(var1.getLine(3))) >= 1) {
               FileConfiguration var20 = FilesManager.getInstance().getConfig("signs.yml");
               Location var24 = var1.getBlock().getLocation();
               int var26 = 1;
               if (var20.getConfigurationSection("Signs.Top") != null && !var20.getConfigurationSection("Signs.Top").getKeys(false).isEmpty()) {
                  var26 = var20.getConfigurationSection("Signs.Top").getKeys(false).size() + 1;
                  if (var20.contains("Signs.Top." + var26)) {
                     var26 = 1;
                  }

                  while (var20.contains("Signs.Top." + var26)) {
                     var26++;
                  }
               }

               var20.set("Signs.Top." + var26, Utils.getStringFromLocation(var24, false));
               FilesManager.getInstance().saveConfig("signs.yml");
               BattleRoyaleX.getInstance().topSigns.put(var24, var26);
               var1.setLine(0, Customization.getInstance().leaderboard_title + var15);
               var1.setLine(1, Customization.getInstance().leaderboard_stat_color + (String)Customization.getInstance().stats.get(var4));
               var1.setLine(2, "Waiting...");
               var1.setLine(3, "(0)");
               BattleRoyaleX.getInstance().startLeaderboardUpdater();
               var2.sendMessage(
                  Customization.getInstance().prefix
                     + "You have "
                     + ChatColor.GREEN
                     + "successfully"
                     + ChatColor.GRAY
                     + " created a "
                     + ChatColor.AQUA
                     + "top"
                     + ChatColor.GRAY
                     + " sign with the id of "
                     + ChatColor.LIGHT_PURPLE
                     + "#"
                     + var26
                     + ChatColor.GRAY
                     + "! use /brx list to check when their next update is!"
               );
               return;
            }

            var1.setLine(3, ChatColor.DARK_RED + "Invalid");
            var2.sendMessage(Customization.getInstance().prefix + "Fourth line is the rank you are looking for!");
            return;
         }

         if (var3.equals("leave") || var3.equals("autojoin")) {
            var2.sendMessage(
               Customization.getInstance().prefix
                  + "You have "
                  + ChatColor.GREEN
                  + "successfully"
                  + ChatColor.GRAY
                  + " created a(n) "
                  + ChatColor.AQUA
                  + var3
                  + ChatColor.GRAY
                  + " sign!"
            );
            if (var3.equals("leave")) {
               var1.setLine(1, Customization.getInstance().signs_leave);
            } else {
               var1.setLine(1, Customization.getInstance().signs_autojoin);
            }

            return;
         }

         var2.sendMessage(Customization.getInstance().prefix + "Second line must be join/leave/autojoin/top");
         var1.setLine(1, ChatColor.DARK_RED + "Invalid");
      }
   }

   @EventHandler
   public void onBlockBreak(BlockBreakEvent var1) {
      if (!var1.isCancelled()) {
         Block var2 = var1.getBlock();
         if (var2.getState() instanceof Sign) {
            Sign var3 = (Sign)var2.getState();
            if (var3.getLine(0).equals(Customization.getInstance().signs_title) || var3.getLine(0).startsWith(Customization.getInstance().leaderboard_title)) {
               Player var4 = var1.getPlayer();
               if (!var4.hasPermission("battleroyalex.breaksigns")) {
                  var4.sendMessage((String)Customization.getInstance().messages.get("No-Permission"));
                  var1.setCancelled(true);
                  return;
               }

               if (var3.getLine(1).equals(Customization.getInstance().signs_join)) {
                  if (BattleRoyaleX.getInstance().joinSigns.containsKey(var3.getLocation())) {
                     int var12 = (Integer)BattleRoyaleX.getInstance().joinSigns.get(var3.getLocation());
                     FilesManager.getInstance().getConfig("signs.yml").set("Signs.Join." + var12, null);
                     FilesManager.getInstance().saveConfig("signs.yml");
                     BattleRoyaleX.getInstance().joinSigns.remove(var3.getLocation());
                     var4.sendMessage(
                        Customization.getInstance().prefix
                           + "You have "
                           + ChatColor.RED
                           + "removed"
                           + ChatColor.GRAY
                           + " the join sign with the id #"
                           + ChatColor.LIGHT_PURPLE
                           + var12
                     );
                  } else {
                     String var13 = ChatColor.stripColor(var3.getLine(2)).toLowerCase();
                     Arena var6 = (Arena)BattleRoyaleX.getInstance().arenas.get(var13);
                     if (var6 != null && var6.signs.containsKey(var3.getLocation())) {
                        int var7 = (Integer)var6.signs.get(var3.getLocation());
                        File var8 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/arenas/" + var6.name, "locations.dat");
                        YamlConfiguration var9 = YamlConfiguration.loadConfiguration(var8);
                        var9.set("Signs." + var7, null);

                        try {
                           var9.save(var8);
                        } catch (IOException var11) {
                           var11.printStackTrace();
                        }

                        var6.signs.remove(var3.getLocation());
                        var4.sendMessage(
                           Customization.getInstance().prefix
                              + "You have "
                              + ChatColor.RED
                              + "removed"
                              + ChatColor.GRAY
                              + " the join sign with the id #"
                              + ChatColor.LIGHT_PURPLE
                              + var7
                        );
                     }
                  }

                  return;
               }

               if (BattleRoyaleX.getInstance().topSigns.containsKey(var3.getLocation())) {
                  int var5 = (Integer)BattleRoyaleX.getInstance().topSigns.get(var3.getLocation());
                  FilesManager.getInstance().getConfig("signs.yml").set("Signs.Top." + var5, null);
                  FilesManager.getInstance().saveConfig("signs.yml");
                  BattleRoyaleX.getInstance().topSigns.remove(var3.getLocation());
                  if (BattleRoyaleX.getInstance().topSigns.isEmpty() && BattleRoyaleX.getInstance().leaderboard_updater != null) {
                     BattleRoyaleX.getInstance().leaderboard_updater.cancel();
                     BattleRoyaleX.getInstance().leaderboard_updater = null;
                  }

                  var4.sendMessage(
                     Customization.getInstance().prefix
                        + "You have "
                        + ChatColor.GREEN
                        + "successfully"
                        + ChatColor.GRAY
                        + " removed the top sign with the id of "
                        + ChatColor.LIGHT_PURPLE
                        + "#"
                        + var5
                  );
               }
            }
         }
      }
   }
}
