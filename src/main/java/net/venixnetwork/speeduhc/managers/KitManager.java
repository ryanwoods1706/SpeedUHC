package net.venixnetwork.speeduhc.managers;

import net.venixnetwork.speeduhc.util.References;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashSet;

/**
 * Created by Ryan on 10/02/2017.
 */
public class KitManager {

    private static KitManager ins;
    private HashSet<String> playerKits = new HashSet<>();


    public static KitManager getIns(){
        if (ins == null){
            ins = new KitManager();
        }
        return ins;
    }

    public HashSet<String> getPlayerKits(){ return playerKits;
    }
    public boolean hasKit(Player pl){
        if (playerKits.contains(pl.getName())){
            return true;
        }
        return false;
    }
    public boolean canUseKit(Player pl){
        if (pl.hasPermission("rank.donator") || pl.hasPermission("rank.prime") || pl.hasPermission("rank.venix")){
            return true;
        }
        return false;
    }
    public boolean basicKit(Player pl){
        if (pl.hasPermission("rank.donator")){
            return true;
        }
        return false;
    }
    public boolean primeKit(Player pl){
        if (pl.hasPermission("rank.prime")){
            return true;
        }
        return false;
    }
    public boolean masterKit(Player pl){
        if (pl.hasPermission("rank.venix")){
            return true;
        }
        return false;
    }

    /**
     *"Donator" rank kits
     */
    public void donatorKit(Player pl){
        PlayerInventory pi = pl.getInventory();
        playerKits.add(pl.getName());
        pi.addItem(new ItemStack(Material.STONE_SWORD));
        pi.addItem(new ItemStack(Material.IRON_PICKAXE));
        pi.addItem(new ItemStack(Material.STONE_SPADE));
        pi.addItem(new ItemStack(Material.STONE_AXE));
        pl.updateInventory();
        pl.sendMessage(References.prefix + "§aYou've been given the §cDonator §akit!");
    }
    /**
     * "Prime" rank kits
     */
    public void primeKits(Player pl){
        PlayerInventory pi = pl.getInventory();
        playerKits.add(pl.getName());
        pi.addItem(new ItemStack(Material.IRON_SWORD));
        pi.addItem(new ItemStack(Material.GOLD_INGOT, 7));
        pi.addItem(new ItemStack(Material.IRON_INGOT, 15));
        pi.addItem(new ItemStack(Material.APPLE, 10));
        pi.addItem(new ItemStack(Material.COOKED_BEEF, 32));
        pl.updateInventory();
        pl.sendMessage(References.prefix + "§aYou've been given the §cPrime §akit!");
    }
    /**
     * Venix Kits
     */
    public void venixItems(Player pl){
        PlayerInventory pi = pl.getInventory();
        playerKits.add(pl.getName());
        pi.addItem(new ItemStack(Material.OBSIDIAN, 4));
        pi.addItem(new ItemStack(Material.BOOK, 1));
        pi.addItem(new ItemStack(Material.GOLD_INGOT, 15));
        pi.addItem(new ItemStack(Material.IRON_INGOT, 15));
        pl.updateInventory();
        pl.sendMessage(References.prefix + "§aYou've been given the §cVenix §akit!");
    }

}
