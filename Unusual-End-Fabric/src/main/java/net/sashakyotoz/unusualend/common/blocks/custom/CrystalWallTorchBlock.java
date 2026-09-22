package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class CrystalWallTorchBlock extends WallTorchBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public CrystalWallTorchBlock(Settings settings, ParticleEffect particleEffect) {
        super(settings, particleEffect);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> Block.createCuboidShape(5.5F, 3.0F, 11.0F, 10.5F, 13.0F, 16.0F);
            case EAST -> Block.createCuboidShape(0.0F, 3.0F, 5.5F, 5.0F, 13.0F, 10.5F);
            case WEST -> Block.createCuboidShape(11.0F, 3.0F, 5.5F, 16.0F, 13.0F, 10.5F);
            default -> Block.createCuboidShape(5.5F, 3.0F, 0.0F, 10.5F, 13.0F, 5.0F);
        };
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(WATERLOGGED, fluidState.isOf(Fluids.WATER));
    }

    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(FACING);
    }
}
