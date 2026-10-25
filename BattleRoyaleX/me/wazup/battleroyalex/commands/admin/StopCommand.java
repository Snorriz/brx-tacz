package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand extends SubCommand {
   public StopCommand() {
      super("battleroyalex.admin", true, "<Arena>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      String var5;
      if (var4.length == 1) {
         if (!(var3 instanceof Player) || ((PlayerData)var1.playerData.get(var3.getName())).arena == null) {
            return false;
         }

         var5 = ((PlayerData)var1.playerData.get(var3.getName())).arena.name.toLowerCase();
      } else {
         var5 = var4[1].toLowerCase();
      }

      if (!var1.arenas.containsKey(var5)) {
         var3.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
         return true;
      } else {
         Arena var6 = (Arena)var1.arenas.get(var5);
         if (!var6.state.equals(ArenaState.INGAME) && !var6.state.equals(ArenaState.ENDING)) {
            var3.sendMessage(var2.prefix + "You may not stop the arena in its current state!");
            return true;
         } else {
            var6.stop();
            var3.sendMessage(var2.prefix + "You have forced the arena " + ChatColor.YELLOW + var6.name + ChatColor.GRAY + " to stop!");
            return true;
         }
      }
   }
}
