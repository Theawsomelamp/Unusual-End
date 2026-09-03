package net.sashakyotoz.unusualend.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.sashakyotoz.unusualend.UnusualEnd;

public class ChorusPlantGrowFeature extends Feature<DefaultFeatureConfig> {
    public ChorusPlantGrowFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        if (structureWorldAccess.getBlockState(blockPos).isReplaceable() && structureWorldAccess.getBlockState(blockPos.down()).isIn(TagKey.of(RegistryKeys.BLOCK, UnusualEnd.makeID("end_soils")))) {
            ChorusFlowerBlock.generate(structureWorldAccess, blockPos, random, 8);
            return true;
        } else {
            return false;
        }
    }
}
