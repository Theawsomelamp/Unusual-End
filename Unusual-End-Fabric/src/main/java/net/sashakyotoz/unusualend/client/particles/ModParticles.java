package net.sashakyotoz.unusualend.client.particles;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.sashakyotoz.unusualend.common.misc.ModParticleTypes;

public class ModParticles {

    public static void register() {
        ParticleFactoryRegistry particleRegistry = ParticleFactoryRegistry.getInstance();

        particleRegistry.register(ModParticleTypes.PINK_FLAME, FlameParticle.Factory::new);
    }
}
