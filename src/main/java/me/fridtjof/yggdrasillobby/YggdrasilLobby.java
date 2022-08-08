package me.fridtjof.yggdrasillobby;

import me.fridtjof.yggdrasillobby.cmds.CommandManager;
import me.fridtjof.yggdrasillobby.events.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.fridtjof.puddingapi.bukkit.utils.Logger;
import me.fridtjof.puddingapi.bukkit.utils.Metrics;
import me.fridtjof.puddingapi.bukkit.utils.UpdateChecker;

public final class YggdrasilLobby extends JavaPlugin {

    private static YggdrasilLobby instance;

    public YggdrasilLobby() {
        instance = this;
    }

    public static YggdrasilLobby getInstance() {
        return instance;
    }

    Logger logger = new Logger(this);

    public ConfigManager configManager;

    @Override
    public void onEnable() {

        if (Bukkit.getPluginManager().getPlugin("PuddingAPI") != null) {
            logger.info("PuddingAPI has been found, hooking in!");
        } else {
            logger.warn("PuddingAPI was not found, but is needed!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            logger.info("PlaceholderAPI has been found, hooking in!");
        } else {
            logger.warn("PlaceholderAPI was not found, but is needed!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (Bukkit.getPluginManager().getPlugin("Plan") != null) {
            logger.info("Plan has been found, hooking in!");
        } else {
            logger.warn("Plan was not found, continuing without!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        configManager = new ConfigManager(this);
        new EventManager(this);
        new CommandManager(this);
        new UpdateChecker(this, 81151, "yggdrasil.update");
        new Metrics(this, 7954);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}