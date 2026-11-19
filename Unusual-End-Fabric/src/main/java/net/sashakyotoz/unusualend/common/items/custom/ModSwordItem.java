package net.sashakyotoz.unusualend.common.items.custom;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Pair;

import java.util.List;

public class ModSwordItem extends SwordItem {
    private final List<Pair<EntityAttribute, EntityAttributeModifier>> attributes;

    public ModSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, List<Pair<EntityAttribute, EntityAttributeModifier>> attributes) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.attributes = attributes;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = LinkedHashMultimap.create(super.getAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            for (Pair<EntityAttribute, EntityAttributeModifier> attribute : attributes) {
                map.put(attribute.getLeft(), attribute.getRight());
            }
        }
        return map;
    }
}
