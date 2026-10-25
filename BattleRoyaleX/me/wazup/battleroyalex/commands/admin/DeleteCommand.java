package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.FilesManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DeleteCommand extends SubCommand {
   public DeleteCommand() {
      super("battleroyalex.admin", true, "<Name>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length == 1) {
         return false;
      } else {
         String var5 = var4[1].toLowerCase();
         if (!var1.arenas.containsKey(var5)) {
            var3.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
            return true;
         } else {
            Arena var6 = (Arena)var1.arenas.get(var5);
            var6.stop();
            FilesManager.deleteFile(new File(var1.getDataFolder() + "/arenas/", var6.name));
            var1.arenas.remove(var5);
            var1.updateArenasInventory();
            var3.sendMessage(var2.prefix + ChatColor.GREEN + "Arena has been deleted!");
            var3.sendMessage(
               var2.prefix
                  + ChatColor.YELLOW
                  + "Note: "
                  + ChatColor.GRAY
                  + "this command does not delete the arena world! If you want to do that, you can either delete the folder manually or use the worldmanager commands"
            );
            return true;
         }
      }
   }
}
