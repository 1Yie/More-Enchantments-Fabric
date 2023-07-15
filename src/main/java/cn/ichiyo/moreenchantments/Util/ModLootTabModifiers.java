package cn.ichiyo.moreenchantments.Util;


import cn.ichiyo.moreenchantments.Items.ItemRegister;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTabModifiers {
    private static final Identifier CHEST = new Identifier("minecraft", "chests/igloo_chest");

    public static void modLootTabModifiers() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1F))
                            .with(ItemEntry.builder(ItemRegister.DOUBLE_JUMP_ITEM))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 1.0F)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
