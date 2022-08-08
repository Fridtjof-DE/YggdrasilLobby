package me.fridtjof.yggdrasillobby.cmds.admin;


import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class YggdrasilLobbyCmd implements CommandExecutor {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    public YggdrasilLobbyCmd() {

        plugin.getCommand("yggdrasillobby").setTabCompleter(new YggdrasilLobbyTab());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0 || args[0].equals("info")) {
            if(sender.hasPermission("yggdrasillobby.info") || sender.isOp()) {
                sender.sendMessage(plugin.getName() + " v" + plugin.getDescription().getVersion() + " for API v" + plugin.getDescription().getAPIVersion());
            }

        } else if(args.length == 1) {
            if(args[0].equals("reload")) {
                if(sender.hasPermission("yggdrasillobby.reload") || sender.isOp()) {
                    sender.sendMessage("§eReloading the plugin...");
                    plugin.configManager.reloadConfigs();
                    sender.sendMessage("§aReload complete!");
                }
            } else {
                sender.sendMessage("§cUnknown args!");
            }
        }

        return false;
    }
}
