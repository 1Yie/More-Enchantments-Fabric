package cn.ichiyo.moreenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;


public class DisarmEnchantment extends Enchantment {
    protected DisarmEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinLevel() * 8;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (user instanceof PlayerEntity && target instanceof LivingEntity targetEntity && !(target instanceof PlayerEntity)) {
            ItemStack heldItem = targetEntity.getMainHandStack();

            if (!heldItem.isEmpty()) {
                ItemEntity itemEntity = new ItemEntity(targetEntity.getWorld(),
                        targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(), heldItem.copy());
                targetEntity.getWorld().spawnEntity(itemEntity);

                if (!(target instanceof VillagerEntity)){
                    heldItem.setCount(0);
                }
            }
        }
    }
}
