package net.venixnetwork.speeduhc.managers;

import com.sun.org.apache.regexp.internal.RE;
import net.venixnetwork.speeduhc.SpeedUHC;
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

    private void unloadWorld(){
        for (Player pl : Bukkit.getServer().getOnlinePlayers()){
            if (pl.getWorld().getName().equals("speed_uhc")){
                pl.kickPlayer(References.prefix + ChatColor.RED + "Server is restarting!");
            }
        }
        Bukkit.getServer().unloadWorld("speed_uhc", false);
    }
    public boolean isWorldReady(){
        if (Bukkit.getWorld("speed_uhc") == null){
            return false;
        }
        return true;
    }
    public void makeWorld(){
        Bukkit.getServer().createWorld(new WorldCreator("speed_uhc"));
    }
    private boolean deleteWorld(File path)
    {
        try
        {
            if (path.exists())
            {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteWorld(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
            return path.delete();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public void restartWorld()
    {
        System.out.println("Restarting world...");
        int i = 0;
        try
        {
            unloadWorld();
            deleteWorld(Bukkit.getWorld("speed_uhc").getWorldFolder());
            System.out.println("Deleted world");
            Bukkit.getServer().shutdown();
        }
        catch (Exception e)
        {
            System.out.println("Exception, shutting down....");
            Bukkit.getServer().shutdown();
        }
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
}
