package cn.ichiyo.moreenchantments.Blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class ModBlocks {
    public static final Block HEALTH_BOX_ORE = new HealthBoxOre(FabricBlockSettings.create().hardness(4.0F));
}