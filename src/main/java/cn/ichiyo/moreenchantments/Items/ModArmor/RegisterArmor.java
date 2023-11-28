package cn.ichiyo.moreenchantments.Items.ModArmor;

import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterArmor {

    public static final Item DARK_STORY_MATERIAL_HELMET = new ArmorItem(RedStoneModMaterial.HEALTH_BOX, ArmorItem.Type.HELMET, new FabricItemSettings());
    public static final Item DARK_STORY_MATERIAL_CHESTPLATE = new ArmorItem(RedStoneModMaterial.HEALTH_BOX, ArmorItem.Type.CHESTPLATE, new FabricItemSettings());
    public static final Item DARK_STORY_MATERIAL_LEGGINGS = new ArmorItem(RedStoneModMaterial.HEALTH_BOX, ArmorItem.Type.LEGGINGS, new FabricItemSettings());
    public static final Item DARK_STORY_MATERIAL_BOOTS = new ArmorItem(RedStoneModMaterial.HEALTH_BOX, ArmorItem.Type.BOOTS, new FabricItemSettings());

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(MoreEnchantments.MOD_ID, "dark_story_helmet"), DARK_STORY_MATERIAL_HELMET);
        Registry.register(Registries.ITEM, new Identifier(MoreEnchantments.MOD_ID, "dark_story_chestplate"), DARK_STORY_MATERIAL_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier(MoreEnchantments.MOD_ID, "dark_story_leggings"), DARK_STORY_MATERIAL_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier(MoreEnchantments.MOD_ID, "dark_story_boots"), DARK_STORY_MATERIAL_BOOTS);

    }
}