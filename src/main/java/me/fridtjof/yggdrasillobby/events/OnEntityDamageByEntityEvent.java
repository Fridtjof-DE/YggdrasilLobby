package me.fridtjof.yggdrasillobby.events;

import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamageByEntityEvent implements Listener {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    @EventHandler
    public void onRespawn(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {

            if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.no_pvp")) {
                if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    if(event.getDamager() instanceof Player) {
                        Player damager = (Player) event.getDamager();
                        if(damager.isOp() || damager.hasPermission("yggdrasil.lobby.bypass_no_pvp")) {

                        } else {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        } else {
            if (plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.no_pve")) {
                if (event.getDamager() instanceof Player) {
                    Player damager = (Player) event.getDamager();
                    if (damager.isOp() || damager.hasPermission("yggdrasil.lobby.bypass_no_pve")) {

                    } else {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}