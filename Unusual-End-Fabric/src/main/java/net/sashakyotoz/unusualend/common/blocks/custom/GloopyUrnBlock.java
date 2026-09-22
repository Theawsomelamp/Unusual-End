package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.UnusualEnd;

public class GloopyUrnBlock extends WaterloggableHorizontalFacingBlock {
    public GloopyUrnBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(3, 0, 3, 13, 14, 13);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            Advancement advancement = serverPlayer.server.getAdvancementLoader().get(UnusualEnd.makeID("break_gloopy_urn"));
            AdvancementProgress progress = serverPlayer.getAdvancementTracker().getProgress(advancement);
            if (!progress.isDone()) {
                for (String criteria : progress.getUnobtainedCriteria())
                    serverPlayer.getAdvancementTracker().grantCriterion(advancement, criteria);
            }
        }
        super.onBreak(world, pos, state, player);
    }
}
