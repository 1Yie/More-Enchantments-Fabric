package cn.ichiyo.moreenchantments.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Random;

public class SaturationEnchantment extends Enchantment {
    protected SaturationEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }


    @Override
    public int getMinPower(int level) {
        return level;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinLevel() * 3;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            // 恢复玩家的饱食度
            Random random = new Random();
            if (level == 1) {
                int isTure = 1;
                int tag = random.nextInt(5) + 1;
                if (tag == isTure) {
                    HungerManager hungerManager = player.getHungerManager();
                    hungerManager.add(1, 0.5f);
                }
            }
        }
    }
}
