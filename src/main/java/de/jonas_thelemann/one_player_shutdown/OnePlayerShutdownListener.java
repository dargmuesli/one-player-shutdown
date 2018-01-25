package de.jonas_thelemann.one_player_shutdown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

class OnePlayerShutdownListener implements Listener {
    private final OnePlayerShutdown plugin;

    public OnePlayerShutdownListener(OnePlayerShutdown plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent playerJoinEvent) {
        plugin.updateShutdownTask();
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent playerQuitEvent) {
        plugin.updateShutdownTask();
    }
}
