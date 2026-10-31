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

public class Modeltsh_4_helmet_tan<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modeltsh_4_helmet_tan"), "main");
   public final ModelPart head;
   public final ModelPart base;

   public Modeltsh_4_helmet_tan(ModelPart root) {
      this.head = root.m_171324_("head");
      this.base = this.head.m_171324_("base");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition head = partdefinition.m_171599_("head", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 0.0F, 0.0F));
      PartDefinition base = head.m_171599_(
         "base",
         CubeListBuilder.m_171558_()
            .m_171514_(36, 9)
            .m_171488_(-3.999F, -8.6999F, 3.1F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-4.0F, -8.7F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 29)
            .m_171488_(2.501F, -9.2F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 29)
            .m_171488_(-3.499F, -9.2F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 29)
            .m_171488_(-1.499F, -9.2F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 29)
            .m_171488_(0.501F, -9.2F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = base.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(20, 28).m_171488_(-0.41F, -1.93F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-5.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.7418F)
      );
      PartDefinition cube_r2 = base.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(40, 25).m_171488_(-2.8F, -1.7F, -1.15F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, -6.0F, -4.0F, -0.1747F, 0.043F, 0.0076F)
      );
      PartDefinition cube_r3 = base.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(0, 20).m_171488_(-1.2F, -1.7F, -1.15F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, -6.0F, -3.0F, 0.0036F, -8.0E-4F, 0.0948F)
      );
      PartDefinition cube_r4 = base.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(0, 20).m_171488_(-0.8F, -1.7F, -1.15F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -6.0F, -3.0F, 0.0036F, -8.0E-4F, -0.0951F)
      );
      PartDefinition cube_r5 = base.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(40, 25).m_171488_(-1.2F, -1.7F, -1.15F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -6.0F, -4.0F, -0.1747F, -0.043F, 0.0076F)
      );
      PartDefinition cube_r6 = base.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_().m_171514_(40, 25).m_171488_(-1.15F, -1.7F, -1.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, -6.0F, 5.0F, 0.0436F, 0.829F, 0.0F)
      );
      PartDefinition cube_r7 = base.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(40, 25).m_171488_(0.5F, -1.7F, -1.75F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-6.0F, -6.0F, 4.0F, 0.0436F, -0.829F, 0.0F)
      );
      PartDefinition cube_r8 = base.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_()
            .m_171514_(10, 43)
            .m_171488_(-3.499F, -3.32F, -1.3F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 43)
            .m_171488_(-5.499F, -3.32F, -1.3F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 43)
            .m_171488_(-7.499F, -3.32F, -1.3F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 43)
            .m_171488_(-9.499F, -3.32F, -1.3F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(6.0F, -5.0F, -4.0F, -0.1745F, 0.0F, 0.0F)
      );
      PartDefinition cube_r9 = base.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_()
            .m_171514_(38, 28)
            .m_171488_(-5.0F, -2.3F, -1.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 0)
            .m_171488_(-6.999F, -3.0F, -1.0F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(10, 38)
            .m_171488_(-7.001F, -3.0F, -0.999F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, -5.0F, -4.0F, -0.0873F, 0.0F, 0.0F)
      );
      PartDefinition cube_r10 = base.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_()
            .m_171514_(38, 32)
            .m_171480_()
            .m_171488_(-1.19F, -1.8F, -0.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .m_171555_(false),
         PartPose.m_171423_(-5.0F, -5.0F, -1.0F, 0.0F, 0.0F, 0.1745F)
      );
      PartDefinition cube_r11 = base.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_().m_171514_(38, 32).m_171488_(-0.81F, -1.7F, -0.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, -5.0F, -1.0F, 0.0F, 0.0F, -0.1745F)
      );
      PartDefinition cube_r12 = base.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_().m_171514_(0, 38).m_171480_().m_171488_(-1.19F, -0.1F, -0.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).m_171555_(false),
         PartPose.m_171423_(-5.0F, -5.0F, -1.0F, 0.0F, 0.0F, -0.0873F)
      );
      PartDefinition cube_r13 = base.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 19)
            .m_171488_(-0.99F, 2.0F, -1.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 11)
            .m_171488_(-0.99F, -1.0F, -1.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 43)
            .m_171480_()
            .m_171488_(-0.99F, -1.0F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171555_(false),
         PartPose.m_171423_(-4.0F, -4.0F, -1.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition cube_r14 = base.m_171599_(
         "cube_r14",
         CubeListBuilder.m_171558_()
            .m_171514_(20, 28)
            .m_171480_()
            .m_171488_(-1.15F, -1.26F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171555_(false),
         PartPose.m_171423_(-4.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.7418F)
      );
      PartDefinition cube_r15 = base.m_171599_(
         "cube_r15",
         CubeListBuilder.m_171558_()
            .m_171514_(20, 20)
            .m_171488_(-3.999F, -3.0F, -1.0F, 8.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 37)
            .m_171488_(0.501F, -2.85F, 0.64F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 37)
            .m_171488_(-1.499F, -2.85F, 0.64F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 37)
            .m_171488_(-3.499F, -2.85F, 0.64F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(40, 37)
            .m_171488_(2.501F, -2.85F, 0.64F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, -5.0F, 4.0F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r16 = base.m_171599_(
         "cube_r16",
         CubeListBuilder.m_171558_()
            .m_171514_(36, 43)
            .m_171488_(2.501F, -1.42F, 0.68F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 43)
            .m_171488_(0.501F, -1.42F, 0.68F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 43)
            .m_171488_(-1.499F, -1.42F, 0.68F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 43)
            .m_171488_(4.501F, -1.42F, 0.68F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 7)
            .m_171488_(-1.999F, -1.0F, 0.36F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, -7.0F, 4.0F, 0.8727F, 0.0F, 0.0F)
      );
      PartDefinition cube_r17 = base.m_171599_(
         "cube_r17",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 44)
            .m_171488_(-3.499F, -1.57F, -1.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 44)
            .m_171488_(-5.499F, -1.57F, -1.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 44)
            .m_171488_(-7.499F, -1.57F, -1.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 44)
            .m_171488_(-1.499F, -1.57F, -1.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 5)
            .m_171488_(-7.999F, -1.2F, -1.26F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -7.0F, -4.0F, -0.7854F, 0.0F, 0.0F)
      );
      PartDefinition cube_r18 = base.m_171599_(
         "cube_r18",
         CubeListBuilder.m_171558_()
            .m_171514_(18, 9)
            .m_171488_(0.0F, -3.9F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
            .m_171514_(18, 37)
            .m_171488_(0.0F, -0.9F, -2.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 37)
            .m_171488_(0.0F, 2.1F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(30, 43)
            .m_171488_(-1.0F, -0.9F, 3.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.0436F)
      );
      PartDefinition cube_r19 = base.m_171599_(
         "cube_r19",
         CubeListBuilder.m_171558_().m_171514_(0, 38).m_171488_(-0.81F, 0.0F, 1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, -5.0F, -3.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition cube_r20 = base.m_171599_(
         "cube_r20",
         CubeListBuilder.m_171558_().m_171514_(0, 9).m_171488_(0.0F, -4.0F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-5.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0436F)
      );
      PartDefinition cube_r21 = base.m_171599_(
         "cube_r21",
         CubeListBuilder.m_171558_().m_171514_(20, 28).m_171488_(0.15F, -1.26F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -7.0F, 0.0F, 0.0F, 0.0F, -0.7418F)
      );
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.head.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.head.f_104204_ = netHeadYaw / (180.0F / (float)Math.PI);
      this.head.f_104203_ = headPitch / (180.0F / (float)Math.PI);
      this.base.f_104204_ = netHeadYaw / (180.0F / (float)Math.PI);
      this.base.f_104203_ = headPitch / (180.0F / (float)Math.PI);
   }
}
