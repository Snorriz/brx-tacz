package me.wazup.battleroyalex.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import me.wazup.battleroyalex.BattleRoyaleX;

public class MySQL {
   public String table;
   private String url;
   private String username;
   private String password;
   private Connection connection;
   private String TABLE;
   public String INSERT;
   public String UPDATE;
   public String UPDATE_STATS;
   public String SELECT;
   public String SELECTALL;
   public String DELETE_PLAYER;

   public MySQL(String var1, String var2, String var3, String var4, String var5, String var6) {
      this.url = "jdbc:mysql://" + var2 + ":" + var3 + "/" + var4;
      this.username = var5;
      this.password = var6;
      this.table = var1;
      this.TABLE = "create table if not exists "
         + var1
         + " (player_uuid char("
         + BattleRoyaleX.getInstance().getConfig().getInt("MySQL.max_uuid_size")
         + "), player_name varchar(16), stats varchar(90), kits varchar(500), loadouts varchar(100), selected varchar(50), celebrations varchar(300))";
      this.INSERT = "insert into " + var1 + " values(?, ?, ?, ?, ?, ?, ?)";
      this.UPDATE = "update "
         + var1
         + " set player_name=?, stats=?, kits=?, loadouts=?, selected=?, celebrations=? where "
         + (Config.getInstance().useUUID ? "player_uuid" : "player_name")
         + "=?";
      this.UPDATE_STATS = "update " + var1 + " set stats=? where player_name=?";
      this.SELECT = "select * from " + var1 + " where " + (Config.getInstance().useUUID ? "player_uuid" : "player_name") + "=?";
      this.SELECTALL = "select * from " + var1;
      this.DELETE_PLAYER = "delete from " + var1 + " where player_name=?";
   }

   public void close() {
      this.connection.close();
   }

   public void connect() {
      this.connection = DriverManager.getConnection(this.url, this.username, this.password);
   }

   public Connection getConnection() {
      if (this.connection == null || !this.connection.isValid(5)) {
         this.connect();
      }

      return this.connection;
   }

   public void setupTable() {
      Connection var1 = this.getConnection();
      Statement var2 = var1.createStatement();
      var2.executeUpdate(this.TABLE);
      if (!this.connection.getMetaData().getColumns(null, null, this.table, "celebrations").next()) {
         var2.executeUpdate("ALTER TABLE " + this.table + " ADD COLUMN celebrations VARCHAR(300)");
      }

      var2.close();
   }
}
