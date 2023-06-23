package cn.ichiyo.moreenchantments;

import cn.ichiyo.moreenchantments.Enchantments.DamageModInitializer.DamageData;
import cn.ichiyo.moreenchantments.Enchantments.ModEnchantments;

import cn.ichiyo.moreenchantments.Items.ItemRegister;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		ItemRegister.register();
		ModEnchantments.registerEnchantments();
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