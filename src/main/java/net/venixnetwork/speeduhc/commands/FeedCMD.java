package net.venixnetwork.speeduhc.commands;

import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ryan on 09/02/2017.
 */
public class FeedCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender){
            return true;
        }
        Player pl = (Player) sender;
        if (pl.hasPermission("rank.venix") || pl.hasPermission("rank.prime")){
            pl.setFoodLevel(20);
            pl.getWorld().playEffect(pl.getLocation(), Effect.HEART, 5, 5);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "playsound random.anvil_use " + pl.getName());
            pl.sendMessage(References.prefix + "§aNom nom nom!");
        }else{
            pl.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            pl.sendMessage(ChatColor.RED + "Purchase a rank at http://venixnetwork.net/ to use these commands");
        }

        return false;
    }
}
