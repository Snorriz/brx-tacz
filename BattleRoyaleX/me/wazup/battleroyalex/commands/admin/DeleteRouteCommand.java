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

public class DeleteRouteCommand extends SubCommand {
   public DeleteRouteCommand() {
      super("battleroyalex.admin", false, "<Arena> <Route #>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length >= 3 && Utils.checkNumbers(new String[]{var4[2]})) {
         Player var5 = (Player)var3;
         String var6 = var4[1].toLowerCase();
         if (!var1.arenas.containsKey(var6)) {
            var3.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
            return true;
         }

         Arena var7 = (Arena)var1.arenas.get(var6);
         int var8 = Integer.parseInt(var4[2]);
         var7.routes.remove(var8);
         File var9 = new File(var1.getDataFolder() + "/arenas/" + var7.name, "locations.dat");
         YamlConfiguration var10 = YamlConfiguration.loadConfiguration(var9);
         var10.set("Routes." + var8, null);

         try {
            var10.save(var9);
         } catch (IOException var12) {
            var12.printStackTrace();
         }

         var5.sendMessage(var2.prefix + "You have removed route #" + var8 + " from the arena " + ChatColor.YELLOW + var7.name);
         return true;
      } else {
         return false;
      }
   }
}
