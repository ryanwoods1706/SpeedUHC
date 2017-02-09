package net.venixnetwork.speeduhc.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ryan on 09/02/2017.
 */
public class PlayerManager {

    private static PlayerManager ins;
    private int playerCount = 0;
    private List<String> playersPlaying = new ArrayList<>();
    private int neededPlayers = 2;
    private HashMap<String, Integer> kills = new HashMap<String, Integer>();


    public static PlayerManager getIns(){
        if (ins == null){
            ins = new PlayerManager();
        }
        return ins;
    }

    public int getPlayerCount(){ return playerCount;}
    public List<String> getPlayersPlaying(){ return playersPlaying;}
    public int getNeededPlayers(){ return neededPlayers;}
    public int getKills(String plName){
        if (kills.containsKey(plName)){
            return kills.get(plName);
        }else{
            kills.put(plName, 0);
            return kills.get(plName);
        }
    }
    public void updateKills(String plName, int kills){
        int tempKills = getKills(plName);
        int newKills = tempKills + kills;
        this.kills.put(plName, newKills);
    }

    public void updatePlayerCount(){
        int i = 0;
        for (Player pl : Bukkit.getServer().getOnlinePlayers()){
            i++;
        }
        this.playerCount = i;
    }


}
