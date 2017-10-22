package de.jonas_thelemann.one_player_shutdown;

import org.bukkit.ChatColor;

class OnePlayerShutdownRunnable implements Runnable {
    private final OnePlayerShutdown plugin;

    OnePlayerShutdownRunnable(OnePlayerShutdown plugin) {
        this.plugin = plugin;
    }

    public void run() {
        if (!plugin.getConfig().getBoolean("enabled")) {
            plugin.stopShutdown(ChatColor.GOLD + "One-Player-Shutdown disabled. The server won't shutdown.");
            return;
        }

        if (plugin.onlinePlayerCount >= 2) {
            plugin.stopShutdown(ChatColor.GOLD + "Somebody joined. The server won't shutdown.");
            return;
        }

        int timelimit = plugin.getConfig().getInt("timelimit");

        plugin.secondsUntilShutdown--;

        if (plugin.secondsUntilShutdown == timelimit / 2) {
            plugin.getLastPlayer().sendMessage(ChatColor.GOLD + "" + timelimit / 2 + " seconds until shutdown.");
        }

        if (plugin.secondsUntilShutdown < 11) {
            if (plugin.secondsUntilShutdown != 0) {
                plugin.getLastPlayer().sendMessage(ChatColor.GOLD + "" + plugin.secondsUntilShutdown + " seconds until shutdown.");
            } else {
                plugin.getLastPlayer().sendMessage(ChatColor.GOLD + "Shutting down.");
                plugin.scheduler.shutdown();
                plugin.getServer().shutdown();
            }
        }
    }
}
