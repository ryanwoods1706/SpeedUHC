package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Created by Ryan on 09/02/2017.
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player pl = e.getPlayer();
        PlayerManager.getIns().updatePlayerCount();
        e.setQuitMessage(ChatColor.RED + pl.getName() + ChatColor.GRAY + " Has left the game!");
        PlayerInventory pi = pl.getInventory();
        for (ItemStack item : pi.getContents()){
            if (item != null){
                e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
            }
        }
        if (StateManager.getIns().getGameState() == GameState.INGAME){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + pl.getName() + ChatColor.GRAY + " has died!");
        }

    }
}
