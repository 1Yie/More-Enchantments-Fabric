package cn.ichiyo.moreenchantments.Items;

import cn.ichiyo.moreenchantments.Blocks.ModBlockRegister;
import cn.ichiyo.moreenchantments.Blocks.ModBlocks;
import cn.ichiyo.moreenchantments.Items.ModArmor.RegisterArmor;
import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
                        entries.add(ItemRegister.HEALTH_BOX);
                        entries.add(ModBlocks.HEALTH_BOX_ORE);

                        entries.add(RegisterArmor.DARK_STORY_MATERIAL_HELMET);
                        entries.add(RegisterArmor.DARK_STORY_MATERIAL_CHESTPLATE);
                        entries.add(RegisterArmor.DARK_STORY_MATERIAL_LEGGINGS);
                        entries.add(RegisterArmor.DARK_STORY_MATERIAL_BOOTS);
                    })
                    .build());
    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
        });
    }
}
