package me.xjqsh.lrtactical.api.animation;

import com.tacz.guns.client.animation.statemachine.ItemAnimationStateContext;
import java.util.Optional;
import java.util.function.Function;
import me.xjqsh.lrtactical.api.item.IThrowable;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class BaseAnimationStateContext extends ItemAnimationStateContext {
   private ItemStack currentItem = ItemStack.f_41583_;
   private int usingTick = 0;
   private boolean using = false;
   private int prepareTime = 0;
   private float walkDistAnchor = 0.0F;

   public void setCurrentItem(ItemStack currentItem) {
      this.currentItem = currentItem;
      if (currentItem.m_41720_() instanceof IThrowable iThrowable) {
         this.prepareTime = (Integer)iThrowable.getThrowableIndex(currentItem).map(index -> index.getData().getPrepareTime()).orElse(0);
      }
   }

   protected <T> Optional<T> processCameraEntity(Function<Entity, T> processor) {
      Entity entity = Minecraft.m_91087_().f_91075_;
      return entity != null ? Optional.ofNullable(processor.apply(entity)) : Optional.empty();
   }

   public long getCurrentTimestamp() {
      return System.currentTimeMillis();
   }

   public boolean isInputUp() {
      return (Boolean)Optional.ofNullable(Minecraft.m_91087_().f_91074_).map(player -> player.f_108618_.f_108568_).orElse(false);
   }

   public boolean isInputDown() {
      return (Boolean)Optional.ofNullable(Minecraft.m_91087_().f_91074_).map(player -> player.f_108618_.f_108569_).orElse(false);
   }

   public boolean isInputLeft() {
      return (Boolean)Optional.ofNullable(Minecraft.m_91087_().f_91074_).map(player -> player.f_108618_.f_108570_).orElse(false);
   }

   public boolean isInputRight() {
      return (Boolean)Optional.ofNullable(Minecraft.m_91087_().f_91074_).map(player -> player.f_108618_.f_108571_).orElse(false);
   }

   public boolean isInputJumping() {
      return (Boolean)Optional.ofNullable(Minecraft.m_91087_().f_91074_).map(player -> player.f_108618_.f_108572_).orElse(false);
   }

   public boolean isOnGround() {
      return (Boolean)this.processCameraEntity(Entity::m_20096_).orElse(false);
   }

   public boolean isCrouching() {
      return (Boolean)this.processCameraEntity(Entity::m_6047_).orElse(false);
   }

   public void anchorWalkDist() {
      this.processCameraEntity(entity -> {
         this.walkDistAnchor = entity.f_19787_ + (entity.f_19787_ - entity.f_19867_) * this.partialTicks;
         return null;
      });
   }

   public float getWalkDist() {
      return (Float)this.processCameraEntity(entity -> {
         float currentWalkDist = entity.f_19787_ + (entity.f_19787_ - entity.f_19867_) * this.partialTicks;
         return currentWalkDist - this.walkDistAnchor;
      }).orElse(0.0F);
   }
}
