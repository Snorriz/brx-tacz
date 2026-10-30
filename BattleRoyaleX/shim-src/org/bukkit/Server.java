package org.bukkit;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * Minimal Bukkit Server facade implemented by the Forge port (ForgeServer).
 */
public interface Server {
   Logger getLogger();

   String getName();

   String getVersion();

   String getBukkitVersion();

   boolean getOnlineMode();

   PluginManager getPluginManager();

   BukkitScheduler getScheduler();

   ServicesManager getServicesManager();

   List<World> getWorlds();

   World getWorld(String name);

   World getWorld(UUID uid);

   World createWorld(WorldCreator creator);

   boolean unloadWorld(String name, boolean save);

   boolean unloadWorld(World world, boolean save);

   File getWorldContainer();

   MapView createMap(World world);

   List<Player> getOnlinePlayers();

   Player getPlayer(String name);

   Player getPlayer(UUID id);

   ConsoleCommandSender getConsoleSender();

   boolean dispatchCommand(CommandSender sender, String commandLine);

   Inventory createInventory(InventoryHolder owner, int size);

   Inventory createInventory(InventoryHolder owner, int size, String title);

   ScoreboardManager getScoreboardManager();

   Messenger getMessenger();

   boolean addRecipe(Recipe recipe);

   List<Recipe> recipeIterator();

   void clearRecipes();

   void resetRecipes();

   void shutdown();
}
