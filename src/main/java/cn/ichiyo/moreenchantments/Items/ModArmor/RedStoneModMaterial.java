package cn.ichiyo.moreenchantments.Items.ModArmor;

import cn.ichiyo.moreenchantments.Items.HealthBox;
import cn.ichiyo.moreenchantments.Items.ItemRegister;
import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.function.Supplier;

import static cn.ichiyo.moreenchantments.Items.ItemRegister.HEALTH_BOX;

public enum RedStoneModMaterial implements ArmorMaterial {
    HEALTH_BOX("health_box", 25, new int[]{4, 10, 7, 4}, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.2f, 5f, () -> Ingredient.ofItems(ItemRegister.HEALTH_BOX));


    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float knock;
    private final float toughness;
    private final Supplier<Ingredient> ingredientSupplier;
    private static final int[] BASE_DURABILITY = {14, 18, 19, 16};

    RedStoneModMaterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability,
                        SoundEvent equipSound, float knock, float toughness, Supplier<Ingredient> ingredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.knock = knock;
        this.toughness = toughness;
        this.ingredientSupplier = ingredientSupplier;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.ingredientSupplier.get();
    }

    @Override
    public String getName() {
        return MoreEnchantments.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knock;
    }

}
