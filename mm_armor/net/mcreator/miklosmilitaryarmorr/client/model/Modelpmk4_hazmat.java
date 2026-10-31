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

public class Modelpmk4_hazmat<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelpmk_4_hazmat"), "main");
   public final ModelPart Head;

   public Modelpmk4_hazmat(ModelPart root) {
      this.Head = root.m_171324_("Head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Head = partdefinition.m_171599_(
         "Head",
         CubeListBuilder.m_171558_()
            .m_171514_(20, 16)
            .m_171488_(-4.0F, -8.0F, -5.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 32)
            .m_171488_(3.2F, -8.01F, -4.99F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(1.0F, -8.01F, -4.99F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 12)
            .m_171488_(-2.0F, -6.01F, 3.01F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 34)
            .m_171488_(-2.0F, -2.01F, 3.01F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 15)
            .m_171488_(-2.0F, -4.01F, 3.01F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 18)
            .m_171488_(1.0F, -4.01F, 3.01F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-2.0F, -8.01F, -4.99F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 32)
            .m_171488_(-4.2F, -8.01F, -4.99F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 26)
            .m_171488_(-4.01F, -6.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 26)
            .m_171488_(-4.01F, -2.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 26)
            .m_171488_(2.01F, -6.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 26)
            .m_171488_(2.01F, -2.01F, -2.99F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 27)
            .m_171488_(-4.0F, -6.0F, -5.5F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 32)
            .m_171488_(-1.5F, -3.0F, -8.6F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 36)
            .m_171488_(-1.0F, -3.8F, -6.8F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 0)
            .m_171488_(-1.3F, -2.0F, -7.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 34)
            .m_171488_(-1.7F, -2.0F, -6.99F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Head.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(32, 6)
            .m_171488_(-3.1F, -1.6F, -2.8F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 36)
            .m_171488_(-4.1F, -1.1F, -2.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, 0.0F, -6.0F, 0.0F, 0.0436F, 0.2618F)
      );
      PartDefinition cube_r2 = Head.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(28, 36).m_171488_(-2.0F, -1.2F, -2.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, -1.0F, -6.0F, 0.0F, -0.0436F, -0.2618F)
      );
      PartDefinition cube_r3 = Head.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(28, 36).m_171488_(-2.0F, -1.2F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 1.0F, -6.0F, 0.2618F, 0.0F, 0.0F)
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
