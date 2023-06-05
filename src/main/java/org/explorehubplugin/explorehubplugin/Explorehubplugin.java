package org.explorehubplugin.explorehubplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.explorehubplugin.explorehubplugin.commands.setteleportlocation;
import org.explorehubplugin.explorehubplugin.events.InventoryEvents;
import org.explorehubplugin.explorehubplugin.events.JoinEvent;
import java.io.IOException;

/**
 * Die Klasse dient als "Hauptklass" und initialisiert in den Methoden onEnable (Serverstart) und onDisable (Serverstop)
 * Commands, Pfade oder auch die Liste an TeleportLocations.
 * @author Moritz Syllaba
 * @author Tobias Gorunovic
 * @version 2023-06-05
 */
public final class Explorehubplugin extends JavaPlugin {

    private static Explorehubplugin plugin;
    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getLogger().info("Hello World!");
        this.registerCommands();
        try {
            Utility.loadTeleportLocations();
        }catch (IOException exc){
            Utility.broadcaststdout("Failed to load teleport locations");
        }
        //RIGHT HERE?
        getServer().getPluginManager().registerEvents(new InventoryEvents(),this);
        getServer().getPluginManager().registerEvents(new JoinEvent(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Explorehubplugin getPlugin(){
        return plugin;
    }

    private void registerCommands(){
        this.getCommand("setteleportlocation").setExecutor(new setteleportlocation());
    }
}
