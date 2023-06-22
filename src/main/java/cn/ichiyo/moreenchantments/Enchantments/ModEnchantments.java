package cn.ichiyo.moreenchantments.Enchantments;

import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Identifier LIFE_STEAL_ID = new Identifier(MoreEnchantments.MOD_ID, "life_steal");
    public static final Identifier FROST_SLOWNESS_ID = new Identifier(MoreEnchantments.MOD_ID, "frost_slowness");
    public static final Identifier SATURATION_ID = new Identifier(MoreEnchantments.MOD_ID, "saturation");
    public static final Identifier ARMOR_PENETRATION_ID = new Identifier(MoreEnchantments.MOD_ID,"armor_penetration");
    public static final Identifier DISARM_ID = new Identifier(MoreEnchantments.MOD_ID, "disarm");


    public static final Enchantment LIFE_STEAL = new LifeStealEnchantment(Enchantment.Rarity.RARE,
            EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment FROST_SLOWNESS = new FrostSlownessEnchantment(Enchantment.Rarity.RARE,
            EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment SATURATION = new SaturationEnchantment(Enchantment.Rarity.RARE,
            EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment ARMOR_PENETRATION = new ArmorPenetrationEnchantment(Enchantment.Rarity.RARE,
            EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment DISARM = new DisarmEnchantment(Enchantment.Rarity.RARE,
            EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static void registerEnchantments() {
        Registry.register(Registries.ENCHANTMENT, LIFE_STEAL_ID, LIFE_STEAL);
        Registry.register(Registries.ENCHANTMENT, FROST_SLOWNESS_ID, FROST_SLOWNESS);
        Registry.register(Registries.ENCHANTMENT, SATURATION_ID, SATURATION);
        Registry.register(Registries.ENCHANTMENT, ARMOR_PENETRATION_ID, ARMOR_PENETRATION);
        Registry.register(Registries.ENCHANTMENT, DISARM_ID, DISARM);
    }
}
