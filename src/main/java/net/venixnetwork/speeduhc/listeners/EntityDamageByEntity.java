package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.managers.SpectatorManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Ryan on 12/02/2017.
 */
public class EntityDamageByEntity implements Listener {

    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e){
        if (e.getEntity().getType().equals(EntityType.PLAYER)){
            Player pl = (Player) e.getEntity();
            if (e.getDamager().getType().equals(EntityType.PLAYER)){
                Player damager = (Player) e.getDamager();
                if (SpectatorManager.getIns().isSpec(damager)){
                    e.setCancelled(true);
                }
            }
        }
    }
}
