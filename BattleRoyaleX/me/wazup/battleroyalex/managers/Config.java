package me.wazup.battleroyalex.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class Config {
   private static Config instance;
   public int startingCoins;
   public int endingLength;
   public int partyInvitationLength;
   public int trackingCompassCooldown;
   public boolean useUUID;
   public boolean loadSkinsOnSkulls;
   public boolean fireworksEnabled;
   public boolean lobbyScoreboard;
   public boolean displayRankInChat;
   public boolean allowSpectatorJoin;
   public boolean useGameMode3ForSpectators;
   public boolean showHealthOnBowHit;
   public boolean displayHealthUnderName;
   public boolean displayHealthInTabList;
   public boolean spectatorsChat;
   public boolean arenaChat;
   public boolean lobbyChat;
   public boolean teleportOwnedMobs;
   public boolean disableNaturalMobSpawning;
   public boolean autoRemovePotionBottles;
   public boolean giveAllKitsCompass;
   public boolean disableHunger;
   public boolean loadChunksOnWorldLoadImmediately;
   public boolean clearShoppingStationItemLoreOnPurchase;
   public boolean downingSystemEnabled;
   public boolean clearEntitiesOnGameEnd;
   public List<String> clearEntitiesOnGameEndExceptions;
   public int coinsPerKill;
   public int coinsPerWin;
   public int expPerDeath;
   public int minExpPerKill;
   public int maxExpPerKill;
   public int expPerNormalWin;
   public int expPerPlunderWin;
   public boolean realisticDrops;
   public boolean realisticChests;
   public boolean qualityArmoryGunsDamageModification;
   public int qualityArmoryMaxBodyShotDamage;
   public int qualityArmoryHeadShotDamage;
   public int qualityArmoryVehiclesDefaultFuel;
   public int qualityArmoryVehiclesMaxHealth;
   public int initialArmor;
   public int maxArmor;
   public int armorRefillAmount;
   public double selling_value;
   public double bleedingDamage;
   public double parachuteVerticalSpeed;
   public double parachuteGeneralSpeedModifier;
   public boolean parachuteChicken;
   public int carepackageHeight;
   public int carepackageTime;
   public boolean carepackageExplosion;
   public String defaultKit;
   public List<Integer> broadcastTime;
   public List<String> allowedCommands;
   public List<String> commandAliases;
   public int party_default_slots;
   public HashMap<Integer, String> party_custom_slots;
   public int loadouts_default_amount;
   public HashMap<Integer, String> loadouts_more_loadouts;
   public List<String> executed_commands_player_win;
   public List<String> executed_commands_arena_countdown;
   public List<String> executed_commands_arena_start;
   public List<String> executed_commands_arena_end;
   public boolean bungee_mode_enabled;
   public String bungee_mode_hub;
   public int first_rank_exp;
   public double rank_modifier;
   public int max_rank;
   public HashMap<Integer, List<String>> rankCommands;
   public HashMap<Integer, List<String>> killstreaks;
   public boolean scoreboardTitleAnimationEnabled;
   public int scoreboardTitleAnimationInterval;
   public List<String> scoreboardTitleAnimationFrames;
   public HashMap<String, ItemStack[]> hotbarItems;
   public Material achievementLocked;
   public Material achievementUnlocked;
   public String achievementLockedLore;
   public String achievementUnlockedLore;
   public String achievementDescription;
   public String achievementPrize;
   public boolean playAgainMessage;
   public String playAgainHeader;
   public String playAgainFooter;
   public List<String> playAgainContent;

   public Config() {
      instance = this;
      FileConfiguration var1 = BattleRoyaleX.getInstance().getConfig();
      this.useUUID = var1.getBoolean("use-UUID");
      this.startingCoins = var1.getInt("Starting-Coins");
      this.endingLength = var1.getInt("Ending-Length");
      this.partyInvitationLength = var1.getInt("Party-Invitation-Length");
      this.trackingCompassCooldown = var1.getInt("Tracking-Compass-Cooldown");
      this.useGameMode3ForSpectators = var1.getBoolean("Use-GameMode-3-For-Spectators");
      this.loadSkinsOnSkulls = var1.getBoolean("Load-Skins-On-Skulls");
      this.fireworksEnabled = var1.getBoolean("Fireworks-Enabled");
      this.lobbyScoreboard = var1.getBoolean("Lobby-Scoreboard");
      this.displayRankInChat = var1.getBoolean("Display-Rank-In-Chat");
      this.allowSpectatorJoin = var1.getBoolean("Allow-Spectator-Join");
      this.showHealthOnBowHit = var1.getBoolean("Show-Health-On-Bow-Hit");
      this.teleportOwnedMobs = var1.getBoolean("Teleport-Owned-Mobs");
      this.giveAllKitsCompass = var1.getBoolean("Give-All-Kits-Compass");
      this.disableNaturalMobSpawning = var1.getBoolean("Disable-Natural-Mob-Spawning");
      this.disableHunger = var1.getBoolean("Disable-Hunger");
      this.autoRemovePotionBottles = var1.getBoolean("Auto-Remove-Potion-Bottles");
      this.loadChunksOnWorldLoadImmediately = var1.getBoolean("Load-Chunks-On-World-Load-Immediately");
      this.clearShoppingStationItemLoreOnPurchase = var1.getBoolean("Clear-Shopping-Station-Item-Lore-On-Purchase");
      this.defaultKit = var1.getString("Default-Kit").toLowerCase();
      this.spectatorsChat = var1.getBoolean("Separate-Chats.Spectators");
      this.arenaChat = var1.getBoolean("Separate-Chats.Arena-Players");
      this.lobbyChat = var1.getBoolean("Separate-Chats.Lobby");
      this.selling_value = var1.getDouble("Selling-Value");
      this.bleedingDamage = var1.getDouble("Bleeding-Damage");
      this.downingSystemEnabled = var1.getBoolean("Downing-System.enabled");
      this.parachuteVerticalSpeed = var1.getDouble("Parachute.Vertical-Speed");
      this.parachuteGeneralSpeedModifier = var1.getDouble("Parachute.General-Speed-Modifier");
      this.parachuteChicken = var1.getBoolean("Parachute.Ride-Invisible-Chicken");
      this.carepackageHeight = var1.getInt("Carepackage.Needed-Height");
      this.carepackageTime = var1.getInt("Carepackage.Needed-Time");
      this.carepackageExplosion = var1.getBoolean("Carepackage.Cause-Explosion");
      this.displayHealthUnderName = var1.getBoolean("Display-Health.Under-Name");
      this.displayHealthInTabList = var1.getBoolean("Display-Health.In-Tablist");
      this.broadcastTime = var1.getIntegerList("Broadcast-Time");
      this.allowedCommands = new ArrayList();

      for (String var3 : var1.getStringList("Allowed-Commands")) {
         this.allowedCommands.add(var3.toLowerCase());
      }

      this.commandAliases = new ArrayList();

      for (String var16 : var1.getStringList("Command-Aliases")) {
         this.commandAliases.add(var16.toLowerCase());
      }

      this.coinsPerKill = var1.getInt("Rewards.Coins-Per-Kill");
      this.coinsPerWin = var1.getInt("Rewards.Coins-Per-Win");
      this.expPerDeath = var1.getInt("Rewards.Exp-Per-Death");
      this.minExpPerKill = var1.getInt("Rewards.Min-Exp-Per-Kill");
      this.maxExpPerKill = var1.getInt("Rewards.Max-Exp-Per-Kill");
      this.expPerNormalWin = var1.getInt("Rewards.Exp-Per-Normal-Win");
      this.expPerPlunderWin = var1.getInt("Rewards.Exp-Per-Plunder-Win");
      this.realisticDrops = var1.getBoolean("Realistic-Items.Drops");
      this.realisticChests = var1.getBoolean("Realistic-Items.Chests");
      this.qualityArmoryGunsDamageModification = var1.getBoolean("Quality-Armory-Guns-Damage-Modification.Enabled");
      this.qualityArmoryMaxBodyShotDamage = var1.getInt("Quality-Armory-Guns-Damage-Modification.Body-Shot-Max-Damage");
      this.qualityArmoryHeadShotDamage = var1.getInt("Quality-Armory-Guns-Damage-Modification.Head-Shot-Damage");
      this.qualityArmoryVehiclesDefaultFuel = var1.getInt("Quality-Armory-Vehicles.Vehicles-Default-Fuel");
      this.qualityArmoryVehiclesMaxHealth = var1.getInt("Quality-Armory-Vehicles.Max-Vehicle-Health");
      this.initialArmor = var1.getInt("Armor.initial-armor");
      this.maxArmor = var1.getInt("Armor.max-armor");
      this.armorRefillAmount = var1.getInt("Armor.armor-refill-amount");
      this.party_default_slots = var1.getInt("Party.Default-Slots");
      this.party_custom_slots = new HashMap();

      for (String var17 : var1.getStringList("Party.More-Slots")) {
         this.party_custom_slots.put(Integer.valueOf(var17.split(" : ")[0]), var17.split(" : ")[1]);
      }

      this.loadouts_default_amount = Math.max(var1.getInt("Loadouts.Default-Amount"), 1);
      this.loadouts_more_loadouts = new HashMap();

      for (String var18 : var1.getStringList("Loadouts.More-Loadouts")) {
         this.loadouts_more_loadouts.put(Integer.valueOf(var18.split(" : ")[0]), var18.split(" : ")[1]);
      }

      this.executed_commands_player_win = var1.getStringList("Executed-Commands.Player-Win");
      this.executed_commands_arena_countdown = var1.getStringList("Executed-Commands.Arena-Countdown");
      this.executed_commands_arena_start = var1.getStringList("Executed-Commands.Arena-Start");
      this.executed_commands_arena_end = var1.getStringList("Executed-Commands.Arena-End");
      this.killstreaks = new HashMap();

      for (String var19 : var1.getStringList("Killstreaks")) {
         String[] var4 = var19.split(" :: ");
         int var5 = Integer.parseInt(var4[0]);
         ArrayList var6 = new ArrayList();

         for (int var7 = 1; var7 < var4.length; var7++) {
            var6.add(var4[var7]);
         }

         this.killstreaks.put(var5, var6);
      }

      this.bungee_mode_enabled = var1.getBoolean("Bungee-Mode.enabled");
      this.bungee_mode_hub = var1.getString("Bungee-Mode.hub");
      this.first_rank_exp = var1.getInt("Ranks.First-Rank-Exp");
      this.rank_modifier = var1.getDouble("Ranks.Rank-Modifier");
      this.max_rank = var1.getInt("Ranks.Max-Rank");
      this.rankCommands = new HashMap();

      for (String var20 : var1.getStringList("Ranks.Commands")) {
         int var24 = Integer.valueOf(var20.split(" : ")[0]);
         ArrayList var27 = new ArrayList();

         for (int var29 = 1; var29 < var20.split(" : ").length; var29++) {
            var27.add(var20.split(" : ")[var29]);
         }

         this.rankCommands.put(var24, var27);
      }

      this.scoreboardTitleAnimationEnabled = var1.getBoolean("Scoreboard-Title-Animation.enabled");
      this.scoreboardTitleAnimationInterval = var1.getInt("Scoreboard-Title-Animation.interval");
      this.scoreboardTitleAnimationFrames = new ArrayList();

      for (String var21 : var1.getStringList("Scoreboard-Title-Animation.frames")) {
         this.scoreboardTitleAnimationFrames.add(Utils.colorize(var21));
      }

      if (this.scoreboardTitleAnimationFrames.size() < 2) {
         this.scoreboardTitleAnimationEnabled = false;
         Bukkit.getConsoleSender()
            .sendMessage(Customization.getInstance().prefix + "Lobby scoreboard animation was disabled because there are not enough amount of frames!");
      } else if (this.scoreboardTitleAnimationEnabled) {
         Bukkit.getConsoleSender()
            .sendMessage(
               Customization.getInstance().prefix
                  + "Scoreboard title animation has been enabled and it contains "
                  + ChatColor.AQUA
                  + this.scoreboardTitleAnimationFrames.size()
                  + ChatColor.GRAY
                  + " frame(s)!"
            );
      }

      new TitleManager(var1.getBoolean("Titles-Enabled"), var1.getBoolean("Action-Bar-Enabled"));
      this.hotbarItems = new HashMap();

      for (String var22 : var1.getConfigurationSection("Hotbar-Items").getKeys(false)) {
         ItemStack[] var25 = new ItemStack[36];

         for (String var30 : var1.getConfigurationSection("Hotbar-Items." + var22).getKeys(false)) {
            if (var1.getBoolean("Hotbar-Items." + var22 + "." + var30 + ".enabled")) {
               var25[var1.getInt("Hotbar-Items." + var22 + "." + var30 + ".slot") - 1] = (ItemStack)Customization.getInstance().items.get(var30);
            }
         }

         this.hotbarItems.put(var22, var25);
      }

      this.clearEntitiesOnGameEnd = var1.getBoolean("Clear-Entities-On-Game-End.enabled");
      this.clearEntitiesOnGameEndExceptions = var1.getStringList("Clear-Entities-On-Game-End.entity-type-exceptions");
      FileConfiguration var15 = FilesManager.getInstance().getConfig("achievements.yml");
      this.achievementLocked = Material.getMaterial(var15.getString("Styling.Locked-Material"));
      this.achievementUnlocked = Material.getMaterial(var15.getString("Styling.Unlocked-Material"));
      this.achievementLockedLore = Utils.colorize(var15.getString("Styling.Locked-Lore"));
      this.achievementUnlockedLore = Utils.colorize(var15.getString("Styling.Unlocked-Lore"));
      this.achievementDescription = Utils.colorize(var15.getString("Styling.Name"));
      this.achievementPrize = Utils.colorize(var15.getString("Styling.Prize"));
      this.playAgainMessage = var1.getBoolean("Play-Again-Message.enabled");
      if (this.playAgainMessage) {
         this.playAgainHeader = Utils.colorize(var1.getString("Play-Again-Message.header"));
         this.playAgainContent = new ArrayList();

         for (String var26 : var1.getStringList("Play-Again-Message.content")) {
            this.playAgainContent.add(Utils.colorize(var26));
         }

         this.playAgainFooter = Utils.colorize(var1.getString("Play-Again-Message.footer"));
      }
   }

   public static Config getInstance() {
      return instance;
   }
}
