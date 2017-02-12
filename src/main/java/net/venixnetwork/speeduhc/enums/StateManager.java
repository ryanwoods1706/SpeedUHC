package net.venixnetwork.speeduhc.enums;

import net.minecraft.server.v1_7_R4.DedicatedServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;

/**
 * Created by Ryan on 09/02/2017.
 */
public class StateManager {

    private static StateManager ins;
    private GameState gameState = GameState.PREPARING;

    public static StateManager getIns(){
        if (ins == null){
            ins = new StateManager();
        }
        return ins;
    }


    public GameState getGameState(){ return gameState;}
    public void setGameState(GameState gameState){
        this.gameState = gameState;

    }
    public void setMOTD(String motd){
        DedicatedServer dedi = (((CraftServer) Bukkit.getServer()).getHandle().getServer());
        String msg = dedi.getMotd();
        dedi.setMotd(motd);
    }
}
