package io.chaws.enderchestplus.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.world.entity.player.PlayerEnderChestContainer")
public class EnderChestInventoryMixin {
    private static final int EXPANDED_SIZE = 54;

    @Redirect(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/NonNullList;withSize(ILjava/lang/Object;)Lnet/minecraft/core/NonNullList;"
        ),
        require = 0
    )
    private NonNullList<ItemStack> expandInventorySize(int size, ItemStack defaultValue) {
        // Replace the default 27 size with 54
        return NonNullList.withSize(EXPANDED_SIZE, defaultValue);
    }
}
