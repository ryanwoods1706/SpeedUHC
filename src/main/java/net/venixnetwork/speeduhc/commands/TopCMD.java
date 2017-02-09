package net.venixnetwork.speeduhc.commands;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ryan on 09/02/2017.
 */
public class TopCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender){
            return true;
        }
        Player pl = (Player) sender;
        if (!pl.hasPermission("rank.prime")){
            pl.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            pl.sendMessage(ChatColor.RED + "Visit: http://venixnetwork.net/ " + ChatColor.GRAY + "To purchase a donator rank to use this!");
            return true;
        }
        if (StateManager.getIns().getGameState() != GameState.INGAME){
            pl.sendMessage(ChatColor.RED + "You can only use this command ingame!");
            return true;
        }
        Location plLoc = pl.getLocation();
        double y = Bukkit.getWorld(plLoc.getWorld().getName()).getHighestBlockYAt(plLoc);
        pl.teleport(new Location(pl.getWorld(), plLoc.getX(), y, plLoc.getZ()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound mob.bat.takeoff " + pl.getName());

        return false;
    }
}
