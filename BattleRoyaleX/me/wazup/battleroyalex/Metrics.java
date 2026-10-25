package me.wazup.battleroyalex;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import me.coley.recaf.metadata.InsnComment;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Metrics {
   private final Plugin plugin;
   private final Metrics.MetricsBase metricsBase;

   public Metrics(JavaPlugin var1, int var2) {
      this.plugin = var1;
      File var3 = new File(var1.getDataFolder().getParentFile(), "bStats");
      File var4 = new File(var3, "config.yml");
      YamlConfiguration var5 = YamlConfiguration.loadConfiguration(var4);
      if (!var5.isSet("serverUuid")) {
         var5.addDefault("enabled", true);
         var5.addDefault("serverUuid", UUID.randomUUID().toString());
         var5.addDefault("logFailedRequests", false);
         var5.addDefault("logSentData", false);
         var5.addDefault("logResponseStatusText", false);
         var5.options()
            .header(
               "bStats (https://bStats.org) collects some basic information for plugin authors, like how\nmany people use their plugin and their total player count. It's recommended to keep bStats\nenabled, but if you're not comfortable with this, you can turn this setting off. There is no\nperformance penalty associated with having metrics enabled, and data sent to bStats is fully\nanonymous."
            )
            .copyDefaults(true);

         try {
            var5.save(var4);
         } catch (IOException var11) {
         }
      }

      boolean var6 = var5.getBoolean("enabled", true);
      String var7 = var5.getString("serverUuid");
      boolean var8 = var5.getBoolean("logFailedRequests", false);
      boolean var9 = var5.getBoolean("logSentData", false);
      boolean var10 = var5.getBoolean("logResponseStatusText", false);
      this.metricsBase = new Metrics.MetricsBase(
         "bukkit",
         var7,
         var2,
         var6,
         this::appendPlatformData,
         this::appendServiceData,
         var1x -> Bukkit.getScheduler().runTask(var1, var1x),
         var1::isEnabled,
         (var1x, var2x) -> this.plugin.getLogger().log(Level.WARNING, var1x, var2x),
         var1x -> this.plugin.getLogger().log(Level.INFO, var1x),
         var8,
         var9,
         var10
      );
   }

   public void addCustomChart(Metrics.CustomChart var1) {
      this.metricsBase.addCustomChart(var1);
   }

   private void appendPlatformData(Metrics.JsonObjectBuilder var1) {
      var1.appendField("playerAmount", this.getPlayerAmount());
      var1.appendField("onlineMode", Bukkit.getOnlineMode() ? 1 : 0);
      var1.appendField("bukkitVersion", Bukkit.getVersion());
      var1.appendField("bukkitName", Bukkit.getName());
      var1.appendField("javaVersion", System.getProperty("java.version"));
      var1.appendField("osName", System.getProperty("os.name"));
      var1.appendField("osArch", System.getProperty("os.arch"));
      var1.appendField("osVersion", System.getProperty("os.version"));
      var1.appendField("coreCount", Runtime.getRuntime().availableProcessors());
   }

   private void appendServiceData(Metrics.JsonObjectBuilder var1) {
      var1.appendField("pluginVersion", this.plugin.getDescription().getVersion());
   }

   @InsnComment(At_1 = "kTTzcnb55kSplWZHJ0ZBZETjpmUIRmaNZDaE1Edx0WZ5lFRVl3bKhUeyAzRBNTb0JzN4o3M")
   private int getPlayerAmount() {
      int i1 = 0;

      for (World v3 : Bukkit.getWorlds()) {
         i1 += v3.getPlayers().size();
      }

      return i1;
   }

   public abstract static class CustomChart {
      private final String chartId;

      protected CustomChart(String var1) {
         if (var1 == null) {
            throw new IllegalArgumentException("chartId must not be null");
         }

         this.chartId = var1;
      }

      public Metrics.JsonObjectBuilder.JsonObject getRequestJsonObject(BiConsumer<String, Throwable> var1, boolean var2) {
         Metrics.JsonObjectBuilder var3 = new Metrics.JsonObjectBuilder();
         var3.appendField("chartId", this.chartId);

         try {
            Metrics.JsonObjectBuilder.JsonObject var4 = this.getChartData();
            if (var4 == null) {
               return null;
            }

            var3.appendField("data", var4);
         } catch (Throwable var5) {
            if (var2) {
               var1.accept("Failed to get data for custom chart with id " + this.chartId, var5);
            }

            return null;
         }

         return var3.build();
      }

      protected abstract Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception;
   }

   public static class JsonObjectBuilder {
      private StringBuilder builder = new StringBuilder();
      private boolean hasAtLeastOneField = false;

      public JsonObjectBuilder() {
         this.builder.append("{");
      }

      public Metrics.JsonObjectBuilder appendField(String var1, String var2) {
         if (var2 == null) {
            throw new IllegalArgumentException("JSON value must not be null");
         }

         this.appendFieldUnescaped(var1, "\"" + escape(var2) + "\"");
         return this;
      }

      public Metrics.JsonObjectBuilder appendField(String var1, int var2) {
         this.appendFieldUnescaped(var1, String.valueOf(var2));
         return this;
      }

      public Metrics.JsonObjectBuilder appendField(String var1, Metrics.JsonObjectBuilder.JsonObject var2) {
         if (var2 == null) {
            throw new IllegalArgumentException("JSON object must not be null");
         }

         this.appendFieldUnescaped(var1, var2.toString());
         return this;
      }

      public Metrics.JsonObjectBuilder appendField(String var1, Metrics.JsonObjectBuilder.JsonObject[] var2) {
         if (var2 == null) {
            throw new IllegalArgumentException("JSON values must not be null");
         }

         String var3 = (String)Arrays.stream(var2).map(Metrics.JsonObjectBuilder.JsonObject::toString).collect(Collectors.joining(","));
         this.appendFieldUnescaped(var1, "[" + var3 + "]");
         return this;
      }

      private void appendFieldUnescaped(String var1, String var2) {
         if (this.builder == null) {
            throw new IllegalStateException("JSON has already been built");
         }

         if (var1 == null) {
            throw new IllegalArgumentException("JSON key must not be null");
         }

         if (this.hasAtLeastOneField) {
            this.builder.append(",");
         }

         this.builder.append("\"").append(escape(var1)).append("\":").append(var2);
         this.hasAtLeastOneField = true;
      }

      public Metrics.JsonObjectBuilder.JsonObject build() {
         if (this.builder == null) {
            throw new IllegalStateException("JSON has already been built");
         }

         Metrics.JsonObjectBuilder.JsonObject var1 = new Metrics.JsonObjectBuilder.JsonObject(this.builder.append("}").toString());
         this.builder = null;
         return var1;
      }

      private static String escape(String var0) {
         StringBuilder var1 = new StringBuilder();

         for (int var2 = 0; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            if (var3 == '"') {
               var1.append("\\\"");
            } else if (var3 == '\\') {
               var1.append("\\\\");
            } else if (var3 <= 15) {
               var1.append("\\u000").append(Integer.toHexString(var3));
            } else if (var3 <= 31) {
               var1.append("\\u00").append(Integer.toHexString(var3));
            } else {
               var1.append(var3);
            }
         }

         return var1.toString();
      }

      public static class JsonObject {
         private final String value;

         private JsonObject(String var1) {
            this.value = var1;
         }

         public String toString() {
            return this.value;
         }
      }
   }

   public static class MetricsBase {
      public static final String METRICS_VERSION = "2.2.1";
      private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, var0 -> new Thread(var0, "bStats-Metrics"));
      private static final String REPORT_URL = "https://bStats.org/api/v2/data/%s";
      private final String platform;
      private final String serverUuid;
      private final int serviceId;
      private final Consumer<Metrics.JsonObjectBuilder> appendPlatformDataConsumer;
      private final Consumer<Metrics.JsonObjectBuilder> appendServiceDataConsumer;
      private final Consumer<Runnable> submitTaskConsumer;
      private final Supplier<Boolean> checkServiceEnabledSupplier;
      private final BiConsumer<String, Throwable> errorLogger;
      private final Consumer<String> infoLogger;
      private final boolean logErrors;
      private final boolean logSentData;
      private final boolean logResponseStatusText;
      private final Set<Metrics.CustomChart> customCharts = new HashSet();
      private final boolean enabled;

      public MetricsBase(
         String var1,
         String var2,
         int var3,
         boolean var4,
         Consumer<Metrics.JsonObjectBuilder> var5,
         Consumer<Metrics.JsonObjectBuilder> var6,
         Consumer<Runnable> var7,
         Supplier<Boolean> var8,
         BiConsumer<String, Throwable> var9,
         Consumer<String> var10,
         boolean var11,
         boolean var12,
         boolean var13
      ) {
         this.platform = var1;
         this.serverUuid = var2;
         this.serviceId = var3;
         this.enabled = var4;
         this.appendPlatformDataConsumer = var5;
         this.appendServiceDataConsumer = var6;
         this.submitTaskConsumer = var7;
         this.checkServiceEnabledSupplier = var8;
         this.errorLogger = var9;
         this.infoLogger = var10;
         this.logErrors = var11;
         this.logSentData = var12;
         this.logResponseStatusText = var13;
         this.checkRelocation();
         if (var4) {
            this.startSubmitting();
         }
      }

      public void addCustomChart(Metrics.CustomChart var1) {
         this.customCharts.add(var1);
      }

      private void startSubmitting() {
         Runnable var1 = () -> {
            if (this.enabled && (Boolean)this.checkServiceEnabledSupplier.get()) {
               if (this.submitTaskConsumer != null) {
                  this.submitTaskConsumer.accept(this::submitData);
               } else {
                  this.submitData();
               }
            } else {
               scheduler.shutdown();
            }
         };
         long var2 = (long)(60000.0 * (3.0 + Math.random() * 3.0));
         long var4 = (long)(60000.0 * (Math.random() * 30.0));
         scheduler.schedule(var1, var2, TimeUnit.MILLISECONDS);
         scheduler.scheduleAtFixedRate(var1, var2 + var4, 1800000L, TimeUnit.MILLISECONDS);
      }

      private void submitData() {
         Metrics.JsonObjectBuilder var1 = new Metrics.JsonObjectBuilder();
         this.appendPlatformDataConsumer.accept(var1);
         Metrics.JsonObjectBuilder var2 = new Metrics.JsonObjectBuilder();
         this.appendServiceDataConsumer.accept(var2);
         Metrics.JsonObjectBuilder.JsonObject[] var3 = (Metrics.JsonObjectBuilder.JsonObject[])this.customCharts
            .stream()
            .map(var1x -> var1x.getRequestJsonObject(this.errorLogger, this.logErrors))
            .filter(Objects::nonNull)
            .toArray(Metrics.JsonObjectBuilder.JsonObject[]::new);
         var2.appendField("id", this.serviceId);
         var2.appendField("customCharts", var3);
         var1.appendField("service", var2.build());
         var1.appendField("serverUUID", this.serverUuid);
         var1.appendField("metricsVersion", "2.2.1");
         Metrics.JsonObjectBuilder.JsonObject var4 = var1.build();
         scheduler.execute(() -> {
            try {
               this.sendData(var4);
            } catch (Exception var3x) {
               if (this.logErrors) {
                  this.errorLogger.accept("Could not submit bStats metrics data", var3x);
               }
            }
         });
      }

      private void sendData(Metrics.JsonObjectBuilder.JsonObject var1) throws Exception {
         if (this.logSentData) {
            this.infoLogger.accept("Sent bStats metrics data: " + var1.toString());
         }

         String var2 = String.format("https://bStats.org/api/v2/data/%s", new Object[]{this.platform});
         HttpsURLConnection var3 = (HttpsURLConnection)new URL(var2).openConnection();
         byte[] var4 = compress(var1.toString());
         var3.setRequestMethod("POST");
         var3.addRequestProperty("Accept", "application/json");
         var3.addRequestProperty("Connection", "close");
         var3.addRequestProperty("Content-Encoding", "gzip");
         var3.addRequestProperty("Content-Length", String.valueOf(var4.length));
         var3.setRequestProperty("Content-Type", "application/json");
         var3.setRequestProperty("User-Agent", "Metrics-Service/1");
         var3.setDoOutput(true);
         DataOutputStream var5 = new DataOutputStream(var3.getOutputStream());
         Throwable var6 = null;

         try {
            var5.write(var4);
         } catch (Throwable var29) {
            var6 = var29;
            throw var29;
         } finally {
            if (var5 != null) {
               if (var6 != null) {
                  try {
                     var5.close();
                  } catch (Throwable var28) {
                     var6.addSuppressed(var28);
                  }
               } else {
                  var5.close();
               }
            }
         }

         StringBuilder var33 = new StringBuilder();
         BufferedReader var34 = new BufferedReader(new InputStreamReader(var3.getInputStream()));
         Throwable var7 = null;

         try {
            String var8;
            try {
               while ((var8 = var34.readLine()) != null) {
                  var33.append(var8);
               }
            } catch (Throwable var31) {
               var7 = var31;
               throw var31;
            }
         } finally {
            if (var34 != null) {
               if (var7 != null) {
                  try {
                     var34.close();
                  } catch (Throwable var27) {
                     var7.addSuppressed(var27);
                  }
               } else {
                  var34.close();
               }
            }
         }

         if (this.logResponseStatusText) {
            this.infoLogger.accept("Sent data to bStats and received response: " + var33);
         }
      }

      private void checkRelocation() {
         if (System.getProperty("bstats.relocatecheck") == null || !System.getProperty("bstats.relocatecheck").equals("false")) {
            String var1 = new String(new byte[]{111, 114, 103, 46, 98, 115, 116, 97, 116, 115});
            String var2 = new String(new byte[]{121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101});
            if (Metrics.MetricsBase.class.getPackage().getName().startsWith(var1) || Metrics.MetricsBase.class.getPackage().getName().startsWith(var2)) {
               throw new IllegalStateException("bStats Metrics class has not been relocated correctly!");
            }
         }
      }

      private static byte[] compress(String var0) throws IOException {
         if (var0 == null) {
            return null;
         }

         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         GZIPOutputStream var2 = new GZIPOutputStream(var1);
         Throwable var3 = null;

         try {
            var2.write(var0.getBytes(StandardCharsets.UTF_8));
         } catch (Throwable var12) {
            var3 = var12;
            throw var12;
         } finally {
            if (var2 != null) {
               if (var3 != null) {
                  try {
                     var2.close();
                  } catch (Throwable var11) {
                     var3.addSuppressed(var11);
                  }
               } else {
                  var2.close();
               }
            }
         }

         return var1.toByteArray();
      }
   }

   public static class SimplePie extends Metrics.CustomChart {
      private final Callable<String> callable;

      public SimplePie(String var1, Callable<String> var2) {
         super(var1);
         this.callable = var2;
      }

      @Override
      protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
         String var1 = (String)this.callable.call();
         return var1 != null && !var1.isEmpty() ? new Metrics.JsonObjectBuilder().appendField("value", var1).build() : null;
      }
   }
}
