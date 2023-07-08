package cn.ichiyo.moreenchantments.Enchantments;

import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;

import java.util.Collection;


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
            ItemStack heldItem = user.getMainHandStack();
            ToolItem toolItem = (ToolItem) heldItem.getItem();
            float attackDamage = toolItem.getMaterial().getAttackDamage() +
                    EnchantmentHelper.getAttackDamage(heldItem, EntityGroup.DEFAULT);

            EntityAttributeInstance attributeInstance = ((LivingEntity) target).getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

            if (attributeInstance != null) {
                armorValue = (float) attributeInstance.getValue();
                if (level == 1) {
                    float damageIncreasePercentage = attackDamage * armorValue * 0.10F;
                    target.damage(user.getDamageSources().mobAttack(user), damageIncreasePercentage);
                } else if (level == 2) {
                    float damageIncreasePercentage = attackDamage * armorValue * 0.11F;
                    target.damage(user.getDamageSources().mobAttack(user), damageIncreasePercentage);
                } else if (level == 3) {
                    float damageIncreasePercentage = attackDamage * armorValue * 0.12F;
                    target.damage(user.getDamageSources().mobAttack(user), damageIncreasePercentage);
                } else if (level == 4) {
                    float damageIncreasePercentage = attackDamage * armorValue * 0.13F;
                    target.damage(user.getDamageSources().mobAttack(user), damageIncreasePercentage);
                }
            }
        }
    }
}
