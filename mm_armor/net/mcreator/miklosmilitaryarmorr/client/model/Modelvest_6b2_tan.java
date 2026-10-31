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

public class Modelvest_6b2_tan<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelvest_6b_2_tan"), "main");
   public final ModelPart Body;
   public final ModelPart right_hand;
   public final ModelPart lefthand;

   public Modelvest_6b2_tan(ModelPart root) {
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
            .m_171514_(18, 0)
            .m_171488_(2.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 0)
            .m_171488_(-4.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 5)
            .m_171488_(-4.3F, 7.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 10)
            .m_171488_(3.3F, 4.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 5)
            .m_171488_(-4.3F, 4.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 10)
            .m_171488_(3.3F, 7.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-4.0F, 0.0F, -2.8F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 15)
            .m_171488_(-2.0F, 10.0F, -2.8F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 11)
            .m_171488_(-4.0F, 0.0F, 1.8F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_().m_171514_(18, 21).m_171488_(0.0F, -2.0F, -2.79F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.8727F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_().m_171514_(18, 18).m_171488_(-3.0F, -2.0F, -2.79F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.8727F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 22)
            .m_171488_(-4.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(6, 22)
            .m_171488_(-10.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(6.0F, 2.0F, 3.0F, -0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 22)
            .m_171488_(-3.99F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 22)
            .m_171488_(1.99F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F)
      );
      PartDefinition right_hand = partdefinition.m_171599_("right_hand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      PartDefinition lefthand = partdefinition.m_171599_("lefthand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 32, 32);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.right_hand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.lefthand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.lefthand.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F) * limbSwingAmount;
      this.right_hand.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
   }
}
