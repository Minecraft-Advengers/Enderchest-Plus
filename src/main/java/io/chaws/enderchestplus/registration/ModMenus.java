package io.chaws.expandedenderchest.registration;

import io.chaws.expandedenderchest.ExpandedEnderchest;
import io.chaws.expandedenderchest.menu.ExpandedEnderchestMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, ExpandedEnderchest.MOD_ID);
    
    public static final DeferredHolder<MenuType<?>, MenuType<ExpandedEnderchestMenu>> EXPANDED_ENDERCHEST = MENUS.register(
        "expanded_enderchest",
        () -> IMenuTypeExtension.create((syncId, inventory, data) -> new ExpandedEnderchestMenu(syncId, inventory, inventory.player.getEnderChestInventory()))
    );
    
    public static void register(IEventBus modEventBus) {
        MENUS.register(modEventBus);
    }
}
