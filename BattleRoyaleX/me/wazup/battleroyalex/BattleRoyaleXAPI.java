package me.wazup.battleroyalex;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import me.wazup.battleroyalex.managers.Config;
import me.wazup.battleroyalex.utils.Enums.Stat;
import org.bukkit.configuration.file.YamlConfiguration;

public class BattleRoyaleXAPI {
   public static HashMap<String, String> getAllPlayersData() {
      HashMap var0 = new HashMap();
      BattleRoyaleX var1 = BattleRoyaleX.getInstance();
      if (var1.mySQL != null) {
         Connection var2 = var1.mySQL.getConnection();
         PreparedStatement var3 = var2.prepareStatement(var1.mySQL.SELECTALL);
         ResultSet var4 = var3.executeQuery();

         while (var4.next()) {
            String var5 = var4.getString("player_name");
            var0.put(var5, var1.playerData.containsKey(var5) ? ((PlayerData)var1.playerData.get(var5)).getStatsString() : var4.getString("stats"));
         }

         var4.close();
         var3.close();
      } else {
         for (File var7 : getPlayersFiles()) {
            YamlConfiguration var8 = YamlConfiguration.loadConfiguration(var7);
            String var9 = var8.getString("Name");
            var0.put(var9, var1.playerData.containsKey(var9) ? ((PlayerData)var1.playerData.get(var9)).getStatsString() : var8.getString("Stats"));
         }
      }

      return var0;
   }

   public static List<Entry<String, Integer>> getTopPlayers(HashMap<String, String> var0, Stat var1, int var2) {
      if (var2 < 1) {
         throw new IllegalArgumentException("Amount must be a number above 0!");
      }

      HashMap var3 = new HashMap();

      for (String var5 : var0.keySet()) {
         var3.put(var5, Integer.valueOf(((String)var0.get(var5)).split(":")[var1.id]));
      }

      if (var3.size() < var2) {
         int var6 = var2 - var3.size() + 1;

         for (int var8 = 1; var8 < var6; var8++) {
            var3.put("NO_PLAYER" + var8, 0);
         }
      }

      LinkedList var7 = new LinkedList(var3.entrySet());
      Collections.sort(var7, new Comparator<Entry<String, Integer>>() {
         public int compare(Entry<String, Integer> var1, Entry<String, Integer> var2x) {
            return (Integer)var2x.getValue() - (Integer)var1.getValue();
         }
      });
      return var7;
   }

   public static List<File> getPlayersFiles() {
      ArrayList var0 = new ArrayList();
      File var1 = new File(BattleRoyaleX.getInstance().getDataFolder(), "players");
      if (var1.exists() && var1.isDirectory()) {
         for (File var5 : var1.listFiles()) {
            if (var5.isFile()) {
               YamlConfiguration var6 = YamlConfiguration.loadConfiguration(var5);
               if (var6.contains("Name") && var6.contains("Stats")) {
                  var0.add(var5);
               }
            }
         }
      }

      return var0;
   }

   public static boolean modifyOfflinePlayerStat(String var0, Stat var1, int var2, boolean var3) {
      BattleRoyaleX var4 = BattleRoyaleX.getInstance();
      boolean var5 = false;
      if (var4.mySQL != null) {
         Connection var6 = var4.mySQL.getConnection();
         PreparedStatement var7 = var6.prepareStatement(var4.mySQL.SELECT);
         var7.setString(1, var0);
         ResultSet var8 = var7.executeQuery();
         if (var8.next()) {
            String var9 = var8.getString("stats");
            int var10 = getCharsBeforePoint(var9, ':', var1.id);
            var9 = var9.substring(0, var10)
               + var9.substring(var10).replaceFirst(var9.split(":")[var1.id], String.valueOf(var3 ? Integer.valueOf(var9.split(":")[var1.id]) + var2 : var2));
            var7 = var6.prepareStatement(var4.mySQL.UPDATE_STATS);
            var7.setString(1, var9);
            var7.setString(2, var0);
            var7.execute();
            var5 = true;
         }

         var8.close();
         var7.close();
      } else {
         for (File var15 : getPlayersFiles()) {
            String var16 = Config.getInstance().useUUID ? YamlConfiguration.loadConfiguration(var15).getString("Name") : var15.getName();
            if (var16.equalsIgnoreCase(var0)) {
               YamlConfiguration var18 = YamlConfiguration.loadConfiguration(var15);
               String var19 = var18.getString("Stats");
               int var11 = getCharsBeforePoint(var19, ':', var1.id);
               var19 = var19.substring(0, var11)
                  + var19.substring(var11)
                     .replaceFirst(var19.split(":")[var1.id], String.valueOf(var3 ? Integer.valueOf(var19.split(":")[var1.id]) + var2 : var2));
               var18.set("Stats", var19);

               try {
                  var18.save(var15);
               } catch (IOException var13) {
                  var13.printStackTrace();
               }

               var5 = true;
               break;
            }
         }
      }

      return var5;
   }

   private static int getCharsBeforePoint(String var0, char var1, int var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var0.length(); var4++) {
         if (var0.charAt(var4) == var1) {
            var3++;
         }

         if (var3 == var2) {
            return var4 + 1;
         }
      }

      return -1;
   }
}
