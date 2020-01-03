package dev.audaxius.grassstep;

import co.aikar.commands.PaperCommandManager;
import dev.audaxius.grassstep.commands.GrassStepCommand;
import dev.audaxius.grassstep.events.GrassStepEvent;
import dev.audaxius.grassstep.events.GrassStepJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
