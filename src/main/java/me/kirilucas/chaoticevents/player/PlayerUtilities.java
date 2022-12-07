package me.kirilucas.chaoticevents.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.Objects;

public final class PlayerUtilities {
    public static Location getAimLocation(Player player) {
        return player.getTargetBlock(null, 100).getLocation();
    }

    public static Material getBlockBelowPlayer(Player player) {
        return Objects.requireNonNull(player.getLocation().getBlock().getRelative(BlockFace.DOWN)).getType();
    }
}