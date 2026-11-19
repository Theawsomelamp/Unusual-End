package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.GlassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class HardGlassBlock extends GlassBlock {
    private final Block crackedBlock;

    public HardGlassBlock(Settings settings, Block crackedBlock) {
        super(settings);
        this.crackedBlock = crackedBlock;
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {

        if (Math.random() < 0.95) {
            world.setBlockState(pos, this.getDefaultState(), 3);
        } else {
            world.setBlockState(pos, crackedBlock.getDefaultState(), 3);
        }
    }

    @Override
    //Note: Forge version does not have this method and duplicates the block when exploded
    public boolean shouldDropItemsOnExplosion(Explosion explosion) {
        return false;
    }
}
