package nl.jdries.infmod.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nl.jdries.infmod.InfMod;
import nl.jdries.infmod.entity.client.piglinkingModel;
import nl.jdries.infmod.entity.custom.piglinkingentity;
import nl.jdries.infmod.entity.ModEntities;
@Mod.EventBusSubscriber(modid = InfMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(piglinkingModel.LAYER_LOCATION, piglinkingModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PIGLINKING.get(), piglinkingentity.createAttributes().build());
    }

}
