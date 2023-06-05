package org.explorehubplugin.explorehubplugin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.explorehubplugin.explorehubplugin.TeleportLocation;
import org.explorehubplugin.explorehubplugin.Utility;
import java.io.*;

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

        for(TeleportLocation l : Utility.getTpls()){
            if(l.getName().equals(args[0])){
                Utility.stderror(sender, "Specified name already exists for another TeleportLocation, choose a diffrent name");
                return true;
            }
        }

        String s = "";
        for(int i = 0; i < args.length - 1; i++){
            s = s + args[i] + " ";
        }
        s = s + args[args.length - 1];
        String[] sub = s.substring(args[0].length() + 1, s.indexOf(args[args.length - 1]) - 1).split(" ");
        if(sub.length > 10){
            Utility.stderror(sender, "Description can not be longer than 10 words");
            return true;
        }
        String[] nargs = new String[]{args[0], s.substring(args[0].length() + 1, s.indexOf(args[args.length - 1]) - 1), args[args.length - 1]};

        Player player = (Player) sender;
        if(Utility.getTpls().size() <= 54){
                if(player.hasPermission("explorehub.admin")){
                    try{
                        Utility.createTeleportLocation(nargs[0], nargs[1], nargs[2], player.getLocation());
                    } catch(IOException exc){
                        Utility.stderror(player, "Failed to save specified location "+exc);
                        return true;
                    }
                    Utility.stdout(player, "A new teleport location has been created");
                    return true;
                }
                else {
                    Utility.stderror(player, "You must be an admin to run this command");
                    return true;
                }
        }
        Utility.stderror(player, "There are already 54 registered locations, delete location(s) to add new ones");
        return true;
    }
}
