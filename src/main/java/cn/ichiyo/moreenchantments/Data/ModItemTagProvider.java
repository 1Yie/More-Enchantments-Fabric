package cn.ichiyo.moreenchantments.Data;

import cn.ichiyo.moreenchantments.Items.ModArmor.RegisterArmor;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(
                RegisterArmor.DARK_STORY_MATERIAL_HELMET,
                RegisterArmor.DARK_STORY_MATERIAL_CHESTPLATE,
                RegisterArmor.DARK_STORY_MATERIAL_LEGGINGS,
                RegisterArmor.DARK_STORY_MATERIAL_BOOTS
        );
    }
}
