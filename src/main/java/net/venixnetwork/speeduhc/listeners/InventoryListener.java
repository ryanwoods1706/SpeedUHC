package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.managers.KitManager;
import net.venixnetwork.speeduhc.managers.RankManager;
import net.venixnetwork.speeduhc.managers.SpectatorManager;
import net.venixnetwork.speeduhc.util.References;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Ryan on 10/02/2017.
 */
public class InventoryListener implements Listener {

    @EventHandler
    public void onInv(InventoryClickEvent e){
        Player pl = (Player) e.getWhoClicked();
        if (e.getClickedInventory() != null){
            if (e.getCurrentItem() != null){
                if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Donator Kits")){
                    if (e.getCurrentItem().getType() != Material.AIR) {
                        if (e.getCurrentItem().getType() != (Material.PAPER)) {
                            e.setCancelled(true);
                            if (KitManager.getIns().getPlayerKits().contains(pl.getName())) {
                                pl.closeInventory();
                                pl.sendMessage(ChatColor.RED + "You already have a kit active!");
                            } else {
                                switch (e.getCurrentItem().getType()) {
                                    case STONE_PICKAXE:
                                        if (KitManager.getIns().basicKit(pl)) {
                                            KitManager.getIns().donatorKit(pl);
                                            break;
                                        }
                                        pl.sendMessage(References.noPerms);
                                        break;
                                    case IRON_CHESTPLATE:
                                        if (KitManager.getIns().primeKit(pl)) {
                                            KitManager.getIns().primeKits(pl);
                                            break;
                                        }
                                        pl.sendMessage(References.noPerms);
                                        break;
                                    case DIAMOND_CHESTPLATE:
                                        if (KitManager.getIns().masterKit(pl)) {
                                            KitManager.getIns().venixItems(pl);
                                            break;
                                        }
                                        pl.sendMessage(References.noPerms);
                                        break;
                                }
                            }
                        }
                    }
                }
                if (SpectatorManager.getIns().isSpec(pl)){
                    e.setCancelled(true);
                }
            }
        }
    }
}
