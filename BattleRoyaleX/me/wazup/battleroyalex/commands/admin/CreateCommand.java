package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import java.io.IOException;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.FilesManager;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CreateCommand extends SubCommand {
   public CreateCommand() {
      super(
         "battleroyalex.admin",
         false,
         "<Name> <Normal/Plunder> <Min Teams> <Max Teams> <Team Size>",
         new String[]{
            "Min teams is the minimum amount of teams for the arena to start",
            "Max Teams is the maximum amount of teams that the arena can handle",
            "Team size is the amount of players that a team can hold"
         }
      );
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      String var5;
      if (var4.length >= 6
         && ((var5 = var4[2].toLowerCase()).equals("normal") || var5.equals("plunder"))
         && Utils.checkNumbers(new String[]{var4[3], var4[4], var4[5]})
         && Integer.parseInt(var4[3]) >= 2
         && Integer.parseInt(var4[4]) >= 2
         && Integer.parseInt(var4[5]) >= 1) {
         Player var6 = (Player)var3;
         String var7 = var4[1];
         if (var1.arenas.containsKey(var7.toLowerCase())) {
            var6.sendMessage(var2.prefix + "There is already an arena with that name!");
            return true;
         }

         String var8 = var6.getWorld().getName();

         for (Arena var10 : var1.arenas.values()) {
            if (var10.worldName.equalsIgnoreCase(var8)) {
               var6.sendMessage(var2.prefix + ChatColor.RED + "There is an arena that is using this world! (Arena: " + var10.name + ")");
               return true;
            }
         }

         if (var8.equals(((World)Bukkit.getWorlds().get(0)).getName())) {
            var6.sendMessage(var2.prefix + "You cannot create an arena in the default world!");
            return true;
         }

         File var17 = new File(var1.getDataFolder() + "/arenas/" + var7, "settings.yml");
         YamlConfiguration var18 = YamlConfiguration.loadConfiguration(var17);
         File var11 = new File(var1.getDataFolder() + "/arenas/" + var7, "locations.dat");
         YamlConfiguration var12 = YamlConfiguration.loadConfiguration(var11);
         var18.set("type", var5);
         var18.set("world", var8);
         var18.set("min-teams", Integer.valueOf(var4[3]));
         var18.set("max-teams", Integer.valueOf(var4[4]));
         var18.set("team-size", Integer.valueOf(var4[5]));
         if (var5.equals("plunder")) {
            var18.set("game-length-in-minutes", 10);
            var18.set("supply-drops.announce-supply-drop-before-seconds-of-drop", 60);
            var18.set("supply-drops.drop-at-minutes", new int[]{7, 3});
         }

         try {
            var18.save(var17);
            var12.save(var11);
         } catch (IOException var16) {
            var16.printStackTrace();
         }

         new Arena(var7);
         BattleRoyaleX.getInstance().updateArenasInventory();
         var6.sendMessage(
            var2.prefix + "Arena " + ChatColor.AQUA + var7 + ChatColor.GRAY + " has been " + ChatColor.GREEN + "successfully" + ChatColor.GRAY + " created!"
         );
         World var13 = var6.getWorld();
         var6.sendMessage(var2.prefix + ChatColor.GRAY + "[Optional] " + ChatColor.GREEN + "Creating backup of world " + var13.getName() + "...");
         long var14 = System.currentTimeMillis();
         var13.save();
         FilesManager.copyFile(var13.getWorldFolder(), new File(var1.getDataFolder() + "/arenas/" + var7, "world_backup"));
         var3.sendMessage(
            var2.prefix
               + "Backup has been "
               + ChatColor.GREEN
               + "successfully"
               + ChatColor.GRAY
               + " created! took "
               + ChatColor.LIGHT_PURPLE
               + (System.currentTimeMillis() - var14)
               + "ms!"
         );
         return true;
      } else {
         return false;
      }
   }
}
