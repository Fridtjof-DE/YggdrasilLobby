package me.fridtjof.yggdrasillobby.cmds;

import me.fridtjof.yggdrasillobby.cmds.admin.EasterEggCMD;
import me.fridtjof.yggdrasillobby.cmds.admin.YggdrasilLobbyCmd;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

    public CommandManager(JavaPlugin plugin) {
        registerCommands(plugin);
    }

    private void registerCommands(JavaPlugin plugin) {

        //ADMIN
        plugin.getCommand("easteregg").setExecutor(new EasterEggCMD());
        plugin.getCommand("yggdrasillobby").setExecutor(new YggdrasilLobbyCmd());

        //MOD

        //PLAYER

        //CHEATS
    }
}
