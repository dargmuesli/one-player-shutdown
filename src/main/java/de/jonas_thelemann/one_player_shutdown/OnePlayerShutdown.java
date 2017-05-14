package de.jonas_thelemann.one_player_shutdown;

import org.bukkit.plugin.java.JavaPlugin;

public class OnePlayerShutdown extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        new OnePlayerShutdownListener(this);
        new OnePlayerShutdownCommandExecutor(this);
    }
}
