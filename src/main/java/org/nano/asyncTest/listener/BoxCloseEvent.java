package org.nano.asyncTest.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.nano.asyncTest.controller.BoxController;
import org.nano.asyncTest.ui.inventory.BoxInputGUI;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BoxCloseEvent implements Listener {
    private final BoxController boxController;

    public BoxCloseEvent(BoxController boxController) {
        this.boxController = boxController;
    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent e){
        if ( e.getInventory().getHolder() instanceof BoxInputGUI ){
            Player player = (Player) e.getPlayer();
            List<ItemStack> contents = Arrays.stream(e.getInventory().getContents())
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            boxController.submitItems(player, contents);
        }
    }

}
