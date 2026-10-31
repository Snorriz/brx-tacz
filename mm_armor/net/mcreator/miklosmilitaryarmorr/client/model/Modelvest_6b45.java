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

public class Modelvest_6b45<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelvest_6b_45"), "main");
   public final ModelPart Body;
   public final ModelPart RightArm;

   public Modelvest_6b45(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.RightArm = root.m_171324_("RightArm");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 0)
            .m_171488_(-4.0F, 0.0F, 1.8F, 8.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 12)
            .m_171488_(-2.5F, 9.5F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 14)
            .m_171488_(-2.5F, 7.75F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 5)
            .m_171488_(-2.5F, 6.0F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 22)
            .m_171488_(-2.5F, 1.0F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 24)
            .m_171488_(-2.5F, 1.0F, -2.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 26)
            .m_171488_(-2.5F, 9.0F, -2.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 16)
            .m_171488_(-3.5F, 7.0F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 18)
            .m_171488_(-3.5F, 5.0F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 20)
            .m_171488_(-3.5F, 3.0F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 12)
            .m_171488_(-4.0F, 0.0F, -2.8F, 8.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 7)
            .m_171488_(3.3F, 6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 32)
            .m_171488_(3.4F, 6.2F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 24)
            .m_171488_(3.4F, 6.2F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 32)
            .m_171488_(3.4F, 8.0F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 27)
            .m_171488_(3.4F, 8.0F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 34)
            .m_171488_(3.4F, 9.8F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 27)
            .m_171488_(3.4F, 9.8F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(4, 34)
            .m_171488_(-4.4F, 6.2F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 7)
            .m_171488_(-4.4F, 6.2F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 34)
            .m_171488_(-4.4F, 8.0F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 29)
            .m_171488_(-4.4F, 8.0F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(34, 16)
            .m_171488_(-4.4F, 9.8F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 0)
            .m_171488_(-4.4F, 9.8F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 7)
            .m_171488_(-4.3F, 6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 22)
            .m_171488_(-4.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 22)
            .m_171488_(2.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(14, 32)
            .m_171488_(2.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 32)
            .m_171488_(-4.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(14, 32)
            .m_171488_(2.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 32)
            .m_171488_(8.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-6.0F, 2.0F, 3.0F, -0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_().m_171514_(10, 32).m_171488_(-0.2F, -1.0F, -0.3F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 1.0F, -3.0F, 0.0F, -0.8727F, 0.0F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(18, 0).m_171488_(-2.2F, -1.65F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(18, 0).m_171488_(-2.0F, -1.85F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.2618F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(30, 28).m_171488_(-2.0F, -2.2F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 1.0F, -2.0F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(30, 31).m_171488_(-0.2F, -2.1F, -1.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 3.0F, -0.0886F, -0.1739F, 0.0154F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(10, 24).m_171488_(-1.8F, -2.1F, -1.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 3.0F, -0.0886F, 0.1739F, -0.0154F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_().m_171514_(10, 32).m_171488_(7.7F, -1.0F, -0.7F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-9.0F, 1.0F, 4.0F, 0.0F, 0.9163F, 0.0F)
      );
      PartDefinition RightArm = partdefinition.m_171599_("RightArm", CubeListBuilder.m_171558_(), PartPose.m_171419_(-5.0F, 2.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.RightArm.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
