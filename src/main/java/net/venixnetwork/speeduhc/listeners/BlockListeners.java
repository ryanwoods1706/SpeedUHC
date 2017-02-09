package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Ryan on 09/02/2017.
 */
public class BlockListeners implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player pl = e.getPlayer();
        if (StateManager.getIns().getGameState() == GameState.INGAME) {
            if (e.getBlock().getType() == Material.GOLD_ORE) {
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                Bukkit.getWorld(pl.getWorld().getName()).dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
            }
            if (e.getBlock().getType() == Material.IRON_ORE) {
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                Bukkit.getWorld(pl.getWorld().getName()).dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
            }
            if (e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.LOG_2){
                loop(e.getBlock(), pl);
                e.getBlock().getWorld().playEffect(e.getBlock().getLocation().add(0.0D, 2.0D, 2.0D), Effect.CLOUD, 20);

            }
        }else{
            e.setCancelled(true);
            pl.sendMessage(ChatColor.RED + "You cannot break blocks now!");
        }
    }

    private void loop(Block block1, Player p) {
        for (BlockFace blockface : BlockFace.values()) {
            if ((block1.getRelative(blockface).getType().equals(Material.LOG)) || (block1.getRelative(blockface).getType().equals(Material.LOG_2))) {
                Block block = block1.getRelative(blockface);
                p.getInventory().addItem(new ItemStack(block.getType()));
                block.setType(Material.AIR);
                loop(block, p);
            }
        }
    }


    @EventHandler
    public void onDecay(LeavesDecayEvent e)
    {
        ItemStack apple = new ItemStack(Material.APPLE);
        Bukkit.getWorld(e.getBlock().getLocation().getWorld().getName()).dropItemNaturally(e.getBlock().getLocation(), apple);
    }


}
