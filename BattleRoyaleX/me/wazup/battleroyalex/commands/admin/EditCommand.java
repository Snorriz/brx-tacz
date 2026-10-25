package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.Arena;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditCommand extends SubCommand {
   public EditCommand() {
      super("battleroyalex.admin", false, "<Arena>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length == 1) {
         return false;
      } else {
         Player var5 = (Player)var3;
         String var6 = var4[1].toLowerCase();
         if (!var1.arenas.containsKey(var6)) {
            var5.sendMessage(var2.prefix + "Couldn't find an arena with that name!");
            return true;
         } else {
            Arena var7 = (Arena)var1.arenas.get(var6);
            if (var7.editor == null) {
               var5.sendMessage(var2.prefix + "Could not open arena editor gui. Arena name is probably too long for GUI inventory");
               return true;
            } else {
               var5.openInventory(var7.editor);
               return true;
            }
         }
      }
   }
}
