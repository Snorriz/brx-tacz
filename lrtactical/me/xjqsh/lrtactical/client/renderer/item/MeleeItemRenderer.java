package me.xjqsh.lrtactical.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.tacz.guns.api.client.animation.statemachine.LuaAnimationStateMachine;
import com.tacz.guns.api.client.event.BeforeRenderHandEvent;
import com.tacz.guns.client.model.BedrockAnimatedModel;
import com.tacz.guns.client.model.SlotModel;
import com.tacz.guns.client.model.bedrock.BedrockPart;
import com.tacz.guns.client.renderer.item.AnimateGeoItemRenderer;
import me.xjqsh.lrtactical.api.LrTacticalAPI;
import me.xjqsh.lrtactical.api.animation.MeleeAnimationStateContext;
import me.xjqsh.lrtactical.client.renderer.JumpSwayUtil;
import me.xjqsh.lrtactical.client.renderer.model.CustomBedrockModel;
import me.xjqsh.lrtactical.client.resource.display.MeleeDisplayInstance;
import me.xjqsh.lrtactical.item.index.MeleeWeaponIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class MeleeItemRenderer extends AnimateGeoItemRenderer<CustomBedrockModel, MeleeAnimationStateContext> {
   private static final SlotModel SLOT_MODEL = new SlotModel();

   public MeleeAnimationStateContext initContext(ItemStack stack, Player player, float partialTick) {
      MeleeAnimationStateContext context = new MeleeAnimationStateContext();
      this.updateContext(context, stack, player, partialTick);
      return context;
   }

   public void updateContext(MeleeAnimationStateContext context, ItemStack stack, Player player, float partialTick) {
      context.setPartialTicks(partialTick);
   }

   public ResourceLocation getTextureLocation(ItemStack stack) {
      return (ResourceLocation)LrTacticalAPI.getMeleeDisplay(stack).map(MeleeDisplayInstance::getTexture).orElse(null);
   }

   @Nullable
   public LuaAnimationStateMachine<MeleeAnimationStateContext> getStateMachine(ItemStack stack) {
      return (LuaAnimationStateMachine<MeleeAnimationStateContext>)LrTacticalAPI.getMeleeDisplay(stack).map(MeleeDisplayInstance::getStateMachine).orElse(null);
   }

   public CustomBedrockModel getModel(ItemStack stack) {
      return (CustomBedrockModel)LrTacticalAPI.getMeleeDisplay(stack).map(MeleeDisplayInstance::getModel).orElse(null);
   }

   public long getPutAwayTime(ItemStack stack) {
      return (Long)LrTacticalAPI.getMeleeIndex(stack).map(MeleeWeaponIndex::getData).map(throwableData -> throwableData.getPutAwayTime() * 50L).orElse(0L);
   }

   public void renderFirstPerson(
      LocalPlayer player, ItemStack stack, ItemDisplayContext ctx, PoseStack poseStack, MultiBufferSource bufferSource, int light, float partialTick
   ) {
      CustomBedrockModel model = this.getModel(stack);
      if (model != null) {
         poseStack.m_85836_();
         LuaAnimationStateMachine<MeleeAnimationStateContext> stateMachine = this.getStateMachine(stack);
         if (stateMachine != null) {
            stateMachine.processContextIfExist(context -> this.updateContext(context, stack, player, partialTick));
            stateMachine.update();
         }

         float xRotOffset = Mth.m_14179_(partialTick, player.f_108588_, player.f_108586_);
         float yRotOffset = Mth.m_14179_(partialTick, player.f_108587_, player.f_108585_);
         float xRot = player.m_5686_(partialTick) - xRotOffset;
         float yRot = player.m_5675_(partialTick) - yRotOffset;
         poseStack.m_252781_(Axis.f_252529_.m_252977_(xRot * -0.1F));
         poseStack.m_252781_(Axis.f_252436_.m_252977_(yRot * -0.1F));
         BedrockPart rootNode = model.getRootNode();
         if (rootNode != null) {
            xRot = (float)Math.tanh(xRot / 25.0F) * 25.0F;
            yRot = (float)Math.tanh(yRot / 25.0F) * 25.0F;
            rootNode.offsetX += yRot * 0.1F / 16.0F / 3.0F;
            rootNode.offsetY += -xRot * 0.1F / 16.0F / 3.0F;
            rootNode.additionalQuaternion.mul(Axis.f_252529_.m_252977_(xRot * 0.05F));
            rootNode.additionalQuaternion.mul(Axis.f_252436_.m_252977_(yRot * 0.05F));
         }

         poseStack.m_252880_(0.0F, 1.5F, 0.0F);
         poseStack.m_252781_(Axis.f_252403_.m_252977_(180.0F));
         this.doExtraTransforms(poseStack, model, stack);
         model.setEffectVisible(true);
         model.render(poseStack, ctx, this.getRenderType(stack), light, OverlayTexture.f_118083_);
         model.setEffectVisible(false);
         model.cleanAnimationTransform();
         poseStack.m_85849_();
      }
   }

   public void applyItemInHandCameraAnimation(BeforeRenderHandEvent event, ItemStack stack, float multiplier) {
      super.applyItemInHandCameraAnimation(event, stack, multiplier);
      CustomBedrockModel model = this.getModel(stack);
      if (model != null) {
         model.cleanCameraAnimationTransform();
      }
   }

   public void doExtraTransforms(PoseStack poseStack, CustomBedrockModel model, ItemStack stack) {
      super.doExtraTransforms(poseStack, model, stack);
      JumpSwayUtil.applyJumpingSway(model, Minecraft.m_91087_().m_91296_());
   }

   public void m_108829_(ItemStack stack, ItemDisplayContext ctx, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
      if (!ctx.m_269069_()) {
         LrTacticalAPI.getMeleeDisplay(stack).ifPresentOrElse(display -> {
            BedrockAnimatedModel model = display.getModel();
            if (ctx == ItemDisplayContext.GUI && display.getSlotTexture() != null) {
               poseStack.m_85837_(0.5, 1.5, 0.5);
               poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
               VertexConsumer buffer = bufferSource.m_6299_(RenderType.m_110473_(display.getSlotTexture()));
               SLOT_MODEL.m_7695_(poseStack, buffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            } else {
               poseStack.m_85836_();
               ItemTransforms transforms = display.getTransforms();
               if (transforms != null) {
                  poseStack.m_252880_(0.5F, 0.5F, 0.5F);
                  ItemTransform transform = transforms.m_269404_(ctx);
                  transform.m_111763_(false, poseStack);
                  poseStack.m_252880_(-0.5F, -0.5F, -0.5F);
               }

               Vector3f displayOffset = display.getDisplayOffset();
               if (displayOffset != null) {
                  poseStack.m_252880_(displayOffset.x(), displayOffset.y(), displayOffset.z());
               }

               poseStack.m_85837_(0.5, 1.5, 0.5);
               poseStack.m_252781_(Axis.f_252403_.m_252977_(180.0F));
               if (model != null) {
                  model.render(poseStack, ctx, RenderType.m_110452_(this.getTextureLocation(stack)), light, overlay);
               } else {
                  VertexConsumer buffer = bufferSource.m_6299_(RenderType.m_110473_(MissingTextureAtlasSprite.m_118071_()));
                  SLOT_MODEL.m_7695_(poseStack, buffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
               }

               poseStack.m_85849_();
            }
         }, () -> {
            poseStack.m_85837_(0.5, 1.5, 0.5);
            poseStack.m_252781_(Axis.f_252393_.m_252977_(180.0F));
            VertexConsumer buffer = bufferSource.m_6299_(RenderType.m_110473_(MissingTextureAtlasSprite.m_118071_()));
            SLOT_MODEL.m_7695_(poseStack, buffer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
         });
      }
   }
}
