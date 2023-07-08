package cn.ichiyo.moreenchantments;

import cn.ichiyo.moreenchantments.Biome.ModWorldGeneration;
import cn.ichiyo.moreenchantments.Enchantments.ModInitializer.DamageData;
import cn.ichiyo.moreenchantments.Enchantments.ModEnchantments;

import cn.ichiyo.moreenchantments.Items.ItemRegister;
import cn.ichiyo.moreenchantments.Items.ModItemGroup;
import cn.ichiyo.moreenchantments.Blocks.ModBlockRegister;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class MoreEnchantments implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("more-enchantments");
    public static final String MOD_ID = "more_enchantments";

    private static final Set<Block> DIAMOND_DROP_BLOCKS = new HashSet<>();

    @Override
    public void onInitialize() {

        ItemRegister.register();
        ModEnchantments.registerEnchantments();
        ModItemGroup.register();
        ModBlockRegister.register();
        ModWorldGeneration.generateModWorldGen();

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) entity;
                float damage = getAttackDamage(player);

                DamageData.setDamage(damage);
            }

            return ActionResult.PASS;
        });

        // iron
        DIAMOND_DROP_BLOCKS.add(Blocks.IRON_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_IRON_ORE);
        // coal
        DIAMOND_DROP_BLOCKS.add(Blocks.COAL_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_COAL_ORE);
        // gold
        DIAMOND_DROP_BLOCKS.add(Blocks.GOLD_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_GOLD_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.NETHER_GOLD_ORE);
        // diamond
        DIAMOND_DROP_BLOCKS.add(Blocks.DIAMOND_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_DIAMOND_ORE);
        // Copper
        DIAMOND_DROP_BLOCKS.add(Blocks.COPPER_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_COPPER_ORE);
        // Lapis Lazuli
        DIAMOND_DROP_BLOCKS.add(Blocks.LAPIS_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_LAPIS_ORE);
        // Emerald
        DIAMOND_DROP_BLOCKS.add(Blocks.EMERALD_ORE);
        DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_EMERALD_ORE);
        // Nether Quartz
        DIAMOND_DROP_BLOCKS.add(Blocks.NETHER_QUARTZ_ORE);
        // Ancient Debris
        DIAMOND_DROP_BLOCKS.add(Blocks.ANCIENT_DEBRIS);

        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            ItemStack mainHandStack = player.getMainHandStack();
            if (EnchantmentHelper.get(mainHandStack).containsKey(ModEnchantments.DIAMOND_LUCK)
                    && DIAMOND_DROP_BLOCKS.contains(state.getBlock())) {
                int enchantmentLevel = EnchantmentHelper.getLevel(ModEnchantments.DIAMOND_LUCK, mainHandStack);

                Random random = new Random();
                int isTrue = 1;
                int tag = random.nextInt(50 - (enchantmentLevel * 10)) + 1;
                if (isTrue == tag) {
                    dropDiamond(world, pos);
                }
            }
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((player, oldPlayer, alive) -> {
        });

        ServerPlayerEvents.COPY_FROM.register((original, cloned, lossless) -> {
        });



        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (player instanceof ServerPlayerEntity) {
                ItemStack itemStack = player.getStackInHand(hand);
                if (itemStack.getItem() == ItemRegister.HEALTH_BOX) {
                }
            }
            return TypedActionResult.pass(player.getStackInHand(hand));
        });
        ServerTickEvents.START_WORLD_TICK.register(world -> {

            List<ServerPlayerEntity> players = world.getPlayers();
            for (ServerPlayerEntity player : players) {
                updatePlayerHealth(player);
                break;
            }
        });
    }

    private static void updatePlayerHealth(ServerPlayerEntity player) {
        boolean isTrue = false;
        AtomicInteger enchantmentLevel = new AtomicInteger(0);
        double ADD_HEALTH = 0.0F;
        for (ItemStack stack : player.getInventory().armor) {
            if (stack.getItem() instanceof ArmorItem) {
                ArmorItem armorItem = (ArmorItem) stack.getItem();
                int level = EnchantmentHelper.getLevel(ModEnchantments.HEALTH_BOOST_ARMOR, stack);
                if (level > 0) {
                    isTrue = true;
                    ADD_HEALTH += 20.0F + (level * 2) * 2;
                    enchantmentLevel.set(level);
                }
            }
        }
        if (isTrue) {
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(ADD_HEALTH);
        } else {
            double defaultMaxHealth = 20.0F;
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(defaultMaxHealth);

            if (player.getHealth() > defaultMaxHealth) {
                player.setHealth((float) defaultMaxHealth);
            }
        }
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