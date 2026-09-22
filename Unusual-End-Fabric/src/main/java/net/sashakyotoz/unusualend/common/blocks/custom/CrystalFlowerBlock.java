package net.sashakyotoz.unusualend.common.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.UnusualEnd;

import java.util.List;

public class CrystalFlowerBlock extends EndFlowerBlock {
    public CrystalFlowerBlock(Settings settings, StatusEffect effectInStew, int effectInStewDuration, float width, float height) {
        super(settings, effectInStew, effectInStewDuration, width, height);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.spawnParticles(ParticleTypes.WITCH, (pos.getX() + 0.5), (pos.getY() + 0.5), (pos.getZ() + 0.5), 3, 0.3f, 0.3f, 0.3f, 0.1f);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, new Box(pos, pos).expand(5), livingEntity -> true);
        for (LivingEntity entity : entities) {
            if (entity.getType().isIn(TagKey.of(RegistryKeys.ENTITY_TYPE, UnusualEnd.makeID("blobqueen_immune")))) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1));
            } else if (entity instanceof PlayerEntity) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0));
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (entity.getType().isIn(TagKey.of(RegistryKeys.ENTITY_TYPE, UnusualEnd.makeID("blobqueen_immune")))) return;
        if (entity instanceof LivingEntity livingEntity && UnusualEnd.CONFIG.crystalFlowerNausea) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 0));
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (player.getType().isIn(TagKey.of(RegistryKeys.ENTITY_TYPE, UnusualEnd.makeID("blobqueen_immune")))) return;
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 0));

    }
}
