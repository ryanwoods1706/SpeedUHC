package net.venixnetwork.speeduhc.util;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.ScatterManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ryan on 09/02/2017.
 */
public class PregameTimer extends BukkitRunnable {


    private int seconds = 21;
    @Override
    public void run(){
        seconds--;
        if (seconds == 20){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l20 §r§aSeconds");
        }
        if (seconds == 15){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l15 §aSeconds");
        }
        if (seconds == 10){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l10 §aSeconds");
        }
        if (seconds == 5){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l5 §aSeconds");
        }
        if (seconds == 4){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l4 §aSeconds");
        }
        if (seconds == 3){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l3 §aSeconds");
        }
        if (seconds == 2){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l2 §aSeconds");
        }
        if (seconds == 1){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l1 §aSeconds");
        }
        if (seconds == 0){
            StateManager.getIns().setGameState(GameState.SCATTERING);
            ScatterManager.getIns().startScatter();
            this.cancel();
        }
    }
}
