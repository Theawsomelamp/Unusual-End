package net.sashakyotoz.unusualend.common.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.BlockSoundGroup;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.blocks.custom.*;
import net.sashakyotoz.unusualend.common.misc.ModParticleTypes;

import java.util.Map;

public class ModBlocks {
    public static void register() {
        UnusualEnd.log("Registering Blocks for modid : " + UnusualEnd.MOD_ID);
        ModRegistry.BlockBuilder.registerWoodSet(CHORUS_NEST_PLANKS, CHORUS_NEST_STAIRS, CHORUS_NEST_SLAB, CHORUS_NEST_FENCE, CHORUS_NEST_FENCE_GATE, CHORUS_NEST_PRESSURE_PLATE, CHORUS_NEST_BUTTON);
        ModRegistry.BlockBuilder.registerBricksSet(RAW_PURPUR_BLOCK, RAW_PURPUR_BLOCK_STAIRS, RAW_PURPUR_BLOCK_SLAB, RAW_PURPUR_WALL);
        ModRegistry.BlockBuilder.registerPolishedSet(POLISHED_PURPUR, POLISHED_PURPUR_STAIRS, POLISHED_PURPUR_SLAB);
        ModRegistry.BlockBuilder.registerPolishedSet(POLISHED_PURPUR_TILES, POLISHED_PURPUR_TILES_STAIRS, POLISHED_PURPUR_TILES_SLAB);
        ModRegistry.BlockBuilder.registerBricksSet(POLISHED_PURPUR_BRICKS, POLISHED_PURPUR_BRICKS_STAIRS, POLISHED_PURPUR_BRICKS_SLAB, POLISHED_PURPUR_BRICKS_WALL);
        ModRegistry.BlockBuilder.registerPolishedSet(POLISHED_GLOOPSLATE, POLISHED_GLOOPSLATE_STAIRS, POLISHED_GLOOPSLATE_SLAB);
        ModRegistry.BlockBuilder.registerPolishedSet(GLOOPSLATE_BRICKS, GLOOPSLATE_BRICKS_STAIRS, GLOOPSLATE_BRICKS_SLAB);
        ModRegistry.BlockBuilder.registerPolishedSet(POLISHED_GLOOPSTONE, POLISHED_GLOOPSTONE_STAIRS, POLISHED_GLOOPSTONE_SLAB);
        ModRegistry.BlockBuilder.registerBricksSet(GLOOPSTONE_BRICKS, GLOOPSTONE_BRICKS_STAIRS, GLOOPSTONE_BRICKS_SLAB, GLOOPSTONE_BRICKS_WALL);
        ModRegistry.BlockBuilder.registerPolishedSet(STRIPPED_CHORUS_NEST_PLANKS, STRIPPED_CHORUS_NEST_STAIRS, STRIPPED_CHORUS_NEST_SLAB);
        ModRegistry.BlockBuilder.registerPolishedSet(CHORUS_NEST_MOSAIC, CHORUS_NEST_MOSAIC_STAIRS, CHORUS_NEST_MOSAIC_SLAB);
        ModRegistry.BlockBuilder.registerPolishedSet(SHINY_CRYSTAL_TILES, SHINY_CRYSTAL_TILE_STAIRS, SHINY_CRYSTAL_TILES_SLAB);
        ModRegistry.BlockBuilder.registerPolishedSet(SHINY_CRYSTAL_BRICKS, SHINY_CRYSTAL_BRICKS_STAIRS, SHINY_CRYSTAL_BRICKS_SLAB);
        ModRegistry.BlockBuilder.registerBricksSet(ENDERBLOB_MOULT_BRICKS, ENDERBLOB_MOULT_BRICKS_STAIRS, ENDERBLOB_MOULT_BRICKS_SLAB, ENDERBLOB_MOULT_BRICKS_WALL);
        ModRegistry.registerSet(CHISELED_GLASS, Map.of(ModRegistry.Models.PANE, CHISELED_GLASS_PANE));
        ModRegistry.registerSet(CRYSTAL_TORCH, Map.of(ModRegistry.Models.WALL_TORCH, CRYSTAL_WALL_TORCH));
    }

    public static final Block PEARLESCENT_BLOCK = ModRegistry.ofBlock("pearlescent_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.COPPER).hardness(2).resistance(15f).luminance(5).jumpVelocityMultiplier(1.1f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block PRISMALITE_BLOCK = ModRegistry.ofBlock("prismalite_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.AMETHYST_BLOCK).hardness(2).resistance(10f).luminance(8).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block CHORUS_PETALS_BLOCK = ModRegistry.ofBlock("chorus_petals_block",
            new ChorusPetalsBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.IRON_GRAY).sounds(BlockSoundGroup.MOSS_BLOCK).strength(0.05f, 1f).jumpVelocityMultiplier(0.9f))).drop().model().tool("_hoe").build();
    public static final Block FIREFLY_BULB = ModRegistry.ofBlock("firefly_bulb",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.YELLOW).sounds(BlockSoundGroup.SLIME).strength(0.15f, 1f).luminance(15).slipperiness(0.9f).jumpVelocityMultiplier(0.9f).nonOpaque())).drop().translucent().build();
    public static final Block SPECTRAL_BLOCK = ModRegistry.ofDescriptiveBlock("spectral_block",
            new CustomCollisionBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.WOOL).hardness(0.2f).resistance(10f))).tool("_pickaxe").drop().translucent().model().build();
    public static final Block PHANTOM_MEMBRANE_BLOCK = ModRegistry.ofDescriptiveBlock("phantom_membrane_block",
            new CustomCollisionBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).slipperiness(0.85f).jumpVelocityMultiplier(0.9f).hardness(0.2f).resistance(3f).pistonBehavior(PistonBehavior.DESTROY))).drop().translucent().tool("_pickaxe").model().build();
    public static final Block DRAGLING_PLUSH = ModRegistry.ofBlock("dragling_plush",
            new DraglingPlushBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.5f, 10f).noCollision().nonOpaque())).drop().cutout().build();

    // Enderblob Blocks
    public static final Block ENDERBLOB_BLOCK = ModRegistry.ofBlock("enderblob_block",
            new BouncyBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.HONEY).strength(0.0f, 3f).slipperiness(0.8f).velocityMultiplier(0.8f).jumpVelocityMultiplier(0.8f).nonOpaque().breakInstantly(), 0.9f)).drop().translucent().model().build();
    public static final Block ENDERBLOB_MOULT_BLOCK = ModRegistry.ofBlock("enderblob_moult_block",
            new Block(FabricBlockSettings.create().instrument(Instrument.HAT).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.STEM).strength(0.4f, 5f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))).drop().translucent().model().tool("_pickaxe").build();
    public static final Block ENDERBLOB_MOULT_BRICKS = ModRegistry.ofBlock("enderblob_moult_bricks",
            new Block(FabricBlockSettings.create().instrument(Instrument.HAT).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.STEM).strength(0.4f, 5f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))).drop().translucent().tool("_pickaxe").build();
    public static final Block ENDERBLOB_MOULT_BRICKS_STAIRS = ModRegistry.ofBlock("enderblob_moult_bricks_stairs",
            new StairsBlock(ModBlocks.ENDERBLOB_MOULT_BRICKS.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.HAT).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.STEM).strength(0.4f, 5f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))).drop().translucent().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block ENDERBLOB_MOULT_BRICKS_SLAB = ModRegistry.ofBlock("enderblob_moult_bricks_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.HAT).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.STEM).strength(0.4f, 5f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))).drop().translucent().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block ENDERBLOB_MOULT_BRICKS_WALL = ModRegistry.ofBlock("enderblob_moult_bricks_wall",
            new WallBlock(FabricBlockSettings.create().instrument(Instrument.HAT).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.STEM).strength(0.4f, 5f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))).drop().translucent().tag(BlockTags.WALLS).tool("_pickaxe").build();

    // Shiny Blocks
    public static final Block SHINY_CRYSTAL_BLOCK = ModRegistry.ofBlock("shiny_crystal_block",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(0.85f, 5f).luminance(10).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block SHINY_CRYSTAL_TILES = ModRegistry.ofBlock("shiny_crystal_tiles",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(0.85f, 4f).luminance(5).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block SHINY_CRYSTAL_TILE_STAIRS = ModRegistry.ofBlock("shiny_crystal_tile_stairs",
            new StairsBlock(ModBlocks.SHINY_CRYSTAL_TILES.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(0.85f, 4f).luminance(5).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block SHINY_CRYSTAL_TILES_SLAB = ModRegistry.ofBlock("shiny_crystal_tiles_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(0.85f, 4f).luminance(5).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block SHINY_CRYSTAL_BRICKS = ModRegistry.ofBlock("shiny_crystal_bricks",
            new Block(FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(0.85f, 4f).luminance(5).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block SHINY_CRYSTAL_BRICKS_STAIRS = ModRegistry.ofBlock("shiny_crystal_bricks_stairs",
            new StairsBlock(ModBlocks.SHINY_CRYSTAL_BRICKS.getDefaultState(), FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(0.85f, 4f).luminance(5).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block SHINY_CRYSTAL_BRICKS_SLAB = ModRegistry.ofBlock("shiny_crystal_bricks_slab",
            new SlabBlock(FabricBlockSettings.create().mapColor(MapColor.PINK).sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(0.85f, 4f).luminance(5).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block CRYSTAL_WALL_TORCH = ModRegistry.ofBlock("crystal_wall_torch",
            new CrystalWallTorchBlock(FabricBlockSettings.create().instrument(Instrument.BASS).mapColor(MapColor.PURPLE).strength(0f, 5f).noCollision().luminance(14), ModParticleTypes.PINK_FLAME), null).cutout().build();
    public static final Block CRYSTAL_TORCH = ModRegistry.ofVerticallyAttachableBlock("crystal_torch",
            new CrystalTorchBlock(FabricBlockSettings.create().instrument(Instrument.BASS).mapColor(MapColor.PURPLE).strength(0f, 5f).noCollision().luminance(14), ModParticleTypes.PINK_FLAME), CRYSTAL_WALL_TORCH).drop().cutout().build();

    // Plants
    public static final Block CHORUS_ROOTS = ModRegistry.ofBlock("chorus_roots",
            new EndFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.DULL_PINK).sounds(BlockSoundGroup.ROOTS), StatusEffects.LEVITATION, 100, 6.0f, 12.0f)).compostable(0.65f).drop_shears().cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block CHORUS_FUNGUS = ModRegistry.ofBlock("chorus_fungus",
            new EndFungusBlock(FabricBlockSettings.copy(Blocks.WARPED_FUNGUS).mapColor(MapColor.PURPLE).replaceable(),
                    RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, UnusualEnd.makeID("chorus_plant_grow")), Blocks.END_STONE, StatusEffects.LEVITATION, 100)).compostable(0.65f).drop().cutout().tool("_hoe").build();
    public static final Block FLOWER_POT_CHORUS_FUNGUS = ModRegistry.ofBlock("flower_pot_chorus_fungus",
            new FlowerPotBlock(ModBlocks.CHORUS_FUNGUS, FabricBlockSettings.copy(Blocks.POTTED_BROWN_MUSHROOM)), null).cutout().tool("_pickaxe").model(ModRegistry.Models.FLOWER_POT).build();
    public static final Block ENDSTONE_SPROUTS = ModRegistry.ofBlock("endstone_sprouts",
            new EndFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PINK).sounds(BlockSoundGroup.NETHER_SPROUTS), StatusEffects.LEVITATION, 100, 6.0f, 3.0f)).compostable(0.5f).drop_shears().cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block PURPUR_GRASS = ModRegistry.ofBlock("purpur_grass",
            new EndFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PINK).sounds(BlockSoundGroup.NETHER_SPROUTS), StatusEffects.LEVITATION, 100, 6.0f, 12.0f)).compostable(0.65f).drop_shears().cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block FLOWERING_PURPUR_GRASS = ModRegistry.ofBlock("flowering_purpur_grass",
            new EndFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PINK).sounds(BlockSoundGroup.NETHER_SPROUTS), StatusEffects.LEVITATION, 100, 6.0f, 12.0f)).compostable(0.65f).drop_shears().tag(BlockTags.FLOWERS).cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block GLOOPY_TENDRILS = ModRegistry.ofBlock("gloopy_tendrils",
            new EndFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.ROOTS), StatusEffects.WEAKNESS, 100, 6.0f, 5.0f)).compostable(0.3f).drop_shears().cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block GLOOPY_BUSH = ModRegistry.ofBlock("gloopy_bush",
            new EndFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.NETHER_SPROUTS), StatusEffects.LEVITATION, 100, 7.0f, 12.0f)).compostable(0.5f).drop().cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block FLOWERING_GLOOPY_PILE = ModRegistry.ofBlock("flowering_gloopy_pile",
            new EndTallFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.SHROOMLIGHT).luminance((state) -> 3))).compostable(0.65f).tag(BlockTags.TALL_FLOWERS).cutout().tool("_hoe").model(ModRegistry.Models.TALL_CROSS).build();
    public static final Block SHINY_SPIREA = ModRegistry.ofBlock("shiny_spirea",
            new EndFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.FUNGUS).luminance((state) -> 8), StatusEffects.LEVITATION, 100, 6.0f, 13.0f)).compostable(0.65f).drop().tag(BlockTags.SMALL_FLOWERS).cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block CRYSTAL_FLOWER = ModRegistry.ofBlock("crystal_flower",
            new CrystalFlowerBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.PINK).sounds(BlockSoundGroup.FUNGUS).luminance((state) -> 10), StatusEffects.NAUSEA, 100, 6.0f, 14.0f)).compostable(1.0f).tag(BlockTags.SMALL_FLOWERS).cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();
    public static final Block DRIPPING_GLOOPSTONE = ModRegistry.ofBlock("dripping_gloopstone",
            new HangingRootsBlock(FabricBlockSettings.copy(Blocks.GRASS).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.CORAL))).compostable(0.3f).drop_shears().cutout().tool("_hoe").model(ModRegistry.Models.CROSS).build();

    // Chorus Cane
    public static final Block CHORUS_CANE = ModRegistry.ofBlock("chorus_cane",
            new ChorusCaneBlock(FabricBlockSettings.create().instrument(Instrument.BASS).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.WOOD).strength(1.7f, 2f).nonOpaque().burnable().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY))).fuel(50).compostable(0.3f).drop().tool("_axe").build();
    public static final Block BLOOMING_CHORUS_CANE = ModRegistry.ofBlock("blooming_chorus_cane",
            new ChorusCaneBlock(FabricBlockSettings.create().instrument(Instrument.BASS).mapColor(MapColor.PURPLE).sounds(BlockSoundGroup.WOOD).strength(1.7f, 2f).nonOpaque().burnable().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY))).fuel(50).compostable(0.3f).drop().cutout().tool("_axe").build();
    public static final Block FLOWER_POT_CANE = ModRegistry.ofBlock("flower_pot_cane",
            new FlowerPotBlock(ModBlocks.CHORUS_CANE, FabricBlockSettings.copy(Blocks.POTTED_BAMBOO)), null).cutout().tool("_pickaxe").build();
    public static final Block CHORUS_CANE_FLOWER = ModRegistry.ofBlock("chorus_cane_flower",
            new ChorusCaneFlowerBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.1f, 10f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY))).compostable(0.85f).cutout().drop().build();

    public static final WoodType CANE = WoodTypeBuilder.copyOf(WoodType.OAK).register(UnusualEnd.makeID("cane"), new BlockSetType("cane"));
    public static final Block STRIPPED_CHORUS_CANE_BLOCK = ModRegistry.ofBlock("stripped_chorus_cane_block",
            new PillarBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.5f, 3f).burnable())).drop().tag(BlockTags.LOGS).tagitem(ItemTags.LOGS).model(ModRegistry.Models.PILLAR).tool("_axe").build();
    public static final Block CHORUS_CANE_BLOCK = ModRegistry.ofBlock("chorus_cane_block",
            new PillarBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.5f, 3f).burnable())).drop().tag(BlockTags.LOGS).tagitem(ItemTags.LOGS).strip(STRIPPED_CHORUS_CANE_BLOCK).model(ModRegistry.Models.PILLAR).tool("_axe").build();
    public static final Block CHORUS_NEST_PLANKS = ModRegistry.ofBlock("chorus_nest_planks",
            new Block(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.PLANKS).tagitem(ItemTags.PLANKS).tool("_axe").build();
    public static final Block CHORUS_NEST_STAIRS = ModRegistry.ofBlock("chorus_nest_stairs",
            new StairsBlock(CHORUS_NEST_PLANKS.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.WOODEN_STAIRS).tagitem(ItemTags.WOODEN_STAIRS).tool("_axe").build();
    public static final Block CHORUS_NEST_SLAB = ModRegistry.ofBlock("chorus_nest_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.WOODEN_SLABS).tagitem(ItemTags.WOODEN_SLABS).tool("_axe").build();
    public static final Block STRIPPED_CHORUS_NEST_PLANKS = ModRegistry.ofBlock("stripped_chorus_nest_planks",
            new Block(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.PLANKS).tagitem(ItemTags.PLANKS).tool("_axe").build();
    public static final Block STRIPPED_CHORUS_NEST_STAIRS = ModRegistry.ofBlock("stripped_chorus_nest_stairs",
            new StairsBlock(STRIPPED_CHORUS_NEST_PLANKS.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.WOODEN_STAIRS).tagitem(ItemTags.WOODEN_STAIRS).tool("_axe").build();
    public static final Block STRIPPED_CHORUS_NEST_SLAB = ModRegistry.ofBlock("stripped_chorus_nest_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.WOODEN_SLABS).tagitem(ItemTags.WOODEN_SLABS).tool("_axe").build();
    public static final Block CHORUS_NEST_MOSAIC = ModRegistry.ofBlock("chorus_nest_mosaic",
            new Block(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.PLANKS).tagitem(ItemTags.PLANKS).tool("_axe").build();
    public static final Block CHORUS_NEST_MOSAIC_STAIRS = ModRegistry.ofBlock("chorus_nest_mosaic_stairs",
            new StairsBlock(CHORUS_NEST_PLANKS.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.WOODEN_STAIRS).tagitem(ItemTags.WOODEN_STAIRS).tool("_axe").build();
    public static final Block CHORUS_NEST_MOSAIC_SLAB = ModRegistry.ofBlock("chorus_nest_mosaic_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.WOODEN_SLABS).tagitem(ItemTags.WOODEN_SLABS).tool("_axe").build();
    public static final Block CHORUS_NEST_FENCE = ModRegistry.ofBlock("chorus_nest_fence",
            new FenceBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable())).drop().tag(BlockTags.FENCES).tagitem(ItemTags.FENCES).tool("_axe").build();
    public static final Block CHORUS_NEST_FENCE_GATE = ModRegistry.ofBlock("chorus_nest_fence_gate",
            new FenceGateBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable(), CANE)).drop().tag(BlockTags.FENCE_GATES).tagitem(ItemTags.FENCE_GATES).tool("_axe").build();
    public static final Block CHORUS_NEST_PLANKS_DOOR = ModRegistry.ofBlock("chorus_nest_planks_door",
            new DoorBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable(), CANE.setType())).drop().tag(BlockTags.WOODEN_DOORS).tagitem(ItemTags.WOODEN_DOORS).model(ModRegistry.Models.DOOR).tool("_axe").build();
    public static final Block CHORUS_NEST_TRAPDOOR = ModRegistry.ofBlock("chorus_nest_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable(), CANE.setType())).drop().tag(BlockTags.WOODEN_TRAPDOORS).tagitem(ItemTags.WOODEN_TRAPDOORS).tool("_axe").build();
    public static final Block CHORUS_NEST_PRESSURE_PLATE = ModRegistry.ofBlock("chorus_nest_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).burnable(), CANE.setType())).drop().tag(BlockTags.WOODEN_PRESSURE_PLATES).tagitem(ItemTags.WOODEN_PRESSURE_PLATES).tool("_axe").build();
    public static final Block CHORUS_NEST_BUTTON = ModRegistry.ofBlock("chorus_nest_button",
            new ButtonBlock(FabricBlockSettings.create().instrument(Instrument.FLUTE).mapColor(MapColor.RED).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3f).noCollision().burnable().pistonBehavior(PistonBehavior.DESTROY), CANE.setType(), 30, true)).drop().tag(BlockTags.WOODEN_BUTTONS).tagitem(ItemTags.WOODEN_BUTTONS).tool("_axe").build();

    // Gloopslate Set
    public static final Block GLOOPY_ENDSTONE = ModRegistry.ofBlock("gloopy_endstone",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).tool("_pickaxe").build();
    public static final Block GLOOPSLATE = ModRegistry.ofBlock("gloopslate",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block BOUNCY_GLOOPSLATE = ModRegistry.ofBlock("bouncy_gloopslate",
            new BouncyBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).slipperiness(0.7f).velocityMultiplier(1.1f).jumpVelocityMultiplier(1.1f).requiresTool(), 0.85f)).drop().tool("_pickaxe").build();
    public static final Block POLISHED_GLOOPSLATE = ModRegistry.ofBlock("polished_gloopslate",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block POLISHED_GLOOPSLATE_STAIRS = ModRegistry.ofBlock("polished_gloopslate_stairs",
            new StairsBlock(POLISHED_GLOOPSLATE.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block POLISHED_GLOOPSLATE_SLAB = ModRegistry.ofBlock("polished_gloopslate_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block GLOOPSLATE_BRICKS = ModRegistry.ofBlock("gloopslate_bricks",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block GLOOPSLATE_BRICKS_STAIRS = ModRegistry.ofBlock("gloopslate_bricks_stairs",
            new StairsBlock(GLOOPSLATE_BRICKS.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block GLOOPSLATE_BRICKS_SLAB = ModRegistry.ofBlock("gloopslate_bricks_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block CHISELED_POLISHED_GLOOPSLATE = ModRegistry.ofBlock("chiseled_polished_gloopslate",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.85f, 5f).requiresTool())).drop().tool("_pickaxe").build();

    // Gloopstone Set
    public static final Block GLOOPSTONE = ModRegistry.ofBlock("gloopstone",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block BOUNCY_GLOOPSTONE = ModRegistry.ofBlock("bouncy_gloopstone",
            new BouncyBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).slipperiness(0.7f).velocityMultiplier(1.1f).jumpVelocityMultiplier(1.1f).requiresTool(), 0.85f)).drop().tool("_pickaxe").build();
    public static final Block POLISHED_GLOOPSTONE = ModRegistry.ofBlock("polished_gloopstone",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block POLISHED_GLOOPSTONE_STAIRS = ModRegistry.ofBlock("polished_gloopstone_stairs",
            new StairsBlock(POLISHED_GLOOPSTONE.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block POLISHED_GLOOPSTONE_SLAB = ModRegistry.ofBlock("polished_gloopstone_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block GLOOPSTONE_BRICKS = ModRegistry.ofBlock("gloopstone_bricks",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block GLOOPSTONE_BRICKS_STAIRS = ModRegistry.ofBlock("gloopstone_bricks_stairs",
            new StairsBlock(GLOOPSTONE_BRICKS.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block GLOOPSTONE_BRICKS_SLAB = ModRegistry.ofBlock("gloopstone_bricks_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block GLOOPSTONE_BRICKS_WALL = ModRegistry.ofBlock("gloopstone_bricks_wall",
            new WallBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(0.95f, 5f).requiresTool())).drop().tag(BlockTags.WALLS).tool("_pickaxe").build();
    public static final Block GLOOPSTONE_TILES = ModRegistry.ofBlock("gloopstone_tiles",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block CHISELED_GLOOPSTONE_BRICKS = ModRegistry.ofBlock("chiseled_gloopstone_bricks",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block GLOOPSTONE_LANTERN = ModRegistry.ofBlock("gloopstone_lantern",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.CORAL).strength(0.95f, 5f).luminance(15).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block GLOOPY_URN = ModRegistry.ofBlock("gloopy_urn",
            new GloopyUrnBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.DARK_DULL_PINK).sounds(BlockSoundGroup.MUD_BRICKS).strength(0.75f, 5f).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))).tool("_pickaxe").build();

    // End Stone
    public static final Block INFESTED_END_STONE = ModRegistry.ofBlock("infested_end_stone",
            new InfestedEndstoneBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block WEAK_END_STONE_BRICKS = ModRegistry.ofBlock("weak_end_stone_bricks",
            new WeakBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block CHISELED_ENDSTONE_BUILDER = ModRegistry.ofDescriptiveBlock("chiseled_endstone_builder",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block CHISELED_ENDSTONE_US = ModRegistry.ofDescriptiveBlock("chiseled_endstone_us",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block CHISELED_ENDSTONE_DRAGON = ModRegistry.ofDescriptiveBlock("chiseled_endstone_dragon",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block CHISELED_ENDSTONE_SCARED = ModRegistry.ofDescriptiveBlock("chiseled_endstone_scared",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block CHISELED_ENDSTONE_WORK = ModRegistry.ofDescriptiveBlock("chiseled_endstone_work",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block CHISELED_ENDSTONE_FRIENDSHIP = ModRegistry.ofDescriptiveBlock("chiseled_endstone_friendship",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().tool("_pickaxe").build();
    // End Stone Tiles
    public static final Block END_STONE_TILES = ModRegistry.ofBlock("end_stone_tiles",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block END_STONE_PILLAR = ModRegistry.ofBlock("end_stone_pillar",
            new PillarBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool())).drop().model(ModRegistry.Models.PILLAR).tool("_pickaxe").build();
    public static final Block INFUSED_END_STONE_TILES = ModRegistry.ofBlock("infused_end_stone_tiles",
            new InfusedBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).strength(1.0f, 5f).requiresTool(), 5.0F)).drop().model().tool("_pickaxe").build();
    public static final Block FADING_BLOCK = ModRegistry.ofBlock("fading_block",
            new FadingBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.STONE).hardness(-1).nonOpaque())).drop().cutout().tool("_pickaxe").build();

    public static final Block PURPUR_EMBEDDED_END_STONE = ModRegistry.ofBlock("purpur_embedded_end_stone",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(3.0f, 9f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_TILES_LANTERN = ModRegistry.ofBlock("polished_purpur_tiles_lantern",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).luminance(15).requiresTool())).drop().model().tool("_pickaxe").build();

    //Raw Purpur Set
    public static final Block RAW_PURPUR_BLOCK = ModRegistry.ofBlock("raw_purpur_block",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block RAW_PURPUR_BLOCK_STAIRS = ModRegistry.ofBlock("raw_purpur_block_stairs",
            new StairsBlock(RAW_PURPUR_BLOCK.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block RAW_PURPUR_BLOCK_SLAB = ModRegistry.ofBlock("raw_purpur_block_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block RAW_PURPUR_WALL = ModRegistry.ofBlock("raw_purpur_wall",
            new WallBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.WALLS).tool("_pickaxe").build();
    public static final Block ROOTED_RAW_PURPUR = ModRegistry.ofBlock("rooted_raw_purpur",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.4f, 6f).requiresTool())).drop().model().tool("_pickaxe").build();
    // Polished Purpur Set
    public static final Block POLISHED_PURPUR = ModRegistry.ofBlock("polished_purpur",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_STAIRS = ModRegistry.ofBlock("polished_purpur_stairs",
            new StairsBlock(POLISHED_PURPUR.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_SLAB = ModRegistry.ofBlock("polished_purpur_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_TILES = ModRegistry.ofBlock("polished_purpur_tiles",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_TILES_STAIRS = ModRegistry.ofBlock("polished_purpur_tiles_stairs",
            new StairsBlock(POLISHED_PURPUR_TILES.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_TILES_SLAB = ModRegistry.ofBlock("polished_purpur_tiles_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block CHISELED_POLISHED_PURPUR_TILES = ModRegistry.ofBlock("chiseled_polished_purpur_tiles",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().model().tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_BRICKS = ModRegistry.ofBlock("polished_purpur_bricks",
            new Block(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_BRICKS_STAIRS = ModRegistry.ofBlock("polished_purpur_bricks_stairs",
            new StairsBlock(POLISHED_PURPUR_BRICKS.getDefaultState(), FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.STAIRS).tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_BRICKS_SLAB = ModRegistry.ofBlock("polished_purpur_bricks_slab",
            new SlabBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.SLABS).tool("_pickaxe").build();
    public static final Block POLISHED_PURPUR_BRICKS_WALL = ModRegistry.ofBlock("polished_purpur_bricks_wall",
            new WallBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).mapColor(MapColor.MAGENTA).sounds(BlockSoundGroup.STONE).strength(1.5f, 6f).requiresTool())).drop().tag(BlockTags.WALLS).tool("_pickaxe").build();

    // Glass
    public static final Block CHISELED_GLASS = ModRegistry.ofBlock("chiseled_glass",
            new GlassBlock(FabricBlockSettings.create().instrument(Instrument.HAT).sounds(BlockSoundGroup.GLASS).strength(0.3F).nonOpaque().requiresTool())).drop().cutout().tag(ConventionalBlockTags.GLASS_BLOCKS).tagitem(ConventionalItemTags.GLASS_BLOCKS).build();
    public static final Block CHISELED_GLASS_PANE = ModRegistry.ofBlock("chiseled_glass_pane",
            new PaneBlock(FabricBlockSettings.create().instrument(Instrument.HAT).sounds(BlockSoundGroup.GLASS).strength(0.3F).nonOpaque().requiresTool())).drop().cutout().tag(ConventionalBlockTags.GLASS_PANES).tagitem(ConventionalItemTags.GLASS_PANES).build();

    public static final Block DRAGON_LAMP = ModRegistry.ofBlock("dragon_lamp",
            new RedstoneLampBlock(FabricBlockSettings.create().instrument(Instrument.BASEDRUM).sounds(BlockSoundGroup.GLASS).strength(0.3F).luminance((state) -> state.get(RedstoneLampBlock.LIT) ? 15 : 0).nonOpaque())).drop().build();


    public static final Block WARPED_END_STONE = ModRegistry.ofBlock("warped_end_stone",
            new GrassBlock(FabricBlockSettings.copy(Blocks.END_STONE).mapColor(MapColor.PALE_GREEN).requiresTool())).tool("_pickaxe").model(ModRegistry.Models.GRASS).build();
}