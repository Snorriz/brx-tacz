package me.wazup.battleroyalex.utils;

import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import me.wazup.battleroyalex.BattleRoyaleX;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class Bus {
   public ArmorStand mainStand;
   ArrayList<ArmorStand> parts;
   private double initialZ;
   private HashMap<ArmorStand, Vector> vectors = new HashMap();
   private HashMap<ArmorStand, Double> multiplys = new HashMap();
   private HashMap<ArmorStand, Float> rValues = new HashMap();
   private float j = 90.0F;

   public Bus(Location var1) {
      this.spawn(var1);
      this.initialZ = Float.parseFloat(this.mainStand.getCustomName());

      for (ArmorStand var3 : this.parts) {
         String[] var4 = var3.getCustomName().split(";");
         Vector var5 = this.vectorFromString(var4[3]);
         this.vectors.put(var3, var5);
         this.multiplys.put(var3, Double.parseDouble(var4[2].replace(",", ".")));
         if (var4.length > 4 && !var4[4].equals("SecondSeat")) {
            this.rValues.put(var3, Float.parseFloat(var4[4]));
         }
      }
   }

   public void moveTo(Location var1) {
      this.mainStand.teleport(var1);

      for (ArmorStand var3 : this.parts) {
         this.positionPart(this.mainStand, var3);
      }
   }

   public void destroy() {
      this.mainStand.remove();

      for (ArmorStand var2 : this.parts) {
         var2.remove();
      }
   }

   private void spawn(Location var1) {
      var1 = var1.add(0.5, 0.0, -0.1);
      this.parts = this.loadStructure(var1);
      Location var2 = var1.clone().add(new Vector(-0.02, 0.0, 0.6));
      var2.setYaw(180.0F);
      var2.setPitch(0.0F);
      this.mainStand = (ArmorStand)var2.getWorld().spawnEntity(var2, EntityType.ARMOR_STAND);
      this.mainStand.setGravity(false);
      this.mainStand.setVisible(false);
      this.mainStand.setCustomName(String.valueOf(this.mainStand.getLocation().getDirection().getZ()));

      for (ArmorStand var4 : this.parts) {
         float var5 = this.j - var4.getLocation().getYaw();
         if (var4.getHelmet().getType() == XMaterial.GLASS_PANE.parseMaterial()
            && (
               var4.getHeadPose().getX() > 0.3 && var4.getHeadPose().getX() < 0.4
                  || var4.getHeadPose().getX() > 0.4 && var4.getHeadPose().getX() < 0.5 && var4.getHeadPose().getZ() != 0.0
            )) {
            var5 += 180.0F;
         }

         Vector var6 = this.getPositionVector(var4, this.mainStand);
         double var7 = this.getMultiple(var4, this.mainStand);
         var4.setCustomName("PlanePart;" + this.mainStand.getUniqueId() + ";" + var7 + ";" + this.vectortoString(var6) + ";" + var5);
      }

      this.mainStand.teleport(this.mainStand.getLocation().setDirection(this.getDirection(450.0F, this.mainStand.getLocation().getPitch())));
   }

   private ArrayList<ArmorStand> loadStructure(Location var1) {
      YamlConfiguration var2 = YamlConfiguration.loadConfiguration(new InputStreamReader(BattleRoyaleX.getInstance().getResource("Plane.schem")));
      ArrayList var3 = new ArrayList();

      for (int var4 = 0; var2.contains(String.valueOf(var4)); var4++) {
         int var5 = var4;
         ItemStack var6 = var2.getItemStack(var5 + ".helmet");
         ItemStack var7 = var2.getItemStack(var5 + ".leggings");
         ItemStack var8 = var2.getItemStack(var5 + ".chestplate");
         ItemStack var9 = var2.getItemStack(var5 + ".boots");
         ItemStack var10 = var2.getItemStack(var5 + ".hand");
         boolean var11 = var2.getBoolean(var5 + ".visible");
         boolean var12 = var2.getBoolean(var5 + ".small");
         boolean var13 = var2.getBoolean(var5 + ".arms");
         boolean var14 = var2.getBoolean(var5 + ".plate");
         EulerAngle var15 = new EulerAngle(var2.getDouble(var5 + ".body.x"), var2.getDouble(var5 + ".body.y"), var2.getDouble(var5 + ".body.z"));
         EulerAngle var16 = new EulerAngle(var2.getDouble(var5 + ".head.x"), var2.getDouble(var5 + ".head.y"), var2.getDouble(var5 + ".head.z"));
         EulerAngle var17 = new EulerAngle(var2.getDouble(var5 + ".leftarm.x"), var2.getDouble(var5 + ".leftarm.y"), var2.getDouble(var5 + ".leftarm.z"));
         EulerAngle var18 = new EulerAngle(var2.getDouble(var5 + ".rightarm.x"), var2.getDouble(var5 + ".rightarm.y"), var2.getDouble(var5 + ".rightarm.z"));
         EulerAngle var19 = new EulerAngle(var2.getDouble(var5 + ".leftleg.x"), var2.getDouble(var5 + ".leftleg.y"), var2.getDouble(var5 + ".leftleg.z"));
         EulerAngle var20 = new EulerAngle(var2.getDouble(var5 + ".rightleg.x"), var2.getDouble(var5 + ".rightleg.y"), var2.getDouble(var5 + ".rightleg.z"));
         float var21 = Float.parseFloat(var2.get(var5 + ".pitch").toString());
         float var22 = Float.parseFloat(var2.get(var5 + ".yaw").toString());
         double var23 = var2.getDouble(var5 + ".position.x");
         double var25 = var2.getDouble(var5 + ".position.y");
         double var27 = var2.getDouble(var5 + ".position.z");
         Location var29 = new Location(var1.getWorld(), var1.getX() - var23, var1.getY() - var25, var1.getZ() - var27);
         if (var10.getType() != Material.AIR && (var10.getType().isBlock() || var10.getType() == Material.PLAYER_HEAD)) {
            if (var10.getType() != Material.PLAYER_HEAD) {
               var18 = new EulerAngle(4.8694687F, Math.PI / 2, 1.9896754F);
            }

            var13 = true;
            if (!var10.getType().toString().endsWith("_WOOL") && var10.getType() != Material.PLAYER_HEAD && var10.getType() != Material.BRICKS) {
               var29.setY(var29.getY() - 0.41);
               var29.setX(var29.getX() - 0.34);
            } else {
               var18 = new EulerAngle(-0.80285144F, Math.PI * 3.0 / 4.0, -Math.PI / 180.0);
               var22 = 180.0F;
               if (var10.getType() == Material.BRICKS) {
                  var29.setY(var29.getY());
                  var29.setX(var29.getX());
                  var29.setZ(var29.getZ() - 0.71);
               } else {
                  var29.setY(var29.getY() + 0.12);
                  var29.setX(var29.getX() - 0.45);
                  var29.setZ(var29.getZ() - 0.61);
               }
            }
         }

         if (var10.getType() == XMaterial.ELYTRA.parseMaterial()) {
            var29.setX(var29.getX() + 0.03);
            var29.setY(var29.getY() + 0.09);
            var18 = new EulerAngle(Math.PI * 3.0 / 2.0, 0.0, 0.0);
         }

         if (var10.getType().toString().endsWith("_BANNER")) {
            var29.setY(var29.getY() + 0.12);
            var29.setZ(var29.getZ() + 0.04);
         }

         if (var10.getType() == Material.STONE_HOE) {
            var29.setY(var29.getY() + 0.16);
            if (var18.getY() < 3.0) {
               var18 = var18.setY(var18.getY() - 0.4);
            } else {
               var18 = var18.setY(var18.getY() + 0.4);
            }
         }

         if (var10.getType() == XMaterial.WOODEN_SHOVEL.parseMaterial()) {
            var29.setY(var29.getY() + 0.1);
         }

         var29.setPitch(var21);
         var29.setYaw(var22);
         ArmorStand var30 = (ArmorStand)var29.getWorld().spawnEntity(var29, EntityType.ARMOR_STAND);
         var30.setGravity(false);
         var30.teleport(var29);
         var30.setHelmet(var6);
         var30.setLeggings(var7);
         var30.setChestplate(var8);
         var30.setBoots(var9);
         var30.setItemInHand(var10);
         var30.setVisible(var11);
         var30.setSmall(var12);
         var30.setArms(var13);
         var30.setBasePlate(var14);
         var30.setBodyPose(var15);
         var30.setHeadPose(var16);
         var30.setLeftArmPose(var17);
         var30.setRightArmPose(var18);
         var30.setLeftLegPose(var19);
         var30.setRightLegPose(var20);
         var3.add(var30);
      }

      return var3;
   }

   private Vector vectorFromString(String var1) {
      Vector var2 = new Vector();
      String[] var3 = var1.split("/");

      try {
         var2.setX(Double.parseDouble(var3[0].replace(",", ".")));
      } catch (NumberFormatException var7) {
         var2.setX(0.044);
      }

      try {
         var2.setY(Double.parseDouble(var3[1].replace(",", ".")));
      } catch (NumberFormatException var6) {
         var2.setY(0.75);
      }

      try {
         var2.setZ(Double.parseDouble(var3[2].replace(",", ".")));
      } catch (NumberFormatException var5) {
         var2.setZ(0.168);
      }

      return var2;
   }

   private void positionPart(ArmorStand var1, ArmorStand var2) {
      Location var3 = var1.getLocation().clone();
      var3.setYaw(var3.getYaw() - 90.0F);
      Vector var4 = var3.getDirection().clone();
      Vector var5 = ((Vector)this.vectors.get(var2)).clone();
      double var6 = var5.getZ();
      Vector var8 = this.getDirection(var3.getYaw() + 90.0F, var3.getPitch());
      var3.add(var8.multiply((Double)this.multiplys.get(var2)));
      var5.setX(var4.getX() * var6 / this.initialZ);
      var5.setZ(var4.getZ() * var6 / this.initialZ);
      float var9 = 0.0F;
      if (this.rValues.containsKey(var2)) {
         var9 += this.rValues.get(var2);
      }

      var3.setPitch(var2.getLocation().getPitch());
      var3.setYaw(var1.getLocation().getYaw() + var9);
      var2.teleport(var3.subtract(var5));
   }

   private double getMultiple(ArmorStand var1, ArmorStand var2) {
      Vector var3 = this.getDirection(90.0F, var2.getLocation().getPitch());
      double var4 = -this.getPositionVector(var1, var2).clone().divide(var3.clone()).getX();
      String var6 = this.firstChars(var4);

      try {
         return Double.parseDouble(var6);
      } catch (NumberFormatException var8) {
         return 0.044;
      }
   }

   private Vector getDirection(float var1, float var2) {
      Vector var3 = new Vector();
      double var4 = var1;
      double var6 = var2;
      var3.setY(-Math.sin(Math.toRadians(var6)));
      double var8 = Math.cos(Math.toRadians(var6));
      var3.setX(-var8 * Math.sin(Math.toRadians(var4)));
      var3.setZ(var8 * Math.cos(Math.toRadians(var4)));
      return var3;
   }

   private Vector getPositionVector(ArmorStand var1, ArmorStand var2) {
      Location var3 = var2.getLocation();
      Location var4 = var1.getLocation();
      double var5 = var3.getX() - var4.getX();
      double var7 = var3.getY() - var4.getY();
      double var9 = var3.getZ() - var4.getZ();
      if (var1.getHelmet().getType() == XMaterial.GLASS_PANE.parseMaterial()) {
         if (var1.getHeadPose().getX() > 0.3 && var1.getHeadPose().getX() < 0.4) {
            if (var9 > 0.0) {
               var9 -= 0.8;
            } else {
               var9 += 0.8;
            }
         }

         if (var1.getHeadPose().getX() > 0.4 && var1.getHeadPose().getX() < 0.5 && var1.getHeadPose().getZ() != 0.0) {
            if (var9 > 0.0) {
               var9 -= 0.9;
            } else {
               var9 += 0.9;
            }
         }
      }

      return new Vector(var5, var7, var9);
   }

   private String vectortoString(Vector var1) {
      return this.firstChars(var1.getX()) + "/" + this.firstChars(var1.getY()) + "/" + this.firstChars(var1.getZ());
   }

   private String firstChars(double var1) {
      DecimalFormat var3 = new DecimalFormat("0.000");
      return var3.format(var1).replace(",", ".");
   }
}
