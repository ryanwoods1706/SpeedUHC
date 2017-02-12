package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.SpectatorManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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
        if (StateManager.getIns().getGameState() == GameState.INGAME || StateManager.getIns().getGameState() == GameState.DEATHMATCH){
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL){
                e.setCancelled(true);
            }
        }
        if (StateManager.getIns().getGameState() == GameState.SCATTERING){
            e.setCancelled(true);
        }
        if (e.getEntity().getType().equals(EntityType.PLAYER)){
            Player pl = (Player) e.getEntity();
            if (SpectatorManager.getIns().getSpecs().contains(pl.getName())){
                e.setCancelled(true);
            }
        }

    }
}
