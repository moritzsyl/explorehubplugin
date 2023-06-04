package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.explorehubplugin.explorehubplugin.Utility;

import java.util.Collections;

public class JoinInventory implements InventoryHolder {
    private Inventory inv;
    public JoinInventory(){
        inv= Bukkit.createInventory(this,36,"Inventory");
        init();
    }
    private void init(){
        ItemStack teleportMenu=Utility.createItem("§lTeleportMenu", Material.BOOK, Collections.singletonList("Opens TeleportMenu."));
        inv.setItem(3,teleportMenu);
    }
    @Override
    public Inventory getInventory(){return inv;}
}
