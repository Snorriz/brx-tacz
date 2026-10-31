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

public class Modelm40_gasmask<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelm_40_gasmask"), "main");
   public final ModelPart Head;

   public Modelm40_gasmask(ModelPart root) {
      this.Head = root.m_171324_("Head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Head = partdefinition.m_171599_(
         "Head",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 5)
            .m_171488_(-4.0F, -8.0F, -5.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 5)
            .m_171488_(3.2F, -8.0F, -4.99F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 0)
            .m_171488_(1.0F, -8.01F, -0.99F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(8, 30)
            .m_171488_(-2.0F, -6.01F, 3.01F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(8, 33)
            .m_171488_(-2.0F, -2.01F, 3.01F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 23)
            .m_171488_(-2.0F, -4.01F, 3.01F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 0)
            .m_171488_(1.0F, -4.01F, 3.01F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-4.0F, -8.01F, -4.99F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 0)
            .m_171488_(-2.0F, -8.01F, -0.99F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 18)
            .m_171488_(-4.2F, -8.0F, -4.99F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 15)
            .m_171488_(-4.01F, -6.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 15)
            .m_171488_(-4.01F, -2.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 15)
            .m_171488_(2.01F, -6.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 15)
            .m_171488_(2.01F, -2.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 19)
            .m_171488_(0.19F, -6.0F, -5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 29)
            .m_171488_(-4.19F, -6.0F, -5.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(26, 34)
            .m_171488_(-1.0F, -3.8F, -7.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 7)
            .m_171488_(-1.3F, -2.0F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 30)
            .m_171488_(-1.7F, -2.0F, -6.99F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Head.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(28, 13)
            .m_171488_(-3.1F, -1.6F, -2.8F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 31)
            .m_171488_(-4.1F, -1.1F, -2.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, 0.0F, -7.0F, 0.0F, 0.0436F, 0.2618F)
      );
      PartDefinition cube_r2 = Head.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(18, 31).m_171488_(-2.0F, -1.2F, -2.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, -1.0F, -7.0F, 0.0F, -0.0436F, -0.2618F)
      );
      PartDefinition cube_r3 = Head.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(28, 24).m_171488_(-2.0F, -1.4F, -2.2F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 1.0F, -7.0F, 0.2618F, 0.0F, 0.0F)
      );
      PartDefinition cube_r4 = Head.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(0, 23).m_171488_(-2.5F, -3.0F, -2.4F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 0.0F, -7.0F, 0.0436F, 0.0F, 0.0F)
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
