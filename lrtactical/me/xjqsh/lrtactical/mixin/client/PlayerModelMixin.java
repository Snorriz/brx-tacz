package me.xjqsh.lrtactical.mixin.client;

import com.tacz.guns.api.client.other.KeepingItemRenderer;
import me.xjqsh.lrtactical.api.item.ICustomItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> {
   @Shadow
   @Final
   public ModelPart f_103374_;
   @Shadow
   @Final
   public ModelPart f_103375_;

   public PlayerModelMixin(ModelPart part) {
      super(part);
   }

   @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
   private void setRotationAnglesTail(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
      if (entityIn instanceof Player player) {
         ItemStack currentItem = KeepingItemRenderer.getRenderer().getCurrentItem();
         if (ageInTicks == 0.0F && currentItem != null && currentItem.m_41720_() instanceof ICustomItem) {
            this.tacz$resetAll(this.f_102811_);
            this.tacz$resetAll(this.f_102812_);
            this.f_103375_.m_104315_(this.f_102811_);
            this.f_103374_.m_104315_(this.f_102812_);
         }
      }
   }

   @Unique
   private void tacz$resetAll(ModelPart part) {
      part.f_104203_ = 0.0F;
      part.f_104204_ = 0.0F;
      part.f_104205_ = 0.0F;
   }
}
