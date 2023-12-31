package cn.ichiyo.moreenchantments.Data;

import cn.ichiyo.moreenchantments.Blocks.ModBlocks;
import cn.ichiyo.moreenchantments.Items.ItemRegister;
import cn.ichiyo.moreenchantments.Items.ModArmor.RegisterArmor;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.HEALTH_BOX_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegister.HEALTH_BOX, Models.GENERATED);
        itemModelGenerator.register(ItemRegister.DOUBLE_JUMP_ITEM, Models.HANDHELD);

        itemModelGenerator.register(RegisterArmor.DARK_STORY_MATERIAL_HELMET,Models.GENERATED);
        itemModelGenerator.register(RegisterArmor.DARK_STORY_MATERIAL_CHESTPLATE,Models.GENERATED);
        itemModelGenerator.register(RegisterArmor.DARK_STORY_MATERIAL_LEGGINGS,Models.GENERATED);
        itemModelGenerator.register(RegisterArmor.DARK_STORY_MATERIAL_BOOTS,Models.GENERATED);

        itemModelGenerator.registerArmor((ArmorItem) RegisterArmor.DARK_STORY_MATERIAL_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) RegisterArmor.DARK_STORY_MATERIAL_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) RegisterArmor.DARK_STORY_MATERIAL_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) RegisterArmor.DARK_STORY_MATERIAL_BOOTS);
    }
}
