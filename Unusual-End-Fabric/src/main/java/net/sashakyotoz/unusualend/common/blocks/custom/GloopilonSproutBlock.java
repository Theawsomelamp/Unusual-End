package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.sashakyotoz.unusualend.UnusualEnd;

public class GloopilonSproutBlock extends EndFlowerBlock implements Fertilizable {
    public static final IntProperty AGE = IntProperty.of("age", 0, 1);
    public static final BooleanProperty TIP = BooleanProperty.of("tip");

    public GloopilonSproutBlock(Settings settings, StatusEffect effectInStew, int effectInStewDuration, float width, float height) {
        super(settings, effectInStew, effectInStewDuration, width, height);
        this.setDefaultState((this.stateManager.getDefaultState()).with(AGE, 0).with(TIP, false));
    }

    @SuppressWarnings("deprecation")
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return this.canPlaceAt(world, pos);
    }

    private boolean canPlaceAt(BlockView world, BlockPos pos) {
        BlockPos blockPos = pos.offset(Direction.UP);
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isIn((TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils")))) || blockState.isOf(this.asBlock());
    }

    @SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
        }

        if (!state.get(TIP) && !world.getBlockState(pos.down()).isOf(this)) {
            state = state.with(AGE, 0);
        }

        return state;
    }

    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return this.canGrowInto(world.getBlockState(pos.down()));
    }

    private boolean canGrowInto(BlockState state) {
        return state.isAir();
    }


    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = state.get(AGE);
        if (i < 3 && random.nextFloat() < 0.01) {
            if (!state.get(TIP)) {
                if (this.canGrowInto(world.getBlockState(pos.down()))) {
                    world.setBlockState(pos.down(), state.with(TIP, true));
                }
            }

            BlockState blockState = state.with(AGE, i + 1);
            world.setBlockState(pos, blockState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(TIP);
    }
}
