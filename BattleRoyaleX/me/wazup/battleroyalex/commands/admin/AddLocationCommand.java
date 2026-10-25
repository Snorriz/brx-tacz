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

public class AddLocationCommand extends SubCommand {
   public AddLocationCommand() {
      super("battleroyalex.admin", false, "<Arena>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length == 1) {
         return false;
      }

      Player var5 = (Player)var3;
      String var6 = var4[1].toLowerCase();
      if (!var1.arenas.containsKey(var6)) {
         var5.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
         return true;
      }

      Arena var7 = (Arena)var1.arenas.get(var6);
      String var8 = Utils.getStringFromLocation(var5.getLocation(), true);
      Location var9 = Utils.getLocationFromString(var8);
      String var10 = var4[0].toLowerCase();
      if (var10.equals("addvehiclespawn")) {
         int var11 = var7.vehicleSpawnpoints.size() + 1;
         File var12 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var13 = YamlConfiguration.loadConfiguration(var12);
         var13.set("Vehicle-Spawnpoints." + var11, var8);

         try {
            var13.save(var12);
         } catch (IOException var18) {
            var18.printStackTrace();
         }

         var7.vehicleSpawnpoints.add(var9);
         var5.sendMessage(
            var2.prefix
               + "You have added vehicle spawnpoint "
               + ChatColor.LIGHT_PURPLE
               + "#"
               + var11
               + ChatColor.GRAY
               + " to the arena "
               + ChatColor.AQUA
               + var7.name
               + ChatColor.GRAY
               + " at "
               + Utils.getReadableLocationString(var5.getLocation(), true)
         );
      } else if (var10.equals("addsupplydrop")) {
         int var19 = var7.supplyDrops.size() + 1;
         File var22 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var25 = YamlConfiguration.loadConfiguration(var22);
         var25.set("Supply-Drops." + var19, var8);

         try {
            var25.save(var22);
         } catch (IOException var17) {
            var17.printStackTrace();
         }

         var7.supplyDrops.add(var9);
         var5.sendMessage(
            var2.prefix
               + "You have added supply drop "
               + ChatColor.LIGHT_PURPLE
               + "#"
               + var19
               + ChatColor.GRAY
               + " to the arena "
               + ChatColor.AQUA
               + var7.name
               + ChatColor.GRAY
               + " at "
               + Utils.getReadableLocationString(var5.getLocation(), true)
         );
      } else if (var10.equals("addmapblip")) {
         int var20 = var7.customBlips.size() + 1;
         File var23 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "maps.dat");
         YamlConfiguration var26 = YamlConfiguration.loadConfiguration(var23);
         var26.set("Custom-Map-Blips." + var20, var8);

         try {
            var26.save(var23);
         } catch (IOException var16) {
            var16.printStackTrace();
         }

         var7.customBlips.add(var9);
         var5.sendMessage(
            var2.prefix
               + "You have added custom blip "
               + ChatColor.LIGHT_PURPLE
               + "#"
               + var20
               + ChatColor.GRAY
               + " to the arena "
               + ChatColor.AQUA
               + var7.name
               + ChatColor.GRAY
               + " at "
               + Utils.getReadableLocationString(var5.getLocation(), true)
         );
      } else if (var10.equals("addextractionpoint")) {
         int var21 = var7.extractionPoints.size() + 1;
         File var24 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var27 = YamlConfiguration.loadConfiguration(var24);
         var27.set("Extraction-Points." + var21, var8);

         try {
            var27.save(var24);
         } catch (IOException var15) {
            var15.printStackTrace();
         }

         var7.extractionPoints.add(var9);
         var5.sendMessage(
            var2.prefix
               + "You have added extraction point "
               + ChatColor.LIGHT_PURPLE
               + "#"
               + var21
               + ChatColor.GRAY
               + " to the arena "
               + ChatColor.AQUA
               + var7.name
               + ChatColor.GRAY
               + " at "
               + Utils.getReadableLocationString(var5.getLocation(), true)
         );
      }

      return true;
   }
}
