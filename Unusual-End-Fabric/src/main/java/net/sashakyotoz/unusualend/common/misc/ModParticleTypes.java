package net.sashakyotoz.unusualend.common.misc;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.sashakyotoz.unusualend.UnusualEnd;

public class ModParticleTypes {
    public static final DefaultParticleType BOLOK_PARTICLE = register("bolok_particle", false);
    public static final DefaultParticleType PINK_FLAME = register("pink_flame", false);
    public static final DefaultParticleType WARPED_BUBBLES = register("warped_bubbles", false);
    public static final DefaultParticleType CITRINE_SHINE = register("citrine_shine", false);

    public static DefaultParticleType register(String name, boolean alwaysShow) {
        return Registry.register(Registries.PARTICLE_TYPE, UnusualEnd.makeID(name), FabricParticleTypes.simple(alwaysShow));
    }

    public static void register() {

    }
}
