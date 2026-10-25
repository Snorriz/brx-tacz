package me.wazup.battleroyalex.managers;

import org.bukkit.Sound;

public class SoundsManager {
   public static Sound CLICK;
   public static Sound NOTE_PLING;
   public static Sound ENDERDRAGON_GROWL;
   public static Sound ITEM_PICKUP;
   public static Sound ENDERMAN_TELEPORT;
   public static Sound WOLF_SHAKE;
   public static Sound FIREWORK_BLAST;
   public static Sound LAVA_POP;
   public static Sound ANVIL_LAND;

   public SoundsManager() {
      try {
         CLICK = Sound.valueOf("CLICK");
         NOTE_PLING = Sound.valueOf("NOTE_PLING");
         ENDERDRAGON_GROWL = Sound.valueOf("ENDERDRAGON_GROWL");
         ITEM_PICKUP = Sound.valueOf("ITEM_PICKUP");
         ENDERMAN_TELEPORT = Sound.valueOf("ENDERMAN_TELEPORT");
         WOLF_SHAKE = Sound.valueOf("WOLF_SHAKE");
         FIREWORK_BLAST = Sound.valueOf("FIREWORK_BLAST");
         LAVA_POP = Sound.valueOf("LAVA_POP");
         ANVIL_LAND = Sound.valueOf("ANVIL_LAND");
      } catch (IllegalArgumentException var4) {
         try {
            CLICK = Sound.valueOf("UI_BUTTON_CLICK");
            NOTE_PLING = Sound.valueOf("BLOCK_NOTE_PLING");
            ENDERDRAGON_GROWL = Sound.valueOf("ENTITY_ENDERDRAGON_GROWL");
            ITEM_PICKUP = Sound.valueOf("ENTITY_ITEM_PICKUP");
            ENDERMAN_TELEPORT = Sound.valueOf("ENTITY_ENDERMEN_TELEPORT");
            WOLF_SHAKE = Sound.valueOf("ENTITY_WOLF_SHAKE");
            FIREWORK_BLAST = Sound.valueOf("ENTITY_FIREWORK_BLAST");
            LAVA_POP = Sound.valueOf("BLOCK_LAVA_POP");
            ANVIL_LAND = Sound.valueOf("BLOCK_ANVIL_LAND");
         } catch (IllegalArgumentException var3) {
            CLICK = Sound.valueOf("UI_BUTTON_CLICK");
            NOTE_PLING = Sound.valueOf("BLOCK_NOTE_BLOCK_PLING");
            ENDERDRAGON_GROWL = Sound.valueOf("ENTITY_ENDER_DRAGON_GROWL");
            ITEM_PICKUP = Sound.valueOf("ENTITY_ITEM_PICKUP");
            ENDERMAN_TELEPORT = Sound.valueOf("ENTITY_ENDERMAN_TELEPORT");
            WOLF_SHAKE = Sound.valueOf("ENTITY_WOLF_SHAKE");
            FIREWORK_BLAST = Sound.valueOf("ENTITY_FIREWORK_ROCKET_BLAST");
            LAVA_POP = Sound.valueOf("BLOCK_LAVA_POP");
            ANVIL_LAND = Sound.valueOf("BLOCK_ANVIL_LAND");
         }
      }
   }
}
