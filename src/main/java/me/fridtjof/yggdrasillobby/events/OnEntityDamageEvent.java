package me.fridtjof.yggdrasillobby.events;

import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamageEvent implements Listener {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    @EventHandler
    public void onRespawn(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {

            if(event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.no_fall_damage")) {
                    event.setCancelled(true);
                }
            }

            if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.no_damage")) {
                event.setCancelled(true);
            }
        }
    }
}
