package cn.ichiyo.moreenchantments.Items;

import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ItemRegister {
    // public static final Item DOUBLE_JUMP_ITEM = new DoubleJumpEnchantmentBook(new FabricItemSettings());
    public static final Item DOUBLE_JUMP_ITEM = new DoubleJumpItem(new Item.Settings());
    public static final Item HEALTH_BOX = new HealthBox(new Item.Settings());

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier(MoreEnchantments.MOD_ID, "double_jump_stick"), DOUBLE_JUMP_ITEM);
        Registry.register(Registries.ITEM, new Identifier(MoreEnchantments.MOD_ID, "health_box"), HEALTH_BOX);
    }
}
