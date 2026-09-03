package net.sashakyotoz.unusualend.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.sashakyotoz.unusualend.UnusualEnd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.ConnectingBlock.FACING_PROPERTIES;

@Mixin(ChorusPlantBlock.class)
public class ChorusPlantBlockMixin {

    @Inject(method = "canPlaceAt", at = @At("RETURN"), cancellable = true)
    private void expandPlacement(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.down()).isIn(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils"))))
            cir.setReturnValue(true);
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("RETURN"), cancellable = true)
    private void updateShape(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (world.getBlockState(pos.down()).isIn(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils")))) {
            cir.setReturnValue(state.with(FACING_PROPERTIES.get(direction), neighborState.isOf((ChorusPlantBlock) (Object) this) || neighborState.isOf(Blocks.CHORUS_FLOWER)).with(FACING_PROPERTIES.get(Direction.DOWN), true));
        }
    }

    @Inject(method = "withConnectionProperties", at = @At("RETURN"), cancellable = true)
    private void updateConnectionProperties(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        if (world.getBlockState(pos.down()).isIn(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils")))) {
            cir.setReturnValue(cir.getReturnValue().with(FACING_PROPERTIES.get(Direction.DOWN), true));
        }
    }
}
