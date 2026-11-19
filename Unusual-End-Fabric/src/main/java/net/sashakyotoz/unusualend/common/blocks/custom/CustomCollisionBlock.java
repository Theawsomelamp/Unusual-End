package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CustomCollisionBlock extends Block {
    private final boolean player;

    public CustomCollisionBlock(Settings settings, boolean player) {
        super(settings);
        this.player = player;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (!this.player) {
            return ((EntityShapeContext) context).getEntity() instanceof PlayerEntity ? VoxelShapes.empty() : getOutlineShape(state, world, pos, context);
        } else {
            return ((EntityShapeContext) context).getEntity() instanceof PlayerEntity ? getOutlineShape(state, world, pos, context) : VoxelShapes.empty();
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.5, 0.5, 0.5, 15.5, 15.5, 15.5);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!this.player && entity instanceof PlayerEntity player)
            player.fallDistance = 0;
    }
}