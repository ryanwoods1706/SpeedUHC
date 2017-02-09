package net.venixnetwork.speeduhc.managers;

import org.bukkit.entity.Player;

/**
 * Created by Ryan on 09/02/2017.
 */
public class RankManager {

    private static RankManager ins;

    public static RankManager getIns(){
        if (ins == null){
            ins = new RankManager();
        }
        return ins;
    }

    public boolean canSpec(Player pl){
        if (pl.hasPermission("rank.prime") || pl.hasPermission("rank.venix")){
            return true;
        }else{
            return false;
        }
    }
}
