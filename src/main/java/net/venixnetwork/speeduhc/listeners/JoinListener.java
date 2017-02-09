package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

/**
 * Created by Ryan on 09/02/2017.
 */
public class JoinListener implements Listener{

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player pl = e.getPlayer();
        PlayerManager.getIns().updatePlayerCount();
        World world = Bukkit.getWorld("spawn");
        if (StateManager.getIns().getGameState() == GameState.LOBBY){
            pl.teleport(world.getSpawnLocation());
            pl.getInventory().clear();
            pl.getInventory().setArmorContents(null);
        }
        pl.sendMessage("§8§l§m---------------------------------------");
        pl.sendMessage("§6§l                    VenixSpeedUHC");
        pl.sendMessage("");
        pl.sendMessage("§8» §7PvP Enabled after 5 Minutes!");
        pl.sendMessage("§8» §cTeaming is NOT allowed!");
        pl.sendMessage("§8» §7If you have a donator rank you can use /top or /feed!");
        pl.sendMessage("");
        pl.sendMessage("§8§l§m---------------------------------------");
        e.setJoinMessage(References.prefix + "§a" + pl.getName() + " §7 has joined the game!");
        for (PotionEffect effect : pl.getActivePotionEffects()){
            pl.removePotionEffect(effect.getType());
        }
    }
}
