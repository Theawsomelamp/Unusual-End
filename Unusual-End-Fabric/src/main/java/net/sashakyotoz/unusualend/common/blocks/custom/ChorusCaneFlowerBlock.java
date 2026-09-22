package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class ChorusCaneFlowerBlock extends WallMountedBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public ChorusCaneFlowerBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(WATERLOGGED, false));
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlaceAt(world, pos, getDirection(state).getOpposite());
    }

    public static boolean canPlaceAt(WorldView world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction);
        return world.getBlockState(blockPos).isSideSolid(world, blockPos, direction.getOpposite(), SideShapeType.CENTER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACE)) {
            case FLOOR -> Block.createCuboidShape(4, 0, 4, 12, 8, 12);
            case WALL -> switch (state.get(FACING)) {
                case WEST -> Block.createCuboidShape(8, 4, 4, 16, 12, 12);
                case EAST -> Block.createCuboidShape(0, 4, 4, 8, 12, 12);
                case SOUTH -> Block.createCuboidShape(4, 4, 0, 12, 12, 8);
                case NORTH, UP, DOWN -> Block.createCuboidShape(4, 4, 8, 12, 12, 16);
            };
            case CEILING -> Block.createCuboidShape(4, 8, 4, 12, 16, 12);
        };
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        for(Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = this.getDefaultState().with(FACE, direction == Direction.UP ? WallMountLocation.CEILING : WallMountLocation.FLOOR).with(FACING, ctx.getHorizontalPlayerFacing()).with(WATERLOGGED, fluidState.isOf(Fluids.WATER));
            } else {
                blockState = this.getDefaultState().with(FACE, WallMountLocation.WALL).with(FACING, direction.getOpposite()).with(WATERLOGGED, fluidState.isOf(Fluids.WATER));
            }

            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState.with(WATERLOGGED, fluidState.isOf(Fluids.WATER));
            }
        }

        return null;
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(FACE);
        builder.add(FACING);
    }
}
