package net.sashakyotoz.unusualend.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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

import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    public static void offer2x2WithCount(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible output, int count, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(category, output, count)
                .input('#', input)
                .pattern("##")
                .pattern("##")
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

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR, ModBlocks.RAW_PURPUR_BLOCK);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE, ModBlocks.GLOOPSLATE);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS, ModBlocks.POLISHED_GLOOPSLATE);

        offer2x2WithCount(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PHANTOM_MEMBRANE_BLOCK, 2, Items.PHANTOM_MEMBRANE);
        offer2x2WithCount(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BLACK_WOOL, 2, ModItems.SPECTRAL_SCRAP);

        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPECTRAL_BLOCK, 2, Items.PHANTOM_MEMBRANE, ModItems.SPECTRAL_SCRAP);
        offerTilesRecipe(exporter, RecipeCategory.TOOLS, ModItems.SPECTRAL_CLOTH, 2, Items.STRING, ModItems.SPECTRAL_SCRAP);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_BUILDER, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_US, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_DRAGON, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_SCARED, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_WORK, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_ENDSTONE_FRIENDSHIP, Blocks.END_STONE);

        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_SWORD);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_PICKAXE);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_AXE);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_SHOVEL);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_HOE);
    }
}