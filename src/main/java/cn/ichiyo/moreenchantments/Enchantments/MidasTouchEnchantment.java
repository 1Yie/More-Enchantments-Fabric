package cn.ichiyo.moreenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class MidasTouchEnchantment extends Enchantment {
    protected MidasTouchEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }
    @Override
    public int getMinPower(int level) {
        return level;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinLevel() * 6;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof PickaxeItem;
    }
}
