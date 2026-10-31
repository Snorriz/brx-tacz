package net.mcreator.miklosmilitaryarmorr.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class WirecuttersItem extends TieredItem {
   public WirecuttersItem() {
      super(new Tier() {
         public int m_6609_() {
            return 20;
         }

         public float m_6624_() {
            return 4.0F;
         }

         public float m_6631_() {
            return 2.0F;
         }

         public int m_6604_() {
            return 1;
         }

         public int m_6601_() {
            return 4;
         }

         public Ingredient m_6282_() {
            return Ingredient.m_43927_(new ItemStack[]{new ItemStack(Items.f_42416_)});
         }
      }, new Properties());
   }

   public boolean m_8096_(BlockState blockstate) {
      return !blockstate.m_204336_(BlockTags.f_144285_) && !blockstate.m_204336_(BlockTags.f_144284_);
   }

   public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
      return ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction)
         || ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction)
         || ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction)
         || ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction)
         || ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
   }

   public float m_8102_(ItemStack itemstack, BlockState blockstate) {
      return 4.0F;
   }

   public Multimap<Attribute, AttributeModifier> m_7167_(EquipmentSlot equipmentSlot) {
      if (equipmentSlot == EquipmentSlot.MAINHAND) {
         Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
         builder.putAll(super.m_7167_(equipmentSlot));
         builder.put(Attributes.f_22281_, new AttributeModifier(f_41374_, "Tool modifier", 3.0, Operation.ADDITION));
         builder.put(Attributes.f_22283_, new AttributeModifier(f_41375_, "Tool modifier", -2.7, Operation.ADDITION));
         return builder.build();
      } else {
         return super.m_7167_(equipmentSlot);
      }
   }

   public boolean m_6813_(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
      itemstack.m_41622_(1, entity, i -> i.m_21166_(EquipmentSlot.MAINHAND));
      return true;
   }

   public boolean m_7579_(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      itemstack.m_41622_(2, entity, i -> i.m_21166_(EquipmentSlot.MAINHAND));
      return true;
   }

   public boolean hasCraftingRemainingItem(ItemStack stack) {
      return true;
   }

   public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
      ItemStack retval = new ItemStack(this);
      retval.m_41721_(itemstack.m_41773_() + 1);
      return retval.m_41773_() >= retval.m_41776_() ? ItemStack.f_41583_ : retval;
   }

   public boolean isRepairable(ItemStack itemstack) {
      return false;
   }
}
