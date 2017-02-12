package net.venixnetwork.speeduhc.managers;

import com.sun.org.apache.regexp.internal.RE;
import net.venixnetwork.speeduhc.SpeedUHC;
import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

/**
 * Created by Ryan on 09/02/2017.
 */
public class WorldManager {

    private static WorldManager ins;

    public static WorldManager getIns(){
        if (ins == null){
            ins = new WorldManager();
        }
        return ins;
    }


    public void makeWorld(){
        Bukkit.getServer().createWorld(new WorldCreator("speed_uhc"));
    }
    public boolean delete(File file) {
        if (file.isDirectory()) {
            for (File subfile : file.listFiles()) {
                if (!delete(subfile)) {
                    return false;
                }
            }
        }
        return file.delete();
    }
    public void deleteWorld(World world, String name){
        World map = Bukkit.createWorld(new WorldCreator(name).environment(World.Environment.NORMAL).type(WorldType.NORMAL));
        Bukkit.unloadWorld(map, false);
        delete(world.getWorldFolder());
        delete(world.getWorldFolder());
    }


    public void genWorld(){
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder:wb speed_uhc setcorners 750 -750 -750 750");
        Bukkit.broadcastMessage(ChatColor.GREEN + "Successfully set border to 250x250");
        setWalls(750, Material.BEDROCK, 3);
        new BukkitRunnable(){
            @Override
            public void run(){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder:wb speed_uhc fill 250");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "worldborder:wb speed_uhc fill confirm");
            }
        }.runTaskLater(SpeedUHC.plugin, 20 * 5);

    }

    public void setWalls(int size, Material mat, int h) {
        World w = Bukkit.getWorld("speed_uhc");
        Location loc = new Location(w, 0, 59, 0);
        int i = h;
        while (i < h + h) {
            for (int x = loc.getBlockX() - size; x <= loc.getBlockX() + size; x++) {
                for (int y = 59; y <= 59; y++) {
                    for (int z = loc.getBlockZ() - size; z <= loc.getBlockZ() + size; z++) {
                        if ((x == loc.getBlockX() - size) || (x == loc.getBlockX() + size) || (z == loc.getBlockZ() - size) || (z == loc.getBlockZ() + size)) {
                            Location loc1 = new Location(w, x, y, z);
                            loc1.setY(w.getHighestBlockYAt(loc1));
                            loc1.getBlock().setType(mat);
                        }
                    }
                }
            }
            i++;
        }
        Location l = new Location(w, 0, w.getHighestBlockYAt(0, 0), 0);
        if (l.getBlock().getType() == Material.BEDROCK) {
            l.getBlock().setType(Material.AIR);
        }
    }

    public void endSpeedGame(){
        StateManager.getIns().setMOTD("Restarting");
        for (Player pl : Bukkit.getServer().getOnlinePlayers()){
            pl.kickPlayer(ChatColor.RED + "Thanks for playing this Speed UHC!");
        }
        StateManager.getIns().setGameState(GameState.PREPARING);
        if (Bukkit.getServer().getWorld("speed_uhc") != null){
            deleteWorld(Bukkit.getWorld("speed_uhc"), "speed_uhc");
        }
        createSpeedGame();
    }
    public void createSpeedGame(){
        if (Bukkit.getWorld("speed_uhc") == null){
            StateManager.getIns().setGameState(GameState.GENERATING);
            makeWorld();
            new BukkitRunnable(){
                @Override
                public void run(){
                    genWorld();
                }
            }.runTaskLater(SpeedUHC.plugin, 20 * 5);

        }
    }
}
