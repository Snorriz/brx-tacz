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

public class Modelhelmet_kaska_1m<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelhelmet_kaska_1m"), "main");
   public final ModelPart head;
   public final ModelPart base;
   public final ModelPart strap;

   public Modelhelmet_kaska_1m(ModelPart root) {
      this.head = root.m_171324_("head");
      this.base = this.head.m_171324_("base");
      this.strap = this.head.m_171324_("strap");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition head = partdefinition.m_171599_("head", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 0.0F, 0.0F));
      PartDefinition base = head.m_171599_(
         "base",
         CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0F, -8.7F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = base.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(18, 29).m_171488_(-2.999F, -2.91F, -0.96F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, -5.0F, -4.0F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition cube_r2 = base.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 33)
            .m_171488_(-1.2F, -2.0F, -0.92F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 9)
            .m_171488_(-1.0F, -3.9F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition cube_r3 = base.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_()
            .m_171514_(36, 15)
            .m_171488_(-1.14F, -1.25F, 3.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 20)
            .m_171488_(-1.15F, -1.26F, -4.01F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.7418F)
      );
      PartDefinition cube_r4 = base.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 35)
            .m_171488_(-3.5F, -2.95F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 35)
            .m_171488_(-11.5F, -2.95F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(7.0F, -5.0F, 3.0F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r5 = base.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(0, 29).m_171488_(-3.999F, -3.0F, -0.11F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, -5.0F, 4.0F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r6 = base.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 9)
            .m_171488_(0.0F, -3.9F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 33)
            .m_171488_(0.2F, -2.0F, -0.92F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition cube_r7 = base.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 20)
            .m_171488_(0.15F, -1.26F, -4.01F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 17)
            .m_171488_(0.14F, -1.26F, 3.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -7.0F, 0.0F, 0.0F, 0.0F, -0.7418F)
      );
      PartDefinition cube_r8 = base.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_()
            .m_171514_(32, 6)
            .m_171488_(3.01F, -1.09F, 0.29F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 2)
            .m_171488_(-4.01F, -1.09F, 0.3F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, -7.0F, 4.0F, 0.8727F, 0.0F, 0.0F)
      );
      PartDefinition cube_r9 = base.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_()
            .m_171514_(36, 19)
            .m_171488_(-4.01F, -1.19F, -1.19F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 0)
            .m_171488_(-3.99F, -1.2F, -1.2F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, -7.0F, -4.0F, -0.7854F, 0.0F, 0.0F)
      );
      PartDefinition strap = head.m_171599_("strap", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 0.0F, 0.0F));
      PartDefinition cube_r10 = strap.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_()
            .m_171514_(6, 35)
            .m_171488_(-1.2F, -6.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 35)
            .m_171488_(6.2F, -6.0F, 0.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, -1.0F, -3.0F, -1.0036F, 0.0F, 0.0F)
      );
      PartDefinition cube_r11 = strap.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_()
            .m_171514_(36, 6)
            .m_171488_(-1.2F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(14, 35)
            .m_171488_(6.2F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 0.0F, -4.0F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition cube_r12 = strap.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_()
            .m_171514_(36, 13)
            .m_171488_(-4.21F, -0.91F, -1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 4)
            .m_171488_(-3.79F, -0.9F, -1.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, -3.0F, 0.5236F, 0.0F, 0.0F)
      );
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.head.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.head.f_104204_ = netHeadYaw / (180.0F / (float)Math.PI);
      this.head.f_104203_ = headPitch / (180.0F / (float)Math.PI);
      this.strap.f_104204_ = netHeadYaw / (180.0F / (float)Math.PI);
      this.strap.f_104203_ = headPitch / (180.0F / (float)Math.PI);
      this.base.f_104204_ = netHeadYaw / (180.0F / (float)Math.PI);
      this.base.f_104203_ = headPitch / (180.0F / (float)Math.PI);
   }
}
