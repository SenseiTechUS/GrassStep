package dev.audaxius.grassstep.events;

import dev.audaxius.grassstep.GrassStep;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class GrassStepEvent implements Listener {

    //  define common variables
    GrassStep plugin = GrassStep.getPlugin();
    FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void onStepOnDirtBlock(PlayerMoveEvent event) {

        //  define common variables
        Player player = event.getPlayer();
        Block block = player.getLocation().subtract(0, 1, 0).getBlock();
        Block partialBlockCheck = player.getLocation().getBlock();

        //  returns if the player has grassStep enabled
        if (!config.getBoolean("players." + player.getName() + ".enabled"))
            return;

        //  returns if the player is in a non-passable block, such as slabs
        if (!partialBlockCheck.isPassable())
            return;

        //  returns if block underneath player isn't a dirt block
        if (!block.getType().equals(Material.DIRT))
            return;

        //  gets player's grassStep counter
        int grassCounter = config.getInt("players." + player.getName() + ".counter");

        //  sets dirt block to a grass block and increments grassCounter
        block.setType(Material.GRASS_BLOCK);
        grassCounter++;

        //  saves player's grassStep counter
        config.set("players." + player.getName() + ".counter", grassCounter);
        plugin.saveConfig();

        //  spawns particles when grassCounter is divisible by three
        if (grassCounter % 3 == 0)
            player.spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 8, 0.5, 0, 0.5);

    }
}
