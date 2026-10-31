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

public class Modelsoviet_ushanka<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelsoviet_ushanka"), "main");
   public final ModelPart Head;

   public Modelsoviet_ushanka(ModelPart root) {
      this.Head = root.m_171324_("Head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Head = partdefinition.m_171599_(
         "Head",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 0)
            .m_171488_(-4.0F, -8.55F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
            .m_171514_(32, 3)
            .m_171488_(3.7F, -9.0F, -2.3F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 11)
            .m_171488_(3.69F, -7.0F, 1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 11)
            .m_171488_(-4.69F, -7.0F, 1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 0)
            .m_171488_(-4.0F, -7.0F, 4.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 3)
            .m_171488_(-4.7F, -9.0F, -2.3F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Head.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(0, 32).m_171488_(-4.0F, -1.5F, -2.3F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition cube_r2 = Head.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(0, 32).m_171488_(0.0F, -1.5F, -2.3F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Head.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.Head.f_104204_ = netHeadYaw / (180.0F / (float)Math.PI);
      this.Head.f_104203_ = headPitch / (180.0F / (float)Math.PI);
   }
}
