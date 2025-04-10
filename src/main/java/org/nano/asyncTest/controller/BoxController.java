package org.nano.asyncTest.controller;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.domain.registry.SorterRegistry;
import org.nano.asyncTest.domain.thread.MineralThread;
import org.nano.asyncTest.domain.thread.WoodThread;
import org.nano.asyncTest.infra.MemoryBoxStorage;
import org.nano.asyncTest.service.BoxService;
import org.nano.asyncTest.ui.inventory.BoxInputGUI;
import org.nano.asyncTest.ui.inventory.BoxOutputGUI;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.nano.asyncTest.AsyncTest.plugin;

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
        gui.setup(sortedItems);
        player.openInventory(gui.getInventory());
    }
    public void showProcessingResult(Player player) {
        CountDownLatch latch = new CountDownLatch(2);

        long start = System.currentTimeMillis();

        sorterRegistry.getSorter(Category.MINERAL).setOnComplete(latch::countDown);
        sorterRegistry.getSorter(Category.WOOD).setOnComplete(latch::countDown);

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long duration = System.currentTimeMillis() - start;
                int mineral = sorterRegistry.getSorter(Category.MINERAL).getProcessedCount();
                int wood = sorterRegistry.getSorter(Category.WOOD).getProcessedCount();

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.sendMessage("모든 분류 완료!");
                        player.sendMessage("총 걸린 시간: " + duration + "ms");
                        player.sendMessage("- 광물: " + mineral + "개");
                        player.sendMessage("- 나무: " + wood + "개");
                    }
                }.runTask(plugin);
            }
        }.runTaskAsynchronously(plugin);
    }

}
