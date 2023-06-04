package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.explorehubplugin.explorehubplugin.Utility;

import java.io.File;
import java.util.Collections;

public class TeleportMenuInventory implements InventoryHolder {
    private Inventory inv;

    public TeleportMenuInventory(){
        inv = Bukkit.createInventory(this,9*6,"Teleport Menü");
        init();
    }

    private void init(){
        for(int i=0;i<Utility.getTpls().size();i++) {
            String tmpName = Utility.getTpls().get(i).getName();
            Material tmpMat = Material.valueOf(Utility.getTpls().get(i).getBlock());
            String tmpLore = Utility.getTpls().get(i).getDesc();
            ItemStack temp = Utility.createItem(tmpName, tmpMat, Collections.singletonList(tmpLore));
            inv.setItem(inv.firstEmpty(), temp);
        }
    }

    @Override
    public Inventory getInventory(){
        return inv;
    }
}
