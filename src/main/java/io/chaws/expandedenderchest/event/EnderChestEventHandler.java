package io.chaws.expandedenderchest.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnderChestEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("expanded-enderchest");
    private static final int EXPANDED_ENDERCHEST_SIZE = 9 * 6;

    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        var enderChestInventory = player.getEnderChestInventory();
        
        // Expand the enderchest inventory size
        try {
            java.lang.reflect.Field sizeField = enderChestInventory.getClass().getDeclaredField("size");
            sizeField.setAccessible(true);
            sizeField.setInt(enderChestInventory, EXPANDED_ENDERCHEST_SIZE);
            
            java.lang.reflect.Field stacksField = enderChestInventory.getClass().getDeclaredField("heldStacks");
            stacksField.setAccessible(true);
            stacksField.set(enderChestInventory, net.minecraft.core.NonNullList.withSize(EXPANDED_ENDERCHEST_SIZE, net.minecraft.world.item.ItemStack.EMPTY));
            
            LOGGER.debug("Expanded enderchest inventory for player: {}", player.getName().getString());
        } catch (Exception e) {
            LOGGER.error("Failed to expand enderchest inventory", e);
        }
    }

    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.containerMenu instanceof ChestMenu chestMenu) {
            // Check if this is an enderchest menu by checking the container
            if (chestMenu.getContainer() == player.getEnderChestInventory()) {
                // This is an enderchest, we need to replace it with a 6-row version
                // Since we can't easily replace the menu, we'll need to use a different approach
                // For now, this event handler is a placeholder for a proper solution
            }
        }
    }
}
