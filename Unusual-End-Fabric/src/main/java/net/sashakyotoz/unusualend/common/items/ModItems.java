package net.sashakyotoz.unusualend.common.items;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.data.client.Models;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.Rarity;
import net.sashakyotoz.unusualend.UnusualEnd;
import net.sashakyotoz.unusualend.common.ModRegistry;
import net.sashakyotoz.unusualend.common.effects.ModEffects;
import net.sashakyotoz.unusualend.common.items.custom.*;
import net.sashakyotoz.unusualend.common.misc.ModSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ModItems {
    public static void register() {
        UnusualEnd.log("Registering Items for modid : " + UnusualEnd.MOD_ID);
    }

    public static final EntityAttributeModifier PEARLESCENT_REACH = new EntityAttributeModifier(UUID.fromString("574188c2-554a-11ee-8c99-0242ac120002"), "Block Reach Modifier", 1.5D, EntityAttributeModifier.Operation.ADDITION);
    public static final EntityAttributeModifier PEARLESCENT_ATTACK_RANGE = new EntityAttributeModifier(UUID.fromString("e5c352ec-554a-11ee-8c99-0242ac120002"), "Attack Reach Modifier", 0.5D, EntityAttributeModifier.Operation.ADDITION);
    public static final EntityAttributeModifier BOUNCY_REACH = new EntityAttributeModifier(UUID.fromString("e5c352ec-554a-11ee-8c99-0242ac120002"), "Attack Reach Modifier", -0.5D, EntityAttributeModifier.Operation.ADDITION);
    public static final EntityAttributeModifier BOUNCY_KNOCKBACK = new EntityAttributeModifier(UUID.fromString("e56350e0-8756-464d-92f9-54289ab41e0a"), "Knockback Modifier", 2.0D, EntityAttributeModifier.Operation.ADDITION);

    public static List<Pair<EntityAttribute, EntityAttributeModifier>> getPearlescentAttributes() {
        List<Pair<EntityAttribute, EntityAttributeModifier>> attributes = new ArrayList<>();
        attributes.add(new Pair<>(ReachEntityAttributes.REACH, PEARLESCENT_REACH));
        attributes.add(new Pair<>(ReachEntityAttributes.ATTACK_RANGE, PEARLESCENT_ATTACK_RANGE));
        return attributes;
    }

    public static List<Pair<EntityAttribute, EntityAttributeModifier>> getBouncyAttributes() {
        List<Pair<EntityAttribute, EntityAttributeModifier>> attributes = new ArrayList<>();
        attributes.add(new Pair<>(ReachEntityAttributes.ATTACK_RANGE, BOUNCY_REACH));
        attributes.add(new Pair<>(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, BOUNCY_KNOCKBACK));
        return attributes;
    }


    public static final Item SHINY_CRYSTAL = ModRegistry.ofItem("shiny_crystal",
            new Item(new FabricItemSettings())).model(Models.GENERATED).tag(ItemTags.TRIM_MATERIALS, ItemTags.BEACON_PAYMENT_ITEMS, ConventionalItemTags.GEMS).build();
    public static final Item ENDER_FIREFLY_EGG = ModRegistry.ofItem("ender_firefly_egg",
            new FireflyEggItem(new FabricItemSettings().maxCount(16))).model(Models.GENERATED).build();
    public static final Item CHORUS_PETAL = ModRegistry.ofItem("chorus_petal",
            new UseTimeItem(new FabricItemSettings().food(new FoodComponent.Builder().hunger(1).saturationModifier(0.2F).alwaysEdible().build()), 16)).compostable(0.3f).model(Models.GENERATED).build();
    public static final Item CHORUS_PIE = ModRegistry.ofItem("chorus_pie",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(8).saturationModifier(0.6F).build()))).model(Models.GENERATED).compostable(1.0f).build();
    public static final Item CHORUS_JUICE = ModRegistry.ofItem("chorus_juice",
            new ChorusJuiceItem(new FabricItemSettings().maxCount(16).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.3F).alwaysEdible().build()))).model(Models.GENERATED).build();
    public static final Item CHORUS_TEA = ModRegistry.ofItem("chorus_tea",
            new ChorusTeaItem(new FabricItemSettings().maxCount(16).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.6F).alwaysEdible().build()))).model(Models.GENERATED).build();
    public static final Item ENDERBLOB_MOULT = ModRegistry.ofItem("enderblob_moult",
            new Item(new FabricItemSettings())).model(Models.GENERATED).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("blob_loot"))).build();
    public static final Item END_BLOB = ModRegistry.ofItem("end_blob",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().statusEffect(
                    new StatusEffectInstance(ModEffects.ENDER_INFECTION, 300, 0), 0.3F).hunger(2).saturationModifier(0.2F).alwaysEdible().build()))).model(Models.GENERATED).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("blob_loot"))).build();
    public static final Item END_BLOB_JELLY = ModRegistry.ofItem("end_blob_jelly",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.5F).alwaysEdible().build()))).model(Models.GENERATED).build();
    public static final Item BLOB_STEW = ModRegistry.ofItem("blob_stew",
            new StewItem(new FabricItemSettings().maxCount(16).food(new FoodComponent.Builder().statusEffect(
                    new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 0), 1.0F).hunger(6).saturationModifier(0.6F).alwaysEdible().build()))).model(Models.GENERATED).build();
    public static final Item ENDER_STEW = ModRegistry.ofItem("ender_stew",
            new StewItem(new FabricItemSettings().maxCount(16).food(new FoodComponent.Builder().hunger(7).saturationModifier(0.8F).build()))).model(Models.GENERATED).build();
    public static final Item GLOOPILON_SLICE = ModRegistry.ofItem("gloopilon_slice",
            new UseTimeItem(new FabricItemSettings().food(new FoodComponent.Builder().statusEffect(
                    new StatusEffectInstance(StatusEffects.HASTE, 200, 0), 1.0F).hunger(3).saturationModifier(0.3F).alwaysEdible().build()), 24)).model(Models.GENERATED).compostable(0.85f).build();
    public static final Item SHINY_GUM = ModRegistry.ofItem("shiny_gum",
            new UseTimeItem(new FabricItemSettings().food(new FoodComponent.Builder().statusEffect(
                    new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).hunger(1).saturationModifier(0.3F).alwaysEdible().build()), 48)).model(Models.GENERATED).build();
    public static final Item PRISMATIC_GUM = ModRegistry.ofItem("prismatic_gum",
            new UseTimeItem(new FabricItemSettings().food(new FoodComponent.Builder().statusEffect(
                    new StatusEffectInstance(StatusEffects.HASTE, 100, 1), 1.0F).statusEffect(
                    new StatusEffectInstance(StatusEffects.SPEED, 100, 1), 1.0F).hunger(1).saturationModifier(0.3F).alwaysEdible().build()), 48)).model(Models.GENERATED).build();
    public static final Item LURKER_SLUDGE = ModRegistry.ofItem("lurker_sludge",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().statusEffect(
                    new StatusEffectInstance(StatusEffects.LEVITATION, 200, 0), 1.0F).hunger(2).saturationModifier(0.3F).alwaysEdible().build()))).model(Models.GENERATED).build();
    public static final Item ENDERBULB_LENS = ModRegistry.ofItem("enderbulb_lens",
            new Item(new FabricItemSettings())).model(Models.GENERATED).build();
    public static final Item SHINY_CHARGE = ModRegistry.ofItem("shiny_charge",
            new ShinyGrenadeItem(new FabricItemSettings().maxCount(16))).model(Models.GENERATED).build();
    public static final Item WANDERING_PEARL = ModRegistry.ofItem("wandering_pearl",
            new WanderingPearlItem(new FabricItemSettings().maxCount(16))).model(Models.GENERATED).build();
    public static final Item PHANTOM_ARROW = ModRegistry.ofItem("phantom_arrow",
            new PhantomArrowItem(new FabricItemSettings())).tag(ItemTags.ARROWS).model(Models.GENERATED).build();
    public static final Item VOID_TOTEM = ModRegistry.ofItem("void_totem",
            new VoidTotemItem(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON))).model(Models.GENERATED).build();
    public static final Item SPECTRAL_SCRAP = ModRegistry.ofItem("spectral_scrap",
            new Item(new FabricItemSettings())).model(Models.GENERATED).build();
    public static final Item SPECTRAL_CLOTH = ModRegistry.ofItem("spectral_cloth",
            new SpectralClothItem(new FabricItemSettings().maxCount(16))).model(Models.GENERATED).build();
    public static final Item FLOATING_POUCH = ModRegistry.ofItem("floating_pouch",
            new FloatingPouchItem(new FabricItemSettings().maxCount(1).maxDamage(384))).model(Models.GENERATED).build();
    public static final Item SPECTRAL_LEGGINGS = ModRegistry.ofItem("spectral_leggings",
            new SpectralLeggingsItem(ModArmorMaterials.SPECTRAL_SCRAP, ArmorItem.Type.LEGGINGS,new FabricItemSettings())).armor().tag(ItemTags.TRIMMABLE_ARMOR).build();
    public static final Item CHORUS_HELMET = ModRegistry.ofItem("chorus_helmet",
            new ChorusHelmetItem(ModArmorMaterials.CHORUS, ArmorItem.Type.HELMET, new FabricItemSettings())).armor().build();
    public static final Item SPIRIT_SMILE_HELMET = ModRegistry.ofItem("spirit_smile_helmet",
            new MaskItem(ModArmorMaterials.SPIRIT, ArmorItem.Type.HELMET, new FabricItemSettings(), "Smile")).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("enderling_mask"))).build();
    public static final Item SPIRIT_MALICE_HELMET = ModRegistry.ofItem("spirit_malice_helmet",
            new MaskItem(ModArmorMaterials.SPIRIT, ArmorItem.Type.HELMET, new FabricItemSettings(), "Malice")).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("enderling_mask"))).build();
    public static final Item SPIRIT_GRIM_HELMET = ModRegistry.ofItem("spirit_grim_helmet",
            new MaskItem(ModArmorMaterials.SPIRIT, ArmorItem.Type.HELMET, new FabricItemSettings(), "Grim")).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("enderling_mask"))).build();
    public static final Item SPIRIT_MANIA_HELMET = ModRegistry.ofItem("spirit_mania_helmet",
            new MaskItem(ModArmorMaterials.SPIRIT, ArmorItem.Type.HELMET, new FabricItemSettings(), "Mania")).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("enderling_mask"))).build();
    public static final Item SPIRIT_VICE_HELMET = ModRegistry.ofItem("spirit_vice_helmet",
            new MaskItem(ModArmorMaterials.SPIRIT, ArmorItem.Type.HELMET, new FabricItemSettings(), "Vice")).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("enderling_mask"))).build();
    public static final Item SPIRIT_TWIST_HELMET = ModRegistry.ofItem("spirit_twist_helmet",
            new MaskItem(ModArmorMaterials.SPIRIT, ArmorItem.Type.HELMET, new FabricItemSettings(), "Twist")).tag(TagKey.of(RegistryKeys.ITEM, UnusualEnd.makeID("enderling_mask"))).build();
    public static final Item PRISMALITE_SHARD = ModRegistry.ofItem("prismalite_shard",
            new Item(new FabricItemSettings())).model(Models.GENERATED).tag(ConventionalItemTags.NUGGETS).build();
    public static final Item PRISMALITE_GEM = ModRegistry.ofItem("prismalite_gem",
            new Item(new FabricItemSettings())).model(Models.GENERATED).tag(ItemTags.TRIM_MATERIALS, ItemTags.BEACON_PAYMENT_ITEMS, ConventionalItemTags.GEMS).build();
    public static final Item PRISMATIC_MIRROR = ModRegistry.ofItem("prismatic_mirror",
            new PrismaticMirrorItem(new FabricItemSettings().maxCount(1).maxDamage(16).rarity(Rarity.UNCOMMON), 48)).model(Models.GENERATED).tag(ItemTags.TOOLS).build();
    public static final Item PEARLESCENT_INGOT = ModRegistry.ofItem("pearlescent_ingot",
            new Item(new FabricItemSettings())).model(Models.GENERATED).tag(ItemTags.TRIM_MATERIALS, ItemTags.BEACON_PAYMENT_ITEMS, ConventionalItemTags.INGOTS).build();
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
            new ModSwordItem(ModTiers.PEARLESCENT,3,-2.7f, new FabricItemSettings(), getPearlescentAttributes())).model(Models.HANDHELD).tag(ItemTags.SWORDS).build();
    public static final Item PEARLESCENT_PICKAXE = ModRegistry.ofItem("pearlescent_pickaxe",
            new ModPickaxeItem(ModTiers.PEARLESCENT,1,-2f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.PICKAXES).build();
    public static final Item PEARLESCENT_AXE = ModRegistry.ofItem("pearlescent_axe",
            new ModAxeItem(ModTiers.PEARLESCENT,5, -3.2f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.AXES).build();
    public static final Item PEARLESCENT_SHOVEL = ModRegistry.ofItem("pearlescent_shovel",
            new ModShovelItem(ModTiers.PEARLESCENT,1,-3f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.SHOVELS).build();
    public static final Item PEARLESCENT_HOE = ModRegistry.ofItem("pearlescent_hoe",
            new ModHoeItem(ModTiers.PEARLESCENT,0, -3f,new FabricItemSettings(), PEARLESCENT_REACH, PEARLESCENT_ATTACK_RANGE)).model(Models.HANDHELD).tag(ItemTags.HOES).build();
    public static final Item BOUNCY_DAGGER = ModRegistry.ofItem("bouncy_dagger",
            new ModSwordItem(ModTiers.BOUNCY,-1,-3f, new FabricItemSettings(), getBouncyAttributes())).model(Models.HANDHELD).tag(ItemTags.SWORDS).build();
    public static final Item ENDERBLOB_SHIELD = ModRegistry.ofItem("enderblob_shield",
            new BlobShieldItem(new FabricItemSettings().maxDamage(244))).tag(ConventionalItemTags.SHIELDS).build();
    public static final Item DISC_FRAGMENT_FLYING_SHIPS = ModRegistry.ofItem("disc_fragment_flying_ships",
            new DescriptiveItem(new FabricItemSettings())).model(Models.GENERATED).build();
    public static final Item MUSIC_DISC_FLYING_SHIPS = ModRegistry.ofItem("music_disc_flying_ships",
            new MusicDiscItem(3, ModSounds.MUSIC_DISC_FLYING_SHIPS, new FabricItemSettings().rarity(Rarity.RARE), 195)).model(Models.GENERATED).tag(ItemTags.MUSIC_DISCS).build();
    public static final Item MUSIC_DISC_QUEEN = ModRegistry.ofItem("music_disc_queen",
            new MusicDiscItem(0, ModSounds.MUSIC_DISC_QUEEN, new FabricItemSettings().rarity(Rarity.RARE), 190)).model(Models.GENERATED).tag(ItemTags.MUSIC_DISCS).build();
}