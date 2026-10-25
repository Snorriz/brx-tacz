package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import java.io.IOException;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetRouteCommand extends SubCommand {
   public SetRouteCommand() {
      super("battleroyalex.admin", false, "<Arena> <Route #> <Start/Finish>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length >= 4 && Utils.checkNumbers(new String[]{var4[2]}) && (var4[3].equalsIgnoreCase("start") || var4[3].equalsIgnoreCase("finish"))) {
         Player var5 = (Player)var3;
         String var6 = var4[1].toLowerCase();
         if (!var1.arenas.containsKey(var6)) {
            var3.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
            return true;
         }

         Arena var7 = (Arena)var1.arenas.get(var6);
         int var8 = Integer.parseInt(var4[2]);
         boolean var9 = var4[3].equalsIgnoreCase("start");
         Location[] var10 = (Location[])var7.routes.getOrDefault(var8, new Location[2]);
         var10[var9 ? 0 : 1] = var5.getLocation();
         var7.routes.put(var8, var10);
         File var11 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var12 = YamlConfiguration.loadConfiguration(var11);
         var12.set("Routes." + var8 + "." + (var9 ? "Start" : "Finish"), Utils.getStringFromLocation(var5.getLocation(), false));

         try {
            var12.save(var11);
         } catch (IOException var14) {
            var14.printStackTrace();
         }

         var5.sendMessage(
            var2.prefix
               + "You have set the arena "
               + ChatColor.YELLOW
               + var7.name
               + ChatColor.GRAY
               + " route "
               + ChatColor.AQUA
               + "#"
               + var8
               + " "
               + var4[3]
               + ChatColor.GRAY
               + " at "
               + Utils.getReadableLocationString(var5.getLocation(), false)
         );
         return true;
      } else {
         return false;
      }
   }
}
