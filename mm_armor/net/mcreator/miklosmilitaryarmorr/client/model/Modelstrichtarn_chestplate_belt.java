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

public class Modelstrichtarn_chestplate_belt<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelstrichtarn_chestplate_belt"), "main");
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;

   public Modelstrichtarn_chestplate_belt(ModelPart root) {
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
            .m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition belt_r1 = Body.m_171599_(
         "belt_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 29)
            .m_171488_(-0.8F, -5.3F, -2.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 29)
            .m_171488_(1.8F, -4.6F, -2.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 29)
            .m_171488_(-0.4F, -4.6F, -2.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 12.0F, -2.0F, 0.0F, 0.1745F, 0.0F)
      );
      PartDefinition belt_r2 = Body.m_171599_(
         "belt_r2",
         CubeListBuilder.m_171558_().m_171514_(20, 29).m_171488_(-0.7F, -4.3F, -1.25F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 10.0F, -1.0F, 0.0873F, -0.1745F, 0.0F)
      );
      PartDefinition belt_r3 = Body.m_171599_(
         "belt_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(12, 29)
            .m_171488_(-1.0F, -2.3F, -1.7F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 29)
            .m_171488_(-1.0F, -3.3F, -0.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, 10.0F, 0.0F, 0.0873F, 0.0F, 0.0F)
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
