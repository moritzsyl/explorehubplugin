package org.explorehubplugin.explorehubplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.explorehubplugin.explorehubplugin.commands.DeleteConfirmTest;
import org.explorehubplugin.explorehubplugin.commands.MenuTest;
import org.explorehubplugin.explorehubplugin.commands.setteleportlocation;
import org.explorehubplugin.explorehubplugin.events.InventoryEvents;

public final class Explorehubplugin extends JavaPlugin {

    private static Explorehubplugin plugin;
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        Bukkit.getLogger().info("Hello World!");
        this.registerCommands();
        //RIGHT HERE?
        getServer().getPluginManager().registerEvents(new InventoryEvents(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Explorehubplugin getPlugin(){
        return plugin;
    }

    private void registerCommands(){
        this.getCommand("menu").setExecutor(new MenuTest(this));
        this.getCommand("setteleportlocation").setExecutor(new setteleportlocation());
        this.getCommand("deleteconfirmtest").setExecutor(new DeleteConfirmTest());
    }
}
