package cn.ichiyo.moreenchantments.Items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DoubleJumpItem extends Item {

    public DoubleJumpItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.more_enchantments.double_jump_stick.tooltip_1").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.more_enchantments.double_jump_stick.tooltip_2"));
        tooltip.add(Text.translatable("item.more_enchantments.double_jump_stick.tooltip_3").formatted(Formatting.DARK_GRAY));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.isOnGround() && !user.isFallFlying()) {
            user.setVelocity(user.getVelocity().add(0, 0.8, 0)); // 给予玩家向上的额外速度
            user.getItemCooldownManager().set(this, 8);

            return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected && entity instanceof PlayerEntity && !entity.isOnGround() && stack.getItem() == this) {
            PlayerEntity player = (PlayerEntity) entity;
            player.fallDistance = 0.0f; // 将玩家的坠落距离设置为0，免疫坠落伤害
        }
    }
}
