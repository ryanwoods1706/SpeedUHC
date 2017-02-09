package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.SpeedUHC;
import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.managers.RankManager;
import net.venixnetwork.speeduhc.managers.SpectatorManager;
import net.venixnetwork.speeduhc.managers.WorldManager;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ryan on 09/02/2017.
 */
public class DeathListener implements Listener {

    @EventHandler
    public void onPDeath(PlayerDeathEvent e){
        final Player pl = e.getEntity().getPlayer();
        e.setDeathMessage(References.prefix + ChatColor.RED + pl.getName() + ChatColor.GRAY + " Has died!");
        PlayerManager.getIns().updatePlayerCount();
        pl.spigot().respawn();
        if (RankManager.getIns().canSpec(pl)){
            SpectatorManager.getIns().addSpec(pl);
        }else{
            new BukkitRunnable(){
                public void run(){
                    pl.kickPlayer(ChatColor.RED + "You've died, thanks for playing!");

                }
            }.runTaskLater(SpeedUHC.plugin, 20 * 5);
        }
        if (e.getEntity().getKiller() != null){
            if (e.getEntity().getKiller() instanceof Player){
                Player killer = e.getEntity().getKiller();
                Player victim = e.getEntity();
                if (PlayerManager.getIns().getPlayersPlaying().contains(victim.getName())){
                    PlayerManager.getIns().getPlayersPlaying().remove(victim.getName());
                }
                PlayerManager.getIns().updateKills(killer.getName(), 1);
                if (PlayerManager.getIns().getPlayersPlaying().size() == 1){
                    Bukkit.broadcastMessage(References.prefix + ChatColor.GREEN + "Congratulations to " + ChatColor.YELLOW + killer.getName() + ChatColor.GREEN + " on winning!");
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            WorldManager.getIns().endSpeedGame();
                        }
                    }.runTaskLater(SpeedUHC.plugin, 20 * 5);
                }
            }
        }else{
            if (PlayerManager.getIns().getPlayersPlaying().contains(e.getEntity().getName())){
                PlayerManager.getIns().getPlayersPlaying().remove(e.getEntity().getName());
            }
            if (PlayerManager.getIns().getPlayersPlaying().size() == 1){
                Player winner = Bukkit.getPlayer(PlayerManager.getIns().getPlayersPlaying().get(0));
                Bukkit.broadcastMessage(References.prefix + ChatColor.GREEN + "Congratulations to " + ChatColor.YELLOW + winner.getName() + ChatColor.GREEN + " on winning!");
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        WorldManager.getIns().endSpeedGame();
                    }
                }.runTaskLater(SpeedUHC.plugin, 20 * 5);
            }
        }

    }
}
