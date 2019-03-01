package eu.poliks.pspigot.command.dev;

import eu.poliks.pspigot.Service.SpookyService;
import eu.poliks.pspigot.knockback.KnockbackProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.Collections;

public class DevLogCommand extends Command {

    public DevLogCommand() {
        super("devlog");
        this.setAliases(Collections.singletonList("devlogs"));
    }

    private void DevLog(CommandSender sender) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH +"-----------------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "DevLog:");
        sender.sendMessage("   ");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "1" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Announce Added!");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "2" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Sprint-Vertical Debug!");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "3" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Default Nether/End config edited!");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "4" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "IPCommand Removed!");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "5" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "SetSlots Added (I have to debug it)");
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH +"-----------------------------------------------------");

    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.devlog") || sender.equals("POLIKS")) {
            sender.sendMessage(SpookyService.NoPermission);
            return true;
        }
        if(args.length == 0){
            DevLog(sender);
        }
        return false;
    }
}
