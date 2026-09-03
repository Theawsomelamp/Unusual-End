package net.sashakyotoz.unusualend.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;

import java.util.Map;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (Map.Entry<Block, ItemConvertible> entry : ModRegistry.BLOCK_DROPS.entrySet()) {
            if (ModRegistry.BLOCK_MODELS.get(ModRegistry.Models.SLAB).contains(entry.getKey()))
                addDrop(entry.getKey(), slabDrops(entry.getKey()));
            //else if (ModRegistry.BLOCK_MODELS.get(ModRegistry.Models.DOOR).contains(entry.getKey()))
            //    addDrop(entry.getKey(), doorDrops(entry.getKey()));
            else
                addDrop(entry.getKey(), entry.getValue());
        }

        addDrop(ModBlocks.WARPED_END_STONE, drops(ModBlocks.WARPED_END_STONE, Blocks.END_STONE));
        addDrop(ModBlocks.CHORUS_ROOTS, dropsWithShears(ModBlocks.CHORUS_ROOTS));
        addDrop(ModBlocks.FLOWER_POT_CHORUS_FUNGUS, pottedPlantDrops(ModBlocks.CHORUS_FUNGUS));
        addDrop(ModBlocks.ENDSTONE_SPROUTS, dropsWithShears(ModBlocks.ENDSTONE_SPROUTS));
        addDrop(ModBlocks.PURPUR_GRASS, dropsWithShears(ModBlocks.PURPUR_GRASS));
        addDrop(ModBlocks.FLOWERING_PURPUR_GRASS, dropsWithShears(ModBlocks.FLOWERING_PURPUR_GRASS));
    }
}