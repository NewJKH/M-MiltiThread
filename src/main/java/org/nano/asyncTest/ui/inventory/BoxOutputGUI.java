package org.nano.asyncTest.ui.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.nano.asyncTest.domain.box.Category;

import java.util.List;

public class BoxOutputGUI implements InventoryHolder {
    private final Inventory inventory;

    public BoxOutputGUI() {
        inventory = Bukkit.createInventory(this,54, Component.text("분류"));
    }

    public void setup(Player player, Category category, List<ItemStack> sortedItems){

    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
