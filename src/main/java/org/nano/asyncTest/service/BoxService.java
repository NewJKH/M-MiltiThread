package org.nano.asyncTest.service;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.domain.box.ItemTypeClassifier;
import org.nano.asyncTest.domain.registry.SorterRegistry;
import org.nano.asyncTest.infra.MemoryBoxStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.nano.asyncTest.AsyncTest.plugin;

public class BoxService {
    private final SorterRegistry sorterRegistry;
    private final MemoryBoxStorage storage;

    public BoxService(SorterRegistry sorterRegistry, MemoryBoxStorage storage) {
        this.sorterRegistry = sorterRegistry;
        this.storage = storage;
    }

    // 아이템 제출 → 각 분류기로 전달
    public void handleBoxSubmission(Player player, List<ItemStack> items) {
        Map<Category, Integer> resultSummary = new HashMap<>();

        for (ItemStack item : items) {
            if (item == null) continue;

            Category category = ItemTypeClassifier.classify(item.getType());

            if (category != Category.UNKNOWN) {
                sorterRegistry.sortItem(category, item);
                resultSummary.merge(category, 1, Integer::sum);
            } else {
                player.sendMessage(item.getType() + "는 분류할 수 없는 아이템이에요!");
            }
        }

        if (resultSummary.isEmpty()) {
            player.sendMessage("📦 분류할 수 있는 아이템이 없어요.");
        }
    }


    // 분류 결과 조회
    public List<ItemStack> getItemsByCategory(Player player, Category category) {
        return storage.getItems(category);
    }

    public void showFinalResultTo(Player player, Runnable whenDone) {
        CountDownLatch latch = new CountDownLatch(2);
        long start = System.currentTimeMillis();

        sorterRegistry.getSorter(Category.MINERAL).setOnComplete(latch::countDown);
        sorterRegistry.getSorter(Category.WOOD).setOnComplete(latch::countDown);

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long duration = System.currentTimeMillis() - start;
            int mineral = sorterRegistry.getSorter(Category.MINERAL).getProcessedCount();
            int wood = sorterRegistry.getSorter(Category.WOOD).getProcessedCount();

            Bukkit.getScheduler().runTask(plugin, () -> {
                player.sendMessage("모든 분류 완료!");
                player.sendMessage("총 걸린 시간: " + duration + "ms");
                player.sendMessage("- 광물: " + mineral + "개");
                player.sendMessage("- 나무: " + wood + "개");
                if (whenDone != null) whenDone.run();
            });
        }).start();
    }

}
