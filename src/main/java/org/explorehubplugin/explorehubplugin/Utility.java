package org.explorehubplugin.explorehubplugin;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.io.*;
import java.util.*;

/**
 * Die Klasse beinhaltet statische Methoden und Attribute welche über das gesamte Projekt öfter gebraucht werden.
 * @author Moritz Syllaba
 * @version 2023-06-05
 */
public class Utility {
    private static ArrayList<TeleportLocation> tpls = new ArrayList<>();
    public static final String CHATPREFIX = ChatColor.DARK_AQUA+"["+ChatColor.DARK_PURPLE+"HeavyMC"+ChatColor.DARK_AQUA+"] ";
    public static void broadcaststdout(String message){
        for (Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(Utility.CHATPREFIX+ChatColor.YELLOW+message);
        }
    }
    public static void stderror(CommandSender sender, String message){
        sender.sendMessage(Utility.CHATPREFIX+ChatColor.RED+message);
    }

    public static void stdout(CommandSender sender, String message){
        sender.sendMessage(Utility.CHATPREFIX+ChatColor.GRAY+message);
    }

    public static ArrayList<TeleportLocation> getTpls(){
        return tpls;
    }

    public static TeleportLocation createTeleportLocation(String name, String desc, String block, Location location) throws IOException{
        TeleportLocation tpl = new TeleportLocation(name, desc, block, location);
        tpls.add(tpl);
        Utility.saveTeleportLocations();
        return tpl;
    }

    public static void deleteTeleportLocation(String name) throws IOException{
        for(TeleportLocation tpl : tpls){
            if(tpl.getName().equalsIgnoreCase(name)){
                tpls.remove(tpl);
                Utility.saveTeleportLocations();
                break;
            }
        }
    }

    public static TeleportLocation readTeleportLocation(String name){
        for(TeleportLocation tpl : tpls){
            if(tpl.getName().equalsIgnoreCase(name)){
                return tpl;
            }
        }
        return null;
    }

    public static TeleportLocation updateTeleportLocation(String name, TeleportLocation newtpl) throws IOException{
        for(TeleportLocation tpl : tpls){
            if(tpl.getName().equalsIgnoreCase(name)){
                tpl.setDesc(newtpl.getDesc());
                tpl.setBlock(newtpl.getBlock());
                tpl.setDirection(newtpl.getDirection());
                tpl.setX(newtpl.getX());
                tpl.setY(newtpl.getY());
                tpl.setZ(newtpl.getZ());
                Utility.saveTeleportLocations();
                return tpl;
            }
        }
        return null;
    }

    public static void saveTeleportLocations() throws IOException {
        Gson gson = new Gson();
        File file = new File(Explorehubplugin.getPlugin().getDataFolder().getAbsolutePath() + "/teleportlocations.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        FileWriter writer = new FileWriter(file, false); //!!!!!
        gson.toJson(tpls, writer);
        writer.flush();
        writer.close();
    }

    public static void loadTeleportLocations() throws IOException{
        Gson gson = new Gson();
        File file = new File(Explorehubplugin.getPlugin().getDataFolder().getAbsolutePath() + "/teleportlocations.json");
        if(file.exists()){
            FileReader reader = new FileReader(file);
            TeleportLocation[] l = gson.fromJson(reader, TeleportLocation[].class);
            tpls = new ArrayList<>(Arrays.asList(l));
        }
    }



    public static ItemStack createItem(String name, Material mat, List<String> lore){
        ItemStack item=new ItemStack(mat,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
