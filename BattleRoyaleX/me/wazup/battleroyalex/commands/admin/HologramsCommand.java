package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HologramsCommand extends SubCommand {
   public HologramsCommand() {
      super("battleroyalex.admin", false, "<Set/Remove> <Stats/Leaderboard>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      String var5;
      String var6;
      if (var4.length >= 3
         && ((var5 = var4[1].toLowerCase()).equals("set") || var5.equals("remove"))
         && ((var6 = var4[2].toLowerCase()).equals("stats") || var6.equals("leaderboard"))) {
         if (var1.hologramsManager == null) {
            var3.sendMessage(var2.prefix + "HolographicDisplays doesn't seem to be loaded!");
            return true;
         }

         Player var7 = (Player)var3;
         if (var5.equals("set")) {
            Location var8 = var7.getLocation();
            if (var6.equals("stats")) {
               var8.add(0.0, 4.0, 0.0);
               var1.hologramsManager.setStatsLocation(var8);
               var1.getConfig().set("Holographic-Stats", Utils.getStringFromLocation(var8, false));
               var7.sendMessage(var2.prefix + "You have set the holographic stats location!");
            } else {
               var8.add(0.0, 3.0, 0.0);
               var1.hologramsManager.setLeaderboardLocation(var8, true);
               var1.getConfig().set("Holographic-Leaderboard", Utils.getStringFromLocation(var8, false));
               var7.sendMessage(var2.prefix + "You have set the holographic leaderboard location!");
            }
         } else if (var6.equals("stats")) {
            var1.hologramsManager.setStatsLocation(null);
            var1.getConfig().set("Holographic-Stats", null);
            var7.sendMessage(var2.prefix + "You have removed the holographic stats location!");
         } else {
            var1.hologramsManager.setLeaderboardLocation(null, false);
            var1.getConfig().set("Holographic-Leaderboard", null);
            var7.sendMessage(var2.prefix + "You have removed the holographic leaderboard location!");
         }

         var1.saveConfig();
         return true;
      } else {
         return false;
      }
   }
}
