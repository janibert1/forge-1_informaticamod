package nl.jdries.infmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nl.jdries.infmod.InfMod;
import nl.jdries.infmod.entity.ModEntities;
import nl.jdries.infmod.item.custom.StaffItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, InfMod.MOD_ID);

    public static final RegistryObject<Item> STAFFSHARDS = ITEMS.register("staffshard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAFFHEART = ITEMS.register("staffheart",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAFF = ITEMS.register("staff",
            () -> new StaffItem(new Item.Properties().durability(100)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
    public static final RegistryObject<Item> PIGLINKING_SPAWN_EGG   = ITEMS.register("piglinking_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.PIGLINKING, 0x010000, 0x004000, new Item.Properties()));
}
