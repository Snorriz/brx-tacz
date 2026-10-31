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

public class Modelcsou_chestplate_sniper<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelcsou_chestplate_sniper"), "main");
   public final ModelPart Body;
   public final ModelPart RightArm;
   public final ModelPart LeftArm;

   public Modelcsou_chestplate_sniper(ModelPart root) {
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
            .m_171514_(24, 0)
            .m_171488_(-4.0F, 0.0F, 1.8F, 8.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(36, 54)
            .m_171488_(1.45F, 8.0F, -4.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 34)
            .m_171488_(-2.05F, 7.0F, -4.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(44, 32)
            .m_171488_(-2.5F, 9.5F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(48, 14)
            .m_171488_(-2.5F, 7.75F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(48, 16)
            .m_171488_(-2.5F, 6.0F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(48, 18)
            .m_171488_(-2.5F, 1.0F, 1.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(20, 48)
            .m_171488_(-2.5F, 1.0F, -2.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(48, 20)
            .m_171488_(-2.5F, 9.0F, -2.9F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 0)
            .m_171488_(-3.5F, 5.0F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 0)
            .m_171488_(-3.5F, 7.0F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 2)
            .m_171488_(-3.5F, 3.0F, -2.9F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 32)
            .m_171488_(-4.0F, 0.0F, -2.8F, 8.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 34)
            .m_171488_(3.3F, 6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 41)
            .m_171488_(3.4F, 6.2F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 40)
            .m_171488_(3.4F, 6.2F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 43)
            .m_171488_(3.4F, 8.0F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(28, 43)
            .m_171488_(3.4F, 8.0F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(44, 50)
            .m_171488_(3.4F, 9.8F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 44)
            .m_171488_(3.4F, 9.8F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 52)
            .m_171488_(-4.4F, 6.2F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(58, 8)
            .m_171488_(-4.4F, 8.0F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(38, 45)
            .m_171488_(-4.4F, 8.0F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(58, 10)
            .m_171488_(-4.4F, 9.8F, 1.01F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(48, 9)
            .m_171488_(-4.4F, 9.8F, -2.01F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(32, 34)
            .m_171488_(-4.3F, 6.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 4)
            .m_171488_(-4.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(42, 4)
            .m_171488_(2.0F, -0.56F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition Body_r1 = Body.m_171599_(
         "Body_r1",
         CubeListBuilder.m_171558_()
            .m_171514_(54, 56)
            .m_171488_(2.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(54, 56)
            .m_171488_(-4.0F, -1.6F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r2 = Body.m_171599_(
         "Body_r2",
         CubeListBuilder.m_171558_()
            .m_171514_(20, 50)
            .m_171488_(2.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .m_171514_(54, 56)
            .m_171488_(8.0F, -1.52F, -2.3F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-6.0F, 2.0F, 3.0F, -0.6109F, 0.0F, 0.0F)
      );
      PartDefinition Body_r3 = Body.m_171599_(
         "Body_r3",
         CubeListBuilder.m_171558_().m_171514_(28, 50).m_171488_(-0.2F, -1.0F, -0.3F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 1.0F, -3.0F, 0.0F, -0.8727F, 0.0F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(32, 27).m_171488_(-2.2F, -1.65F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(8, 56).m_171488_(-1.3F, -3.1F, -1.3F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 11.0F, -2.75F, -0.0436F, -0.0873F, 0.0F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(36, 54).m_171488_(-1.3F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 11.0F, -2.75F, 0.0F, -0.0873F, 0.0F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(8, 56).m_171488_(-1.3F, -3.1F, -1.3F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.75F, 11.0F, -3.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(10, 48).m_171488_(-1.3F, -3.1F, -1.3F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.75F, 10.0F, -3.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r6 = Body.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_().m_171514_(58, 0).m_171488_(-0.8F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 10.75F, -2.5F, -0.0436F, 0.6109F, 0.0F)
      );
      PartDefinition cube_r7 = Body.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(42, 34).m_171488_(-1.3F, -4.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.5F, 11.0F, -2.5F, 0.0F, 0.6109F, 0.0F)
      );
      PartDefinition cube_r8 = Body.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_().m_171514_(10, 48).m_171488_(-1.3F, -3.1F, -1.3F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.5F, 10.0F, -2.5F, -0.0436F, 0.6109F, 0.0F)
      );
      PartDefinition cube_r9 = Body.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_().m_171514_(58, 0).m_171488_(-0.8F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 10.75F, -3.0F, -0.055F, 0.6538F, -0.0335F)
      );
      PartDefinition cube_r10 = Body.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_().m_171514_(58, 0).m_171488_(-0.8F, -2.8F, -1.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.25F, 10.75F, -3.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r11 = Body.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_().m_171514_(32, 27).m_171488_(-2.0F, -1.85F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.2618F)
      );
      PartDefinition cube_r12 = Body.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_().m_171514_(48, 45).m_171488_(-2.0F, -2.2F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 1.0F, -2.0F, 0.1309F, 0.0F, 0.0F)
      );
      PartDefinition cube_r13 = Body.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_().m_171514_(56, 31).m_171488_(-0.2F, -2.1F, -1.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.0F, 3.0F, -0.0886F, -0.1739F, 0.0154F)
      );
      PartDefinition cube_r14 = Body.m_171599_(
         "cube_r14",
         CubeListBuilder.m_171558_().m_171514_(52, 42).m_171488_(-1.8F, -2.1F, -1.1F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 1.0F, 3.0F, -0.0886F, 0.1739F, -0.0154F)
      );
      PartDefinition Body_r4 = Body.m_171599_(
         "Body_r4",
         CubeListBuilder.m_171558_().m_171514_(28, 50).m_171488_(7.7F, -1.0F, -0.7F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-9.0F, 1.0F, 4.0F, 0.0F, 0.9163F, 0.0F)
      );
      PartDefinition cube_r15 = Body.m_171599_(
         "cube_r15",
         CubeListBuilder.m_171558_()
            .m_171514_(48, 22)
            .m_171488_(-1.0F, -1.5F, -0.9F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(0, 57)
            .m_171488_(-1.0F, -2.5F, -0.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.5F, 9.75F, 3.0F, -3.0623F, -0.5638F, 3.1059F)
      );
      PartDefinition cube_r16 = Body.m_171599_(
         "cube_r16",
         CubeListBuilder.m_171558_().m_171514_(32, 57).m_171488_(0.1F, -4.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.5F, 9.75F, 3.0F, -2.9233F, -1.2563F, 2.9404F)
      );
      PartDefinition cube_r17 = Body.m_171599_(
         "cube_r17",
         CubeListBuilder.m_171558_().m_171514_(42, 9).m_171488_(-1.1F, -2.0F, -0.7F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 6.0F, -3.0F, 0.0438F, 0.0872F, 0.0019F)
      );
      PartDefinition cube_r18 = Body.m_171599_(
         "cube_r18",
         CubeListBuilder.m_171558_().m_171514_(32, 48).m_171488_(-0.1F, -2.0F, 5.3F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.5F, 2.0F, -2.75F, -0.0416F, -0.0379F, -0.0417F)
      );
      PartDefinition cube_r19 = Body.m_171599_(
         "cube_r19",
         CubeListBuilder.m_171558_().m_171514_(32, 12).m_171488_(-0.1F, -3.0F, -0.7F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.5F, 2.0F, -2.75F, -0.0432F, -0.0361F, 0.0019F)
      );
      PartDefinition cube_r20 = Body.m_171599_(
         "cube_r20",
         CubeListBuilder.m_171558_().m_171514_(56, 48).m_171488_(-1.1F, -2.0F, -0.7F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.75F, 2.0F, -2.75F, -0.0399F, -0.0397F, -0.0853F)
      );
      PartDefinition cube_r21 = Body.m_171599_(
         "cube_r21",
         CubeListBuilder.m_171558_().m_171514_(42, 9).m_171488_(-1.1F, -2.0F, -0.7F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 6.0F, -3.0F, 0.0438F, -0.0437F, 0.0019F)
      );
      PartDefinition Body_r5 = Body.m_171599_(
         "Body_r5",
         CubeListBuilder.m_171558_().m_171514_(32, 20).m_171488_(-3.0F, -17.0F, -3.0F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 24.0F, 6.0F, 0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r22 = Body.m_171599_(
         "cube_r22",
         CubeListBuilder.m_171558_().m_171514_(18, 41).m_171488_(-1.0F, -2.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, 9.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
      );
      PartDefinition cube_r23 = Body.m_171599_(
         "cube_r23",
         CubeListBuilder.m_171558_().m_171514_(18, 32).m_171488_(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-5.0F, 9.0F, 0.0F, 0.0F, 0.0873F, -0.0873F)
      );
      PartDefinition RightArm = partdefinition.m_171599_(
         "RightArm",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 16)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 16)
            .m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(-5.0F, 2.0F, 0.0F)
      );
      PartDefinition cube_r24 = RightArm.m_171599_(
         "cube_r24",
         CubeListBuilder.m_171558_().m_171514_(24, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.25F, 6.25F, 1.75F, 0.3043F, 0.0262F, -0.0832F)
      );
      PartDefinition cube_r25 = RightArm.m_171599_(
         "cube_r25",
         CubeListBuilder.m_171558_().m_171514_(54, 27).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.75F, 5.25F, 2.0F, 0.303F, -0.0393F, 0.1249F)
      );
      PartDefinition cube_r26 = RightArm.m_171599_(
         "cube_r26",
         CubeListBuilder.m_171558_().m_171514_(24, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.25F, 3.75F, 2.0F, 0.2986F, 0.0651F, -0.2084F)
      );
      PartDefinition cube_r27 = RightArm.m_171599_(
         "cube_r27",
         CubeListBuilder.m_171558_().m_171514_(16, 54).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.25F, 2.5F, 2.0F, 0.3052F, 0.0131F, -0.0416F)
      );
      PartDefinition cube_r28 = RightArm.m_171599_(
         "cube_r28",
         CubeListBuilder.m_171558_().m_171514_(24, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.25F, 1.25F, 2.0F, 0.3019F, 0.0471F, -0.1499F)
      );
      PartDefinition cube_r29 = RightArm.m_171599_(
         "cube_r29",
         CubeListBuilder.m_171558_().m_171514_(24, 54).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.75F, 0.5F, 1.75F, 0.3023F, -0.0445F, 0.1416F)
      );
      PartDefinition cube_r30 = RightArm.m_171599_(
         "cube_r30",
         CubeListBuilder.m_171558_().m_171514_(16, 54).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.25F, 6.75F, 2.0F, 0.3052F, -0.0131F, 0.0416F)
      );
      PartDefinition cube_r31 = RightArm.m_171599_(
         "cube_r31",
         CubeListBuilder.m_171558_().m_171514_(36, 50).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.75F, 0.75F, -2.0F, -0.287F, -0.0309F, -0.0972F)
      );
      PartDefinition cube_r32 = RightArm.m_171599_(
         "cube_r32",
         CubeListBuilder.m_171558_().m_171514_(20, 50).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.0F, 1.75F, -1.75F, -0.2741F, 0.0917F, 0.322F)
      );
      PartDefinition cube_r33 = RightArm.m_171599_(
         "cube_r33",
         CubeListBuilder.m_171558_().m_171514_(0, 49).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.25F, 3.0F, -1.75F, -0.2808F, -0.0677F, -0.223F)
      );
      PartDefinition cube_r34 = RightArm.m_171599_(
         "cube_r34",
         CubeListBuilder.m_171558_().m_171514_(48, 48).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.75F, 4.0F, -2.0F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r35 = RightArm.m_171599_(
         "cube_r35",
         CubeListBuilder.m_171558_().m_171514_(10, 44).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.75F, 6.0F, -2.0F, -0.2881F, -0.0186F, -0.0554F)
      );
      PartDefinition cube_r36 = RightArm.m_171599_(
         "cube_r36",
         CubeListBuilder.m_171558_().m_171514_(24, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.75F, 7.0F, -1.75F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r37 = RightArm.m_171599_(
         "cube_r37",
         CubeListBuilder.m_171558_().m_171514_(54, 4).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.75F, 0.5F, -0.25F, -0.2618F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r38 = RightArm.m_171599_(
         "cube_r38",
         CubeListBuilder.m_171558_().m_171514_(0, 53).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.75F, 1.75F, 0.75F, 0.0436F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r39 = RightArm.m_171599_(
         "cube_r39",
         CubeListBuilder.m_171558_().m_171514_(52, 52).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.75F, 2.5F, -1.0F, -0.1744F, 0.0076F, 0.2611F)
      );
      PartDefinition cube_r40 = RightArm.m_171599_(
         "cube_r40",
         CubeListBuilder.m_171558_().m_171514_(44, 52).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.75F, 4.5F, 0.5F, 0.1745F, -0.0019F, 0.2618F)
      );
      PartDefinition cube_r41 = RightArm.m_171599_(
         "cube_r41",
         CubeListBuilder.m_171558_().m_171514_(52, 38).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.75F, 6.75F, 0.75F, 0.0437F, 0.0038F, 0.2616F)
      );
      PartDefinition cube_r42 = RightArm.m_171599_(
         "cube_r42",
         CubeListBuilder.m_171558_().m_171514_(20, 50).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-2.5F, 6.25F, -1.0F, -0.0872F, 0.0038F, 0.2616F)
      );
      PartDefinition LeftArm = partdefinition.m_171599_(
         "LeftArm",
         CubeListBuilder.m_171558_()
            .m_171514_(0, 16)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
            .m_171514_(16, 16)
            .m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(5.0F, 2.0F, 0.0F)
      );
      PartDefinition cube_r43 = LeftArm.m_171599_(
         "cube_r43",
         CubeListBuilder.m_171558_().m_171514_(10, 44).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.75F, 6.25F, 1.75F, 0.3043F, 0.0262F, -0.0832F)
      );
      PartDefinition cube_r44 = LeftArm.m_171599_(
         "cube_r44",
         CubeListBuilder.m_171558_().m_171514_(24, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.25F, 0.5F, 1.75F, 0.3023F, -0.0445F, 0.1416F)
      );
      PartDefinition cube_r45 = LeftArm.m_171599_(
         "cube_r45",
         CubeListBuilder.m_171558_().m_171514_(24, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.75F, 1.25F, 2.0F, 0.3019F, 0.0471F, -0.1499F)
      );
      PartDefinition cube_r46 = LeftArm.m_171599_(
         "cube_r46",
         CubeListBuilder.m_171558_().m_171514_(52, 34).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.75F, 2.5F, 2.0F, 0.3052F, 0.0131F, -0.0416F)
      );
      PartDefinition cube_r47 = LeftArm.m_171599_(
         "cube_r47",
         CubeListBuilder.m_171558_().m_171514_(24, 12).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.75F, 3.75F, 2.0F, 0.2986F, 0.0651F, -0.2084F)
      );
      PartDefinition cube_r48 = LeftArm.m_171599_(
         "cube_r48",
         CubeListBuilder.m_171558_().m_171514_(8, 52).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.25F, 5.25F, 2.0F, 0.303F, -0.0393F, 0.1249F)
      );
      PartDefinition cube_r49 = LeftArm.m_171599_(
         "cube_r49",
         CubeListBuilder.m_171558_().m_171514_(36, 50).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 0.75F, -2.0F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r50 = LeftArm.m_171599_(
         "cube_r50",
         CubeListBuilder.m_171558_().m_171514_(20, 50).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-0.25F, 1.75F, -1.75F, -0.2741F, 0.0917F, 0.322F)
      );
      PartDefinition cube_r51 = LeftArm.m_171599_(
         "cube_r51",
         CubeListBuilder.m_171558_().m_171514_(0, 49).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.0F, 3.0F, -1.75F, -0.287F, -0.0309F, -0.0972F)
      );
      PartDefinition cube_r52 = LeftArm.m_171599_(
         "cube_r52",
         CubeListBuilder.m_171558_().m_171514_(48, 48).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 4.0F, -2.0F, -0.287F, 0.031F, 0.112F)
      );
      PartDefinition cube_r53 = LeftArm.m_171599_(
         "cube_r53",
         CubeListBuilder.m_171558_().m_171514_(10, 44).m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.5F, 6.0F, -2.0F, -0.2834F, 0.0556F, 0.1958F)
      );
      PartDefinition cube_r54 = LeftArm.m_171599_(
         "cube_r54",
         CubeListBuilder.m_171558_()
            .m_171514_(24, 12)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .m_171514_(24, 12)
            .m_171488_(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.5F, 7.25F, -1.75F, -0.2834F, -0.0555F, -0.181F)
      );
      PartDefinition cube_r55 = LeftArm.m_171599_(
         "cube_r55",
         CubeListBuilder.m_171558_().m_171514_(48, 56).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 0.5F, 0.5F, 0.0436F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r56 = LeftArm.m_171599_(
         "cube_r56",
         CubeListBuilder.m_171558_().m_171514_(42, 56).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 1.5F, -0.75F, -0.0873F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r57 = LeftArm.m_171599_(
         "cube_r57",
         CubeListBuilder.m_171558_().m_171514_(42, 56).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 3.0F, -0.25F, -0.3054F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r58 = LeftArm.m_171599_(
         "cube_r58",
         CubeListBuilder.m_171558_().m_171514_(42, 56).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(2.75F, 6.75F, -0.75F, 0.0873F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r59 = LeftArm.m_171599_(
         "cube_r59",
         CubeListBuilder.m_171558_().m_171514_(42, 56).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 5.75F, 0.75F, -0.0436F, 0.0F, -0.3491F)
      );
      PartDefinition cube_r60 = LeftArm.m_171599_(
         "cube_r60",
         CubeListBuilder.m_171558_().m_171514_(56, 22).m_171488_(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.25F, 4.5F, -0.5F, 0.1745F, 0.0F, -0.3491F)
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
