package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import java.io.IOException;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class RemoveLocationCommand extends SubCommand {
   public RemoveLocationCommand() {
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
      String var8 = var4[0].toLowerCase();
      if (var8.equals("removevehiclespawn")) {
         if (var7.vehicleSpawnpoints.isEmpty()) {
            var3.sendMessage(var2.prefix + "The arena doesn't have any vehicle spawnpoints!");
            return true;
         }

         int var9 = var7.vehicleSpawnpoints.size();
         File var10 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var11 = YamlConfiguration.loadConfiguration(var10);
         var11.set("Vehicle-Spawnpoints." + var9, null);

         try {
            var11.save(var10);
         } catch (IOException var16) {
            var16.printStackTrace();
         }

         var7.vehicleSpawnpoints.remove(var7.vehicleSpawnpoints.get(var9 - 1));
         var5.sendMessage(
            var2.prefix
               + "You have remove the last vehicle spawnpoint "
               + ChatColor.LIGHT_PURPLE
               + "(#"
               + var9
               + ")"
               + ChatColor.GRAY
               + " from the arena "
               + ChatColor.AQUA
               + var7.name
         );
      } else if (var8.equals("removesupplydrop")) {
         if (var7.supplyDrops.isEmpty()) {
            var3.sendMessage(var2.prefix + "The arena doesn't have any supply drop spawnpoints!");
            return true;
         }

         int var17 = var7.supplyDrops.size();
         File var20 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var23 = YamlConfiguration.loadConfiguration(var20);
         var23.set("Supply-Drops." + var17, null);

         try {
            var23.save(var20);
         } catch (IOException var15) {
            var15.printStackTrace();
         }

         var7.supplyDrops.remove(var7.supplyDrops.get(var17 - 1));
         var5.sendMessage(
            var2.prefix
               + "You have remove the last supply drop location "
               + ChatColor.LIGHT_PURPLE
               + "(#"
               + var17
               + ")"
               + ChatColor.GRAY
               + " from the arena "
               + ChatColor.AQUA
               + var7.name
         );
      } else if (var8.equals("removemapblip")) {
         if (var7.customBlips.isEmpty()) {
            var3.sendMessage(var2.prefix + "The arena does not have any custom blips added!");
            return true;
         }

         int var18 = var7.customBlips.size();
         File var21 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "maps.dat");
         YamlConfiguration var24 = YamlConfiguration.loadConfiguration(var21);
         var24.set("Custom-Map-Blips." + var18, null);

         try {
            var24.save(var21);
         } catch (IOException var14) {
            var14.printStackTrace();
         }

         var7.customBlips.remove(var7.customBlips.get(var18 - 1));
         var3.sendMessage(
            var2.prefix
               + "You have remove custom blip  "
               + ChatColor.LIGHT_PURPLE
               + "#"
               + var18
               + ChatColor.GRAY
               + " from the arena "
               + ChatColor.AQUA
               + var7.name
         );
      } else if (var8.equals("removeextractionpoint")) {
         if (var7.extractionPoints.isEmpty()) {
            var3.sendMessage(var2.prefix + "The arena doesn't have any extraction points defined!");
            return true;
         }

         int var19 = var7.extractionPoints.size();
         File var22 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var25 = YamlConfiguration.loadConfiguration(var22);
         var25.set("Extraction-Points." + var19, null);

         try {
            var25.save(var22);
         } catch (IOException var13) {
            var13.printStackTrace();
         }

         var7.extractionPoints.remove(var7.extractionPoints.get(var19 - 1));
         var5.sendMessage(
            var2.prefix
               + "You have remove the last extraction point location "
               + ChatColor.LIGHT_PURPLE
               + "(#"
               + var19
               + ")"
               + ChatColor.GRAY
               + " from the arena "
               + ChatColor.AQUA
               + var7.name
         );
      }

      return true;
   }
}
