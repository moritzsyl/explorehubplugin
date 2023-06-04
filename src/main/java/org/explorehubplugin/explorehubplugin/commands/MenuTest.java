package org.explorehubplugin.explorehubplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.explorehubplugin.explorehubplugin.Explorehubplugin;
import org.explorehubplugin.explorehubplugin.Utility;

import java.util.ArrayList;
import java.util.List;

public class MenuTest implements Listener, CommandExecutor {
    private String invName="TeleportMenu";

    public MenuTest(Explorehubplugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (!event.getView().getTitle().equals(invName)){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        int slot=event.getSlot();
        event.setCancelled(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (!(sender instanceof Player)) {
            Utility.stdout(sender, "You must be an Player to run commands!");
            return true;
        }
        Player player=(Player) sender;
        Inventory inv = Bukkit.createInventory(player,9*3,invName);
        inv.setItem(11,getItem(new ItemStack(Material.DIAMOND_SWORD), "&9PVP", "descriptipm", "&anblablabal"));
        inv.setItem(13,getItem(new ItemStack(Material.BEACON), "&9PVP", "descriptipm123", "&anblablabasdasdayyxyxxcyxcyxal"));
        inv.setItem(15,getItem(new ItemStack(Material.GRASS), "&9PVP", "descriptipm312", "&anblablhhhhhhhhabal"));
        player.openInventory(inv);
        return true;
    }
    private ItemStack getItem(ItemStack item, String name, String ... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        List<String> lores=new ArrayList<>();
        for(String s : lore){
            lores.add(ChatColor.translateAlternateColorCodes('&',s));
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }
}
