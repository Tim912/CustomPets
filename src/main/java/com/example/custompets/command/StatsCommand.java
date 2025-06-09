package com.example.custompets.command;


import com.example.custompets.CustomPetsPlugin;
import com.example.custompets.gui.StatsGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * Command to open the stats GUI.
 */
public class StatsCommand implements CommandExecutor {
    private final CustomPetsPlugin plugin;

    public StatsCommand(CustomPetsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        StatsGUI.open(plugin, player);

        return true;
    }
}
