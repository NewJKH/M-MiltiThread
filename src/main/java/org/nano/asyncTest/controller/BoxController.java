package org.nano.asyncTest.controller;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.domain.registry.SorterRegistry;
import org.nano.asyncTest.domain.thread.MineralThread;
import org.nano.asyncTest.domain.thread.WoodThread;
import org.nano.asyncTest.infra.MemoryBoxStorage;
import org.nano.asyncTest.service.BoxService;
import org.nano.asyncTest.ui.inventory.BoxInputGUI;
import org.nano.asyncTest.ui.inventory.BoxOutputGUI;

import java.util.List;

public class BoxController {
    private final BoxService boxService;
    private final SorterRegistry sorterRegistry;
    private final MemoryBoxStorage storage;

    public BoxController() {
        this.sorterRegistry = new SorterRegistry();
        this.storage = new MemoryBoxStorage();

        sorterRegistry.register(Category.MINERAL, new MineralThread(storage));
        sorterRegistry.register(Category.WOOD, new WoodThread(storage));

        this.boxService = new BoxService(sorterRegistry,storage);
    }

    public void openBox(Player player) {
        BoxInputGUI gui = new BoxInputGUI();
        gui.setup();
        player.openInventory(gui.getInventory());
    }

    public void submitItems(Player player, List<ItemStack> items) {
        boxService.handleBoxSubmission(player, items);
    }

    public void openCategory(Player player, Category category) {
        List<ItemStack> sortedItems = boxService.getItemsByCategory(player, category);
        BoxOutputGUI gui = new BoxOutputGUI();
        gui.setup(player,category,sortedItems);
        player.openInventory(gui.getInventory());
    }
}
