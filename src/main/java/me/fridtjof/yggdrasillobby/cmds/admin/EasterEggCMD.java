package me.fridtjof.yggdrasillobby.cmds.admin;

import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EasterEggCMD implements CommandExecutor {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if (sender.hasPermission("yggdrasil.cmd.easteregg") || sender.isOp()) {

                if (args.length == 0 || args.length == 1 || args.length == 2 || args.length == 3) {
                    sender.sendMessage(plugin.configManager.messagesFile.getConfig().getString("error.not_enough_args"));
                }

                if (args.length == 4) {

                    String operator = args[0];
                    String name = args[1];
                    String material = args[2];
                    int slot = Integer.parseInt(args[3]);

                    if(operator.equalsIgnoreCase("create")) {

                        sender.sendMessage(plugin.configManager.dataFile.getConfig().getInt("lobby.easteregg." + slot + ".name") + "");

                        if(plugin.configManager.dataFile.getConfig().getString("lobby.easteregg.slot_" + slot + ".name") != "") {

                            Player player = (Player) sender;
                            Location location = player.getLocation();
                            int posX = location.getBlockX();
                            int posY = location.getBlockY();
                            int posZ = location.getBlockZ();

                            plugin.configManager.dataFile.getConfig().set("lobby.easteregg.slot_" + slot + ".name", name);
                            plugin.configManager.dataFile.getConfig().set("lobby.easteregg.slot_" + slot + ".material", material);
                            plugin.configManager.dataFile.getConfig().set("lobby.easteregg.slot_" + slot + ".x", posX);
                            plugin.configManager.dataFile.getConfig().set("lobby.easteregg.slot_" + slot + ".y", posY);
                            plugin.configManager.dataFile.getConfig().set("lobby.easteregg.slot_" + slot + ".z", posZ);

                            plugin.configManager.dataFile.getConfig().options().copyDefaults(true);
                            plugin.configManager.dataFile.save();
                        } else {
                            sender.sendMessage(plugin.configManager.messagesFile.getConfig().getString("error.slot_occupied"));
                        }
                    }
                }
            } else {
                sender.sendMessage(plugin.configManager.messagesFile.getConfig().getString("error.no_permission"));
            }
        } else {
            sender.sendMessage(plugin.configManager.messagesFile.getConfig().getString("error.player_only"));
        }
        return false;
    }
}
