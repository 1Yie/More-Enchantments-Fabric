package cn.ichiyo.moreenchantments;

import cn.ichiyo.moreenchantments.Biome.ModWorldGeneration;
import cn.ichiyo.moreenchantments.Blocks.ModBlocks;
import cn.ichiyo.moreenchantments.Enchantments.Implementer.DamageData;
import cn.ichiyo.moreenchantments.Enchantments.ModEnchantments;

import cn.ichiyo.moreenchantments.Items.ItemRegister;
import cn.ichiyo.moreenchantments.Items.ModArmor.RegisterArmor;
import cn.ichiyo.moreenchantments.Items.ModItemGroup;
import cn.ichiyo.moreenchantments.Blocks.ModBlockRegister;
import cn.ichiyo.moreenchantments.Util.ModLootTabModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
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

    private static final Set<Block> MIDAS_TOUCH_BLOCKS = new HashSet<>();

    private static boolean canRun = false;

    @Override
    public void onInitialize() {

        ItemRegister.register();
        ModEnchantments.registerEnchantments();
        ModItemGroup.register();
        ModBlockRegister.register();
        ModWorldGeneration.generateModWorldGen();
        ModLootTabModifiers.modLootTabModifiers();
        RegisterArmor.register();


        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) entity;
                float damage = getAttackDamage(player);

                DamageData.setDamage(damage);
            }
            return ActionResult.PASS;
        });

        {
            // Iron
            DIAMOND_DROP_BLOCKS.add(Blocks.IRON_ORE);
            DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_IRON_ORE);
            // Coal
            DIAMOND_DROP_BLOCKS.add(Blocks.COAL_ORE);
            DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_COAL_ORE);
            // Gold
            DIAMOND_DROP_BLOCKS.add(Blocks.GOLD_ORE);
            DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_GOLD_ORE);
            DIAMOND_DROP_BLOCKS.add(Blocks.NETHER_GOLD_ORE);
            // Diamond
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
            // Redstone
            DIAMOND_DROP_BLOCKS.add(Blocks.REDSTONE_ORE);
            DIAMOND_DROP_BLOCKS.add(Blocks.DEEPSLATE_REDSTONE_ORE);

            // Mod Blocks
            DIAMOND_DROP_BLOCKS.add(ModBlocks.HEALTH_BOX_ORE);
        }

        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            ItemStack mainHandStack = player.getMainHandStack();

            if (EnchantmentHelper.get(mainHandStack).containsKey(ModEnchantments.DIAMOND_LUCK)
                    && DIAMOND_DROP_BLOCKS.contains(state.getBlock())) {
                int enchantmentLevel = EnchantmentHelper.getLevel(ModEnchantments.DIAMOND_LUCK, mainHandStack);

                int enchantLuckyLevel = EnchantmentHelper.getLevel(Enchantments.FORTUNE, mainHandStack);

                Random random = new Random();
                int isTrue = 1;
                int tag = random.nextInt(((50 - enchantLuckyLevel) - (enchantmentLevel * 10))) + 1;
                if (isTrue == tag) {
                    dropDiamond(world, pos);
                    int dropTag = random.nextInt((10 - (enchantLuckyLevel * 2))) + 1;
                    if (dropTag == isTrue && enchantLuckyLevel != 0) {
                        int luckyTag = random.nextInt((8 - (enchantLuckyLevel * 2))) + 1;
                        dropDiamond(world, pos);
                        if (luckyTag == isTrue) {
                            dropDiamond(world, pos);
                        }
                    }
                }
            }
        });

        {
            MIDAS_TOUCH_BLOCKS.add(Blocks.STONE);
            MIDAS_TOUCH_BLOCKS.add(Blocks.DEEPSLATE);
            MIDAS_TOUCH_BLOCKS.add(Blocks.GRANITE);
            MIDAS_TOUCH_BLOCKS.add(Blocks.DIORITE);
            MIDAS_TOUCH_BLOCKS.add(Blocks.ANDESITE);
            MIDAS_TOUCH_BLOCKS.add(Blocks.CALCITE);
            MIDAS_TOUCH_BLOCKS.add(Blocks.TUFF);
            MIDAS_TOUCH_BLOCKS.add(Blocks.BLACKSTONE);
            MIDAS_TOUCH_BLOCKS.add(Blocks.BASALT);
            MIDAS_TOUCH_BLOCKS.add(Blocks.SMOOTH_BASALT);
            MIDAS_TOUCH_BLOCKS.add(Blocks.BEDROCK);
        }

        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            ItemStack mainHandStack = player.getMainHandStack();
            if (EnchantmentHelper.get(mainHandStack).containsKey(ModEnchantments.MIDAS_TOUCH)
                    && MIDAS_TOUCH_BLOCKS.contains(state.getBlock())) {
                Random random = new Random();
                int isTrue = 1;
                int tag = random.nextInt(5) + 1;
                if (tag == isTrue) {
                    dropGold(world, pos);
                }
            }
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
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
        boolean isDefault = false;
        AtomicInteger enchantmentLevel = new AtomicInteger(0);
        double ADD_HEALTH = 0.0F;
        for (ItemStack stack : player.getInventory().armor) {
            if (stack.getItem() instanceof ArmorItem) {
                ArmorItem armorItem = (ArmorItem) stack.getItem();
                int level = EnchantmentHelper.getLevel(ModEnchantments.HEALTH_BOOST_ARMOR, stack);
                if (level > 0) {
                    isDefault = true;
                    ADD_HEALTH += player.defaultMaxHealth + (level * 2) * 2;
                    enchantmentLevel.set(level);
                }
            }
        }

        if (isDefault) {
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(ADD_HEALTH);
        } else
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(player.defaultMaxHealth);
    }

    // 获取玩家身上装备的附魔
    public static List<String> getPlayerEquipmentEnchantmentKeys(PlayerEntity player) {
        List<String> enchantmentKeys = new ArrayList<>();
        for (ItemStack itemStack : player.getArmorItems()) {
            if (!itemStack.isEmpty()) {
                // 获取物品上的附魔
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(itemStack);
                for (Enchantment enchantment : enchantments.keySet()) {
                    String key = enchantment.getTranslationKey(); // 获取附魔的 key 值
                    enchantmentKeys.add(key);
                }
            }
        }
        return enchantmentKeys;
    }

    /*
            if (isDefault) {
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(ADD_HEALTH);
            } else {
                double defaultMaxHealth = 20.0F;
                Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(defaultMaxHealth);

                if (player.getHealth() > defaultMaxHealth) {
                    player.setHealth((float) defaultMaxHealth);
                }
            }
        }
    */
    private static boolean isCanRun(boolean bool) {
        canRun = bool;
        return false;
    }

    private static void dropDiamond(World world, BlockPos pos) {
        ItemStack diamondStack = new ItemStack(Items.DIAMOND);
        net.minecraft.entity.ItemEntity itemEntity = new net.minecraft.entity.ItemEntity(world,
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, diamondStack);
        world.spawnEntity(itemEntity);
    }

    private static void dropGold(World world, BlockPos pos) {
        ItemStack diamondStack = new ItemStack(Items.GOLD_NUGGET);
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