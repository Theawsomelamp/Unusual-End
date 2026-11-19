package net.sashakyotoz.unusualend.common.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.entity.custom.PhantomArrowEntity;
import net.sashakyotoz.unusualend.common.entity.custom.ShinyGrenadeEntity;
import net.sashakyotoz.unusualend.common.entity.custom.WanderingPearlEntity;

public class ModEntities {
    public static final EntityType<WanderingPearlEntity> WANDERING_PEARL = Registry.register(Registries.ENTITY_TYPE, UnusualEnd.makeID("wandering_pearl"),
            FabricEntityTypeBuilder.<WanderingPearlEntity>create(SpawnGroup.MISC, WanderingPearlEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
    public static final EntityType<PhantomArrowEntity> PHANTOM_ARROW = Registry.register(Registries.ENTITY_TYPE, UnusualEnd.makeID("phantom_arrow"),
            FabricEntityTypeBuilder.<PhantomArrowEntity>create(SpawnGroup.MISC, PhantomArrowEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build());
    public static final EntityType<ShinyGrenadeEntity> SHINY_CHARGE = Registry.register(Registries.ENTITY_TYPE, UnusualEnd.makeID("bomb_flower"),
            FabricEntityTypeBuilder.<ShinyGrenadeEntity>create(SpawnGroup.MISC, ShinyGrenadeEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());

    public static void register() {
    }
}
