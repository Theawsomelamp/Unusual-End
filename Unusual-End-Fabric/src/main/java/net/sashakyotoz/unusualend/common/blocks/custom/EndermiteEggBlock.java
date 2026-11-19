package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class EndermiteEggBlock extends WaterloggableWallMountedBlock {
    public static final BooleanProperty CRACKED = BooleanProperty.of("cracked");

    public EndermiteEggBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(CRACKED, false));
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
        return switch (state.get(FACING)) {
            case NORTH -> switch (state.get(FACE)) {
                case FLOOR -> VoxelShapes.union(createCuboidShape(6, 0, 2, 12, 7, 8), createCuboidShape(2, 0, 5, 7, 4, 10), createCuboidShape(9, 0, 9, 14, 5, 14));
                case WALL -> VoxelShapes.union(createCuboidShape(6, 2, 9, 12, 8, 16), createCuboidShape(2, 5, 12, 7, 10, 16), createCuboidShape(9, 9, 11, 14, 14, 16));
                case CEILING -> VoxelShapes.union(createCuboidShape(4, 9, 2, 10, 16, 8), createCuboidShape(9, 12, 5, 14, 16, 10), createCuboidShape(2, 11, 9, 7, 16, 14));
            };
            case EAST -> switch (state.get(FACE)) {
                case FLOOR -> VoxelShapes.union(createCuboidShape(8, 0, 6, 14, 7, 12), createCuboidShape(6, 0, 2, 11, 4, 7), createCuboidShape(2, 0, 9, 7, 5, 14));
                case WALL -> VoxelShapes.union(createCuboidShape(0, 2, 6, 7, 8, 12), createCuboidShape(0, 5, 2, 4, 10, 7), createCuboidShape(0, 9, 9, 5, 14, 14));
                case CEILING -> VoxelShapes.union(createCuboidShape(8, 9, 4, 14, 16, 10), createCuboidShape(6, 12, 9, 11, 16, 14), createCuboidShape(2, 11, 2, 7, 16, 7));
            };
            case WEST -> switch (state.get(FACE)) {
                case FLOOR -> VoxelShapes.union(createCuboidShape(2, 0, 4, 8, 7, 10), createCuboidShape(5, 0, 9, 10, 4, 14), createCuboidShape(9, 0, 2, 14, 5, 7));
                case WALL -> VoxelShapes.union(createCuboidShape(9, 2, 4, 16, 8, 10), createCuboidShape(12, 5, 9, 16, 10, 14), createCuboidShape(11, 9, 2, 16, 14, 7));
                case CEILING -> VoxelShapes.union(createCuboidShape(2, 9, 6, 8, 16, 12), createCuboidShape(5, 12, 2, 10, 16, 7), createCuboidShape(9, 11, 9, 14, 16, 14));
            };
            default -> switch (state.get(FACE)) {
                case FLOOR -> VoxelShapes.union(createCuboidShape(4, 0, 8, 10, 7, 14), createCuboidShape(9, 0, 6, 14, 4, 11), createCuboidShape(2, 0, 2, 7, 5, 7));
                case WALL -> VoxelShapes.union(createCuboidShape(4, 2, 0, 10, 8, 7), createCuboidShape(9, 5, 0, 14, 10, 4), createCuboidShape(2, 9, 0, 7, 14, 5));
                case CEILING -> VoxelShapes.union(createCuboidShape(6, 9, 8, 12, 16, 14), createCuboidShape(2, 12, 6, 7, 16, 11), createCuboidShape(9, 11, 2, 14, 16, 7));
            };
        };
    }

    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean isPlayerNearby = !world.getEntitiesByClass(PlayerEntity.class, Box.of(pos.toCenterPos(), 8, 8, 8),
                (player) -> !player.getAbilities().creativeMode && !player.hasStatusEffect(StatusEffects.INVISIBILITY) && !player.isSneaking()).isEmpty();
        if (isPlayerNearby) {
            world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS);
            world.spawnParticles(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 20, 1.3, 1.3, 1.3, 0);
            world.spawnParticles(ParticleTypes.REVERSE_PORTAL, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 10, 1.3, 1.3, 1.3, 0);
            if (random.nextFloat() < 0.9f) {
                if (!state.get(CRACKED)) {
                    world.setBlockState(pos, state.with(CRACKED, true));
                } else {
                    world.breakBlock(pos, false);
                }
            }
            for (int i = 0; i < random.nextBetween(2, 3); ++i) {
                EntityType.ENDERMITE.spawn(world, pos, SpawnReason.TRIGGERED);
            }
            //Todo: add chance for enderbulb once entity exists
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CRACKED);
    }
}
