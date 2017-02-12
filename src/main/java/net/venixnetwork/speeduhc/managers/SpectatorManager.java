package net.venixnetwork.speeduhc.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 09/02/2017.
 */
public class SpectatorManager {

    private static SpectatorManager ins;
    private List<String> specs = new ArrayList<String>();
    public static SpectatorManager getIns(){
        if (ins == null){
            ins = new SpectatorManager();
        }
        return ins;
    }

    public List<String> getSpecs(){ return specs;}
    public boolean isSpec(Player pl){
        if (specs.contains(pl.getName())){
            return true;
        }
        return false;
    }
    public void hidePlayer(Player player)
    {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.hidePlayer(player);
        }
    }

    private void showPlayer(Player player)
    {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.showPlayer(player);
        }
    }

    public void addSpec(Player pl){
        hidePlayer(pl);
        specs.add(pl.getName());
        pl.getInventory().clear();
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN  + "Spectating Tool");
        item.setItemMeta(meta);
        pl.getInventory().setItem(4, item);
        pl.setGameMode(GameMode.CREATIVE);
        pl.setAllowFlight(true);
        pl.setFlying(true);
    }



}
