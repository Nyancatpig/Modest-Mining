package com.ncpbails.modestmining.world;

import com.ncpbails.modestmining.ModestMining;
import com.ncpbails.modestmining.world.gen.ModEntityGeneration;
import com.ncpbails.modestmining.world.gen.ModOreGeneration;
import com.ncpbails.modestmining.world.gen.ModResourceGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModestMining.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);
        ModResourceGeneration.generateResources(event);
        ModEntityGeneration.onEntitySpawn(event);
    }
}
