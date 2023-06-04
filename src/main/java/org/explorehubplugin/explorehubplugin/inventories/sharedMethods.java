package org.explorehubplugin.explorehubplugin.inventories;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class sharedMethods {
    static ItemStack createItem(String name, Material mat, List<String> lore){
        ItemStack item=new ItemStack(mat,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
