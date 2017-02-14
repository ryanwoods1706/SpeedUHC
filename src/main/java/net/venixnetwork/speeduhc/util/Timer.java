package net.venixnetwork.speeduhc.util;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.GameManager;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.managers.SpectatorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Ref;

/**
 * Created by Ryan on 09/02/2017.
 */
public class Timer extends BukkitRunnable {
    private ScoreboardManager scoreboard = new ScoreboardManager();
    private int second;
    private int alive;
    private int dmatchTime = 600;
    public void run() {
        PlayerManager.getIns().alivePlayers = 0;
        second ++;
        dmatchTime--;
        for (Player p : Bukkit.getServer().getOnlinePlayers()){
            if (!p.isDead() && !p.isFlying() && p.getGameMode().equals(GameMode.SURVIVAL) && !SpectatorManager.getIns().isSpec(p) && p.getWorld().getName().equalsIgnoreCase("speed_uhc")){
                PlayerManager.getIns().alivePlayers++;
            }
            int x = PlayerManager.getIns().getKills(p.getName());
            this.scoreboard.unrankedSidebarDisplay(p, References.sbTitle, new String[]{
                    ChatColor.GREEN.toString(),
                    ChatColor.AQUA + "Time: §f " + GameManager.getIns().getTime(second),
                    ChatColor.BLACK.toString(),
                    ChatColor.AQUA + "Players Alive: §f" + PlayerManager.getIns().getAlivePlayers(),
                    ChatColor.AQUA + "Watching: §f" + SpectatorManager.getIns().getSpecs().size(),
                    ChatColor.AQUA + "Your Kills: §f" + x,
                    ChatColor.AQUA.toString(),
                    ChatColor.AQUA + "DeathMatch in: §f" + GameManager.getIns().getTime(dmatchTime),
                    ChatColor.RED.toString(),
                    ChatColor.GREEN + "@TheVenixNetwork"
            });
        }
        if (second == 5 * 60){
            Bukkit.broadcastMessage(References.prefix + "§aPvP is now §c§lENABLED!");
            Bukkit.getServer().getWorld("speed_uhc").setPVP(true);
        }
        if (second == 5 * 60){
            Bukkit.broadcastMessage(References.prefix + "§aDeathmatch will begin in §c§l5 §r§aMinutes!");
        }
        if (second == 6 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will begin in §c§l4 §r§aMinutes!");
        }
        if (second == 7 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will being in §c§l3 §r§aMinutes!");
        }
        if (second == 8 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will begin in §c§l2 §r§aMinutes!");
        }
        if (second == 9 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aDeathmatch will begin in §c§l1 §r§aMinutes!");
        }
        if (second == 10 * 60){
            Bukkit.broadcastMessage(References.prefix + ChatColor.RED + "§aNow starting §c§lDEATHMATCH!");
            GameManager.getIns().startDeathMatch();
        }
        if (StateManager.getIns().getGameState() == GameState.DEATHMATCH){
            for (Player p : Bukkit.getServer().getOnlinePlayers()){
                int x = PlayerManager.getIns().getKills(p.getName());
                this.scoreboard.unrankedSidebarDisplay(p, References.sbTitle, new String[]{
                        ChatColor.GREEN.toString(),
                        ChatColor.AQUA + "Time: §f " + GameManager.getIns().getTime(second),
                        ChatColor.BLACK.toString(),
                        ChatColor.AQUA + "Players Alive: §f" + PlayerManager.getIns().getAlivePlayers(),
                        ChatColor.AQUA + "Watching: §f" + SpectatorManager.getIns().getSpecs().size(),
                        ChatColor.AQUA + "Your Kills: §f" + x,
                        ChatColor.AQUA.toString(),
                        ChatColor.AQUA + "DeathMatch: §fNOW",
                        ChatColor.RED.toString(),
                        ChatColor.GREEN + "@TheVenixNetwork"
                });
            }
        }

    }
}
