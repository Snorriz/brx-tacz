package me.wazup.battleroyalex;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import me.wazup.battleroyalex.managers.AchievementsManager;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.managers.Customization;
import me.wazup.battleroyalex.managers.MySQL;
import me.wazup.battleroyalex.managers.RanksManager;
import me.wazup.battleroyalex.shop.Celebration;
import me.wazup.battleroyalex.shop.Kit;
import me.wazup.battleroyalex.utils.FlexibleScoreboard;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Loadout;
import me.wazup.battleroyalex.utils.SmartInventory;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerData {
   private Location location;
   private double maxhealth;
   private double health;
   private int food;
   private int level;
   private float exp;
   private GameMode gameMode;
   private boolean flying;
   private Collection<PotionEffect> effects;
   private ItemStack[] items;
   private ItemStack[] armor;
   private Scoreboard scoreboard;
   public Arena arena;
   public Party party;
   private HashMap<String, Long> cooldowns = new HashMap();
   public String lastHit = "";
   public long lastHitTime = 0L;
   public long lastActionBarSent;
   public int loadoutEditing;
   public String bountyTarget_temp;
   public HashMap<String, Integer> bounties = new HashMap();
   long currentPlayTime;
   public boolean save;
   public ArrayList<String> ownedKits;
   public ArrayList<String> ownedCelebrations;
   public String selectedKit;
   public String selectedCelebration;
   public ArrayList<Loadout> loadouts;
   public int selectedLoadout;
   private int coins;
   public int kills;
   public int deaths;
   public int games_played;
   public int normal_wins;
   public int plunder_wins;
   public int modifier;
   public int projectiles_hit;
   public int player_exp;
   public int player_rank;
   public int blocks_placed;
   public int blocks_broken;
   public int seconds;
   public int minutes;
   public int hours;
   public int days;
   public SmartInventory achievements;
   public FlexibleScoreboard lobbyScoreboard;

   public PlayerData(Player var1) {
      this.load(var1);
   }

   protected void saveData(Player var1) {
      this.location = var1.getLocation();
      this.maxhealth = var1.getMaxHealth();
      this.health = var1.getHealth();
      this.food = var1.getFoodLevel();
      this.level = var1.getLevel();
      this.exp = var1.getExp();
      this.gameMode = var1.getGameMode();
      this.flying = var1.isFlying();
      this.effects = var1.getActivePotionEffects();
      this.items = var1.getInventory().getContents();
      this.armor = var1.getInventory().getArmorContents();
      this.scoreboard = var1.getScoreboard();
      this.currentPlayTime = System.currentTimeMillis();
      this.save = true;
   }

   protected void restoreData(Player var1) {
      var1.teleport(this.location);
      var1.setFallDistance(0.0F);
      var1.setMaxHealth(this.maxhealth);
      var1.setHealth(this.health);
      var1.setFoodLevel(this.food);
      var1.setLevel(this.level);
      var1.setExp(this.exp);
      var1.setGameMode(this.gameMode);
      if (this.flying) {
         var1.setAllowFlight(true);
         var1.setFlying(true);
      }

      for (PotionEffect var3 : var1.getActivePotionEffects()) {
         var1.removePotionEffect(var3.getType());
      }

      var1.addPotionEffects(this.effects);
      var1.getInventory().setContents(this.items);
      var1.getInventory().setArmorContents(this.armor);
      var1.updateInventory();
      var1.setScoreboard(this.scoreboard);
      this.updatePlayTime();
      if (BattleRoyaleX.getInstance().hologramsManager != null) {
         BattleRoyaleX.getInstance().hologramsManager.deleteStatsHologram(var1);
      }

      this.destroyData();
   }

   public void enterLobby(Player var1) {
      var1.teleport(BattleRoyaleX.getInstance().lobbyLocation);
      var1.getInventory().setArmorContents(null);
      var1.setMaxHealth(20.0);
      var1.setHealth(var1.getMaxHealth());
      var1.setFoodLevel(20);
      var1.setFireTicks(0);
      var1.setLevel(0);
      var1.setExp(0.0F);
      var1.setGameMode(GameMode.SURVIVAL);
      var1.setAllowFlight(false);
      var1.setFlying(false);

      for (PotionEffect var3 : var1.getActivePotionEffects()) {
         var1.removePotionEffect(var3.getType());
      }

      if (Config.getInstance().lobbyScoreboard) {
         this.createScoreboard(var1);
      } else {
         var1.setScoreboard(this.scoreboard);
      }

      var1.getInventory().setContents((ItemStack[])Config.getInstance().hotbarItems.get("Lobby"));
      var1.updateInventory();
      if (BattleRoyaleX.getInstance().hologramsManager != null) {
         BattleRoyaleX.getInstance().hologramsManager.createStatsHologram(var1, this);
      }
   }

   protected void destroyData() {
      this.location = null;
      this.maxhealth = 0.0;
      this.health = 0.0;
      this.food = 0;
      this.level = 0;
      this.exp = 0.0F;
      this.gameMode = null;
      this.effects = null;
      this.items = null;
      this.armor = null;
      this.scoreboard = null;
      this.lobbyScoreboard = null;
   }

   public void createScoreboard(Player var1) {
      this.lobbyScoreboard = new FlexibleScoreboard(
         false, false, Customization.getInstance().lobby_scoreboard_title, Customization.getInstance().lobbyScoreboard
      );
      this.lobbyScoreboard.update("%coins%", this.getCoins(var1));
      this.lobbyScoreboard.update("%kills%", this.kills);
      this.lobbyScoreboard.update("%deaths%", this.deaths);
      this.lobbyScoreboard.update("%games_played%", this.games_played);
      this.lobbyScoreboard.update("%normal_wins%", this.normal_wins);
      this.lobbyScoreboard.update("%plunder_wins%", this.plunder_wins);
      this.lobbyScoreboard.update("%modifier%", this.modifier);
      this.lobbyScoreboard.update("%projectiles_hit%", this.projectiles_hit);
      this.lobbyScoreboard.update("%player_exp%", this.player_exp);
      this.lobbyScoreboard.update("%player_rank%", this.player_rank);
      this.lobbyScoreboard.update("%blocks_broken%", this.blocks_broken);
      this.lobbyScoreboard.update("%blocks_placed%", this.blocks_placed);
      this.lobbyScoreboard
         .update(
            "%selected_kit%",
            BattleRoyaleX.getInstance().kits.containsKey(this.selectedKit) ? ((Kit)BattleRoyaleX.getInstance().kits.get(this.selectedKit)).name : "None"
         );
      this.lobbyScoreboard
         .update(
            "%selected_celebration%",
            BattleRoyaleX.getInstance().celebrations.containsKey(this.selectedCelebration)
               ? ((Celebration)BattleRoyaleX.getInstance().celebrations.get(this.selectedCelebration)).name
               : "None"
         );
      this.lobbyScoreboard.apply(var1);
   }

   public void load(final Player var1) {
      final String var2 = Config.getInstance().useUUID ? var1.getUniqueId().toString() : var1.getName();
      (new BukkitRunnable() {
         public void run() {
            String var1x = Config.getInstance().startingCoins + ":0:0:0:0:0:1:0:0:0:0:0D-0H-0M-0S";
            String var2x = Config.getInstance().defaultKit;
            String var3 = "";
            String var4 = "";
            String var5 = var2x + " : " + 0 + " : " + var3;
            if (BattleRoyaleX.getInstance().mySQL != null) {
               try {
                  MySQL var6 = BattleRoyaleX.getInstance().mySQL;
                  Connection var7 = var6.getConnection();
                  PreparedStatement var8 = var7.prepareStatement(var6.SELECT);
                  var8.setString(1, var2);
                  ResultSet var9 = var8.executeQuery();
                  if (var9.next()) {
                     var1x = var9.getString("stats");
                     var2x = var9.getString("kits");
                     var4 = var9.getString("loadouts");
                     var5 = var9.getString("selected");
                     var3 = var9.getString("celebrations");
                  }

                  var8.close();
                  var9.close();
               } catch (SQLException var10) {
                  var10.printStackTrace();
               }
            } else {
               File var11 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/players/", var2);
               if (var11.exists()) {
                  YamlConfiguration var12 = YamlConfiguration.loadConfiguration(var11);
                  var1x = var12.getString("Stats", "");
                  var2x = var12.getString("Kits", "");
                  var4 = var12.getString("Loadouts", "");
                  var5 = var12.getString("Selected", "");
                  var3 = var12.getString("Celebrations", "");
               }
            }

            PlayerData.this.loadStatsString(var1x);
            PlayerData.this.loadKitsString(var2x);
            PlayerData.this.loadCelebrationsString(var3);
            PlayerData.this.loadLoadoutsString(var1, var4);
            PlayerData.this.loadSelectedItemsString(var5);
            if (BattleRoyaleX.getInstance().lobbyPlayers.contains(var1.getName())) {
               (new BukkitRunnable() {
                  public void run() {
                     PlayerData.this.createScoreboard(var1);
                     if (BattleRoyaleX.getInstance().hologramsManager != null) {
                        BattleRoyaleX.getInstance().hologramsManager.createStatsHologram(var1, PlayerData.this);
                     }
                  }
               }).runTask(BattleRoyaleX.getInstance());
            }
         }
      }).runTaskAsynchronously(BattleRoyaleX.getInstance());
   }

   protected void save(Player var1) {
      if (this.save) {
         String var2 = Config.getInstance().useUUID ? var1.getUniqueId().toString() : var1.getName();
         if (BattleRoyaleX.getInstance().mySQL != null) {
            try {
               MySQL var3 = BattleRoyaleX.getInstance().mySQL;
               Connection var4 = var3.getConnection();
               PreparedStatement var5 = var4.prepareStatement(var3.SELECT);
               int var6 = 1;
               var5.setString(var6, var2);
               if (var5.executeQuery().next()) {
                  var5 = var4.prepareStatement(var3.UPDATE);
                  var5.setString(var6++, var1.getName());
                  var5.setString(var6++, this.getStatsString());
                  var5.setString(var6++, this.getListAsString(this.ownedKits));
                  var5.setString(var6++, this.getLoadoutsString());
                  var5.setString(var6++, this.getSelectedItemsString());
                  var5.setString(var6++, this.getListAsString(this.ownedCelebrations));
                  var5.setString(var6++, var2);
               } else {
                  var5 = var4.prepareStatement(var3.INSERT);
                  var5.setString(var6++, var1.getUniqueId().toString());
                  var5.setString(var6++, var1.getName());
                  var5.setString(var6++, this.getStatsString());
                  var5.setString(var6++, this.getListAsString(this.ownedKits));
                  var5.setString(var6++, this.getLoadoutsString());
                  var5.setString(var6++, this.getSelectedItemsString());
                  var5.setString(var6++, this.getListAsString(this.ownedCelebrations));
               }

               var5.execute();
               var5.close();
            } catch (SQLException var8) {
               var8.printStackTrace();
            }
         } else {
            File var9 = new File(BattleRoyaleX.getInstance().getDataFolder() + "/players/", var2);
            YamlConfiguration var10 = YamlConfiguration.loadConfiguration(var9);
            var10.set("Name", var1.getName());
            var10.set("Stats", this.getStatsString());
            var10.set("Kits", this.getListAsString(this.ownedKits));
            var10.set("Loadouts", this.getLoadoutsString());
            var10.set("Selected", this.getSelectedItemsString());
            var10.set("Celebrations", this.getListAsString(this.ownedCelebrations));

            try {
               var10.save(var9);
            } catch (IOException var7) {
               var7.printStackTrace();
            }
         }
      }
   }

   public void saveAsync(final Player var1) {
      if (this.save) {
         (new BukkitRunnable() {
            public void run() {
               PlayerData.this.save(var1);
            }
         }).runTaskAsynchronously(BattleRoyaleX.getInstance());
      }
   }

   private String getSelectedItemsString() {
      return this.selectedKit + " : " + this.selectedLoadout + " : " + this.selectedCelebration;
   }

   private void loadSelectedItemsString(String var1) {
      for (int var2 = var1.split(" : ").length; var2 < 3; var2++) {
         var1 = var1 + " :  ";
      }

      this.selectedKit = var1.split(" : ")[0];
      if (!this.ownedKits.contains(this.selectedKit)) {
         this.selectedKit = "";
      }

      this.selectedLoadout = Integer.parseInt(var1.split(" : ")[1]);
      this.selectedLoadout = Math.min(this.selectedLoadout, this.loadouts.size());
      this.selectedCelebration = var1.split(" : ")[2];
      if (!this.ownedCelebrations.contains(this.selectedCelebration)) {
         this.selectedCelebration = "";
      }
   }

   protected String getListAsString(ArrayList<String> var1) {
      if (var1.isEmpty()) {
         return "";
      }

      String var2 = (String)var1.get(0);

      for (int var3 = 1; var3 < var1.size(); var3++) {
         var2 = var2 + " : " + (String)var1.get(var3);
      }

      return var2;
   }

   protected void loadKitsString(String var1) {
      this.ownedKits = new ArrayList();
      if (!var1.isEmpty()) {
         for (String var5 : var1.split(" : ")) {
            if (BattleRoyaleX.getInstance().kits.containsKey(var5)) {
               this.ownedKits.add(var5);
            }
         }
      }
   }

   protected void loadCelebrationsString(String var1) {
      this.ownedCelebrations = new ArrayList();
      if (!var1.isEmpty()) {
         for (String var5 : var1.split(" : ")) {
            if (BattleRoyaleX.getInstance().celebrations.containsKey(var5)) {
               this.ownedCelebrations.add(var5);
            }
         }
      }
   }

   protected String getLoadoutsString() {
      if (this.loadouts.isEmpty()) {
         return "";
      }

      String var1 = ((Loadout)this.loadouts.get(0)).toString();

      for (int var2 = 1; var2 < this.loadouts.size(); var2++) {
         var1 = var1 + " : " + ((Loadout)this.loadouts.get(var2)).toString();
      }

      return var1;
   }

   protected void loadLoadoutsString(Player var1, String var2) {
      this.loadouts = new ArrayList();
      int var3 = Config.getInstance().loadouts_default_amount;

      for (int var5 : Config.getInstance().loadouts_more_loadouts.keySet()) {
         if (var1.hasPermission((String)Config.getInstance().loadouts_more_loadouts.get(var5)) && var5 > var3) {
            var3 = var5;
         }
      }

      if (!var2.isEmpty()) {
         for (String var7 : var2.split(" : ")) {
            this.loadouts.add(new Loadout(this.loadouts.size() + 1, var7));
            if (this.loadouts.size() >= var3) {
               break;
            }
         }
      }

      while (this.loadouts.size() < var3) {
         this.loadouts.add(new Loadout(this.loadouts.size() + 1, ""));
      }
   }

   public Loadout getLoadout() {
      return (Loadout)this.loadouts.get(this.selectedLoadout);
   }

   protected String getStatsString() {
      return this.coins
         + ":"
         + this.kills
         + ":"
         + this.deaths
         + ":"
         + this.games_played
         + ":"
         + this.normal_wins
         + ":"
         + this.plunder_wins
         + ":"
         + this.modifier
         + ":"
         + this.projectiles_hit
         + ":"
         + this.player_exp
         + ":"
         + this.blocks_placed
         + ":"
         + this.blocks_broken
         + ":"
         + this.getPlayTime();
   }

   private void loadStatsString(String var1) {
      String[] var2 = var1.split(":");
      int var3 = 0;
      this.coins = this.i(var2[var3++]);
      this.kills = this.i(var2[var3++]);
      this.deaths = this.i(var2[var3++]);
      this.games_played = this.i(var2[var3++]);
      this.normal_wins = this.i(var2[var3++]);
      this.plunder_wins = this.i(var2[var3++]);
      this.modifier = this.i(var2[var3++]);
      this.projectiles_hit = this.i(var2[var3++]);
      this.player_exp = this.i(var2[var3++]);
      this.blocks_placed = this.i(var2[var3++]);
      this.blocks_broken = this.i(var2[var3++]);
      this.player_rank = RanksManager.getInstance().getRank(this);
      this.achievements = AchievementsManager.getInstance().getAchievements(this);
      this.loadPlayTime(var2[var3++]);
   }

   protected void updatePlayTime() {
      this.seconds = (int)(this.seconds + (System.currentTimeMillis() - this.currentPlayTime) / 1000L);

      for (this.currentPlayTime = System.currentTimeMillis(); this.seconds > 60; this.minutes++) {
         this.seconds -= 60;
      }

      while (this.minutes > 60) {
         this.minutes -= 60;
         this.hours++;
      }

      while (this.hours > 24) {
         this.hours -= 24;
         this.days++;
      }
   }

   private String getPlayTime() {
      return this.days + "D-" + this.hours + "H-" + this.minutes + "M-" + this.seconds + "S";
   }

   private void loadPlayTime(String var1) {
      String[] var2 = var1.replace("D", "").replace("H", "").replace("M", "").replace("S", "").split("-");
      this.days = this.i(var2[0]);
      this.hours = this.i(var2[1]);
      this.minutes = this.i(var2[2]);
      this.seconds = this.i(var2[3]);
   }

   private int i(String var1) {
      return Integer.parseInt(var1);
   }

   public int getTotalBounty() {
      int var1 = 0;

      for (int var3 : this.bounties.values()) {
         var1 += var3;
      }

      return var1;
   }

   public void cancelBounty(String var1) {
      if (!this.bounties.isEmpty()) {
         for (String var3 : this.bounties.keySet()) {
            if (BattleRoyaleX.getInstance().playerData.containsKey(var3)) {
               Player var4 = Bukkit.getPlayer(var3);
               int var5 = (Integer)this.bounties.get(var3);
               ((PlayerData)BattleRoyaleX.getInstance().playerData.get(var3)).addCoins(var4, var5);
               var4.sendMessage(
                  ((String)Customization.getInstance().messages.get("Bounty-Cancel")).replace("%player%", var1).replace("%bounty%", String.valueOf(var5))
               );
            }
         }

         this.bounties.clear();
      }
   }

   public Inventory getStatsInventory(Player var1) {
      Inventory var2 = Bukkit.createInventory(null, 45, (String)Customization.getInstance().inventories.get("Stats-Inventory"));
      this.updatePlayTime();
      Utils.cageInventory(var2, false);
      var2.setItem(var2.getSize() - 5, (ItemStack)Customization.getInstance().items.get("Back"));
      double var3 = new BigDecimal(this.deaths > 1 ? Double.valueOf((double)this.kills) / this.deaths : this.kills)
         .setScale(2, RoundingMode.HALF_UP)
         .doubleValue();
      ItemStackBuilder var5 = new ItemStackBuilder(Material.PAPER);
      Customization var6 = Customization.getInstance();
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("COINS")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.getCoins(var1)))})
               .setType(Material.EMERALD)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("GAMES_PLAYED")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.games_played))})
               .setType(Material.REDSTONE)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("NORMAL_WINS")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.normal_wins))})
               .setType(Material.DIAMOND)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("PLUNDER_WINS")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.plunder_wins))})
               .setType(Material.DIAMOND)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("KILLS")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.kills))})
               .setType(Material.DIAMOND_SWORD)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("DEATHS")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.deaths))})
               .setType(Material.REDSTONE)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("KDR")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(var3))})
               .setType(Material.ENCHANTED_BOOK)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("PROJECTILES_HIT")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.projectiles_hit))})
               .setType(Material.ARROW)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("BLOCKS_PLACED")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.blocks_placed))})
               .setType(XMaterial.GRASS_BLOCK.parseMaterial())
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("BLOCKS_BROKEN")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.blocks_broken))})
               .setType(Material.TNT)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("PLAYER_EXP")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.player_exp))})
               .setType(XMaterial.EXPERIENCE_BOTTLE.parseMaterial())
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("RANK")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.player_rank))})
               .setType(Material.CHEST)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("NEXT_RANK_NEEDED_EXP")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(RanksManager.getInstance().getNextRankExp(this)))})
               .setType(Material.ENDER_CHEST)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("MODIFIER")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", String.valueOf(this.modifier))})
               .setType(Material.GOLD_INGOT)
               .build()
         }
      );
      var2.addItem(
         new ItemStack[]{
            var5.setName(var6.statsInventoryStat.replace("%stat%", (CharSequence)var6.stats.get("PLAY_TIME")))
               .addLore(new String[]{var6.statsInventoryValue.replace("%value%", this.getPlayTime())})
               .setType(Material.PAPER)
               .build()
         }
      );
      return var2;
   }

   public Inventory getKitsInventory() {
      Inventory var1 = Bukkit.createInventory(
         null, Utils.getInventorySize(this.ownedKits.size() + 1), (String)Customization.getInstance().inventories.get("Owned-Kits")
      );
      var1.setItem(var1.getSize() - 1, (ItemStack)Customization.getInstance().items.get("Back"));

      for (String var3 : this.ownedKits) {
         var1.addItem(new ItemStack[]{((Kit)BattleRoyaleX.getInstance().kits.get(var3)).item});
      }

      return var1;
   }

   public Inventory getCelebrationsInventory() {
      Inventory var1 = Bukkit.createInventory(
         null, Utils.getInventorySize(this.ownedCelebrations.size() + 1), (String)Customization.getInstance().inventories.get("Owned-Celebrations")
      );
      var1.setItem(var1.getSize() - 1, (ItemStack)Customization.getInstance().items.get("Back"));

      for (String var3 : this.ownedCelebrations) {
         var1.addItem(new ItemStack[]{((Celebration)BattleRoyaleX.getInstance().celebrations.get(var3)).item});
      }

      return var1;
   }

   public Inventory getLoadoutsInventory() {
      Inventory var1 = Bukkit.createInventory(
         null, Utils.getInventorySize(this.loadouts.size() + 1), (String)Customization.getInstance().inventories.get("Loadouts")
      );
      Utils.cageInventory(var1, true);
      var1.setItem(var1.getSize() - 1, (ItemStack)Customization.getInstance().items.get("Back"));

      for (int var2 = 0; var2 < this.loadouts.size(); var2++) {
         ItemStackBuilder var3 = new ItemStackBuilder(Material.IRON_SWORD).setName(ChatColor.GRAY + "Loadout " + (var2 + 1));
         var3.addLore(new String[]{ChatColor.GREEN + "Left click to equip"});
         var3.addLore(new String[]{ChatColor.RED + "Right click to view/edit"});
         if (this.selectedLoadout == var2) {
            var3.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            var3.addLore(new String[]{" ", ChatColor.GOLD + "- Selected"});
         }

         var1.setItem(var2, var3.build());
      }

      return var1;
   }

   public boolean hasCooldown(Player var1, String var2, int var3) {
      long var4 = this.cooldowns.containsKey(var2) ? (Long)this.cooldowns.get(var2) - System.currentTimeMillis() : 0L;
      if (var4 > 0L) {
         var1.sendMessage(
            ((String)Customization.getInstance().messages.get("Cooldown"))
               .replace("%seconds%", String.valueOf(new BigDecimal(Double.valueOf((double)var4) / 1000.0).setScale(1, RoundingMode.HALF_UP).doubleValue()))
         );
         return true;
      } else {
         this.cooldowns.put(var2, System.currentTimeMillis() + var3 * 1000);
         return false;
      }
   }

   public int getCoins(Player var1) {
      return BattleRoyaleX.getInstance().vault != null ? (int)BattleRoyaleX.getInstance().vault.getBalance(var1) : this.coins;
   }

   public void addCoins(Player var1, int var2) {
      if (BattleRoyaleX.getInstance().vault != null) {
         BattleRoyaleX.getInstance().vault.depositPlayer(var1, var2);
      } else {
         this.coins += var2;
      }
   }

   public void removeCoins(Player var1, int var2) {
      if (BattleRoyaleX.getInstance().vault != null) {
         BattleRoyaleX.getInstance().vault.withdrawPlayer(var1, var2);
      } else {
         this.coins -= var2;
      }
   }

   public void setCoins(Player var1, int var2) {
      if (BattleRoyaleX.getInstance().vault != null) {
         int var3 = var2 - this.getCoins(var1);
         if (var3 > 0) {
            BattleRoyaleX.getInstance().vault.depositPlayer(var1, var3);
         } else {
            BattleRoyaleX.getInstance().vault.withdrawPlayer(var1, -var3);
         }
      } else {
         this.coins = var2;
      }
   }
}
