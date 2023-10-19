package cn.ichiyo.moreenchantments.Items.ModArmor.Custom;

import cn.ichiyo.moreenchantments.Items.ModArmor.RedStoneModMaterial;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ModArmorItem extends ArmorItem {


    private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                    .put(null, new StatusEffectInstance(StatusEffects.HASTE, 400, 1,
                            false, false, true)).build();

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }


}
