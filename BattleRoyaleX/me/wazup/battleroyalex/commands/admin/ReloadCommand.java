package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {
   public ReloadCommand() {
      super("battleroyalex.admin", true, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      var3.sendMessage(var2.prefix + "Attempting to reload the plugin!");
      long var5 = System.currentTimeMillis();

      for (Arena var8 : BattleRoyaleX.getInstance().arenas.values()) {
         var8.stop();
      }

      for (Player var11 : Utils.getPlayers(var1.players)) {
         var1.leave(var11, true);
         var11.sendMessage((String)var2.messages.get("Reload-Kick"));
      }

      var1.mainSetup();
      long var10 = System.currentTimeMillis();
      var3.sendMessage(
         var2.prefix
            + "The plugin has been "
            + ChatColor.GREEN
            + "successfully"
            + ChatColor.GRAY
            + " reloaded! took "
            + ChatColor.LIGHT_PURPLE
            + (var10 - var5)
            + "ms!"
      );
      return true;
   }
}
