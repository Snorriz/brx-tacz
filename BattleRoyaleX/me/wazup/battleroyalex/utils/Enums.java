package me.wazup.battleroyalex.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;

public class Enums {
   public enum AchievementType {
      GAMES_PLAYED("Play %x% game(s)!", 10, 1, 5, 10, 20, 50, 100, 200, 300, 400, 500, 750, 1000),
      NORMAL_WINS("Win %x% normal game(s)!", 100, 1, 5, 10, 20, 50, 100),
      PLUNDER_WINS("Win %x% plunder game(s)!", 100, 1, 5, 10, 20, 50, 100),
      KILLS("Get %x% kills!", 10, 10, 30, 50, 100, 150, 200, 500, 1000),
      PROJECTILES_HIT("Hit %x% projectile!", 2, 25, 50, 100, 250, 500, 1000),
      PLAYER_RANK("Reach rank %x%!", 100, 5, 10, 15, 20, 25, 30),
      BLOCKS_PLACED("Place %x% block(s)!", 1, 50, 200, 400, 1000, 2000),
      BLOCKS_BROKEN("Break %x% block(s)!", 1, 50, 200, 400, 1000, 2000);

      public String defaultDescription;
      public int prizeMultiplier;
      public int[] levels;

      AchievementType(String var3, int var4, int... var5) {
         this.defaultDescription = var3;
         this.prizeMultiplier = var4;
         this.levels = var5;
      }
   }

   public enum ArenaMode {
      Normal,
      Plunder;

      public static Enums.ArenaMode getByName(String var0) {
         for (Enums.ArenaMode var4 : values()) {
            if (var4.name().equalsIgnoreCase(var0)) {
               return var4;
            }
         }

         return null;
      }
   }

   public enum ArenaState {
      WAITING(null, true),
      STARTING(null, true),
      INGAME(null, false),
      ENDING(null, false),
      DISABLED(null, false);

      public String value;
      private boolean available;

      ArenaState(String var3, boolean var4) {
         this.value = var3;
         this.available = var4;
      }

      public String toString() {
         return this.value;
      }

      public boolean AVAILABLE() {
         return this.available;
      }
   }

   public enum LoadoutCategory {
      Helmets(0),
      Chestplates(1),
      Leggings(2),
      Boots(3),
      Main_Weapon(4),
      Secondary_Weapon(5),
      Items(6);

      public int slot;

      LoadoutCategory(int var3) {
         this.slot = var3 + 18;
      }
   }

   public enum PartyPrivacy {
      INVITE(ChatColor.RED + "Invite only"),
      PUBLIC(ChatColor.GREEN + "Public");

      String value;

      PartyPrivacy(String var3) {
         this.value = var3;
      }

      public String toString() {
         return this.value;
      }
   }

   public enum Rarity {
      COMMON(ChatColor.GREEN, "Common", 1),
      RARE(ChatColor.BLUE, "Rare", 2),
      LEGENDARY(ChatColor.GOLD, "Legendary", 3);

      private String value;
      private ChatColor c;
      private int power;

      Rarity(ChatColor var3, String var4, int var5) {
         this.value = var3 + var4;
         this.c = var3;
         this.power = var5;
      }

      public String toString() {
         return this.value;
      }

      public ChatColor getColor() {
         return this.c;
      }

      public int getPower() {
         return this.power;
      }
   }

   public enum SPECIAL_CHARACTER {
      ARROW(StringEscapeUtils.unescapeJava("➝")),
      HEART(StringEscapeUtils.unescapeJava("❤")),
      STAR(StringEscapeUtils.unescapeJava("✪")),
      POINTED_STAR(StringEscapeUtils.unescapeJava("✦")),
      BOX(StringEscapeUtils.unescapeJava("⬛")),
      EMPTY_BOX(StringEscapeUtils.unescapeJava("⬜")),
      RECTANGLE(StringEscapeUtils.unescapeJava("▌"));

      private String value;

      SPECIAL_CHARACTER(String var3) {
         this.value = var3;
      }

      public String toString() {
         return this.value;
      }
   }

   public enum Stat {
      COINS(0, false),
      KILLS(1, true),
      DEATHS(2, true),
      GAMES_PLAYED(3, true),
      NORMAL_WINS(4, true),
      PLUNDER_WINS(5, true),
      MODIFIER(6, false),
      PROJECTILES_HIT(7, true),
      PLAYER_EXP(8, true),
      BLOCKS_PLACED(9, true),
      BLOCKS_BROKEN(10, true);

      public int id;
      public boolean holoLeaderboard;

      Stat(int var3, boolean var4) {
         this.id = var3;
         this.holoLeaderboard = var4;
      }

      public static Enums.Stat getByName(String var0) {
         for (Enums.Stat var4 : values()) {
            if (var4.name().equals(var0)) {
               return var4;
            }
         }

         return null;
      }
   }
}
