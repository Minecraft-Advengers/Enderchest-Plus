package io.chaws.enderchestplus.mixin;

import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderChestBlock.class)
public class EnderChestBlockMixin {
    @Inject(
        method = "getMenuProvider",
        at = @At("HEAD"),
        cancellable = true,
        require = 0
    )
    private void onGetMenuProvider(BlockState state, CallbackInfoReturnable<MenuProvider> cir) {
        // Return a custom menu provider that creates a 6-row enderchest menu
        cir.setReturnValue(new MenuProvider() {
            @Override
            public net.minecraft.world.inventory.AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
                // Return a 6-row chest menu for the enderchest
                return ChestMenu.sixRows(syncId, inventory, player.getEnderChestInventory());
            }

            @Override
            public net.minecraft.network.chat.Component getDisplayName() {
                return net.minecraft.network.chat.Component.translatable("container.enderchest");
            }
        });
    }
}
