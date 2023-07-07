package cn.ichiyo.moreenchantments.Biome;

import cn.ichiyo.moreenchantments.Blocks.ModBlocks;
import cn.ichiyo.moreenchantments.MoreEnchantments;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;


public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> HEALTH_BOX_ORE_KEY = registryKey("health_box_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneRecplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateRecplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldHealthBoxOres =
                List.of(OreFeatureConfig.createTarget(stoneRecplacables, ModBlocks.HEALTH_BOX_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateRecplacables, ModBlocks.HEALTH_BOX_ORE.getDefaultState()));

        register(context, HEALTH_BOX_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldHealthBoxOres, 12));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MoreEnchantments.MOD_ID, name));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                  RegistryKey<ConfiguredFeature<?, ?>>key,
                                                                                  F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
