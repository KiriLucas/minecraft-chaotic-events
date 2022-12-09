package me.kirilucas.chaoticevents.player.listeners;

import me.kirilucas.chaoticevents.ChaoticEvents;
import me.kirilucas.chaoticevents.common.Time;
import me.kirilucas.chaoticevents.entity.Entity;
import me.kirilucas.chaoticevents.player.PlayerUtilities;
import me.kirilucas.chaoticevents.server.ServerUtilities;
import me.kirilucas.chaoticevents.world.WorldUtilities;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlayerListener implements Listener {

    private static final int EXPLOSION_POWER = 5;
    private static final int EFFECT_AMPLIFIER = 2;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Block playerBlockOnDeath = event.getEntity().getLocation().getBlock();

        if (Material.CHEST != playerBlockOnDeath.getType()) {
            playerBlockOnDeath.setType(Material.CHEST);
        }

        Chest chest = (Chest) playerBlockOnDeath.getState();
        List<ItemStack> droppedItems = event.getDrops();

        droppedItems.forEach((item) -> chest.getInventory().addItem(item));
        droppedItems.clear();
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent event) {
        if (event.getTo() == event.getFrom()) return;
        Player player = event.getPlayer();

        if (Material.GRASS_BLOCK == PlayerUtilities.getBlockBelowPlayer(player)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Time.TEN_SECONDS_TICKS, EFFECT_AMPLIFIER));
        }
    }

    @EventHandler
    public void onPlayerInventoryInteraction(InventoryOpenEvent event) {
        HumanEntity player = event.getPlayer();
        Location location = player.getLocation();
        Entity.spawnEntities(location, EntityType.SALMON, 30);

        NamespacedKey namespacedKey = new NamespacedKey(ChaoticEvents.getPlugin(), "inventoryInteractions");
        PersistentDataContainer data = player.getPersistentDataContainer();

        Integer inventoryInteractions = Optional.ofNullable(data.get(namespacedKey, PersistentDataType.INTEGER)).orElse(0);
        data.set(namespacedKey, PersistentDataType.INTEGER, inventoryInteractions);

        ServerUtilities.scheduleEvent(Time.SEVEN_SECONDS_TICKS, () -> WorldUtilities.explodeAt(location, EXPLOSION_POWER));
    }


    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event) {
        ItemStack itemStack = event.getItem();
        Player player = event.getPlayer();

        switch (itemStack.getType()) {
            case APPLE:
            case BREAD:
            case BEEF:
            case MUTTON:
            case RABBIT:
            case CHICKEN:
            case COOKED_BEEF:
            case COOKED_CHICKEN:
            case COOKED_MUTTON:
            case COOKED_RABBIT:
                player.sendMessage("Hmmmmmmm, tasty");
                break;
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (Action.RIGHT_CLICK_AIR == action) {
            Location eyeLocation = player.getEyeLocation();
            player.teleport(eyeLocation);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();
        Block brokenBlock = event.getBlock();

//        PersistentDataContainer data = player.getPersistentDataContainer();
//        NamespacedKey key = new NamespacedKey(ChaoticEvents.getPlugin(), "potato");

//
//        MetadataValue meta = player.meta
//
//        PersistentDataContainer data = player.getMetadata();

        switch (brokenBlock.getType()) {
            case DIRT:
                player.sendMessage("Quebra a terrinha, quebra");
                break;
            case SAND:
                player.sendMessage("Cuidado pra areia não entrar no seu c");

                ServerUtilities.scheduleEvent(Time.THREE_SECONDS_TICKS, () -> player.sendMessage("no seu cursor**"));

                break;
            case GRASS_BLOCK:
                player.sendMessage("Quebra a graminha, quebra");
                break;
            case IRON_ORE:
                player.sendMessage("Meu amigo... Tu se ferrou");
                break;
            case COAL_ORE:
                player.sendMessage("Meu amigo... Tu se ferrou");
                break;
            case DIAMOND_ORE:
                player.dropItem(false);
                player.playSound(location, Sound.ENTITY_CREEPER_PRIMED, 80, 0);
                break;
            case OAK_LOG:
            case ACACIA_LOG:
            case SPRUCE_LOG:
            case BIRCH_LOG:
                WorldUtilities.spawnTree(Objects.requireNonNull(world), location.add(new Vector(5, 0, 5)), TreeType.BIG_TREE);
                break;
            case STONE:
                player.sendMessage(stoneMessage());
                world.spawnEntity(location, EntityType.CHICKEN);
                break;
        }

    }

    public String stoneMessage() {
        switch ((int) ((Math.random() * (20 - 0)) + 0)) {
            case 1:
            case 2:
            case 3:
                return "Ó A PEDRADA";
            case 4:
            case 5:
            case 6:
                return "preda";
            case 7:
            case 8:
            case 9:
                return "quebra quebra quebra";
            case 10:
            case 11:
            case 12:
                return "I AM A DWARF AND I'M DIGGING A HOLE";
            case 13:
            case 14:
            case 15:
                return "SABE PORQUE O 6 TEM MEDO DO 7?";
            case 16:
            case 17:
            case 18:
                return "PÁ PÁ PÁ PÁ PÁ PÁ";
            case 19:
            case 20:
                return "PLOC";
            default:
                return "p e d r a d a";
        }
    }
}
