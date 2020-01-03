package dev.audaxius.grassstep.events;

import dev.audaxius.grassstep.GrassStep;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GrassStepJoinEvent implements Listener {

    //  define common variables
    GrassStep plugin = GrassStep.getPlugin();
    FileConfiguration config = plugin.getConfig();

    @EventHandler
    public void checkConfigValues(PlayerJoinEvent event) {
        //  define common variables
        Player player = event.getPlayer();
        String playerName = config.getString("players." + player.getName());

        //  if player is not found in config, define config variables, then saves config
        if (playerName == null) {
            config.set("players." + player.getName() + ".enabled", false);
            config.set("players." + player.getName() + ".counter", 0);
            plugin.saveConfig();
        }
    }
}
