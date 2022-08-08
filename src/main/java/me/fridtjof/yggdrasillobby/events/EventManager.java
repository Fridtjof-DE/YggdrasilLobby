package me.fridtjof.yggdrasillobby.events;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;

public class EventManager implements Listener {

    public EventManager(JavaPlugin plugin) {
        registerListeners(plugin);
    }

    private void registerListeners(JavaPlugin plugin) {
        getServer().getPluginManager().registerEvents(new OnEntityDamageEvent(), plugin);
        getServer().getPluginManager().registerEvents(new OnEntityDamageByEntityEvent(), plugin);
        getServer().getPluginManager().registerEvents(new OnFoodLevelChangeEvent(), plugin);
        getServer().getPluginManager().registerEvents(new OnPlayerDropItemEvent(), plugin);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEvent(), plugin);
        getServer().getPluginManager().registerEvents(new OnInventoryClickEvent(), plugin);
    }
}