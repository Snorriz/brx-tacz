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

public class SetSpectatorsAndMapCenterCommand extends SubCommand {
   public SetSpectatorsAndMapCenterCommand() {
      super("battleroyalex.admin", false, "<Arena>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length < 2) {
         return false;
      }

      Player var5 = (Player)var3;
      String var6 = var4[1].toLowerCase();
      if (!var1.arenas.containsKey(var6)) {
         var5.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
         return true;
      }

      Arena var7 = (Arena)var1.arenas.get(var6);
      File var8 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
      YamlConfiguration var9 = YamlConfiguration.loadConfiguration(var8);
      String var10 = var4[0].equalsIgnoreCase("setspectators") ? "Spectators-Spawnpoint" : "Map-Center";
      var9.set(var10, Utils.getStringFromLocation(var5.getLocation(), true));

      try {
         var9.save(var8);
      } catch (IOException var12) {
         var12.printStackTrace();
      }

      if (var10.equals("Spectators-Spawnpoint")) {
         var7.spectatorsLocation = var5.getLocation().getBlock().getLocation().add(0.5, 1.0, 0.5);
      } else {
         var7.mapCenterX = var5.getLocation().getBlockX();
         var7.mapCenterZ = var5.getLocation().getBlockZ();
         var7.createBorder(var5.getWorld(), var7.mapCenterX, var7.mapCenterZ, var7.worldBorderInitialSize);
      }

      var5.sendMessage(var2.prefix + "You have set the " + var10 + " for the arena " + ChatColor.LIGHT_PURPLE + var7.name + ChatColor.GOLD + "!");
      return true;
   }
}
