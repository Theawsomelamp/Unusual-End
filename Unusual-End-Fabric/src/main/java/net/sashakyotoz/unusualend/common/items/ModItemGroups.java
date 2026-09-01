package net.sashakyotoz.unusualend.common.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.blocks.ModBlocks;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> UNUSUAL_END = RegistryKey.of(RegistryKeys.ITEM_GROUP, UnusualEnd.makeID("unusual_end"));

    public static void register() {
        UnusualEnd.log("Registering ItemGroups for modid : " + UnusualEnd.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, UNUSUAL_END, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.tabunusual_end"))
                .icon(() -> new ItemStack(ModBlocks.PEARLESCENT_BLOCK)).build());
    }
}