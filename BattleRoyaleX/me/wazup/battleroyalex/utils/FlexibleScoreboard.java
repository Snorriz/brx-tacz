package me.wazup.battleroyalex.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import me.wazup.battleroyalex.utils.Enums.SPECIAL_CHARACTER;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class FlexibleScoreboard {
   private Scoreboard scoreboard;
   private Objective objective;
   private HashMap<Integer, FlexibleScoreboard.LineEntry> lines = new HashMap();
   private HashMap<String, FlexibleScoreboard.PlaceholderEntry> placeholders = new HashMap();
   private HashMap<Integer, List<String>> multiplePlaceholders = new HashMap();
   private boolean showHealth;
   // TaxBattle: per-player placeholder values (e.g. %kills%) so a shared board can still show
   // personal info. Each player receives their own clone of the Bukkit scoreboard with these
   // values applied to the "%player%"-anchored line. See apply(Player).
   private final java.util.HashMap<String, String> globalValues = new java.util.HashMap<>();
   private final java.util.HashMap<String, java.util.HashMap<String, String>> playerValues = new java.util.HashMap<>();

   public void setPlaceholderValue(String ph, String value) {
      this.globalValues.put(ph, value);
   }

   public void setPlayerPlaceholder(String playerName, String ph, String value) {
      this.playerValues.computeIfAbsent(playerName, k -> new java.util.HashMap<>()).put(ph, value);
   }

   public FlexibleScoreboard(boolean var1, boolean var2, String var3, String... var4) {
      this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
      this.objective = this.scoreboard.registerNewObjective("BRX", "dummy");
      this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      this.objective.setDisplayName(this.format(var3));

      for (int var5 = 0; var5 < var4.length; var5++) {
         while (this.scoreboard.getEntries().contains(var4[var5])) {
            var4[var5] = var4[var5] + " ";
         }

         String var6 = var4[var5];
         String var7 = this.format(var4[var5]);
         int var8 = var4.length - var5;
         this.objective.getScore(var7).setScore(var8);
         this.lines.put(var8, new FlexibleScoreboard.LineEntry(var6, var7));
         int var9 = this.getCharacterCount(var6, '%');
         if (var9 > 0 && var9 % 2 == 0) {
            if (var9 / 2 > 1) {
               ArrayList<String> var10 = new ArrayList<>();

               for (byte var11 = 1; var11 < var9; var11 += 2) {
                  var10.add("%" + var6.split("%")[var11] + "%");
               }

               this.multiplePlaceholders.put(var8, var10);
            }

            for (byte var14 = 1; var14 < var9; var14 += 2) {
               this.placeholders.put("%" + var6.split("%")[var14] + "%", new FlexibleScoreboard.PlaceholderEntry(var8, "N/A"));
            }
         }
      }

      this.showHealth = var1 || var2;
      if (var1) {
         Objective var12 = this.scoreboard.registerNewObjective("BRX_HEALTH", "health");
         var12.setDisplaySlot(DisplaySlot.BELOW_NAME);
         var12.setDisplayName(ChatColor.DARK_RED + SPECIAL_CHARACTER.HEART.toString());
      }

      if (var2) {
         Objective var13 = this.scoreboard.registerNewObjective("BRX_HEALTH2", "health");
         var13.setDisplaySlot(DisplaySlot.PLAYER_LIST);
      }
   }

   public void update(String var1, String var2) {
      if (this.placeholders.containsKey(var1)) {
         FlexibleScoreboard.PlaceholderEntry var3 = (FlexibleScoreboard.PlaceholderEntry)this.placeholders.get(var1);
         var3.currentValue = var2;
         int var4 = var3.score;
         FlexibleScoreboard.LineEntry var5 = (FlexibleScoreboard.LineEntry)this.lines.get(var4);
         this.scoreboard.resetScores(var5.currentText);
         String var6 = var5.originalText;
         if (this.multiplePlaceholders.containsKey(var4)) {
            for (String var8 : (List<String>)this.multiplePlaceholders.get(var4)) {
               var6 = var6.replace(var8, ((FlexibleScoreboard.PlaceholderEntry)this.placeholders.get(var8)).currentValue);
            }
         } else {
            var6 = var6.replace(var1, var2);
         }

         while (this.scoreboard.getEntries().contains(var6)) {
            var6 = var6 + " ";
         }

         var6 = this.format(var6);
         this.objective.getScore(var6).setScore(var4);
         var5.currentText = var6;
      }
   }

   public void update(String var1, int var2) {
      this.update(var1, String.valueOf(var2));
   }

   private int getCharacterCount(String var1, char var2) {
      int var3 = 0;

      for (char var7 : var1.toCharArray()) {
         if (var7 == var2) {
            var3++;
         }
      }

      return var3;
   }

   private String format(String var1) {
      // Hex color tags &#RRGGBB are natively supported on 1.16+ (we run 1.20.1): convert them
      // to the legacy \u00A7x code sequence so gradients work in scoreboard titles/lines too.
      var1 = hexToLegacy(var1);
      int visible = visibleLength(var1);
      if (visible > 32) {
         var1 = truncateToVisible(var1, 32);
      } else if (visible > 16 && var1.startsWith("\u00A7x")) {
         // animated gradient headers can legitimately exceed the old 16-char limit on modern versions
      }
      return var1;
   }

   public static String hexToLegacy(String input) {
      if (input == null || input.indexOf('#') < 0) {
         return input;
      }
      StringBuilder out = new StringBuilder();
      int i = 0;
      while (i < input.length()) {
         if (input.charAt(i) == '&' && i + 7 < input.length() && input.charAt(i + 1) == '#') {
            String hex = input.substring(i + 2, i + 8);
            if (hex.matches("[0-9a-fA-F]{6}")) {
               out.append('\u00A7').append('x');
               for (char c : hex.toCharArray()) {
                  out.append('\u00A7').append(c);
               }
               i += 8;
               continue;
            }
         }
         out.append(input.charAt(i));
         i++;
      }
      return out.toString();
   }

   public static int visibleLength(String s) {
      int len = 0;
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == '\u00A7' && i + 1 < s.length()) {
            char code = Character.toLowerCase(s.charAt(i + 1));
            i++;
            if (code == 'x') {
               i += 6; // \u00A7x + six single-hex color codes
            }
         } else {
            len++;
         }
      }
      return len;
   }

   public static String truncateToVisible(String s, int maxVisible) {
      StringBuilder out = new StringBuilder();
      int visible = 0;
      boolean pendingX = false;
      for (int i = 0; i < s.length() && visible < maxVisible; i++) {
         char c = s.charAt(i);
         if (c == '\u00A7' && i + 1 < s.length()) {
            char code = Character.toLowerCase(s.charAt(i + 1));
            out.append(c).append(s.charAt(++i));
            if (code == 'x') {
               // copy up to 6 following hex color codes without counting them as visible
               int copied = 0;
               while (i + 2 < s.length() && s.charAt(i + 1) == '\u00A7' && copied < 6) {
                  out.append(s.charAt(++i)).append(s.charAt(++i));
                  copied++;
               }
            }
         } else {
            out.append(c);
            visible++;
         }
      }
      return out.toString();
   }

   public Team registerTeam(String var1) {
      return this.scoreboard.registerNewTeam(var1);
   }

   public Set<Team> getTeams() {
      return this.scoreboard.getTeams();
   }

   public void setName(String var1) {
      this.objective.setDisplayName(hexToLegacy(var1));
   }

   /** Replaces the display name every tick (used by the animated TaxPhobia gradient header). */
   public void setTitle(String var1) {
      this.setName(var1);
   }

   public void apply(Player var1) {
      // TaxBattle: assicura che i placeholder personali esistano e popola %player_name%
      if (!this.playerValues.containsKey(var1.getName())) {
         this.setPlayerPlaceholder(var1.getName(), "%player_kills%", "0");
      }
      this.setPlayerPlaceholder(var1.getName(), "%player_name%", var1.getName());
      java.util.HashMap<String, String> mine = this.playerValues.get(var1.getName());
      if (mine == null || mine.isEmpty()) {
         var1.setScoreboard(this.scoreboard);
      } else {
         // Per-player clone: copy the shared board and rewrite only the lines that carry
         // player-specific placeholders (e.g. "%player% Kills" -> "<nome> Kills").
         Scoreboard personal = Bukkit.getScoreboardManager().getNewScoreboard();
         Objective personalObj = personal.registerNewObjective("BRX_P", "dummy");
         personalObj.setDisplaySlot(DisplaySlot.SIDEBAR);
         personalObj.setDisplayName(this.objective.getDisplayName());
         for (java.util.Map.Entry<Integer, LineEntry> e : this.lines.entrySet()) {
            String text = e.getValue().originalText;
            for (java.util.Map.Entry<String, String> pv : mine.entrySet()) {
               text = text.replace(pv.getKey(), pv.getValue());
            }
            for (java.util.Map.Entry<String, PlaceholderEntry> g : this.placeholders.entrySet()) {
               text = text.replace(g.getKey(), g.getValue().currentValue);
            }
            personalObj.getScore(this.format(text)).setScore(e.getKey());
         }
         var1.setScoreboard(personal);
      }
      if (this.showHealth) {
         var1.setHealth(var1.getHealth() - 0.1);
      }
   }

   /** TaxBattle: updates a personal placeholder for one player and re-applies his board. */
   public void updateForPlayer(Player player, String placeholder, String value) {
      this.setPlayerPlaceholder(player.getName(), placeholder, value);
      this.apply(player);
   }

   private class LineEntry {
      String originalText;
      String currentText;

      private LineEntry(String var2, String var3) {
         this.originalText = var2;
         this.currentText = var3;
      }
   }

   private class PlaceholderEntry {
      int score;
      String currentValue;

      private PlaceholderEntry(int var2, String var3) {
         this.score = var2;
         this.currentValue = var3;
      }
   }
}
