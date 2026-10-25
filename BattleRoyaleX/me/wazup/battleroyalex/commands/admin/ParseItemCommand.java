package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ParseItemCommand extends SubCommand implements Listener {
   public ParseItemCommand() {
      super(
         "battleroyalex.admin",
         false,
         "<Item String>",
         new String[]{"Examples for <Item String>:", "DIAMOND_SWORD : 1 : enchant:DAMAGE_ALL:1", "QualityArmory:awp : 1", "MMOItems:SWORD:CUTLASS : 1"}
      );
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      Player var5 = (Player)var3;
      if (var4.length == 1) {
         return false;
      }

      String var6 = "";
      if (var4.length >= 2) {
         var6 = var4[1];

         for (int var7 = 2; var7 < var4.length; var7++) {
            var6 = var6 + " " + var4[var7];
         }
      }

      var5.getInventory().addItem(new ItemStack[]{Utils.getItemStack(var6, true, true)});
      return true;
   }
}
