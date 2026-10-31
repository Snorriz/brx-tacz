package me.xjqsh.lrtactical.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.client.renderer.item.ThrowableItemRendererWrapper;
import me.xjqsh.lrtactical.client.renderer.model.CustomBedrockModel;
import me.xjqsh.lrtactical.entity.StickyGrenadeEntity;
import me.xjqsh.lrtactical.entity.ThrowableItemEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ThrowableEntityRenderer extends EntityRenderer<ThrowableItemEntity> {
   public ThrowableEntityRenderer(Context renderManager) {
      super(renderManager);
   }

   public void render(ThrowableItemEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int light) {
      poseStack.m_85836_();
      if (entityIn.getItem() != null) {
         if (entityIn instanceof StickyGrenadeEntity stickyGrenade) {
            Direction stuckFace = stickyGrenade.getStuckFace();
            if (stuckFace != null) {
               poseStack.m_85837_(-stuckFace.m_122429_() * 0.15, (1 - stuckFace.m_122430_()) * 0.15, -stuckFace.m_122431_() * 0.15);
            }
         }

         float yRot = Mth.m_14179_(partialTicks, entityIn.f_19859_, entityIn.m_146908_());
         poseStack.m_252781_(Axis.f_252392_.m_252977_(yRot));
         poseStack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_(partialTicks, entityIn.f_19860_, entityIn.m_146909_())));
         LrTacticalAPI.getThrowableDisplay(entityIn.getItem()).ifPresent(display -> {
            ItemTransform entityTransform = display.getEntityTransform();
            if (entityTransform != null) {
               entityTransform.m_111763_(false, poseStack);
            }
         });
         CustomBedrockModel model = null;
         if (IClientItemExtensions.of(entityIn.getItem()).getCustomRenderer() instanceof ThrowableItemRendererWrapper renderer
            && renderer.getModel(entityIn.getItem()) instanceof CustomBedrockModel customModel) {
            model = customModel;
            model.setEntityRendering(true);
         }

         Minecraft.m_91087_()
            .m_91291_()
            .m_269128_(entityIn.getItem(), ItemDisplayContext.NONE, light, OverlayTexture.f_118083_, poseStack, bufferIn, entityIn.m_9236_(), 0);
         if (model != null) {
            model.setEntityRendering(false);
         }
      }

      poseStack.m_85849_();
   }

   public ResourceLocation getTextureLocation(ThrowableItemEntity pEntity) {
      return null;
   }
}
