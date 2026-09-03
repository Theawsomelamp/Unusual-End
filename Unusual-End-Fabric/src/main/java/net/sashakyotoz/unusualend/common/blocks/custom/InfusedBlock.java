package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.UnusualEnd;

public class InfusedBlock extends Block {
    private final float damage;

    public InfusedBlock(Settings settings, float damage) {
        super(settings);
        this.damage = damage;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.bypassesSteppingEffects() && entity instanceof LivingEntity && !entity.getType().isIn(TagKey.of(Registries.ENTITY_TYPE.getKey(), UnusualEnd.makeID("infused_immunity")))) {
            entity.damage(world.getDamageSources().magic(), damage);
            world.addParticle(ParticleTypes.DRAGON_BREATH, entity.getX() + (world.random.nextDouble() - 0.5), entity.getY() + (world.random.nextDouble() - 0.5), entity.getZ() + (world.random.nextDouble() - 0.5), 0.0F, 0.0F, 0.0F);
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
