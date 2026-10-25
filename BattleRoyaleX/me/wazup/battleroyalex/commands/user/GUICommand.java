package me.wazup.battleroyalex.commands.user;

import java.util.Arrays;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUICommand extends SubCommand {
   List<String> options = Arrays.asList(new String[]{"kits", "loadouts", "stats", "arenas", "party", "profile", "quit"});

   public GUICommand() {
      super(null, false, "<Kits/Loadouts/Stats/Arenas/Party/Profile/Quit>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length != 1 && this.options.contains(var4[1].toLowerCase())) {
         Player var5 = (Player)var3;
         if (var1.lobbyPlayers.contains(var5.getName())) {
            String var6 = var4[1].toLowerCase();
            if (var6.equals("kits")) {
               var5.openInventory(var1.kitOptionsInventory);
            } else if (var6.equals("loadouts")) {
               var5.openInventory(((PlayerData)var1.playerData.get(var5.getName())).getLoadoutsInventory());
            } else if (var6.equals("stats")) {
               var5.openInventory(((PlayerData)var1.playerData.get(var5.getName())).getStatsInventory(var5));
            } else if (var6.equals("arenas")) {
               var1.arenaSelector.open(var5);
            } else if (var6.equals("party")) {
               PlayerData var7 = (PlayerData)var1.playerData.get(var5.getName());
               if (var7.party == null) {
                  var5.openInventory(BattleRoyaleX.getInstance().partyMenu);
               } else {
                  var5.openInventory(var7.party.gui);
               }
            } else if (var6.equals("profile")) {
               var5.openInventory(var1.profileInventory);
            } else if (var6.equals("quit")) {
               var5.openInventory(var1.quitInventory);
            }
         } else {
            var5.sendMessage((String)var2.messages.get("Not-In-Lobby"));
         }

         return true;
      } else {
         return false;
      }
   }
}
