package me.wazup.battleroyalex.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.admin.AddLocationCommand;
import me.wazup.battleroyalex.commands.admin.AdminCommand;
import me.wazup.battleroyalex.commands.admin.CoinsCommand;
import me.wazup.battleroyalex.commands.admin.CreateCommand;
import me.wazup.battleroyalex.commands.admin.DeleteCommand;
import me.wazup.battleroyalex.commands.admin.DeleteRouteCommand;
import me.wazup.battleroyalex.commands.admin.EditCommand;
import me.wazup.battleroyalex.commands.admin.EditModeCommand;
import me.wazup.battleroyalex.commands.admin.GenerateMapCommand;
import me.wazup.battleroyalex.commands.admin.GiveItemCommand;
import me.wazup.battleroyalex.commands.admin.HologramsCommand;
import me.wazup.battleroyalex.commands.admin.InventoryCommand;
import me.wazup.battleroyalex.commands.admin.ParseItemCommand;
import me.wazup.battleroyalex.commands.admin.ReloadCommand;
import me.wazup.battleroyalex.commands.admin.RemoveLocationCommand;
import me.wazup.battleroyalex.commands.admin.ResetCommand;
import me.wazup.battleroyalex.commands.admin.SetLobbyCommand;
import me.wazup.battleroyalex.commands.admin.SetModifierCommand;
import me.wazup.battleroyalex.commands.admin.SetRouteCommand;
import me.wazup.battleroyalex.commands.admin.SetSpectatorsAndMapCenterCommand;
import me.wazup.battleroyalex.commands.admin.StartCommand;
import me.wazup.battleroyalex.commands.admin.StopCommand;
import me.wazup.battleroyalex.commands.admin.TestCommand;
import me.wazup.battleroyalex.commands.admin.WorldManagerCommand;
import me.wazup.battleroyalex.commands.user.AutoJoinCommand;
import me.wazup.battleroyalex.commands.user.GUICommand;
import me.wazup.battleroyalex.commands.user.JoinCommand;
import me.wazup.battleroyalex.commands.user.LeaveCommand;
import me.wazup.battleroyalex.commands.user.ListCommand;
import me.wazup.battleroyalex.commands.user.PartyCommand;
import me.wazup.battleroyalex.commands.user.RecipesCommand;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor, TabCompleter {
   HashMap<String, SubCommand> commands = new HashMap();

   public MainCommand() {
      this.commands.put("join", new JoinCommand());
      this.commands.put("leave", new LeaveCommand());
      this.commands.put("autojoin", new AutoJoinCommand());
      this.commands.put("list", new ListCommand());
      this.commands.put("gui", new GUICommand());
      this.commands.put("recipes", new RecipesCommand());
      this.commands.put("party", new PartyCommand());
      this.commands.put("admin", new AdminCommand());
      this.commands.put("setlobby", new SetLobbyCommand());
      this.commands.put("create", new CreateCommand());
      this.commands.put("delete", new DeleteCommand());
      this.commands.put("setroute", new SetRouteCommand());
      this.commands.put("deleteroute", new DeleteRouteCommand());
      this.commands.put("setspectators", new SetSpectatorsAndMapCenterCommand());
      this.commands.put("setmapcenter", this.commands.get("setspectators"));
      this.commands.put("addvehiclespawn", new AddLocationCommand());
      this.commands.put("addsupplydrop", this.commands.get("addvehiclespawn"));
      this.commands.put("addmapblip", this.commands.get("addvehiclespawn"));
      this.commands.put("addextractionpoint", this.commands.get("addvehiclespawn"));
      this.commands.put("removevehiclespawn", new RemoveLocationCommand());
      this.commands.put("removesupplydrop", this.commands.get("removevehiclespawn"));
      this.commands.put("removemapblip", this.commands.get("removevehiclespawn"));
      this.commands.put("removeextractionpoint", this.commands.get("removevehiclespawn"));
      this.commands.put("generatemap", new GenerateMapCommand());
      this.commands.put("worldmanager", new WorldManagerCommand());
      this.commands.put("wm", this.commands.get("worldmanager"));
      this.commands.put("inventory", new InventoryCommand());
      this.commands.put("giveitem", new GiveItemCommand());
      this.commands.put("start", new StartCommand());
      this.commands.put("stop", new StopCommand());
      this.commands.put("coins", new CoinsCommand());
      this.commands.put("setmodifier", new SetModifierCommand());
      this.commands.put("holograms", new HologramsCommand());
      this.commands.put("reset", new ResetCommand());
      this.commands.put("edit", new EditCommand());
      this.commands.put("editmode", new EditModeCommand());
      this.commands.put("reload", new ReloadCommand());
      this.commands.put("parseitem", new ParseItemCommand());
      this.commands.put("test", new TestCommand());
   }

   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      BattleRoyaleX var5 = BattleRoyaleX.getInstance();
      if (var1 instanceof Player) {
         ((Player)var1).playSound(((Player)var1).getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
      }

      if (var4.length == 0) {
         ChatColor var14 = Utils.getRandomColor();
         var1.sendMessage(
            var14
               + ""
               + ChatColor.STRIKETHROUGH
               + "-------------"
               + ChatColor.YELLOW
               + " Battle Royale X "
               + ChatColor.GRAY
               + "["
               + var5.getDescription().getVersion()
               + "] "
               + var14
               + ""
               + ChatColor.STRIKETHROUGH
               + "-------------"
         );
         var1.sendMessage(var14 + " - " + ChatColor.YELLOW + "/battleroyale " + var14 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Shows a list of commands");
         if (!Config.getInstance().bungee_mode_enabled) {
            var1.sendMessage(
               var14 + " - " + ChatColor.YELLOW + "/battleroyale Join " + var14 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Puts you in the game lobby"
            );
         }

         var1.sendMessage(
            var14 + " - " + ChatColor.YELLOW + "/battleroyale Leave " + var14 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Removes you from the game!"
         );
         var1.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale Autojoin "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Puts you in the best available arena!"
         );
         var1.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale List "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Shows a list of arenas and other information!"
         );
         var1.sendMessage(var14 + " - " + ChatColor.YELLOW + "/battleroyale Gui " + var14 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Opens a specific GUI");
         var1.sendMessage(
            var14 + " - " + ChatColor.YELLOW + "/battleroyale Recipes " + var14 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " View a list of custom recipes!"
         );
         var1.sendMessage(
            var14 + " - " + ChatColor.YELLOW + "/battleroyale Party " + var14 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Manages party features"
         );
         var1.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale "
               + (var1.hasPermission("battleroyalex.admin") ? ChatColor.GREEN : ChatColor.RED)
               + "Admin "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Shows a list of admin commands"
         );
         var1.sendMessage(var14 + "" + ChatColor.STRIKETHROUGH + "---------------------------------------------");
         return true;
      } else {
         String var6 = var4[0].toLowerCase();
         Customization var7 = Customization.getInstance();
         if (!this.commands.containsKey(var6)) {
            var1.sendMessage((String)var7.messages.get("Unknown-Command"));
            return true;
         }

         SubCommand var8 = (SubCommand)this.commands.get(var6);
         if (!var8.allowConsole && !(var1 instanceof Player)) {
            var1.sendMessage(var7.prefix + "You must be a player to use this command");
            return true;
         }

         if (var8.permission != null && !var1.hasPermission(var8.permission)) {
            var1.sendMessage((String)var7.messages.get("No-Permission"));
            return false;
         }

         boolean var9 = var8.execute(var5, var7, var1, var4);
         if (!var9) {
            var1.sendMessage(var7.prefix + "Usage: /BattleRoyale " + ChatColor.GREEN + var4[0] + ChatColor.YELLOW + " " + var8.arguments);

            for (String var13 : var8.argumentsExplaination) {
               var1.sendMessage(var7.prefix + ChatColor.AQUA + "- " + ChatColor.GRAY + var13);
            }
         }

         return true;
      }
   }

   public List<String> onTabComplete(CommandSender var1, Command var2, String var3, String[] var4) {
      if (var4.length != 1) {
         String var9 = var4[0].toLowerCase();
         return !var9.equals("join") && !var9.equals("start") && !var9.equals("stop") && !var9.equals("edit")
            ? null
            : new ArrayList(BattleRoyaleX.getInstance().arenas.keySet());
      }

      ArrayList var5 = new ArrayList();

      for (String var7 : this.commands.keySet()) {
         SubCommand var8 = (SubCommand)this.commands.get(var7);
         if (var8.permission == null || var1.hasPermission(var8.permission)) {
            var5.add(var7);
         }
      }

      return var5;
   }
}
