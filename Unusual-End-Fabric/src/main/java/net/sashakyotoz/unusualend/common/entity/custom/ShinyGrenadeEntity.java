package net.sashakyotoz.unusualend.common.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.common.entity.ModEntities;
import net.sashakyotoz.unusualend.common.items.ModItems;
import net.sashakyotoz.unusualend.common.misc.ModParticleTypes;

public class ShinyGrenadeEntity extends ThrownItemEntity {
    public ShinyGrenadeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ShinyGrenadeEntity(double d, double e, double f, World world) {
        super(ModEntities.SHINY_CHARGE, d, e, f, world);
    }

    public ShinyGrenadeEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.SHINY_CHARGE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SHINY_CHARGE;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient) {
            Entity entity = entityHitResult.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                if ((livingEntity.getGroup() == EntityGroup.UNDEAD) || (livingEntity.getGroup() == EntityGroup.ARTHROPOD)) {
                    livingEntity.damage(this.getWorld().getDamageSources().explosion(this, livingEntity), 25);
                } else if (livingEntity instanceof Monster) {
                    livingEntity.damage(this.getWorld().getDamageSources().explosion(this, livingEntity), 15);
                } else {
                    livingEntity.damage(this.getWorld().getDamageSources().explosion(this, livingEntity), 3);
                }
            }
        }
        super.onEntityHit(entityHitResult);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld() instanceof ServerWorld world) {
            world.spawnParticles(ModParticleTypes.PINK_FLAME, this.getX(), this.getY(), this.getZ(), 15, 0.8, 0.8, 0.8, 0.5);
            world.spawnParticles(ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), 15, 2.0, 2.0, 2.0, 0.1);
            world.createExplosion(null, this.getX(), this.getY(), this.getZ(), 0, false, World.ExplosionSourceType.NONE);
            world.sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }
}
