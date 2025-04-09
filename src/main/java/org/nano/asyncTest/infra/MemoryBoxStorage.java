package org.nano.asyncTest.infra;

import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryBoxStorage {
    private final Map<Category, List<ItemStack>> sortedItems = new ConcurrentHashMap<>();

    public void addItem(Category category, ItemStack item) {
        sortedItems.computeIfAbsent(category, k -> new ArrayList<>()).add(item);
    }

    public List<ItemStack> getItems(Category category) {
        return sortedItems.getOrDefault(category, new ArrayList<>());
    }
}
