package me.fridtjof.yggdrasillobby.events;

import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import me.fridtjof.yggdrasillobby.utils.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        Lobby.HotBarItems(player);
        Lobby.CompassItems(player);
        Lobby.HeadItems(player);
        Lobby.EggItems(player);

        if(event.getItem() != null && event.getItem().hasItemMeta()) {

            if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.compass.enable")
                    && event.getItem().getItemMeta().getDisplayName().equals(plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.name"))) {
                player.openInventory(Lobby.CompassInventory);
                event.setCancelled(true);
            }

            if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.head.enable")
                    && event.getItem().getItemMeta().getDisplayName().equals(plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.name"))) {
                player.openInventory(Lobby.HeadInventory);
                event.setCancelled(true);
            }

            if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.egg.enable")
                    && event.getItem().getItemMeta().getDisplayName().equals(plugin.configManager.lobbyConfig.getConfig().getString("lobby.egg.name"))) {
                player.openInventory(Lobby.EggInventory);
                event.setCancelled(true);
            }
        }
    }
}
