package me.fridtjof.yggdrasillobby;

import me.fridtjof.puddingapi.bukkit.utils.Logger;
import me.fridtjof.puddingapi.bukkit.utils.YamlConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    JavaPlugin plugin;

    private Logger logger;
    public YamlConfig lobbyConfig, dataFile, messagesFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = new Logger(plugin);
        reloadConfigs();
    }

    public void reloadConfigs() {
        loadLobbyConfig();
        loadDataFile();
        loadMessagesFile();
    }

    public void loadDataFile() {
        dataFile = new YamlConfig(plugin.getDataFolder(), "data");
        dataFile.getConfig().options().header("This is the lobby data file - Don't touch!");

        //

        dataFile.getConfig().options().copyDefaults(true);
        dataFile.save();
        logger.info("Successfully (re)loaded data.yml");
    }

    public void loadLobbyConfig() {
        lobbyConfig = new YamlConfig(plugin.getDataFolder(), "lobby_config");
        lobbyConfig.getConfig().options().header("This is the lobby configuration file - Read the wiki for help!");

        lobbyConfig.getConfig().addDefault("lobby.no_fall_damage", false);
        lobbyConfig.getConfig().addDefault("lobby.no_pvp", false);
        lobbyConfig.getConfig().addDefault("lobby.no_pve", false);
        lobbyConfig.getConfig().addDefault("lobby.no_hunger", false);
        lobbyConfig.getConfig().addDefault("lobby.no_damage", false);
        lobbyConfig.getConfig().addDefault("lobby.no_item_dropping", false);

        lobbyConfig.getConfig().addDefault("lobby.compass.enable", false);
        lobbyConfig.getConfig().addDefault("lobby.compass.name", "§9§lNavigator");
        lobbyConfig.getConfig().addDefault("lobby.compass.slot", 0);
        lobbyConfig.getConfig().addDefault("lobby.compass.slot_1.name", "§cSurvival");
        lobbyConfig.getConfig().addDefault("lobby.compass.slot_3.name", "§bCreative");
        lobbyConfig.getConfig().addDefault("lobby.compass.slot_1.server", "Survival");
        lobbyConfig.getConfig().addDefault("lobby.compass.slot_3.server", "Creative");
        lobbyConfig.getConfig().addDefault("lobby.compass.slot_1.material", "APPLE");
        lobbyConfig.getConfig().addDefault("lobby.compass.slot_3.material", "DIAMOND_BLOCK");

        lobbyConfig.getConfig().addDefault("lobby.head.enable", false);
        lobbyConfig.getConfig().addDefault("lobby.head.name", "§e§lPlayer Profile");
        lobbyConfig.getConfig().addDefault("lobby.head.slot", 8);
        lobbyConfig.getConfig().addDefault("lobby.head.slot_10.name", "§9%player_name%");
        lobbyConfig.getConfig().addDefault("lobby.head.slot_12.name", "§l§eSpielzeit: §a%plan_player_time_total%");
        lobbyConfig.getConfig().addDefault("lobby.head.slot_14.name", "§l§bRegistriert: §a%plan_player_registered%");
        lobbyConfig.getConfig().addDefault("lobby.head.slot_16.name", "§l§cKill/Death-Rate: §a%plan_player_kill_death_ratio%");
        lobbyConfig.getConfig().addDefault("lobby.head.slot_10.material", "PLAYER_HEAD");
        lobbyConfig.getConfig().addDefault("lobby.head.slot_12.material", "CLOCK");
        lobbyConfig.getConfig().addDefault("lobby.head.slot_14.material", "OAK_SAPLING");
        lobbyConfig.getConfig().addDefault("lobby.head.slot_16.material", "IRON_SWORD");

        lobbyConfig.getConfig().addDefault("lobby.egg.enable", false);
        lobbyConfig.getConfig().addDefault("lobby.egg.name", "§d§lEaster Eggs");
        lobbyConfig.getConfig().addDefault("lobby.egg.slot", 4);

        lobbyConfig.getConfig().options().copyDefaults(true);
        lobbyConfig.save();
        logger.info("Successfully (re)loaded lobby_config.yml");
    }

    public void loadMessagesFile() {
        messagesFile = new YamlConfig(plugin.getDataFolder(), "messages");
        messagesFile.getConfig().options().header("This is the lobby localization file.");

        //

        messagesFile.getConfig().options().copyDefaults(true);
        messagesFile.save();
        logger.info("Successfully (re)loaded messages.yml");
    }
}