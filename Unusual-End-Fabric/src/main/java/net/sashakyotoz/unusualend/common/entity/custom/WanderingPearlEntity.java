package net.sashakyotoz.unusualend.common.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.common.items.ModItems;
import net.sashakyotoz.unusualend.common.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

public class WanderingPearlEntity extends ThrownItemEntity {
    public WanderingPearlEntity(EntityType<? extends WanderingPearlEntity> entityType, World world) {
        super(entityType, world);
    }

    public WanderingPearlEntity(World world, LivingEntity owner) {
        super(ModEntities.WANDERING_PEARL, owner, world);
    }

    public WanderingPearlEntity(World world, double x, double y, double z) {
        super(ModEntities.WANDERING_PEARL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return ModItems.WANDERING_PEARL;
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 0.0F);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        for(int i = 0; i < 32; ++i) {
            this.getWorld().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * (double)2.0F, this.getZ(), this.random.nextGaussian(), (double)0.0F, this.random.nextGaussian());
        }

        if (!this.getWorld().isClient && !this.isRemoved()) {
            Entity entity = this.getOwner();
            World world = this.getWorld();
            if (entity instanceof LivingEntity livingEntity) {
                for(int i = 0; i < 16; ++i) {
                    double g = this.getX() + (livingEntity.getRandom().nextDouble() - (double)0.5F) * (double)3.0F;
                    double h = MathHelper.clamp(this.getY() + (double)(livingEntity.getRandom().nextInt(2) - 1), world.getBottomY(), world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1);
                    double j = this.getZ() + (livingEntity.getRandom().nextDouble() - (double)0.5F) * (double)3.0F;
                    if (livingEntity.hasVehicle()) {
                        livingEntity.stopRiding();
                    }
                    livingEntity.teleport(g, h, j, true);
                }
            } else if (entity != null) {
                entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                entity.onLanding();
            }

            this.discard();
        }

    }

    public void tick() {
        Entity entity = this.getOwner();
        if (entity instanceof PlayerEntity && !entity.isAlive()) {
            this.discard();
        } else {
            super.tick();
        }

    }

    @Nullable
    public Entity moveToWorld(ServerWorld destination) {
        Entity entity = this.getOwner();
        if (entity != null && entity.getWorld().getRegistryKey() != destination.getRegistryKey()) {
            this.setOwner(null);
        }

        return super.moveToWorld(destination);
    }
}
