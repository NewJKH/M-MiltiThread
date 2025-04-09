package org.nano.asyncTest.domain.thread;

import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.domain.box.Category;
import org.nano.asyncTest.infra.MemoryBoxStorage;

public class MineralThread extends ItemSorterThread {

    public MineralThread(MemoryBoxStorage storage) {
        super(storage, Category.MINERAL);
    }

    @Override
    protected void process(ItemStack item) {
        storage.addItem(category, item);
        System.out.println("[광물 분류기] " + item.getType() + " 처리 완료");
    }
}
