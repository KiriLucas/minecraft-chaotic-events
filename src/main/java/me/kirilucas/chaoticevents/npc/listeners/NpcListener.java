package me.kirilucas.chaoticevents.npc.listeners;

import me.kirilucas.chaoticevents.world.WorldUtilities;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

public class NpcListener implements Listener {

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        Entity entity = event.getEntity();
        World world = entity.getWorld();

        if (entity instanceof Monster && WorldUtilities.isDaytime(world)) {
            event.setCancelled(true);
        }
    }
}
