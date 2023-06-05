package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.explorehubplugin.explorehubplugin.Utility;
import java.util.Collections;

/**
 * Die Klasse ermöglicht das erhalten, von zwei Menüs (Teleport und Löschmenü) in Form von Items im Inventar, beim Betreten des Servers.
 * Das Löschmenü wird nur an Admins gegeben.
 * @author Tobias Gorunovic
 * @version 2023-06-05
 */
public class JoinInventory implements InventoryHolder {
    private Inventory inv;
    public JoinInventory(boolean admin){
        inv= Bukkit.createInventory(this,36,"Inventory");
        init();
        if(admin){
            adminInit();
        }
    }
    private void init(){
        ItemStack teleportMenu=Utility.createItem("§lTeleportMenu", Material.BOOK, Collections.singletonList("Opens TeleportMenu."));
        inv.setItem(3,teleportMenu);
    }
    private void adminInit(){
        ItemStack deleteMenu=Utility.createItem("§c§lDeleteMenu",Material.DEAD_BUSH,Collections.singletonList("Delete TeleportLocation."));
        inv.setItem(5,deleteMenu);
    }
    @Override
    public Inventory getInventory(){return inv;}
}
