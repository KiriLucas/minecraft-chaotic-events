package me.kirilucas.chaoticevents.server.listeners;

import me.kirilucas.chaoticevents.ChaoticEvents;
import me.kirilucas.chaoticevents.common.PersistentDataKey;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.Optional;

public class ServerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        NamespacedKey inventoryInteractionsKey = new NamespacedKey(ChaoticEvents.getPlugin(), PersistentDataKey.INVENTORY_INTERACTIONS);
        NamespacedKey brokenBlocksKey = new NamespacedKey(ChaoticEvents.getPlugin(), PersistentDataKey.INVENTORY_INTERACTIONS);

        PersistentDataContainer data = event.getPlayer().getPersistentDataContainer();

        Integer inventoryInteractions = Optional.ofNullable(data.get(inventoryInteractionsKey, PersistentDataType.INTEGER)).orElse(0);
        Integer brokenBlocks = Optional.ofNullable(data.get(brokenBlocksKey, PersistentDataType.INTEGER)).orElse(0);

        data.set(inventoryInteractionsKey, PersistentDataType.INTEGER, inventoryInteractions);
        data.set(brokenBlocksKey, PersistentDataType.INTEGER, brokenBlocks);
    }
}
