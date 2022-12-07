package me.kirilucas.chaoticevents.server;

import me.kirilucas.chaoticevents.ChaoticEvents;

public class ServerUtilities {

    public static void scheduleEvent(int scheduleTo, Runnable runnable) {
        ChaoticEvents.getScheduler().scheduleSyncDelayedTask(ChaoticEvents.getPlugin(), runnable, scheduleTo);
    }
}
