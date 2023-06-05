package org.explorehubplugin.explorehubplugin.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.explorehubplugin.explorehubplugin.TeleportLocation;
import org.explorehubplugin.explorehubplugin.Utility;
import org.explorehubplugin.explorehubplugin.inventories.DeleteConfirmInventory;
import org.explorehubplugin.explorehubplugin.inventories.TeleportMenuInventory;
import java.io.IOException;

// FIXME: Error with deleting/teleporting maps if two players act at the same time

/**
 * Die Klasse implementiert verschiedene Eventhandler welche verschiedene Aktionen in Bezug auf
 * das GUI nicht möblich bzw. möglich machen.
 * @author Tobias Gorunovic
 * @author Moritz Syllaba
 * @version 2023-06-05
 */
public class InventoryEvents implements Listener {
    private boolean delete;
    private String currentItemInDeletionSelection;

    /**
     * Alle User außer Admins sollen, die Inventar Items (toter Busch und Buch) nicht droppen können.
     * Die Methode implementiert genau das.
     * @param e PlayerDropItemEvent.
     */
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        if(!(e.getPlayer().hasPermission("explorehub.admin"))){
            e.setCancelled(true);
        }
    }

    /**
     * Kein Spieler soll den toten Busch (delete Menu) als Block platzieren können.
     * Die Methode verhindert genau das.
     * @param e BlockPlaceEvent.
     */
    @EventHandler
    public void onPlayerPlaceItem(BlockPlaceEvent e){
        if(e.getItemInHand().getType() == Material.DEAD_BUSH){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (!(player.hasPermission("explorehub.admin"))) {
            e.setCancelled(true);
        }
        if(e.getClickedInventory()==null){return;}
        if(e.getClickedInventory().getHolder()  instanceof DeleteConfirmInventory) {
            e.setCancelled(true);
            if(e.isLeftClick()) {
                if (e.getCurrentItem() == null) {
                    return;
                }
                if (e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE) {
                    try {
                        Utility.deleteTeleportLocation(currentItemInDeletionSelection);
                        Utility.stdout(player, "Yes selected, "+currentItemInDeletionSelection+" was deleted");
                    } catch (IOException ex) {
                        Utility.stderror(player, "ERROR: Yes selected, "+currentItemInDeletionSelection+" was not deleted");
                        throw new RuntimeException(ex);
                    }
                    currentItemInDeletionSelection=null;
                    player.closeInventory();
                } else if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                    Utility.stdout(player, "No selected, "+currentItemInDeletionSelection+" was not deleted");
                    currentItemInDeletionSelection=null;
                    player.closeInventory();
                } else if (e.getCurrentItem().getType() == Material.BOOK) {
                    return;
                }
            }
        }
        if(e.getClickedInventory().getHolder()  instanceof TeleportMenuInventory) {
            e.setCancelled(true);
            if(e.isLeftClick()) {
                Player p = (Player) e.getWhoClicked();
                if (e.getCurrentItem() == null) {
                    return;
                }
                String selectedName=e.getCurrentItem().getItemMeta().getDisplayName();
                if(!delete) {
                    TeleportLocation l = Utility.readTeleportLocation(selectedName);
                    Location teleportTo = new Location(p.getWorld(), l.getX(), l.getY(), l.getZ());
                    teleportTo.setDirection(l.getDirection());
                    p.teleport(teleportTo);
                }else{
                    String delName=e.getCurrentItem().getItemMeta().getDisplayName();
                    currentItemInDeletionSelection=delName;
                    p.openInventory(new DeleteConfirmInventory(delName).getInventory());
                }
            }
        }
        if(e.getClickedInventory().getHolder() instanceof PlayerInventory){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void ItemInteract(PlayerInteractEvent e){
        Player p=e.getPlayer();
        if(e.getItem()==null) return;
        if(!(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK)) return;
        if(e.getItem().getType()==Material.BOOK){
            delete=false;
            TeleportMenuInventory TMIGui=new TeleportMenuInventory();
            p.openInventory(TMIGui.getInventory());
        }else if(e.getItem().getType()==Material.DEAD_BUSH){
            delete=true;
            TeleportMenuInventory TMIGui=new TeleportMenuInventory();
            p.openInventory(TMIGui.getInventory());
        }

    }
}
