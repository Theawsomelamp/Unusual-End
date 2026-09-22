package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChorusPetalsBlock extends FallingBlock {
    public ChorusPetalsBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 0.2F, world.getDamageSources().fall());
    }
}
