package net.venixnetwork.speeduhc.managers;


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

    public List<String> getPlayersLeft(){return playersLeft;}


}
