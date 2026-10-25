package me.wazup.battleroyalex.commands.user;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.Party;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand extends SubCommand {
   public PartyCommand() {
      super(null, false, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      Player var5 = (Player)var3;
      if (!var1.players.contains(var5.getName())) {
         var5.sendMessage((String)var2.messages.get("Not-In-Game"));
         return true;
      }

      if (var4.length == 1) {
         ChatColor var11 = Utils.getRandomColor();
         var3.sendMessage(
            var11
               + ""
               + ChatColor.STRIKETHROUGH
               + "------------"
               + ChatColor.YELLOW
               + " Battle Royale X "
               + ChatColor.RED
               + "Party Manager "
               + var11
               + ""
               + ChatColor.STRIKETHROUGH
               + "------------"
         );
         var3.sendMessage(
            var11 + " - " + ChatColor.YELLOW + "/battleroyale Party Join <Owner> " + var11 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Joins a party"
         );
         var3.sendMessage(var11 + "" + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
         return true;
      }

      String var6 = var4[1].toLowerCase();
      if (var6.equals("join")) {
         if (var4.length < 3) {
            var3.sendMessage(var2.prefix + "Usage: /BattleRoyale" + ChatColor.GREEN + " Party Join " + ChatColor.YELLOW + "<Owner>");
            return true;
         }

         if (((PlayerData)var1.playerData.get(var5.getName())).party != null) {
            var5.sendMessage((String)var2.messages.get("Party-Already-In-Party"));
            return true;
         }

         String var7 = var4[2];
         Player var8 = Bukkit.getPlayer(var7);
         if (var8 == null) {
            var3.sendMessage((String)var2.messages.get("Unknown-Player"));
            return true;
         }

         for (Party var10 : var1.parties) {
            if (var10.leaderName.equals(var8.getName())) {
               var10.join(var5);
               return true;
            }
         }

         var5.sendMessage((String)var2.messages.get("Party-Not-Invited"));
         return true;
      } else {
         var5.sendMessage((String)var2.messages.get("Unknown-Command"));
         return true;
      }
   }
}
