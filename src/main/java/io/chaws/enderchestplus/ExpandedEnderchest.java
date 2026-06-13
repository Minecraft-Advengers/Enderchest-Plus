package io.chaws.enderchestplus;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ExpandedEnderchest.MOD_ID)
public class ExpandedEnderchest {
    public static final String MOD_ID = "enderchestplus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public ExpandedEnderchest(IEventBus modEventBus) {
        modEventBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Enderchest+ initialized - using mixin approach for inventory expansion");
    }
}
