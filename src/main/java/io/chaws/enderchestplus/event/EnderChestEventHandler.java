package io.chaws.enderchestplus.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnderChestEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("enderchestplus");

    // Reflection-based expansion removed - now using mixin approach
    // The mixin in EnderChestInventoryMixin handles the inventory expansion
}
