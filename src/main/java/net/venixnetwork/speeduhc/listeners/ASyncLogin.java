package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.managers.RankManager;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Created by Ryan on 09/02/2017.
 */
public class ASyncLogin implements Listener {

    @EventHandler
    public void onASyncJoin(AsyncPlayerPreLoginEvent e){
        if (Bukkit.getServer().getOnlinePlayers().size() == 25 && StateManager.getIns().getGameState() == GameState.LOBBY){
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, References.prefix + ChatColor.RED + "Game is full!");
        }
        if (StateManager.getIns().getGameState() == GameState.SCATTERING){
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, References.prefix + ChatColor.RED + "The game is currently scattering!");
        }
        if (StateManager.getIns().getGameState() == GameState.GENERATING){
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, References.prefix + ChatColor.RED + "The server is currently generating!");
        }

    }
}
