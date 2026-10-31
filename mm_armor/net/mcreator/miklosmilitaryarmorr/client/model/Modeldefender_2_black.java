package net.mcreator.miklosmilitaryarmorr.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class Modeldefender_2_black<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modeldefender_2_black"), "main");
   public final ModelPart Body;
   public final ModelPart right_hand;
   public final ModelPart lefthand;

   public Modeldefender_2_black(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.right_hand = root.m_171324_("right_hand");
      this.lefthand = root.m_171324_("lefthand");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 27)
            .m_171488_(2.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 27)
            .m_171488_(-4.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 21)
            .m_171488_(-2.99F, -0.8F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 21)
            .m_171488_(1.99F, -0.8F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 20)
            .m_171488_(-4.3F, 6.02F, -3.01F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-3.7F, 6.01F, -3.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 30)
            .m_171488_(-1.5F, 6.0F, -3.01F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 10)
            .m_171488_(-4.0F, 1.0F, -2.8F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 27)
            .m_171488_(-4.0F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 8)
            .m_171488_(2.0F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 0)
            .m_171488_(-2.5F, 9.5F, -2.79F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 10)
            .m_171488_(-4.0F, 0.0F, 1.8F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_().m_171514_(28, 5).m_171488_(-2.5F, -2.0F, -0.2F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 1.0F, 2.0F, -0.3054F, 0.0F, 0.0F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 25)
            .m_171488_(-4.01F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 23)
            .m_171488_(-9.99F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(6.0F, 2.0F, 3.0F, -0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 21)
            .m_171488_(-3.99F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 21)
            .m_171488_(1.99F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F)
      );
      PartDefinition right_hand = partdefinition.m_171599_("right_hand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      PartDefinition lefthand = partdefinition.m_171599_("lefthand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.right_hand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.lefthand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.lefthand.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F) * limbSwingAmount;
   }
}
