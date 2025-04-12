package nl.jdries.infmod.entity;

import net.minecraft.resources.ResourceLocation;
import nl.jdries.infmod.InfMod;

import nl.jdries.infmod.entity.custom.piglinkingentity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, InfMod.MOD_ID);

    public static final RegistryObject<EntityType<piglinkingentity>> PIGLINKING =
            ENTITY_TYPES.register("piglinking", () -> EntityType.Builder.of(piglinkingentity::new, MobCategory.MONSTER)
                    .sized(3f, 4f).build("piglinking"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}