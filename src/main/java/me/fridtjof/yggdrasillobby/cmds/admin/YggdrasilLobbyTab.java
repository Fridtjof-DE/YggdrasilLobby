package me.fridtjof.yggdrasillobby.cmds.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class YggdrasilLobbyTab implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> arguments = new ArrayList<String>();
        List<String> result = new ArrayList<>();

        if(sender.hasPermission("yggdrasillobby.reload") || sender.isOp()) {
            arguments.add("reload");
        }
        if(sender.hasPermission("yggdrasillobby.info") || sender.isOp()) {
            arguments.add("info");
        }


        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        } else if (args.length >= 2) {
            return result;
        }

        return null;
    }
}

