package me.wazup.battleroyalex.commands.admin;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveItemCommand extends SubCommand {
   public GiveItemCommand() {
      super("battleroyalex.admin", true, "<Player> <Item Text>", new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      if (var4.length < 3) {
         return false;
      }

      Player var5 = Bukkit.getPlayer(var4[1]);
      if (var5 == null) {
         var3.sendMessage(var2.prefix + "Could not find the player " + var4[1]);
         return true;
      }

      String var6 = var4[2];

      for (int var7 = 3; var7 < var4.length; var7++) {
         var6 = var6 + " " + var4[var7];
      }

      ItemStack var10;
      try {
         var10 = Utils.getItemStack(var6, true, true);
      } catch (Exception var9) {
         var10 = null;
      }

      if (var10 == null) {
         var3.sendMessage(var2.prefix + "Could not parse the item!");
         return true;
      }

      var5.getInventory().addItem(new ItemStack[]{var10});
      if (var3 instanceof Player) {
         ((Player)var3).playSound(((Player)var3).getLocation(), SoundsManager.NOTE_PLING, 1.0F, 1.0F);
      }

      return true;
   }
}
