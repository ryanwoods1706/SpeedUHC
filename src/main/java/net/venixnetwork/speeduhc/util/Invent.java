package net.venixnetwork.speeduhc.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Created by Ryan on 10/02/2017.
 */
public class Invent {
    private Inventory kitInv = Bukkit.createInventory(null, 9 * 3, ChatColor.AQUA + "Donator Kits");

    public Inventory getKitInv(){

        //DONOR KITS
        kitInv.setItem(0, new ItemBuilder(Material.PAPER).setName(ChatColor.AQUA+ "Donator Kits!").toItemStack());
        kitInv.setItem(1, new ItemBuilder(Material.STONE_PICKAXE).setName(ChatColor.RED + "Donator Kit 1").toItemStack());
        //PRIMEKITS
        kitInv.setItem(9, new ItemBuilder(Material.PAPER).setName(ChatColor.AQUA + "Prime Kits!").toItemStack());
        kitInv.setItem(10, new ItemBuilder(Material.IRON_CHESTPLATE).setName(ChatColor.RED + "Prime Kit 1").toItemStack());
        //VENIXKITS
        kitInv.setItem(18, new ItemBuilder(Material.PAPER).setName(ChatColor.AQUA + "Venix Kits!").toItemStack());
        kitInv.setItem(19, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName(ChatColor.RED + "Venix Kit 1").toItemStack());
        return this.kitInv;
    }
}
