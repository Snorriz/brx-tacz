package me.zombie_striker.qg.api;
import java.util.List;
import me.zombie_striker.qg.ammo.Ammo;
import me.zombie_striker.qg.armor.ArmorObject;
import me.zombie_striker.qg.guns.Gun;
import org.bukkit.inventory.ItemStack;
public class QualityArmory {
   public static Iterable<Gun> getGuns() { return null; }
   public static Iterable<me.zombie_striker.customitemmanager.CustomBaseObject> getMisc() { return null; }
   public static Gun getGunByName(String name) { return null; }
   public static Gun getGun(ItemStack is) { return null; }
   public static boolean isGun(ItemStack is) { return false; }
   public static Iterable<Ammo> getAmmo() { return null; }
   public static Iterable<ArmorObject> getArmor() { return null; }
   public static Ammo getAmmoByName(String name) { return null; }
   public static ArmorObject getArmorByName(String name) { return null; }
   public static ItemStack getCustomItemAsItemStack(String name) { return null; }
}
