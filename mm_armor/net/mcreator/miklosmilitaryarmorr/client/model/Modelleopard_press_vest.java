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

public class Modelleopard_press_vest<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelleopard_press_vest"), "main");
   public final ModelPart Body;
   public final ModelPart bone;

   public Modelleopard_press_vest(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.bone = root.m_171324_("bone");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 0)
            .m_171488_(-3.5F, 0.0F, 1.8F, 7.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 5)
            .m_171488_(-4.0F, 5.0F, 1.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 10)
            .m_171488_(-3.5F, 1.0F, -2.8F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 19)
            .m_171488_(1.5F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(6, 19)
            .m_171488_(-3.5F, 0.0F, -2.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 0)
            .m_171488_(-4.0F, 5.0F, -2.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 10)
            .m_171488_(3.3F, 5.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 10)
            .m_171488_(-4.3F, 5.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 18)
            .m_171488_(-3.5F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 18)
            .m_171488_(1.5F, -0.25F, -2.01F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition bone = partdefinition.m_171599_("bone", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.bone.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }
}
