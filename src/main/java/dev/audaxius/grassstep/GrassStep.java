package dev.audaxius.grassstep;

import co.aikar.commands.PaperCommandManager;
import dev.audaxius.grassstep.commands.GrassStepCommand;
import dev.audaxius.grassstep.events.GrassStepEvent;
import dev.audaxius.grassstep.events.GrassStepJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * GrassStep Module
 * https://bukkit.org/threads/grass-step-plugin.483177/
 * <p>
 * When a player moves on a dirt block, it will change to a dirt block and will spawn particles
 * periodically, also toggleable. A counter feature is present as well; counts the amount of blocks
 * changed from dirt to grass
 * <p>
 */

public class GrassStep extends JavaPlugin {

    //  define common variables
    public static GrassStep plugin;
    public static PaperCommandManager commandManager;
    public static PluginManager pluginManager;

    @Override
    public void onEnable() {
        //  define managers
        plugin = this;
        commandManager = new PaperCommandManager(this);
        pluginManager = getServer().getPluginManager();

        //  load config file
        plugin.saveDefaultConfig();

        //  register commands
        commandManager.registerCommand(new GrassStepCommand());

        //  register events
        pluginManager.registerEvents(new GrassStepEvent(), plugin);
        pluginManager.registerEvents(new GrassStepJoinEvent(), plugin);

        getLogger().info("GrassStep is enabled!");
    }

    @Override
    public void onDisable() {
        //  saves config
        plugin.saveConfig();

        getLogger().info("GrassStep is disabled!");
    }

    //  static manager getters
    public static GrassStep getPlugin() {
        return plugin;
    }

    public static PaperCommandManager getCommandManager() {
        return commandManager;
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

}
