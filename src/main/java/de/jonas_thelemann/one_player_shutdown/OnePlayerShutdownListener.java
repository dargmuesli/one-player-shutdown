package de.jonas_thelemann.one_player_shutdown;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnePlayerShutdownListener implements Listener {
    private OnePlayerShutdown onePlayerShutdown;
    private int currentPlayerCount;
    private Timer timer;
    private int seconds;

    public OnePlayerShutdownListener(OnePlayerShutdown onePlayerShutdown) {
        onePlayerShutdown.getServer().getPluginManager().registerEvents(this, onePlayerShutdown);
        this.onePlayerShutdown = onePlayerShutdown;
        this.currentPlayerCount = 0;
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent playerJoinEvent) {
        currentPlayerCount++;
        shutdownTask();
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent playerQuitEvent) {
        currentPlayerCount--;
        shutdownTask();
    }

    private void shutdownTask() {
        if (onePlayerShutdown.getConfig().getBoolean("enabled") && currentPlayerCount == 1) {
            Server server = onePlayerShutdown.getServer();

            server.broadcastMessage("You are the only player on this multiplayer server."
                + "If nobody joins within two minutes, the server will shutdown.");

            seconds = onePlayerShutdown.getConfig().getInt("timelimit");

            timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    seconds--;

                    if (currentPlayerCount == 2) {
                        server.broadcastMessage("Somebody joined. The server won't shutdown.");
                        timer.cancel();
                    }

                    if (seconds == 60) {
                        server.broadcastMessage("One minute until shutdown.");
                    }

                    if (seconds < 11) {
                        if (seconds != 0) {
                            server.broadcastMessage(seconds + " seconds until shutdown.");
                        } else {
                            server.broadcastMessage("Shutting down.");
                            timer.cancel();
                            server.shutdown();
                        }
                    }
                }
            }, 1000, 1000);
        }
    }
}
