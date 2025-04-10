package nl.jdries.infmod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class StaffItem extends Item {

    // Separate cooldown maps for blaze (15 sec) and fireball (2 sec) modes.
    private static final Map<UUID, Long> blazeCooldowns = new HashMap<>();
    private static final Map<UUID, Long> fireballCooldowns = new HashMap<>();

    public StaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            // Use the current game time (ticks) to schedule cooldowns.
            long currentTime = level.getGameTime();
            UUID playerId = player.getUUID();

            if (player.isShiftKeyDown()) {
                // Blazes mode (15 sec cooldown = 300 ticks)
                Long nextAllowedUse = blazeCooldowns.get(playerId);
                if (nextAllowedUse != null && currentTime < nextAllowedUse) {
                    // Still under cooldown: deny the use
                    return InteractionResultHolder.fail(player.getItemInHand(hand));
                }

                // Spawn 5 blazes around the player.
                for (int i = 0; i < 5; i++) {
                    double offsetX = (Math.random() - 0.5) * 2;
                    double offsetZ = (Math.random() - 0.5) * 2;
                    Blaze blaze = new Blaze(EntityType.BLAZE, level);
                    blaze.moveTo(player.getX() + offsetX, player.getY(), player.getZ() + offsetZ, 0, 0);
                    level.addFreshEntity(blaze);
                }
                // Set the blaze cooldown (currentTime + 300 ticks)
                blazeCooldowns.put(playerId, currentTime + 300);
                // Damage the item by 25 durability points.
                ((ServerPlayer) player).getItemInHand(hand)
                        .hurtAndBreak(25, (ServerLevel) level, (ServerPlayer) player, (Consumer<Item>)(p -> {}));

            } else {
                // Fireball mode (2 sec cooldown = 40 ticks)
                Long nextAllowedUse = fireballCooldowns.get(playerId);
                if (nextAllowedUse != null && currentTime < nextAllowedUse) {
                    // Still under cooldown: deny the use
                    return InteractionResultHolder.fail(player.getItemInHand(hand));
                }

                // Spawn a fireball in the direction the player is facing.
                Vec3 look = player.getLookAngle();
                LargeFireball fireball = new LargeFireball(level, player, look, 2);
                fireball.moveTo(player.getX(), player.getEyeY(), player.getZ(), 0, 0);
                level.addFreshEntity(fireball);
                // Set the fireball cooldown (currentTime + 40 ticks)
                fireballCooldowns.put(playerId, currentTime + 40);
                // Damage the item by 1 durability point.
                ((ServerPlayer) player).getItemInHand(hand)
                        .hurtAndBreak(1, (ServerLevel) level, (ServerPlayer) player, (Consumer<Item>)(p -> {}));
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}
