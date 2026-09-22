package net.sashakyotoz.unusualend.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.sashakyotoz.unusualend.client.entity.PhantomArrowRenderer;
import net.sashakyotoz.unusualend.client.particles.ModParticles;
import net.sashakyotoz.unusualend.client.rendering.HatRenderer;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.entity.ModEntities;
import net.sashakyotoz.unusualend.common.items.ModItems;

public class UnusualEndClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        for (Block block : ModRegistry.BLOCK_CUTOUT)
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        for (Block block : ModRegistry.BLOCK_TRANSLUCENT)
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());

        ArmorRenderer.register(new HatRenderer(), ModItems.CHORUS_HELMET,
                ModItems.SPIRIT_SMILE_HELMET, ModItems.SPIRIT_GRIM_HELMET, ModItems.SPIRIT_MALICE_HELMET, ModItems.SPIRIT_MANIA_HELMET, ModItems.SPIRIT_TWIST_HELMET, ModItems.SPIRIT_VICE_HELMET);

        ModParticles.register();

        EntityRendererRegistry.register(ModEntities.WANDERING_PEARL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PHANTOM_ARROW, PhantomArrowRenderer::new);
    }
}