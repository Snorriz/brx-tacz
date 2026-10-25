package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditModeCommand extends SubCommand {
   public EditModeCommand() {
      super("battleroyalex.admin", false, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      Player var5 = (Player)var3;
      if (!Config.getInstance().bungee_mode_enabled) {
         var5.sendMessage(var2.prefix + "You must be in BungeeMode to use this command!");
         return true;
      }

      if (var1.players.contains(var5.getName())) {
         var5.sendMessage(var2.prefix + "You have " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + " the editing mode!");
         String var6 = Config.getInstance().bungee_mode_hub;
         Config.getInstance().bungee_mode_hub = String.valueOf(Utils.random.nextInt());
         var1.leave(var5, true);
         Config.getInstance().bungee_mode_hub = var6;
      } else if (var1.lobbyLocation != null) {
         var5.sendMessage(var2.prefix + "You have " + ChatColor.RED + "disabled" + ChatColor.GRAY + " the editing mode!");
         var1.join(var5);
      } else {
         var5.sendMessage((String)var2.messages.get("Lobby-Not-Set"));
      }

      return true;
   }
}
