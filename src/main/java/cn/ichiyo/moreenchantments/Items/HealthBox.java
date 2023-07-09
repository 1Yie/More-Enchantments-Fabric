package cn.ichiyo.moreenchantments.Items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.TagCommand;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HealthBox extends Item {

    private static final EntityAttributeModifier HEALTH_BOOST_MODIFIER = new EntityAttributeModifier(
            "max_health_boost",
            1.0F,
            EntityAttributeModifier.Operation.ADDITION
    );

    private static final EntityAttribute MAX_HEALTH_ATTRIBUTE = EntityAttributes.GENERIC_MAX_HEALTH;
    public HealthBox(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.more_enchantments.health_box.tooltip_1").formatted(Formatting.DARK_GRAY));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            AttributeContainer attributes = user.getAttributes();
            EntityAttributeInstance maxHealthAttribute = attributes.getCustomInstance(MAX_HEALTH_ATTRIBUTE);

            if (maxHealthAttribute != null) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,3, 8, false,false));
                ItemStack heldItem = user.getStackInHand(hand);
                heldItem.decrement(1);
                user.getItemCooldownManager().set(this, 10);
            }
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
