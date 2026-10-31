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

public class Modelcsou_hat_sniper<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelcsou_hat_sniper"), "main");
   public final ModelPart Head;

   public Modelcsou_hat_sniper(ModelPart root) {
      this.Head = root.m_171324_("Head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Head = partdefinition.m_171599_(
         "Head",
         CubeListBuilder.m_171558_()
            .m_171514_(40, 15)
            .m_171488_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
            .m_171514_(40, 31)
            .m_171488_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Head.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(18, 41).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, -6.0F, 4.25F, 0.3051F, 0.1225F, -0.0987F)
      );
      PartDefinition cube_r2 = Head.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(34, 41).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.25F, -5.0F, -0.5F, 0.1745F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r3 = Head.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(20, 49).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.5F, -5.75F, 3.25F, -0.0436F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r4 = Head.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(20, 49).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.25F, -5.75F, -3.75F, 0.0873F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r5 = Head.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(20, 49).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.25F, -5.0F, 1.75F, -0.3054F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r6 = Head.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_().m_171514_(20, 49).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.75F, -6.0F, -2.5F, -0.0873F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r7 = Head.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(26, 49).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.25F, -6.5F, 0.5F, 0.0436F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r8 = Head.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_().m_171514_(26, 41).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.5F, -5.5F, -4.75F, -0.2834F, -0.0555F, -0.181F)
      );
      PartDefinition cube_r9 = Head.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_().m_171514_(18, 41).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.5F, -5.0F, -4.25F, -0.2834F, 0.0556F, 0.1958F)
      );
      PartDefinition cube_r10 = Head.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, -6.75F, -4.0F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r11 = Head.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, -7.75F, -3.0F, -1.3874F, 0.1696F, 0.0726F)
      );
      PartDefinition cube_r12 = Head.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, -7.75F, -1.0F, -1.2208F, 0.1699F, -0.2119F)
      );
      PartDefinition cube_r13 = Head.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.5F, -8.0F, -3.0F, -1.7713F, -0.3452F, -0.0244F)
      );
      PartDefinition cube_r14 = Head.m_171599_(
         "cube_r14",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.5F, -5.0F, -6.75F, -1.8001F, -0.601F, 0.0381F)
      );
      PartDefinition cube_r15 = Head.m_171599_(
         "cube_r15",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.5F, -5.0F, -4.75F, -1.6779F, -0.6304F, -0.1728F)
      );
      PartDefinition cube_r16 = Head.m_171599_(
         "cube_r16",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.5F, -5.0F, -6.75F, -1.4056F, -0.5807F, -0.2766F)
      );
      PartDefinition cube_r17 = Head.m_171599_(
         "cube_r17",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.5F, -4.5F, 0.25F, -1.4056F, -0.5807F, -0.2766F)
      );
      PartDefinition cube_r18 = Head.m_171599_(
         "cube_r18",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.5F, -4.25F, 5.25F, -1.4056F, -0.5807F, -0.2766F)
      );
      PartDefinition cube_r19 = Head.m_171599_(
         "cube_r19",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.75F, -7.75F, -4.0F, -1.5095F, -0.3452F, -0.0244F)
      );
      PartDefinition cube_r20 = Head.m_171599_(
         "cube_r20",
         CubeListBuilder.m_171558_().m_171514_(42, 0).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.5F, -7.75F, -0.5F, -1.4469F, 0.2168F, -0.2349F)
      );
      PartDefinition cube_r21 = Head.m_171599_(
         "cube_r21",
         CubeListBuilder.m_171558_().m_171514_(42, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, -6.5F, -4.25F, -0.287F, -0.0309F, -0.0972F)
      );
      PartDefinition cube_r22 = Head.m_171599_(
         "cube_r22",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, -6.25F, -4.5F, -0.2741F, 0.0917F, 0.322F)
      );
      PartDefinition cube_r23 = Head.m_171599_(
         "cube_r23",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.5F, -8.0F, -2.5F, -1.5988F, 0.0881F, 0.1468F)
      );
      PartDefinition cube_r24 = Head.m_171599_(
         "cube_r24",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.5F, -4.25F, 2.75F, -1.601F, -0.3916F, 0.1608F)
      );
      PartDefinition cube_r25 = Head.m_171599_(
         "cube_r25",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.5F, -7.75F, 1.5F, -1.6661F, 0.3407F, -0.0967F)
      );
      PartDefinition cube_r26 = Head.m_171599_(
         "cube_r26",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.75F, -8.0F, 0.75F, -1.6271F, 0.337F, -0.174F)
      );
      PartDefinition cube_r27 = Head.m_171599_(
         "cube_r27",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.25F, -7.75F, 2.75F, -1.5847F, 0.8213F, -0.0421F)
      );
      PartDefinition cube_r28 = Head.m_171599_(
         "cube_r28",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.25F, -4.25F, 4.75F, -1.6545F, 0.6041F, -0.0156F)
      );
      PartDefinition cube_r29 = Head.m_171599_(
         "cube_r29",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.25F, -8.0F, -1.25F, -1.6541F, -0.1253F, 0.0717F)
      );
      PartDefinition cube_r30 = Head.m_171599_(
         "cube_r30",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.5F, -4.5F, -3.25F, -1.6618F, -0.4296F, 0.0993F)
      );
      PartDefinition cube_r31 = Head.m_171599_(
         "cube_r31",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.25F, -8.0F, 1.25F, -1.6159F, -0.1235F, 0.1157F)
      );
      PartDefinition cube_r32 = Head.m_171599_(
         "cube_r32",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.25F, -8.25F, 0.0F, -1.8957F, -0.436F, 0.6338F)
      );
      PartDefinition cube_r33 = Head.m_171599_(
         "cube_r33",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.25F, -8.25F, -3.0F, -0.9845F, -0.9808F, -0.7329F)
      );
      PartDefinition cube_r34 = Head.m_171599_(
         "cube_r34",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.75F, -5.25F, -6.0F, -0.9845F, -0.9808F, -0.7329F)
      );
      PartDefinition cube_r35 = Head.m_171599_(
         "cube_r35",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.25F, -4.75F, 4.0F, -0.9845F, -0.9808F, -0.7329F)
      );
      PartDefinition cube_r36 = Head.m_171599_(
         "cube_r36",
         CubeListBuilder.m_171558_().m_171514_(42, 8).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.75F, -4.5F, 5.0F, -0.9845F, -0.9808F, -0.7329F)
      );
      PartDefinition cube_r37 = Head.m_171599_(
         "cube_r37",
         CubeListBuilder.m_171558_().m_171514_(0, 43).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.5F, -5.75F, -4.25F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r38 = Head.m_171599_(
         "cube_r38",
         CubeListBuilder.m_171558_().m_171514_(8, 43).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.75F, -4.75F, 4.0F, 0.303F, -0.0393F, 0.1249F)
      );
      PartDefinition cube_r39 = Head.m_171599_(
         "cube_r39",
         CubeListBuilder.m_171558_().m_171514_(26, 41).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.25F, -6.25F, 4.25F, 0.3052F, -0.0131F, 0.0416F)
      );
      PartDefinition cube_r40 = Head.m_171599_(
         "cube_r40",
         CubeListBuilder.m_171558_().m_171514_(16, 45).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.25F, -5.5F, 4.5F, 0.3043F, -0.0262F, 0.0832F)
      );
      PartDefinition cube_r41 = Head.m_171599_(
         "cube_r41",
         CubeListBuilder.m_171558_().m_171514_(26, 41).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.75F, -6.5F, 4.25F, 0.3019F, 0.0471F, -0.1499F)
      );
      PartDefinition cube_r42 = Head.m_171599_(
         "cube_r42",
         CubeListBuilder.m_171558_().m_171514_(26, 41).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, -5.25F, 4.5F, 0.2969F, 0.0728F, -0.2335F)
      );
      PartDefinition cube_r43 = Head.m_171599_(
         "cube_r43",
         CubeListBuilder.m_171558_().m_171514_(24, 45).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.25F, -5.25F, -0.25F, -0.2618F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r44 = Head.m_171599_(
         "cube_r44",
         CubeListBuilder.m_171558_().m_171514_(32, 45).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, -6.5F, 1.75F, 0.0436F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r45 = Head.m_171599_(
         "cube_r45",
         CubeListBuilder.m_171558_().m_171514_(0, 47).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.25F, -6.25F, -1.75F, 0.1745F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r46 = Head.m_171599_(
         "cube_r46",
         CubeListBuilder.m_171558_().m_171514_(8, 47).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.5F, -5.5F, -3.25F, -0.0872F, 0.0038F, 0.2616F)
      );
      PartDefinition cube_r47 = Head.m_171599_(
         "cube_r47",
         CubeListBuilder.m_171558_().m_171514_(40, 47).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.25F, -5.25F, 3.25F, 0.0437F, 0.0038F, 0.2616F)
      );
      PartDefinition cube_r48 = Head.m_171599_(
         "cube_r48",
         CubeListBuilder.m_171558_().m_171514_(0, 30).m_171488_(-5.5F, 1.5F, 0.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.5F, -8.75F, -5.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r49 = Head.m_171599_(
         "cube_r49",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 0)
            .m_171488_(-6.5F, 0.5F, -1.0F, 7.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 15)
            .m_171488_(0.5F, 0.5F, -1.0F, 6.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, -6.0F, -6.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r50 = Head.m_171599_(
         "cube_r50",
         CubeListBuilder.m_171558_()
            .m_171514_(16, 49)
            .m_171488_(4.5F, 0.5F, 3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 41)
            .m_171488_(5.25F, 5.5F, 3.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(48, 47)
            .m_171488_(13.0F, 0.5F, 3.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-9.25F, -6.25F, -5.0F, -0.1309F, 0.0F, 0.0F)
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
