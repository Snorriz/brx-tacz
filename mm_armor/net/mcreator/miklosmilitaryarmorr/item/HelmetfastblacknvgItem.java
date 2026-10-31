package net.mcreator.miklosmilitaryarmorr.item;

import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import net.mcreator.miklosmilitaryarmorr.client.model.Modelhelmet_fast_black_nvg;
import net.mcreator.miklosmilitaryarmorr.init.MmArmorModItems;
import net.mcreator.miklosmilitaryarmorr.procedures.Helmet6b47emrnvgHelmetTickEventProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class HelmetfastblacknvgItem extends ArmorItem {
   public HelmetfastblacknvgItem(Type type, Properties properties) {
      super(new ArmorMaterial() {
         public int m_266425_(Type type) {
            return new int[]{13, 15, 16, 11}[type.m_266308_().m_20749_()] * 600;
         }

         public int m_7366_(Type type) {
            return new int[]{4, 4, 4, 6}[type.m_266308_().m_20749_()];
         }

         public int m_6646_() {
            return 5;
         }

         public SoundEvent m_7344_() {
            return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron"));
         }

         public Ingredient m_6230_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack((ItemLike)MmArmorModItems.LIGHTIRONPLATE.get())});
         }

         public String m_6082_() {
            return "helmetfastblacknvg";
         }

         public float m_6651_() {
            return 0.0F;
         }

         public float m_6649_() {
            return 0.1F;
         }
      }, type, properties);
   }

   public static class Helmet extends HelmetfastblacknvgItem {
      public Helmet() {
         super(Type.HELMET, new Properties().m_41486_());
      }

      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "head",
                           (new Modelhelmet_fast_black_nvg(Minecraft.m_91087_().m_167973_().m_171103_(Modelhelmet_fast_black_nvg.LAYER_LOCATION))).head,
                           "hat",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "body",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "right_arm",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "left_arm",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "right_leg",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "left_leg",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap())
                        )
                     )
                  );
                  armorModel.f_102817_ = living.m_6144_();
                  armorModel.f_102609_ = defaultModel.f_102609_;
                  armorModel.f_102610_ = living.m_6162_();
                  return armorModel;
               }
            }
         );
      }

      public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
         return "mm_armor:textures/entities/helmet_fast_black_nvg.png";
      }

      public void m_6883_(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
         super.m_6883_(itemstack, world, entity, slot, selected);
         if (entity instanceof Player player && Iterables.contains(player.m_6168_(), itemstack)) {
            Helmet6b47emrnvgHelmetTickEventProcedure.execute(entity);
         }
      }
   }
}
