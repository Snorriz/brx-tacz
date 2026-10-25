package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.PlayerData;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand extends SubCommand {
   public InventoryCommand() {
      super("battleroyalex.admin", true, "Give <Player> <Kit>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length >= 4 && var4[1].equalsIgnoreCase("Give")) {
         Player var5 = Bukkit.getPlayer(var4[2]);
         if (var5 == null) {
            var3.sendMessage(var2.prefix + "Couldn't find a player with that name! Player must be online for this command to work");
            return true;
         }

         String var6 = var4[3].toLowerCase();

         for (int var7 = 4; var7 < var4.length; var7++) {
            var6 = var6 + " " + var4[var7].toLowerCase();
         }

         if (!BattleRoyaleX.getInstance().kits.containsKey(var6)) {
            var3.sendMessage(var2.prefix + "Couldn't find an item with that name!");
            return true;
         } else {
            PlayerData var8 = (PlayerData)var1.playerData.get(var5.getName());
            if (var8.ownedKits.contains(var6)) {
               var3.sendMessage(var2.prefix + "Player already owns that item!");
               return true;
            } else if (var8.ownedKits.size() == 54) {
               var3.sendMessage(var2.prefix + "Player does not have enough space in inventory to receive this item!");
               return true;
            } else {
               var8.ownedKits.add(var6);
               var3.sendMessage(var2.prefix + "Player received the item successfully!");
               var8.save = true;
               return true;
            }
         }
      } else {
         return false;
      }
   }
}
