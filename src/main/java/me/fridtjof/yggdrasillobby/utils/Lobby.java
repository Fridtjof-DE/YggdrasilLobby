package me.fridtjof.yggdrasillobby.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.fridtjof.puddingapi.bukkit.items.PlayerHead;
import me.fridtjof.puddingapi.bukkit.player.PlayerUtils;
import me.fridtjof.yggdrasillobby.YggdrasilLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Lobby {

    static YggdrasilLobby plugin = YggdrasilLobby.getInstance();

    public static Inventory CompassInventory = Bukkit.createInventory(
            null, InventoryType.HOPPER, plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.name"));

    public static Inventory HeadInventory = Bukkit.createInventory(
            null, InventoryType.CHEST, plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.name"));

    public static Inventory EggInventory = Bukkit.createInventory(
            null, InventoryType.CHEST, plugin.configManager.lobbyConfig.getConfig().getString("lobby.egg.name"));

    public static void HotBarItems(Player player) {

        if(plugin.configManager.lobbyConfig.getConfig().getBoolean("lobby.compass.enable")) {

            if(player.hasPermission("lobby.bypass_lobby_item_clearing") || player.isOp()) {
                if(!PlayerUtils.hasEmptyInventory(player)) {
                    return;
                }
            }

            player.getInventory().clear();

            Lobby.HotBarItemsHelper(new ItemStack(Material.COMPASS), plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.name"),
                    plugin.configManager.lobbyConfig.getConfig().getInt("lobby.compass.slot"), player);

            Lobby.HotBarItemsHelper(new ItemStack(Material.DRAGON_EGG), plugin.configManager.lobbyConfig.getConfig().getString("lobby.egg.name"),
                    plugin.configManager.lobbyConfig.getConfig().getInt("lobby.egg.slot"), player);

            Lobby.HotBarItemsHelper(PlayerHead.getSkullFromOwner(player.getName()), plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.name"),
                    plugin.configManager.lobbyConfig.getConfig().getInt("lobby.head.slot"), player);
        }
    }

    public static void CompassItems(Player player) {

        for(int i = 0; i <= 4; i++) {

            if(plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.slot_" + i + ".material") != null &&
                    !plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.slot_" + i + ".name").equals("") &&
                    !plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.slot_" + i + ".server").equals("")) {

                Material material = Material.getMaterial(plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.slot_" + i + ".material"));

                ItemStack stack = new ItemStack(material);
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(plugin.configManager.lobbyConfig.getConfig().getString("lobby.compass.slot_" + i + ".name"));
                stack.setItemMeta(meta);

                CompassInventory.setItem(i, stack);
            }
        }
    }

    public static void HeadItems(Player player) {

        for(int i = 0; i < 28; i++) {

            if(plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.slot_" + i + ".material") != null &&
                    !plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.slot_" + i + ".name").equals("") &&
                    !plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.slot_" + i + ".material").equals("")) {

                ItemStack stack = new ItemStack(Material.AIR);

                if(plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.slot_" + i + ".material").equals("PLAYER_HEAD")) {
                    stack = PlayerHead.getSkullFromOwner(player.getName());
                } else {
                    stack = new ItemStack(Material.getMaterial(plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.slot_" + i + ".material")));
                }

                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(PlaceholderAPI.setPlaceholders(player, plugin.configManager.lobbyConfig.getConfig().getString("lobby.head.slot_" + i + ".name")));
                stack.setItemMeta(meta);

                HeadInventory.setItem(i, stack);
            }
        }
    }

    public static void EggItems(Player player) {

    }

    public static void HotBarItemsHelper(ItemStack item, String displayName, int hotbarSlot, Player player) {
        ItemStack itemStack = new ItemStack(item);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        player.getInventory().setItem((hotbarSlot), itemStack);
    }
}
