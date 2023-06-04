package org.explorehubplugin.explorehubplugin;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Utility {
    private static ArrayList<TeleportLocation> tpls = new ArrayList<>();
    public static final String CHATPREFIX = ChatColor.GREEN+"["+ChatColor.DARK_AQUA+"ExploreHub"+ChatColor.GREEN+"] ";
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

    public static HashMap<String, TeleportLocation> getLocations(){
        HashMap<String, TeleportLocation> locations = new HashMap<>();
        return locations;
    }
    public static void getLocationNames(){
        HashSet<String> locationnames = new HashSet<>();
    }




    public static TeleportLocation createTeleportLocation(String name, String desc, String block, Location location){
        TeleportLocation tpl = new TeleportLocation(name, desc, block, location);
        tpls.add(tpl);
        return tpl;
    }

    public static void deleteTeleportLocation(String name){
        for(TeleportLocation tpl : tpls){
            if(tpl.getName().equalsIgnoreCase(name)){
                tpls.remove(tpl);
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

    public static TeleportLocation updateTeleportLocation(String name, TeleportLocation newtpl){
        for(TeleportLocation tpl : tpls){
            if(tpl.getName().equalsIgnoreCase(name)){
                tpl.setDesc(newtpl.getDesc());
                tpl.setBlock(newtpl.getBlock());
                tpl.setDirection(newtpl.getDirection());
                tpl.setX(newtpl.getX());
                tpl.setY(newtpl.getY());
                tpl.setZ(newtpl.getZ());
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
}
