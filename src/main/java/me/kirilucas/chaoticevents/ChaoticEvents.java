package me.kirilucas.chaoticevents;

import me.kirilucas.chaoticevents.npc.listeners.NpcListener;
import me.kirilucas.chaoticevents.player.listeners.PlayerListener;
import me.kirilucas.chaoticevents.server.listeners.ServerListener;
import me.kirilucas.chaoticevents.world.listeners.WorldListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class ChaoticEvents extends JavaPlugin implements Listener {

    private static ChaoticEvents plugin;

    public ChaoticEvents() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        System.out.println("Chaotic Events is enabled");

        getServer().getPluginManager().registerEvents(new ServerListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new NpcListener(), this);
    }

    public static ChaoticEvents getPlugin() {
        return plugin;
    }

    public static BukkitScheduler getScheduler() {
        return getPlugin().getServer().getScheduler();
    }
}