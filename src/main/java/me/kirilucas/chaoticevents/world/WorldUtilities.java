package me.kirilucas.chaoticevents.world;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.World;

import java.util.Objects;

public class WorldUtilities {
    private static final int DAYLIGHT_MAXIMUM_TICK = 12300;

    public static void spawnTree(World world, Location spawnLocation, TreeType treeType) {
        world.generateTree(spawnLocation, treeType);
    }

    public static void explodeAt(Location location, int explosionPower) {
        Objects.requireNonNull(location.getWorld()).createExplosion(location, explosionPower);
    }

    public static boolean isDaytime(World world) {
        return world.getTime() < DAYLIGHT_MAXIMUM_TICK;
    }
}
