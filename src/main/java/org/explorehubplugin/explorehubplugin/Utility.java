package org.explorehubplugin.explorehubplugin;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Utility{
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
                break;
            }
        }
        Utility.saveTeleportLocations();
    }

    public static TeleportLocation readTeleportLocation(String name) throws IOException{
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
        File file = new File(Explorehubplugin.getPlugin().getDataFolder().getAbsolutePath() + "/teleportlocations.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Gson gson = new Gson();
        String text = "";
        FileReader reader = new FileReader(file);
        gson.toString();
        reader.read();
        reader.close();
    }
}
