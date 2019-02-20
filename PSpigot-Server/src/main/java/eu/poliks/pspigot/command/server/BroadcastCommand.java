package eu.poliks.pspigot.command.server;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class BroadcastCommand extends Command
{

    public BroadcastCommand() {
        super("broadcast");
        this.usageMessage = "/broadcast";
        this.setAliases(Arrays.asList("bc", "announce", "annonce"));
    }

    public boolean execute(CommandSender sender, String label, String[] args) {

        StringBuilder msg = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            msg.append(args[i] + " ");
        }
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.broadcast") || sender.equals("POLIKS")) {
            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Unknown command.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /" + label + " <reason>"));
        }
        else {
            Bukkit.broadcastMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
            Bukkit.broadcastMessage(Bukkit.getServer().getNegativeColor() + ChatColor.BOLD + "Alert" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getSecondColor() + msg);
            Bukkit.broadcastMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
        }
        return false;
}

}
