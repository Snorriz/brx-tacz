package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import java.io.IOException;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetLobbyCommand extends SubCommand {
   public SetLobbyCommand() {
      super("battleroyalex.admin", false, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      Player var5 = (Player)var3;
      if (var4.length == 1) {
         var1.lobbyLocation = var5.getLocation();
         var1.getConfig().set("Lobby", Utils.getStringFromLocation(var1.lobbyLocation, true));
         var1.saveConfig();
         var5.sendMessage(
            var2.prefix
               + "Lobby has been "
               + ChatColor.GREEN
               + "successfully"
               + ChatColor.GRAY
               + " set at "
               + Utils.getReadableLocationString(var1.lobbyLocation, true)
         );
      } else {
         String var6 = var4[1].toLowerCase();
         if (!var1.arenas.containsKey(var6)) {
            var5.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
            return true;
         }

         Arena var7 = (Arena)var1.arenas.get(var6);
         File var8 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var9 = YamlConfiguration.loadConfiguration(var8);
         String var10 = Utils.getStringFromLocation(var5.getLocation(), true);
         var9.set("Lobby", var10);

         try {
            var9.save(var8);
         } catch (IOException var12) {
            var12.printStackTrace();
         }

         var7.lobby = Utils.getLocationFromString(var10);
         var5.sendMessage(
            var2.prefix
               + "Lobby has been "
               + ChatColor.GREEN
               + "successfully"
               + ChatColor.GRAY
               + " set for the arena "
               + ChatColor.GOLD
               + var7.name
               + ChatColor.GRAY
               + " at "
               + Utils.getReadableLocationString(var7.lobby, true)
         );
      }

      return true;
   }
}
