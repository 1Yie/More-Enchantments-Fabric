package cn.ichiyo.moreenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HealthBoostEnchantment extends Enchantment {

    protected HealthBoostEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        return super.isAcceptableItem(stack) && (item instanceof ArmorItem && ((ArmorItem) item).getSlotType() == EquipmentSlot.CHEST);
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
        return 5;
    }
}
