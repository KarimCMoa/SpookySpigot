package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@Deprecated
public class TestForCommand extends VanillaCommand {
    public TestForCommand() {
        super("testfor");
        this.description = "Tests whether a specifed player is online";
        this.usageMessage = "/testfor <player>";
        this.setPermission("bukkit.command.testfor");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length < 1)  {
            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: " + usageMessage);
            return false;
        }

        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "/testfor is only usable by commandblocks with analog output.");
        return true;
    }

    // Spigot Start
    @Override
    public java.util.List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException
    {
        if ( args.length == 0 )
        {
            return super.tabComplete( sender, alias, args );
        }
        return java.util.Collections.emptyList();
    }
    // Spigot End
}
