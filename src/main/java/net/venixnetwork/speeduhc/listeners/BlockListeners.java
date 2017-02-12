package net.venixnetwork.speeduhc.listeners;

import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.managers.SpectatorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ExperienceOrb;
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
        Block ore = e.getBlock();
        if (StateManager.getIns().getGameState() == GameState.INGAME) {
            if (SpectatorManager.getIns().isSpec(pl)){
                e.setCancelled(true);
                return;
            }
            if (e.getBlock().getType() == Material.GOLD_ORE) {
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                Bukkit.getWorld(pl.getWorld().getName()).dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                e.getBlock().getWorld().spawn(e.getBlock().getLocation(), ExperienceOrb.class).setExperience(3);
            }
            if (e.getBlock().getType() == Material.IRON_ORE) {
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                Bukkit.getWorld(pl.getWorld().getName()).dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                e.getBlock().getWorld().spawn(e.getBlock().getLocation(), ExperienceOrb.class).setExperience(2);
            }
            if (e.getBlock().getType() == Material.GRAVEL){
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                Bukkit.getWorld(pl.getWorld().getName()).dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.FLINT));

            }
            if (e.getBlock().getType() == Material.LOG || e.getBlock().getType() == Material.LOG_2){
                loop(e.getBlock(), pl);
                e.getBlock().getWorld().playEffect(e.getBlock().getLocation().add(0.0D, 2.0D, 2.0D), Effect.CLOUD, 20);
            }
            switch (e.getBlock().getType()){
                case GRAVEL:
                    e.setCancelled(true);
                    ore.setType(Material.AIR);
                    ore.getState().update();
                    ore.getWorld().dropItemNaturally(ore.getLocation(), new ItemStack(Material.FLINT, 2));
                    break;
                case IRON_ORE:
                    e.setCancelled(true);
                    ore.setType(Material.AIR);
                    ore.getState().update();
                    ore.getWorld().dropItemNaturally(ore.getLocation(), new ItemStack(Material.IRON_INGOT, 2));
                    ore.getWorld().spawn(ore.getLocation(), ExperienceOrb.class).setExperience(2);
                    break;
                case COAL:
                    e.setCancelled(true);
                    ore.setType(Material.AIR);
                    ore.getState().update();
                    ore.getWorld().dropItemNaturally(ore.getLocation(), new ItemStack(Material.COAL, 2));
                    ore.getWorld().spawn(ore.getLocation(), ExperienceOrb.class).setExperience(1);
                    break;
                case REDSTONE_ORE:
                    e.setCancelled(true);
                    ore.setType(Material.AIR);
                    ore.getState().update();
                    ore.getWorld().dropItemNaturally(ore.getLocation(), new ItemStack(Material.REDSTONE, 2));
                    break;
                case GOLD_ORE:
                    e.setCancelled(true);
                    ore.setType(Material.AIR);
                    ore.getState().update();
                    ore.getWorld().dropItemNaturally(ore.getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
                    ore.getWorld().spawn(ore.getLocation(), ExperienceOrb.class).setExperience(4);
                    break;
                case DIAMOND_ORE:
                    e.setCancelled(true);
                    ore.setType(Material.AIR);
                    ore.getState().update();
                    ore.getWorld().dropItemNaturally(ore.getLocation(), new ItemStack(Material.DIAMOND, 2));
                    ore.getWorld().spawn(ore.getLocation(), ExperienceOrb.class).setExperience(6);
                    break;
                case EMERALD_ORE:
                    e.setCancelled(true);
                    ore.setType(Material.AIR);
                    ore.getState().update();
                    ore.getWorld().dropItemNaturally(ore.getLocation(), new ItemStack(Material.EMERALD, 2));
                    ore.getWorld().spawn(ore.getLocation(), ExperienceOrb.class).setExperience(6);
                    break;
            }
        }else{
            if (!pl.isOp()) {
                e.setCancelled(true);
                pl.sendMessage(ChatColor.RED + "You cannot break blocks now!");
            }
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
        if (Math.random() * 100 <= 10){
            ItemStack apple = new ItemStack(Material.APPLE);
            Bukkit.getWorld(e.getBlock().getLocation().getWorld().getName()).dropItemNaturally(e.getBlock().getLocation(), apple);
        }

    }


}
