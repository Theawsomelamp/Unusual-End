package net.sashakyotoz.unusualend;

import net.fabricmc.api.ModInitializer;
import net.sashakyotoz.unusualend.common.UnusualEndDataGenerator;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;
import net.sashakyotoz.unusualend.common.config.UnusualEndConfig;
import net.sashakyotoz.unusualend.common.effects.ModEffects;
import net.sashakyotoz.unusualend.common.enchantments.ModEnchantments;
import net.minecraft.util.Identifier;
import net.sashakyotoz.unusualend.common.items.ModItemGroups;
import net.sashakyotoz.unusualend.common.items.ModItems;
import net.sashakyotoz.unusualend.common.misc.ModParticleTypes;
import net.sashakyotoz.unusualend.common.world.ModFeatures;
import net.sashakyotoz.unusualend.common.entity.ModEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class UnusualEnd implements ModInitializer {
    public static final String MOD_ID = "unusualend";
    public static final Logger LOGGER = LoggerFactory.getLogger("Unusual End");
    public static final UnusualEndConfig CONFIG = UnusualEndConfig.createToml(Paths.get("config"), "", "unusual_end", UnusualEndConfig.class);

    @Override
    public void onInitialize() {
        ModItems.register();
        ModBlocks.register();
        ModItemGroups.register();
        ModEntities.register();
        ModEffects.register();
        ModEnchantments.register();
        ModParticleTypes.register();
        ModFeatures.register();

        UnusualEndDataGenerator.registerBurnable();
        UnusualEndDataGenerator.registerFuels();
        UnusualEndDataGenerator.registerCompostable();
        UnusualEndDataGenerator.registerStripped();
    }

    public static Identifier makeID(String id) {
        return new Identifier(MOD_ID, id);
    }

    public static <T> T log(T message) {
        LOGGER.info(String.valueOf(message));
        return message;
    }
}