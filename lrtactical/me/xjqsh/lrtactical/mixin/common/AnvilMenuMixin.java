package me.xjqsh.lrtactical.mixin.common;

import me.xjqsh.lrtactical.api.item.ICustomItem;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
   @Shadow
   @Final
   private DataSlot f_39002_;
   @Shadow
   public int f_39000_;

   public AnvilMenuMixin(@Nullable MenuType<?> pType, int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess pAccess) {
      super(pType, pContainerId, pPlayerInventory, pAccess);
   }

   @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
   public void onCreateResult(CallbackInfo ci) {
      ItemStack stack1 = this.f_39769_.m_8020_(0);
      ItemStack stack2 = this.f_39769_.m_8020_(1);
      if (stack1.m_41720_() instanceof ICustomItem item1 && stack2.m_41720_() instanceof ICustomItem && !item1.isSame(stack1, stack2)) {
         this.f_39768_.m_6836_(0, ItemStack.f_41583_);
         this.f_39002_.m_6422_(0);
         this.f_39000_ = 0;
         ci.cancel();
      }
   }
}
