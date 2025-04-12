package nl.jdries.infmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import nl.jdries.infmod.InfMod;
import nl.jdries.infmod.entity.custom.piglinkingentity;

public class piglinkingModel<T extends piglinkingentity> extends HierarchicalModel<T> {
// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports



        // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("infmod", "piglinking"), "main");
    private final ModelPart body;
    private final ModelPart head;

    public piglinkingModel(ModelPart root) {
        this.body = root.getChild("body");

        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, -54.0F, -13.0F, 40.0F, 38.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(62, 104).addBox(-8.0F, -2.0F, -12.0F, 17.0F, 18.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -16.0F, 1.0F));

        PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(62, 64).addBox(-10.0F, -2.0F, -13.0F, 17.0F, 18.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -16.0F, 2.0F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition arm_right = arms.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(0, 121).addBox(-2.0F, -1.0F, -7.0F, 16.0F, 31.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(24.0F, -49.0F, 0.0F));

        PartDefinition arm_right_r1 = arm_right.addOrReplaceChild("arm_right_r1", CubeListBuilder.create().texOffs(140, 60).addBox(0.0F, -26.0F, -14.0F, 16.0F, 18.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 29.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition staff = arm_right.addOrReplaceChild("staff", CubeListBuilder.create(), PartPose.offset(4.75F, 7.0F, 20.0F));

        PartDefinition cube_r1 = staff.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 13).addBox(-0.55F, -1.3F, -0.25F, 0.5F, 2.25F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r2 = staff.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(19, 14).addBox(-0.75F, -0.25F, -0.25F, 1.75F, 0.5F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r3 = staff.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(19, 13).addBox(-1.0F, -0.25F, -0.25F, 1.75F, 0.5F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r4 = staff.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(20, 13).addBox(0.05F, -1.3F, -0.25F, 0.5F, 2.25F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition cube_r5 = staff.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(5, 5).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -6.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition hilt = staff.addOrReplaceChild("hilt", CubeListBuilder.create().texOffs(0, 5).addBox(-8.75F, -11.0F, 7.25F, 1.5F, 11.0F, 1.5F, new CubeDeformation(0.0F))
                .texOffs(10, 14).addBox(-9.0F, -2.0F, 7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 3).addBox(-9.0F, -10.0F, 7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(9.25F, 10.0F, -8.0F));

        PartDefinition chappe = hilt.addOrReplaceChild("chappe", CubeListBuilder.create().texOffs(3, 16).addBox(-9.0F, -12.0F, 7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition clinger_base = staff.addOrReplaceChild("clinger_base", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, 2.0F, 0.0F));

        PartDefinition staff_tip_east = staff.addOrReplaceChild("staff_tip_east", CubeListBuilder.create(), PartPose.offset(1.25F, -3.0F, 0.0F));

        PartDefinition cube_r6 = staff_tip_east.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(19, 7).addBox(-1.0F, -6.25F, -0.9F, 1.0F, 2.0F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r7 = staff_tip_east.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(9, 6).addBox(-4.0F, -3.0F, -1.0F, 1.0F, 3.6F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r8 = staff_tip_east.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(17, 18).addBox(-2.0F, -3.4F, -0.9F, 1.0F, 4.0F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition staff_tip_west = staff.addOrReplaceChild("staff_tip_west", CubeListBuilder.create(), PartPose.offset(1.25F, -3.0F, 0.0F));

        PartDefinition cube_r9 = staff_tip_west.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(9, 7).addBox(0.0F, -6.25F, -0.9F, 1.0F, 2.0F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r10 = staff_tip_west.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(5, 9).addBox(3.0F, -3.0F, -1.0F, 1.0F, 3.6F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r11 = staff_tip_west.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(5, 10).addBox(1.0F, -3.4F, -0.9F, 1.0F, 4.0F, 1.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition arm_left = arms.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(0, 64).addBox(-15.0F, 0.0F, -7.0F, 16.0F, 42.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(-19.0F, -50.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(132, 31).addBox(-9.0F, -12.0F, -8.0F, 18.0F, 13.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(132, 0).addBox(-10.0F, -23.0F, -9.0F, 20.0F, 13.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -54.0F, 0.0F));

        PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(140, 93).addBox(-5.0F, -6.0F, -1.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 9.0F, -0.2182F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(piglinkingentity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(piglinkingAnimations.ANIM_PIGLINKING_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, piglinkingAnimations.ANIM_PIGLINKING_IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}