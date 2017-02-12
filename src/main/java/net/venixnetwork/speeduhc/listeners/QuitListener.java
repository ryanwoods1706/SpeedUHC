package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.SpeedUHC;
import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.managers.SpectatorManager;
import net.venixnetwork.speeduhc.managers.WorldManager;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ryan on 09/02/2017.
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player pl = e.getPlayer();
        e.setQuitMessage(References.prefix + ChatColor.RED + pl.getName() + ChatColor.GRAY + " Has left the game!");
        PlayerInventory pi = pl.getInventory();
        for (ItemStack item : pi.getContents()){
            if (item != null){
                e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
            }
        }
        for (ItemStack item : pi.getArmorContents()){
            if (item.getType() != Material.AIR){
                e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), item);
            }
        }
        if (StateManager.getIns().getGameState() == GameState.SCATTERING){
            if (PlayerManager.getIns().getPlayersLeft().contains(pl.getName())){
                PlayerManager.getIns().getPlayersLeft().remove(pl.getName());
            }
        }
        if (StateManager.getIns().getGameState() == GameState.INGAME){
            if (PlayerManager.getIns().getPlayersLeft().contains(pl.getName())){
                PlayerManager.getIns().getPlayersLeft().remove(pl.getName());
            }
            if (Bukkit.getWorld("speed_uhc").getPlayers().size() == 1){
                Player winner = Bukkit.getWorld("speed_uhc").getPlayers().get(0);
                Bukkit.broadcastMessage(References.prefix + ChatColor.GREEN + "Congratulations to " + ChatColor.YELLOW + winner.getName() + ChatColor.GREEN + " on winning!");
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        WorldManager.getIns().endSpeedGame();
                    }
                }.runTaskLater(SpeedUHC.plugin, 20 * 5);
            }
            if (PlayerManager.getIns().getPlayersLeft().size() == 1){
                Player winner = Bukkit.getPlayer(PlayerManager.getIns().getPlayersLeft().get(0));
                Bukkit.broadcastMessage(References.prefix + ChatColor.GREEN + "Congratulations to " + ChatColor.YELLOW + winner.getName() + ChatColor.GREEN + " on winning!");
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        WorldManager.getIns().endSpeedGame();
                    }
                }.runTaskLater(SpeedUHC.plugin, 20 * 5);
            }
        }
        if (SpectatorManager.getIns().getSpecs().contains(pl.getName())){
            SpectatorManager.getIns().getSpecs().remove(pl.getName());
            System.out.println("Quitlistener line 60, removed player from specs");
        }

    }
}
