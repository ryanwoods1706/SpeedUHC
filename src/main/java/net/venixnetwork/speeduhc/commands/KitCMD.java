package net.venixnetwork.speeduhc.commands;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.KitManager;
import net.venixnetwork.speeduhc.util.Invent;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ryan on 10/02/2017.
 */
public class KitCMD implements CommandExecutor {

    private Invent inv = new Invent();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender){
            return true;
        }
        if (StateManager.getIns().getGameState() == GameState.INGAME) {
            Player pl = (Player) sender;
            if (!KitManager.getIns().canUseKit(pl)) {
                pl.sendMessage(References.prefix + ChatColor.RED + "To use donator kits, purchase donator: store.venixnetwork.net");
                return true;
            }
            if (KitManager.getIns().hasKit(pl)){
                pl.sendMessage(ChatColor.RED + "You already have chosen a kit for this game!");
                return true;
            }
            pl.openInventory(this.inv.getKitInv());
        }else{
            sender.sendMessage(ChatColor.RED + "You can only use this command in game!");
        }
        return false;
    }
}
