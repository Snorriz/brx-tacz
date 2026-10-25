package me.zombie_striker.qg.guns;
import me.zombie_striker.customitemmanager.CustomBaseObject;
import org.bukkit.inventory.ItemStack;
public class Gun extends CustomBaseObject {
   public me.zombie_striker.qg.ammo.Ammo getAmmoType() { return null; }
   public float getDamage() { return 0F; }
   public ItemStack getItemStack() { return null; }
   public String getInternalName() { return null; }
   public String getName() { return null; }
   public int getMaxBullets() { return 0; }
   public void setRecoil(double d) {}
   public static void updateAmmo(Gun g, ItemStack is, int amount) {}
}
