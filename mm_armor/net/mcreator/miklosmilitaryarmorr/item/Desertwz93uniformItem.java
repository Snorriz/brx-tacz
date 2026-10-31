package net.mcreator.miklosmilitaryarmorr.item;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import net.mcreator.miklosmilitaryarmorr.client.model.Modeldesert_wz_93_cap;
import net.mcreator.miklosmilitaryarmorr.client.model.Modeldesert_wz_93_chestplate;
import net.mcreator.miklosmilitaryarmorr.client.model.Modeldesert_wz_93_leggings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class Desertwz93uniformItem extends ArmorItem {
   public Desertwz93uniformItem(Type type, Properties properties) {
      super(new ArmorMaterial() {
         public int m_266425_(Type type) {
            return new int[]{13, 15, 16, 11}[type.m_266308_().m_20749_()] * 600;
         }

         public int m_7366_(Type type) {
            return new int[]{4, 3, 3, 1}[type.m_266308_().m_20749_()];
         }

         public int m_6646_() {
            return 5;
         }

         public SoundEvent m_7344_() {
            return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("mm_armor:clothes_wear"));
         }

         public Ingredient m_6230_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack(Items.f_42454_)});
         }

         public String m_6082_() {
            return "desertwz_93uniform";
         }

         public float m_6651_() {
            return 0.0F;
         }

         public float m_6649_() {
            return 0.1F;
         }
      }, type, properties);
   }

   public static class Chestplate extends Desertwz93uniformItem {
      public Chestplate() {
         super(Type.CHESTPLATE, new Properties());
      }

      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               @OnlyIn(Dist.CLIENT)
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "body",
                           (new Modeldesert_wz_93_chestplate(Minecraft.m_91087_().m_167973_().m_171103_(Modeldesert_wz_93_chestplate.LAYER_LOCATION))).Body,
                           "left_arm",
                           (new Modeldesert_wz_93_chestplate(Minecraft.m_91087_().m_167973_().m_171103_(Modeldesert_wz_93_chestplate.LAYER_LOCATION))).LeftArm,
                           "right_arm",
                           (new Modeldesert_wz_93_chestplate(Minecraft.m_91087_().m_167973_().m_171103_(Modeldesert_wz_93_chestplate.LAYER_LOCATION))).RightArm,
                           "head",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "hat",
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
         return "mm_armor:textures/entities/desert_wz_93_chestplate.png";
      }
   }

   public static class Helmet extends Desertwz93uniformItem {
      public Helmet() {
         super(Type.HELMET, new Properties());
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
                           (new Modeldesert_wz_93_cap(Minecraft.m_91087_().m_167973_().m_171103_(Modeldesert_wz_93_cap.LAYER_LOCATION))).Head,
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
         return "mm_armor:textures/entities/desert_wz_93_cap.png";
      }
   }

   public static class Leggings extends Desertwz93uniformItem {
      public Leggings() {
         super(Type.LEGGINGS, new Properties());
      }

      public void initializeClient(Consumer<IClientItemExtensions> consumer) {
         consumer.accept(
            new IClientItemExtensions() {
               @OnlyIn(Dist.CLIENT)
               public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                  HumanoidModel armorModel = new HumanoidModel(
                     new ModelPart(
                        Collections.emptyList(),
                        Map.of(
                           "left_leg",
                           (new Modeldesert_wz_93_leggings(Minecraft.m_91087_().m_167973_().m_171103_(Modeldesert_wz_93_leggings.LAYER_LOCATION))).LeftLeg,
                           "right_leg",
                           (new Modeldesert_wz_93_leggings(Minecraft.m_91087_().m_167973_().m_171103_(Modeldesert_wz_93_leggings.LAYER_LOCATION))).RightLeg,
                           "head",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "hat",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "body",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "right_arm",
                           new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                           "left_arm",
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
         return "mm_armor:textures/entities/desert_wz_93_leggings.png";
      }
   }
}
