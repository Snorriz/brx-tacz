package me.xjqsh.lrtactical.item;

import com.tacz.guns.api.item.IAnimationItem;
import com.tacz.guns.client.renderer.item.AnimateGeoItemRenderer;
import java.util.Optional;
import java.util.function.Consumer;
import javax.annotation.ParametersAreNonnullByDefault;
import me.xjqsh.lrtactical.api.item.IThrowable;
import me.xjqsh.lrtactical.capability.CustomItemCoolDownsProvider;
import me.xjqsh.lrtactical.client.renderer.item.ThrowableItemRendererWrapper;
import me.xjqsh.lrtactical.entity.ThrowableItemEntity;
import me.xjqsh.lrtactical.init.ModItems;
import me.xjqsh.lrtactical.inventory.tooltip.ThrowableTooltip;
import me.xjqsh.lrtactical.item.index.ThrowableIndex;
import me.xjqsh.lrtactical.item.throwable.ThrowableData;
import me.xjqsh.lrtactical.item.throwable.explode.ExplodeThrowableData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class ThrowableItem extends Item implements IAnimationItem, IThrowable {
   public ThrowableItem() {
      super(new Properties().m_41487_(1));
   }

   public int getMaxStackSize(ItemStack stack) {
      return (Integer)this.getThrowableIndex(stack).map(ThrowableIndex::getMaxStackSize).orElse(1);
   }

   @ParametersAreNonnullByDefault
   public int m_8105_(ItemStack pStack) {
      return 72000;
   }

   public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
      return true;
   }

   @NotNull
   public UseAnim m_6164_(@NotNull ItemStack pStack) {
      return UseAnim.BOW;
   }

   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
      consumer.accept(new IClientItemExtensions() {
         private ThrowableItemRendererWrapper renderer = null;

         public ThrowableItemRendererWrapper getCustomRenderer() {
            if (this.renderer == null) {
               this.renderer = new ThrowableItemRendererWrapper();
            }

            return this.renderer;
         }
      });
   }

   @OnlyIn(Dist.CLIENT)
   public void triggerAnimation(ItemStack stack, String animationName) {
      if (IClientItemExtensions.of(stack.m_41720_()).getCustomRenderer() instanceof AnimateGeoItemRenderer<?, ?> renderer) {
         renderer.triggerAnimation(stack, animationName);
      }
   }

   @ParametersAreNonnullByDefault
   @NotNull
   public InteractionResultHolder<ItemStack> m_7203_(Level pLevel, Player player, InteractionHand pUsedHand) {
      if (pUsedHand == InteractionHand.OFF_HAND) {
         return InteractionResultHolder.m_19100_(player.m_21120_(pUsedHand));
      }

      ItemStack stack = player.m_21120_(pUsedHand);
      boolean flag = (Boolean)this.getThrowableIndex(stack)
         .map(index -> index.getData().getCooldownCategory())
         .map(id -> (Boolean)player.getCapability(CustomItemCoolDownsProvider.CAPABILITY).map(cap -> cap.isOnCooldown(id)).orElse(false))
         .orElse(false);
      if (!flag) {
         player.m_6672_(pUsedHand);
      }

      return InteractionResultHolder.m_19096_(stack);
   }

   public void onThrow(Level world, LivingEntity entity, ItemStack stack, ThrowableIndex<?, ?> index) {
      ThrowableItemEntity throwable = index.createEntity(stack, entity);
      if (index.getData().isCookable()) {
         int newLife = throwable.getLife() - (entity.m_21252_() - index.getData().getPrepareTime());
         newLife = Math.max(newLife, 0);
         throwable.setLife(newLife);
      }

      world.m_7967_(throwable);
      ResourceLocation id = index.getData().getCooldownCategory();
      if (id != null) {
         entity.getCapability(CustomItemCoolDownsProvider.CAPABILITY).ifPresent(cap -> cap.addCooldown(id, index.getData().getCooldown()));
      }

      stack.m_41774_(1);
      if (index.getData() instanceof ExplodeThrowableData explode && explode.getExplode().isRemoteDetonation()) {
         ItemStack detonatorStack = new ItemStack((ItemLike)ModItems.DETONATOR.get());
         if (detonatorStack.m_41720_() instanceof DetonatorItem detonatorItem) {
            detonatorItem.recordEntity(throwable, detonatorStack);
         }

         entity.m_21008_(InteractionHand.MAIN_HAND, detonatorStack);
      }
   }

   @ParametersAreNonnullByDefault
   public void m_5929_(Level world, LivingEntity entity, ItemStack stack, int pRemainingUseDuration) {
      this.getThrowableIndex(stack).ifPresent(index -> {
         ThrowableData data = index.getData();
         if (data.isCookable() && entity.m_21252_() >= data.getPrepareTime() + data.getEntityData().getLifeTime() && !world.m_5776_()) {
            this.onThrow(world, entity, stack, index);
            entity.m_5810_();
         }
      });
   }

   @ParametersAreNonnullByDefault
   public void m_5551_(ItemStack stack, Level world, LivingEntity entity, int timeLeft) {
      this.getThrowableIndex(stack).ifPresent(index -> {
         if (entity.m_21252_() >= index.getData().getPrepareTime() && !world.m_5776_()) {
            this.onThrow(world, entity, stack, index);
         }
      });
   }

   public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
      super.onStopUsing(stack, entity, count);
   }

   public boolean m_41463_(@NotNull ItemStack pStack) {
      return true;
   }

   @NotNull
   public String m_5671_(@NotNull ItemStack stack) {
      return (String)this.getThrowableIndex(stack).map(ThrowableIndex::getDescriptionId).orElse(super.m_5671_(stack));
   }

   public boolean isSame(ItemStack stack1, ItemStack stack2) {
      return super.isSame(stack1, stack2);
   }

   public Optional<TooltipComponent> m_142422_(ItemStack stack) {
      return this.getThrowableIndex(stack).isEmpty() ? Optional.empty() : Optional.of(new ThrowableTooltip(stack));
   }
}
