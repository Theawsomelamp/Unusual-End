package net.sashakyotoz.unusualend.common.world;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.world.feature.ChorusPlantGrowFeature;

public class ModFeatures {

    public static final Feature CHORUS_PLANT_GROW = registerFeature("chorus_plant_grow", new ChorusPlantGrowFeature(DefaultFeatureConfig.CODEC));

    private static Feature registerFeature(String name, Feature feature) {
        return Registry.register(Registries.FEATURE, UnusualEnd.makeID(name), feature);
    }

    public static void register() {
    }
}
