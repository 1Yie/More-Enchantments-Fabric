package cn.ichiyo.moreenchantments.Data;

import cn.ichiyo.moreenchantments.Blocks.ModBlocks;
import cn.ichiyo.moreenchantments.Items.HealthBox;
import cn.ichiyo.moreenchantments.Items.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTabGenerator extends FabricBlockLootTableProvider {
    public ModLootTabGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        oreDrops(ModBlocks.HEALTH_BOX_ORE, ItemRegister.HEALTH_BOX);
    }
}
