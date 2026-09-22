package net.sashakyotoz.unusualend.common.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.sashakyotoz.unusualend.common.entity.ModEntities;
import net.sashakyotoz.unusualend.common.items.ModItems;

public class PhantomArrowEntity extends PersistentProjectileEntity {
    private int age;

    public PhantomArrowEntity(EntityType<? extends PhantomArrowEntity> type, World world) {
        super(type, world);
    }

    public PhantomArrowEntity(World world, LivingEntity owner) {
        super(ModEntities.PHANTOM_ARROW, owner, world);
    }

    public PhantomArrowEntity(World world, double x, double y, double z) {
        super(ModEntities.PHANTOM_ARROW, x, y, z, world);
    }

    public void initFromStack() {
        this.setNoGravity(true);
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.age;
        if (this.age >= 80) {
            this.setNoGravity(false);
        }

        if (this.age >= 240) {
            for (int i = 0; i < 20; ++i) {
                getWorld().addParticle(ParticleTypes.FIREWORK, this.getParticleX(this.getWidth()), this.getRandomBodyY(), this.getParticleZ(this.getWidth()), 0.0f, 0.0f, 0.0f);
            }
            this.discard();
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.PHANTOM_ARROW);
    }
}
