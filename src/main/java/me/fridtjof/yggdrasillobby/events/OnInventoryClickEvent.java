package me.fridtjof.yggdrasillobby.events;

import me.fridtjof.puddingapi.bukkit.utils.BungeeUtils;
import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import me.fridtjof.yggdrasillobby.utils.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.logging.Logger;

public class OnInventoryClickEvent implements Listener {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    Logger logger = plugin.getLogger();

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {

        if(event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            Lobby.HotBarItems(player);
            Lobby.CompassItems(player);
            Lobby.HeadItems(player);
            Lobby.EggItems(player);

            if(event.getCurrentItem() != null) {
                if(event.getCurrentItem().hasItemMeta()) {

                    if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.compass.enable") &&
                            event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.name"))) {
                        player.openInventory(Lobby.CompassInventory);
                        event.setCancelled(true);
                    }

                    if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.head.enable") &&
                            event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.name"))) {
                        player.openInventory(Lobby.HeadInventory);
                        event.setCancelled(true);
                    }

                    if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.egg.enable") &&
                            event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.configManager.lobbyConfig.getConfig().getString("lobby.egg.name"))) {
                        player.openInventory(Lobby.EggInventory);
                        event.setCancelled(true);
                    }

                    if(event.getClickedInventory().equals(Lobby.CompassInventory)) {
                        for(int i = 0; i < 4; i++) {
                            if (plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.compass.enable") &&
                                    event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.slot_" + i + ".name"))) {
                                BungeeUtils.sendPlayerToServer(player, plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.slot_" + i + ".server"), plugin);
                                //player.closeInventory();
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
