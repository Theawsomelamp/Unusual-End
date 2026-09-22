package net.sashakyotoz.unusualend.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.entry.ItemEntry;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;
import net.sashakyotoz.unusualend.common.items.ModItems;

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
            else if (ModRegistry.BLOCK_MODELS.get(ModRegistry.Models.DOOR).contains(entry.getKey()))
                addDrop(entry.getKey(), doorDrops(entry.getKey()));
            else
                addDrop(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Block, ItemConvertible> entry : ModRegistry.BLOCK_SHEARS_DROPS.entrySet())
            addDrop(entry.getKey(), dropsWithShears(entry.getValue()));


        addDrop(ModBlocks.CRYSTAL_WALL_TORCH, drops(ModBlocks.CRYSTAL_TORCH));
        addDrop(ModBlocks.WARPED_END_STONE, drops(ModBlocks.WARPED_END_STONE, Blocks.END_STONE));
        addDrop(ModBlocks.WARPED_END_STONE, drops(ModBlocks.GLOOPY_ENDSTONE, Blocks.END_STONE));
        addDrop(ModBlocks.FLOWER_POT_CHORUS_FUNGUS, pottedPlantDrops(ModBlocks.CHORUS_FUNGUS));
        addDrop(ModBlocks.FLOWER_POT_CANE, pottedPlantDrops(ModBlocks.CHORUS_CANE));
        addDrop(ModBlocks.CRYSTAL_FLOWER, dropsWithSilkTouchOrShears(ModBlocks.CRYSTAL_FLOWER, addSurvivesExplosionCondition(ModItems.SHINY_CRYSTAL, ItemEntry.builder(ModItems.SHINY_CRYSTAL))));
        addDrop(ModBlocks.FLOWERING_GLOOPY_PILE, doorDrops(ModBlocks.FLOWERING_GLOOPY_PILE));
    }
}