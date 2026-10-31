package me.xjqsh.lrtactical.mixin.common;

import com.tacz.guns.init.ModDamageTypes;
import me.xjqsh.lrtactical.api.item.IConsumable;
import me.xjqsh.lrtactical.capability.CombatProperties;
import me.xjqsh.lrtactical.capability.CombatPropertiesProvider;
import me.xjqsh.lrtactical.capability.CustomItemCoolDownsProvider;
import me.xjqsh.lrtactical.item.FlashShieldItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ICapabilityProvider {
   public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
      super(pEntityType, pLevel);
   }

   @Shadow
   public abstract ItemStack m_21205_();

   @Inject(method = "isDamageSourceBlocked", at = @At("HEAD"), cancellable = true)
   public void isDamageSourceBlocked(DamageSource pDamageSource, CallbackInfoReturnable<Boolean> cir) {
      ItemStack stack = this.m_21205_();
      if (stack.m_41720_() instanceof FlashShieldItem && pDamageSource.m_269533_(ModDamageTypes.BULLETS_TAG)) {
         boolean isDrawing = (Boolean)this.getCapability(CombatPropertiesProvider.CAPABILITY).map(CombatProperties::isDrawing).orElse(false);
         boolean isDisabled = (Boolean)this.getCapability(CustomItemCoolDownsProvider.CAPABILITY)
            .map(cap -> cap.isOnCooldown(new ResourceLocation("shield_disabled")))
            .orElse(false);
         boolean isBlocking = !isDrawing && !isDisabled && stack.m_41773_() < stack.m_41776_();
         if (isBlocking) {
            Vec3 vec32 = pDamageSource.m_7270_();
            if (vec32 != null) {
               Vec3 vec3 = this.m_20252_(1.0F);
               Vec3 vec31 = vec32.m_82505_(this.m_20182_()).m_82541_();
               vec31 = new Vec3(vec31.f_82479_, 0.0, vec31.f_82481_);
               if (vec31.m_82526_(vec3) < 0.0) {
                  cir.setReturnValue(true);
               }
            }
         }
      }
   }

   @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
   public void isBlocking(CallbackInfoReturnable<Boolean> cir) {
      ItemStack stack = this.m_21205_();
      if (stack.m_41720_() instanceof FlashShieldItem) {
         boolean isDrawing = (Boolean)this.getCapability(CombatPropertiesProvider.CAPABILITY).map(CombatProperties::isDrawing).orElse(false);
         boolean isDisabled = (Boolean)this.getCapability(CustomItemCoolDownsProvider.CAPABILITY)
            .map(cap -> cap.isOnCooldown(new ResourceLocation("shield_disabled")))
            .orElse(false);
         cir.setReturnValue(!isDrawing && !isDisabled && stack.m_41773_() < stack.m_41776_());
      }
   }

   @Inject(method = "breakItem", at = @At("HEAD"), cancellable = true)
   public void onItemBreak(ItemStack pStack, CallbackInfo ci) {
      if (pStack != null && pStack.m_41720_() instanceof IConsumable consumable && !consumable.spawnParticleOnBreak(pStack)) {
         ci.cancel();
      }
   }
}
