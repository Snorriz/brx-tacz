package me.wazup.battleroyalex.commands.admin;

import java.sql.SQLException;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.BattleRoyaleXAPI;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.Stat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CoinsCommand extends SubCommand {
   public CoinsCommand() {
      super("battleroyalex.admin", true, "<Action> <Player> <Amount>", new String[]{"Action can only be one of the following", "Add, Remove, Set"});
   }

   public boolean execute(BattleRoyaleX var1, final Customization var2, final CommandSender var3, final String[] var4) {
      final String var5;
      if (var4.length >= 4
         && ((var5 = var4[1].toLowerCase()).equals("add") || var5.equals("remove") || var5.equals("set"))
         && Utils.checkNumbers(new String[]{var4[3]})) {
         Player var6 = Bukkit.getPlayer(var4[2]);
         final int var7 = Integer.valueOf(var4[3]);
         if (var6 == null) {
            var3.sendMessage(var2.prefix + "Couldn't find that players online, looking up the database...");
            (new BukkitRunnable() {
               boolean increment = !var5.equals("set");

               public void run() {
                  try {
                     boolean var1x = BattleRoyaleXAPI.modifyOfflinePlayerStat(var4[2], Stat.COINS, var5.equals("remove") ? -var7 : var7, this.increment);
                     if (var1x) {
                        var3.sendMessage(var2.prefix + ChatColor.YELLOW + var4[2] + ChatColor.GRAY + " coins were updated!");
                     } else {
                        var3.sendMessage(var2.prefix + "Couldn't find that player in the database!");
                     }
                  } catch (SQLException var2x) {
                     var2x.printStackTrace();
                  }
               }
            }).runTaskAsynchronously(var1);
            return true;
         }

         PlayerData var8 = (PlayerData)var1.playerData.get(var6.getName());
         int var9 = var8.getCoins(var6);
         if (var5.equals("add")) {
            var8.addCoins(var6, var7);
         } else if (var5.equals("remove")) {
            var8.removeCoins(var6, var7);
         } else if (var5.equals("set")) {
            var8.setCoins(var6, var7);
         }

         if (var9 != var8.getCoins(var6)) {
            var3.sendMessage(
               var2.prefix
                  + "You have "
                  + ChatColor.GREEN
                  + "successfully"
                  + ChatColor.GRAY
                  + " changed "
                  + ChatColor.AQUA
                  + var6.getName()
                  + "'s"
                  + ChatColor.GRAY
                  + " coins!"
            );
            var6.sendMessage(
               (String)var2.messages.get("Coins-Update")
                  + (
                     var8.getCoins(var6) - var9 > 0
                        ? ChatColor.GREEN + " (+" + (var8.getCoins(var6) - var9) + ")!"
                        : ChatColor.RED + " (" + (var8.getCoins(var6) - var9) + ")!"
                  )
            );
            if (var8.lobbyScoreboard != null) {
               var8.lobbyScoreboard.update("%coins%", var8.getCoins(var6));
            }

            var8.save = true;
         } else {
            var3.sendMessage(var2.prefix + "User coins haven't changed!");
         }

         return true;
      } else {
         return false;
      }
   }
}
