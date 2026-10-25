package me.wazup.battleroyalex.commands.admin;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.commands.SubCommand;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.FilesManager;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldManagerCommand extends SubCommand {
   public WorldManagerCommand() {
      super("battleroyalex.admin", true, null, new String[0]);
   }

   public boolean execute(final BattleRoyaleX var1, final Customization var2, final CommandSender var3, String[] var4) {
      if (var4.length == 1) {
         ChatColor var14 = Utils.getRandomColor();
         var3.sendMessage(
            var14
               + ""
               + ChatColor.STRIKETHROUGH
               + "------------"
               + ChatColor.YELLOW
               + " Battle Royale X "
               + ChatColor.RED
               + "WorldManager "
               + var14
               + ""
               + ChatColor.STRIKETHROUGH
               + "------------"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Create <World> "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Creates a new world"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Delete <World> "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Deletes an existing world"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Import <World> "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Imports a new world!"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Backup <World> "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Creates a backup of a world"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Restore <World> "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Restores a world from the backup file"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Tp <World> "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Teleports you to a world"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Setspawn <World> "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Sets the spawn of a world"
         );
         var3.sendMessage(
            var14
               + " - "
               + ChatColor.YELLOW
               + "/battleroyale WorldManager Backupall "
               + var14
               + SPECIAL_CHARACTER.ARROW
               + ChatColor.GREEN
               + " Creates a backup of all loaded worlds"
         );
         var3.sendMessage(
            var14 + " - " + ChatColor.YELLOW + "/battleroyale WorldManager List " + var14 + SPECIAL_CHARACTER.ARROW + ChatColor.GREEN + " Lists loaded worlds!"
         );
         var3.sendMessage(var14 + "" + ChatColor.STRIKETHROUGH + "------------------------------------------------");
         return true;
      }

      String var5 = var4[1].toLowerCase();
      if (var5.equals("create")) {
         List var21 = Arrays.asList(new String[]{"normal", "nether", "the_end"});
         if (var4.length >= 4 && var21.contains(var4[3].toLowerCase())) {
            String var29 = var4[2];
            String var34 = var4[3].toLowerCase();
            if (Bukkit.getWorld(var29) != null) {
               var3.sendMessage(var2.prefix + "There is a world with that name!");
               return true;
            }

            for (String var46 : Bukkit.getWorldContainer().list()) {
               if (var46.equalsIgnoreCase(var29)) {
                  var3.sendMessage(var2.prefix + "Seems like there is an unloaded world with that name! try using /battleroyale WorldManager Import <Name>");
                  return true;
               }
            }

            var3.sendMessage(var2.prefix + "Creating a new world!");
            long var39 = System.currentTimeMillis();
            Bukkit.createWorld(new WorldCreator(var29).environment(Environment.valueOf(var34.toUpperCase())));
            var3.sendMessage(
               var2.prefix
                  + "World has been created! took "
                  + ChatColor.LIGHT_PURPLE
                  + (System.currentTimeMillis() - var39)
                  + "ms"
                  + ChatColor.GRAY
                  + " to complete the process!"
            );
            if (var3 instanceof Player && var1.clickableTextManager != null) {
               Player var45 = (Player)var3;
               var1.clickableTextManager
                  .sendMessage(
                     var45,
                     var2.prefix + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "Click here to teleport to the world created!",
                     "battleroyale worldmanager tp " + var29
                  );
            }

            return true;
         } else {
            var3.sendMessage(var2.prefix + "Usage: /battleroyale " + ChatColor.GREEN + "WorldManager Create" + ChatColor.GRAY + " <Name> <Type>");
            var3.sendMessage(var2.prefix + ChatColor.AQUA + "- " + ChatColor.GRAY + "Type can be normal, nether, the_end");
            return true;
         }
      } else if (var5.equals("delete")) {
         if (var4.length == 2) {
            var3.sendMessage(var2.prefix + "Usage: /battleroyale " + ChatColor.GREEN + "WorldManager Delete" + ChatColor.GRAY + " <Name>");
            return true;
         }

         String var20 = var4[2].toLowerCase();
         World var28 = Bukkit.getWorld(var20);
         File var33 = null;
         if (var28 != null) {
            if (!var28.getPlayers().isEmpty()) {
               var3.sendMessage(var2.prefix + "The world contains players. so the world can't be deleted!");

               for (Player var40 : var28.getPlayers()) {
                  var3.sendMessage(var2.prefix + "- " + var40.getName());
               }

               return true;
            }

            var33 = var28.getWorldFolder();
            Bukkit.unloadWorld(var28, false);
         } else {
            var3.sendMessage(var2.prefix + "The world you looking for seems to be unloaded, looking up folders...");

            for (File var12 : Bukkit.getWorldContainer().listFiles()) {
               if (var12.getName().equalsIgnoreCase(var4[2])) {
                  var33 = var12;
                  break;
               }
            }
         }

         if (var33 != null) {
            long var37 = System.currentTimeMillis();
            FilesManager.deleteFile(var33);
            var3.sendMessage(var2.prefix + "World has been deleted! took " + ChatColor.LIGHT_PURPLE + (System.currentTimeMillis() - var37) + "ms!");
         } else {
            var3.sendMessage(var2.prefix + "Couldn't find a world with that name!");
         }

         return true;
      } else if (var5.equals("import")) {
         if (var4.length == 2) {
            var3.sendMessage(var2.prefix + "Usage: /battleroyale " + ChatColor.GREEN + "WorldManager Import" + ChatColor.GRAY + " <Name>");
            return true;
         }

         String var19 = var4[2];
         if (Bukkit.getWorld(var19) != null) {
            var3.sendMessage(var2.prefix + "There is a loaded world with that name!");
            return true;
         }

         for (String var10 : Bukkit.getWorldContainer().list()) {
            if (var19.equalsIgnoreCase(var10)) {
               var3.sendMessage(var2.prefix + "Importing the world...");
               long var11 = System.currentTimeMillis();
               Bukkit.createWorld(new WorldCreator(var10));
               var3.sendMessage(
                  var2.prefix + "World has been imported! check your logs! took " + ChatColor.LIGHT_PURPLE + (System.currentTimeMillis() - var11) + "ms!"
               );
               if (var3 instanceof Player && var1.clickableTextManager != null) {
                  Player var13 = (Player)var3;
                  var1.clickableTextManager
                     .sendMessage(
                        var13,
                        var2.prefix + ChatColor.YELLOW + "" + ChatColor.UNDERLINE + "Click here to teleport to the world imported!",
                        "battleroyale worldmanager tp " + var10
                     );
               }

               return true;
            }
         }

         var3.sendMessage(var2.prefix + "Couldn't find any world with that name!");
         return true;
      } else if (var5.equals("backup") || var5.equals("restore")) {
         if (var4.length == 2) {
            var3.sendMessage(
               var2.prefix
                  + "Usage: /battleroyale "
                  + ChatColor.GREEN
                  + "WorldManager "
                  + var5.substring(0, 1).toUpperCase()
                  + var5.substring(1, var5.length())
                  + ChatColor.GRAY
                  + " <Name>"
            );
            return true;
         }

         final World var18 = Bukkit.getWorld(var4[2]);
         if (var18 == null) {
            var3.sendMessage(var2.prefix + "Couldn't find a loaded world with that name!");
            return true;
         }

         if (var5.equals("backup")) {
            (new BukkitRunnable() {
                  public void run() {
                     var3.sendMessage(var2.prefix + "Creating a backup of the world " + ChatColor.GREEN + var18.getName() + ChatColor.GRAY + "!");
                     long var1x = System.currentTimeMillis();
                     var18.save();
                     FilesManager.getInstance();
                     FilesManager.copyFile(var18.getWorldFolder(), new File(var1.getDataFolder() + "/backup", var18.getName()));
                     var3.sendMessage(
                        var2.prefix
                           + "Backup has been "
                           + ChatColor.GREEN
                           + "successfully"
                           + ChatColor.GRAY
                           + " created! took "
                           + ChatColor.LIGHT_PURPLE
                           + (System.currentTimeMillis() - var1x)
                           + "ms!"
                     );
                  }
               })
               .runTaskAsynchronously(var1);
            return true;
         }

         if (!var5.equals("restore")) {
            return true;
         }

         if (var18.getPlayers().isEmpty()) {
            final File var26 = new File(var1.getDataFolder() + "/backup", var18.getName());
            if (!var26.exists()) {
               var3.sendMessage(var2.prefix + "Couldn't find a backup for that world!");
               return true;
            } else {
               (new BukkitRunnable() {
                     public void run() {
                        var3.sendMessage(var2.prefix + "Restoring the world " + ChatColor.LIGHT_PURPLE + var18.getName() + ChatColor.GRAY + "!");
                        final long var1x = System.currentTimeMillis();
                        File var3x = var18.getWorldFolder();
                        final Environment var4 = var18.getEnvironment();
                        Bukkit.unloadWorld(var18, false);
                        FilesManager.deleteFile(var3x);
                        FilesManager.copyFile(var26, var3x);
                        (new BukkitRunnable() {
                              public void run() {
                                 Bukkit.createWorld(new WorldCreator(var26.getName()).environment(var4));
                                 var3.sendMessage(
                                    var2.prefix
                                       + "World "
                                       + ChatColor.AQUA
                                       + var26.getName()
                                       + ChatColor.GRAY
                                       + " has been "
                                       + ChatColor.GREEN
                                       + "successfully"
                                       + ChatColor.GRAY
                                       + " restored! took "
                                       + ChatColor.LIGHT_PURPLE
                                       + (System.currentTimeMillis() - var1x)
                                       + "ms!"
                                 );
                                 var3.sendMessage(var2.prefix + "If the world didn't restore correctly, try using the restore command again!");
                              }
                           })
                           .runTask(var1);
                     }
                  })
                  .runTaskAsynchronously(var1);
               return true;
            }
         } else {
            var3.sendMessage(var2.prefix + "The world contains players. so the world can't be restored!");

            for (Player var31 : var18.getPlayers()) {
               var3.sendMessage(var2.prefix + "- " + var31.getName());
            }

            return true;
         }
      } else {
         if (var5.equals("backupall")) {
            final List var17 = Bukkit.getWorlds();
            var3.sendMessage(
               var2.prefix + ChatColor.GREEN + "Creating a backup of all loaded worlds " + ChatColor.GOLD + "(" + var17.size() + ")" + ChatColor.GREEN + "!"
            );
            final long var24 = System.currentTimeMillis();
            (new BukkitRunnable() {
                  int i = 0;
                  boolean busy = false;

                  public void run() {
                     if (this.i >= var17.size()) {
                        var3.sendMessage(
                           var2.prefix
                              + ChatColor.GREEN
                              + "A backup for "
                              + ChatColor.GOLD
                              + "("
                              + this.i
                              + ")"
                              + ChatColor.GREEN
                              + " worlds has been created! "
                              + ChatColor.LIGHT_PURPLE
                              + "Took: "
                              + (System.currentTimeMillis() - var24)
                              + "ms"
                        );
                        this.cancel();
                     } else {
                        if (!this.busy) {
                           World var1x = (World)var17.get(this.i);
                           this.busy = true;
                           var3.sendMessage(var2.prefix + "Creating a backup for the world " + ChatColor.AQUA + var1x.getName());
                           var1x.save();
                           FilesManager.copyFile(var1x.getWorldFolder(), new File(var1.getDataFolder() + "/backup", var1x.getName()));
                           this.busy = false;
                           this.i++;
                        }
                     }
                  }
               })
               .runTaskTimerAsynchronously(var1, 0L, 20L);
            return true;
         }

         if (var5.equals("tp")) {
            if (!(var3 instanceof Player)) {
               var3.sendMessage(var2.prefix + "You must be a player to use this command");
               return true;
            } else if (var4.length == 2) {
               var3.sendMessage(var2.prefix + "Usage: /battleroyale " + ChatColor.GREEN + "WorldManager Tp" + ChatColor.GRAY + " <World>");
               return true;
            } else {
               Player var16 = (Player)var3;
               String var23 = var4[2];
               if (Bukkit.getWorld(var23) == null) {
                  var16.sendMessage(var2.prefix + "Couldn't find a world with that name!");
                  return true;
               } else {
                  var16.teleport(Bukkit.getWorld(var23).getSpawnLocation());
                  var16.sendMessage(var2.prefix + "You have been teleported to " + ChatColor.GREEN + var23 + ChatColor.GRAY + "!");
                  return true;
               }
            }
         } else if (var5.equals("setspawn")) {
            if (!(var3 instanceof Player)) {
               var3.sendMessage(var2.prefix + "You must be a player to use this command");
               return true;
            } else if (var4.length == 2) {
               var3.sendMessage(var2.prefix + "Usage: /battleroyale " + ChatColor.GREEN + "WorldManager Setspawn" + ChatColor.GRAY + " <World>");
               return true;
            } else {
               Player var15 = (Player)var3;
               String var22 = var4[2];
               World var30 = Bukkit.getWorld(var22);
               if (var30 == null) {
                  var15.sendMessage(var2.prefix + "Couldn't find a world with that name!");
                  return true;
               } else if (!var30.getName().equals(var15.getWorld().getName())) {
                  var15.sendMessage(var2.prefix + "You are not in that world to set its spawn!");
                  return true;
               } else {
                  var30.setSpawnLocation(var15.getLocation().getBlockX(), var15.getLocation().getBlockY() + 1, var15.getLocation().getBlockZ());
                  var15.sendMessage(var2.prefix + "You have set the world spawn location!");
                  return true;
               }
            }
         } else {
            if (!var5.equals("list")) {
               var3.sendMessage(var2.prefix + "Unknown command! use /battleroyale WorldManager for a list of commands");
               return true;
            }

            ChatColor var6 = Utils.getRandomColor();
            var3.sendMessage(
               var6
                  + ""
                  + ChatColor.STRIKETHROUGH
                  + "------------"
                  + ChatColor.YELLOW
                  + " Battle Royal X "
                  + ChatColor.RED
                  + "WorldManager "
                  + var6
                  + ""
                  + ChatColor.STRIKETHROUGH
                  + "------------"
            );
            var3.sendMessage(var2.prefix + "Loaded worlds: " + ChatColor.LIGHT_PURPLE + Bukkit.getWorlds().size());

            for (World var8 : Bukkit.getWorlds()) {
               var3.sendMessage(
                  ChatColor.AQUA
                     + "- "
                     + ChatColor.LIGHT_PURPLE
                     + var8.getName()
                     + ChatColor.DARK_AQUA
                     + " -> "
                     + ChatColor.RED
                     + "Environment: "
                     + ChatColor.AQUA
                     + var8.getEnvironment().name()
                     + ", "
                     + ChatColor.YELLOW
                     + "Difficulty: "
                     + ChatColor.AQUA
                     + var8.getDifficulty().name()
                     + ", "
                     + ChatColor.GREEN
                     + "PVP: "
                     + ChatColor.AQUA
                     + var8.getPVP()
                     + ", "
                     + ChatColor.DARK_AQUA
                     + "Players: "
                     + ChatColor.AQUA
                     + var8.getPlayers().size()
               );
            }

            var3.sendMessage(var6 + "" + ChatColor.STRIKETHROUGH + "--------------------------------------------");
            return true;
         }
      }
   }
}
