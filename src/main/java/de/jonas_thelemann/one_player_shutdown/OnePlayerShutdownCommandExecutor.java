package de.jonas_thelemann.one_player_shutdown;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

class OnePlayerShutdownCommandExecutor implements CommandExecutor {
    private final OnePlayerShutdown plugin;

    public OnePlayerShutdownCommandExecutor(OnePlayerShutdown plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("oneplayershutdown")) {
            return false;
        }

        if (args.length == 0) {
            return false;
        }

        if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("start")) {
            if (args.length != 1) {
                return false;
            }

            if (plugin.getConfig().getBoolean("enabled")) {
                sender.sendMessage(ChatColor.GOLD + "One-Player-Shutdown (" + plugin.getConfig().getInt("timelimit") + "s) is already enabled.");
            } else {
                plugin.getConfig().set("enabled", true);
                plugin.saveConfig();

                sender.sendMessage(ChatColor.GOLD + "One-Player-Shutdown (" + plugin.getConfig().getInt("timelimit") + "s) enabled.");
            }

            plugin.updateShutdownTask();
        } else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("stop")) {
            if (args.length != 1) {
                return false;
            }

            if (plugin.getConfig().getBoolean("enabled")) {
                plugin.getConfig().set("enabled", false);
                plugin.saveConfig();

                sender.sendMessage(ChatColor.GOLD + "One-Player-Shutdown (" + plugin.getConfig().getInt("timelimit") + "s) disabled.");
            } else {
                sender.sendMessage(ChatColor.GOLD + "One-Player-Shutdown (" + plugin.getConfig().getInt("timelimit") + "s) is already disabled.");
            }

            plugin.updateShutdownTask();
        } else if (args[0].equalsIgnoreCase("status")) {
            if (args.length != 1) {
                return false;
            }

            if (plugin.getConfig().getBoolean("enabled")) {
                sender.sendMessage(ChatColor.GOLD + "One-Player-Shutdown: enabled (" + plugin.secondsUntilShutdown + "s/" + plugin.getConfig().getInt("timelimit") + "s).");
            } else {
                sender.sendMessage(ChatColor.GOLD + "One-Player-Shutdown: disabled (" + plugin.getConfig().getInt("timelimit") + "s).");
            }
        } else if (args[0].equalsIgnoreCase("timelimit")) {
            if (args.length != 2) {
                return false;
            }

            plugin.getConfig().set("timelimit", Integer.parseInt(args[1]));
            plugin.saveConfig();

            sender.sendMessage(ChatColor.GOLD + "One-Player-Shutdown timelimit set to " + args[1] + " seconds.");

            plugin.updateShutdownTask();
        } else {
            return false;
        }

        return true;
    }
}
