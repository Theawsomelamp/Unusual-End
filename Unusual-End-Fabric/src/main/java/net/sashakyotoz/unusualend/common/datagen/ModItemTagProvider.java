package net.sashakyotoz.unusualend.common.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;
import net.sashakyotoz.unusualend.common.items.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        for (TagKey<Item> tag : ModRegistry.ITEM_TAGS.keySet()) {
            FabricTagProvider<Item>.FabricTagBuilder builder = getOrCreateTagBuilder(tag);
            for (Item item : ModRegistry.ITEM_TAGS.get(tag)) {
                builder.add(item);
            }
        }

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("chorus_flowers"))).add(
                Blocks.CHORUS_FLOWER.asItem(),
                ModBlocks.CHORUS_CANE_FLOWER.asItem()
        );

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "eggs"))).add(
                Items.EGG,
                Items.TURTLE_EGG,
                ModItems.ENDER_FIREFLY_EGG
        );

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "ender_pearls"))).add(
                Items.ENDER_PEARL,
                ModItems.WANDERING_PEARL
        );
    }
}