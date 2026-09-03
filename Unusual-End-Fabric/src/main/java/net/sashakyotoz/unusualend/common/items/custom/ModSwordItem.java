package net.sashakyotoz.unusualend.common.items.custom;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ModSwordItem extends SwordItem {
    private final EntityAttributeModifier reach;
    private final EntityAttributeModifier attackRange;

    public ModSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, EntityAttributeModifier reach, EntityAttributeModifier attackRange) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.reach = reach;
        this.attackRange = attackRange;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = LinkedHashMultimap.create(super.getAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND) {
            map.put(ReachEntityAttributes.REACH, reach);
            map.put(ReachEntityAttributes.ATTACK_RANGE, attackRange);
        }
        return map;
    }
}
