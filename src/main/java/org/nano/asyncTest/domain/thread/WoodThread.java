package org.nano.asyncTest.domain.thread;

import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.infra.MemoryBoxStorage;

public class WoodThread extends ItemSorterThread{
    private int processedCount;

    public WoodThread(MemoryBoxStorage storage) {
        super(storage, Category.WOOD);
    }

    @Override
    protected void process(ItemStack item) {
        storage.addItem(category, item);
        processedCount++;
        System.out.println("[나무 분류기] " + item.getType() + " 처리 완료");
    }
    public int getProcessedCount() {
        return processedCount;
    }
}
