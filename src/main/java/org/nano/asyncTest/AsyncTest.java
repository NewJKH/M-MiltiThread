package org.nano.asyncTest;

import org.bukkit.plugin.java.JavaPlugin;
import org.nano.asyncTest.command.BoxCommand;
import org.nano.asyncTest.controller.BoxController;
import org.nano.asyncTest.listener.BoxCloseEvent;

import java.util.Objects;

public final class AsyncTest extends JavaPlugin {

    @Override
    public void onEnable() {
        BoxController controller = new BoxController();

        Objects.requireNonNull(getCommand("box")).setExecutor(new BoxCommand(controller));
        getServer().getPluginManager().registerEvents(new BoxCloseEvent(controller),this);
    }

    @Override
    public void onDisable() {

    }
}
