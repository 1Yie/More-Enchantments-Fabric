package cn.ichiyo.moreenchantments.Enchantments;


import cn.ichiyo.moreenchantments.Enchantments.ModInitializer.DamageData;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class LeechEnchantment extends Enchantment {

    protected LeechEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMaxPower(level) * 7;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if (other instanceof LifeStealEnchantment) {
            return false;
        }
        return super.canAccept(other);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        float damage = DamageData.getDamage();
        float healAmount = damage * level * 0.1f;
        user.heal(healAmount);
    }
}
