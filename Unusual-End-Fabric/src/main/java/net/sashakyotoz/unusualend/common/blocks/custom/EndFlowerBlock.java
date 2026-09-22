package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.sashakyotoz.unusualend.UnusualEnd;

public class EndFlowerBlock extends FlowerBlock {
    protected final float width;
    protected final float height;

    public EndFlowerBlock(Settings settings, StatusEffect effectInStew, int effectInStewDuration, float width, float height) {
        super(effectInStew, effectInStewDuration, settings);
        this.width = width;
        this.height = height;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        return createCuboidShape((8.0f - width), 0.0f, (8.0f - width), (8.0f + width), height, (8.0f + width)).offset(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.NYLIUM) || floor.isIn((TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils"))));
    }
}
