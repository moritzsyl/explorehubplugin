package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class TeleportMenuInventory implements InventoryHolder {
    private Inventory inv;

    public TeleportMenuInventory(){
        inv = Bukkit.createInventory(this,9*6,"Teleport Menü");
    }

    private void init(){
        File file = new File("");
    }

    @Override
    public Inventory getInventory(){
        return inv;
    }
}
