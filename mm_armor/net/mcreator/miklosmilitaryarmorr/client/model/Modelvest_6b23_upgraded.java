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

public class Modelvest_6b23_upgraded<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelvest_6b_23_upgraded"), "main");
   public final ModelPart Body;
   public final ModelPart righthand;
   public final ModelPart lefthand;

   public Modelvest_6b23_upgraded(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.righthand = root.m_171324_("righthand");
      this.lefthand = root.m_171324_("lefthand");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 28)
            .m_171488_(2.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 28)
            .m_171488_(-4.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 19)
            .m_171488_(-4.3F, 6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 19)
            .m_171488_(3.3F, 6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 7)
            .m_171488_(-4.0F, 0.0F, -2.8F, 8.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 28)
            .m_171488_(-2.0F, 10.45F, -2.78F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 28)
            .m_171488_(-2.0F, 10.45F, 1.79F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 7)
            .m_171488_(-4.0F, 0.0F, 1.8F, 8.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 31)
            .m_171488_(-2.0F, 10.5F, -2.79F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 31)
            .m_171488_(1.0F, 10.0F, 1.81F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 31)
            .m_171488_(-2.0F, 10.0F, 1.81F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 31)
            .m_171488_(1.0F, 10.5F, -2.79F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(18, 32).m_171488_(-1.3F, -9.0F, -0.8F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 0.0F, -6.0F, -1.5708F, 0.0F, 0.0873F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(18, 32).m_171488_(-0.7F, -9.0F, -0.8F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 0.0F, -6.0F, -1.5708F, 0.0F, -0.0873F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(22, 31).m_171488_(-1.0F, -8.0F, -0.6F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 8.0F, -3.0F, -0.0435F, -0.0038F, -0.0872F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(22, 31).m_171488_(-1.0F, -7.9F, -0.6F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 8.0F, -3.0F, -0.0435F, 0.0038F, 0.0872F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_()
            .m_171514_(32, 33)
            .m_171488_(6.32F, -5.0F, -2.4F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(6, 33)
            .m_171488_(7.3F, -5.0F, -2.39F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 11.0F, -4.0F, -0.0443F, -0.1744F, 0.0077F)
      );
      PartDefinition cube_r6 = Body.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_()
            .m_171514_(34, 19)
            .m_171488_(6.31F, -6.0F, -2.9F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 8)
            .m_171488_(7.31F, -5.99F, -2.89F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 11.0F, -4.0F, -0.1316F, -0.1744F, 0.0077F)
      );
      PartDefinition cube_r7 = Body.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(6, 38).m_171488_(5.0F, -5.15F, -1.6F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 12.0F, -5.0F, -0.1316F, -0.1744F, 0.0077F)
      );
      PartDefinition cube_r8 = Body.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 33)
            .m_171488_(6.4F, -5.0F, -1.1F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 28)
            .m_171488_(7.34F, -5.0F, -1.09F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 32)
            .m_171488_(4.7F, -5.0F, -1.09F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(26, 31)
            .m_171488_(3.8F, -5.0F, -1.1F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-6.0F, 11.0F, -4.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r9 = Body.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_()
            .m_171514_(34, 19)
            .m_171488_(6.39F, -6.0F, -1.6F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 37)
            .m_171488_(6.85F, -4.01F, -1.46F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 4)
            .m_171488_(7.35F, -6.01F, -1.59F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 0)
            .m_171488_(4.71F, -5.99F, -1.59F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(34, 19)
            .m_171488_(3.79F, -6.0F, -1.6F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-6.0F, 11.0F, -4.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition cube_r10 = Body.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_().m_171514_(0, 38).m_171488_(7.25F, -4.99F, -1.59F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-9.0F, 12.0F, -4.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition cube_r11 = Body.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_()
            .m_171514_(30, 0)
            .m_171488_(7.57F, -5.0F, 0.21F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 21)
            .m_171488_(6.6F, -5.0F, 0.2F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-11.0F, 11.0F, -4.0F, -0.0443F, 0.1744F, -0.0077F)
      );
      PartDefinition cube_r12 = Body.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_().m_171514_(26, 36).m_171488_(7.1F, -5.01F, -0.29F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-11.0F, 12.0F, -4.0F, -0.1316F, 0.1744F, -0.0077F)
      );
      PartDefinition cube_r13 = Body.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_()
            .m_171514_(34, 23)
            .m_171488_(7.58F, -6.01F, -0.29F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(34, 19)
            .m_171488_(6.59F, -6.0F, -0.3F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-11.0F, 11.0F, -4.0F, -0.1316F, 0.1744F, -0.0077F)
      );
      PartDefinition cube_r14 = Body.m_171599_(
         "cube_r14",
         CubeListBuilder.m_171558_().m_171514_(30, 19).m_171488_(-1.0F, -7.9F, -0.7F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 8.0F, 3.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition cube_r15 = Body.m_171599_(
         "cube_r15",
         CubeListBuilder.m_171558_().m_171514_(30, 19).m_171488_(-1.0F, -7.93F, -0.7F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 8.0F, 3.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition cube_r16 = Body.m_171599_(
         "cube_r16",
         CubeListBuilder.m_171558_().m_171514_(0, 19).m_171488_(-0.5F, -4.0F, -0.6F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 9.0F, -3.0F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition cube_r17 = Body.m_171599_(
         "cube_r17",
         CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-0.5F, -4.0F, -0.6F, 9.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 11.0F, -3.0F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition cube_r18 = Body.m_171599_(
         "cube_r18",
         CubeListBuilder.m_171558_().m_171514_(36, 15).m_171488_(-1.8F, -2.1F, -1.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 3.0F, -0.0886F, 0.1739F, -0.0154F)
      );
      PartDefinition cube_r19 = Body.m_171599_(
         "cube_r19",
         CubeListBuilder.m_171558_().m_171514_(36, 12).m_171488_(-0.2F, -2.1F, -1.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 3.0F, -0.0886F, -0.1739F, 0.0154F)
      );
      PartDefinition cube_r20 = Body.m_171599_(
         "cube_r20",
         CubeListBuilder.m_171558_().m_171514_(22, 28).m_171488_(-2.0F, -2.2F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 1.0F, -2.0F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition cube_r21 = Body.m_171599_(
         "cube_r21",
         CubeListBuilder.m_171558_().m_171514_(0, 21).m_171488_(-2.0F, -1.85F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.2618F)
      );
      PartDefinition cube_r22 = Body.m_171599_(
         "cube_r22",
         CubeListBuilder.m_171558_().m_171514_(0, 21).m_171488_(-2.2F, -1.65F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(38, 27)
            .m_171488_(0.0F, -0.55F, -3.22F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 27)
            .m_171488_(0.0F, -0.55F, -7.77F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 11.0F, 5.0F, 0.0F, 0.0F, -0.4363F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(38, 27)
            .m_171488_(-1.0F, -0.55F, -3.22F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 27)
            .m_171488_(-1.0F, -0.55F, -7.77F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 11.0F, 5.0F, 0.0F, 0.0F, 0.4363F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(30, 5)
            .m_171488_(-2.0F, -1.0F, -0.14F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 5)
            .m_171488_(4.0F, -1.0F, -0.14F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, -3.0F, -0.3054F, 0.0F, 0.0F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 26)
            .m_171488_(2.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 26)
            .m_171488_(-4.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 2.0F, 3.0F, -0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r5 = Body.m_171599_(
         "Body_r5",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 26)
            .m_171488_(2.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(12, 26)
            .m_171488_(8.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-6.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F)
      );
      PartDefinition righthand = partdefinition.m_171599_("righthand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      PartDefinition lefthand = partdefinition.m_171599_("lefthand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.righthand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.lefthand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.lefthand.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F) * limbSwingAmount;
      this.righthand.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
   }
}
