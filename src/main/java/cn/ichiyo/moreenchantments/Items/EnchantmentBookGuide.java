package cn.ichiyo.moreenchantments.Items;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;

public class EnchantmentBookGuide {
    public static void nbtR() {
        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK); // 创建附魔书的物品栈
        NbtCompound nbt = enchantedBook.getOrCreateSubNbt("Enchantments");
        nbt.putString("Introduction", "这本附魔书可以提供强力的攻击力增加效果。");
        enchantedBook.setSubNbt("Enchantments", nbt);
    }
}
