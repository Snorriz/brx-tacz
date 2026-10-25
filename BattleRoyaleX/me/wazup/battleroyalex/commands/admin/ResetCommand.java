package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.BattleRoyaleXAPI;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ResetCommand extends SubCommand {
   public ResetCommand() {
      super("battleroyalex.admin", true, "<Player>", new String[0]);
   }

   public boolean execute(final BattleRoyaleX var1, final Customization var2, final CommandSender var3, final String[] var4) {
      if (var4.length == 1) {
         return false;
      }

      final Player var5 = Bukkit.getPlayer(var4[1]);
      final long var6 = System.currentTimeMillis();
      var3.sendMessage(var2.prefix + "Looking up the database...");
      (new BukkitRunnable() {
         public void run() {
            boolean var1x = false;
            if (var1.mySQL != null) {
               try {
                  Connection var2x = var1.mySQL.getConnection();
                  PreparedStatement var3x = var2x.prepareStatement(var1.mySQL.SELECT);
                  var3x.setString(1, var4[1]);
                  ResultSet var4x = var3x.executeQuery();
                  if (var4x.next()) {
                     var3x = var2x.prepareStatement(var1.mySQL.DELETE_PLAYER);
                     var3x.setString(1, var4[1]);
                     var3x.execute();
                     var1x = true;
                  }

                  var4x.close();
                  var3x.close();
               } catch (SQLException var5x) {
                  var5x.printStackTrace();
               }
            } else {
               for (File var7 : BattleRoyaleXAPI.getPlayersFiles()) {
                  String var8 = Config.getInstance().useUUID ? YamlConfiguration.loadConfiguration(var7).getString("Name") : var7.getName();
                  if (var8.equalsIgnoreCase(var4[1])) {
                     var7.delete();
                     var1x = true;
                     break;
                  }
               }
            }

            if (!var1x && var5 == null) {
               var3.sendMessage(var2.prefix + "Couldn't find that player in the database!");
            } else {
               if (var5 != null) {
                  ((PlayerData)var1.playerData.get(var5.getName())).load(var5);
                  ((PlayerData)var1.playerData.get(var5.getName())).save = true;
               }

               var3.sendMessage(var2.prefix + "Player stats have been reset! took " + ChatColor.LIGHT_PURPLE + (System.currentTimeMillis() - var6) + "ms!");
            }
         }
      }).runTaskAsynchronously(var1);
      return true;
   }
}
