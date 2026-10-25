package me.wazup.battleroyalex.commands.user;

import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.RecipeManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RecipesCommand extends SubCommand {
   public RecipesCommand() {
      super(null, false, null, new String[0]);
   }

   public boolean execute(BattleRoyaleX var1, Customization var2, CommandSender var3, String[] var4) {
      Player var5 = (Player)var3;
      if (var1.players.contains(var5.getName())) {
         RecipeManager.recipesInventory.open(var5);
      } else {
         var5.sendMessage((String)var2.messages.get("Not-In-Game"));
      }

      return true;
   }
}
