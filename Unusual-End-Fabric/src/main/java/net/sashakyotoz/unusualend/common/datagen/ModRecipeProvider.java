package net.sashakyotoz.unusualend.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;
import net.sashakyotoz.unusualend.common.items.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    public static void offerMultiItemShapelessRecipe(
            Consumer<RecipeJsonProvider> exporter, ItemConvertible output, List<ItemConvertible> inputs, @Nullable String group, int outputCount
    ) {
        ShapelessRecipeJsonBuilder recipe = ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount);

        for (ItemConvertible item : inputs) {
            recipe.input(item).criterion(hasItem(item), conditionsFromItem(item));
        }
        recipe.group(group).offerTo(exporter);
    }

    public static void offer2x2WithCount(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible output, int count, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(category, output, count)
                .input('#', input)
                .pattern("##")
                .pattern("##")
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerPillarRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(category, output, 2)
                .input('#', input)
                .pattern("#")
                .pattern("#")
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    private static void offerTilesRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible result, int count,
                                         ItemConvertible a, ItemConvertible b) {
        ShapedRecipeJsonBuilder.create(category, result, count)
                .pattern("AB").pattern("BA")
                .input('A', a).input('B', b)
                .criterion(hasItem(a), conditionsFromItem(a))
                .criterion(hasItem(b), conditionsFromItem(b))
                .group(Registries.ITEM.getId(result.asItem()).getPath())
                .offerTo(exporter, Registries.ITEM.getId(result.asItem()));
    }

    public static void offerSmithingUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, Item template, Item tool, Item ingot, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template), Ingredient.ofItems(tool), Ingredient.ofItems(ingot), category, result
                )
                .criterion(hasItem(ingot), conditionsFromItem(ingot))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        for (Block parent : ModRegistry.BLOCK_SETS.keySet()) {
            for (Map.Entry<ModRegistry.Models, Block> entry : ModRegistry.BLOCK_SETS.get(parent).entrySet()) {
                if (entry.getKey() == ModRegistry.Models.STAIRS)
                    createStairsRecipe(entry.getValue(), Ingredient.ofItems(parent))
                            .criterion(hasItem(parent), conditionsFromItem(parent))
                            .offerTo(exporter);

                if (entry.getKey() == ModRegistry.Models.SLAB)
                    offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, entry.getValue(), parent);

                if (entry.getKey() == ModRegistry.Models.PRESSURE_PLATE)
                    offerPressurePlateRecipe(exporter, entry.getValue(), parent);

                if (entry.getKey() == ModRegistry.Models.BUTTON)
                    offerSingleOutputShapelessRecipe(exporter, entry.getValue(), parent, "mod_buttons");

                if (entry.getKey() == ModRegistry.Models.FENCE)
                    createFenceRecipe(entry.getValue(), Ingredient.ofItems(parent))
                            .criterion(hasItem(parent), conditionsFromItem(parent))
                            .offerTo(exporter);

                if (entry.getKey() == ModRegistry.Models.WALL)
                    offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, entry.getValue(), parent);

                if (entry.getKey() == ModRegistry.Models.FENCE_GATE)
                    createFenceGateRecipe(entry.getValue(), Ingredient.ofItems(parent))
                            .criterion(hasItem(parent), conditionsFromItem(parent))
                            .offerTo(exporter);

                if (entry.getKey() == ModRegistry.Models.TRAPDOOR)
                    createTrapdoorRecipe(entry.getValue(), Ingredient.ofItems(parent))
                            .criterion(hasItem(parent), conditionsFromItem(parent))
                            .offerTo(exporter);

                if (entry.getKey() == ModRegistry.Models.DOOR)
                    createDoorRecipe(entry.getValue(), Ingredient.ofItems(parent))
                            .criterion(hasItem(parent), conditionsFromItem(parent))
                            .offerTo(exporter);

                if (entry.getKey() == ModRegistry.Models.SIGN)
                    createSignRecipe(entry.getValue(), Ingredient.ofItems(parent))
                            .criterion(hasItem(parent), conditionsFromItem(parent))
                            .offerTo(exporter);

                if (entry.getKey() == ModRegistry.Models.PANE)
                    ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, entry.getValue(), 16)
                            .input('#', parent)
                            .pattern("###").pattern("###")
                            .group(Registries.ITEM.getId(entry.getValue().asItem()).getPath())
                            .criterion(hasItem(parent), conditionsFromItem(parent))
                            .offerTo(exporter);
            }
        }

        offerReversibleCompactingRecipes(exporter,RecipeCategory.MISC, ModItems.PEARLESCENT_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEARLESCENT_BLOCK);

        offerShapelessRecipe(exporter, Items.BLACK_DYE, ModItems.SPECTRAL_SCRAP, "black_dye", 1);
        offerShapelessRecipe(exporter, Items.PHANTOM_MEMBRANE, ModBlocks.PHANTOM_MEMBRANE_BLOCK, null, 2);

        offerMultiItemShapelessRecipe(exporter, ModBlocks.ROOTED_RAW_PURPUR, List.of(ModBlocks.RAW_PURPUR_BLOCK, ModBlocks.CHORUS_ROOTS), null, 1);

        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR, ModBlocks.RAW_PURPUR_BLOCK);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS, ModBlocks.POLISHED_PURPUR);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE, ModBlocks.GLOOPSLATE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS, ModBlocks.POLISHED_GLOOPSLATE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES, Blocks.END_STONE_BRICKS);

        offerPillarRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR, ModBlocks.END_STONE_TILES);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_TILES_SLAB);

        offer2x2WithCount(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PHANTOM_MEMBRANE_BLOCK, 2, Items.PHANTOM_MEMBRANE);
        offer2x2WithCount(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BLACK_WOOL, 2, ModItems.SPECTRAL_SCRAP);

        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPECTRAL_BLOCK, 2, Items.PHANTOM_MEMBRANE, ModItems.SPECTRAL_SCRAP);
        offerTilesRecipe(exporter, RecipeCategory.TOOLS, ModItems.SPECTRAL_CLOTH, 2, Items.STRING, ModItems.SPECTRAL_SCRAP);
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FADING_BLOCK, 2, ModBlocks.END_STONE_TILES, ModItems.SPECTRAL_SCRAP);
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.INFUSED_END_STONE_TILES, 2, ModBlocks.END_STONE_TILES, Items.DRAGON_BREATH);

        offerSmelting(exporter, List.of(Blocks.END_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.WEAK_END_STONE_BRICKS, 0.1F, 200, null);

        //Stone Zone
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_BUILDER, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_US, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_DRAGON, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_SCARED, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_WORK, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_FRIENDSHIP, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES, Blocks.END_STONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR, Blocks.END_STONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR, ModBlocks.END_STONE_TILES);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PURPUR_BLOCK_STAIRS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PURPUR_BLOCK_SLAB, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PURPUR_WALL, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_STAIRS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_SLAB, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_STAIRS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_SLAB, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_WALL, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_STAIRS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_SLAB, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_STAIRS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_SLAB, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_WALL, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_STAIRS, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_SLAB, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_WALL, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.POLISHED_PURPUR_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.POLISHED_PURPUR_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_TILES);

        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_SWORD);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_PICKAXE);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_AXE);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_SHOVEL);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_HOE);

        // Spectral Leggings
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SPECTRAL_LEGGINGS)
                .input('A', ModItems.SPECTRAL_SCRAP).input('B', Items.PHANTOM_MEMBRANE)
                .pattern("AAA")
                .pattern("A A")
                .pattern("B B")
                .criterion(hasItem(ModItems.SPECTRAL_SCRAP), conditionsFromItem(ModItems.SPECTRAL_SCRAP))
                .criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(exporter);

    }
}