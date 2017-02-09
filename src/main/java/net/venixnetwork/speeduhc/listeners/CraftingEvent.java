package net.venixnetwork.speeduhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Ryan on 09/02/2017.
 */
public class CraftingEvent implements Listener {

    public void makeGoldenHead()
    {
        ItemStack head = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta meta = head.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Golden Head");
        meta.setLore(Arrays.asList(new String[] { ChatColor.AQUA + "Eat for even more regen!" }));
        head.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(head);
        recipe.shape(new String[] { "###", "#@#", "###" });

        recipe.setIngredient('#', Material.GOLD_INGOT);
        recipe.setIngredient('@', Material.SKULL_ITEM, 3);

        Bukkit.getServer().addRecipe(recipe);
    }
}
