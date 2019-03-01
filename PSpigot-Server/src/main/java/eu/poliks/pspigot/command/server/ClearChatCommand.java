package eu.poliks.pspigot.command.server;

import java.util.Arrays;

import eu.poliks.pspigot.Service.SpookyService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand extends Command
{

    public ClearChatCommand() {
        super("clearchat");
        this.usageMessage = "/clearchat";
        this.setAliases(Arrays.asList("clearc", "cc", "chatclear"));
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /" + label + " <reason>"));
        }
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.clearchat") || sender.equals("POLIKS")) {
            sender.sendMessage(SpookyService.NoPermission);
            return true;
        }
        else {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.sendMessage(new String[101]);
                online.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPublic chat has been cleared by " + sender.getName() + "."));
            }
        }
        return false;
}

}
