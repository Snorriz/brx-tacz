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

public class Modelrussian_belt<T extends Entity> extends EntityModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("mm_armor", "modelrussian_belt"), "main");
   public final ModelPart Body;
   public final ModelPart righthand;
   public final ModelPart lefthand;

   public Modelrussian_belt(ModelPart root) {
      this.Body = root.m_171324_("Body");
      this.righthand = root.m_171324_("righthand");
      this.lefthand = root.m_171324_("lefthand");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.m_171576_();
      PartDefinition Body = partdefinition.m_171599_(
         "Body",
         CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.25F)),
         PartPose.m_171419_(0.0F, 0.0F, 0.0F)
      );
      PartDefinition cube_r1 = Body.m_171599_(
         "cube_r1",
         CubeListBuilder.m_171558_().m_171514_(22, 14).m_171488_(-0.0038F, 0.0F, -0.4128F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 8.0F, -1.0F, -0.0076F, 0.0869F, -0.0876F)
      );
      PartDefinition cube_r2 = Body.m_171599_(
         "cube_r2",
         CubeListBuilder.m_171558_().m_171514_(24, 0).m_171488_(-0.0038F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(4.0F, 9.0F, -1.0F, -0.0076F, 0.0869F, -0.0876F)
      );
      PartDefinition cube_r3 = Body.m_171599_(
         "cube_r3",
         CubeListBuilder.m_171558_().m_171514_(6, 20).m_171488_(-1.0F, -3.0F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(5.0F, 11.0F, -1.0F, 0.0F, 0.0873F, 0.0F)
      );
      PartDefinition cube_r4 = Body.m_171599_(
         "cube_r4",
         CubeListBuilder.m_171558_().m_171514_(24, 0).m_171488_(-1.05F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 10.0F, -1.0F, -0.0076F, -0.0869F, 0.0876F)
      );
      PartDefinition cube_r5 = Body.m_171599_(
         "cube_r5",
         CubeListBuilder.m_171558_().m_171514_(20, 19).m_171488_(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 8.0F, -1.0F, -0.0076F, -0.0869F, 0.0876F)
      );
      PartDefinition cube_r6 = Body.m_171599_(
         "cube_r6",
         CubeListBuilder.m_171558_().m_171514_(6, 20).m_171488_(-1.0F, -3.0F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 11.0F, -1.0F, 0.0F, -0.0873F, 0.0F)
      );
      PartDefinition cube_r7 = Body.m_171599_(
         "cube_r7",
         CubeListBuilder.m_171558_().m_171514_(24, 0).m_171488_(-1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 9.0F, 1.0F, 0.0076F, 0.0869F, 0.0876F)
      );
      PartDefinition cube_r8 = Body.m_171599_(
         "cube_r8",
         CubeListBuilder.m_171558_().m_171514_(20, 19).m_171488_(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 8.0F, 1.0F, 0.0076F, 0.0869F, 0.0876F)
      );
      PartDefinition cube_r9 = Body.m_171599_(
         "cube_r9",
         CubeListBuilder.m_171558_().m_171514_(6, 20).m_171488_(-1.0F, -3.0F, -0.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-4.0F, 11.0F, 1.0F, 0.0F, 0.0873F, 0.0F)
      );
      PartDefinition cube_r10 = Body.m_171599_(
         "cube_r10",
         CubeListBuilder.m_171558_().m_171514_(12, 14).m_171488_(-1.1F, -3.8F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(3.0F, 12.0F, 2.0F, 0.0F, 0.5672F, 0.0F)
      );
      PartDefinition cube_r11 = Body.m_171599_(
         "cube_r11",
         CubeListBuilder.m_171558_().m_171514_(12, 19).m_171488_(-1.0F, -3.0F, -0.8F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-3.0F, 11.0F, 2.0F, 0.0457F, -0.3051F, -0.0138F)
      );
      PartDefinition cube_r12 = Body.m_171599_(
         "cube_r12",
         CubeListBuilder.m_171558_().m_171514_(0, 20).m_171488_(-0.5F, -3.05F, -1.7F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 11.0F, 3.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition cube_r13 = Body.m_171599_(
         "cube_r13",
         CubeListBuilder.m_171558_().m_171514_(20, 23).m_171488_(-0.5588F, -1.0F, -0.2F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(1.0F, 11.0F, 3.0F, 0.0F, 0.6981F, 0.0F)
      );
      PartDefinition cube_r14 = Body.m_171599_(
         "cube_r14",
         CubeListBuilder.m_171558_().m_171514_(20, 23).m_171488_(-1.5F, -1.0F, -0.15F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(-1.0F, 11.0F, 3.0F, 0.0F, -0.7418F, 0.0F)
      );
      PartDefinition cube_r15 = Body.m_171599_(
         "cube_r15",
         CubeListBuilder.m_171558_().m_171514_(0, 14).m_171488_(-2.0F, -2.0F, -0.8F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
         PartPose.m_171423_(0.0F, 10.0F, 2.0F, -0.0436F, 0.0F, 0.0F)
      );
      PartDefinition righthand = partdefinition.m_171599_("righthand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      PartDefinition lefthand = partdefinition.m_171599_("lefthand", CubeListBuilder.m_171558_(), PartPose.m_171419_(0.0F, 24.0F, 0.0F));
      return LayerDefinition.m_171565_(meshdefinition, 32, 32);
   }

   public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
      this.Body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.righthand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
      this.lefthand.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
   }

   public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.lefthand.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F) * limbSwingAmount;
      this.righthand.f_104203_ = Mth.m_14089_(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
   }
}
