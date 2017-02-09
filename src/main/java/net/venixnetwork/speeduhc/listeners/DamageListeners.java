package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Ryan on 09/02/2017.
 */
public class DamageListeners implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (StateManager.getIns().getGameState() == GameState.LOBBY){
            e.setCancelled(true);
        }
        if (StateManager.getIns().getGameState() == GameState.INGAME){
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL){
                e.setCancelled(true);
            }
        }
        if (StateManager.getIns().getGameState() == GameState.SCATTERING){
            e.setCancelled(true);
        }
    }
}
