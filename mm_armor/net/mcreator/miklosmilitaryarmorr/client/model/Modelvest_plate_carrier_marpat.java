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

public class Modelvest_plate_carrier_marpat<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelvest_plate_carrier_marpat"), "main");
   public final ModelPart Body;
   public final ModelPart bone;

   public Modelvest_plate_carrier_marpat(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.bone = root.m_171324_("bone");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 12)
            .m_171488_(-3.0F, 2.0F, 1.8F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 21)
            .m_171488_(-2.0F, 1.0F, -2.8F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 6)
            .m_171488_(-4.0F, 5.0F, 1.5F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 24)
            .m_171488_(-2.0F, 1.0F, 1.8F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 9)
            .m_171488_(-3.0F, 2.7F, -2.78F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 12)
            .m_171488_(-3.0F, 3.0F, -2.8F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-4.0F, 5.0F, -2.5F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 0)
            .m_171488_(3.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 0)
            .m_171488_(-4.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(14, 20)
            .m_171488_(0.0F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(22, 25)
            .m_171488_(0.0F, -0.24F, 1.01F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(22, 25)
            .m_171488_(-2.0F, -0.24F, 1.01F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 20)
            .m_171488_(-2.0F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 21)
            .m_171488_(0.0F, 0.0F, -3.21F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 21)
            .m_171488_(0.4F, 0.0F, -3.22F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.7854F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 23)
            .m_171488_(0.0F, 0.0F, -3.21F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 23)
            .m_171488_(0.0F, 0.4F, -3.22F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.7854F)
      );
      PartDefinition Body_r5 = Body.m_171599_(
         "Body_r5",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 25)
            .m_171488_(0.0F, 0.0F, -0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 25)
            .m_171488_(0.0F, 0.0F, 3.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.4363F)
      );
      PartDefinition Body_r6 = Body.m_171599_(
         "Body_r6",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 25)
            .m_171488_(-2.0F, 0.0F, -0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 25)
            .m_171488_(-2.0F, 0.0F, 3.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 0.0F, -2.0F, 0.0F, 0.0F, -0.4363F)
      );
      PartDefinition Body_r7 = Body.m_171599_(
         "Body_r7",
         CubeListBuilder.m_171558_().m_171514_(0, 26).m_171488_(0.0F, 0.0F, -2.79F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0472F)
      );
      PartDefinition Body_r8 = Body.m_171599_(
         "Body_r8",
         CubeListBuilder.m_171558_().m_171514_(26, 20).m_171488_(-2.0F, 0.0F, -2.79F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 0.0F, 0.0F, 0.0F, -1.0472F)
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
