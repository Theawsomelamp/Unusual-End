package net.sashakyotoz.unusualend.client.rendering.custom;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.sashakyotoz.unusualend.UnusualEnd;

public class SpiritMaskRenderer<t extends Entity> extends EntityModel<t> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(UnusualEnd.makeID("spirit_mask"), "main");
    public final ModelPart mask;

    public SpiritMaskRenderer(ModelPart root) {
        this.mask = root.getChild("mask");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData mask = partdefinition.addChild("mask", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, -1.0F));
        ModelPartData cube_r1 = mask.addChild("cube_r1", ModelPartBuilder.create().uv(1, 1).cuboid(-9.0F, -11.0F, -3.8F, 8.0F, 10.0F, 7.0F, new Dilation(0.1F)), ModelTransform.of(5.0F, 3.25F, 1.5F, 0.0436F, 0.0F, 0.0F));
        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    @Override
    public void setAngles(t entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.mask.yaw = headYaw / (180F / (float) Math.PI);
        this.mask.pitch = headPitch / (180F / (float) Math.PI);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        mask.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
