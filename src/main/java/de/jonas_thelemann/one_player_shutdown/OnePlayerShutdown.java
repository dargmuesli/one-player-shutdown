package de.jonas_thelemann.one_player_shutdown;

import com.google.common.collect.Iterables;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OnePlayerShutdown extends JavaPlugin {
    private final OnePlayerShutdownListener onePlayerShutdownListener = new OnePlayerShutdownListener(this);
    private final OnePlayerShutdownCommandExecutor onePlayerShutdownCommandExecutor = new OnePlayerShutdownCommandExecutor(this);
    protected ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    protected ScheduledFuture schedule;
    protected int onlinePlayerCount = 0;
    protected int secondsUntilShutdown = 0;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(onePlayerShutdownListener, this);
        getCommand("oneplayershutdown").setExecutor(onePlayerShutdownCommandExecutor);

        onlinePlayerCount = getServer().getOnlinePlayers().size();
        secondsUntilShutdown = getConfig().getInt("timelimit");
    }

    protected void updateShutdownTask() {
        if (getConfig().getBoolean("enabled") && onlinePlayerCount == 1 && (schedule == null || schedule.isDone())) {
            getLastPlayer().sendMessage(ChatColor.GOLD + "You are the only player on this multiplayer server. If nobody joins within " + getConfig().getInt("timelimit") + " seconds, the server will shutdown.");
            startShutdown();
        }
    }

    protected void startShutdown() {
        OnePlayerShutdownRunnable runnable = new OnePlayerShutdownRunnable(this);
        schedule = scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }

    protected void stopShutdown(String message) {
        schedule.cancel(false);
        getServer().broadcastMessage(message);
        secondsUntilShutdown = getConfig().getInt("timelimit");
    }

    protected Player getLastPlayer() {
        return Iterables.get(getServer().getOnlinePlayers(), 0);
    }
}
