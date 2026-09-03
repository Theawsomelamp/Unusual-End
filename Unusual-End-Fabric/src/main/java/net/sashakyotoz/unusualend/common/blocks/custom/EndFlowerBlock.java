package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.sashakyotoz.unusualend.UnusualEnd;

public class EndFlowerBlock extends FlowerBlock {
    public EndFlowerBlock(Settings settings, StatusEffect effectInStew, int effectInStewDuration) {
        super(effectInStew, effectInStewDuration, settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.NYLIUM) || floor.isIn((TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils"))));
    }
}
