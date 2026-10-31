package me.wazup.battleroyalex;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.shampaggon.crackshot.CSUtility;
import emanondev.itemedit.ItemEdit;
import emanondev.itemedit.storage.ServerStorage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import me.wazup.battleroyalex.Arena.PAData;
import me.wazup.battleroyalex.Metrics.SimplePie;
import me.wazup.battleroyalex.commands.MainCommand;
import me.wazup.battleroyalex.custommaps.CustomMapsManager;
import me.wazup.battleroyalex.custommaps.WinnersMapRenderer;
import me.wazup.battleroyalex.holograms.GeneralizedHologramsManager;
import me.wazup.battleroyalex.holograms.GeneralizedHologramsManager.HologramProvider;
import me.wazup.battleroyalex.listeners.GUIListener;
import me.wazup.battleroyalex.listeners.PlayerListener;
import me.wazup.battleroyalex.listeners.QAListener;
import me.wazup.battleroyalex.listeners.SignsListener;
import me.wazup.battleroyalex.listeners.WorldListener;
import me.wazup.battleroyalex.managers.AchievementsManager;
import me.wazup.battleroyalex.managers.CelebrationsManager;
import me.wazup.battleroyalex.managers.ClickableTextManager;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.DefaultsManager;
import me.wazup.battleroyalex.managers.FilesManager;
import me.wazup.battleroyalex.managers.LoadoutsManager;
import me.wazup.battleroyalex.managers.MySQL;
import me.wazup.battleroyalex.managers.NotificationsManager;
import me.wazup.battleroyalex.managers.RanksManager;
import me.wazup.battleroyalex.managers.RecipeManager;
import me.wazup.battleroyalex.managers.SoundsManager;
import me.wazup.battleroyalex.managers.TitleManager;
import me.wazup.battleroyalex.placeholderhooks.MVdWPlacholderHook;
import me.wazup.battleroyalex.placeholderhooks.PlaceholderAPIHook;
import me.wazup.battleroyalex.scenarios.ScenarioManager;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.shop.Kit;
import me.wazup.battleroyalex.shop.MysteryBox;
import me.wazup.battleroyalex.utils.Broadcaster;
import me.wazup.battleroyalex.utils.ChestType;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.RealisticItems;
import me.wazup.battleroyalex.utils.SmartInventory;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.ArenaMode;
import me.wazup.battleroyalex.utils.Enums.Rarity;
import me.wazup.battleroyalex.utils.Enums.Stat;
import me.zombie_striker.qav.Main;
import me.zombie_striker.qav.VehicleEntity;
import me.zombie_striker.qav.api.QualityArmoryVehicles;
import me.zombie_striker.qav.vehicles.AbstractVehicle;
import me.zombie_striker.qg.api.QualityArmory;
import me.zombie_striker.qg.guns.Gun;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;

public class BattleRoyaleX extends JavaPlugin {
   private static BattleRoyaleX instance;

   // ---- TaCZ (Timeless and Classy Guns, Forge mod) reflection hook ----
   // The plugin runs on ArcLight (Forge+Bukkit hybrid), so we cannot compile against
   // the mod's classes: everything is resolved at runtime through reflection.
   public Object taczApi;
   public Constructor<?> resourceLocationCtor;
   public ArrayList<String> players = new ArrayList();
   public ArrayList<String> lobbyPlayers = new ArrayList();
   public ArrayList<String> protectedPlayers = new ArrayList();
   public ArrayList<Party> parties;
   private ArrayList<String> vehicles;
   public HashMap<String, PlayerData> playerData = new HashMap();
   public HashMap<Location, Integer> joinSigns;
   public HashMap<Location, Integer> topSigns;
   public HashMap<String, Arena> arenas = new HashMap();
   public HashMap<String, Kit> kits;
   public HashMap<String, Celebration> celebrations;
   public HashMap<Integer, Integer> shoppingStationItems;
   public HashMap<String, ChestType> chests;
   public Inventory profileInventory;
   public Inventory kitOptionsInventory;
   public Inventory celebrationOptionsInventory;
   public Inventory partyMenu;
   public Inventory quitInventory;
   public Inventory spectatorMenu;
   public Inventory votingInventory;
   public Inventory shoppingStation;
   public Inventory bountiesInventory;
   public SmartInventory kitsShop;
   public SmartInventory arenaSelector;
   public SmartInventory partySelector;
   public SmartInventory playerInviter;
   public Material shoppingStationMaterial;
   public Location lobbyLocation;
   public BukkitTask savingTask;
   public BukkitTask leaderboard_updater;
   public long leaderboard_updater_time = 0L;
   BukkitTask scoreboardTitleAnimationTask;
   public boolean availableUpdate;
   public String downloadURL = "https://bit.ly/3sAoWkC";
   public GeneralizedHologramsManager hologramsManager;
   public MySQL mySQL;
   public ClickableTextManager clickableTextManager;
   Economy vault;
   public boolean QualityArmoryEnabled;
   public boolean QualityArmoryVehiclesEnabled;
   public boolean CorpseRebornEnabled;
   public boolean ItemsAdderEnabled;
   public boolean CrackShotPlusEnabled;
   public CSUtility crackShotAPI;
   public Class<?> mmoItems;
   public Object mmoItemsInstance;
   public ServerStorage itemEdit;
   public Class<?> weaponMechanicsAPI;
   public Method setTypeAndDataMethod;
   public boolean below1_10;
   public boolean offHandAvailable;

   public void onEnable() {
      instance = this;
      MainCommand v1 = new MainCommand();
      this.getCommand("taxbattle").setExecutor(v1);
      this.getCommand("taxbattle").setTabCompleter(v1);
      new CustomMapsManager();
      new RealisticItems();
      new SoundsManager();
      new Broadcaster();
      if (this.getConfig().getBoolean("Check-For-General-Notifications")) {
         new NotificationsManager();
      }

      ItemStackBuilder.loadMethods();

      try {
         this.clickableTextManager = new ClickableTextManager();
      } catch (Exception var10) {
         this.clickableTextManager = null;
      }

      if (!XMaterial.supports(13)) {
         try {
            this.setTypeAndDataMethod = Block.class.getMethod("setTypeIdAndData", new Class[]{int.class, byte.class, boolean.class});
         } catch (Exception var9) {
         }
      }

      try {
         LivingEntity.class.getMethod("setAI", new Class[]{boolean.class});
         this.below1_10 = false;
      } catch (NoSuchMethodException | SecurityException var8) {
         this.below1_10 = true;
      }

      try {
         EntityEquipment.class.getMethod("setItemInMainHand", new Class[]{ItemStack.class});
         this.offHandAvailable = true;
      } catch (NoSuchMethodException | SecurityException var7) {
         this.offHandAvailable = false;
      }

      this.QualityArmoryEnabled = Bukkit.getPluginManager().isPluginEnabled("QualityArmory");
      if (this.QualityArmoryEnabled && this.getConfig().getBoolean("Disable-Quality-Armory-Recoil")) {
         for (Gun v3 : QualityArmory.getGuns()) {
            v3.setRecoil(0.0);
         }
      }

      this.QualityArmoryVehiclesEnabled = Bukkit.getPluginManager().isPluginEnabled("QualityArmoryVehicles2");
      this.CorpseRebornEnabled = Bukkit.getPluginManager().isPluginEnabled("CorpseReborn");
      this.ItemsAdderEnabled = Bukkit.getPluginManager().isPluginEnabled("ItemsAdder");
      this.CrackShotPlusEnabled = Bukkit.getPluginManager().isPluginEnabled("CrackShotPlus");
      if (Bukkit.getPluginManager().isPluginEnabled("WeaponMechanics")) {
         try {
            this.weaponMechanicsAPI = Class.forName("me.deecaad.weaponmechanics.WeaponMechanicsAPI");
         } catch (ClassNotFoundException var6) {
            Utils.error("Could not hook to WeaponMechanicsAPI");
            var6.printStackTrace();
         }
      }

      if (Bukkit.getPluginManager().isPluginEnabled("CrackShot")) {
         this.crackShotAPI = new CSUtility();
      }

      // TaCZ is a Forge mod: it has no Bukkit plugin to check, so we always try to
      // resolve its API classes through reflection (no-op if the mod is not installed).
      Utils.initTaczHook(this);

      if (Bukkit.getPluginManager().isPluginEnabled("ItemEdit")) {
         this.itemEdit = ItemEdit.get().getServerStorage();
      }

      if (Bukkit.getPluginManager().isPluginEnabled("MMOItems")) {
         try {
            this.mmoItems = Class.forName("net.Indyuce.mmoitems.MMOItems");
            this.mmoItemsInstance = this.mmoItems.getField("plugin").get(null);
         } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException var5) {
            Utils.error("Could not hook to MMOItems");
            var5.printStackTrace();
         }
      }

      this.mainSetup();
      me.wazup.battleroyalex.utils.GradientTitle.start();
      Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
      Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
      Bukkit.getPluginManager().registerEvents(new SignsListener(), this);
      Bukkit.getPluginManager().registerEvents(new WorldListener(), this);
      if (this.QualityArmoryEnabled) {
         Bukkit.getPluginManager().registerEvents(new QAListener(), this);
      }

      Object var11 = Customization.getInstance().prefix;
      if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
         Bukkit.getConsoleSender().sendMessage(var11 + "Found PlaceholderAPI, Hooked: " + new PlaceholderAPIHook().register());
      }

      if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
         new MVdWPlacholderHook();
         Bukkit.getConsoleSender().sendMessage(var11 + "Found MVdWPlaceholderAPI, Hooked: true");
      }

      for (Player v4 : Utils.getOnlinePlayers()) {
         this.playerData.put(v4.getName(), new PlayerData(v4));
      }

      this.setupMetrics();
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "=============================================");
      Bukkit.getConsoleSender().sendMessage("" + var11 + ChatColor.GREEN + "Plugin has been enabled (v" + this.getDescription().getVersion() + ")");
      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "=============================================");
   }

   public void onDisable() {
      if (!Config.getInstance().useGameMode3ForSpectators) {
         for (Arena var2 : this.arenas.values()) {
            for (Player var4 : var2.getAllPlayers()) {
               for (Player var6 : var2.getSpectators()) {
                  var4.showPlayer(var6);
               }
            }
         }
      }

      for (Arena var9 : this.arenas.values()) {
         var9.rollback();
      }

      Customization var8 = Customization.getInstance();

      for (Player var12 : Utils.getPlayers(this.players)) {
         ((PlayerData)this.playerData.get(var12.getName())).restoreData(var12);
         var12.sendMessage((String)var8.messages.get("Player-Leave"));
      }

      for (Player var13 : Utils.getPlayers(this.playerData.keySet())) {
         ((PlayerData)this.playerData.get(var13.getName())).save(var13);
      }

      if (this.hologramsManager != null) {
         this.hologramsManager.deleteLeaderboardHologram();
      }

      Bukkit.getConsoleSender().sendMessage(var8.prefix + "Plugin has been disabled!");
   }

   public static BattleRoyaleX getInstance() {
      return instance;
   }

   public void join(Player var1) {
      this.players.add(var1.getName());
      this.lobbyPlayers.add(var1.getName());
      this.protectedPlayers.add(var1.getName());
      PlayerData var2 = (PlayerData)this.playerData.get(var1.getName());
      var2.saveData(var1);
      var2.enterLobby(var1);
      this.updateJoinSigns();
      if (Config.getInstance().scoreboardTitleAnimationEnabled && this.scoreboardTitleAnimationTask == null) {
         this.scoreboardTitleAnimationTask = (new BukkitRunnable() {
            int index = 0;

            public void run() {
               String var1x = (String)Config.getInstance().scoreboardTitleAnimationFrames.get(this.index);
               if (++this.index >= Config.getInstance().scoreboardTitleAnimationFrames.size()) {
                  this.index = 0;
               }

               for (String var4 : (List<String>)BattleRoyaleX.this.lobbyPlayers.clone()) {
                  PlayerData var5 = (PlayerData)BattleRoyaleX.this.playerData.get(var4);
                  if (var5.lobbyScoreboard != null) {
                     var5.lobbyScoreboard.setName(var1x);
                  }
               }

               for (Arena var7 : BattleRoyaleX.this.arenas.values()) {
                  var7.scoreboard.setName(var1x);
               }
            }
         }).runTaskTimer(this, Config.getInstance().scoreboardTitleAnimationInterval, Config.getInstance().scoreboardTitleAnimationInterval);
      }

      if (this.isOneGamePerServer()) {
         ((Arena)this.arenas.values().toArray()[0]).join(var1);
      } else {
         var1.sendMessage((String)Customization.getInstance().messages.get("Player-Join"));
      }

      ItemStack var3 = Utils.getSkull(var1.getName(), ChatColor.AQUA + var1.getName());

      for (int var4 = 0; var4 < this.playerInviter.getSize(); var4++) {
         if (this.playerInviter.addItem(var4, var3)) {
            return;
         }
      }

      int var5 = this.playerInviter.addInventory(ChatColor.RED + "List #" + (this.playerInviter.getSize() + 1));
      this.playerInviter.setItem(var5, 49, (ItemStack)Customization.getInstance().items.get("Back"));
      this.playerInviter.addItem(var5, var3);
   }

   public void leave(Player var1, boolean var2) {
      String var3 = var1.getName();
      PlayerData var4 = (PlayerData)this.playerData.get(var3);
      if (this.isOneGamePerServer()) {
         var2 = true;
      }

      if (var4.arena != null) {
         var4.arena.leave(var1);
         if (!var2) {
            return;
         }
      }

      this.players.remove(var3);
      this.lobbyPlayers.remove(var3);
      this.protectedPlayers.remove(var3);
      var4.restoreData(var1);
      if (var4.party != null) {
         var4.party.leave(var1);
      }

      var1.sendMessage((String)Customization.getInstance().messages.get("Player-Leave"));
      this.updateJoinSigns();
      if (Config.getInstance().bungee_mode_enabled) {
         ByteArrayDataOutput var5 = ByteStreams.newDataOutput();
         var5.writeUTF("Connect");
         var5.writeUTF(Config.getInstance().bungee_mode_hub);
         var1.sendPluginMessage(this, "BungeeCord", var5.toByteArray());
      }

      if (this.scoreboardTitleAnimationTask != null && this.players.isEmpty()) {
         this.scoreboardTitleAnimationTask.cancel();
         this.scoreboardTitleAnimationTask = null;
      }

      for (int var11 = 0; var11 < this.playerInviter.getSize(); var11++) {
         for (int var9 : SmartInventory.slots) {
            ItemStack var10 = this.playerInviter.getItem(var11, var9);
            if (var10 != null && ChatColor.stripColor(var10.getItemMeta().getDisplayName()).equals(var3)) {
               this.playerInviter.removeItem(var11, var9);
               return;
            }
         }
      }
   }

   public void autojoin(Player var1, String var2) {
      PlayerData var3 = (PlayerData)instance.playerData.get(var1.getName());
      int var4 = 1;
      if (var3.party != null) {
         var4 = var3.party.players.size();
         if (!var3.party.leaderName.equals(var1.getName())) {
            var1.sendMessage((String)Customization.getInstance().messages.get("Party-Must-Be-Leader"));
            return;
         }

         for (String var6 : var3.party.players) {
            if (!instance.lobbyPlayers.contains(var6)) {
               var1.sendMessage(((String)Customization.getInstance().messages.get("Party-Player-Not-Available")).replace("%member%", var6));
               return;
            }
         }
      }

      ArrayList<Arena> var10 = new ArrayList<>();

      for (Arena var7 : this.arenas.values()) {
         if (var7.enabled && var7.state.AVAILABLE() && var7.players.size() + var4 <= var7.maxPlayers) {
            var10.add(var7);
         }
      }

      if (var2 != null && !var2.isEmpty()) {
         boolean var12 = var2.equalsIgnoreCase("solo");
         ArrayList<Arena> var14 = new ArrayList<>();
         if (var12) {
            for (Arena var18 : var10) {
               if (var18.teamSize > 1) {
                  var14.add(var18);
               }
            }
         } else {
            for (Arena var9 : var10) {
               if (var9.teamSize == 1) {
                  var14.add(var9);
               }
            }
         }

         var10.removeAll(var14);
      }

      if (var10.isEmpty()) {
         var1.sendMessage((String)Customization.getInstance().messages.get("No-Available-Arena"));
      } else {
         Arena var13 = (Arena)var10.get(0);

         for (Arena var17 : var10) {
            if (var17.players.size() > var13.players.size()) {
               var13 = var17;
            } else if (var17.players.size() == var13.players.size()) {
               var13 = Utils.random.nextBoolean() ? var17 : var13;
            }
         }

         var13.join(var1);
      }
   }

   protected void updateJoinSigns() {
      for (Location var2 : this.joinSigns.keySet()) {
         if (var2.getBlock().getState() instanceof Sign) {
            Sign var3 = (Sign)var2.getBlock().getState();
            var3.setLine(3, String.valueOf(this.players.size()));
            var3.update(true);
         }
      }
   }

   protected void delayedSetup() {
      Bukkit.getScheduler()
         .scheduleSyncDelayedTask(
            this,
            new Runnable() {
               public void run() {
                  BattleRoyaleX.this.arenas.clear();
                  File var1 = new File(BattleRoyaleX.this.getDataFolder(), "arenas");
                  if (var1.isDirectory()) {
                     for (File var5 : var1.listFiles()) {
                        if (var5.isDirectory()
                           && new File(BattleRoyaleX.this.getDataFolder() + "/arenas/" + var5.getName(), "settings.yml").exists()
                           && new File(BattleRoyaleX.this.getDataFolder() + "/arenas/" + var5.getName(), "locations.dat").exists()) {
                           new Arena(var5.getName());
                        }
                     }
                  }

                  BattleRoyaleX.this.updateArenasInventory();
                  BattleRoyaleX.this.joinSigns = new HashMap();
                  FileConfiguration var7 = FilesManager.getInstance().getConfig("signs.yml");
                  if (var7.getConfigurationSection("Signs.Join") != null && !var7.getConfigurationSection("Signs.Join").getKeys(false).isEmpty()) {
                     for (String var12 : var7.getConfigurationSection("Signs.Join").getKeys(false)) {
                        int var15 = Integer.parseInt(var12);
                        Location var6 = Utils.getLocationFromString(var7.getString("Signs.Join." + var12));
                        BattleRoyaleX.this.joinSigns.put(var6, var15);
                     }
                  }

                  BattleRoyaleX.this.updateJoinSigns();
                  BattleRoyaleX.this.topSigns = new HashMap();
                  if (var7.getConfigurationSection("Signs.Top") != null && !var7.getConfigurationSection("Signs.Top").getKeys(false).isEmpty()) {
                     for (String var13 : var7.getConfigurationSection("Signs.Top").getKeys(false)) {
                        int var16 = Integer.parseInt(var13);
                        Location var17 = Utils.getLocationFromString(var7.getString("Signs.Top." + var13));
                        if (var17 != null) {
                           BattleRoyaleX.this.topSigns.put(var17, var16);
                        }
                     }
                  }

                  if (BattleRoyaleX.this.hologramsManager != null) {
                     BattleRoyaleX.this.hologramsManager.deleteLeaderboardHologram();
                  }

                  BattleRoyaleX.this.hologramsManager = null;
                  if (Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays") || Bukkit.getPluginManager().isPluginEnabled("DecentHolograms")) {
                     HologramProvider var10;
                     if (Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
                        var10 = HologramProvider.HolographicDisplays;
                        Bukkit.getConsoleSender()
                           .sendMessage(
                              Customization.getInstance().prefix + "HolographicDisplays has been detected! HolographicDisplays features are now accessible.."
                           );
                     } else {
                        var10 = HologramProvider.DecentHolograms;
                        Bukkit.getConsoleSender()
                           .sendMessage(Customization.getInstance().prefix + "DecentHolograms has been detected! DecentHolograms features are now accessible..");
                     }

                     BattleRoyaleX.this.hologramsManager = new GeneralizedHologramsManager(var10);
                     if (BattleRoyaleX.this.getConfig().contains("Holographic-Stats")) {
                        BattleRoyaleX.this.hologramsManager
                           .setStatsLocation(Utils.getLocationFromString(BattleRoyaleX.this.getConfig().getString("Holographic-Stats")));
                     }

                     if (BattleRoyaleX.this.getConfig().contains("Holographic-Leaderboard")) {
                        BattleRoyaleX.this.hologramsManager
                           .setLeaderboardLocation(Utils.getLocationFromString(BattleRoyaleX.this.getConfig().getString("Holographic-Leaderboard")), false);
                     }
                  }

                  BattleRoyaleX.this.startLeaderboardUpdater();
                  if (BattleRoyaleX.this.getConfig().contains("Lobby")) {
                     BattleRoyaleX.this.lobbyLocation = Utils.getLocationFromString(BattleRoyaleX.this.getConfig().getString("Lobby"));
                     if (Config.getInstance().bungee_mode_enabled) {
                        for (Player var14 : Utils.getOnlinePlayers()) {
                           BattleRoyaleX.this.join(var14);
                        }
                     }
                  }

                  Bukkit.getConsoleSender()
                     .sendMessage(
                        Customization.getInstance().prefix
                           + "Loaded "
                           + ChatColor.AQUA
                           + BattleRoyaleX.this.arenas.size()
                           + ChatColor.GRAY
                           + " arena(s)"
                           + ChatColor.LIGHT_PURPLE
                           + " and "
                           + ChatColor.AQUA
                           + BattleRoyaleX.this.joinSigns.size()
                           + ChatColor.GRAY
                           + " join sign(s)"
                           + ChatColor.LIGHT_PURPLE
                           + " and "
                           + ChatColor.AQUA
                           + BattleRoyaleX.this.kits.size()
                           + ChatColor.GRAY
                           + " kit(s)"
                           + ChatColor.LIGHT_PURPLE
                           + " and "
                           + ChatColor.AQUA
                           + BattleRoyaleX.this.kitsShop.getSize()
                           + ChatColor.GRAY
                           + " shop(s)"
                           + ChatColor.LIGHT_PURPLE
                           + " and "
                           + ChatColor.AQUA
                           + AchievementsManager.getInstance().getSize()
                           + ChatColor.GRAY
                           + " achievement(s)"
                           + ChatColor.LIGHT_PURPLE
                           + " and "
                           + ChatColor.AQUA
                           + Broadcaster.getInstance().messages.size()
                           + ChatColor.GRAY
                           + " broadcaster message(s)"
                     );
               }
            },
            this.getConfig().getInt("Load-Delay-Ticks")
         );
   }

   public void mainSetup() {
      new FilesManager();
      FileConfiguration var1 = FilesManager.getInstance().getConfig("customization.yml");
      new Customization(var1);
      // TaxBattle: carica i frame del titolo gradient animato ("TaxPhobia")
      me.wazup.battleroyalex.utils.GradientTitle.load(var1);
      new Config();
      new RanksManager();
      new RecipeManager();
      new CelebrationsManager();
      new ScenarioManager();
      this.arenaSelector = new SmartInventory(ChatColor.RED + "Arenas");
      this.arenaSelector.addInventory(ChatColor.RED + "List #1");
      this.arenaSelector.setItem(0, 49, (ItemStack)Customization.getInstance().items.get("Autojoin"));
      this.delayedSetup();
      this.kits = new HashMap();
      FileConfiguration var2 = FilesManager.getInstance().getConfig("kits.yml");
      if (var2.getConfigurationSection("Kits") == null || var2.getConfigurationSection("Kits").getKeys(false).isEmpty()) {
         DefaultsManager.createDefaultKits(var2);
      }

      String[] var3 = new String[]{"boots", "leggings", "chestplate", "helmet"};

      for (String var5 : var2.getConfigurationSection("Kits").getKeys(false)) {
         String var6 = "Kits." + var5 + ".";
         String varItemTok = var2.getString(var6 + "item");
         ItemStack var7 = Utils.getItemStack(varItemTok, false, false);
         if (var7 == null && varItemTok != null && !varItemTok.isEmpty()) {
            // Token failed to parse (missing mod/id unknown): fall back to a plain compass so the
            // kit icon never crashes plugin enable.
            Bukkit.getConsoleSender()
               .sendMessage(
                  Customization.getInstance().prefix
                     + ChatColor.RED
                     + "Un item in kits.yml nel kit: "
                     + var5
                     + " non e' corretto! il formato e': "
                     + varItemTok
               );
            var7 = new ItemStack(Material.PAPER);
         }
         String var8 = var2.getString(var6 + "permission");
         Rarity var9 = Rarity.valueOf(var2.getString(var6 + "rarity").toUpperCase());
         int var10 = var2.getInt(var6 + "value");
         ItemStack[] var11 = new ItemStack[4];

         for (int var12 = 0; var12 < var11.length; var12++) {
            String var13 = var2.getString(var6 + var3[var12]);

            try {
               ItemStack var14 = Utils.getItemStack(var13, false, true);
               var11[var12] = var14 != null ? var14 : new ItemStack(Material.AIR);
            } catch (Throwable var22) {
               Bukkit.getConsoleSender()
                  .sendMessage(
                     Customization.getInstance().prefix
                        + ChatColor.RED
                        + "Un'armatura in kits.yml nel kit: "
                        + var5
                        + " non e' corretta! il formato e': "
                        + var13
                  );
               var22.printStackTrace();
            }
         }

         ArrayList var53 = new ArrayList();

         for (String var60 : var2.getStringList(var6 + "items")) {
            try {
               ItemStack parsedItem = Utils.getItemStack(var60, true, true);
               if (parsedItem == null) {
                  throw new IllegalArgumentException("null item");
               }
               ItemStackBuilder var15 = new ItemStackBuilder(parsedItem);
               var53.add(var15.build());
            } catch (Throwable var21) {
               Bukkit.getConsoleSender()
                  .sendMessage(
                     Customization.getInstance().prefix
                        + ChatColor.RED
                        + "Un item in kits.yml nel kit: "
                        + var5
                        + " non e' corretto! il formato e': "
                        + var60
                  );
            }
         }

         if (Config.getInstance().giveAllKitsCompass) {
            var53.add(new ItemStack(Material.COMPASS));
         }

         ArrayList var58 = new ArrayList();
         if (var2.contains(var6 + "potion-effects")) {
            for (String var64 : var2.getStringList(var6 + "potion-effects")) {
               String[] var16 = var64.split(" : ");
               var58.add(new PotionEffect(PotionEffectType.getByName(var16[0]), Integer.valueOf(var16[1]) * 20, Integer.valueOf(var16[2]) - 1));
            }
         }

         Object var62 = var2.contains(var6 + "executed-commands") ? var2.getStringList(var6 + "executed-commands") : new ArrayList();
         ArrayList var65 = new ArrayList();

         for (String var17 : var2.getStringList(var6 + "description")) {
            var65.add(Utils.colorize(var17));
         }

         this.kits.put(var5.toLowerCase(), new Kit(var5, var7, var8, var53, var11, var58, (List)var62, var65, var9, var10));
      }

      this.kitsShop = new SmartInventory(ChatColor.BLUE + "Negozio");
      FileConfiguration var23 = FilesManager.getInstance().getConfig("shop.yml");
      if (var23.getConfigurationSection("Normal-Mode-Kits") == null || var23.getConfigurationSection("Normal-Mode-Kits").getKeys(false).isEmpty()) {
         DefaultsManager.createDefaultKitsShop(var23);
      }

      for (String var26 : var23.getConfigurationSection("Normal-Mode-Kits").getKeys(false)) {
         int var29 = this.kitsShop.addInventory(Utils.colorize(var26));
         this.kitsShop.setItem(var29, 49, (ItemStack)Customization.getInstance().items.get("Back"));

         for (String var37 : var23.getConfigurationSection("Normal-Mode-Kits." + var26).getKeys(false)) {
            String var42 = var23.getString("Normal-Mode-Kits." + var26 + "." + var37).toLowerCase();
            ItemStack var47 = null;
            if (this.kits.containsKey(var42)) {
               var47 = ((Kit)this.kits.get(var42)).item;
            }

            if (var47 != null) {
               this.kitsShop.setItem(var29, Integer.parseInt(var37), var47);
            }
         }
      }

      new LoadoutsManager();
      FileConfiguration var25 = FilesManager.getInstance().getConfig("chests.yml");
      if (var25.getStringList("Chests.Normal.items").isEmpty()
         || var25.getStringList("Chests.Carepackage.items").isEmpty()
         || var25.getStringList("Chests.Supply-Drops.items").isEmpty()) {
         DefaultsManager.createDefaultChests(var25);
      }

      this.chests = new HashMap();

      for (String var30 : var25.getConfigurationSection("Chests").getKeys(false)) {
         int var33 = var25.getInt("Chests." + var30 + ".min-items");
         int var38 = var25.getInt("Chests." + var30 + ".max-items");
         HashMap var43 = new HashMap();

         for (String var54 : var25.getStringList("Chests." + var30 + ".items")) {
            try {
               ItemStack var59 = Utils.getItemStack(var54.split(" :: ")[0], true, true);
               if (var59 == null) {
                  Bukkit.getConsoleSender()
                     .sendMessage(
                        Customization.getInstance().prefix
                           + ChatColor.RED
                           + "Un item in chest.yml non puo' essere caricato (forse manca una dipendenza?), il formato e': "
                           + var54
                     );
               } else {
                  int var63 = Integer.parseInt(var54.split(" :: ")[1].replace("%", ""));
                  var43.put(var59, var63);
               }
            } catch (Exception var20) {
               var20.printStackTrace();
               Bukkit.getConsoleSender()
                  .sendMessage(
                     Customization.getInstance().prefix
                        + ChatColor.RED
                        + "Un item in chest.yml non e' corretto! il formato e': "
                        + var54
                  );
            }
         }

         this.chests.put(var30, new ChestType(var33, var38, var43));
      }

      FileConfiguration var28 = FilesManager.getInstance().getConfig("shopping-station.yml");
      this.shoppingStationItems = new HashMap();
      this.shoppingStation = Bukkit.createInventory(
         null, Utils.getInventorySize(var28.getStringList("Items").size()), (String)Customization.getInstance().inventories.get("Shopping-Station")
      );
      int var31 = 0;

      for (String var39 : var28.getStringList("Items")) {
         try {
            String var44 = var39.split(" :: ")[0];
            int var49 = Integer.parseInt(var39.split(" :: ")[1]);
            ItemStack var55 = Utils.getItemStack(var44, true, true);
            if (var55 != null) {
               this.shoppingStationItems.put(var31++, var49);
               this.shoppingStation.addItem(new ItemStack[]{var55});
            }
         } catch (Exception var19) {
            var19.printStackTrace();
            Bukkit.getConsoleSender()
               .sendMessage(
                  Customization.getInstance().prefix
                     + ChatColor.RED
                     + "Un item in shopping-station.yml non e' corretto! il formato e': "
                     + var39
               );
         }
      }

      this.shoppingStationMaterial = ((XMaterial)XMaterial.matchXMaterial(var28.getString("Material")).get()).parseMaterial();
      DefaultsManager.createDefaultAchievements(FilesManager.getInstance().getConfig("achievements.yml"));
      new AchievementsManager();
      new MysteryBox();
      this.loadVehicles();
      Broadcaster.getInstance().loadMessages();
      this.partyMenu = Bukkit.createInventory(null, 9, ChatColor.RED + "Scegli un opzione!");
      Utils.cageInventory(this.partyMenu, true);
      this.partyMenu
         .setItem(
            2,
            new ItemStackBuilder(Material.BEACON)
               .setName(ChatColor.GREEN + "Crea")
               .addLore(new String[]{ChatColor.GRAY + "Clicca per creare un gruppo!"})
               .build()
         );
      this.partyMenu
         .setItem(
            6, new ItemStackBuilder(Material.CHEST).setName(ChatColor.RED + "Unisciti").addLore(new String[]{ChatColor.GRAY + "Clicca per unirti a un gruppo!"}).build()
         );
      this.parties = new ArrayList();

      for (PlayerData var40 : this.playerData.values()) {
         var40.party = null;
      }

      this.partySelector = new SmartInventory(ChatColor.BLUE + "Gruppi");
      this.partySelector.addInventory(ChatColor.RED + "List #1");
      this.partySelector.setItem(0, 49, (ItemStack)Customization.getInstance().items.get("Back"));
      this.playerInviter = new SmartInventory(ChatColor.BLUE + "Invita Giocatori");
      this.profileInventory = Bukkit.createInventory(null, 45, (String)Customization.getInstance().inventories.get("Profile-Inventory"));
      Utils.cageInventory(this.profileInventory, true);
      this.profileInventory.setItem(10, (ItemStack)Customization.getInstance().items.get("Stats"));
      this.profileInventory.setItem(12, (ItemStack)Customization.getInstance().items.get("Kits"));
      this.profileInventory.setItem(14, (ItemStack)Customization.getInstance().items.get("Loadouts"));
      this.profileInventory.setItem(16, (ItemStack)Customization.getInstance().items.get("Achievements"));
      this.profileInventory.setItem(31, (ItemStack)Customization.getInstance().items.get("Celebrations"));
      this.kitOptionsInventory = Bukkit.createInventory(null, 27, (String)Customization.getInstance().inventories.get("Kit-Options"));
      Utils.cageInventory(this.kitOptionsInventory, true);
      this.kitOptionsInventory.setItem(10, (ItemStack)Customization.getInstance().items.get("Owned-Kits"));
      this.kitOptionsInventory.setItem(16, (ItemStack)Customization.getInstance().items.get("Shop"));
      this.kitOptionsInventory.setItem(this.kitOptionsInventory.getSize() - 5, (ItemStack)Customization.getInstance().items.get("Back"));
      this.celebrationOptionsInventory = Bukkit.createInventory(null, 27, (String)Customization.getInstance().inventories.get("Celebration-Options"));
      Utils.cageInventory(this.celebrationOptionsInventory, true);
      this.celebrationOptionsInventory.setItem(10, (ItemStack)Customization.getInstance().items.get("Owned-Celebrations"));
      this.celebrationOptionsInventory.setItem(16, (ItemStack)Customization.getInstance().items.get("Shop"));
      this.celebrationOptionsInventory.setItem(this.celebrationOptionsInventory.getSize() - 5, (ItemStack)Customization.getInstance().items.get("Back"));
      this.quitInventory = Bukkit.createInventory(null, 9, (String)Customization.getInstance().inventories.get("Quit-Inventory"));
      Utils.cageInventory(this.quitInventory, true);
      this.quitInventory.setItem(2, (ItemStack)Customization.getInstance().items.get("Confirm"));
      this.quitInventory.setItem(6, (ItemStack)Customization.getInstance().items.get("Cancel"));
      FileConfiguration var36 = this.getConfig();
      this.votingInventory = Bukkit.createInventory(null, 9, (String)Customization.getInstance().inventories.get("Arena-Settings"));
      Utils.cageInventory(this.votingInventory, true);
      if (var36.getBoolean("Voting-Options.Time.enabled")) {
         this.votingInventory.setItem(2, Utils.getItemStack(var36.getString("Voting-Options.Time.item"), false, true));
      }

      if (var36.getBoolean("Voting-Options.Scenarios.enabled")) {
         this.votingInventory.setItem(4, Utils.getItemStack(var36.getString("Voting-Options.Scenarios.item"), false, true));
      }

      if (var36.getBoolean("Voting-Options.Health.enabled")) {
         this.votingInventory.setItem(6, Utils.getItemStack(var36.getString("Voting-Options.Health.item"), false, true));
      }

      this.spectatorMenu = Bukkit.createInventory(null, 9, (String)Customization.getInstance().inventories.get("Spectator-Menu"));
      ItemStack[] var41 = (ItemStack[])Config.getInstance().hotbarItems.get("Spectator");

      for (int var45 = 0; var45 < 9; var45++) {
         if (var41[var45] != null) {
            this.spectatorMenu.setItem(var45, var41[var45]);
         }
      }

      List var46 = var36.getIntegerList("Bounties");
      this.bountiesInventory = Bukkit.createInventory(
         null, Utils.getInventorySize(var46.size()), (String)Customization.getInstance().inventories.get("Bounty-Options")
      );
      Utils.cageInventory(this.bountiesInventory, true);

      for (int var50 = 0; var50 < var46.size(); var50++) {
         this.bountiesInventory.setItem(var50, new ItemStackBuilder(Material.GOLD_INGOT).setName(ChatColor.YELLOW + "" + var46.get(var50)).build());
      }

      this.vault = null;
      if (var36.getBoolean("use-Vault")
         && Bukkit.getPluginManager().getPlugin("Vault") != null
         && Bukkit.getServicesManager().getRegistration(Economy.class) != null) {
         this.vault = (Economy)Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
         Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "The plugin will be using vault economy instead of coins..");
      }

      if (Config.getInstance().bungee_mode_enabled && !Bukkit.getMessenger().isOutgoingChannelRegistered(this, "BungeeCord")) {
         Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
      }

      try {
         if (this.mySQL != null) {
            this.mySQL.close();
            this.mySQL = null;
         }

         if (var36.getBoolean("MySQL.enabled")) {
            Bukkit.getConsoleSender()
               .sendMessage(Customization.getInstance().prefix + "Attempting to connect to the database and creating a table if it doesn't exist!");
            long var51 = System.currentTimeMillis();
            this.mySQL = new MySQL(
               var36.getString("MySQL.table"),
               var36.getString("MySQL.host"),
               var36.getString("MySQL.port"),
               var36.getString("MySQL.database"),
               var36.getString("MySQL.username"),
               var36.getString("MySQL.password")
            );
            this.mySQL.setupTable();
            Bukkit.getConsoleSender()
               .sendMessage(
                  Customization.getInstance().prefix
                     + "Connection has been successfully established! it took "
                     + (System.currentTimeMillis() - var51)
                     + "ms to complete!"
               );
         }
      } catch (SQLException var18) {
         var18.printStackTrace();
      }

      if (this.savingTask != null) {
         this.savingTask.cancel();
      }

      if (var36.getBoolean("Saving-Task.Enabled")) {
         this.savingTask = (new BukkitRunnable() {
            public void run() {
               final List<Player> var1 = Utils.getOnlinePlayers();
               (new BukkitRunnable() {
                  int currentPlayer = 0;

                  public void run() {
                     if (this.currentPlayer >= var1.size()) {
                        this.cancel();
                        Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "Players stats have been saved!");
                     } else {
                        Player var1x = var1.get(this.currentPlayer);
                        if (var1x != null && var1x.isOnline()) {
                           PlayerData var2 = (PlayerData)BattleRoyaleX.this.playerData.get(var1x.getName());
                           var2.save(var1x);
                        }

                        this.currentPlayer++;
                     }
                  }
               }).runTaskTimerAsynchronously(BattleRoyaleX.instance, 0L, 1L);
            }
         }).runTaskTimer(this, var36.getInt("Saving-Task.Save-Every-Minutes") * 1200L, var36.getInt("Saving-Task.Save-Every-Minutes") * 1200L);
      } else {
         this.savingTask = null;
      }

      this.availableUpdate = false;
      this.checkUpdates(null);
      CustomMapsManager.getInstance().clear();
      if (var36.getBoolean("Custom-Maps.Winner-Map.enabled")) {
         List var52 = var36.getStringList("Custom-Maps.Winner-Map.texts");
         String var56 = var36.getString("Custom-Maps.Winner-Map.image");
         CustomMapsManager.getInstance().registerMap("winnerMap", new WinnersMapRenderer(this, var52, var56, true));
      }

      CustomMapsManager.getInstance().loadColorPalete();
   }

   public void checkUpdates(final Player var1) {
      if (this.getConfig().getBoolean("Check-For-Updates")) {
         (new BukkitRunnable() {
               public void run() {
                  try {
                     HttpURLConnection v1 = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=94613").openConnection();
                     v1.setRequestMethod("GET");
                     String v2 = new BufferedReader(new InputStreamReader(v1.getInputStream())).readLine();
                     BattleRoyaleX.this.availableUpdate = !v2.equals(BattleRoyaleX.this.getDescription().getVersion());
                     String v3 = Customization.getInstance().prefix
                        + (
                           BattleRoyaleX.this.availableUpdate
                              ? "Found a new available version! " + ChatColor.LIGHT_PURPLE + "download at " + BattleRoyaleX.this.downloadURL
                              : "Looks like you have the latest version installed!"
                        );
                     if (var1 == null) {
                        Bukkit.getConsoleSender().sendMessage(v3);

                        for (Player v5 : Utils.getOnlinePlayers()) {
                           if (v5.hasPermission("battleroyalex.admin")) {
                              v5.sendMessage(v3);
                           }
                        }
                     } else {
                        var1.sendMessage(v3);
                     }
                  } catch (IOException var6) {
                     Bukkit.getConsoleSender().sendMessage(Customization.getInstance().prefix + "Couldn't check for an available update");
                     var6.printStackTrace();
                  }
               }
            })
            .runTaskAsynchronously(this);
      }
   }

   public void updateArenasInventory() {
      for (int var1 = 0; var1 < this.arenaSelector.getSize(); var1++) {
         this.arenaSelector.clear(var1);
      }

      ArrayList<Arena> var15 = new ArrayList<>();
      ArrayList<Arena> var2 = new ArrayList<>();

      for (Arena var4 : this.arenas.values()) {
         if (var4.mode.equals(ArenaMode.Plunder)) {
            var2.add(var4);
         } else {
            var15.add(var4);
         }
      }

      HashMap<Arena, Integer> var16 = new HashMap<>();

      for (Arena var5 : var15) {
         var16.put(var5, var5.teamSize);
      }

      LinkedList<Entry<Arena, Integer>> var18 = new LinkedList<>(var16.entrySet());
      Collections.sort(var18, new Comparator<Entry<Arena, Integer>>() {
         public int compare(Entry<Arena, Integer> var1, Entry<Arena, Integer> var2x) {
            return (Integer)var1.getValue() - (Integer)var2x.getValue();
         }
      });
      HashMap<Arena, Integer> var19 = new HashMap<>();

      for (Arena var7 : var2) {
         var19.put(var7, var7.teamSize);
      }

      LinkedList<Entry<Arena, Integer>> var20 = new LinkedList<>(var19.entrySet());
      Collections.sort(var20, new Comparator<Entry<Arena, Integer>>() {
         public int compare(Entry<Arena, Integer> var1, Entry<Arena, Integer> var2) {
            return (Integer)var1.getValue() - (Integer)var2.getValue();
         }
      });
      ArrayList<Arena> var21 = new ArrayList<>();

      for (Entry<Arena, Integer> var9 : var18) {
         var21.add(var9.getKey());
      }

      for (Entry<Arena, Integer> var24 : var20) {
         var21.add(var24.getKey());
      }

      Iterator var23 = var21.iterator();

      for (int var25 = 0; var25 < Math.ceil(Double.valueOf((double)this.arenas.size()) / SmartInventory.slots.length); var25++) {
         if (var25 >= this.arenaSelector.getSize()) {
            this.arenaSelector.addInventory(ChatColor.RED + "List #" + (var25 + 1));
            this.arenaSelector.setItem(var25, 49, (ItemStack)Customization.getInstance().items.get("Autojoin"));
         }

         for (int var13 : SmartInventory.slots) {
            if (!var23.hasNext()) {
               break;
            }

            Arena var14 = (Arena)var23.next();
            var14.id = var25;
            var14.slot = var13;
            var14.updateItem();
         }
      }
   }

   public void updatePartiesInventory() {
      for (int var1 = 0; var1 < this.partySelector.getSize(); var1++) {
         this.partySelector.clear(var1);
      }

      int var8 = 0;

      for (int var2 = 0; var2 < Math.ceil(Double.valueOf((double)this.parties.size()) / SmartInventory.slots.length); var2++) {
         if (var2 >= this.partySelector.getSize()) {
            this.partySelector.addInventory(ChatColor.RED + "List #" + (var2 + 1));
            this.partySelector.setItem(var2, 49, (ItemStack)Customization.getInstance().items.get("Back"));
         }

         for (int var6 : SmartInventory.slots) {
            if (var8 >= this.parties.size()) {
               break;
            }

            Party var7 = (Party)this.parties.get(var8);
            var7.id = var2;
            var7.slot = var6;
            var7.updateItem();
            var8++;
         }
      }
   }

   public void startLeaderboardUpdater() {
      if (this.leaderboard_updater != null) {
         instance.leaderboard_updater.cancel();
      }

      this.leaderboard_updater = (new BukkitRunnable() {
            public void run() {
               Customization var1 = Customization.getInstance();
               Bukkit.getConsoleSender().sendMessage(var1.prefix + "Updating leaderboards...");
               final HashMap<Stat, Integer> var2 = new HashMap<>();
               final HashMap<Location, Integer> var3 = new HashMap<>();
               final HashMap<Location, Stat> var4 = new HashMap<>();

               for (Location var6 : BattleRoyaleX.this.topSigns.keySet()) {
                  Block var7 = var6.getBlock();
                  if (var7.getState() instanceof Sign) {
                     Sign var8 = (Sign)var7.getState();
                     if (var8.getLine(0).startsWith(var1.leaderboard_title)
                        && Utils.checkNumbers(new String[]{ChatColor.stripColor(var8.getLine(0).substring(var8.getLine(0).length() - 1))})) {
                        int var9 = Integer.valueOf(ChatColor.stripColor(var8.getLine(0).substring(var8.getLine(0).length() - 1)));
                        Stat var10 = null;
                        String var11 = ChatColor.stripColor(var8.getLine(1));

                        for (String var13 : var1.stats.keySet()) {
                           if (((String)var1.stats.get(var13)).equals(var11)) {
                              var10 = Stat.valueOf(var13);
                              break;
                           }
                        }

                        if (var10 == null) {
                           Bukkit.getConsoleSender()
                              .sendMessage(
                                 var1.prefix
                                    + "Your top sign with the id of "
                                    + ChatColor.AQUA
                                    + "#"
                                    + (Integer)BattleRoyaleX.this.topSigns.get(var6)
                                    + ChatColor.GRAY
                                    + " does not have a valid stat linked to it. You should recreate it as you have probably changed some value in customization.yml"
                              );
                        } else {
                           var3.put(var6, var9);
                           if (var10 != null) {
                              var4.put(var6, var10);
                           }
                           if (!var2.containsKey(var10) || (Integer)var2.get(var10) < var9) {
                              var2.put(var10, var9);
                           }
                        }
                     } else {
                        Bukkit.getConsoleSender()
                           .sendMessage(
                              var1.prefix
                                 + "Your top sign with the id of "
                                 + ChatColor.AQUA
                                 + "#"
                                 + (Integer)BattleRoyaleX.this.topSigns.get(var6)
                                 + ChatColor.GRAY
                                 + " isn't really a top sign"
                           );
                     }
                  } else {
                     Bukkit.getConsoleSender()
                        .sendMessage(
                           var1.prefix
                              + "Your top sign with the id of "
                              + ChatColor.AQUA
                              + "#"
                              + (Integer)BattleRoyaleX.this.topSigns.get(var6)
                              + ChatColor.GRAY
                              + " doesn't exist at the coordinates "
                              + ChatColor.GREEN
                              + Utils.getReadableLocationString(var6, false)
                        );
                  }
               }

               if (var3.isEmpty()) {
                  Bukkit.getConsoleSender().sendMessage(var1.prefix + "Couldn't find any valid top sign to update!");
                  if (BattleRoyaleX.this.hologramsManager == null || BattleRoyaleX.this.hologramsManager.leaderboardHologramLocation == null) {
                     Bukkit.getConsoleSender().sendMessage(var1.prefix + "No Holographic leaderboard was set either, cancelling the task!");
                     this.cancel();
                     BattleRoyaleX.this.leaderboard_updater = null;
                     return;
                  }
               }

               (new BukkitRunnable() {
                     public void run() {
                        try {
                           HashMap var1 = BattleRoyaleXAPI.getAllPlayersData();
                           final HashMap<Stat, Entry<String, Integer>> var2x = new HashMap<>();
                           final HashMap<Stat, List<Entry<String, Integer>>> var3x = new HashMap<>();

                           for (Stat var5 : var2.keySet()) {
                              int var6 = (Integer)var2.get(var5);
                              List<Entry<String, Integer>> var7 = BattleRoyaleXAPI.getTopPlayers(var1, var5, var6);
                              var3x.put(var5, var7);
                              if (var5.holoLeaderboard) {
                                 var2x.put(var5, var7.get(0));
                              }
                           }

                           if (BattleRoyaleX.this.hologramsManager != null && BattleRoyaleX.this.hologramsManager.leaderboardHologramLocation != null) {
                              for (Stat var12 : Stat.values()) {
                                 if (var12.holoLeaderboard && !var2x.containsKey(var12)) {
                                    var2x.put(var12, (Entry<String, Integer>)BattleRoyaleXAPI.getTopPlayers(var1, var12, 1).get(0));
                                 }
                              }
                           }

                           (new BukkitRunnable() {
                                 public void run() {
                                    for (Stat var2xx : var2.keySet()) {
                                       List<Entry<String, Integer>> var3xx = var3x.get(var2xx);

                                       for (Location var5 : var4.keySet()) {
                                          if (((Stat)var4.get(var5)).equals(var2xx)) {
                                             Sign var6 = (Sign)var5.getBlock().getState();
                                             Entry var7 = (Entry)var3xx.get((Integer)var3.get(var5) - 1);
                                             var6.setLine(2, (String)var7.getKey());
                                             var6.setLine(3, "(" + var7.getValue() + ")");
                                             var6.update();
                                             Utils.updateSkull(var6, ((String)var7.getKey()).contains("NO_PLAYER") ? "MHF_Question" : (String)var7.getKey());
                                          }
                                       }
                                    }

                                    if (BattleRoyaleX.this.hologramsManager != null && BattleRoyaleX.this.hologramsManager.leaderboardHologramLocation != null) {
                                       BattleRoyaleX.this.hologramsManager.updateLeaderboardHologram(var2x);
                                    }

                                    BattleRoyaleX.this.leaderboard_updater_time = System.currentTimeMillis()
                                       + BattleRoyaleX.this.getConfig().getInt("Update-Leaderboard-Every-Minutes") * 60000;
                                 }
                              })
                              .runTask(BattleRoyaleX.instance);
                        } catch (Exception var8) {
                           var8.printStackTrace();
                        }
                     }
                  })
                  .runTaskAsynchronously(BattleRoyaleX.instance);
            }
         })
         .runTaskTimer(this, 0L, this.getConfig().getInt("Update-Leaderboard-Every-Minutes") * 1200);
   }

   public void openParachute(final Player var1) {
      final ArmorStand var2 = (ArmorStand)var1.getWorld().spawnEntity(var1.getLocation(), EntityType.ARMOR_STAND);
      var2.setArms(true);
      var2.setLeftArmPose(new EulerAngle(4.71F, 3.14F, 1.57F));
      var2.setRightArmPose(new EulerAngle(1.57F, 0.0, 1.57F));
      if (this.offHandAvailable) {
         var2.getEquipment().setItemInMainHand(XMaterial.CYAN_BANNER.parseItem());
         var2.getEquipment().setItemInOffHand(XMaterial.CYAN_BANNER.parseItem());
      }

      var2.setVisible(false);
      var2.setGravity(false);
      TitleManager.getInstance().sendTitle(var1, (String)Customization.getInstance().titles.get("Parachute-Deploy"));
      var1.playSound(var1.getLocation(), SoundsManager.CLICK, 1.0F, 1.0F);
      final Arena var3 = ((PlayerData)this.playerData.get(var1.getName())).arena;
      if (Config.getInstance().parachuteChicken) {
         final LivingEntity var4 = (LivingEntity)var1.getWorld().spawnEntity(var1.getLocation(), EntityType.CHICKEN);
         var4.setMetadata("BRX_PARACHUTE", new FixedMetadataValue(this, null));
         var4.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 999999));
         if (XMaterial.supports(16)) {
            var4.addPassenger(var1);
         } else {
            var4.setPassenger(var1);
         }

         (new BukkitRunnable() {
               int runs = 0;

               public void run() {
                  if (!var4.isValid()
                     || var4.isOnGround()
                     || var4.getLocation().getBlock().getType().equals(Material.WATER)
                     || !var3.players.containsKey(var1.getName())) {
                     this.cancel();
                     if (var4.getPassenger() != null) {
                        Player var2x = (Player)var4.getPassenger();
                        TitleManager.getInstance().sendTitle(var2x, (String)Customization.getInstance().titles.get("Parachute-Exit"));
                        var2x.playSound(var2x.getLocation(), SoundsManager.NOTE_PLING, 1.0F, 1.0F);
                        var4.eject();
                     }

                     var2.remove();
                     var4.remove();
                  } else if (var4.getPassenger() != null) {
                     Player var1x = (Player)var4.getPassenger();
                     var4.setVelocity(
                        var1x.getEyeLocation()
                           .getDirection()
                           .setY(Config.getInstance().parachuteVerticalSpeed)
                           .multiply(Config.getInstance().parachuteGeneralSpeedModifier)
                     );
                     var2.teleport(var1x.getLocation().add(var1x.getLocation().getDirection().setY(0)));
                     this.runs++;
                     if (this.runs == 10) {
                        var1x.playSound(var1x.getLocation(), SoundsManager.ITEM_PICKUP, 1.0F, 1.0F);
                        this.runs = 0;
                     }
                  }
               }
            })
            .runTaskTimer(this, 1L, 1L);
      } else {
         (new BukkitRunnable() {
               int runs = 0;

               public void run() {
                  if (var1.isValid() && !var1.isOnGround()) {
                     var1.setVelocity(
                        var1.getEyeLocation()
                           .getDirection()
                           .setY(Config.getInstance().parachuteVerticalSpeed)
                           .multiply(Config.getInstance().parachuteGeneralSpeedModifier)
                     );
                     var1.setFallDistance(0.0F);
                     var2.teleport(var1.getLocation().add(var1.getLocation().getDirection().setY(-0.2).multiply(2)));
                     this.runs++;
                     if (this.runs == 10) {
                        var1.playSound(var1.getLocation(), SoundsManager.ITEM_PICKUP, 1.0F, 1.0F);
                        this.runs = 0;
                     }
                  } else {
                     var2.remove();
                     this.cancel();
                     if (var1.isValid()) {
                        TitleManager.getInstance().sendTitle(var1, (String)Customization.getInstance().titles.get("Parachute-Exit"));
                        var1.playSound(var1.getLocation(), SoundsManager.NOTE_PLING, 1.0F, 1.0F);
                     }
                  }
               }
            })
            .runTaskTimer(getInstance(), 5L, 1L);
      }
   }

   public boolean claimCash(Player var1, PAData var2, ItemStack var3) {
      if (var3.hasItemMeta() && var3.getItemMeta().hasDisplayName()) {
         String var4 = ChatColor.stripColor(var3.getItemMeta().getDisplayName());
         if (var4.startsWith("Cash: $")) {
            int var5 = Integer.parseInt(var4.replace("Cash: $", ""));
            var2.cash = var2.cash + var5 * var3.getAmount();
            var2.updateDataString();
            var1.playSound(var1.getLocation(), SoundsManager.LAVA_POP, 1.0F, 1.0F);
            return true;
         }
      }

      return false;
   }

   public boolean isOneGamePerServer() {
      return Config.getInstance().bungee_mode_enabled && this.arenas.size() == 1;
   }

   public void loadVehicles() {
      this.vehicles = new ArrayList();
      if (this.QualityArmoryVehiclesEnabled) {
         FileConfiguration var1 = FilesManager.getInstance().getConfig("vehicles.yml");

         for (String var3 : var1.getStringList("Possible-Vehicles")) {
            if (QualityArmoryVehicles.getVehicle(var3) != null) {
               this.vehicles.add(var3);
            }
         }

         if (!this.vehicles.isEmpty()) {
            Main.onlyPublicVehicles = true;
            Main.allowVehiclePickup = false;
         }
      }
   }

   public VehicleEntity spawnVehicle(Location var1) {
      if (this.vehicles.isEmpty()) {
         return null;
      }

      String var2 = (String)this.vehicles.get(Utils.random.nextInt(this.vehicles.size()));
      AbstractVehicle var3 = QualityArmoryVehicles.getVehicle(var2);
      VehicleEntity var4 = new VehicleEntity(var3, var1.getBlock().getRelative(BlockFace.UP).getLocation(), null);
      var4.getWhiteList().clear();
      var4.setAllowsPassagers(true);
      var4.setFuel(Config.getInstance().qualityArmoryVehiclesDefaultFuel);
      var4.setHealth(Math.min(var3.getMaxHealth(), Config.getInstance().qualityArmoryVehiclesMaxHealth));
      var4.spawn();
      return var4;
   }

   private void setupMetrics() {
      short var1 = 12524;
      Metrics var2 = new Metrics(this, var1);
      var2.addCustomChart(new SimplePie("using_bungee-mode", () -> String.valueOf(Config.getInstance().bungee_mode_enabled)));
      var2.addCustomChart(new SimplePie("using_mysql", () -> String.valueOf(this.mySQL != null)));
   }
}
