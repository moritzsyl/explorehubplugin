package org.explorehubplugin.explorehubplugin.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.explorehubplugin.explorehubplugin.Utility;
import org.explorehubplugin.explorehubplugin.commands.DeleteConfirmTest;
import org.explorehubplugin.explorehubplugin.inventories.DeleteConfirmInventory;
import org.explorehubplugin.explorehubplugin.inventories.TeleportMenuInventory;

import java.util.concurrent.Executor;

// TODO: Change pretty much everything happening after click
public class InventoryEvents implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getClickedInventory()==null){return;}
        if(e.getClickedInventory().getHolder()  instanceof DeleteConfirmInventory) {
            e.setCancelled(true);
            if(e.isLeftClick()) {
                Player player = (Player) e.getWhoClicked();
                if (e.getCurrentItem() == null) {
                    return;
                }
                if (e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE) {
                    Utility.stdout(player, "YES SELECTED");
                    player.closeInventory();
                } else if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                    Utility.stdout(player, "NO SELECTED");
                    player.closeInventory();
                } else if (e.getCurrentItem().getType() == Material.BOOK) {
                    return;
                }
            }
        }
    }
    @EventHandler
    public void ItemInteract(PlayerInteractEvent e){
        Player p=e.getPlayer();
        if(!(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK)) return;
        if(e.getItem().getType()==Material.GRASS_BLOCK){
            Utility.stdout(p,"TEST 1");
            DeleteConfirmInventory gui=new DeleteConfirmInventory();
            p.openInventory(gui.getInventory());
        }
    }
}
