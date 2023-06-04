package org.explorehubplugin.explorehubplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.explorehubplugin.explorehubplugin.Utility;
import org.explorehubplugin.explorehubplugin.inventories.DeleteConfirmInventory;

public class DeleteConfirmTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (!(sender instanceof Player)) {
            Utility.stderror(sender, "You must be an Player to run commands!");
            return true;
        }
        Player player = (Player) sender;
        DeleteConfirmInventory gui=new DeleteConfirmInventory();
        player.openInventory(gui.getInventory());
        return true;
    }
}
