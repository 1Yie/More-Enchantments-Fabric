package cn.ichiyo.moreenchantments.Items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
            "MaxHealthIncreaseItem modifier",
            1.0F,
            EntityAttributeModifier.Operation.ADDITION
    );
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
        if (user != null) {
            EntityAttributeInstance attributeInstance = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
            if (attributeInstance != null && !attributeInstance.hasModifier(HEALTH_BOOST_MODIFIER)) {
                attributeInstance.addTemporaryModifier(HEALTH_BOOST_MODIFIER);
                return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
            }
        }
        return super.use(world, user, hand);
    }
}
