package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.managers.SpectatorManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by Ryan on 11/02/2017.
 */
public class PickupListener implements Listener {

    @EventHandler
    public void onPick(PlayerPickupItemEvent e){
        Player pl = e.getPlayer();
        if (SpectatorManager.getIns().isSpec(pl)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player pl = e.getPlayer();
        if (SpectatorManager.getIns().isSpec(pl)){
            e.setCancelled(true);
        }
    }
}
