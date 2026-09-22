package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BouncyBlock extends Block {
    private final Float height;

    public BouncyBlock(Settings settings, Float height) {
        super(settings);
        this.height = height;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        Vec3d velocity = entity.getVelocity();
        if (!entity.isSneaking()) {
            entity.setVelocity(velocity.x, height, velocity.z);
            world.playSound(null, pos, SoundEvents.BLOCK_HONEY_BLOCK_HIT, SoundCategory.BLOCKS);
        }
    }
}
