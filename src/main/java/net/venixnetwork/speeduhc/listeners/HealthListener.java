package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Ryan on 09/02/2017.
 */
public class HealthListener implements Listener {

    @EventHandler
    public void onRegen(EntityRegainHealthEvent e)
    {
        if ((e.getEntity() instanceof Player))
        {
            Player player = (Player)e.getEntity();
            if ((!e.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.MAGIC)) ||
                    (!e.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.MAGIC_REGEN)) ||
                    (!e.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.REGEN))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHungerLoss(FoodLevelChangeEvent e)
    {
        if ((e.getEntity() instanceof Player))
        {
            Player player = (Player)e.getEntity();
            if (StateManager.getIns().getGameState() == GameState.LOBBY)
            {
                e.setCancelled(true);
                player.setFoodLevel(20);
                player.setSaturation(20.0F);
            }
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e)
    {
        Player player = e.getPlayer();
        if ((e.getItem().getType() == Material.GOLDEN_APPLE) &&
                (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Golden Head")))
        {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1800, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1800, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1));
        }
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e)
    {
        if (!(e.getEntity() instanceof Player)) {
            e.setCancelled(true);
        }
    }
}
