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
 * Bukkit facade shim for the Forge 1.20.1 port of BattleRoyaleX.
 * All calls are routed to the active {@link Server} implementation (ForgeServer).
 */
public final class Bukkit {
   private static Server server;

   public static void setServer(Server server) {
      Bukkit.server = server;
   }

   public static Server getServer() {
      return server;
   }

   public static Logger getLogger() {
      return server.getLogger();
   }

   public static String getName() {
      return server.getName();
   }

   public static String getVersion() {
      return server.getVersion();
   }

   public static String getBukkitVersion() {
      return server.getBukkitVersion();
   }

   public static boolean getOnlineMode() {
      return server.getOnlineMode();
   }

   public static PluginManager getPluginManager() {
      return server.getPluginManager();
   }

   public static BukkitScheduler getScheduler() {
      return server.getScheduler();
   }

   public static ServicesManager getServicesManager() {
      return server.getServicesManager();
   }

   public static List<World> getWorlds() {
      return server.getWorlds();
   }

   public static World getWorld(String name) {
      return server.getWorld(name);
   }

   public static World getWorld(UUID uid) {
      return server.getWorld(uid);
   }

   public static World createWorld(WorldCreator creator) {
      return server.createWorld(creator);
   }

   public static boolean unloadWorld(String name, boolean save) {
      return server.unloadWorld(name, save);
   }

   public static boolean unloadWorld(World world, boolean save) {
      return server.unloadWorld(world, save);
   }

   public static File getWorldContainer() {
      return server.getWorldContainer();
   }

   public static MapView createMap(World world) {
      return server.createMap(world);
   }

   public static List<Player> getOnlinePlayers() {
      return server.getOnlinePlayers();
   }

   public static Player getPlayer(String name) {
      return server.getPlayer(name);
   }

   public static Player getPlayer(UUID id) {
      return server.getPlayer(id);
   }

   public static ConsoleCommandSender getConsoleSender() {
      return server.getConsoleSender();
   }

   public static CommandSender getCommandSender() {
      return server.getConsoleSender();
   }

   public static boolean dispatchCommand(CommandSender sender, String commandLine) {
      return server.dispatchCommand(sender, commandLine);
   }

   public static Inventory createInventory(InventoryHolder owner, int size) {
      return server.createInventory(owner, size);
   }

   public static Inventory createInventory(InventoryHolder owner, int size, String title) {
      return server.createInventory(owner, size, title);
   }

   public static ScoreboardManager getScoreboardManager() {
      return server.getScoreboardManager();
   }

   public static Messenger getMessenger() {
      return server.getMessenger();
   }

   public static boolean addRecipe(Recipe recipe) {
      return server.addRecipe(recipe);
   }

   public static List<Recipe> recipeIterator() {
      return server.recipeIterator();
   }

   public static void clearRecipes() {
      server.clearRecipes();
   }

   public static void resetRecipes() {
      server.resetRecipes();
   }
}
