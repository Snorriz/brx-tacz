package me.wazup.battleroyalex.commands.user;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AutoJoinCommand extends SubCommand {
   public AutoJoinCommand() {
      super(null, false, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      Player var5 = (Player)var3;
      PlayerData var6 = (PlayerData)var1.playerData.get(var5.getName());
      if (var6.hasCooldown(var5, "AUTOJOIN_COMMAND", 3)) {
         return true;
      }

      if (var6.arena != null) {
         var5.sendMessage((String)var2.messages.get("Already-In-Game"));
         return true;
      }

      if (!var1.players.contains(var5.getName())) {
         var1.join(var5);
      }

      var1.autojoin(var5, var4.length < 2 ? null : var4[1]);
      return true;
   }
}
