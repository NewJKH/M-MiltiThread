package org.nano.asyncTest.domain.thread;

import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.infra.MemoryBoxStorage;

import java.util.concurrent.atomic.AtomicInteger;

public class MineralThread extends ItemSorterThread {
    private final AtomicInteger processedCount = new AtomicInteger(0);

    public MineralThread(MemoryBoxStorage storage) {
        super(storage, Category.MINERAL);
    }

    @Override
    protected void process(ItemStack item) {
        storage.addItem(category, item);
        processedCount.incrementAndGet();
        System.out.println("[광물 분류기] " + item.getType() + " 처리 완료");
    }
    public int getProcessedCount() {
        return processedCount.get();
    }
}
