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

public class Modelucon_chestplate<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelucon_chestplate"), "main");
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;

   public Modelucon_chestplate(ModelPart root) {
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
            .m_171514_(32, 26)
            .m_171488_(-3.0F, 2.0F, 1.8F, 6.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 11)
            .m_171488_(-2.0F, 1.0F, -2.8F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 20)
            .m_171488_(-4.0F, 5.0F, 1.5F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 41)
            .m_171488_(-2.0F, 1.0F, 1.8F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 5)
            .m_171488_(-3.0F, 2.7F, -2.78F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 38)
            .m_171488_(-3.0F, 3.0F, -2.8F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 7)
            .m_171488_(-2.5F, 10.0F, -2.8F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-4.5F, 10.3F, -2.5F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 14)
            .m_171488_(-4.0F, 5.0F, -2.5F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 38)
            .m_171488_(3.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 38)
            .m_171488_(-4.3F, 5.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 0)
            .m_171488_(0.0F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(46, 14)
            .m_171488_(0.0F, -0.24F, 1.01F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(38, 46).m_171488_(0.0394F, 0.0F, -2.3F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 6.0F, -1.0F, 0.0515F, 1.1784F, 0.0344F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(46, 28).m_171488_(0.0F, -2.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 8.0F, -1.0F, 0.1387F, 1.1784F, 0.0344F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(46, 32).m_171488_(0.0394F, 0.0F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 6.0F, -2.0F, 0.0265F, 0.4805F, 0.0148F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(46, 28).m_171488_(0.0F, -2.0F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 8.0F, 2.0F, -3.0358F, 0.0867F, 3.1284F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(38, 46).m_171488_(0.0394F, 0.0F, -1.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 6.0F, 2.0F, -3.123F, 0.0867F, 3.1284F)
      );
      PartDefinition cube_r6 = Body.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_().m_171514_(46, 28).m_171488_(0.0F, -2.0F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 8.0F, 2.0F, -3.0401F, 0.3921F, 3.1274F)
      );
      PartDefinition cube_r7 = Body.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(38, 46).m_171488_(0.0394F, 0.0F, -1.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 6.0F, 2.0F, -3.1273F, 0.3921F, 3.1274F)
      );
      PartDefinition cube_r8 = Body.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_().m_171514_(46, 28).m_171488_(0.0F, -2.0F, -0.8F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 8.0F, -2.0F, 0.1138F, 0.4805F, 0.0148F)
      );
      PartDefinition cube_r9 = Body.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_().m_171514_(24, 44).m_171488_(-0.05F, -2.9F, -1.8F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 5.0F, 4.0F, 0.0833F, -0.0444F, -3.1279F)
      );
      PartDefinition cube_r10 = Body.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_().m_171514_(24, 44).m_171488_(-0.2F, -2.9F, -1.35F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 5.0F, 3.0F, 0.0873F, -0.3052F, 3.1327F)
      );
      PartDefinition cube_r11 = Body.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 44)
            .m_171488_(0.0F, -3.1F, -0.9F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(46, 26)
            .m_171488_(-0.5F, -3.0F, -0.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(34, 41)
            .m_171488_(-0.5F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 9.0F, -2.0F, 0.0873F, -0.4363F, 0.0F)
      );
      PartDefinition cube_r12 = Body.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_()
            .m_171514_(46, 26)
            .m_171488_(-0.5F, -3.0F, -0.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(34, 41)
            .m_171488_(-0.5F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 9.0F, 3.0F, -3.1278F, -0.0435F, 3.1365F)
      );
      PartDefinition cube_r13 = Body.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_()
            .m_171514_(34, 41)
            .m_171488_(-0.8F, -2.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(46, 26)
            .m_171488_(-0.8F, -3.0F, -0.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 9.0F, 3.0F, -3.1271F, -0.3052F, 3.1327F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(46, 14)
            .m_171488_(-2.0F, -0.24F, 1.01F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 0)
            .m_171488_(-2.0F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(34, 46)
            .m_171488_(-0.5F, 1.3F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 43)
            .m_171488_(-0.5F, 0.3F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-5.0F, 10.0F, 1.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_().m_171514_(30, 44).m_171488_(-0.49F, -4.0574F, -0.0478F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-5.0F, 14.0F, -2.0F, -0.2618F, 0.0F, 0.0F)
      );
      PartDefinition Body_r5 = Body.m_171599_(
         "Body_r5",
         CubeListBuilder.m_171558_().m_171514_(24, 38).m_171488_(-1.5F, -0.8F, -0.1F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 3.0F, -3.0F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition Body_r6 = Body.m_171599_(
         "Body_r6",
         CubeListBuilder.m_171558_().m_171514_(24, 41).m_171488_(-2.0F, -0.8F, -0.4F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 7.0F, -3.0F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition Body_r7 = Body.m_171599_(
         "Body_r7",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 14)
            .m_171488_(0.0F, 0.0F, -3.21F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 14)
            .m_171488_(0.4F, 0.0F, -3.22F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.7854F)
      );
      PartDefinition Body_r8 = Body.m_171599_(
         "Body_r8",
         CubeListBuilder.m_171558_()
            .m_171514_(28, 20)
            .m_171488_(0.0F, 0.0F, -3.21F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 20)
            .m_171488_(0.0F, 0.4F, -3.22F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.7854F)
      );
      PartDefinition Body_r9 = Body.m_171599_(
         "Body_r9",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 46)
            .m_171488_(0.0F, 0.0F, -0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(6, 46)
            .m_171488_(0.0F, 0.0F, 3.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 0.0F, -2.0F, 0.0F, 0.0F, 0.4363F)
      );
      PartDefinition Body_r10 = Body.m_171599_(
         "Body_r10",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 46)
            .m_171488_(-2.0F, 0.0F, -0.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(6, 46)
            .m_171488_(-2.0F, 0.0F, 3.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 0.0F, -2.0F, 0.0F, 0.0F, -0.4363F)
      );
      PartDefinition Body_r11 = Body.m_171599_(
         "Body_r11",
         CubeListBuilder.m_171558_().m_171514_(46, 16).m_171488_(0.0F, 0.0F, -2.79F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0472F)
      );
      PartDefinition Body_r12 = Body.m_171599_(
         "Body_r12",
         CubeListBuilder.m_171558_().m_171514_(46, 18).m_171488_(-2.0F, 0.0F, -2.79F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 0.0F, 0.0F, 0.0F, -1.0472F)
      );
      PartDefinition RightArm = partdefinition.m_171599_(
         "RightArm",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 0)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 22)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
            .m_171514_(32, 35)
            .m_171488_(-3.0F, 8.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition LeftArm = partdefinition.m_171599_(
         "LeftArm",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 0)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 22)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
            .m_171514_(32, 35)
            .m_171488_(-1.0F, 8.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
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
