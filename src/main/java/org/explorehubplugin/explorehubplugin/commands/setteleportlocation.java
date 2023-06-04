package org.explorehubplugin.explorehubplugin.commands;

import com.google.common.io.ByteStreams;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.explorehubplugin.explorehubplugin.TeleportLocation;
import org.explorehubplugin.explorehubplugin.Utility;
import org.json.simple.JSONObject;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class setteleportlocation implements CommandExecutor{
    /**
     * Der Command /setteleportlocation erlaubt es Admins einen Teleportpunkt der Liste an Teleportpunkten
     * (resources/locations/teleportlocations.txt) hinzuzufügen.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        //Falls Befehl von Konsole ausgegeben wird
        if(!(sender instanceof Player)){
            Utility.stderror(sender, "You must be an Player to run commands!");
            return true;
        }
        Player player = (Player) sender;
        ArrayList<Integer> errors = this.checkArgs(args);
        if(errors.size() == 0){
            if(player.hasPermission("explorehub.admin")){
                try{
                    Utility.createTeleportLocation(args[0], args[1], args[2], player.getLocation());
                    for(TeleportLocation l : Utility.getTpls()){
                        Utility.stdout(player, l.getName());
                    }

                } catch(IOException exc){
                    Utility.stderror(player, "Failed to save specified location"+exc);
                    return true;
                }
                Utility.stdout(player, "A new teleport location was created");
                return true;
            }
            else {
                Utility.stderror(player, "You must be an admin to run this command");
                return true;
            }
        }
        else if(errors.contains(-1)) {
            Utility.stderror(player, "Wrong number of parameters (3)");
            return true;
        }
        else {
            String errmsg = "Wrong arguments for parameters ";
            for(int i = 0; i < errors.size(); i++){
                if(i == errors.size() - 1){
                    errmsg = errmsg + errors.get(i);
                }
                else{
                    errmsg = errmsg + errors.get(i)+", ";
                }
            }
            Utility.stderror(player, errmsg);
            return true;
        }
    }

    public ArrayList<Integer> checkArgs(String[] args){
        ArrayList<Integer> errors = new ArrayList<>();
        if(args.length != 3){
            errors.add(-1);
            return errors;
        }
        if(args.length >= 1 && args.length <= 3){
            for(int i = 0; i < 2; i++){
                if(args[i] == null || args[i].equals("")){
                    errors.add(i+1);
                }
            }
            try{
                Material.valueOf(args[2].toUpperCase());
            } catch(IllegalArgumentException exc){
                errors.add(3);
            }
        }
        return errors;
    }
}
