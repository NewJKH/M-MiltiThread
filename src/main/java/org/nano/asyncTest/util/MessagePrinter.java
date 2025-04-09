package org.nano.asyncTest.util;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class MessagePrinter {
    public static void print(Player player, String text){
        Component component = Component.text(text);
        player.sendMessage(component);
    }
}
