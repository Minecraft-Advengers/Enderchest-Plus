package io.chaws.expandedenderchest;

import io.chaws.expandedenderchest.event.EnderChestEventHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ExpandedEnderchest.MOD_ID)
public class ExpandedEnderchest {
    public static final String MOD_ID = "expandedenderchest";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public ExpandedEnderchest(IEventBus modEventBus) {
        modEventBus.addListener(this::onCommonSetup);
        
        NeoForge.EVENT_BUS.addListener(EnderChestEventHandler::onPlayerLoggedIn);
        NeoForge.EVENT_BUS.addListener(EnderChestEventHandler::onPlayerTick);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Expanded Enderchest initialized");
    }
}
