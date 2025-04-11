package nl.jdries.infmod.world;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.saveddata.SavedData.Factory;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class PiglinKingManager extends SavedData {
    private static final String DATA_NAME = "piglin_king_manager";
    private final Set<BlockPos> clearedFortresses = new HashSet<>();

    public PiglinKingManager() {}

    public static PiglinKingManager get(ServerLevel level) {
// The lambda is explicitly cast to Function<CompoundTag, PiglinKingManager>

        return level.getDataStorage().computeIfAbsent(
                PiglinKingManager::load,
                PiglinKingManager::new,
                DATA_NAME
        );}

    /**
     * Retrieves the saved data instance from the world's DataStorage.
     */





    /**
     * Checks whether the given fortress center has already had its Piglin King defeated.
     */
    public boolean hasFortressBeenCleared(BlockPos fortressCenter) {
        return clearedFortresses.contains(fortressCenter);
    }

    /**
     * Marks the fortress as cleared (i.e. the Piglin King was killed).
     */
    public void markFortressCleared(BlockPos fortressCenter) {
        if (clearedFortresses.add(fortressCenter.immutable())) {
            setDirty(); // Marks the data as changed so it will be saved
        }
    }

    /**
     * Loads the saved data from a CompoundTag.
     */
    public static PiglinKingManager load(CompoundTag tag) {
        PiglinKingManager manager = new PiglinKingManager();
        ListTag listTag = tag.getList("cleared", Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag posTag = listTag.getCompound(i);
            BlockPos pos = new BlockPos(
                    posTag.getInt("x"),
                    posTag.getInt("y"),
                    posTag.getInt("z")
            );
            manager.clearedFortresses.add(pos);
        }
        return manager;
    }

    /**
     * Saves the current state into a CompoundTag.
     */
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (BlockPos pos : clearedFortresses) {
            CompoundTag posTag = new CompoundTag();
            posTag.putInt("x", pos.getX());
            posTag.putInt("y", pos.getY());
            posTag.putInt("z", pos.getZ());
            list.add(posTag);
        }
        tag.put("cleared", list);
        return tag;
    }

    /**
     * NEW: Implement the additional abstract method required by newer mappings of SavedData.
     */
    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        return this.save(tag);
    }
}