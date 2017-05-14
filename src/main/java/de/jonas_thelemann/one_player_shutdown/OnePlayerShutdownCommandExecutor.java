package de.jonas_thelemann.one_player_shutdown;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OnePlayerShutdownCommandExecutor implements CommandExecutor {
    private OnePlayerShutdown onePlayerShutdown;

    public OnePlayerShutdownCommandExecutor(OnePlayerShutdown onePlayerShutdown) {
        onePlayerShutdown.getCommand("oneplayershutdown").setExecutor(this);
        this.onePlayerShutdown = onePlayerShutdown;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("oneplayershutdown")) {
            return false;
        }

        if (args.length != 1) {
            return false;
        }

        if (args[0].equalsIgnoreCase("enable")) {
            onePlayerShutdown.getConfig().set("enabled", true);
            onePlayerShutdown.saveConfig();
            return true;
        } else if (args[0].equalsIgnoreCase("disable")) {
            onePlayerShutdown.getConfig().set("enabled", false);
            onePlayerShutdown.saveConfig();
            return true;
        } else {
            return false;
        }
    }
}
