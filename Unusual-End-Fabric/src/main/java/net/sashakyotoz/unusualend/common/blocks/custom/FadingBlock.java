package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FadingBlock extends Block {
    public static final IntProperty FADING = IntProperty.of("fading", 0, 3);

    public FadingBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(FADING, 0));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(FADING) > 0) {
            return Block.createCuboidShape(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        }
        return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.isSneaking()) {
            world.playSound(null, pos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS);
            for (BlockPos pos2 : BlockPos.iterate(pos.add(-3, -3, -3), pos.add(3, 3, 3))) {
                world.scheduleBlockTick(pos2, this, 1);
            }
            for (int i = 0; i < 8; ++i) {
                world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + 0.5 + (2.0 * world.random.nextDouble() - 1.0), pos.getY() + 0.5 + (2.0 * world.random.nextDouble() - 1.0), pos.getZ() + 0.5 + (2.0 * world.random.nextDouble() - 1.0), 0.0F, 0.0F, 0.0F);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        world.playSound(null, hit.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS);
        for (BlockPos pos : BlockPos.iterate(hit.getBlockPos().add(-3, -3, -3), hit.getBlockPos().add(3, 3, 3))) {
            world.scheduleBlockTick(pos, this, 1);
        }
        for (int i = 0; i < 8; ++i) {
            world.addParticle(ParticleTypes.DRAGON_BREATH, hit.getBlockPos().getX() + 0.5 + (2.0 * world.random.nextDouble() - 1.0), hit.getBlockPos().getY() + 0.5 + (2.0 * world.random.nextDouble() - 1.0), hit.getBlockPos().getZ() + 0.5 + (2.0 * world.random.nextDouble() - 1.0), 0.0F, 0.0F, 0.0F);
        }
        super.onProjectileHit(world, state, hit, projectile);
    }

    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.cycle(FADING), 2);

        if (state.get(FADING) == 3) {
            // we do nothing
        } else if (state.get(FADING) == 1) {
            world.scheduleBlockTick(pos, this, 40);
        } else {
            world.scheduleBlockTick(pos, this, 10);
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FADING);
    }
}
