package me.wazup.battleroyalex;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import me.wazup.battleroyalex.custommaps.ArenaMapRenderer;
import me.wazup.battleroyalex.custommaps.CustomMapsManager;
import me.wazup.battleroyalex.custommaps.utils.Pixel;
import me.wazup.battleroyalex.events.BRArenaStartEvent;
import me.wazup.battleroyalex.events.BRArenaStopEvent;
import me.wazup.battleroyalex.events.BRPlayerDeathEvent;
import me.wazup.battleroyalex.events.BRPlayerJoinArenaEvent;
import me.wazup.battleroyalex.events.BRPlayerLeaveArenaEvent;
import me.wazup.battleroyalex.managers.AchievementsManager;
import me.wazup.battleroyalex.managers.CelebrationsManager;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.RanksManager;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.managers.TitleManager;
import me.wazup.battleroyalex.managers.VotesManager;
import me.wazup.battleroyalex.scenarios.Scenario;
import me.wazup.battleroyalex.scenarios.ScenarioManager;
import me.wazup.battleroyalex.shop.Kit;
import me.wazup.battleroyalex.utils.Bus;
import me.wazup.battleroyalex.utils.FlexibleScoreboard;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.RealisticItems;
import me.wazup.battleroyalex.utils.SmartInventory;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.AchievementType;
import me.wazup.battleroyalex.utils.Enums.ArenaMode;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import me.zombie_striker.qav.VehicleEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.Directional;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MapCursor.Type;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.golde.bukkit.corpsereborn.CorpseAPI.CorpseAPI;

public class Arena {
   public String worldName;
   public int mapCenterX;
   public int mapCenterZ;
   public String name;
   public boolean enabled;
   public ArenaState state;
   public ArenaMode mode;
   public int minTeams;
   public int maxTeams;
   public int teamSize;
   public int minPlayers;
   public int maxPlayers;
   public int lobbyCountdown;
   public int minPlayersForCountdownShorten;
   public int countdownShortenTo;
   List<PotionEffect> potionEffects;
   public int worldBorderInitialSize;
   public int worldBorderFinalSize;
   public int eachBorderShrinkageAmount;
   public int eachBorderShrinkageDuration;
   HashMap<Integer, String> shrinkageEvent;
   HashMap<Integer, String> supplyDropsEvent;
   public int gameLengthInSeconds;
   public Location lobby;
   public Location spectatorsLocation;
   public HashMap<Integer, Location[]> routes;
   public ArrayList<Location> vehicleSpawnpoints;
   public ArrayList<Location> extractionPoints;
   WorldBorder worldBorder;
   public Inventory editor;
   public int id;
   public int slot;
   public VotesManager votesManager;
   Scenario scenario;
   public FlexibleScoreboard scoreboard;
   public String date;
   public BukkitTask task;
   public HashMap<Location, Integer> signs;
   public HashMap<String, Arena.PAData> players;
   public HashMap<String, Integer> killers;
   public List<Arena.CustomTeam> aliveTeams;
   public ArrayList<String> spectators;
   public SmartInventory teamSelector;
   public ArrayList<Location> supplyDrops;
   public List<Pixel> mapPixels;
   public List<ArenaMapRenderer> activeRenderers;
   public List<Location> customBlips;
   public ArrayList<Location> openedChests = new ArrayList();
   public ArrayList<Location> extractionChests = new ArrayList();
   public HashMap<Location, BlockState> modifiedBlocks = new HashMap();
   public HashMap<Location, Inventory> deathChests = new HashMap();
   public ArrayList<VehicleEntity> spawnedVehicles = new ArrayList();
   public int selectedSupplyDrop;
   Location behindSign;
   private static final String[] BRX_ENTITIES_METADATA = new String[]{
      "BRX_ITEM-DROP",
      "BRX_MOB",
      "BRX_DOWNED",
      "BRX_PACKAGE",
      "CELEBRATION_PARTY",
      "CELEBRATION_ANVIL",
      "CELEBRATION_GHAST",
      "BRX_PARACHUTE",
      "BRX_SUPPLY-DROP",
      "BRX_BUS"
   };

   public Arena(String var1) {
      try {
         BattleRoyaleX.getInstance().arenas.put(var1.toLowerCase(), this);
         File var2 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/arenas/" + var1, "settings.yml");
         YamlConfiguration var3 = YamlConfiguration.loadConfiguration(var2);
         var3.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(BattleRoyaleX.getInstance().getResource("arena-settings.yml"))));
         var3.options().copyDefaults(true);
         var3.save(var2);
         this.name = var1;
         this.mode = ArenaMode.getByName(var3.getString("type"));
         if (this.mode == null) {
            this.mode = ArenaMode.Normal;
         }

         this.worldName = var3.getString("world");
         this.enabled = var3.getBoolean("enabled");
         this.minTeams = Math.max(var3.getInt("min-teams"), 2);
         this.maxTeams = Math.max(var3.getInt("max-teams"), 2);
         this.teamSize = Math.max(var3.getInt("team-size"), 1);
         this.minPlayers = this.minTeams * this.teamSize;
         this.maxPlayers = this.maxTeams * this.teamSize;
         this.lobbyCountdown = var3.getInt("lobby-countdown");
         this.minPlayersForCountdownShorten = var3.getInt("lobby-countdown-shortening.min-players");
         this.countdownShortenTo = var3.getInt("lobby-countdown-shortening.shorten-to");
         this.potionEffects = new ArrayList();
         this.worldBorderInitialSize = var3.getInt("world-border.initial-size");
         this.worldBorderFinalSize = var3.getInt("world-border.final-size");
         this.gameLengthInSeconds = var3.getInt("game-length-in-minutes") * 60;
         this.shrinkageEvent = new HashMap();
         int var4 = var3.getInt("world-border.total-number-of-shrinks");
         int var5 = this.gameLengthInSeconds / (var4 + 1);
         int var6 = var3.getInt("world-border.announce-safezone-before-seconds-of-each-shrink");
         this.eachBorderShrinkageAmount = (this.worldBorderInitialSize - this.worldBorderFinalSize) / var4;
         this.eachBorderShrinkageDuration = var3.getInt("world-border.each-shrinkage-duration");
         if (this.mode.equals(ArenaMode.Normal)) {
            for (int var7 = 0; var7 < var4; var7++) {
               int var8 = this.gameLengthInSeconds - (var7 + 1) * var5;
               this.shrinkageEvent.put(var8, "Border close");
               this.shrinkageEvent.put(var8 + var6, "Safezone Dispatch");
            }
         }

         this.supplyDropsEvent = new HashMap();
         int var17 = var3.getInt("supply-drops.announce-supply-drop-before-seconds-of-drop");

         for (int var9 : var3.getIntegerList("supply-drops.drop-at-minutes")) {
            this.supplyDropsEvent.put(var9 * 60, "Supply Drop");
            this.supplyDropsEvent.put(var9 * 60 + var17, "Supply Drop Dispatch");
         }

         for (String var21 : var3.getStringList("effects")) {
            String[] var10 = var21.split(" : ");
            if (var10.length == 3) {
               this.potionEffects.add(new PotionEffect(PotionEffectType.getByName(var10[0]), Integer.valueOf(var10[1]) * 20, Integer.valueOf(var10[2]) - 1));
            }
         }

         this.players = new HashMap();
         this.spectators = new ArrayList();
         this.killers = new HashMap();
         this.aliveTeams = new ArrayList();
         this.teamSelector = new SmartInventory((String)Customization.getInstance().inventories.get("Team-Selector"));
         this.instantiateTeamsList();
         this.votesManager = new VotesManager();
         this.state = this.enabled ? ArenaState.WAITING : ArenaState.DISABLED;
         this.date = ChatColor.GRAY + new SimpleDateFormat("dd/MM/yyyy").format(new Date());
         File var20 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/arenas/" + var1, "locations.dat");
         YamlConfiguration var22 = YamlConfiguration.loadConfiguration(var20);
         if (Bukkit.getWorld(this.worldName) == null) {
            Bukkit.getConsoleSender()
               .sendMessage(Customization.getInstance().prefix + "The arena " + var1 + " world seems to be unloaded! attempting to import it");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "battleroyale worldmanager import " + this.worldName);
            if (Bukkit.getWorld(this.worldName) == null) {
               Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "Unable to load " + var1 + " world! arena will not load");
               BattleRoyaleX.getInstance().arenas.remove(var1.toLowerCase());
               return;
            }
         }

         this.signs = new HashMap();
         if (var22.getConfigurationSection("Signs") != null && !var22.getConfigurationSection("Signs").getKeys(false).isEmpty()) {
            for (String var11 : var22.getConfigurationSection("Signs").getKeys(false)) {
               int var12 = Integer.parseInt(var11);
               Location var13 = Utils.getLocationFromString(var22.getString("Signs." + var11));
               if (var13 != null) {
                  this.signs.put(var13, var12);
               }
            }
         }

         this.spectatorsLocation = var22.contains("Spectators-Spawnpoint") ? Utils.getLocationFromString(var22.getString("Spectators-Spawnpoint")) : null;
         this.lobby = var22.contains("Lobby") ? Utils.getLocationFromString(var22.getString("Lobby")) : null;
         this.routes = new HashMap();
         if (var22.getConfigurationSection("Routes") != null && !var22.getConfigurationSection("Routes").getKeys(false).isEmpty()) {
            for (String var30 : var22.getConfigurationSection("Routes").getKeys(false)) {
               Location[] var35 = new Location[2];
               if (var22.contains("Routes." + var30 + ".Start")) {
                  var35[0] = Utils.getLocationFromString(var22.getString("Routes." + var30 + ".Start"));
               }

               if (var22.contains("Routes." + var30 + ".Finish")) {
                  var35[1] = Utils.getLocationFromString(var22.getString("Routes." + var30 + ".Finish"));
               }

               this.routes.put(Integer.parseInt(var30), var35);
            }
         }

         if (var22.contains("Map-Center")) {
            Location var25 = Utils.getLocationFromString(var22.getString("Map-Center"));
            this.mapCenterX = var25.getBlockX();
            this.mapCenterZ = var25.getBlockZ();
         } else {
            this.mapCenterX = 0;
            this.mapCenterZ = 0;
         }

         this.worldBorder = this.createBorder(Bukkit.getWorld(this.worldName), this.mapCenterX, this.mapCenterZ, this.worldBorderInitialSize);
         this.vehicleSpawnpoints = new ArrayList();
         if (var22.getConfigurationSection("Vehicle-Spawnpoints") != null && !var22.getConfigurationSection("Vehicle-Spawnpoints").getKeys(false).isEmpty()) {
            for (String var31 : var22.getConfigurationSection("Vehicle-Spawnpoints").getKeys(false)) {
               this.vehicleSpawnpoints.add(Utils.getLocationFromString(var22.getString("Vehicle-Spawnpoints." + var31)));
            }
         }

         this.supplyDrops = new ArrayList();
         if (var22.getConfigurationSection("Supply-Drops") != null && !var22.getConfigurationSection("Supply-Drops").getKeys(false).isEmpty()) {
            for (String var32 : var22.getConfigurationSection("Supply-Drops").getKeys(false)) {
               this.supplyDrops.add(Utils.getLocationFromString(var22.getString("Supply-Drops." + var32)));
            }
         }

         this.extractionPoints = new ArrayList();
         if (var22.getConfigurationSection("Extraction-Points") != null && !var22.getConfigurationSection("Extraction-Points").getKeys(false).isEmpty()) {
            for (String var33 : var22.getConfigurationSection("Extraction-Points").getKeys(false)) {
               this.extractionPoints.add(Utils.getLocationFromString(var22.getString("Extraction-Points." + var33)));
            }
         }

         this.customBlips = new ArrayList();
         File var29 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/arenas/" + var1, "maps.dat");
         if (var29.exists()) {
            YamlConfiguration var34 = YamlConfiguration.loadConfiguration(var29);
            this.mapPixels = new ArrayList(16384);

            for (String var38 : var34.getStringList("Maps.1")) {
               String[] var14 = var38.split(":");
               this.mapPixels.add(new Pixel(Integer.parseInt(var14[0]), Integer.parseInt(var14[1]), Byte.parseByte(var14[2])));
            }

            if (var34.getConfigurationSection("Custom-Map-Blips") != null && !var34.getConfigurationSection("Custom-Map-Blips").getKeys(false).isEmpty()) {
               for (String var39 : var34.getConfigurationSection("Custom-Map-Blips").getKeys(false)) {
                  this.customBlips.add(Utils.getLocationFromString(var34.getString("Custom-Map-Blips." + var39)));
               }
            }
         }

         this.activeRenderers = new ArrayList();
         this.createScoreboard(true);
      } catch (Exception var16) {
         Bukkit.getConsoleSender()
            .sendMessage(
               Customization.getInstance().prefix + "Couldn't load the arena " + var1 + ", please check for missing arena files or incorrect settings!"
            );
         BattleRoyaleX.getInstance().arenas.remove(var1.toLowerCase());
         var16.printStackTrace();
      }

      try {
         this.createEditor();
      } catch (Exception var15) {
         Bukkit.getConsoleSender()
            .sendMessage(
               Customization.getInstance().prefix + "Could not create arena editor gui. Arena name is too long for gui inventory or something else went wrong"
            );
      }
   }

   public WorldBorder createBorder(World var1, int var2, int var3, int var4) {
      WorldBorder var5 = var1.getWorldBorder();
      var5.setCenter(var2, var3);
      var5.setSize(Double.valueOf((double)var4));
      var5.setDamageAmount(0.5);
      var5.setDamageBuffer(0.0);
      var5.setWarningTime(1);
      return var5;
   }

   public void createEditor() {
      if (this.editor == null) {
         this.editor = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "BB-Editing: " + ChatColor.BLUE + this.name);
      } else {
         this.editor.clear();
      }

      Utils.cageInventory(this.editor, true);
      ItemStackBuilder var1 = new ItemStackBuilder(Material.PAPER);
      byte var2 = 7;

      for (int var3 = 0; var3 < var2; var3++) {
         this.editor.setItem(var3, (ItemStack)Customization.getInstance().items.get("Plus"));
      }

      for (int var4 = 18; var4 < 18 + var2; var4++) {
         this.editor.setItem(var4, (ItemStack)Customization.getInstance().items.get("Minus"));
      }

      int var5 = 9;
      this.editor.setItem(var5++, var1.setName(ChatColor.YELLOW + "Min teams: " + ChatColor.GOLD + this.minTeams).build());
      this.editor.setItem(var5++, var1.setName(ChatColor.YELLOW + "Max teams: " + ChatColor.GOLD + this.maxTeams).build());
      this.editor.setItem(var5++, var1.setName(ChatColor.YELLOW + "Team size: " + ChatColor.GOLD + this.teamSize).build());
      this.editor.setItem(var5++, var1.setName(ChatColor.YELLOW + "Lobby countdown: " + ChatColor.GOLD + this.lobbyCountdown).build());
      this.editor.setItem(var5++, var1.setName(ChatColor.YELLOW + "Game length in minutes: " + ChatColor.GOLD + this.gameLengthInSeconds / 60).build());
      this.editor.setItem(var5++, var1.setName(ChatColor.YELLOW + "World border.Initial size: " + ChatColor.GOLD + this.worldBorderInitialSize).build());
      this.editor.setItem(var5++, var1.setName(ChatColor.YELLOW + "World border.Final size: " + ChatColor.GOLD + this.worldBorderFinalSize).build());
      this.editor
         .setItem(
            40,
            new ItemStackBuilder(this.enabled ? XMaterial.LIME_DYE.parseItem() : XMaterial.GRAY_DYE.parseItem())
               .setName(this.enabled ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled")
               .build()
         );
      this.editor.setItem(43, (ItemStack)Customization.getInstance().items.get("Save"));
   }

   private void instantiateTeamsList() {
      if (!this.aliveTeams.isEmpty()) {
         this.aliveTeams.clear();
      }

      for (int var1 = 0; var1 < this.teamSelector.getSize(); var1++) {
         this.teamSelector.clear(var1);
      }

      int var8 = 0;

      for (int var2 = 0; var2 < Math.ceil(Double.valueOf((double)this.maxTeams) / SmartInventory.slots.length); var2++) {
         if (var2 >= this.teamSelector.getSize()) {
            this.teamSelector.addInventory(ChatColor.RED + "List #" + (var2 + 1));
         }

         for (int var6 : SmartInventory.slots) {
            if (var8 >= this.maxTeams) {
               break;
            }

            Arena.CustomTeam var7 = new Arena.CustomTeam(var8 + 1, var2, var6);
            this.aliveTeams.add(var7);
            var7.updateItem(this.teamSelector);
            var8++;
         }
      }
   }

   public void updateItem() {
      int var1 = this.players.size();
      int var2 = this.state.equals(ArenaState.WAITING) ? 5 : (this.state.equals(ArenaState.STARTING) ? 4 : 14);
      BattleRoyaleX.getInstance()
         .arenaSelector
         .setItem(
            this.id,
            this.slot,
            new ItemStackBuilder(((XMaterial)XMaterial.matchXMaterial("STAINED_CLAY:" + var2).get()).parseItem())
               .setName(ChatColor.GREEN + this.name)
               .addLore(
                  new String[]{
                     (String)Customization.getInstance().lores.get("Lines"),
                     ((String)Customization.getInstance().lores.get("Players"))
                        .replace("%players%", String.valueOf(var1))
                        .replace("%maxplayers%", String.valueOf(this.maxPlayers)),
                     ((String)Customization.getInstance().lores.get("Team-Size")).replace("%teamsize%", String.valueOf(this.teamSize)),
                     ((String)Customization.getInstance().lores.get("Arena-Mode")).replace("%mode%", this.mode.name()),
                     ((String)Customization.getInstance().lores.get("State")).replace("%state%", this.state.value),
                     (String)Customization.getInstance().lores.get("Lines")
                  }
               )
               .build()
         );

      for (Location var4 : this.signs.keySet()) {
         if (var4.getBlock().getState() instanceof Sign) {
            Sign var5 = (Sign)var4.getBlock().getState();
            var5.setLine(
               3,
               Customization.getInstance()
                  .signs_arena_players
                  .replace("%players%", String.valueOf(var1))
                  .replace("%max_players%", String.valueOf(this.maxPlayers))
            );
            var5.update(true);
            if (this.behindSign == null) {
               if (XMaterial.supports(13)) {
                  if (var5.getBlock().getBlockData() instanceof Directional) {
                     this.behindSign = var5.getBlock().getRelative(((Directional)var5.getBlock().getBlockData()).getFacing().getOppositeFace()).getLocation();
                  }
               } else {
                  BlockFace var6 = ((org.bukkit.material.Sign)var5.getBlock().getState().getData()).getFacing();
                  this.behindSign = var5.getLocation().clone();
                  if (var6.equals(BlockFace.WEST)) {
                     this.behindSign.add(1.0, 0.0, 0.0);
                  } else if (var6.equals(BlockFace.SOUTH)) {
                     this.behindSign.add(0.0, 0.0, -1.0);
                  } else if (var6.equals(BlockFace.EAST)) {
                     this.behindSign.add(-1.0, 0.0, 0.0);
                  } else if (var6.equals(BlockFace.NORTH)) {
                     this.behindSign.add(0.0, 0.0, 1.0);
                  }
               }
            }

            if (this.behindSign != null) {
               if (XMaterial.supports(13)) {
                  this.behindSign.getBlock().setType(((XMaterial)XMaterial.matchXMaterial("STAINED_CLAY:" + var2).get()).parseMaterial());
               } else {
                  try {
                     BattleRoyaleX.getInstance().setTypeAndDataMethod.invoke(this.behindSign.getBlock(), new Object[]{159, (byte)var2, false});
                  } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException var7) {
                     var7.printStackTrace();
                  }
               }
            }
         }
      }
   }

   public void createScoreboard(boolean var1) {
      this.scoreboard = new FlexibleScoreboard(
         !var1 && Config.getInstance().displayHealthUnderName,
         !var1 && Config.getInstance().displayHealthInTabList,
         var1 ? Customization.getInstance().arena_lobby_scoreboard_title : Customization.getInstance().arena_game_scoreboard_title,
         var1 ? Customization.getInstance().arenaLobbyScoreboard : Customization.getInstance().arenaGameScoreboard
      );
      this.scoreboard.update("%players%", this.players.size());
      this.scoreboard.update("%max_players%", this.maxPlayers);
      this.scoreboard.update("%date%", this.date);
      this.scoreboard.update("%arena%", this.name);
      this.scoreboard.update("%mode%", this.mode.name());
      this.scoreboard.update("%timer%", this.lobbyCountdown);
      this.scoreboard.update("%border_distance%", (int)(this.worldBorder.getSize() / 2.0));
   }

   public void cancelTask() {
      if (this.task != null) {
         this.task.cancel();
      }

      this.task = null;
   }

   public List<Player> getAllPlayers() {
      ArrayList var1 = new ArrayList(this.players.keySet());
      var1.addAll(this.spectators);
      return Utils.getPlayers(var1);
   }

   public List<Player> getPlayers() {
      return Utils.getPlayers(this.players.keySet());
   }

   public List<Player> getSpectators() {
      return Utils.getPlayers(this.spectators);
   }

   public Inventory getPlayersInventory(String var1) {
      Inventory var2 = Bukkit.createInventory(null, 54, var1);

      for (Player var4 : this.getPlayers()) {
         var2.addItem(new ItemStack[]{Utils.getSkull(var4.getName(), ChatColor.AQUA + var4.getName())});
      }

      return var2;
   }

   public void join(Player var1) {
      if (!this.state.AVAILABLE()) {
         if (this.state.equals(ArenaState.INGAME)
            && BattleRoyaleX.getInstance().lobbyPlayers.contains(var1.getName())
            && Config.getInstance().allowSpectatorJoin) {
            this.joinSpectator(var1);
         } else {
            var1.sendMessage((String)Customization.getInstance().messages.get("Arena-Not-Available"));
         }
      } else if (this.lobby == null) {
         var1.sendMessage((String)Customization.getInstance().messages.get("Arena-No-Lobby"));
      } else {
         boolean var2 = true;
         if (this.routes.isEmpty()) {
            var2 = false;
         } else {
            for (Location[] var4 : this.routes.values()) {
               if (var4[0] == null || var4[1] == null) {
                  var2 = false;
                  break;
               }
            }
         }

         if (!var2) {
            var1.sendMessage((String)Customization.getInstance().messages.get("Routes-Not-Ready"));
         } else {
            PlayerData var12 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName());
            if (this.players.size() + (var12.party != null ? var12.party.players.size() : 1) > this.maxPlayers) {
               var1.sendMessage((String)Customization.getInstance().messages.get("Arena-Full"));
            } else {
               List<Player> var13 = var12.party != null ? Utils.getPlayers(var12.party.players) : Collections.singletonList(var1);
               Arena.CustomTeam var5 = this.getAvailableTeam(var13.size());
               if (var5 == null) {
                  var1.sendMessage((String)Customization.getInstance().messages.get("No-Available-Team"));
               } else {
                  if (var12.party != null) {
                     if (!var12.party.leaderName.equals(var1.getName())) {
                        var1.sendMessage((String)Customization.getInstance().messages.get("Party-Must-Be-Leader"));
                        return;
                     }

                     for (Player var7 : var13) {
                        if (!BattleRoyaleX.getInstance().lobbyPlayers.contains(var7.getName())) {
                           PlayerData var8 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var7.getName());
                           if (var8.arena == null || !var8.arena.spectators.contains(var7.getName())) {
                              var1.sendMessage(
                                 ((String)Customization.getInstance().messages.get("Party-Player-Not-Available")).replace("%member%", var7.getName())
                              );
                              return;
                           }

                           var8.arena.leave(var7);
                        }
                     }
                  }

                  for (Player var15 : var13) {
                     this.scoreboard.apply(var15);
                     var5.players.add(var15.getName());
                     this.players.put(var15.getName(), new Arena.PAData(var5));
                     ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var15.getName())).arena = this;
                     ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var15.getName())).lobbyScoreboard = null;
                     BattleRoyaleX.getInstance().lobbyPlayers.remove(var15.getName());
                     var15.teleport(this.lobby);
                     var15.getInventory().setContents((ItemStack[])Config.getInstance().hotbarItems.get("Arena"));
                     var15.updateInventory();
                     String var16 = ((String)Customization.getInstance().messages.get("Player-Join-Arena"))
                        .replace("%player%", var15.getName())
                        .replace("%players%", String.valueOf(this.players.size()))
                        .replace("%maxplayers%", String.valueOf(this.maxPlayers));
                     List<Player> var9 = this.getPlayers();

                     for (Player var11 : var9) {
                        var11.sendMessage(var16);
                     }

                     Bukkit.getPluginManager().callEvent(new BRPlayerJoinArenaEvent(var15, this.name, this.worldName, var9));
                  }

                  var5.updateItem(this.teamSelector);
                  this.scoreboard.update("%players%", this.players.size());
                  if (this.players.size() >= this.minPlayers && this.task == null) {
                     this.countDown();
                  }

                  this.updateItem();
               }
            }
         }
      }
   }

   private Arena.CustomTeam getAvailableTeam(int var1) {
      for (Arena.CustomTeam var3 : this.aliveTeams) {
         if (var3.players.size() + var1 <= this.teamSize) {
            return var3;
         }
      }

      return null;
   }

   public void joinSpectator(Player var1) {
      BattleRoyaleX var2 = BattleRoyaleX.getInstance();
      PlayerData var3 = (PlayerData)var2.playerData.get(var1.getName());
      this.spectators.add(var1.getName());
      var2.lobbyPlayers.remove(var1.getName());
      if (!var2.protectedPlayers.contains(var1.getName())) {
         var2.protectedPlayers.add(var1.getName());
      }

      var3.arena = this;
      var3.lobbyScoreboard = null;
      if (this.spectatorsLocation != null) {
         var1.teleport(this.spectatorsLocation);
      } else {
         var1.teleport(((Player)this.getPlayers().get(0)).getLocation());
      }

      var1.getInventory().clear();
      var1.updateInventory();
      if (Config.getInstance().useGameMode3ForSpectators) {
         var1.setGameMode(GameMode.SPECTATOR);
         var1.sendMessage((String)Customization.getInstance().messages.get("Spectator-Tip"));
      } else {
         Utils.makeSpectator(var1);

         for (Player var5 : this.getAllPlayers()) {
            var5.hidePlayer(var1);
         }
      }

      this.updateItem();
      this.scoreboard.apply(var1);
      var1.sendMessage((String)Customization.getInstance().messages.get("Player-Spectate"));
      TitleManager.getInstance().sendTitle(var1, (String)Customization.getInstance().titles.get("Player-Spectate"));
   }

   public void leave(Player var1) {
      BattleRoyaleX var2 = BattleRoyaleX.getInstance();
      PlayerData var3 = (PlayerData)var2.playerData.get(var1.getName());
      ArrayList<Player> var4 = new ArrayList<>();
      if (this.state.AVAILABLE() && var3.party != null) {
         if (var3.party.leaderName.equals(var1.getName())) {
            for (String var6 : var3.party.players) {
               if (this.players.containsKey(var6)) {
                  var4.add(Bukkit.getPlayer(var6));
               }
            }
         } else {
            var4.add(var1);
         }
      } else {
         var4.add(var1);
      }

      List<Player> var13 = this.getAllPlayers();

      for (Player var7 : var4) {
         for (Player var9 : var13) {
            var9.showPlayer(var7);
            var7.showPlayer(var9);
         }

         var13.remove(var7);
      }

      for (Player var16 : var4) {
         PlayerData var17 = (PlayerData)var2.playerData.get(var16.getName());
         var17.arena = null;
         if (!this.players.containsKey(var16.getName())) {
            this.spectators.remove(var16.getName());
         } else {
            Arena.CustomTeam var18 = ((Arena.PAData)this.players.get(var16.getName())).team;
            var18.players.remove(var16.getName());
            if (this.state.AVAILABLE()) {
               var18.updateItem(this.teamSelector);
            } else if (var18.players.isEmpty()) {
               this.aliveTeams.remove(var18);
            }

            this.players.remove(var16.getName());
            String var10 = ((String)Customization.getInstance().messages.get("Player-Leave-Arena"))
               .replace("%player%", var16.getName())
               .replace("%players%", String.valueOf(this.players.size()))
               .replace("%maxplayers%", String.valueOf(this.maxPlayers));

            for (Player var12 : var13) {
               var12.sendMessage(var10);
            }

            var17.cancelBounty(var16.getName());
         }

         var16.sendMessage((String)Customization.getInstance().messages.get("Player-Leave"));
         if (!var2.protectedPlayers.contains(var16.getName())) {
            var2.protectedPlayers.add(var16.getName());
         }

         var2.lobbyPlayers.add(var16.getName());
         var17.enterLobby(var16);
         if (!this.state.AVAILABLE()) {
            if (this.aliveTeams.size() < 2 && !this.state.equals(ArenaState.ENDING)) {
               this.finish();
            }
         } else {
            this.votesManager.removeVotes(var16);
            if (this.task != null && this.players.size() < this.minPlayers) {
               this.cancelTask();
               this.state = ArenaState.WAITING;
               String var19 = (String)Customization.getInstance().messages.get("Countdown-Cancel");

               for (Player var21 : var13) {
                  var21.sendMessage(var19);
               }

               this.scoreboard.update("%timer%", this.lobbyCountdown);
            }
         }

         Bukkit.getPluginManager().callEvent(new BRPlayerLeaveArenaEvent(var16, this.name, this.worldName, var13));
      }

      this.scoreboard.update("%players%", this.players.size());
      this.updateItem();
   }

   public void countDown() {
      this.state = ArenaState.STARTING;

      for (String var2 : Config.getInstance().executed_commands_arena_countdown) {
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var2.replace("%arena%", this.name).replace("%seconds%", String.valueOf(this.lobbyCountdown)));
      }

      this.task = (new BukkitRunnable() {
            int seconds = Arena.this.lobbyCountdown;

            public void run() {
               if (this.seconds > Arena.this.countdownShortenTo && Arena.this.players.size() >= Arena.this.minPlayersForCountdownShorten) {
                  this.seconds = Arena.this.countdownShortenTo;
                  String var5 = ((String)Customization.getInstance().messages.get("Arena-Starting-Countdown-Shortening"))
                     .replace("%seconds%", String.valueOf(this.seconds));
                  String var6 = ((String)Customization.getInstance().titles.get("Starting-Shortening")).replace("%seconds%", String.valueOf(this.seconds));

                  for (Player var8 : Arena.this.getPlayers()) {
                     var8.sendMessage(var5);
                     var8.playSound(var8.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
                     TitleManager.getInstance().sendTitle(var8, var6);
                  }
               } else if (Config.getInstance().broadcastTime.contains(this.seconds)) {
                  String var1 = ((String)Customization.getInstance().messages.get("Arena-Starting-Countdown"))
                     .replace("%seconds%", String.valueOf(this.seconds));
                  String var2 = ((String)Customization.getInstance().titles.get("Starting")).replace("%seconds%", String.valueOf(this.seconds));

                  for (Player var4 : Arena.this.getPlayers()) {
                     var4.sendMessage(var1);
                     var4.playSound(var4.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
                     TitleManager.getInstance().sendTitle(var4, var2);
                  }
               }

               Arena.this.scoreboard.update("%timer%", this.seconds);
               if (this.seconds == 0) {
                  Arena.this.start();
               }

               this.seconds--;
            }
         })
         .runTaskTimer(BattleRoyaleX.getInstance(), 0L, 20L);
   }

   public void start() {
      this.state = ArenaState.INGAME;
      this.cancelTask();
      this.updateItem();

      for (String var2 : Config.getInstance().executed_commands_arena_start) {
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var2.replace("%world%", this.worldName));
      }

      this.createScoreboard(false);
      ArrayList<Arena.CustomTeam> var14 = new ArrayList<>();

      for (Arena.CustomTeam var3 : this.aliveTeams) {
         if (var3.players.isEmpty()) {
            var14.add(var3);
         }
      }

      this.aliveTeams.removeAll(var14);
      List<Player> var16 = this.getPlayers();
      String var17 = this.votesManager.getVoted(2);
      if (!var17.equals("Default")) {
         World var4 = Bukkit.getWorld(this.worldName);
         var4.setTime(((Integer)this.votesManager.timeOptions.get(var17)).intValue());
      }

      String var18 = this.votesManager.getVoted(6);
      if (!var18.equals("Default")) {
         int var5 = (Integer)this.votesManager.healthOptions.get(var18);

         for (Player var7 : var16) {
            var7.setMaxHealth(var5);
            var7.setHealth(var7.getMaxHealth());
         }
      }

      String var19 = this.votesManager.getVoted(4);
      this.scenario = ScenarioManager.getInstance().getScenario(ChatColor.stripColor(var19.toLowerCase()));
      if (this.scenario != null) {
         this.scenario.registerArena(this);
      }

      ArrayList<String> var20 = new ArrayList<>();

      for (String var8 : Customization.getInstance().arenaSettings) {
         var20.add(var8.replace("%votedTime%", var17).replace("%votedScenario%", var19).replace("%votedHealth%", var18));
      }

      for (String var24 : this.mode.equals(ArenaMode.Normal) ? Customization.getInstance().normalArenaStart : Customization.getInstance().plunderArenaStart) {
         var20.add(var24);
      }

      Location[] var23 = (Location[])this.routes.values().toArray()[Utils.random.nextInt(this.routes.size())];
      this.startBus(var23, var16);
      String var25 = (String)Customization.getInstance().titles.get("Start");

      for (Player var10 : var16) {
         BattleRoyaleX.getInstance().protectedPlayers.remove(var10.getName());
         var10.playSound(var10.getLocation(), SoundsManager.ENDERDRAGON_GROWL, 1.0F, 1.0F);

         for (String var12 : var20) {
            var10.sendMessage(var12);
         }

         var10.closeInventory();
         var10.getInventory().clear();

         for (PotionEffect var36 : this.potionEffects) {
            var10.addPotionEffect(var36);
         }

         PlayerData var33 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var10.getName());
         if (this.mode.equals(ArenaMode.Normal)) {
            if (BattleRoyaleX.getInstance().kits.containsKey(var33.selectedKit)) {
               Kit var37 = (Kit)BattleRoyaleX.getInstance().kits.get(var33.selectedKit);
               var37.apply(var10);
               var10.sendMessage(((String)Customization.getInstance().messages.get("Kit-Receive")).replace("%kit%", var37.name));
            }
         } else {
            var33.getLoadout().apply(var10);
         }

         this.scoreboard.apply(var10);
         TitleManager.getInstance().sendTitle(var10, var25);
         if (this.mapPixels != null) {
            var10.getInventory().addItem(new ItemStack[]{this.createMap()});
         }
      }

      if (this.mapPixels != null) {
         MapCursorCollection var26 = this.getBorderCursorCollection((int)this.worldBorder.getSize(), -1);
         MapCursorCollection var29 = this.getBlipsCursorCollection();

         for (ArenaMapRenderer var38 : this.activeRenderers) {
            var38.updateBorderCursors(var26);

            for (int var13 = 0; var13 < var29.size(); var13++) {
               var38.addPermanentCursor(var29.getCursor(var13));
            }

            var38.updateCursors();
         }
      }

      this.startMainTimer();

      for (Location var30 : this.vehicleSpawnpoints) {
         VehicleEntity var35 = BattleRoyaleX.getInstance().spawnVehicle(var30);
         if (var35 == null) {
            break;
         }

         this.spawnedVehicles.add(var35);
      }

      for (Location var31 : this.extractionPoints) {
         this.constructExtractionPoint(var31);
      }

      Bukkit.getPluginManager().callEvent(new BRArenaStartEvent(this.name, this.worldName, var16));
   }

   public ItemStack createMap() {
      MapView var1 = Bukkit.createMap(Bukkit.getWorld(this.worldName));

      for (MapRenderer var3 : var1.getRenderers()) {
         var1.getRenderers().remove(var3);
      }

      ArenaMapRenderer var4 = new ArenaMapRenderer(this);
      var1.addRenderer(var4);
      this.activeRenderers.add(var4);
      return CustomMapsManager.getInstance().generateItemStack(var1);
   }

   public MapCursorCollection getBorderCursorCollection(int var1, int var2) {
      MapCursorCollection var3 = new MapCursorCollection();
      int var4 = this.worldBorderInitialSize + 1;
      var1 = (int)(Double.valueOf((double)var1) / var4 * 128.0);
      int var5 = (int)(var1 / 127.0 * 124.0);
      byte var6 = 10;

      for (int var7 = -var1 + var6; var7 < var1 - var6; var7 += var6) {
         var3.addCursor(var7, var5, Utils.byteFromFace(BlockFace.NORTH), Type.RED_POINTER.getValue());
         var3.addCursor(var7, -var5, Utils.byteFromFace(BlockFace.SOUTH), Type.RED_POINTER.getValue());
         var3.addCursor(-var5, var7, Utils.byteFromFace(BlockFace.EAST), Type.RED_POINTER.getValue());
         var3.addCursor(var5, var7, Utils.byteFromFace(BlockFace.WEST), Type.RED_POINTER.getValue());
      }

      if (var2 > 0) {
         var2 = (int)(Double.valueOf((double)var2) / var4 * 128.0);
         var5 = (int)(var2 / 127.0 * 124.0);
         var6 = 8;

         for (int var12 = -var2 + var6; var12 < var2 - var6; var12 += var6) {
            var3.addCursor(var12, var5, Utils.byteFromFace(BlockFace.NORTH), Type.BLUE_POINTER.getValue());
            var3.addCursor(var12, -var5, Utils.byteFromFace(BlockFace.SOUTH), Type.BLUE_POINTER.getValue());
            var3.addCursor(-var5, var12, Utils.byteFromFace(BlockFace.EAST), Type.BLUE_POINTER.getValue());
            var3.addCursor(var5, var12, Utils.byteFromFace(BlockFace.WEST), Type.BLUE_POINTER.getValue());
         }
      }

      return var3;
   }

   public MapCursorCollection getSupplyDropCursorCollection(Location var1, int var2) {
      MapCursorCollection var3 = new MapCursorCollection();
      int var4 = this.worldBorderInitialSize + 1;
      var2 = (int)(Double.valueOf((double)var2) / var4 * 128.0);
      byte var5 = 8;
      int var6 = (int)((var1.getX() - this.mapCenterX) / var4 * 256.0);
      int var7 = (int)((var1.getZ() - this.mapCenterZ) / var4 * 256.0);

      for (int var8 = -var2 + var5; var8 < var2 - var5; var8 += var5) {
         var3.addCursor(var6 + var8, var7 + var2, Utils.byteFromFace(BlockFace.NORTH), Type.GREEN_POINTER.getValue());
         var3.addCursor(var6 + var8, var7 - var2, Utils.byteFromFace(BlockFace.SOUTH), Type.GREEN_POINTER.getValue());
         var3.addCursor(var6 + var2, var7 + var8, Utils.byteFromFace(BlockFace.WEST), Type.RED_POINTER.getValue());
         var3.addCursor(var6 - var2, var7 - var8, Utils.byteFromFace(BlockFace.EAST), Type.RED_POINTER.getValue());
      }

      return var3;
   }

   public MapCursorCollection getBlipsCursorCollection() {
      MapCursorCollection var1 = new MapCursorCollection();
      int var2 = this.worldBorderInitialSize + 1;

      for (Location var4 : this.customBlips) {
         int var5 = (int)((var4.getX() - this.mapCenterX) / var2 * 256.0);
         int var6 = (int)((var4.getZ() - this.mapCenterZ) / var2 * 256.0);
         var1.addCursor(var5, var6, (byte)0, Type.WHITE_CROSS.getValue());
      }

      for (Location var8 : this.extractionPoints) {
         int var9 = (int)((var8.getX() - this.mapCenterX) / var2 * 256.0);
         int var10 = (int)((var8.getZ() - this.mapCenterZ) / var2 * 256.0);
         var1.addCursor(var9, var10, (byte)0, Type.GREEN_POINTER.getValue());
      }

      return var1;
   }

   private int getHighestEvent(int var1) {
      int var2 = 0;

      for (int var4 : this.shrinkageEvent.keySet()) {
         if (var4 > var2 && var4 < var1) {
            var2 = var4;
         }
      }

      if (!this.supplyDrops.isEmpty()) {
         for (int var6 : this.supplyDropsEvent.keySet()) {
            if (var6 > var2 && var6 < var1) {
               var2 = var6;
            }
         }
      }

      return var2;
   }

   public String getEventName(int var1) {
      if (this.shrinkageEvent.containsKey(var1)) {
         return (String)this.shrinkageEvent.get(var1);
      } else {
         return !this.supplyDrops.isEmpty() && this.supplyDropsEvent.containsKey(var1) ? (String)this.supplyDropsEvent.get(var1) : "Game ending";
      }
   }

   private String getFormattedTime(int var1) {
      return var1 < 61 ? var1 + " seconds" : var1 / 60 + 1 + " minutes";
   }

   public void startMainTimer() {
      this.scoreboard.update("%current_event%", this.getEventName(this.getHighestEvent(this.gameLengthInSeconds)));
      this.task = (new BukkitRunnable() {
         int seconds = Arena.this.gameLengthInSeconds;
         int nextEventTime = Arena.this.getHighestEvent(this.seconds);
         String lastEventTimer = "";

         public void run() {
            this.seconds--;
            if (TitleManager.getInstance().isActionBarEnabled()) {
               for (Player var2 : Arena.this.getPlayers()) {
                  Arena.this.sendDataBar(var2, false);
               }
            }

            if (this.seconds == this.nextEventTime) {
               if (this.seconds == 0) {
                  Arena.this.finish();
                  return;
               }

               if (Arena.this.shrinkageEvent.containsKey(this.seconds)) {
                  final int var10 = (int)Arena.this.worldBorder.getSize() - Arena.this.eachBorderShrinkageAmount;
                  if (((String)Arena.this.shrinkageEvent.get(this.seconds)).equals("Safezone Dispatch")) {
                     MapCursorCollection var14 = Arena.this.getBorderCursorCollection((int)Arena.this.worldBorder.getSize(), var10);

                     for (ArenaMapRenderer var4 : Arena.this.activeRenderers) {
                        var4.updateBorderCursors(var14);
                        var4.updateCursors();
                     }
                  } else {
                     Arena.this.worldBorder.setSize(var10, Arena.this.eachBorderShrinkageDuration);
                     (new BukkitRunnable() {
                        int seconds = 0;

                        public void run() {
                           if (!Arena.this.state.equals(ArenaState.INGAME)) {
                              this.cancel();
                           } else {
                              int var1 = (int)(Arena.this.worldBorder.getSize() / 2.0);
                              Arena.this.scoreboard.update("%border_distance%", var1);
                              this.seconds++;
                              MapCursorCollection var2;
                              if (this.seconds >= Arena.this.eachBorderShrinkageDuration) {
                                 this.cancel();
                                 var2 = Arena.this.getBorderCursorCollection((int)Arena.this.worldBorder.getSize(), -1);
                              } else {
                                 var2 = Arena.this.getBorderCursorCollection((int)Arena.this.worldBorder.getSize(), var10);
                              }

                              for (ArenaMapRenderer var4 : Arena.this.activeRenderers) {
                                 var4.updateBorderCursors(var2);
                                 var4.updateCursors();
                              }
                           }
                        }
                     }).runTaskTimer(BattleRoyaleX.getInstance(), 20L, 20L);
                  }
               }

               if (!Arena.this.supplyDrops.isEmpty() && Arena.this.supplyDropsEvent.containsKey(this.seconds)) {
                  if (!((String)Arena.this.supplyDropsEvent.get(this.seconds)).equals("Supply Drop Dispatch")) {
                     Location var12 = (Location)Arena.this.supplyDrops.get(Arena.this.selectedSupplyDrop);
                     Arena.this.spawnSupplyDrop(var12);
                     MapCursorCollection var16 = new MapCursorCollection();

                     for (ArenaMapRenderer var20 : Arena.this.activeRenderers) {
                        var20.updateSupplyDropCursors(var16);
                        var20.updateCursors();
                     }
                  } else {
                     Arena.this.selectedSupplyDrop = Utils.random.nextInt(Arena.this.supplyDrops.size());
                     Location var11 = (Location)Arena.this.supplyDrops.get(Arena.this.selectedSupplyDrop);
                     int var15 = (int)(Arena.this.worldBorder.getSize() / 2.0);
                     int var17 = Arena.this.mapCenterX - var15;
                     int var19 = Arena.this.mapCenterX + var15;
                     int var5 = Arena.this.mapCenterZ - var15;
                     int var6 = Arena.this.mapCenterZ + var15;

                     for (int var7 = 0; var7 < Arena.this.supplyDrops.size(); var7++) {
                        if (var11.getBlockX() < var17 || var11.getBlockX() > var19 || var11.getBlockZ() < var5 || var11.getBlockZ() > var6) {
                           Arena.this.selectedSupplyDrop++;
                           if (Arena.this.selectedSupplyDrop == Arena.this.supplyDrops.size()) {
                              Arena.this.selectedSupplyDrop = 0;
                           }
                        }
                     }

                     MapCursorCollection var21 = Arena.this.getSupplyDropCursorCollection(var11, 75);

                     for (ArenaMapRenderer var9 : Arena.this.activeRenderers) {
                        var9.updateSupplyDropCursors(var21);
                        var9.updateCursors();
                     }
                  }
               }

               this.nextEventTime = Arena.this.getHighestEvent(this.seconds);
               Arena.this.scoreboard.update("%current_event%", Arena.this.getEventName(this.nextEventTime));
            }

            String var13 = Arena.this.getFormattedTime(this.seconds - this.nextEventTime);
            if (!var13.equals(this.lastEventTimer)) {
               Arena.this.scoreboard.update("%current_event_timer%", var13);
               this.lastEventTimer = var13;
            }
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), 0L, 20L);
   }

   public void finish() {
      this.state = ArenaState.ENDING;
      this.updateItem();
      this.scoreboard.update("%current_event%", "Ending");
      this.cancelTask();
      ArrayList<String> var1 = new ArrayList<>();
      if (this.mode.equals(ArenaMode.Normal)) {
         if (this.aliveTeams.size() == 1) {
            var1 = ((Arena.CustomTeam)this.aliveTeams.get(0)).players;
         } else {
            int var2 = -1;

            for (Arena.CustomTeam var4 : this.aliveTeams) {
               if (var4.kills > var2) {
                  var2 = var4.kills;
                  var1 = var4.players;
               }
            }
         }
      } else {
         int var14 = -1;

         for (Arena.CustomTeam var18 : this.aliveTeams) {
            int var5 = var18.cash;

            for (String var7 : var18.players) {
               if (this.players.containsKey(var7)) {
                  var5 += ((Arena.PAData)this.players.get(var7)).cash;
               }
            }

            if (var5 > var14) {
               var14 = var5;
               var1 = var18.players;
            }
         }
      }

      ArrayList<String> var15 = new ArrayList<>();
      ChatColor var17 = Utils.getRandomColor();
      List var19 = this.getTopKillers();

      for (String var22 : Customization.getInstance().matchConclusion) {
         var15.add(
            var22.replace("%randomcolor%", var17.toString())
               .replace("%star%", SPECIAL_CHARACTER.STAR.toString())
               .replace("%winners%", var1.toString().replace("[", "").replace("]", ""))
               .replace("%topkiller1_name%", (CharSequence)((Entry)var19.get(0)).getKey())
               .replace("%topkiller1_kills%", String.valueOf(((Entry)var19.get(0)).getValue()))
               .replace("%topkiller2_name%", (CharSequence)((Entry)var19.get(1)).getKey())
               .replace("%topkiller2_kills%", String.valueOf(((Entry)var19.get(1)).getValue()))
               .replace("%topkiller3_name%", (CharSequence)((Entry)var19.get(2)).getKey())
               .replace("%topkiller3_kills%", String.valueOf(((Entry)var19.get(2)).getValue()))
         );
      }

      for (Player var25 : this.getAllPlayers()) {
         for (String var9 : var15) {
            var25.sendMessage(var9);
         }

         if (!this.spectators.contains(var25.getName())) {
            var25.setHealth(var25.getMaxHealth());
            if (!BattleRoyaleX.getInstance().protectedPlayers.contains(var25.getName())) {
               BattleRoyaleX.getInstance().protectedPlayers.add(var25.getName());
            }

            PlayerData var27 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var25.getName());
            var27.games_played++;
            AchievementsManager.getInstance().checkPlayer(var25, AchievementType.GAMES_PLAYED, var27.games_played);
         }
      }

      String var24 = (String)Customization.getInstance().messages.get("Arena-Win");
      String var26 = (String)Customization.getInstance().titles.get("Win");

      for (Player var30 : Utils.getPlayers(var1)) {
         var30.sendMessage(var24);
         PlayerData var10 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var30.getName());
         if (this.mode.equals(ArenaMode.Plunder)) {
            var10.plunder_wins++;
            AchievementsManager.getInstance().checkPlayer(var30, AchievementType.PLUNDER_WINS, var10.plunder_wins);
            RanksManager.getInstance().addExp(var30, Config.getInstance().expPerPlunderWin);
         } else {
            var10.normal_wins++;
            AchievementsManager.getInstance().checkPlayer(var30, AchievementType.NORMAL_WINS, var10.normal_wins);
            RanksManager.getInstance().addExp(var30, Config.getInstance().expPerNormalWin);
         }

         int var11 = Config.getInstance().coinsPerWin * var10.modifier;
         var10.addCoins(var30, var11);
         var30.sendMessage(
            ((String)Customization.getInstance().messages.get("Arena-Win-Prize")).replace("%coins%", String.valueOf(var11))
               + ChatColor.YELLOW
               + (var10.modifier > 1 ? " (x" + var10.modifier + ")" : "")
         );

         for (String var13 : Config.getInstance().executed_commands_player_win) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var13.replace("%player%", var30.getName()).replace("%world%", this.worldName));
         }

         TitleManager.getInstance().sendTitle(var30, var26);
         if (CustomMapsManager.getInstance().isRegistered("winnerMap")) {
            var30.getInventory().setItem(0, CustomMapsManager.getInstance().getRegisteration("winnerMap"));
         }

         if (!var10.selectedCelebration.isEmpty()) {
            CelebrationsManager.getInstance().startCelebration(var10.selectedCelebration, var30, this);
         }

         int var31 = var10.getTotalBounty();
         if (var31 > 0) {
            var10.addCoins(var30, var31);
            var30.sendMessage(((String)Customization.getInstance().messages.get("Bounty-Survive")).replace("%bounty%", String.valueOf(var31)));
            var10.bounties.clear();
         }
      }

      final ArrayList var29 = var1;
      this.task = (new BukkitRunnable() {
         int seconds = Config.getInstance().endingLength;
         boolean fireworks = true;

         public void run() {
            Arena.this.scoreboard.update("%current_event_timer%", this.seconds);
            if (this.fireworks) {
               for (Player var2 : Arena.this.getPlayers()) {
                  if (var29.contains(var2.getName())) {
                     Utils.fireWorkEffect(var2.getLocation(), true);
                  }
               }

               this.fireworks = false;
            } else {
               this.fireworks = true;
            }

            if (this.seconds == 0) {
               Arena.this.stop();
            }

            this.seconds--;
         }
      }).runTaskTimer(BattleRoyaleX.getInstance(), 0L, 20L);
   }

   public void stop() {
      List<Player> var1 = this.getAllPlayers();
      Bukkit.getPluginManager().callEvent(new BRArenaStopEvent(this.name, this.worldName, var1));

      for (String var3 : Config.getInstance().executed_commands_arena_end) {
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var3.replace("%world%", this.worldName).replace("%arena%", this.name));
      }

      if (BattleRoyaleX.getInstance().isOneGamePerServer()) {
         ByteArrayDataOutput var9 = ByteStreams.newDataOutput();
         var9.writeUTF("Connect");
         var9.writeUTF(Config.getInstance().bungee_mode_hub);

         for (Player var13 : var1) {
            var13.sendPluginMessage(BattleRoyaleX.getInstance(), "BungeeCord", var9.toByteArray());
         }

         (new BukkitRunnable() {
            public void run() {
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), BattleRoyaleX.getInstance().getConfig().getString("Bungee-Mode.restart-command"));
            }
         }).runTaskLater(BattleRoyaleX.getInstance(), 20L);
      } else {
         this.cancelTask();
         this.createScoreboard(true);
         if (this.scenario != null) {
            this.scenario.unregisterArena(this);
         }

         for (Player var10 : var1) {
            PlayerData var4 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var10.getName());
            var4.arena = null;
            BattleRoyaleX.getInstance().lobbyPlayers.add(var10.getName());
            if (!BattleRoyaleX.getInstance().protectedPlayers.contains(var10.getName())) {
               BattleRoyaleX.getInstance().protectedPlayers.add(var10.getName());
            }

            for (Player var6 : var1) {
               var10.showPlayer(var6);
               var6.showPlayer(var10);
            }

            var10.setMaxHealth(20.0);
            var4.enterLobby(var10);
            var4.cancelBounty(var10.getName());
            if (BattleRoyaleX.getInstance().clickableTextManager != null && Config.getInstance().playAgainMessage) {
               var10.sendMessage(Config.getInstance().playAgainHeader);

               for (String var15 : Config.getInstance().playAgainContent) {
                  if (var15.equals(" ")) {
                     var10.sendMessage(var15);
                  } else {
                     try {
                        BattleRoyaleX.getInstance()
                           .clickableTextManager
                           .sendMessage(var10, var15, "battleroyalex autojoin " + (this.teamSize == 1 ? "SOLO" : "TEAM"));
                     } catch (Exception var16x) {
                        var10.sendMessage(var15);
                     }
                  }
               }

               var10.sendMessage(Config.getInstance().playAgainFooter);
            }
         }

         this.players.clear();
         this.spectators.clear();
         this.killers.clear();
         this.scoreboard.update("%date%", this.date = ChatColor.GRAY + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
         this.activeRenderers.clear();
         this.deathChests.clear();
         this.extractionChests.clear();
         this.votesManager.reset();
         this.instantiateTeamsList();
         this.worldBorder = this.createBorder(Bukkit.getWorld(this.worldName), this.mapCenterX, this.mapCenterZ, this.worldBorderInitialSize);
         this.rollback();
         this.state = this.enabled ? ArenaState.WAITING : ArenaState.DISABLED;
         this.updateItem();
         if (BattleRoyaleX.getInstance().isOneGamePerServer()) {
            for (Player var11 : Utils.getOnlinePlayers()) {
               this.join(var11);
            }
         }
      }
   }

   public void kill(Player var1, Arena.PAData var2, String var3) {
      List<Player> var4 = this.getAllPlayers();

      for (Player var6 : var4) {
         var6.sendMessage(var3);
      }

      Bukkit.getPluginManager().callEvent(new BRPlayerDeathEvent(var1, this.name, this.worldName, var4));
      if (this.mode.equals(ArenaMode.Normal)) {
         var2.downed_chicken.remove();
         var2.downed_chicken = null;
         BattleRoyaleX.getInstance().protectedPlayers.add(var1.getName());
         Arena.CustomTeam var8 = var2.team;
         var8.players.remove(var1.getName());
         if (var8.players.isEmpty()) {
            this.aliveTeams.remove(var8);
         }

         this.players.remove(var1.getName());
         this.spectators.add(var1.getName());
         TitleManager.getInstance().sendTitle(var1, (String)Customization.getInstance().titles.get("Player-Spectate"));
         this.scoreboard.update("%players%", this.players.size());
         if (!Config.getInstance().useGameMode3ForSpectators) {
            for (Player var7 : var4) {
               var7.hidePlayer(var1);
            }
         }

         PlayerData var10 = (PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName());
         var10.games_played++;
         AchievementsManager.getInstance().checkPlayer(var1, AchievementType.GAMES_PLAYED, var10.games_played);
         if (this.aliveTeams.size() < 2 && !this.state.equals(ArenaState.ENDING)) {
            this.finish();
         }
      } else {
         var2.armor = Config.getInstance().initialArmor;
         var2.updateDataString();
      }
   }

   public Location getRandomHighLocation() {
      World var1 = Bukkit.getWorld(this.worldName);
      int var2 = (int)(this.worldBorder.getSize() / 2.0);
      int var3 = this.mapCenterX - var2;
      int var4 = this.mapCenterX + var2;
      int var5 = this.mapCenterZ - var2;
      int var6 = this.mapCenterZ + var2;
      int var7 = Utils.random.nextInt(var4 - var3 + 1);
      int var8 = Utils.random.nextInt(var6 - var5 + 1);
      return new Location(var1, var3 + var7, 256.0, var5 + var8);
   }

   public void startBus(final Location[] var1, final List<Player> var2) {
      final float var3 = 1.0F;
      final Vector var4 = var1[1].clone().subtract(var1[0]).toVector().normalize().setY(0).multiply(var3);
      final ArrayList<LivingEntity> var5 = new ArrayList<>();

      for (Player var7 : var2) {
         var7.teleport(var1[0]);
         LivingEntity var8 = (LivingEntity)var7.getWorld().spawnEntity(var7.getLocation(), EntityType.SHEEP);
         var8.setMetadata("BRX_BUS", new FixedMetadataValue(BattleRoyaleX.getInstance(), null));
         var5.add(var8);

         for (Player var10 : var2) {
            var7.hidePlayer(var10);
         }
      }

      final LivingEntity var11 = (LivingEntity)var1[0].getWorld().spawnEntity(var1[0], EntityType.SHEEP);
      var11.setMetadata("BRX_BUS", new FixedMetadataValue(BattleRoyaleX.getInstance(), null));
      var5.add(var11);
      PotionEffect var12 = new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 999999);

      for (LivingEntity var16 : var5) {
         var16.addPotionEffect(var12);
      }

      if (BattleRoyaleX.getInstance().offHandAvailable) {
         int var14 = 0;

         for (Player var19 : var2) {
            ((LivingEntity)var5.get(var14++)).setPassenger(var19);
         }
      } else {
         (new BukkitRunnable() {
            public void run() {
               int var1x = 0;

               for (Player var3x : var2) {
                  ((LivingEntity)var5.get(var1x++)).setPassenger(var3x);
               }
            }
         }).runTaskLater(BattleRoyaleX.getInstance(), 2L);
      }

      Location var15 = var11.getLocation().setDirection(var4);
      final Bus var18 = new Bus(var15);
      (new BukkitRunnable() {
            int counter = 0;
            final int initialDistance = (int)var1[1].distanceSquared(var1[0]);
            final Vector busAddedVector = var4.clone().multiply(1.0F / var3).add(new Vector(0.0, 0.5, 0.0));
            final float correctionFactor = Utils.random.nextFloat() * 0.08F;

            public void run() {
               for (Entity var2 : var5) {
                  var2.setVelocity(var4);
               }

               this.counter++;
               if (this.counter == 10) {
                  Bukkit.getWorld(Arena.this.worldName).playSound(var11.getLocation(), SoundsManager.WOLF_SHAKE, 1.0F, 1.0F);
                  if (TitleManager.getInstance().isActionBarEnabled()) {
                     for (Entity var6 : var5) {
                        if (var6.getPassenger() != null) {
                           Player var3x = (Player)var6.getPassenger();
                           TitleManager.getInstance()
                              .sendForcedActionBar(
                                 var3x, ChatColor.YELLOW + "Press shift to deploy!", (PlayerData)BattleRoyaleX.getInstance().playerData.get(var3x.getName())
                              );
                        }
                     }
                  }

                  var18.moveTo(
                     var11.getLocation()
                        .add(this.busAddedVector)
                        .setDirection(var4.clone().add(new Vector(this.correctionFactor, this.correctionFactor, this.correctionFactor)))
                  );
                  if (var11.isValid() && !(var11.getLocation().distanceSquared(var1[0]) > this.initialDistance)) {
                     this.counter = 0;
                  } else {
                     this.cancel();

                     for (Entity var7 : var5) {
                        var7.eject();
                        var7.remove();
                     }

                     var18.destroy();
                  }
               } else {
                  var18.moveTo(var11.getLocation().add(this.busAddedVector).setDirection(var4));
               }
            }
         })
         .runTaskTimer(BattleRoyaleX.getInstance(), 1L, 1L);
   }

   public void constructExtractionPoint(Location var1) {
      Block var2 = var1.getBlock();
      if (!this.modifiedBlocks.containsKey(var2.getLocation())) {
         this.modifiedBlocks.put(var2.getLocation(), var2.getState());
      }

      var2.setType(Material.CHEST);
      this.extractionChests.add(var2.getLocation());
      new Bus(var2.getLocation().add(0.0, 10.0, 0.0));
      Material var3 = XMaterial.ACACIA_FENCE.parseMaterial();

      for (int var4 = 1; var4 < 10; var4++) {
         Block var5 = var2.getLocation().add(0.0, var4, 0.0).getBlock();
         if (!this.modifiedBlocks.containsKey(var5.getLocation())) {
            this.modifiedBlocks.put(var5.getLocation(), var2.getState());
         }

         var5.setType(var3);
      }
   }

   public void spawnSupplyDrop(final Location var1) {
      ArrayList<Location> var2 = new ArrayList<>();

      for (int var3 = 0; var3 < 10; var3++) {
         for (int var4 = 0; var4 < 10; var4++) {
            if (Utils.random.nextInt(15) == 0) {
               var2.add(var1.clone().add(new Vector(var3, 0, var4)));
            }
         }
      }

      final Iterator var5 = var2.iterator();
      (new BukkitRunnable() {
            public void run() {
               if (var5.hasNext() && Arena.this.state.equals(ArenaState.INGAME)) {
                  FallingBlock var1x = var1.getWorld()
                     .spawnFallingBlock(((Location)var5.next()).add(0.0, Config.getInstance().carepackageHeight, 0.0), Material.SAND, (byte)0);
                  var1x.setDropItem(false);
                  var1x.setMetadata("BRX_SUPPLY-DROP", new FixedMetadataValue(BattleRoyaleX.getInstance(), Arena.this.name));
               } else {
                  this.cancel();
               }
            }
         })
         .runTaskTimer(BattleRoyaleX.getInstance(), 0L, 20L);
   }

   public List<Entry<String, Integer>> getTopKillers() {
      int var1 = 1;

      while (this.killers.size() < 3) {
         this.killers.put("NA-" + var1++, 0);
      }

      LinkedList var2 = new LinkedList(this.killers.entrySet());
      Collections.sort(var2, new Comparator<Entry<String, Integer>>() {
         public int compare(Entry<String, Integer> var1, Entry<String, Integer> var2x) {
            return (Integer)var2x.getValue() - (Integer)var1.getValue();
         }
      });
      return var2;
   }

   public List<Player> getEnemies(Player var1) {
      ArrayList<Player> var2 = new ArrayList<>();
      Arena.CustomTeam var3 = ((Arena.PAData)this.players.get(var1.getName())).team;

      for (Player var5 : this.getPlayers()) {
         if (!var3.players.contains(var5.getName())) {
            var2.add(var5);
         }
      }

      return var2;
   }

   public void rollback() {
      for (Location var2 : this.openedChests) {
         if (var2.getBlock().getType().equals(Material.CHEST)) {
            Chest var3 = (Chest)var2.getBlock().getState();
            var3.getInventory().clear();
            if (Config.getInstance().realisticChests) {
               RealisticItems.getInstance().playChestAnimation(var2.getBlock(), false);
            }
         }
      }

      this.openedChests.clear();

      for (BlockState var11 : this.modifiedBlocks.values()) {
         var11.update(true);
      }

      this.modifiedBlocks.clear();
      if (BattleRoyaleX.getInstance().CorpseRebornEnabled) {
         CorpseAPI.removeAllCorpses(Bukkit.getWorld(this.worldName));
      }

      for (VehicleEntity var12 : this.spawnedVehicles) {
         var12.deconstruct(null, null);
      }

      this.spawnedVehicles.clear();
      if (Config.getInstance().clearEntitiesOnGameEnd) {
         for (Entity var13 : Bukkit.getWorld(this.worldName).getEntities()) {
            if (!var13.getType().equals(EntityType.PLAYER) && !Config.getInstance().clearEntitiesOnGameEndExceptions.contains(var13.getType().name())) {
               var13.remove();
            }
         }
      }

      for (Entity var14 : Bukkit.getWorld(this.worldName).getEntities()) {
         for (String var6 : BRX_ENTITIES_METADATA) {
            if (var14.hasMetadata(var6)) {
               var14.remove();
            }
         }
      }
   }

   public void sendDataBar(Player var1, boolean var2) {
      if (var2) {
         TitleManager.getInstance()
            .sendForcedActionBar(
               var1, ((Arena.PAData)this.players.get(var1.getName())).getDataString(), (PlayerData)BattleRoyaleX.getInstance().playerData.get(var1.getName())
            );
      } else {
         TitleManager.getInstance().sendSoftActionBar(var1, ((Arena.PAData)this.players.get(var1.getName())).getDataString());
      }
   }

   public class CustomTeam {
      public ArrayList<String> players;
      public int name;
      public int id;
      public int slot;
      public int kills;
      public int cash;

      public CustomTeam(int var2, int var3, int var4) {
         this.name = var2;
         this.id = var3;
         this.slot = var4;
         this.players = new ArrayList();
         this.kills = 0;
      }

      public void updateItem(SmartInventory var1) {
         ItemStackBuilder var2 = new ItemStackBuilder(Material.CHEST)
            .setName("Team " + this.name)
            .addLore(new String[]{ChatColor.GRAY + " - Players: " + ChatColor.WHITE + this.players.size() + "/" + Arena.this.teamSize, " "});

         for (String var4 : this.players) {
            var2.addLore(new String[]{ChatColor.GRAY + " - " + var4});
         }

         var1.setItem(this.id, this.slot, var2.build());
      }
   }

   public class PAData {
      public int votingCategory;
      public Arena.CustomTeam team;
      public int cash;
      double armor;
      public int killstreak;
      public LivingEntity downed_chicken;
      public ArrayList<ItemStack> customCrafted = new ArrayList();
      public ItemStack[] downedItems;
      public ItemStack[] downedArmor;
      public long lastChannelingTime;
      public BukkitTask channelingTask;
      String dataString;

      public PAData(Arena.CustomTeam var2) {
         this.team = var2;
         this.armor = Config.getInstance().initialArmor;
         this.cash = 200;
         this.updateDataString();
      }

      public double getArmor() {
         return this.armor;
      }

      public void setArmor(double var1) {
         this.armor = var1;
         this.updateDataString();
      }

      public void updateDataString() {
         this.dataString = (String)Customization.getInstance().actionBars.get("Arena-Player-Data");
         if (this.dataString.contains("%armor%")) {
            this.dataString = this.dataString.replace("%armor%", Utils.getArmorString(this.armor, Config.getInstance().maxArmor));
         }

         if (this.dataString.contains("%cash%")) {
            this.dataString = this.dataString.replace("%cash%", String.valueOf(this.cash));
         }
      }

      public String getDataString() {
         return this.dataString;
      }
   }
}
