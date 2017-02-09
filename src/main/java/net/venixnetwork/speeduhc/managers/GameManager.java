package net.venixnetwork.speeduhc.managers;

import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.Material;

/**
 * Created by Ryan on 09/02/2017.
 */
public class GameManager {

    private static GameManager ins;

    public static GameManager getIns(){
        if (ins == null) {
            ins = new GameManager();
        }
        return ins;
    }

    public String getTime(int secondTime) {
        int hours = secondTime / 3600;
        int secondsLeft = secondTime - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10) {
            //formattedTime = formattedTime + "0";
        }
        //formattedTime = formattedTime + hours + ":";
        if (minutes < 10) {
            formattedTime = formattedTime + "0";
        }
        formattedTime = formattedTime + minutes + ":";
        if (seconds < 10) {
            formattedTime = formattedTime + "0";
        }
        formattedTime = formattedTime + seconds;

        return formattedTime;
    }

    public void startDeathMatch(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder:wb speed_uhc setcorners 100 -100 -100 100");
        WorldManager.getIns().setWalls(100, Material.BEDROCK, 3);
        Bukkit.broadcastMessage(References.prefix + "§cBorder is now §c§l100 x 100");
    }
}
