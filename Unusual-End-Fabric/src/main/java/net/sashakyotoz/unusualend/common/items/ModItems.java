package net.sashakyotoz.unusualend.common.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.Models;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.items.custom.*;

import java.util.List;
import java.util.UUID;

public class ModItems {
    public static void register() {
        UnusualEnd.log("Registering Items for modid : " + UnusualEnd.MOD_ID);
    }

    public static final EntityAttributeModifier PEARLESCENT_REACH = new EntityAttributeModifier(UUID.fromString("574188c2-554a-11ee-8c99-0242ac120002"), "Block Reach Modifier", 1.5D, EntityAttributeModifier.Operation.ADDITION);
    public static final EntityAttributeModifier PEARLESCENT_ATTACK_RANGE = new EntityAttributeModifier(UUID.fromString("e5c352ec-554a-11ee-8c99-0242ac120002"), "Attack Reach Modifier", 0.5D, EntityAttributeModifier.Operation.ADDITION);

    public static final Item PEARLESCENT_INGOT = ModRegistry.ofItem("pearlescent_ingot",
            new Item(new FabricItemSettings())).model(Models.GENERATED).build();
    public static final Item PEARLESCENT_UPGRADE_SMITHING_TEMPLATE = ModRegistry.ofItem("pearlescent_upgrade_smithing_template",
            new SmithingTemplateItem(
                    Text.translatable("item.minecraft.smithing_template.netherite_upgrade.applies_to").formatted(Formatting.BLUE),
                    Text.translatable("item.unusualend.pearlescent_ingot").formatted(Formatting.BLUE),
                    Text.translatable("upgrade.unusualend.pearlescent_upgrade").formatted(Formatting.GRAY),
                    Text.translatable("item.minecraft.smithing_template.netherite_upgrade.base_slot_description").formatted(Formatting.BLUE),
                    Text.translatable("item.minecraft.smithing_template.netherite_upgrade.additions_slot_description").formatted(Formatting.BLUE),
                    SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
                    List.of(new Identifier("item/empty_slot_ingot")))).model(Models.GENERATED).build();
    public static final Item PEARLESCENT_SWORD = ModRegistry.ofItem("pearlescent_sword",
            new ModSwordItem(ModTiers.PEARLESCENT,3,-2.7f, new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.SWORDS).build();
    public static final Item PEARLESCENT_PICKAXE = ModRegistry.ofItem("pearlescent_pickaxe",
            new ModPickaxeItem(ModTiers.PEARLESCENT,1,-2f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.PICKAXES).build();
    public static final Item PEARLESCENT_AXE = ModRegistry.ofItem("pearlescent_axe",
            new ModAxeItem(ModTiers.PEARLESCENT,5, -3.2f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.AXES).build();
    public static final Item PEARLESCENT_SHOVEL = ModRegistry.ofItem("pearlescent_shovel",
            new ModShovelItem(ModTiers.PEARLESCENT,1,-3f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.SHOVELS).build();
    public static final Item PEARLESCENT_HOE = ModRegistry.ofItem("pearlescent_hoe",
            new ModHoeItem(ModTiers.PEARLESCENT,0, -3f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.HOES).build();
    public static final Item LURKER_SLUDGE = ModRegistry.ofItem("lurker_sludge",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().statusEffect(
                    new StatusEffectInstance(StatusEffects.LEVITATION, 200, 0), 1.0F).hunger(2).saturationModifier(0.3F).alwaysEdible().build()))).model(Models.GENERATED).build();
    public static final Item SPECTRAL_SCRAP = ModRegistry.ofItem("spectral_scrap",
            new Item(new FabricItemSettings())).model(Models.GENERATED).build();
    public static final Item SPECTRAL_CLOTH = ModRegistry.ofItem("spectral_cloth",
            new SpectralClothItem(new FabricItemSettings().maxCount(16))).model(Models.GENERATED).build();
    public static final Item FLOATING_POUCH = ModRegistry.ofItem("floating_pouch",
            new FloatingPouchItem(new FabricItemSettings().maxCount(1).maxDamage(384))).model(Models.GENERATED).build();
    public static final Item SPECTRAL_LEGGINGS = ModRegistry.ofItem("spectral_leggings",
            new SpectralLeggingsItem(ModArmorMaterials.SPECTRAL_SCRAP, ArmorItem.Type.LEGGINGS,new FabricItemSettings())).build();
}