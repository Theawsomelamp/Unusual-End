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
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.sashakyotoz.unusualend.UnusualEnd;
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
        recipe.group(group).offerTo(exporter, getItemPath(output) + "_shapeless");
    }

    public static void offerShapelessWithDuplicatesRecipe(
            Consumer<RecipeJsonProvider> exporter, ItemConvertible output, Map<ItemConvertible, Integer> inputs, @Nullable String group, int outputCount
    ) {
        ShapelessRecipeJsonBuilder recipe = ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount);

        for (ItemConvertible item : inputs.keySet()) {
            recipe.input(item , inputs.get(item)).criterion(hasItem(item), conditionsFromItem(item));
        }
        recipe.group(group).offerTo(exporter, getItemPath(output) + "_shapeless");
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
                                         ItemConvertible a, Ingredient b) {
        ShapedRecipeJsonBuilder.create(category, result, count)
                .pattern("AB").pattern("BA")
                .input('A', a).input('B', b)
                .criterion(hasItem(a), conditionsFromItem(a))
                .group(Registries.ITEM.getId(result.asItem()).getPath())
                .offerTo(exporter, Registries.ITEM.getId(result.asItem()));
    }

    private static void offerCompassRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible result, int count,
                                         ItemConvertible a, ItemConvertible b) {
        ShapedRecipeJsonBuilder.create(category, result, count)
                .pattern(" B ").pattern("BAB").pattern(" B ")
                .input('A', a).input('B', b)
                .criterion(hasItem(a), conditionsFromItem(a))
                .criterion(hasItem(b), conditionsFromItem(b))
                .group(Registries.ITEM.getId(result.asItem()).getPath())
                .offerTo(exporter, Registries.ITEM.getId(result.asItem()));
    }

    private static void offerArrowRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible result, int count,
                                           ItemConvertible a, ItemConvertible b, ItemConvertible c) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, result, count)
                .pattern("A")
                .pattern("B")
                .pattern("C")
                .input('A', a).input('B', b).input('C', c)
                .criterion(hasItem(a), conditionsFromItem(a))
                .criterion(hasItem(b), conditionsFromItem(b))
                .criterion(hasItem(c), conditionsFromItem(c))
                .group(Registries.ITEM.getId(result.asItem()).getPath())
                .offerTo(exporter, Registries.ITEM.getId(result.asItem()));
    }

    private static void offerLeadRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible result, int count,
                                         ItemConvertible a, ItemConvertible b) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, result, count)
                .input('A', a).input('B', b)
                .pattern("AA ")
                .pattern("AB ")
                .pattern("  A")
                .criterion(hasItem(a), conditionsFromItem(a))
                .criterion(hasItem(b), conditionsFromItem(b))
                .offerTo(exporter);
    }

    private static void offerCatalystConversionRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory category, ItemConvertible result,
                                           ItemConvertible a, ItemConvertible b) {
        ShapedRecipeJsonBuilder.create(category, result, 8)
                .input('#', b).input('X', a)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .criterion(hasItem(a), conditionsFromItem(a))
                .criterion(hasItem(b), conditionsFromItem(b))
                .offerTo(exporter, getItemPath(result) + "_from_" + getItemPath(b) + "_conversion");
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
        offerReversibleCompactingRecipes(exporter,RecipeCategory.MISC, ModItems.SHINY_CRYSTAL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_BLOCK);
        offerReversibleCompactingRecipes(exporter,RecipeCategory.MISC, ModItems.CHORUS_PETAL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHORUS_PETALS_BLOCK);
        offerReversibleCompactingRecipes(exporter,RecipeCategory.MISC, ModItems.END_BLOB, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_BLOCK);
        offerReversibleCompactingRecipes(exporter,RecipeCategory.MISC, ModItems.ENDERBLOB_MOULT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BLOCK);
        offerReversibleCompactingRecipes(exporter,RecipeCategory.MISC, ModItems.PRISMALITE_SHARD, RecipeCategory.MISC, ModItems.PRISMALITE_GEM);
        offerCompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHORUS_CANE_BLOCK, ModBlocks.CHORUS_CANE);
        offerCompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PRISMALITE_BLOCK, ModItems.PRISMALITE_GEM);

        offerShapelessRecipe(exporter, Items.BLACK_DYE, ModItems.SPECTRAL_SCRAP, "black_dye", 1);
        offerShapelessRecipe(exporter, Items.MAGENTA_DYE, Items.CHORUS_FRUIT, "magenta_dye", 1);
        offerShapelessRecipe(exporter, Items.PURPLE_DYE, ModBlocks.GLOOPY_BUSH, "purple_dye", 1);
        offerShapelessRecipe(exporter, Items.PHANTOM_MEMBRANE, ModBlocks.PHANTOM_MEMBRANE_BLOCK, null, 2);
        offerShapelessRecipe(exporter, ModBlocks.CHORUS_CANE, ModBlocks.BLOOMING_CHORUS_CANE, null, 1);
        offerShapelessRecipe(exporter, ModItems.CHORUS_PETAL, ModBlocks.CHORUS_CANE_FLOWER, null, 2);
        offerShapelessRecipe(exporter, ModBlocks.CHORUS_NEST_PLANKS, ModBlocks.CHORUS_CANE_BLOCK, null, 4);
        offerShapelessRecipe(exporter, ModBlocks.STRIPPED_CHORUS_NEST_PLANKS, ModBlocks.STRIPPED_CHORUS_CANE_BLOCK, null, 4);
        offerShapelessRecipe(exporter, ModBlocks.CHORUS_NEST_PLANKS, ModBlocks.STRIPPED_CHORUS_NEST_PLANKS, null, 1);
        offerShapelessRecipe(exporter, ModBlocks.CHORUS_NEST_STAIRS, ModBlocks.STRIPPED_CHORUS_NEST_STAIRS, null, 1);
        offerShapelessRecipe(exporter, ModBlocks.CHORUS_NEST_SLAB, ModBlocks.STRIPPED_CHORUS_NEST_SLAB, null, 1);
        offerShapelessRecipe(exporter, ModItems.PRISMALITE_GEM, ModBlocks.PRISMALITE_BLOCK, null, 9);

        offerMultiItemShapelessRecipe(exporter, ModBlocks.ROOTED_RAW_PURPUR, List.of(ModBlocks.RAW_PURPUR_BLOCK, ModBlocks.CHORUS_ROOTS), null, 1);
        offerMultiItemShapelessRecipe(exporter, Blocks.END_ROD, List.of(Blocks.PURPUR_BLOCK, ModBlocks.FIREFLY_BULB), null, 8);
        offerMultiItemShapelessRecipe(exporter, ModBlocks.PURPUR_EMBEDDED_END_STONE, List.of(Blocks.END_STONE, ModBlocks.POLISHED_PURPUR), null, 2);
        offerMultiItemShapelessRecipe(exporter, ModItems.CHORUS_PIE, List.of(ModItems.ENDER_FIREFLY_EGG, ModItems.CHORUS_PETAL, Items.CHORUS_FRUIT), null, 1);
        offerMultiItemShapelessRecipe(exporter, ModBlocks.DRAGON_LAMP, List.of(Blocks.REDSTONE_LAMP, ModItems.SHINY_CRYSTAL, Items.DRAGON_BREATH), null, 1);
        offerMultiItemShapelessRecipe(exporter, ModBlocks.GLOOPY_ENDSTONE, List.of(ModBlocks.GLOOPSLATE, Blocks.END_STONE), null, 2);
        offerMultiItemShapelessRecipe(exporter, ModItems.SHINY_GUM, List.of(ModItems.END_BLOB_JELLY, ModItems.SHINY_CRYSTAL), null, 1);
        offerMultiItemShapelessRecipe(exporter, ModItems.PRISMATIC_GUM, List.of(ModItems.END_BLOB_JELLY, ModItems.PRISMALITE_GEM), null, 1);
        offerMultiItemShapelessRecipe(exporter, ModItems.ENDER_STEW, List.of(Items.CHORUS_FRUIT, ModBlocks.CHORUS_FUNGUS, ModBlocks.CHORUS_ROOTS, Items.BOWL, ModItems.END_BLOB), null, 1);
        offerMultiItemShapelessRecipe(exporter, ModBlocks.BOUNCY_GLOOPSLATE, List.of(ModBlocks.GLOOPSLATE, ModItems.END_BLOB), null, 1);
        offerMultiItemShapelessRecipe(exporter, ModBlocks.BOUNCY_GLOOPSTONE, List.of(ModBlocks.GLOOPSTONE, ModItems.END_BLOB), null, 1);

        offerShapelessWithDuplicatesRecipe(exporter, Items.PAPER, Map.of(ModItems.CHORUS_PETAL, 3), null, 1);
        offerShapelessWithDuplicatesRecipe(exporter, ModItems.CHORUS_JUICE, Map.of(Items.GLASS_BOTTLE, 1, Items.CHORUS_FRUIT, 3), null, 1);
        offerShapelessWithDuplicatesRecipe(exporter, ModItems.CHORUS_TEA, Map.of(ModItems.CHORUS_JUICE, 1, ModItems.CHORUS_PETAL, 2), null, 1);
        offerShapelessWithDuplicatesRecipe(exporter, ModItems.BLOB_STEW, Map.of(ModItems.END_BLOB, 2, Items.BOWL, 1), null, 1);

        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR, ModBlocks.RAW_PURPUR_BLOCK);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS, ModBlocks.POLISHED_PURPUR);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE, ModBlocks.GLOOPSLATE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS, ModBlocks.POLISHED_GLOOPSLATE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSTONE, ModBlocks.GLOOPSTONE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS, ModBlocks.POLISHED_GLOOPSTONE);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_TILES, ModBlocks.GLOOPSTONE_BRICKS);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_TILES, Blocks.END_STONE_BRICKS);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_GLASS, Blocks.GLASS);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_BRICKS, ModBlocks.SHINY_CRYSTAL_TILES);
        offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS, ModBlocks.ENDERBLOB_MOULT_BLOCK);

        offerPillarRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.END_STONE_PILLAR, ModBlocks.END_STONE_TILES);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_TILES_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_GLOOPSLATE, ModBlocks.POLISHED_GLOOPSLATE_SLAB);
        offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_GLOOPSTONE_BRICKS, ModBlocks.GLOOPSTONE_BRICKS_SLAB);
        offerMosaicRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHORUS_NEST_MOSAIC, ModBlocks.CHORUS_NEST_SLAB);

        createDoorRecipe(ModBlocks.CHORUS_NEST_PLANKS_DOOR, Ingredient.ofItems(ModBlocks.CHORUS_NEST_PLANKS)).criterion(hasItem(ModBlocks.CHORUS_NEST_PLANKS), conditionsFromItem(ModBlocks.CHORUS_NEST_PLANKS)).offerTo(exporter);
        createTrapdoorRecipe(ModBlocks.CHORUS_NEST_TRAPDOOR, Ingredient.ofItems(ModBlocks.CHORUS_NEST_PLANKS)).criterion(hasItem(ModBlocks.CHORUS_NEST_PLANKS), conditionsFromItem(ModBlocks.CHORUS_NEST_PLANKS)).offerTo(exporter);

        offer2x2WithCount(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PHANTOM_MEMBRANE_BLOCK, 2, Items.PHANTOM_MEMBRANE);
        offer2x2WithCount(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BLACK_WOOL, 2, ModItems.SPECTRAL_SCRAP);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHORUS_NEST_PLANKS, ModBlocks.CHORUS_CANE);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PURPLE_STAINED_GLASS, ModItems.ENDERBLOB_MOULT);

        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPECTRAL_BLOCK, 2, Items.PHANTOM_MEMBRANE, Ingredient.ofItems(ModItems.SPECTRAL_SCRAP));
        offerTilesRecipe(exporter, RecipeCategory.TOOLS, ModItems.SPECTRAL_CLOTH, 2, Items.STRING, Ingredient.ofItems(ModItems.SPECTRAL_SCRAP));
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FADING_BLOCK, 2, ModBlocks.END_STONE_TILES, Ingredient.ofItems(ModItems.SPECTRAL_SCRAP));
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.INFUSED_END_STONE_TILES, 2, ModBlocks.END_STONE_TILES, Ingredient.ofItems(Items.DRAGON_BREATH));
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_TILES, 4, ModItems.SHINY_CRYSTAL, Ingredient.ofItems(Items.AMETHYST_SHARD));
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_LANTERN, 1, ModBlocks.POLISHED_GLOOPSTONE, Ingredient.ofItems(ModItems.SHINY_CRYSTAL));
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE, 4, ModBlocks.RAW_PURPUR_BLOCK, Ingredient.fromTag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("blob_loot"))));
        offerTilesRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE, 4, ModBlocks.GLOOPSLATE, Ingredient.fromTag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("blob_loot"))));


        offerCompassRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_LANTERN, 4, ModBlocks.FIREFLY_BULB, ModBlocks.POLISHED_PURPUR_TILES);
        offerCompassRecipe(exporter, RecipeCategory.COMBAT, ModItems.WANDERING_PEARL, 1, Items.ENDER_PEARL, Items.CHORUS_FRUIT);
        offerCompassRecipe(exporter, RecipeCategory.COMBAT, ModItems.PHANTOM_ARROW, 1, Items.ARROW, Items.PHANTOM_MEMBRANE);
        offerCompassRecipe(exporter, RecipeCategory.MISC, ModBlocks.DRAGLING_PLUSH, 1, Items.STRING, ModItems.SPECTRAL_SCRAP);

        offerArrowRecipe(exporter, Items.MAGENTA_CANDLE, 1, ModBlocks.CHORUS_ROOTS, ModItems.END_BLOB, ModBlocks.GLOOPSLATE);
        offerArrowRecipe(exporter, Items.PURPLE_CANDLE, 1, ModBlocks.CHORUS_ROOTS, ModItems.END_BLOB, ModBlocks.GLOOPSTONE);
        offerArrowRecipe(exporter, ModBlocks.CRYSTAL_TORCH, 2, ModItems.SHINY_CRYSTAL, ModItems.END_BLOB, Items.STICK);

        offerLeadRecipe(exporter, Items.LEAD, 2, Items.STRING, ModItems.END_BLOB);
        offerLeadRecipe(exporter, ModItems.PRISMATIC_MIRROR, 1, ModItems.PEARLESCENT_INGOT, ModItems.PRISMALITE_GEM);

        offerCatalystConversionRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE, ModItems.SHINY_CRYSTAL, ModBlocks.WARPED_END_STONE);
        offerCatalystConversionRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_NYLIUM, ModItems.SHINY_CRYSTAL, Blocks.WARPED_NYLIUM);
        offerCatalystConversionRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK, ModItems.SHINY_CRYSTAL, Blocks.NETHER_WART_BLOCK);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.CRIMSON_FUNGUS, ModItems.SHINY_CRYSTAL, Blocks.WARPED_FUNGUS);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.TUBE_CORAL_BLOCK, ModItems.SHINY_CRYSTAL, Blocks.DEAD_TUBE_CORAL_BLOCK);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.BRAIN_CORAL_BLOCK, ModItems.SHINY_CRYSTAL, Blocks.DEAD_BRAIN_CORAL_BLOCK);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.BUBBLE_CORAL_BLOCK, ModItems.SHINY_CRYSTAL, Blocks.DEAD_BUBBLE_CORAL_BLOCK);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.FIRE_CORAL_BLOCK, ModItems.SHINY_CRYSTAL, Blocks.DEAD_FIRE_CORAL_BLOCK);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.HORN_CORAL_BLOCK, ModItems.SHINY_CRYSTAL, Blocks.DEAD_HORN_CORAL_BLOCK);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.TUBE_CORAL, ModItems.SHINY_CRYSTAL, Blocks.DEAD_TUBE_CORAL);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.BRAIN_CORAL, ModItems.SHINY_CRYSTAL, Blocks.DEAD_BRAIN_CORAL);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.BUBBLE_CORAL, ModItems.SHINY_CRYSTAL, Blocks.DEAD_BUBBLE_CORAL);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.FIRE_CORAL, ModItems.SHINY_CRYSTAL, Blocks.DEAD_FIRE_CORAL);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, Blocks.HORN_CORAL, ModItems.SHINY_CRYSTAL, Blocks.DEAD_HORN_CORAL);
        offerCatalystConversionRecipe(exporter, RecipeCategory.MISC, ModBlocks.CHORUS_ROOTS, ModItems.SHINY_CRYSTAL, Blocks.WARPED_ROOTS);

        offerSmelting(exporter, List.of(Blocks.END_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, ModBlocks.WEAK_END_STONE_BRICKS, 0.1F, 200, null);
        offerSmelting(exporter, List.of(ModItems.END_BLOB), RecipeCategory.MISC, ModItems.END_BLOB_JELLY, 0.35F, 200, null);

        offerBlasting(exporter, List.of(ModBlocks.CRYSTAL_FLOWER), RecipeCategory.MISC, ModItems.SHINY_CRYSTAL, 0.2F, 100, null);

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
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PURPUR_BLOCK_SLAB, ModBlocks.RAW_PURPUR_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PURPUR_WALL, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_STAIRS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_SLAB, ModBlocks.RAW_PURPUR_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_STAIRS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_SLAB, ModBlocks.RAW_PURPUR_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_WALL, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.RAW_PURPUR_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.RAW_PURPUR_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_STAIRS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_SLAB, ModBlocks.POLISHED_PURPUR, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_STAIRS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_SLAB, ModBlocks.POLISHED_PURPUR, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_WALL, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.POLISHED_PURPUR, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_STAIRS, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_SLAB, ModBlocks.POLISHED_PURPUR_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_BRICKS_WALL, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.POLISHED_PURPUR_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_STAIRS, ModBlocks.POLISHED_PURPUR_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_PURPUR_TILES_SLAB, ModBlocks.POLISHED_PURPUR_TILES, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_PURPUR_TILES, ModBlocks.POLISHED_PURPUR_TILES);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_TILE_STAIRS, ModBlocks.SHINY_CRYSTAL_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_TILES_SLAB, ModBlocks.SHINY_CRYSTAL_TILES, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_BRICKS, ModBlocks.SHINY_CRYSTAL_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_BRICKS_STAIRS, ModBlocks.SHINY_CRYSTAL_TILES);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_BRICKS_SLAB, ModBlocks.SHINY_CRYSTAL_TILES, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_BRICKS_STAIRS, ModBlocks.SHINY_CRYSTAL_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SHINY_CRYSTAL_BRICKS_SLAB, ModBlocks.SHINY_CRYSTAL_BRICKS, 2);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE, ModBlocks.GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE_STAIRS, ModBlocks.GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE_SLAB, ModBlocks.GLOOPSLATE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_GLOOPSLATE, ModBlocks.GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS, ModBlocks.GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS_STAIRS, ModBlocks.GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS_SLAB, ModBlocks.GLOOPSLATE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE_STAIRS, ModBlocks.POLISHED_GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSLATE_SLAB, ModBlocks.POLISHED_GLOOPSLATE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_POLISHED_GLOOPSLATE, ModBlocks.POLISHED_GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS, ModBlocks.POLISHED_GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS_STAIRS, ModBlocks.POLISHED_GLOOPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS_SLAB, ModBlocks.POLISHED_GLOOPSLATE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS_STAIRS, ModBlocks.GLOOPSLATE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSLATE_BRICKS_SLAB, ModBlocks.GLOOPSLATE_BRICKS, 2);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSTONE, ModBlocks.GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSTONE_STAIRS, ModBlocks.GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSTONE_SLAB, ModBlocks.GLOOPSTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS, ModBlocks.GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_STAIRS, ModBlocks.GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_SLAB, ModBlocks.GLOOPSTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_WALL, ModBlocks.GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_TILES, ModBlocks.GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_GLOOPSTONE_BRICKS, ModBlocks.GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSTONE_STAIRS, ModBlocks.POLISHED_GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_GLOOPSTONE_SLAB, ModBlocks.POLISHED_GLOOPSTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS, ModBlocks.POLISHED_GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_STAIRS, ModBlocks.POLISHED_GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_SLAB, ModBlocks.POLISHED_GLOOPSTONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_WALL, ModBlocks.POLISHED_GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_TILES, ModBlocks.POLISHED_GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_GLOOPSTONE_BRICKS, ModBlocks.POLISHED_GLOOPSTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_STAIRS, ModBlocks.GLOOPSTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_SLAB, ModBlocks.GLOOPSTONE_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_BRICKS_WALL, ModBlocks.GLOOPSTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLOOPSTONE_TILES, ModBlocks.GLOOPSTONE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_GLOOPSTONE_BRICKS, ModBlocks.GLOOPSTONE_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS, ModBlocks.ENDERBLOB_MOULT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS_STAIRS, ModBlocks.ENDERBLOB_MOULT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS_SLAB, ModBlocks.ENDERBLOB_MOULT_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS_WALL, ModBlocks.ENDERBLOB_MOULT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS_STAIRS, ModBlocks.ENDERBLOB_MOULT_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS_SLAB, ModBlocks.ENDERBLOB_MOULT_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERBLOB_MOULT_BRICKS_WALL, ModBlocks.ENDERBLOB_MOULT_BRICKS);

        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_SWORD);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_PICKAXE);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_AXE);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_SHOVEL);
        offerSmithingUpgradeRecipe(exporter, RecipeCategory.TOOLS, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.PEARLESCENT_INGOT, ModItems.PEARLESCENT_HOE);

        // Stick from Chorus Cane
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STICK)
                .input('#', ModBlocks.CHORUS_CANE)
                .pattern("#")
                .pattern("#")
                .criterion(hasItem(ModBlocks.CHORUS_CANE), conditionsFromItem(ModBlocks.CHORUS_CANE))
                .offerTo(exporter, getItemPath(Items.STICK) + "_from_chorus_cane");

        // Glass Bottle form Enderblob Moult
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GLASS_BOTTLE)
                .input('#', ModItems.ENDERBLOB_MOULT)
                .pattern("# #")
                .pattern(" # ")
                .criterion(hasItem(ModItems.ENDERBLOB_MOULT), conditionsFromItem(ModItems.ENDERBLOB_MOULT))
                .offerTo(exporter, getItemPath(Items.GLASS_BOTTLE) + "_from_enderblob_moult");

        // String from Chorus Roots (and Chorus Cane Flower)
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STRING, 2)
                .input('A', ModBlocks.CHORUS_ROOTS).input('B', ModBlocks.CHORUS_CANE_FLOWER)
                .pattern("ABA")
                .criterion(hasItem(ModBlocks.CHORUS_ROOTS), conditionsFromItem(ModBlocks.CHORUS_ROOTS))
                .criterion(hasItem(ModBlocks.CHORUS_CANE_FLOWER), conditionsFromItem(ModBlocks.CHORUS_CANE_FLOWER))
                .offerTo(exporter,getItemPath(Items.STRING) + "_from_chorus_roots" );

        // Floating Pouch
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLOATING_POUCH)
                .input('A', ModItems.SPECTRAL_SCRAP).input('B', ModItems.LURKER_SLUDGE).input('C', Items.STRING)
                .pattern(" C ")
                .pattern("ABA")
                .pattern(" A ")
                .criterion(hasItem(ModItems.SPECTRAL_SCRAP), conditionsFromItem(ModItems.SPECTRAL_SCRAP))
                .criterion(hasItem(ModItems.LURKER_SLUDGE), conditionsFromItem(ModItems.LURKER_SLUDGE))
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .offerTo(exporter);

        // Pearlescent Template Duplication
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE, 2)
                .input('#', ModItems.PRISMALITE_GEM).input('C', ModItems.PEARLESCENT_INGOT).input('S', ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .criterion(hasItem(ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(ModItems.PEARLESCENT_UPGRADE_SMITHING_TEMPLATE))
                .offerTo(exporter);

        // Spectral Leggings
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SPECTRAL_LEGGINGS)
                .input('A', ModItems.SPECTRAL_SCRAP).input('B', Items.PHANTOM_MEMBRANE)
                .pattern("AAA")
                .pattern("A A")
                .pattern("B B")
                .criterion(hasItem(ModItems.SPECTRAL_SCRAP), conditionsFromItem(ModItems.SPECTRAL_SCRAP))
                .criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(exporter);

        // Chorus Helmet
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CHORUS_HELMET)
                .input('A', ModItems.END_BLOB)
                .input('B', Items.CHORUS_FRUIT)
                .input('C', Ingredient.fromTag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("chorus_flowers"))))
                .input('D', Items.LEATHER_HELMET)
                .pattern("BCB")
                .pattern("ADA")
                .criterion(hasItem(ModItems.END_BLOB), conditionsFromItem(ModItems.END_BLOB))
                .criterion(hasItem(Items.CHORUS_FRUIT), conditionsFromItem(Items.CHORUS_FRUIT))
                .criterion(hasItem(Items.LEATHER_HELMET), conditionsFromItem(Items.LEATHER_HELMET))
                .offerTo(exporter);

    }
}