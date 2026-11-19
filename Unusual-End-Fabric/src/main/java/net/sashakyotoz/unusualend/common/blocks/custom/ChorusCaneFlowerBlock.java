package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class ChorusCaneFlowerBlock extends WaterloggableWallMountedBlock {
    public ChorusCaneFlowerBlock(Settings settings) {
        super(settings);
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
}
