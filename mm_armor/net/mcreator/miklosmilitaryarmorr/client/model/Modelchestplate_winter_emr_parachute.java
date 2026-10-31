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

public class Modelchestplate_winter_emr_parachute<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
      new ResourceLocation("mm_armor", "modelchestplate_winter_emr_parachute"), "main"
   );
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;

   public Modelchestplate_winter_emr_parachute(ModelPart root) {
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
            .m_171514_(42, 46)
            .m_171488_(-1.5F, 3.0F, -2.8F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_().m_171514_(42, 49).m_171488_(-1.0F, -3.0F, 0.6F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 3.0F, -3.0F, 0.0F, 0.0F, -0.6109F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(40, 22).m_171488_(-2.5F, -4.0F, -1.2F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 3.0F, 4.0F, 0.3054F, 0.0F, 0.0F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(24, 9).m_171488_(-3.5F, -4.0F, -1.2F, 7.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 5.0F, 3.0F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_().m_171514_(42, 49).m_171488_(-1.0F, -3.0F, 0.59F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 3.0F, -3.0F, 0.0F, 0.0F, 0.6109F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_().m_171514_(42, 49).m_171488_(-1.0F, -3.0F, 0.59F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 7.0F, -3.0F, 0.0F, 0.0F, 0.4363F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(46, 43).m_171488_(-1.1F, -1.3F, -1.7F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 8.0F, -3.0F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(46, 18).m_171488_(-0.3F, -2.5F, -2.1745F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 9.0F, -3.0F, 0.043F, -0.0076F, 0.1744F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(32, 46).m_171488_(-8.0F, -3.2F, -3.7F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(6.0F, 12.0F, -3.0F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition cube_r6 = Body.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_().m_171514_(24, 0).m_171488_(-11.0F, -4.5F, -3.0F, 10.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(6.0F, 12.0F, -3.0F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_().m_171514_(42, 49).m_171488_(-1.0F, -3.0F, 0.6F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 7.0F, -3.0F, 0.0F, 0.0F, -0.4363F)
      );
      PartDefinition Body_r5 = Body.m_171599_(
         "Body_r5",
         CubeListBuilder.m_171558_().m_171514_(48, 49).m_171488_(-1.0F, -3.5F, 0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 7.0F, -4.0F, -0.0436F, -0.0019F, -0.0436F)
      );
      PartDefinition Body_r6 = Body.m_171599_(
         "Body_r6",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 28)
            .m_171488_(-1.01F, -7.5F, 0.01F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 45)
            .m_171488_(-1.0F, -7.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 7.0F, -3.0F, -0.0436F, -0.0019F, -0.0436F)
      );
      PartDefinition Body_r7 = Body.m_171599_(
         "Body_r7",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 45)
            .m_171488_(0.694F, -0.2631F, 1.4F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 38)
            .m_171488_(0.694F, -0.2631F, -4.6F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 7.0F, 0.0F, 0.6981F, -0.0019F, 0.0F)
      );
      PartDefinition Body_r8 = Body.m_171599_(
         "Body_r8",
         CubeListBuilder.m_171558_()
            .m_171514_(32, 38)
            .m_171488_(-0.68F, -0.2631F, -4.6F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 47)
            .m_171488_(-0.68F, -0.2631F, 1.4F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 7.0F, 0.0F, 0.6981F, -0.0019F, 0.0F)
      );
      PartDefinition Body_r9 = Body.m_171599_(
         "Body_r9",
         CubeListBuilder.m_171558_().m_171514_(46, 9).m_171488_(-0.306F, -0.2631F, -0.6F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 0.0F, 2.0F, 0.0F, -0.0019F, 0.0F)
      );
      PartDefinition Body_r10 = Body.m_171599_(
         "Body_r10",
         CubeListBuilder.m_171558_()
            .m_171514_(46, 37)
            .m_171488_(-1.1F, -3.9F, 0.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 32)
            .m_171488_(-8.9F, -3.9F, 0.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 7.0F, -5.0F, -0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r11 = Body.m_171599_(
         "Body_r11",
         CubeListBuilder.m_171558_().m_171514_(48, 49).m_171488_(-4.0F, -3.4F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 7.0F, -3.0F, -0.0436F, 0.0019F, 0.0436F)
      );
      PartDefinition Body_r12 = Body.m_171599_(
         "Body_r12",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 28)
            .m_171488_(-2.99F, -7.5F, 0.01F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 45)
            .m_171488_(-3.0F, -7.0F, 0.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 7.0F, -3.0F, -0.0436F, 0.0019F, 0.0436F)
      );
      PartDefinition Body_r13 = Body.m_171599_(
         "Body_r13",
         CubeListBuilder.m_171558_().m_171514_(40, 34).m_171488_(-1.694F, 5.6497F, -0.61F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 0.0F, 2.0F, 0.0F, 0.0019F, 0.0F)
      );
      PartDefinition Body_r14 = Body.m_171599_(
         "Body_r14",
         CubeListBuilder.m_171558_().m_171514_(6, 45).m_171488_(-1.694F, -0.3503F, -0.6F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 0.0F, 2.0F, 0.0F, 0.0019F, 0.0F)
      );
      PartDefinition RightArm = partdefinition.m_171599_(
         "RightArm",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 32)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 22)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition LeftArm = partdefinition.m_171599_(
         "LeftArm",
         CubeListBuilder.m_171558_()
            .m_171514_(16, 38)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 22)
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
