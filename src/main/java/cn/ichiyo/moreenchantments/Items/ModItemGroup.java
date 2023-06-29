package cn.ichiyo.moreenchantments.Items;

import cn.ichiyo.moreenchantments.Enchantments.ArmorPenetrationEnchantment;
import cn.ichiyo.moreenchantments.Enchantments.ModEnchantments;
import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup ENCHANTMENTS = Registry.register(Registries.ITEM_GROUP, new Identifier(MoreEnchantments.MOD_ID,
            "item_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.enchantments"))
                    .icon(() -> new ItemStack(ItemRegister.DOUBLE_JUMP_ITEM))
                    .entries((displayContext, entries) -> {
                        entries.add(ItemRegister.DOUBLE_JUMP_ITEM);
                    })
                    .build());
    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
        });
    }
}
