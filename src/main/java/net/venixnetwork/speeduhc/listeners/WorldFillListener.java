package net.venixnetwork.speeduhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Ryan on 09/02/2017.
 */
public class WorldFillListener implements Listener {

    @EventHandler
    public void onFill(com.wimbli.WorldBorder.Events.WorldBorderFillFinishedEvent e){
        Bukkit.getServer().shutdown();
    }
}
