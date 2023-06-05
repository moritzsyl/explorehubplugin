package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.explorehubplugin.explorehubplugin.Utility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Die Klasse implementiert ein Löschbestätigungs GUI in welchem ein Nutzer bestätigen muss um eine TeleportLocation zu löschen.
 * @author Tobias Gorunovic
 * @version 2023-06-05
 */
public class DeleteConfirmInventory implements InventoryHolder {
    private Inventory inv;
    public DeleteConfirmInventory(String name){
        inv = Bukkit.createInventory(this,9,"Confirm deletion of "+name);
        init(name);
    }

    private void init(String name){
        ItemStack accept = Utility.createItem("§a§lYES", Material.LIME_STAINED_GLASS_PANE, Collections.singletonList("Yes I want to delete this TeleportLocation."));
        ItemStack reject = Utility.createItem("§c§lNO", Material.RED_STAINED_GLASS_PANE, Collections.singletonList("No I don't want to delete this TeleportLocation."));
        List<String> infoLore = new ArrayList<>();
        infoLore.add("Do you want to delete this TeleportLocation?");
        infoLore.add(name);
        ItemStack info = Utility.createItem("§lDELETE?", Material.BOOK, infoLore);
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
