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

public class Modelchestplate_desert_emr_6sh117<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
      new ResourceLocation("mm_armor", "modelchestplate_desert_emr_6sh_117"), "main"
   );
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;

   public Modelchestplate_desert_emr_6sh117(ModelPart root) {
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
            .m_171514_(24, 0)
            .m_171488_(-4.0F, 7.0F, 1.8F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 10)
            .m_171488_(-1.0F, 1.0F, 1.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(34, 5)
            .m_171488_(0.3F, 5.0F, -4.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 19)
            .m_171488_(-2.3F, 5.0F, -4.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 13)
            .m_171488_(-2.0F, 2.0F, 1.8F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 19)
            .m_171488_(-4.0F, 0.0F, -2.8F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 32)
            .m_171488_(-4.0F, 4.0F, -2.8F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 40)
            .m_171488_(2.0F, 0.0F, -2.8F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 26)
            .m_171488_(-1.0F, 4.0F, -2.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 29)
            .m_171488_(1.0F, 4.0F, -2.8F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 5)
            .m_171488_(3.3F, 7.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 5)
            .m_171488_(-4.3F, 7.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 29)
            .m_171488_(-4.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 29)
            .m_171488_(2.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 24)
            .m_171488_(2.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 24)
            .m_171488_(-4.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_().m_171514_(24, 13).m_171488_(-1.4F, -1.0F, 0.2F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 3.0F, -4.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_().m_171514_(24, 13).m_171488_(-0.5F, -1.0F, 0.2F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 3.0F, -4.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(0.0F, -4.0F, -0.79F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 4.0F, -2.0F, 0.0F, 0.0F, 0.2618F)
      );
      PartDefinition Body_r5 = Body.m_171599_(
         "Body_r5",
         CubeListBuilder.m_171558_().m_171514_(42, 5).m_171488_(-1.0F, -4.0F, -0.79F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 4.0F, -2.0F, 0.0F, 0.0F, -0.2618F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(8, 32).m_171488_(-1.3F, -5.0F, -0.9F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 10.0F, -3.0F, 0.0F, 0.2182F, 0.0F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(32, 26)
            .m_171488_(-0.8F, -2.8F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 34)
            .m_171488_(-1.3F, -5.1F, -1.2F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 10.0F, -3.0F, -0.0436F, 0.2182F, 0.0F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 37)
            .m_171488_(-1.3F, -5.1F, -1.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 12)
            .m_171488_(-0.8F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 15)
            .m_171488_(1.8F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(8, 39)
            .m_171488_(1.3F, -5.1F, -1.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 10.0F, -3.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(32, 39)
            .m_171488_(-0.7F, -5.1F, -1.2F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 34)
            .m_171488_(-0.2F, -2.8F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 10.0F, -3.0F, -0.0436F, -0.2182F, 0.0F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(16, 34).m_171488_(-0.7F, -5.0F, -0.9F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 10.0F, -3.0F, 0.0F, -0.2182F, 0.0F)
      );
      PartDefinition Body_r6 = Body.m_171599_(
         "Body_r6",
         CubeListBuilder.m_171558_().m_171514_(40, 39).m_171488_(-1.2F, -4.0F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 3.0F, 2.0F, 0.0F, 0.0F, -0.5672F)
      );
      PartDefinition Body_r7 = Body.m_171599_(
         "Body_r7",
         CubeListBuilder.m_171558_().m_171514_(16, 41).m_171488_(0.0F, -4.4F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.5672F)
      );
      PartDefinition RightArm = partdefinition.m_171599_(
         "RightArm",
         CubeListBuilder.m_171558_()
            .m_171514_(16, 16)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition LeftArm = partdefinition.m_171599_(
         "LeftArm",
         CubeListBuilder.m_171558_()
            .m_171514_(16, 16)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
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
