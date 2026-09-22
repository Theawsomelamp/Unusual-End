package net.sashakyotoz.unusualend.client.rendering.custom;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.sashakyotoz.unusualend.UnusualEnd;

public class ChorusHelmetRenderer<t extends Entity> extends EntityModel<t> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(UnusualEnd.makeID("chorus_helmet"), "main");
    public final ModelPart head;

    public ChorusHelmetRenderer(ModelPart root) {
        this.head = root.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData head = partdefinition.addChild("head",
                ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)).uv(32, 5).cuboid(-3.0F, -9.5F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData cube_r1 = head.addChild("cube_r1",
                ModelPartBuilder.create().uv(28, 13).cuboid(3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)).uv(28, 13).cuboid(3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -8.0F, 0.0F, -2.9284F, 0.762F, -2.8378F));
        ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(28, 13).mirrored().cuboid(-7.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false).uv(28, 13).mirrored()
                .cuboid(-7.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -8.0F, 0.0F, 0.2132F, 0.762F, 0.3038F));
        ModelPartData cube_r3 = head.addChild("cube_r3", ModelPartBuilder.create().uv(28, 13).mirrored().cuboid(-7.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false).uv(28, 13).mirrored()
                .cuboid(-7.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -8.0F, 0.0F, 2.9284F, 0.762F, 2.8378F));
        ModelPartData cube_r4 = head.addChild("cube_r4",
                ModelPartBuilder.create().uv(28, 13).cuboid(3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)).uv(28, 13).cuboid(3.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -8.0F, 0.0F, -0.2132F, 0.762F, -0.3038F));
        return TexturedModelData.of(meshdefinition, 64, 32);
    }

    @Override
    public void setAngles(t entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.yaw = headYaw / (180F / (float) Math.PI);
        this.head.pitch = headPitch / (180F / (float) Math.PI);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        head.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
