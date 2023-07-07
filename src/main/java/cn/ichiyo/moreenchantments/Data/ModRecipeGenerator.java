package cn.ichiyo.moreenchantments.Data;

import cn.ichiyo.moreenchantments.Items.ItemRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, List.of(ItemRegister.HEALTH_BOX), RecipeCategory.MISC, ItemRegister.HEALTH_BOX,
                3f, 300, "health_box");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ItemRegister.DOUBLE_JUMP_ITEM)
                .pattern("SXS")
                .pattern(" T ")
                .pattern(" T ")
                .input('X', Items.GOLD_INGOT)
                .input('S', Items.PHANTOM_MEMBRANE)
                .input('T', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ItemRegister.DOUBLE_JUMP_ITEM)));
    }
}
