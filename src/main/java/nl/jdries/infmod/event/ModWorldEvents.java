package nl.jdries.infmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nl.jdries.infmod.InfMod;
import nl.jdries.infmod.entity.custom.piglinkingentity;
import nl.jdries.infmod.entity.ModEntities; // Your mod's entity registration
import nl.jdries.infmod.util.FortressUtils;
import nl.jdries.infmod.world.PiglinKingManager;

@Mod.EventBusSubscriber(modid = InfMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent event) {
        // Process only during the END phase and only on the server side.
        if (event.phase != TickEvent.Phase.END || event.level.isClientSide()) return;
        if (!(event.level instanceof ServerLevel serverLevel)) return;

        // Check once every 200 ticks
        if (serverLevel.getGameTime() % 200 != 0) return;

        // Iterate over online players rather than loaded chunks.
        for (ServerPlayer player : serverLevel.players()) {
            // Determine a fortress center from the player's position.
            BlockPos playerPos = player.blockPosition();
            BlockPos fortressCenter = FortressUtils.getFortressCenter(serverLevel, playerPos);
            Vec3 fortressCenterVec = Vec3.atCenterOf(fortressCenter);

            // Obtain the saved data manager to check if this fortress is already cleared.
            PiglinKingManager manager = PiglinKingManager.get(serverLevel);

            // Check whether a boss is already present in a 16×16×16 area around the fortress center.
            boolean bossNearby = !serverLevel.getEntitiesOfClass(piglinkingentity.class,
                            new AABB(fortressCenterVec, fortressCenterVec.add(16, 16, 16)))
                    .isEmpty();

            // If not cleared and no boss is nearby, spawn the boss.
            if (!manager.hasFortressBeenCleared(fortressCenter) && !bossNearby) {
                piglinkingentity boss = ModEntities.PIGLINKING.get().create(serverLevel);
                if (boss != null) {
                    // Spawn at the computed fortress center with a small Y-offset.
                    boss.moveTo(fortressCenter.getX() + 0.5, fortressCenter.getY() + 5, fortressCenter.getZ() + 0.5, 0, 0);
                    serverLevel.addFreshEntity(boss);
                }
            }
        }
    }
}
