package me.kirilucas.chaoticevents.entity;

import me.kirilucas.chaoticevents.common.GeneralUtilities;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

public class Entity {

    public static void spawnEntities(Location location, EntityType entityType, int entityAmount) {
        World world = location.getWorld();
        assert (world != null);

        for (int i = 0; i <= entityAmount; i++) {
            world.spawnEntity(location, entityType);
        }
    }

    public static EntityType getRandomEntity() {
        EntityType[] entities = EntityType.values();
        return entities[GeneralUtilities.getRandomNumber(entities.length - 1)];
    }
}
