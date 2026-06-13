package io.chaws.enderchestplus.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestMenu.class)
public class EnderChestInventoryMixin {
    @Inject(
        method = "threeRows",
        at = @At("HEAD"),
        cancellable = true,
        require = 0
    )
    private static void onThreeRows(int syncId, Inventory inventory, CallbackInfoReturnable<ChestMenu> cir) {
        // Redirect all threeRows calls to sixRows
        // This will affect all 3-row chests, not just enderchests
        // But since we're also expanding the inventory via EnderChestBlockMixin,
        // this should work for enderchests
        cir.setReturnValue(ChestMenu.sixRows(syncId, inventory, inventory.player.getEnderChestInventory()));
    }
}
