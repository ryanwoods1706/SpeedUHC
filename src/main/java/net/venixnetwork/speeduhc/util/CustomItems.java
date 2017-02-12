package net.venixnetwork.speeduhc.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Created by Ryan on 10/02/2017.
 */
public class CustomItems {

    public CustomItems(){
        ItemStack ist = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta im = ist.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Golden Head");
        ArrayList al = new ArrayList();
        al.add(ChatColor.DARK_PURPLE + "Some say consuming the head of a");
        al.add(ChatColor.DARK_PURPLE + "fallen foe strengthens the blood");
        al.add(References.prefix);
        im.setLore(al);
        ist.setItemMeta(im);
        ShapedRecipe sr = new ShapedRecipe(ist);
        sr.shape("EEE", "ERE", "EEE");
        sr.setIngredient('E', Material.GOLD_INGOT).setIngredient('R', Material.SKULL_ITEM, 3);
        Bukkit.getServer().addRecipe(sr);
        ShapelessRecipe glmelon = new ShapelessRecipe(new ItemStack(Material.SPECKLED_MELON, 1));
        glmelon.addIngredient(Material.GOLD_BLOCK);
        glmelon.addIngredient(Material.MELON);
        Bukkit.getServer().addRecipe(glmelon);
    }
}
