package eu.poliks.pspigot.command.server;

import eu.poliks.pspigot.knockback.KnockbackProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;

public class IPCommand extends Command {

    public IPCommand() {
        super("ip");
        this.setAliases(Arrays.asList("ts", "website", "site", "web", "discord", "store", "boutique", "shop"));
    }

    private void DevLog(CommandSender sender) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH +"-----------------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "All IP of " + Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + Bukkit.getServer().getPSName() + Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + ":");
        sender.sendMessage("   ");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "Website" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getSecondColor() + "http://" + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPSName());
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "Teamspeak" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getSecondColor() + "ts." + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPSName());
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "Discord" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getDiscordIP());
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH +"-----------------------------------------------------");

    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.ipcommand") || sender.equals("POLIKS")) {
            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Unknown command.");
            return true;
        }
        if(args.length == 0){
            DevLog(sender);
        }
        return false;
    }
}

