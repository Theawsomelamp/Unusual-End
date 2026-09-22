package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ChorusCaneBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public ChorusCaneBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(WATERLOGGED, false));
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 10.0F);
    }

    public boolean hasRandomTicks(BlockState state) {
        return state.isOf(ModBlocks.CHORUS_CANE);
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean water = world.getBlockState(pos.up()).isOf(Blocks.WATER);
        if (random.nextInt(3) == 0 && world.getBlockState(pos.up()).isReplaceable()) {
            int i = this.countCaneBelow(world, pos) + 1;
            if (i < 16) {
                if (random.nextInt(4) == 0) {
                    world.setBlockState(pos.up(), ModBlocks.BLOOMING_CHORUS_CANE.getDefaultState().with(WATERLOGGED, water), 3);
                    if (random.nextInt(4) == 0) {
                        if (world.getBlockState(pos.up(2)).isReplaceable()) {
                            world.setBlockState(pos.up(2), ModBlocks.CHORUS_CANE_FLOWER.getDefaultState().with(WATERLOGGED, water), 3);
                        }
                    }
                } else {
                    world.setBlockState(pos.up(), this.getDefaultState().with(WATERLOGGED, water), 3);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }

    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState floor = world.getBlockState(pos.down());
        return (floor.getBlock() instanceof ChorusCaneBlock) || floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.NYLIUM) || floor.isIn(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils")));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(WATERLOGGED, fluidState.isOf(Fluids.WATER));
    }

    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected int countCaneBelow(BlockView world, BlockPos pos) {
        int i;
        for(i = 0; i < 16 && world.getBlockState(pos.down(i + 1)).isOf(this); ++i) {
        }

        return i;
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
}
