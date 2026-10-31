package net.mcreator.miklosmilitaryarmorr.item;

import net.mcreator.miklosmilitaryarmorr.procedures.HolycrossLivingEntityIsHitWithItemProcedure;
import net.mcreator.miklosmilitaryarmorr.procedures.HolycrossRightclickedProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;

public class HolycrossItem extends Item {
   public HolycrossItem() {
      super(new Properties().m_41503_(200).m_41497_(Rarity.EPIC));
   }

   public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
      InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
      HolycrossRightclickedProcedure.execute(world, entity, (ItemStack)ar.m_19095_());
      return ar;
   }

   public boolean m_7579_(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
      boolean retval = super.m_7579_(itemstack, entity, sourceentity);
      HolycrossLivingEntityIsHitWithItemProcedure.execute(entity.m_9236_(), entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
      return retval;
   }

   public boolean onDroppedByPlayer(ItemStack itemstack, Player entity) {
      HolycrossLivingEntityIsHitWithItemProcedure.execute(entity.m_9236_(), entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
      return true;
   }
}
