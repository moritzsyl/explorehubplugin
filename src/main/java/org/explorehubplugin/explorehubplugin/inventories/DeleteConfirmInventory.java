package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeleteConfirmInventory implements InventoryHolder {
    private Inventory inv;
    public DeleteConfirmInventory(){
        inv = Bukkit.createInventory(this,9,"Confirm Deletion of PLACEHOLDER");
        init();
    }

    private void init(){
        ItemStack accept = sharedMethods.createItem("§a§lYES", Material.LIME_STAINED_GLASS_PANE, Collections.singletonList("Yes I want to delete this point."));
        ItemStack reject = sharedMethods.createItem("§c§lNO", Material.RED_STAINED_GLASS_PANE, Collections.singletonList("No I don't want to delete this point."));
        List<String> infoLore = new ArrayList<>();
        infoLore.add("Do you want to delete this point?");
        infoLore.add("PLACEHOLDER ITEMNAME");
        ItemStack info = sharedMethods.createItem("§lDELETE?", Material.BOOK, infoLore);
        for(int i=0;i<4;i++){
            inv.setItem(inv.firstEmpty(),accept);
        }
        inv.setItem(inv.firstEmpty(),info);
        for(int i=5;i<9;i++){
            inv.setItem(inv.firstEmpty(),reject);
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
