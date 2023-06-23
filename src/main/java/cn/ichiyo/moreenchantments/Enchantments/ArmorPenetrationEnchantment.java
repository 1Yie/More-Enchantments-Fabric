package cn.ichiyo.moreenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import net.minecraft.text.Text;


import java.util.Collection;
import java.util.UUID;


public class ArmorPenetrationEnchantment extends Enchantment {

    protected ArmorPenetrationEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinLevel() * 5;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (user instanceof ServerPlayerEntity) {

            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
            ServerWorld serverWorld = serverPlayer.getServerWorld();

            // 使用 serverWorld 获取玩家实例
            PlayerEntity targetPlayer = serverWorld.getPlayerByUuid(UUID.fromString("160be752-3a68-43b9-9c39-35141b966280"));

            if (target instanceof LivingEntity) {
                // 获取玩家实例的护甲值
                float armorValue = 0.0f;
                assert targetPlayer != null;

                ItemStack heldItem = user.getMainHandStack();
                ToolItem toolItem = (ToolItem) heldItem.getItem();
                float attackDamage = toolItem.getMaterial().getAttackDamage() +
                        EnchantmentHelper.getAttackDamage(heldItem, EntityGroup.DEFAULT);

                EntityAttributeInstance attributeInstance = ((LivingEntity) target)
                        .getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

                if (attributeInstance != null) {
                    armorValue = (float) attributeInstance.getValue();
                    float damageAkkackDamage = attackDamage * 0.1F;
                    if (armorValue > 8.0F) {

                        if (level == 1) {
                            float damageIncreasePercentage = damageAkkackDamage * armorValue * 0.10F;
                            float newHealth = ((LivingEntity) target).getHealth() - damageIncreasePercentage;
                            ((LivingEntity) target).setHealth(newHealth);
                        } else if (level == 2) {
                            float damageIncreasePercentage = damageAkkackDamage * armorValue * 0.15F;
                            float newHealth = ((LivingEntity) target).getHealth() - damageIncreasePercentage;
                            ((LivingEntity) target).setHealth(newHealth);
                        } else if (level == 3) {
                            float damageIncreasePercentage = damageAkkackDamage * armorValue * 0.20F;
                            float newHealth = ((LivingEntity) target).getHealth() - damageIncreasePercentage;
                            ((LivingEntity) target).setHealth(newHealth);
                        } else if (level == 4) {
                            float damageIncreasePercentage = damageAkkackDamage * armorValue * 0.25F;
                            float newHealth = ((LivingEntity) target).getHealth() - damageIncreasePercentage;
                            ((LivingEntity) target).setHealth(newHealth);
                        }

                    }

                }

                boolean deadFlag;
                boolean dead = ((LivingEntity) target).isDead();

                if (((LivingEntity) target).getHealth() <= 0.1F) {
                    deadFlag = true;

                    if (target instanceof PlayerEntity) {
                        PlayerEntity playerDeath = (PlayerEntity) target;
                        ItemStack totemItem = playerDeath.getInventory().getMainHandStack();

                        if (deadFlag && totemItem.getItem() == Items.TOTEM_OF_UNDYING) {
                            ((PlayerEntity) target).setHealth(1.0F);
                            // 触发不死图腾效果
                            playerDeath.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,
                                    200, 5));
                            playerDeath.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,
                                    200, 1));

                            totemItem.decrement(1);
                        }
                        if (dead && ((PlayerEntity) target).getHealth() <= 0.0F) {
                            ServerPlayerEntity player = (ServerPlayerEntity) user;
                            String deathMessageContent = "虽然%s叠的甲很厚，但是被%s的真伤击穿了！";
                            String formattedDeathMessage = String.format(deathMessageContent, target.getName().getString(), player.getName().getString());
                            Text deathMessage = Text.of(formattedDeathMessage);

                            MinecraftServer server = player.getServer();

                            if (server != null) {
                                Collection<ServerPlayerEntity> onlinePlayers = server.getPlayerManager().getPlayerList();
                                for (ServerPlayerEntity onlinePlayer : onlinePlayers) {
                                    onlinePlayer.sendMessage(deathMessage, false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}