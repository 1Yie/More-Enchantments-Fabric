package cn.ichiyo.moreenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;


public class SoulPenetrationEnchantment extends Enchantment {

    protected SoulPenetrationEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinLevel() * 5;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (user instanceof PlayerEntity && target instanceof LivingEntity) {

            float armorValue = 0.0F;
            LivingEntity targetEntity = (LivingEntity) target;
            ItemStack heldItem = user.getMainHandStack();
            ToolItem toolItem = (ToolItem) heldItem.getItem();
            float attackDamage = toolItem.getMaterial().getAttackDamage() +
                    EnchantmentHelper.getAttackDamage(heldItem, EntityGroup.DEFAULT);

            EntityAttributeInstance attributeInstance = targetEntity.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

            if (attributeInstance != null) {
                armorValue = (float) attributeInstance.getValue();
                float damageAttackDamage = attackDamage * 0.1F;
                float damageIncreasePercentage = 0.0F;

                switch (level) {
                    case 1:
                        damageIncreasePercentage = damageAttackDamage * armorValue * 0.1F;
                        break;
                    case 2:
                        damageIncreasePercentage = damageAttackDamage * armorValue * 0.15F;
                        break;
                    case 3:
                        damageIncreasePercentage = damageAttackDamage * armorValue * 0.2F;
                        break;
                    case 4:
                        damageIncreasePercentage = damageAttackDamage * armorValue * 0.25F;
                        break;
                }
                float newHealth = ((LivingEntity) target).getHealth() - damageIncreasePercentage;
                ((LivingEntity) target).setHealth(newHealth);
            }
        }
    }
}
