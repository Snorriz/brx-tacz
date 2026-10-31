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

public class Modelchestplate_emr_ribbon_6b46_upgraded<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
      new ResourceLocation("mm_armor", "modelchestplate_emr_ribbon_6b_46_upgraded"), "main"
   );
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;

   public Modelchestplate_emr_ribbon_6b46_upgraded(ModelPart root) {
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
            .m_171514_(0, 32)
            .m_171488_(-4.0F, 1.0F, 1.8F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 39)
            .m_171488_(-1.0F, 5.0F, -4.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 39)
            .m_171488_(-3.5F, 5.0F, -4.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 39)
            .m_171488_(1.5F, 5.0F, -4.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 44)
            .m_171488_(-2.3F, 2.0F, -3.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 44)
            .m_171488_(-2.9F, 2.01F, -3.79F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 44)
            .m_171488_(-0.85F, 2.0F, -3.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 44)
            .m_171488_(-0.15F, 2.01F, -3.79F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 44)
            .m_171488_(1.3F, 2.0F, -3.8F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 44)
            .m_171488_(2.0F, 2.01F, -3.79F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 40)
            .m_171488_(-4.0F, 0.0F, 1.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 40)
            .m_171488_(2.0F, 0.0F, 1.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 44)
            .m_171488_(-2.5F, 8.0F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 42)
            .m_171488_(-2.5F, 6.0F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 42)
            .m_171488_(-2.5F, 4.0F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 42)
            .m_171488_(-2.5F, 2.0F, -2.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 42)
            .m_171488_(-2.5F, 3.5F, -2.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 22)
            .m_171488_(-3.5F, 8.2F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 20)
            .m_171488_(-3.5F, 5.2F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 20)
            .m_171488_(-3.5F, 6.7F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 36)
            .m_171488_(2.0F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 34)
            .m_171488_(-4.0F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 15)
            .m_171488_(-3.0F, 1.0F, -2.8F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 0)
            .m_171488_(-4.0F, 5.0F, -2.8F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 6)
            .m_171488_(3.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 34)
            .m_171488_(3.4F, 5.2F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 34)
            .m_171488_(3.4F, 6.8F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 34)
            .m_171488_(3.4F, 8.3F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 46)
            .m_171488_(3.4F, 5.2F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 46)
            .m_171488_(3.4F, 6.8F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 46)
            .m_171488_(3.4F, 8.3F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(8, 46)
            .m_171488_(-4.4F, 5.2F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 29)
            .m_171488_(-4.4F, 5.2F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(8, 46)
            .m_171488_(-4.4F, 6.8F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(4, 46)
            .m_171488_(-4.4F, 8.3F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 29)
            .m_171488_(-4.4F, 6.8F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 29)
            .m_171488_(-4.4F, 8.3F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 6)
            .m_171488_(-4.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 24)
            .m_171488_(-4.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 24)
            .m_171488_(2.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 32)
            .m_171488_(2.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 32)
            .m_171488_(-4.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 32)
            .m_171488_(2.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 32)
            .m_171488_(8.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-6.0F, 2.0F, 3.0F, -0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_().m_171514_(36, 42).m_171488_(0.0F, -1.4F, -2.79F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.7854F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_().m_171514_(18, 38).m_171488_(-0.6F, -1.0F, -2.79F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.7854F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(0, 46).m_171488_(-0.3F, -2.3F, -1.19F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 5.0F, -3.0F, -0.1309F, 0.0429F, -0.0095F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(0, 46).m_171488_(-0.45F, -2.3F, -1.19F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 5.0F, -3.0F, -0.1309F, 0.0429F, -0.0095F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(0, 46).m_171488_(0.45F, -2.3F, -1.19F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 5.0F, -3.0F, -0.1309F, 0.0429F, -0.0095F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 44)
            .m_171488_(0.0F, -5.1F, -1.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 45)
            .m_171488_(3.0F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 44)
            .m_171488_(2.5F, -5.1F, -1.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 45)
            .m_171488_(-2.0F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 44)
            .m_171488_(-2.5F, -5.1F, -1.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 45)
            .m_171488_(0.5F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 10.0F, -3.0F, -0.0436F, 0.0F, 0.0F)
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
            .m_171514_(24, 29)
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
