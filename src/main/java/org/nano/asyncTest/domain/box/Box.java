package org.nano.asyncTest.domain.box;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private final Player owner;
    private final List<ItemStack> items = new ArrayList<>();

    public Box(Player owner) {
        this.owner = owner;
    }

    public void addItem(ItemStack item) {
        items.add(item);
    }

    public List<ItemStack> getItems() {
        return new ArrayList<>(items);
    }

    public Player getOwner() {
        return owner;
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
