package net.venixnetwork.speeduhc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Ryan on 10/02/2017.
 */
public class ConsumeListener implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e){
        final Player pl = e.getPlayer();
        if (e.getItem() != null) {
            if (e.getItem().getType() == Material.GOLDEN_APPLE && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Golden Head")) {
                pl.removePotionEffect(PotionEffectType.REGENERATION);
                pl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));

            }
        }
    }
}
