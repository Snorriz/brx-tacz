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

public class SetModifierCommand extends SubCommand {
   public SetModifierCommand() {
      super("battleroyalex.admin", true, "<Player> <Modifier>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, final Customization var2, final CommandSender var3, final String[] var4) {
      if (var4.length >= 3 && Utils.checkNumbers(new String[]{var4[2]})) {
         Player var5 = Bukkit.getPlayer(var4[1]);
         if (var5 == null) {
            var3.sendMessage(var2.prefix + "Couldn't find a player with that name! looking up the database...");
            (new BukkitRunnable() {
               public void run() {
                  try {
                     boolean var1x = BattleRoyaleXAPI.modifyOfflinePlayerStat(var4[1], Stat.MODIFIER, Integer.valueOf(var4[2]), false);
                     if (var1x) {
                        var3.sendMessage(var2.prefix + ChatColor.YELLOW + var4[1] + ChatColor.GRAY + " modifier was updated!");
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

         int var6 = Integer.valueOf(var4[2]);
         PlayerData var7 = (PlayerData)var1.playerData.get(var5.getName());
         int var8 = var7.modifier;
         if (var6 != var8) {
            var7.modifier = var6;
            var3.sendMessage(
               var2.prefix
                  + "You have "
                  + ChatColor.GREEN
                  + "successfully"
                  + ChatColor.GRAY
                  + " changed "
                  + ChatColor.YELLOW
                  + var5.getName()
                  + ChatColor.GRAY
                  + " modifier to "
                  + ChatColor.AQUA
                  + var6
                  + ChatColor.GRAY
                  + "!"
            );
            var5.sendMessage((String)var2.messages.get("Modifier-Update"));
            var7.save = true;
         } else {
            var3.sendMessage(var2.prefix + "The player " + ChatColor.YELLOW + var5.getName() + ChatColor.GRAY + " already has this modifier!");
         }

         return true;
      } else {
         return false;
      }
   }
}
