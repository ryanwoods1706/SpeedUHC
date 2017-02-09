package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by Ryan on 09/02/2017.
 */
public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player pl = e.getPlayer();
        if (StateManager.getIns().getGameState() == GameState.INGAME || StateManager.getIns().getGameState() == GameState.DEATHMATCH){
            if (RankManager.getIns().canSpec(pl)){
                e.setResult(PlayerLoginEvent.Result.ALLOWED);
            }else{
                e.setKickMessage(ChatColor.RED + "You cannot join an active game! To spectate purchase a rank at http://venixnetwork.net/");
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            }
        }
    }
}
