package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class AdminCommand extends SubCommand {
   public AdminCommand() {
      super("battleroyalex.admin", true, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      ChatColor var5 = Utils.getRandomColor();
      int var6 = var4.length > 1 && Utils.checkNumbers(new String[]{var4[1]}) ? Math.min(Math.max(Integer.valueOf(var4[1]), 1), 2) : 1;
      var3.sendMessage(
         var5
            + ""
            + ChatColor.STRIKETHROUGH
            + "-------"
            + ChatColor.YELLOW
            + " TaxBattle "
            + ChatColor.RED
            + "Admin"
            + ChatColor.GRAY
            + " [Page "
            + var6
            + "] "
            + var5
            + ""
            + ChatColor.STRIKETHROUGH
            + "-------"
      );
      if (var6 == 1) {
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Setlobby " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Sets the lobby location!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Create " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Creates a new arena!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Delete " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Deletes an existing arena!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale SetMapCenter " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Sets the map center"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale GenerateMap "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Generates map drawings for the arena"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale SetRoute/DeleteRoute "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Creates/Deletes a route for the bus"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale AddVehicleSpawn/RemoveVehicleSpawn "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Adds/Removes a vehicle spawnpoint"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale AddSupplyDrop/RemoveSupplyDrop "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Adds/Removes a supply drop spawnpoint"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale AddExtractionPoint/RemoveExtractionPoint "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Adds/Removes a plunder mode cash extraction point"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale AddMapBlip/RemoveMapBlip "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Adds/Removes custom green blips in the map at your location"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Edit " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Modifies arena settings"
         );
      } else {
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale SetSpectators "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Sets the location that spectators teleport to"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Shows a list of World management commands!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Inventory " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Modifies a player inventory"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale GiveItem " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Gives a player a certain item"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale Start/Stop "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Forces an arena to start/stop!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Coins " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Modifies a player coins!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale SetModifier " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Changes a player modifier!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Holograms " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Manages holographic features!"
         );
         var3.sendMessage(
            var5 + " - " + ChatColor.YELLOW + "/battleroyale Reset " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Resets a player stats!"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale Editmode "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Allows the user to modify surroundings in bungeemode!"
         );
         var3.sendMessage(
            var5
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale ParseItem "
               + var5
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Allows the user to parse items from strings (use to test items before adding to chests.yml)"
         );
         var3.sendMessage(var5 + " - " + ChatColor.YELLOW + "/battleroyale Reload " + var5 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Reloads the plugin!");
      }

      var3.sendMessage(var5 + "" + ChatColor.STRIKETHROUGH + "--------------------------------------");
      return true;
   }
}
