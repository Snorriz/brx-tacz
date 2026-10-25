package me.wazup.battleroyalex.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.wazup.battleroyalex.utils.ItemStackBuilder;
import me.wazup.battleroyalex.utils.Utils;
import me.wazup.battleroyalex.utils.XMaterial;
import me.wazup.battleroyalex.utils.Enums.ArenaState;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class Customization {
   private static Customization instance;
   public String prefix;
   public HashMap<String, String> messages;
   public HashMap<String, String> inventories;
   public HashMap<String, String> titles;
   public HashMap<String, String> lores;
   public HashMap<String, String> deathMessages;
   public HashMap<String, String> stats;
   public HashMap<String, String> actionBars;
   public List<String> matchConclusion;
   public List<String> holographicStats;
   public List<String> killMessages;
   public List<String> arenaSettings;
   public List<String> normalArenaStart;
   public List<String> plunderArenaStart;
   public String player_suicide;
   public String signs_title;
   public String signs_leave;
   public String signs_autojoin;
   public String signs_join;
   public String leaderboard_title;
   public String signs_arena_players;
   public ChatColor signs_arena_color;
   public ChatColor leaderboard_stat_color;
   public String lobby_scoreboard_title;
   public String arena_lobby_scoreboard_title;
   public String arena_game_scoreboard_title;
   public String[] lobbyScoreboard;
   public String[] arenaLobbyScoreboard;
   public String[] arenaGameScoreboard;
   public String statsInventoryStat;
   public String statsInventoryValue;
   public String holographicLeaderboardHeader;
   public String holographicLeaderboardBody;
   public String holographicLeaderboardFooter;
   public HashMap<String, ItemStack> items;

   public Customization(FileConfiguration var1) {
      instance = this;
      this.prefix = this.c(var1.getString("prefix"));
      this.messages = new HashMap();

      for (String var3 : var1.getConfigurationSection("Messages").getKeys(false)) {
         this.messages.put(var3, this.prefix + this.c(var1.getString("Messages." + var3)));
      }

      this.deathMessages = new HashMap();

      for (String var22 : var1.getConfigurationSection("Death-Messages").getKeys(false)) {
         if (!var22.equalsIgnoreCase("PLAYER")) {
            this.deathMessages.put(var22.toUpperCase(), this.prefix + this.c(var1.getString("Death-Messages." + var22)));
         }
      }

      this.killMessages = new ArrayList();

      for (String var23 : var1.getStringList("Death-Messages.PLAYER.OTHER")) {
         this.killMessages.add(this.prefix + this.c(var23));
      }

      this.player_suicide = this.prefix + this.c(var1.getString("Death-Messages.PLAYER.SUICIDE"));
      this.stats = new HashMap();

      for (String var24 : var1.getConfigurationSection("Stats").getKeys(false)) {
         this.stats.put(var24, this.c(var1.getString("Stats." + var24)));
      }

      this.arenaSettings = new ArrayList();

      for (String var25 : var1.getStringList("Arena-Settings")) {
         this.arenaSettings.add(this.c(var25));
      }

      this.matchConclusion = new ArrayList();

      for (String var26 : var1.getStringList("Match-Conclusion")) {
         this.matchConclusion.add(this.c(var26).replace("%star%", SPECIAL_CHARACTER.STAR.toString()));
      }

      this.normalArenaStart = new ArrayList();

      for (String var27 : var1.getStringList("Normal-Mode-Start")) {
         this.normalArenaStart.add(this.c(var27).replace("%star%", SPECIAL_CHARACTER.STAR.toString()));
      }

      this.plunderArenaStart = new ArrayList();

      for (String var28 : var1.getStringList("Plunder-Mode-Start")) {
         this.plunderArenaStart.add(this.c(var28).replace("%star%", SPECIAL_CHARACTER.STAR.toString()));
      }

      this.holographicStats = new ArrayList();

      for (String var29 : var1.getStringList("Holographic-Stats")) {
         this.holographicStats.add(this.c(var29).replace("%arrow%", SPECIAL_CHARACTER.ARROW.toString()));
      }

      this.inventories = new HashMap();

      for (String var30 : var1.getConfigurationSection("Inventories").getKeys(false)) {
         this.inventories.put(var30, this.c(var1.getString("Inventories." + var30)));
      }

      this.titles = new HashMap();

      for (String var31 : var1.getConfigurationSection("Titles").getKeys(false)) {
         this.titles.put(var31, this.c(var1.getString("Titles." + var31)));
      }

      this.lores = new HashMap();

      for (String var32 : var1.getConfigurationSection("Lores").getKeys(false)) {
         this.lores.put(var32, this.c(var1.getString("Lores." + var32)));
      }

      this.actionBars = new HashMap();

      for (String var33 : var1.getConfigurationSection("Action-Bars").getKeys(false)) {
         this.actionBars.put(var33, this.c(var1.getString("Action-Bars." + var33)));
      }

      for (ArenaState var5 : ArenaState.values()) {
         var5.value = this.c(var1.getString("States." + var5.name()));
      }

      this.lobby_scoreboard_title = this.c(var1.getString("Scoreboards.Lobby.Title").replace("%pointedstar%", String.valueOf(SPECIAL_CHARACTER.POINTED_STAR)));
      this.lobbyScoreboard = new String[var1.getStringList("Scoreboards.Lobby.Content").size()];
      int var19 = 0;

      for (String var39 : var1.getStringList("Scoreboards.Lobby.Content")) {
         this.lobbyScoreboard[var19++] = this.c(var39);
      }

      this.arena_lobby_scoreboard_title = this.c(
         var1.getString("Scoreboards.Arena-Lobby.Title").replace("%pointedstar%", String.valueOf(SPECIAL_CHARACTER.POINTED_STAR))
      );
      this.arenaLobbyScoreboard = new String[var1.getStringList("Scoreboards.Arena-Lobby.Content").size()];
      var19 = 0;

      for (String var40 : var1.getStringList("Scoreboards.Arena-Lobby.Content")) {
         this.arenaLobbyScoreboard[var19++] = this.c(var40);
      }

      this.arena_game_scoreboard_title = this.c(
         var1.getString("Scoreboards.Arena-Game.Title").replace("%pointedstar%", String.valueOf(SPECIAL_CHARACTER.POINTED_STAR))
      );
      this.arenaGameScoreboard = new String[var1.getStringList("Scoreboards.Arena-Game.Content").size()];
      var19 = 0;

      for (String var41 : var1.getStringList("Scoreboards.Arena-Game.Content")) {
         this.arenaGameScoreboard[var19++] = this.c(var41);
      }

      this.statsInventoryStat = this.c(var1.getString("Stats-Inventory-Styling.Stat"));
      this.statsInventoryValue = this.c(var1.getString("Stats-Inventory-Styling.Value"));
      this.holographicLeaderboardHeader = this.c(var1.getString("Holographic-Leaderboard-Styling.Header"));
      this.holographicLeaderboardBody = this.c(var1.getString("Holographic-Leaderboard-Styling.Body"));
      this.holographicLeaderboardFooter = this.c(var1.getString("Holographic-Leaderboard-Styling.Footer"));
      this.signs_title = this.c(var1.getString("Signs.Title"));
      this.signs_leave = this.c(var1.getString("Signs.Leave"));
      this.signs_autojoin = this.c(var1.getString("Signs.Autojoin"));
      this.signs_join = this.c(var1.getString("Signs.Join"));
      this.signs_arena_color = ChatColor.getByChar(var1.getString("Signs.Arena-Color"));
      this.signs_arena_players = this.c(var1.getString("Signs.Arena-Players"));
      this.leaderboard_title = this.c(var1.getString("Signs.Leaderboard-Title"));
      this.leaderboard_stat_color = ChatColor.getByChar(var1.getString("Signs.Leaderboard-Stat-Color"));
      this.items = new HashMap();

      for (String var42 : var1.getConfigurationSection("Items").getKeys(false)) {
         this.items.put(var42, Utils.getItemStack(var1.getString("Items." + var42), false, true));
      }

      this.items.put("Pane", new ItemStackBuilder(XMaterial.GRAY_STAINED_GLASS_PANE.parseItem()).setName(ChatColor.DARK_GRAY + " ").build());
      this.items.put("Green Pane", new ItemStackBuilder(XMaterial.LIME_STAINED_GLASS_PANE.parseItem()).setName(ChatColor.DARK_GRAY + " ").build());
      this.items.put("Save", new ItemStackBuilder(Material.DIAMOND).setName(ChatColor.AQUA + "Save & Apply").build());
      this.items.put("Plus", new ItemStackBuilder(XMaterial.LIME_STAINED_GLASS_PANE.parseItem()).setName(ChatColor.GREEN + "+").build());
      this.items.put("Minus", new ItemStackBuilder(XMaterial.RED_STAINED_GLASS_PANE.parseItem()).setName(ChatColor.RED + "-").build());
   }

   private String c(String var1) {
      return Utils.colorize(var1);
   }

   public static Customization getInstance() {
      return instance;
   }
}
