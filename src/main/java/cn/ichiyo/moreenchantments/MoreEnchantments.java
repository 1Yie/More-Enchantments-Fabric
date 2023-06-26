package cn.ichiyo.moreenchantments;

import cn.ichiyo.moreenchantments.Enchantments.DiamondLuckEnchantment;
import cn.ichiyo.moreenchantments.Enchantments.ModInitializer.DamageData;
import cn.ichiyo.moreenchantments.Enchantments.ModEnchantments;

import cn.ichiyo.moreenchantments.Items.ItemRegister;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class MoreEnchantments implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("more-enchantments");
	public static final String MOD_ID = "more_enchantments";

	@Override
	public void onInitialize() {

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof LivingEntity) {
				LivingEntity target = (LivingEntity) entity;
				float damage = getAttackDamage(player);

				DamageData.setDamage(damage);
			}

			return ActionResult.PASS;
		});

		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			ItemStack mainHandStack = player.getMainHandStack();
			if (EnchantmentHelper.get(mainHandStack).containsKey(ModEnchantments.DIAMOND_LUCK)) {
				dropDiamond(world, pos);
			}
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((player, oldPlayer, alive) -> {
		});

		ServerPlayerEvents.COPY_FROM.register((original, cloned, lossless) -> {
		});

		ItemRegister.register();
		ModEnchantments.registerEnchantments();
	}

	private static void dropDiamond(World world, BlockPos pos) {
		ItemStack diamondStack = new ItemStack(Items.DIAMOND);
		net.minecraft.entity.ItemEntity itemEntity = new net.minecraft.entity.ItemEntity(world,
				pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, diamondStack);
		world.spawnEntity(itemEntity);
	}

	private float getAttackDamage(PlayerEntity player) {
		EntityAttributeInstance attributeInstance = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
		if (attributeInstance != null) {
			return (float) attributeInstance.getValue();
		}
		return 0.0F;
	}

	private void handlePlayerDamage(PlayerEntity player, LivingEntity target, float damage) {
	}
}