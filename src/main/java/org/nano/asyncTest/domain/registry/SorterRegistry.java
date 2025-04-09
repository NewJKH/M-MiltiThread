package org.nano.asyncTest.domain.registry;

import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.domain.thread.ItemSorterThread;

import java.util.HashMap;
import java.util.Map;

public class SorterRegistry {
    private final Map<Category, ItemSorterThread> sorters = new HashMap<>();

    public void register(Category category, ItemSorterThread sorter) {
        sorters.put(category, sorter);
        sorter.start();
    }

    public void sortItem(Category category, ItemStack item) {
        if (sorters.containsKey(category)) {
            sorters.get(category).sort(item);
        }
    }
}
