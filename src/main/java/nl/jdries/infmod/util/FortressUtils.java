package nl.jdries.infmod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class FortressUtils {
    /**
     * A very simple utility that returns a "center" for a fortress.
     * In this example, we simply offset the given position by (8, 0, 8).
     * For a more robust solution, use the structure manager to compute a fortress's bounding box.
     */
    public static BlockPos getFortressCenter(ServerLevel level, BlockPos pos) {
        // TODO: Replace with a more accurate calculation if needed.
        return pos.offset(8, 0, 8);
    }
}
