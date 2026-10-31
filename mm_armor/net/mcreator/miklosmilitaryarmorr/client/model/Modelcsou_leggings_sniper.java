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

public class Modelcsou_leggings_sniper<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelcsou_leggings_sniper"), "main");
   public final ModelPart RightLeg;
   public final ModelPart LeftLeg;

   public Modelcsou_leggings_sniper(ModelPart root) {
      this.RightLeg = root.m_171324_("RightLeg");
      this.LeftLeg = root.m_171324_("LeftLeg");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition RightLeg = partdefinition.m_171599_(
         "RightLeg",
         CubeListBuilder.m_171558_()
            .m_171514_(16, 0)
            .m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 0)
            .m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
            .m_171514_(16, 13)
            .m_171488_(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition cube_r1 = RightLeg.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(32, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.65F, 8.25F, 1.75F, 0.3043F, 0.0262F, -0.0832F)
      );
      PartDefinition cube_r2 = RightLeg.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(32, 28).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.85F, 7.25F, 1.75F, 0.303F, -0.0393F, 0.1249F)
      );
      PartDefinition cube_r3 = RightLeg.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(32, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.65F, 5.75F, 1.75F, 0.2986F, 0.0651F, -0.2084F)
      );
      PartDefinition cube_r4 = RightLeg.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(32, 32).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.35F, 4.5F, 2.0F, 0.3052F, 0.0131F, -0.0416F)
      );
      PartDefinition cube_r5 = RightLeg.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(32, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.65F, 3.25F, 1.75F, 0.3019F, 0.0471F, -0.1499F)
      );
      PartDefinition cube_r6 = RightLeg.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_().m_171514_(16, 35).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.85F, 2.5F, 1.75F, 0.3023F, -0.0445F, 0.1416F)
      );
      PartDefinition cube_r7 = RightLeg.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(32, 32).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.35F, 8.75F, 2.0F, 0.3052F, -0.0131F, 0.0416F)
      );
      PartDefinition cube_r8 = RightLeg.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_().m_171514_(32, 16).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.15F, 2.75F, -1.75F, -0.287F, -0.0309F, -0.0972F)
      );
      PartDefinition cube_r9 = RightLeg.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_().m_171514_(16, 27).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.1F, 3.75F, -1.75F, -0.2741F, 0.0917F, 0.322F)
      );
      PartDefinition cube_r10 = RightLeg.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_().m_171514_(32, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.15F, 5.0F, -1.75F, -0.2808F, -0.0677F, -0.223F)
      );
      PartDefinition cube_r11 = RightLeg.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_().m_171514_(32, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.85F, 6.0F, -2.0F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r12 = RightLeg.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_().m_171514_(8, 32).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.15F, 8.0F, -2.0F, -0.2881F, -0.0186F, -0.0554F)
      );
      PartDefinition cube_r13 = RightLeg.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_().m_171514_(32, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.85F, 9.0F, -1.75F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r14 = RightLeg.m_171599_(
         "cube_r14",
         CubeListBuilder.m_171558_().m_171514_(0, 32).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.6F, 2.5F, -0.25F, -0.2618F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r15 = RightLeg.m_171599_(
         "cube_r15",
         CubeListBuilder.m_171558_().m_171514_(24, 31).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.85F, 3.75F, 0.75F, 0.0436F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r16 = RightLeg.m_171599_(
         "cube_r16",
         CubeListBuilder.m_171558_().m_171514_(16, 31).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.6F, 4.5F, -1.0F, -0.1744F, 0.0076F, 0.2611F)
      );
      PartDefinition cube_r17 = RightLeg.m_171599_(
         "cube_r17",
         CubeListBuilder.m_171558_().m_171514_(24, 27).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.85F, 6.5F, 0.5F, 0.1745F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r18 = RightLeg.m_171599_(
         "cube_r18",
         CubeListBuilder.m_171558_().m_171514_(32, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.85F, 8.75F, 0.75F, 0.0437F, 0.0038F, 0.2616F)
      );
      PartDefinition cube_r19 = RightLeg.m_171599_(
         "cube_r19",
         CubeListBuilder.m_171558_().m_171514_(16, 27).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.6F, 8.25F, -1.0F, -0.0872F, 0.0038F, 0.2616F)
      );
      PartDefinition LeftLeg = partdefinition.m_171599_(
         "LeftLeg",
         CubeListBuilder.m_171558_()
            .m_171514_(16, 0)
            .m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 16)
            .m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
            .m_171514_(16, 20)
            .m_171488_(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(1.9F, 12.0F, 0.0F)
      );
      PartDefinition cube_r20 = LeftLeg.m_171599_(
         "cube_r20",
         CubeListBuilder.m_171558_().m_171514_(8, 32).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.85F, 8.25F, 1.75F, 0.3043F, 0.0262F, -0.0832F)
      );
      PartDefinition cube_r21 = LeftLeg.m_171599_(
         "cube_r21",
         CubeListBuilder.m_171558_().m_171514_(32, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.65F, 2.5F, 1.75F, 0.3023F, -0.0445F, 0.1416F)
      );
      PartDefinition cube_r22 = LeftLeg.m_171599_(
         "cube_r22",
         CubeListBuilder.m_171558_().m_171514_(32, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.85F, 3.25F, 1.75F, 0.3019F, 0.0471F, -0.1499F)
      );
      PartDefinition cube_r23 = LeftLeg.m_171599_(
         "cube_r23",
         CubeListBuilder.m_171558_().m_171514_(32, 24).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.15F, 4.5F, 2.0F, 0.3052F, 0.0131F, -0.0416F)
      );
      PartDefinition cube_r24 = LeftLeg.m_171599_(
         "cube_r24",
         CubeListBuilder.m_171558_().m_171514_(32, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.85F, 5.75F, 1.75F, 0.2986F, 0.0651F, -0.2084F)
      );
      PartDefinition cube_r25 = LeftLeg.m_171599_(
         "cube_r25",
         CubeListBuilder.m_171558_().m_171514_(32, 20).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.65F, 7.25F, 1.75F, 0.303F, -0.0393F, 0.1249F)
      );
      PartDefinition cube_r26 = LeftLeg.m_171599_(
         "cube_r26",
         CubeListBuilder.m_171558_().m_171514_(32, 16).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.1F, 2.75F, -1.75F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r27 = LeftLeg.m_171599_(
         "cube_r27",
         CubeListBuilder.m_171558_().m_171514_(16, 27).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.15F, 3.75F, -1.75F, -0.2741F, 0.0917F, 0.322F)
      );
      PartDefinition cube_r28 = LeftLeg.m_171599_(
         "cube_r28",
         CubeListBuilder.m_171558_().m_171514_(32, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.1F, 5.0F, -1.75F, -0.287F, -0.0309F, -0.0972F)
      );
      PartDefinition cube_r29 = LeftLeg.m_171599_(
         "cube_r29",
         CubeListBuilder.m_171558_().m_171514_(32, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.9F, 6.0F, -2.0F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r30 = LeftLeg.m_171599_(
         "cube_r30",
         CubeListBuilder.m_171558_().m_171514_(8, 32).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.6F, 8.0F, -2.0F, -0.2834F, 0.0556F, 0.1958F)
      );
      PartDefinition cube_r31 = LeftLeg.m_171599_(
         "cube_r31",
         CubeListBuilder.m_171558_()
            .m_171514_(32, 4)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 4)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.4F, 9.25F, -1.75F, -0.2834F, -0.0555F, -0.181F)
      );
      PartDefinition cube_r32 = LeftLeg.m_171599_(
         "cube_r32",
         CubeListBuilder.m_171558_().m_171514_(6, 36).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.1F, 2.5F, 0.5F, 0.0436F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r33 = LeftLeg.m_171599_(
         "cube_r33",
         CubeListBuilder.m_171558_().m_171514_(0, 36).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.85F, 3.5F, -0.75F, -0.0873F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r34 = LeftLeg.m_171599_(
         "cube_r34",
         CubeListBuilder.m_171558_().m_171514_(0, 36).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.1F, 5.0F, -0.25F, -0.3054F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r35 = LeftLeg.m_171599_(
         "cube_r35",
         CubeListBuilder.m_171558_().m_171514_(0, 36).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.85F, 8.75F, -0.75F, 0.0873F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r36 = LeftLeg.m_171599_(
         "cube_r36",
         CubeListBuilder.m_171558_().m_171514_(0, 36).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.1F, 7.75F, 0.75F, -0.0436F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r37 = LeftLeg.m_171599_(
         "cube_r37",
         CubeListBuilder.m_171558_().m_171514_(24, 35).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.85F, 6.5F, -0.5F, 0.1745F, 0.0F, -0.3491F)
      );
      return LayerDefinition.m_171565_(meshdefinition, 64, 64);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.RightLeg.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.LeftLeg.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.LeftLeg.f_104203_ = Mth.m_14089_(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
      this.RightLeg.f_104203_ = Mth.m_14089_(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
   }
}
