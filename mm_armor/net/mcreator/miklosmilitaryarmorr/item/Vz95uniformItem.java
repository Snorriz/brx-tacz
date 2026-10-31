package net.mcreator.miklosmilitaryarmorr.item;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import net.mcreator.miklosmilitaryarmorr.client.model.Modelvz95_cap;
import net.mcreator.miklosmilitaryarmorr.client.model.Modelvz95_chestplate;
import net.mcreator.miklosmilitaryarmorr.client.model.Modelvz95_leggings;
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

public abstract class Vz95uniformItem extends ArmorItem {
   public Vz95uniformItem(Type type, Properties properties) {
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
            return "vz_95uniform";
         }

         public float m_6651_() {
            return 0.0F;
         }

         public float m_6649_() {
            return 0.1F;
         }
      }, type, properties);
   }

   public static class Chestplate extends Vz95uniformItem {
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
                           (new Modelvz95_chestplate(Minecraft.m_91087_().m_167973_().m_171103_(Modelvz95_chestplate.LAYER_LOCATION))).Body,
                           "left_arm",
                           (new Modelvz95_chestplate(Minecraft.m_91087_().m_167973_().m_171103_(Modelvz95_chestplate.LAYER_LOCATION))).LeftArm,
                           "right_arm",
                           (new Modelvz95_chestplate(Minecraft.m_91087_().m_167973_().m_171103_(Modelvz95_chestplate.LAYER_LOCATION))).RightArm,
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
         return "mm_armor:textures/entities/vz95_chestplate.png";
      }
   }

   public static class Helmet extends Vz95uniformItem {
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
                           (new Modelvz95_cap(Minecraft.m_91087_().m_167973_().m_171103_(Modelvz95_cap.LAYER_LOCATION))).Head,
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
         return "mm_armor:textures/entities/vz95_cap.png";
      }
   }

   public static class Leggings extends Vz95uniformItem {
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
                           (new Modelvz95_leggings(Minecraft.m_91087_().m_167973_().m_171103_(Modelvz95_leggings.LAYER_LOCATION))).LeftLeg,
                           "right_leg",
                           (new Modelvz95_leggings(Minecraft.m_91087_().m_167973_().m_171103_(Modelvz95_leggings.LAYER_LOCATION))).RightLeg,
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
         return "mm_armor:textures/entities/vz95_leggings.png";
      }
   }
}
