package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SideShapeType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HealingLanternBlock extends WaterloggableWallMountedBlock {
    public HealingLanternBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> list, TooltipContext options) {
        super.appendTooltip(stack, world, list, options);
        list.add(Text.translatable("lore.unusualend.when_nearby").formatted(Formatting.GRAY));
        list.add(Text.literal(Text.translatable("effect.minecraft.regeneration").getString() + " (0:05)").formatted(Formatting.BLUE));
        list.add(Text.translatable("lore.unusualend.place_side").formatted(Formatting.DARK_GRAY));
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
                case FLOOR -> VoxelShapes.union(createCuboidShape(6, 7, 6, 10, 9, 10), createCuboidShape(5, 0, 5, 11, 7, 11));
                case WALL -> VoxelShapes.union(createCuboidShape(6, 8, 7, 10, 10, 11), createCuboidShape(5, 1, 6, 11, 8, 12));
                case CEILING -> VoxelShapes.union(createCuboidShape(6, 8, 6, 10, 10, 10), createCuboidShape(5, 1, 5, 11, 8, 11));
            };
            case EAST -> switch (state.get(FACE)) {
                case FLOOR -> VoxelShapes.union(createCuboidShape(6, 7, 6, 10, 9, 10), createCuboidShape(5, 0, 5, 11, 7, 11));
                case WALL -> VoxelShapes.union(createCuboidShape(5, 8, 6, 9, 10, 10), createCuboidShape(4, 1, 5, 10, 8, 11));
                case CEILING -> VoxelShapes.union(createCuboidShape(6, 8, 6, 10, 10, 10), createCuboidShape(5, 1, 5, 11, 8, 11));
            };
            case WEST -> switch (state.get(FACE)) {
                case FLOOR -> VoxelShapes.union(createCuboidShape(6, 7, 6, 10, 9, 10), createCuboidShape(5, 0, 5, 11, 7, 11));
                case WALL -> VoxelShapes.union(createCuboidShape(7, 8, 6, 11, 10, 10), createCuboidShape(6, 1, 5, 12, 8, 11));
                case CEILING -> VoxelShapes.union(createCuboidShape(6, 8, 6, 10, 10, 10), createCuboidShape(5, 1, 5, 11, 8, 11));
            };
            default -> switch (state.get(FACE)) {
                case FLOOR -> VoxelShapes.union(createCuboidShape(6, 7, 6, 10, 9, 10), createCuboidShape(5, 0, 5, 11, 7, 11));
                case WALL -> VoxelShapes.union(createCuboidShape(6, 8, 5, 10, 10, 9), createCuboidShape(5, 1, 4, 11, 8, 10));
                case CEILING -> VoxelShapes.union(createCuboidShape(6, 8, 6, 10, 10, 10), createCuboidShape(5, 1, 5, 11, 8, 11));
            };
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);

        if (!world.isClient) {
            List<PlayerEntity> players = world.getEntitiesByClass(PlayerEntity.class, Box.of(pos.toCenterPos(), 6, 6, 6), (player) -> true);
            for (PlayerEntity player : players) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0, false, false));
            }
        }
    }
}
