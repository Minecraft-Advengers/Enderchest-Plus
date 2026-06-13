package io.chaws.enderchestplus.mixin;

import net.minecraft.world.SimpleContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SimpleContainer.class)
public class EnderChestInventoryMixin {
    @Inject(method = "<init>(I)V", at = @At("RETURN"), require = 0)
    private void onInit(int size, CallbackInfo ci) {
        // Check if this is being called for an enderchest (size 27)
        // If so, expand the internal list to 54 slots
        if (size == 27) {
            try {
                // Access the items field and replace it with a larger list
                java.lang.reflect.Field itemsField = SimpleContainer.class.getDeclaredField("items");
                itemsField.setAccessible(true);
                @SuppressWarnings("unchecked")
                net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack> items =
                    (net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack>) itemsField.get(this);

                // Create a new list with 54 slots and copy existing items
                net.minecraft.core.NonNullList<net.minecraft.world.item.ItemStack> newItems =
                    net.minecraft.core.NonNullList.withSize(54, net.minecraft.world.item.ItemStack.EMPTY);

                for (int i = 0; i < items.size(); i++) {
                    newItems.set(i, items.get(i));
                }

                itemsField.set(this, newItems);
            } catch (Exception e) {
                // If reflection fails, just continue with original size
            }
        }
    }
}
