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
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.EntityType;
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
        Player victim = e.getEntity();
        e.setDeathMessage(References.prefix + ChatColor.RED + pl.getName() + ChatColor.GRAY + " Has died!");
        if (e.getEntity().getKiller() != null){
            if (e.getEntity().getKiller() instanceof Player){
                Player killer = e.getEntity().getKiller();
                PlayerManager.getIns().updateKills(killer.getName(), 1);
                if (PlayerManager.getIns().getAlivePlayers() == 1){
                    Player winner = Bukkit.getPlayer(PlayerManager.getIns().getWinner());
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
            if (PlayerManager.getIns().getAlivePlayers() == 1){
                Player winner = Bukkit.getPlayer(PlayerManager.getIns().getWinner());
                Bukkit.broadcastMessage(References.prefix + ChatColor.GREEN + "Congratulations to " + ChatColor.YELLOW + winner.getName() + ChatColor.GREEN + " on winning!");
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        WorldManager.getIns().endSpeedGame();
                    }
                }.runTaskLater(SpeedUHC.plugin, 20 * 5);
            }

        }
        if (e.getEntity().getType().equals(EntityType.PLAYER)) {
            victim.getLocation().getBlock().setType(Material.NETHER_FENCE);
            victim.getWorld().getBlockAt(victim.getLocation().add(0.0D, 1.0D, 0.0D)).setType(Material.SKULL);
            Skull skull = (Skull) victim.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getState();
            skull.setOwner(victim.getName());
            skull.update();
            Block block = victim.getLocation().add(0.0D, 1.0D, 0.0D).getBlock();
            block.setData((byte) 1);
        }
        pl.spigot().respawn();
        World spawn = Bukkit.getWorld(References.spawnWorld);
        pl.teleport(spawn.getSpawnLocation());
        if (RankManager.getIns().canSpec(pl)){
            SpectatorManager.getIns().addSpec(pl);
        }else{
            new BukkitRunnable(){
                public void run(){
                    pl.kickPlayer(ChatColor.RED + "You've died, thanks for playing!");

                }
            }.runTaskLater(SpeedUHC.plugin, 20 * 5);
        }


    }
}
