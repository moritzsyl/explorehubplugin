package org.explorehubplugin.explorehubplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.explorehubplugin.explorehubplugin.inventories.JoinInventory;

/**
 * Die Klasse fügt jedem Nutzer welcher dem Server joined, anhand seiner Berechtigungen
 * entweder ein oder zwei Items ins Inventar hinzu.
 * @author Tobias Gorunovic
 * @version 2023-06-05
 */
public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p=e.getPlayer();
        p.getInventory().clear();
        JoinInventory spawnInv=new JoinInventory(p.hasPermission("explorehub.admin"));
        p.getInventory().setContents(spawnInv.getInventory().getContents());
    }
}
