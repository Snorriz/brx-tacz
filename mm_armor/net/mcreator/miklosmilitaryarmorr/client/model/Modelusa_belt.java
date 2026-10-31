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
import net.minecraft.world.entity.Entity;

public class Modelusa_belt<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelusa_belt"), "main");
   public final ModelPart Body;
   public final ModelPart bone;

   public Modelusa_belt(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.bone = root.m_171324_("bone");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 16)
            .m_171488_(-0.8F, -0.3F, -2.4F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 16)
            .m_171488_(-0.95F, -0.3F, -2.39F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 8.0F, -1.0F, -0.1309F, -0.1745F, 0.0F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(0, 16).m_171488_(-0.4F, -4.3F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 12.0F, -2.0F, 0.0F, 0.1745F, 0.0F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 16)
            .m_171488_(-0.15F, -0.3F, -2.1F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 16)
            .m_171488_(-0.25F, -0.3F, -2.09F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 8.0F, -1.0F, -0.1309F, 0.1745F, 0.0F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(0, 16).m_171488_(-0.7F, -4.3F, -1.3F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 12.0F, -2.0F, 0.0F, -0.1745F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 20)
            .m_171488_(1.0F, -17.0F, 1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 20)
            .m_171488_(0.0F, -16.0F, 1.8F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 25.0F, 0.0F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition bone = partdefinition.m_171599_("bone", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 32, 32);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.bone.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
