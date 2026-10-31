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

public class Modelchestplate_mm14_green_plate_carrier_upgraded<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
      new ResourceLocation("mm_armor", "modelchestplate_mm_14_green_plate_carrier_upgraded"), "main"
   );
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;

   public Modelchestplate_mm14_green_plate_carrier_upgraded(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.RightArm = root.m_171324_("RightArm");
      this.LeftArm = root.m_171324_("LeftArm");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 0)
            .m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
            .m_171514_(0, 38)
            .m_171488_(-4.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 38)
            .m_171488_(3.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 32)
            .m_171488_(-4.0F, 5.0F, -2.5F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 7)
            .m_171488_(-2.5F, 10.0F, -2.8F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 35)
            .m_171488_(-3.0F, 3.0F, -2.8F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 5)
            .m_171488_(-3.0F, 2.7F, -2.78F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 14)
            .m_171488_(-2.0F, 1.0F, 1.8F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 29)
            .m_171488_(-4.0F, 5.0F, 1.5F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 11)
            .m_171488_(-2.0F, 1.0F, -2.8F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 35)
            .m_171488_(-3.0F, 2.0F, 1.8F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_().m_171514_(42, 29).m_171488_(-2.0F, 0.0F, -2.79F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 0.0F, 0.0F, 0.0F, -1.0472F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_().m_171514_(42, 31).m_171488_(0.0F, 0.0F, -2.79F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0472F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 32)
            .m_171488_(-2.0F, 0.0F, -0.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 23)
            .m_171488_(-2.0F, 0.0F, -4.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.4363F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 32)
            .m_171488_(0.0F, 0.0F, -0.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 23)
            .m_171488_(0.0F, 0.0F, -4.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.4363F)
      );
      PartDefinition Body_r5 = Body.m_171599_(
         "Body_r5",
         CubeListBuilder.m_171558_()
            .m_171514_(26, 44)
            .m_171488_(0.0F, 0.4F, -3.22F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(26, 44)
            .m_171488_(0.0F, 0.0F, -3.21F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.7854F)
      );
      PartDefinition Body_r6 = Body.m_171599_(
         "Body_r6",
         CubeListBuilder.m_171558_()
            .m_171514_(44, 43)
            .m_171488_(0.4F, 0.0F, -3.22F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(44, 43)
            .m_171488_(0.0F, 0.0F, -3.21F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.7854F)
      );
      PartDefinition Body_r7 = Body.m_171599_(
         "Body_r7",
         CubeListBuilder.m_171558_().m_171514_(40, 16).m_171488_(-1.5F, -0.8F, -0.1F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 3.0F, -3.0F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition Body_r8 = Body.m_171599_(
         "Body_r8",
         CubeListBuilder.m_171558_()
            .m_171514_(42, 33)
            .m_171488_(0.0F, -0.24F, 1.01F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 0)
            .m_171488_(0.0F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition Body_r9 = Body.m_171599_(
         "Body_r9",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 0)
            .m_171488_(-2.0F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 33)
            .m_171488_(-2.0F, -0.24F, 1.01F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 38)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 26)
            .m_171488_(-1.0F, -3.25F, -0.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 9.0F, -3.0F, 0.0791F, 0.0F, 0.0F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(10, 43).m_171488_(0.5F, -1.2F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 8.0F, -3.0F, 0.0791F, 0.0F, 0.0F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(40, 19).m_171488_(0.5F, -4.3F, -0.9F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 10.0F, -3.0F, 0.0791F, -0.2182F, 0.0F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 26)
            .m_171488_(-1.01F, -3.25F, -0.7F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 38)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 9.0F, -3.0F, 0.0791F, -0.2182F, 0.0F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(40, 19).m_171488_(0.15F, -4.3F, -1.7F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 10.0F, -2.0F, 0.1265F, -0.9131F, -0.0832F)
      );
      PartDefinition cube_r6 = Body.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 26)
            .m_171488_(-1.01F, -3.25F, -0.7F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 38)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 9.0F, -2.0F, 0.1265F, -0.9131F, -0.0832F)
      );
      PartDefinition cube_r7 = Body.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(10, 43).m_171488_(-0.5F, -1.2F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 8.0F, -3.0F, 0.0791F, -0.2182F, 0.0F)
      );
      PartDefinition cube_r8 = Body.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_().m_171514_(40, 19).m_171488_(0.5F, -4.3F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 10.0F, -3.0F, 0.0791F, 0.2182F, 0.0F)
      );
      PartDefinition cube_r9 = Body.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 26)
            .m_171488_(-0.99F, -3.25F, -0.7F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 38)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 9.0F, -3.0F, 0.0791F, 0.2182F, 0.0F)
      );
      PartDefinition cube_r10 = Body.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_().m_171514_(14, 43).m_171488_(-0.5F, -1.2F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 8.0F, -3.0F, 0.0791F, 0.2182F, 0.0F)
      );
      PartDefinition cube_r11 = Body.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_().m_171514_(22, 44).m_171488_(0.5F, -1.15F, -2.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 8.0F, -3.0F, -0.0082F, 0.0F, 0.0F)
      );
      PartDefinition cube_r12 = Body.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_().m_171514_(22, 44).m_171488_(-0.5F, -1.15F, -2.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 8.0F, -3.0F, -0.0103F, -0.2173F, 0.0193F)
      );
      PartDefinition cube_r13 = Body.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_().m_171514_(36, 43).m_171488_(-0.9F, -1.3F, 7.95F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(6.0F, 8.0F, -6.0F, -0.0466F, -1.3532F, 0.0284F)
      );
      PartDefinition cube_r14 = Body.m_171599_(
         "cube_r14",
         CubeListBuilder.m_171558_().m_171514_(40, 19).m_171488_(0.5F, -4.3F, -0.8F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 10.0F, -3.0F, 0.0791F, 0.0F, 0.0F)
      );
      PartDefinition cube_r15 = Body.m_171599_(
         "cube_r15",
         CubeListBuilder.m_171558_().m_171514_(32, 43).m_171488_(0.0F, -2.0F, -0.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 8.0F, -2.0F, 0.1138F, 0.4805F, 0.0148F)
      );
      PartDefinition cube_r16 = Body.m_171599_(
         "cube_r16",
         CubeListBuilder.m_171558_().m_171514_(40, 43).m_171488_(0.0394F, 0.0F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 6.0F, -2.0F, 0.0265F, 0.4805F, 0.0148F)
      );
      PartDefinition cube_r17 = Body.m_171599_(
         "cube_r17",
         CubeListBuilder.m_171558_().m_171514_(32, 43).m_171488_(0.0F, -2.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 8.0F, -1.0F, 0.1387F, 1.1784F, 0.0344F)
      );
      PartDefinition cube_r18 = Body.m_171599_(
         "cube_r18",
         CubeListBuilder.m_171558_().m_171514_(18, 44).m_171488_(0.0394F, 0.0F, -2.3F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 6.0F, -1.0F, 0.0515F, 1.1784F, 0.0344F)
      );
      PartDefinition RightArm = partdefinition.m_171599_(
         "RightArm",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 16)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 0)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition LeftArm = partdefinition.m_171599_(
         "LeftArm",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 16)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 0)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(5.0F, 2.0F, 0.0F)
      );
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.RightArm.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.LeftArm.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.RightArm.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
      this.LeftArm.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F) * limbSwingAmount;
   }
}
