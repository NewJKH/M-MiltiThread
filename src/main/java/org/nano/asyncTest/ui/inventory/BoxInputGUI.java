package org.nano.asyncTest.ui.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class BoxInputGUI implements InventoryHolder {
    private final Inventory inventory;

    public BoxInputGUI() {
        inventory = Bukkit.createInventory(this,54, Component.text("분류할 아이템 넣기"));
    }

    public void setup(){

    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
