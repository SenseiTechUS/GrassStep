package dev.audaxius.grassstep.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.audaxius.grassstep.GrassStep;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@CommandAlias("grassstep|gs|gstep")
public class GrassStepCommand extends BaseCommand {

    //  define common variables
    static GrassStep plugin = GrassStep.getPlugin();
    static FileConfiguration config = plugin.getConfig();

    @Default
    @Syntax("/grassstep")
    @Description("Toggle GrassStep")
    @CommandPermission("grassstep.toggle")
    public static void onGrassStepToggle(Player player) {
        boolean playerEnabled = config.getBoolean("players." + player.getName() + ".enabled");

        if (playerEnabled) {
            config.set("players." + player.getName() + ".enabled", false);
            player.sendMessage(ChatColor.GREEN + "GrassStep " + ChatColor.RED + "disabled");
        } else {
            config.set("players." + player.getName() + ".enabled", true);
            player.sendMessage(ChatColor.GREEN + "GrassStep enabled");
        }

        plugin.saveConfig();
    }

    @Subcommand("enable|e|t|true")
    @Description("Enable GrassStep")
    @CommandPermission("grassstep.enable")
    public static void onGrassStepEnable(Player player) {
        config.set("players." + player.getName() + ".enabled", true);
        player.sendMessage(ChatColor.GREEN + "GrassStep enabled");

        plugin.saveConfig();
    }

    @Subcommand("disable|d|f|false")
    @Description("Disable GrassStep")
    @CommandPermission("grassstep.disable")
    public static void onGrassStepDisable(Player player) {
        config.set("players." + player.getName() + ".enabled", false);
        player.sendMessage(ChatColor.GREEN + "GrassStep " + ChatColor.RED + "disabled");
        plugin.saveConfig();
    }

    @Subcommand("counter|count|amount|c|a")
    @Description("Amount of dirt blocks changed to grass blocks")
    @CommandPermission("grassstep.counter")
    public static void countGrassStep(Player player) {
        player.sendMessage(ChatColor.GREEN + "You have changed " + config.getInt("players." + player.getName() + ".counter") + " dirt blocks to grass blocks");
    }
}
