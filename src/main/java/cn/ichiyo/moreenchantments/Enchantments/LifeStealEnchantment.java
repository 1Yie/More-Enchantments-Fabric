package cn.ichiyo.moreenchantments.Enchantments;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Random;

public class LifeStealEnchantment extends Enchantment {
    protected LifeStealEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMaxPower(level) * 7;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        if (target instanceof LivingEntity && !target.getWorld().isClient) {
            DamageSource recentDamageSource = ((LivingEntity) target).getRecentDamageSource();
            if (recentDamageSource != null && recentDamageSource.getAttacker() instanceof PlayerEntity) {
                Random random = new Random();

                if (level == 1) {
                    float healAmount = (float) (0.5 + random.nextFloat(1.5f));
                    user.heal(healAmount);
                } else if (level == 2) {
                    float healAmount = (float) (1.0 + random.nextFloat(2.0f));
                    user.heal(healAmount);
                } else if (level == 3) {
                    float healAmount = (float) (1.5 + random.nextFloat(2.5f));
                    user.heal(healAmount);
                }

                // 幸运选择 Luck Time！
                if (level == 1) {
                    int isTrue = 1;
                    int tag = random.nextInt(100) + 1;

                    if (tag == isTrue) {
                        user.heal(10);
                        user.sendMessage(Text.literal("真理之选择！(恢复所有HP)").formatted(Formatting.DARK_RED));
                    }

                } else if (level == 2) {
                    int isTrue = 1;
                    int tag = random.nextInt(80) + 1;

                    if (tag == isTrue) {
                        user.heal(10);
                        user.sendMessage(Text.literal("真理之选择！(恢复所有HP)").formatted(Formatting.DARK_RED));
                    }

                } else if (level == 3) {
                    int isTrue = 1;
                    int tag = random.nextInt(60) + 1;

                    if (tag == isTrue) {
                        user.heal(10);
                        user.sendMessage(Text.literal("真理之选择！(恢复所有HP)").formatted(Formatting.DARK_RED));
                    }
                }
            }
        }
    }
}
