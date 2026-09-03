package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.sashakyotoz.unusualend.UnusualEnd;

public class EndFungusBlock extends FungusBlock implements SuspiciousStewIngredient {
    private final StatusEffect effectInStew;
    private final int effectInStewDuration;

    public EndFungusBlock(Settings settings, RegistryKey<ConfiguredFeature<?, ?>> featureKey, Block nylium, StatusEffect effectInStew, int effectInStewDuration) {
        super(settings, featureKey, nylium);
        this.effectInStew = effectInStew;
        this.effectInStewDuration = effectInStewDuration;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.NYLIUM) || floor.isIn(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils")));
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        BlockState blockState = world.getBlockState(pos.down());
        return blockState.isIn(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils")));
    }

    @Override
    public StatusEffect getEffectInStew() {
        return this.effectInStew;
    }

    @Override
    public int getEffectInStewDuration() {
        return this.effectInStewDuration;
    }
}
