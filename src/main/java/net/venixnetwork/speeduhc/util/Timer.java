package net.venixnetwork.speeduhc.util;

import net.venixnetwork.speeduhc.managers.GameManager;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.managers.SpectatorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Ref;

/**
 * Created by Ryan on 09/02/2017.
 */
public class Timer extends BukkitRunnable {
    private ScoreboardManager scoreboard = new ScoreboardManager();
    private int second;
    public void run() {
        second +=5;
        for (Player p : Bukkit.getServer().getOnlinePlayers()){
            int x = PlayerManager.getIns().getKills(p.getName());
            this.scoreboard.unrankedSidebarDisplay(p, References.sbTitle, new String[]{
                    ChatColor.GREEN.toString(),
                    ChatColor.AQUA + "Time: §f " + GameManager.getIns().getTime(second),
                    ChatColor.BLACK.toString(),
                    ChatColor.AQUA + "Players Alive: §f" + PlayerManager.getIns().getPlayersPlaying().size(),
                    ChatColor.AQUA + "Watching: §f" + SpectatorManager.getIns().getSpecs().size(),
                    ChatColor.AQUA + "Your Kills: §f" + x,
                    ChatColor.AQUA.toString(),
                    ChatColor.AQUA + "DeathMatch Time: §f15m",
                    ChatColor.RED.toString(),
                    ChatColor.GREEN + "@TheVenixNetwork"
            });
        }
        if (second == 5 * 60){
            Bukkit.broadcastMessage(References.prefix + "§aPvP is now §cENABLED!");
            Bukkit.getServer().getWorld("speed_uhc").setPVP(true);
        }
        if (second == 10 * 60){
            Bukkit.broadcastMessage(References.prefix + "§aDeathmatch will being in §c§l5 §r§cMinutes!");
        }
        if (second == 11 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will being in §c§l4 §r§cMinutes!");
        }
        if (second == 12 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will being in §c§l3 §r§cMinutes!");
        }
        if (second == 13 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will being in §c§l2 §r§cMinutes!");
        }
        if (second == 14 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will being in §c§l1 §r§cMinutes!");
        }
        if (second == 15 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aNow starting §c§lDEATHMATCH!");
            GameManager.getIns().startDeathMatch();
        }

    }
}
