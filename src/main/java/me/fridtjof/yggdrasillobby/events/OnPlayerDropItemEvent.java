package me.fridtjof.yggdrasillobby.events;

import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnPlayerDropItemEvent implements Listener {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {

        if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.no_item_dropping")) {
            Player player = event.getPlayer();
            if(player.isOp() || player.hasPermission("yggdrasil.lobby.bypass_no_item_dropping")) {

            } else {
                event.setCancelled(true);
            }
        }
    }
}
