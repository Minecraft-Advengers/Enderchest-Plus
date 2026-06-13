package io.chaws.enderchestplus.menu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Container;

public class ExpandedEnderchestMenu extends AbstractContainerMenu {
    private final Container container;
    private final int containerRows;

    public ExpandedEnderchestMenu(int syncId, Inventory playerInventory, Container container) {
        super(MenuType.GENERIC_9x6, syncId);
        this.container = container;
        this.containerRows = 6;

        // Add the 6 rows of enderchest slots (54 slots total)
        int i = (this.containerRows - 4) * 18;
        int j;
        int k;

        for (j = 0; j < this.containerRows; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlot(new net.minecraft.world.inventory.Slot(container, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        // Add the player inventory slots
        int l = 140;
        int i1 = this.containerRows * 18 + 13;

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlot(new net.minecraft.world.inventory.Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, i1 + j * 18));
            }
        }

        // Add the player hotbar slots
        for (j = 0; j < 9; ++j) {
            this.addSlot(new net.minecraft.world.inventory.Slot(playerInventory, j, 8 + j * 18, l));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public net.minecraft.world.item.ItemStack quickMoveStack(Player player, int index) {
        net.minecraft.world.item.ItemStack itemstack = net.minecraft.world.item.ItemStack.EMPTY;
        net.minecraft.world.inventory.Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            net.minecraft.world.item.ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.containerRows * 9) {
                if (!this.moveItemStackTo(itemstack1, this.containerRows * 9, this.slots.size(), true)) {
                    return net.minecraft.world.item.ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.containerRows * 9, false)) {
                return net.minecraft.world.item.ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(net.minecraft.world.item.ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return net.minecraft.world.item.ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
