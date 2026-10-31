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

public class Modelmarpat_hat<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelmarpat_hat"), "main");
   public final ModelPart Head;

   public Modelmarpat_hat(ModelPart root) {
      this.Head = root.m_171324_("Head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Head = partdefinition.m_171599_(
         "Head",
         CubeListBuilder.m_171558_().m_171514_(40, 15).m_171488_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Head.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(0, 30).m_171488_(-5.5F, 1.5F, 0.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.5F, -8.75F, -5.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r2 = Head.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 33)
            .m_171488_(4.5F, 0.5F, 3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 31)
            .m_171488_(-3.25F, 5.5F, 3.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 40)
            .m_171488_(-4.0F, 0.5F, 3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.75F, -6.25F, -5.0F, -0.1309F, 0.0F, 0.0F)
      );
      PartDefinition cube_r3 = Head.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 0)
            .m_171488_(-6.5F, 0.5F, -1.0F, 7.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 15)
            .m_171488_(0.5F, 0.5F, -1.0F, 6.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, -6.0F, -6.0F, -0.0436F, 0.0F, 0.0F)
      );
      return LayerDefinition.m_171565_(meshdefinition, 128, 128);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Head.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.Head.f_104204_ = netHeadYaw / (180.0F / (float)Math.PI);
      this.Head.f_104203_ = headPitch / (180.0F / (float)Math.PI);
   }
}
