package me.fridtjof.yggdrasillobby.events;

import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class OnFoodLevelChangeEvent implements Listener {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {

        if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.no_hunger")) {
            if(event.getEntity() instanceof Player) {
                event.setCancelled(true);
                ((Player) event.getEntity()).setFoodLevel(20);
            }
        }
    }
}
