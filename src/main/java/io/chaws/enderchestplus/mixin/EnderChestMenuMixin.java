package io.chaws.expandedenderchest.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestMenu.class)
public class EnderChestMenuMixin {
    @Inject(
        method = "threeRows",
        at = @At("HEAD"),
        cancellable = true,
        require = 0
    )
    private static void onThreeRows(int syncId, Inventory inventory, CallbackInfoReturnable<ChestMenu> cir) {
        // Check if this is an enderchest by checking if the container is the player's enderchest inventory
        // The container field in Inventory is private, so we need to use reflection to access it
        try {
            java.lang.reflect.Field containerField = Inventory.class.getDeclaredField("container");
            containerField.setAccessible(true);
            Object container = containerField.get(inventory);
            
            if (container == inventory.player.getEnderChestInventory()) {
                // This is an enderchest, return sixRows instead
                cir.setReturnValue(ChestMenu.sixRows(syncId, inventory, inventory.player.getEnderChestInventory()));
            }
        } catch (Exception e) {
            // If reflection fails, just let the normal threeRows proceed
        }
    }
}
