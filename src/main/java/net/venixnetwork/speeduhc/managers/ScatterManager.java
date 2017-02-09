package net.venixnetwork.speeduhc.managers;

import net.venixnetwork.speeduhc.SpeedUHC;
import net.venixnetwork.speeduhc.enums.GameState;
import net.venixnetwork.speeduhc.enums.StateManager;
import net.venixnetwork.speeduhc.util.References;
import net.venixnetwork.speeduhc.util.Timer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ryan on 09/02/2017.
 */
public class ScatterManager {

    private static ScatterManager ins;
    private int scatterTicks = 9;
    private int toScatterInt = 1;
    private final List<Player> scatter = new ArrayList<>();
    public static ScatterManager getIns(){
        if (ins == null) {
            ins = new ScatterManager();
        }
        return ins;
    }

    public Location scatterLocation(){
        World speedW = Bukkit.getServer().getWorld("speed_uhc");
        Random ran = new Random();
        int x = ran.nextInt(750 * 2) - 750;
        int z = ran.nextInt(750 * 2) - 750;
        Location loc = new Location(speedW, x, speedW.getHighestBlockYAt(x, z), z);
        return loc;
    }
    public void startScatter(){
        Bukkit.getWorld("speed_uhc").setPVP(false);
        new BukkitRunnable(){
            @Override
            public void run(){
                if (toScatterInt == 0){
                    startGame();
                    scatter.clear();
                    this.cancel();
                }
                for (Player pl : Bukkit.getServer().getWorld(References.spawnWorld).getPlayers()) {
                    scatter.add(pl);
                }
                toScatterInt = Bukkit.getServer().getWorld(References.spawnWorld).getPlayers().size() - 1;
                try {
                    scatter.get(toScatterInt);
                } catch (IndexOutOfBoundsException e) {
                    scatter.remove(toScatterInt);
                    toScatterInt = Bukkit.getServer().getWorld(References.spawnWorld).getPlayers().size() - 1;
                }
                Player pl = scatter.get(toScatterInt);
                if (pl != null){
                    pl.teleport(scatterLocation());
                    if (PlayerManager.getIns().getPlayersPlaying().contains(pl.getName())) {
                        PlayerManager.getIns().getPlayersPlaying().add(pl.getName());
                    }
                    pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 999));
                    pl.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999, 999));
                    if (scatter.contains(pl)) {
                        scatter.remove(pl);
                    }
                    toScatterInt--;
                }


            }
        }.runTaskTimer(SpeedUHC.plugin, scatterTicks, scatterTicks);
    }

    public void startGame(){
        Bukkit.getWorld("speed_uhc").setPVP(true);
        StateManager.getIns().setGameState(GameState.INGAME);
        for (Player pl : Bukkit.getServer().getOnlinePlayers()){
            ItemStack is = new ItemStack(Material.COOKED_BEEF, 10);
            pl.getInventory().clear();
            pl.getInventory().setArmorContents(null);
            pl.setFoodLevel(20);
            pl.getInventory().addItem(is);
            pl.setTotalExperience(0);
            pl.setLevel(0);
            pl.setGameMode(GameMode.SURVIVAL);
            pl.sendMessage(ChatColor.GREEN + "You have been healed!");
            pl.setHealth(pl.getMaxHealth());
            pl.damage(0.1);
            for (PotionEffect effect : pl.getActivePotionEffects()){
                pl.removePotionEffect(effect.getType());
            }
        }
        new Timer().runTaskTimer(SpeedUHC.plugin,20 * 5, 20 * 5);
    }


}
