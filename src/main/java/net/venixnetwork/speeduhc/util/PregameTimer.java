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
        seconds-=1;
        if (StateManager.getIns().getGameState() == GameState.SCATTERING || StateManager.getIns().getGameState() == GameState.INGAME){
            this.cancel();
        }
        if (seconds == 20){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l20 §rSeconds");
        }
        if (seconds == 15){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l15 Seconds");
        }
        if (seconds == 10){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l10 Seconds");
        }
        if (seconds == 5){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l15 Seconds");
        }
        if (seconds == 4){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l4 Seconds");
        }
        if (seconds == 3){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l3 Seconds");
        }
        if (seconds == 2){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l2 Seconds");
        }
        if (seconds == 1){
            Bukkit.broadcastMessage(References.prefix + "§aGame starting in §c§l1 Seconds");
        }
        if (seconds == 0){
            StateManager.getIns().setGameState(GameState.SCATTERING);
            ScatterManager.getIns().startScatter();
        }
    }
}
