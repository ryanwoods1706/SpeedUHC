package net.venixnetwork.speeduhc;

import net.venixnetwork.speeduhc.commands.FeedCMD;
import net.venixnetwork.speeduhc.commands.TopCMD;
import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.listeners.*;
import net.venixnetwork.speeduhc.managers.PlayerManager;
import net.venixnetwork.speeduhc.managers.ScatterManager;
import net.venixnetwork.speeduhc.managers.WorldManager;
import net.venixnetwork.speeduhc.util.PregameTimer;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.plaf.nimbus.State;
import java.io.File;

/**
 * Created by Ryan on 09/02/2017.
 */
public class SpeedUHC extends JavaPlugin {
    public static SpeedUHC plugin;
    public void onEnable(){
        plugin = this;
        registerCom();
        registerEvents();
        checkOnline();
        new BukkitRunnable(){
            @Override
            public void run(){
                if (Bukkit.getWorld("speed_uhc") == null){
                    WorldManager.getIns().makeWorld();
                }
            }
        }.runTaskLater(this, 20 * 5);
        StateManager.getIns().setGameState(GameState.LOBBY);
    }

    public void onDisable(){
        plugin = null;

    }

    private void registerCom(){
        getCommand("feed").setExecutor(new FeedCMD());
        getCommand("top").setExecutor(new TopCMD());

    }

    private void registerEvents(){
        PluginManager plm = Bukkit.getPluginManager();
        plm.registerEvents(new ASyncLogin(), this);
        plm.registerEvents(new BlockListeners(), this);
        plm.registerEvents(new CraftingEvent(), this);
        plm.registerEvents(new DeathListener(), this);
        plm.registerEvents(new JoinListener(), this);
        plm.registerEvents(new LoginListener(), this);
        plm.registerEvents(new QuitListener(), this);
        plm.registerEvents(new HealthListener(), this);
        plm.registerEvents(new WorldFillListener(), this);
        plm.registerEvents(new DamageListeners(), this);
    }


    private void checkOnline(){
        new BukkitRunnable(){
            public void run(){
                if (StateManager.getIns().getGameState() == GameState.LOBBY) {
                    if (PlayerManager.getIns().getPlayerCount() >= PlayerManager.getIns().getNeededPlayers()) {
                        new PregameTimer().runTaskTimer(plugin, 20, 20);
                    }else{
                        Bukkit.broadcastMessage(References.prefix + "§aPlayers Needed to start the game §b" + PlayerManager.getIns().getPlayerCount() + "/" + PlayerManager.getIns().getNeededPlayers());
                    }
                }
            }
        }.runTaskTimer(this, 0, 20 * 10);
    }
}
