package net.sashakyotoz.unusualend.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        for (TagKey<Block> tag : ModRegistry.BLOCK_TAGS.keySet()) {
            FabricTagProvider<Block>.FabricTagBuilder builder = getOrCreateTagBuilder(tag);
            for (Block block : ModRegistry.BLOCK_TAGS.get(tag)) {
                builder.add(block);
            }
        }

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("endstone_bricks"))).add(
                Blocks.END_STONE_BRICKS,
                ModBlocks.WEAK_END_STONE_BRICKS,
                ModBlocks.END_STONE_TILES
        );

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils"))).add(
                Blocks.END_STONE,
                ModBlocks.INFESTED_END_STONE,
                ModBlocks.RAW_PURPUR_BLOCK,
                ModBlocks.ROOTED_RAW_PURPUR,
                ModBlocks.WARPED_END_STONE
        );
    }
}