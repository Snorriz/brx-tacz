package me.xjqsh.lrtactical.api.animation;

import com.tacz.guns.client.animation.statemachine.ItemAnimationStateContext;
import me.xjqsh.lrtactical.api.item.IThrowable;
import net.minecraft.world.item.ItemStack;

public class ThrowableAnimationStateContext extends ItemAnimationStateContext {
   private ItemStack currentItem = ItemStack.f_41583_;
   private int usingTick = 0;
   private boolean using = false;
   private int prepareTime = 0;

   public void setCurrentItem(ItemStack currentItem) {
      this.currentItem = currentItem;
      if (currentItem.m_41720_() instanceof IThrowable iThrowable) {
         this.prepareTime = (Integer)iThrowable.getThrowableIndex(currentItem).map(index -> index.getData().getPrepareTime()).orElse(0);
      }
   }

   public int getStackCount() {
      return this.currentItem.m_41613_();
   }

   public int getUsingTick() {
      return this.usingTick;
   }

   public void setUsingTick(int throwTime) {
      this.usingTick = throwTime;
   }

   public boolean isUsing() {
      return this.using;
   }

   public void setUsing(boolean using) {
      this.using = using;
   }

   public int getPrepareTime() {
      return this.prepareTime;
   }
}
