package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class InfestedEndstoneBlock extends Block {
    public InfestedEndstoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            if (!livingEntity.isSneaking() && !livingEntity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 3, 4, false, false));
                if (!world.getDifficulty().equals(Difficulty.PEACEFUL)) {
                    // TODO: Summon Endstone Trapper
                }
            }
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
