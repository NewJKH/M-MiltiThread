package org.nano.asyncTest.domain.thread;

import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.infra.MemoryBoxStorage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ItemSorterThread extends Thread {
    protected final BlockingQueue<ItemStack> queue = new LinkedBlockingQueue<>();
    private Runnable onComplete;
    protected final MemoryBoxStorage storage;
    protected final Category category;

    public ItemSorterThread(MemoryBoxStorage storage, Category category) {
        this.storage = storage;
        this.category = category;
    }

    public void sort(ItemStack item) {
        queue.offer(item);
    }

    @Override
    public void run() {
        while (true) {
            try {
                ItemStack item = queue.take();
                process(item);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    protected abstract void process(ItemStack item);
    public abstract int getProcessedCount();

    public void setOnComplete(Runnable callback) {
        this.onComplete = callback;
    }
    public void notifyCompletion() {
        if (onComplete != null) {
            onComplete.run();
        }
    }
}
