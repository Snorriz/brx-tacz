package me.wazup.battleroyalex.commands.user;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand extends SubCommand {
   public ListCommand() {
      super(null, true, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var3 instanceof Player && ((PlayerData)var1.playerData.get(var3.getName())).hasCooldown((Player)var3, "LIST_COMMAND", 5)) {
         return true;
      }

      ChatColor var5 = Utils.getRandomColor();
      var3.sendMessage(
         var5 + "" + ChatColor.STRIKETHROUGH + "----------------" + ChatColor.YELLOW + " List " + var5 + "" + ChatColor.STRIKETHROUGH + "----------------"
      );
      var3.sendMessage(var5 + " - " + ChatColor.YELLOW + "Players: " + ChatColor.GREEN + var1.players.size());
      var3.sendMessage(
         var5
            + " - "
            + ChatColor.YELLOW
            + "Next leaderboard update: "
            + (
               var1.leaderboard_updater == null
                  ? ChatColor.RED + "Task is off"
                  : ChatColor.GREEN
                     + String.valueOf((var1.leaderboard_updater_time - System.currentTimeMillis()) / 1000L)
                     + ChatColor.AQUA
                     + "s "
                     + ChatColor.LIGHT_PURPLE
                     + "("
                     + (var1.leaderboard_updater_time - System.currentTimeMillis()) / 60000L
                     + "m)"
            )
      );
      var3.sendMessage(var5 + " - " + ChatColor.YELLOW + "Bukkit: " + ChatColor.GREEN + Bukkit.getBukkitVersion());
      var3.sendMessage(var5 + " - " + ChatColor.YELLOW + "Loaded arenas: " + ChatColor.GREEN + var1.arenas.size());

      for (Arena var7 : var1.arenas.values()) {
         String var8 = var5
            + " - "
            + ChatColor.YELLOW
            + var7.name
            + " ("
            + var7.state
            + ChatColor.YELLOW
            + ")"
            + var5
            + " | Mode: "
            + ChatColor.RED
            + var7.mode.name()
            + var5
            + " | Players: "
            + ChatColor.LIGHT_PURPLE
            + var7.players.size()
            + var5
            + " | Min/Max players: "
            + ChatColor.GOLD
            + var7.minPlayers
            + "/"
            + var7.maxPlayers;
         if (var1.clickableTextManager != null && var3 instanceof Player) {
            var8 = var8 + ChatColor.GRAY + " (Click to join)";
            var1.clickableTextManager.sendMessage((Player)var3, var8, "battleroyale join " + var7.name);
         } else {
            var3.sendMessage(var8);
         }
      }

      var3.sendMessage(var5 + "" + ChatColor.STRIKETHROUGH + "-------------------------------------");
      return true;
   }
}
