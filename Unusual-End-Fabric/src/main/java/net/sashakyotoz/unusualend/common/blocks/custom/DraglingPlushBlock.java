package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DraglingPlushBlock extends WaterloggableHorizontalFacingBlock{
    public DraglingPlushBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> Block.createCuboidShape(6, 5, 5, 11, 10, 10);
            case EAST -> Block.createCuboidShape(6, 5, 6, 11, 10, 11);
            case WEST -> Block.createCuboidShape(5, 5, 5, 10, 10, 10);
            default -> Block.createCuboidShape(5, 5, 6, 10, 10, 11);
        };
    }
}
