package me.wazup.battleroyalex.commands.user;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand extends SubCommand {
   public JoinCommand() {
      super(null, false, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (Config.getInstance().bungee_mode_enabled) {
         var3.sendMessage((String)var2.messages.get("Unknown-Command"));
         return true;
      }

      Player var5 = (Player)var3;
      if (var1.lobbyLocation == null) {
         var5.sendMessage((String)var2.messages.get("Lobby-Not-Set"));
         return true;
      }

      if (var4.length > 1) {
         String var6 = var4[1].toLowerCase();
         if (var1.arenas.containsKey(var6)) {
            if (var1.lobbyPlayers.contains(var5.getName())) {
               ((Arena)var1.arenas.get(var6)).join(var5);
            } else if (!var1.players.contains(var5.getName())) {
               var1.join(var5);
               ((Arena)var1.arenas.get(var6)).join(var5);
            } else {
               var5.sendMessage((String)var2.messages.get("Already-In-Game"));
            }
         } else {
            var5.sendMessage((String)var2.messages.get("Invalid-Arena"));
         }
      } else {
         if (var1.players.contains(var5.getName())) {
            var5.sendMessage((String)var2.messages.get("Already-In-Game"));
            return true;
         }

         var1.join(var5);
      }

      return true;
   }
}
