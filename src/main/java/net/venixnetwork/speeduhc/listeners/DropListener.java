package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.managers.SpectatorManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Ryan on 10/02/2017.
 */
public class DropListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player pl = e.getPlayer();
        if (SpectatorManager.getIns().getSpecs().contains(pl.getName())){
            e.setCancelled(true);
        }
    }
}
