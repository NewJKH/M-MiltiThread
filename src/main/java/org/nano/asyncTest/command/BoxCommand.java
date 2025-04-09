package org.nano.asyncTest.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nano.asyncTest.controller.BoxController;
import org.nano.asyncTest.domain.box.Category;

import static org.nano.asyncTest.util.MessagePrinter.print;

public class BoxCommand implements CommandExecutor {
    private final BoxController controller;

    public BoxCommand(BoxController controller) {
        this.controller = controller;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if ( commandSender instanceof Player player ){
            switch (strings.length){
                case 0 ->{
                    controller.openBox(player);
                }
                case 2 ->{
                    controller.openCategory(player, Category.valueOf(strings[1]));
                }
                default -> print(player,"/box | /box open <category>");
            }
        }
        return false;
    }
}
