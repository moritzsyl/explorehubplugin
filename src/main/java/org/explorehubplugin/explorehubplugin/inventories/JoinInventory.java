package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class JoinInventory implements InventoryHolder {
    private Inventory inv;
    public JoinInventory(){
        inv= Bukkit.createInventory(this,36,"Inventory");
        init();
    }
    private void init(){
        ItemStack TeleportMenu=sharedMethods.createItem("§lTeleportMenu", Material.GRASS_BLOCK, Collections.singletonList("Opens TeleportMenu"));
        inv.setItem(4,TeleportMenu);
    }
    @Override
    public Inventory getInventory(){return inv;}
}
