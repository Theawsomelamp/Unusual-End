package net.sashakyotoz.unusualend.client.rendering;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.client.rendering.custom.ChorusHelmetRenderer;
import net.sashakyotoz.unusualend.client.rendering.custom.SpiritMaskRenderer;
import net.sashakyotoz.unusualend.common.items.ModItems;
import net.sashakyotoz.unusualend.common.items.custom.MaskItem;

public class HatRenderer implements ArmorRenderer {
    private static final ChorusHelmetRenderer<?> chorusHelmet = new ChorusHelmetRenderer<>(ChorusHelmetRenderer.getTexturedModelData().createModel());
    private static final SpiritMaskRenderer<?> spiritMask = new SpiritMaskRenderer<>(SpiritMaskRenderer.getTexturedModelData().createModel());

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity,
                       EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {

        if (!stack.isEmpty() && stack.isOf(ModItems.CHORUS_HELMET)) {
            matrices.push();
            contextModel.getHead().rotate(matrices);
            matrices.scale(1.19F, 1.19F, 1.19F);
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, chorusHelmet.getLayer(UnusualEnd.makeID("textures/models/armor/chorus__layer_1.png")), false, stack.hasGlint());
            chorusHelmet.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
        }

        if (!stack.isEmpty() && stack.getItem() instanceof MaskItem mask) {
            matrices.push();
            contextModel.getHead().rotate(matrices);
            matrices.scale(1.19F, 1.19F, 1.19F);
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, spiritMask.getLayer(UnusualEnd.makeID("textures/entities/spirit_mask_" + mask.getMaskType() + ".png")), false, stack.hasGlint());
            spiritMask.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
        }
    }
}
