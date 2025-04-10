package org.nano.asyncTest.ui.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BoxOutputGUI implements InventoryHolder {
    private final Inventory inventory;

    public BoxOutputGUI() {
        inventory = Bukkit.createInventory(this,54, Component.text("분류"));
    }

    public void setup(List<ItemStack> sortedItems){
        sortedItems.forEach(inventory::addItem);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
