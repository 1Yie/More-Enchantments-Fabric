package cn.ichiyo.moreenchantments.Blocks;

import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockRegister {
    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier(MoreEnchantments.MOD_ID, "health_box_ore"), ModBlocks.HEALTH_BOX_ORE);
        Registry.register(Registries.ITEM, new Identifier(MoreEnchantments.MOD_ID, "health_box_ore"), new BlockItem(ModBlocks.HEALTH_BOX_ORE,
                new FabricItemSettings()));
    }
}
