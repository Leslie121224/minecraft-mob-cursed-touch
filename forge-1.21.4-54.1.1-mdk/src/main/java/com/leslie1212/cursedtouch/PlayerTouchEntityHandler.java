package com.leslie1212.cursedtouch;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.Holder;

import java.util.*;

@Mod.EventBusSubscriber  // 讓 Forge 自動註冊這個類別的事件
public class PlayerTouchEntityHandler {
    private static final Random random = new Random();

    // Cooldown 機制：每位玩家最多每 1 秒觸發一次
    private static final Map<UUID, Long> cooldownMap = new HashMap<>();
    private static final long COOLDOWN_TICKS = 20; // 1 秒（20 ticks = 1 秒）

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();

        // 只在伺服器端執行，避免雙重執行
        if (level.isClientSide || !(player instanceof ServerPlayer)) return;

        UUID playerId = player.getUUID();
        long currentTick = level.getGameTime();

        // 如果玩家還在冷卻中就跳過
        if (cooldownMap.containsKey(playerId) &&
            currentTick - cooldownMap.get(playerId) < COOLDOWN_TICKS) {
            return;
        }

        // 找出靠近玩家的生物（不含自己）
        List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(
            LivingEntity.class,
            player.getBoundingBox().inflate(0.5),  // 檢查碰觸距離
            e -> e != player
        );

        // 如果碰到至少一個生物，就觸發詛咒
        if (!nearbyEntities.isEmpty()) {
            applyRandomPotion(player);
            cooldownMap.put(playerId, currentTick);
        }
    }

    private static void applyRandomPotion(Player player) {
        List<Holder<MobEffect>> allEffects = Arrays.asList(
            MobEffects.ABSORPTION,
            MobEffects.BAD_OMEN,
            MobEffects.CONDUIT_POWER,
            MobEffects.DOLPHINS_GRACE,
            MobEffects.FIRE_RESISTANCE,
            MobEffects.GLOWING,
            MobEffects.HEALTH_BOOST,
            MobEffects.HERO_OF_THE_VILLAGE,
            MobEffects.INVISIBILITY,
            MobEffects.JUMP,
            MobEffects.LEVITATION,
            MobEffects.LUCK,
            MobEffects.MOVEMENT_SPEED,
            MobEffects.NIGHT_VISION,
            MobEffects.REGENERATION,
            MobEffects.DAMAGE_RESISTANCE,
            MobEffects.SATURATION,
            MobEffects.SLOW_FALLING,
            MobEffects.WATER_BREATHING,
    
            MobEffects.BLINDNESS,
            MobEffects.CONFUSION,
            MobEffects.DARKNESS,
            MobEffects.HUNGER,
            MobEffects.DIG_SLOWDOWN,
            MobEffects.MOVEMENT_SLOWDOWN,
            MobEffects.POISON,
            MobEffects.UNLUCK,
            MobEffects.WEAKNESS,
            MobEffects.WITHER
        );
    
        Holder<MobEffect> effect = allEffects.get(random.nextInt(allEffects.size()));
        player.addEffect(new MobEffectInstance(effect, 15 * 20));

    }
    
}
