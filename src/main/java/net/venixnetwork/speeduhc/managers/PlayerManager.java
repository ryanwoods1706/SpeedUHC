package net.venixnetwork.speeduhc.managers;


import com.mysql.jdbc.Buffer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ryan on 09/02/2017.
 */
public class PlayerManager {

    private static PlayerManager ins;
    //private List<String> playersPlaying = new ArrayList<>();
    private int neededPlayers = 16;
    public int alivePlayers = 0;
    private List<String> players = new ArrayList<>();
    private String winner = "";
    private List<String> playersLeft = new ArrayList<>();
    private HashMap<String, Integer> kills = new HashMap<String, Integer>();


    public static PlayerManager getIns(){
        if (ins == null){
            ins = new PlayerManager();
        }
        return ins;
    }


    //public List<String> getPlayersPlaying(){ return playersPlaying;}
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

    /*public void updatePlayerCount(){
        alivePlayers = 0;
        players.clear();
        for (Player pl : Bukkit.getServer().getOnlinePlayers()){
            if (!pl.isDead() && pl.getGameMode().equals(GameMode.SURVIVAL) && !pl.isFlying() && pl.getLocation().getWorld().getName().equals("speed_uhc")){
                alivePlayers++;
                players.add(pl.getName());
            }
        }
        if (players.size() == 1){
            winner = players.get(0);
        }
    }*/


    public int getAlivePlayers(){
        return alivePlayers;
    }

    public String getWinner(){ return winner;}

    public List<String> gettPlayersLeft(){return playersLeft;}


}
