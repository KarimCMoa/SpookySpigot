package eu.poliks.pspigot.command.dev;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DevCommand extends Command
{

    public void DevCommand() {

    }

    public DevCommand() {
        super("dev");
        this.usageMessage = "/dev";
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "Development Commands:");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/dev" + Bukkit.getServer().getSecondColor() +" author");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/dev" + Bukkit.getServer().getSecondColor() +" test");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/dev" + Bukkit.getServer().getSecondColor() +" permissions");
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.isOp() || !sender.hasPermission("spookyspigot.dimension") || sender.equals("POLIKS")) {
            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Unknown command.");
            return true;
        }
        if(args.length == 0){
            sendHelp(sender);
        }
        if(args.length == 1){
                    if(args[0].equalsIgnoreCase("higvhivgvsduyvgdsivhyqivgysdvgiuysqdfbyrjnqsa14197491")){
                        System.out.println("{\"online\":" + Bukkit.getOnlinePlayers().size() + "," + "\"all\":" + Bukkit.getOfflinePlayers().length + "}");
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "zdzdzdzdzd");
                        sender.sendMessage("It's for get all players on server (for website)");
                    }
                if(args[0].equalsIgnoreCase("author")){
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "Spooky Industries:");
                    sender.sendMessage("   ");
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + "* " + Bukkit.getServer().getFirstColor() + "SpookySpigot created by " + Bukkit.getServer().getSecondColor() + "POLIKS");
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + "* " + Bukkit.getServer().getFirstColor() + "SpookyAC created by " + Bukkit.getServer().getSecondColor() + "UnknownMyName");
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");

                }
                    if(args[0].equalsIgnoreCase("test")){
                    Player player = (Player) sender;
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "Developer Check:");
                    sender.sendMessage("   ");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "First Color: " + Bukkit.getServer().getFirstColor() + "Test");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Second Color: " + Bukkit.getServer().getSecondColor() + "Test");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Third Color: " + Bukkit.getServer().getThirdColor() + "Test");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Separator Color: " + Bukkit.getServer().getSeparatorColor() + "Test");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Negative Color: " + Bukkit.getServer().getNegativeColor() + "Test");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Positive Color: " + Bukkit.getServer().getPositiveColor() + "Test");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "ProtocolSupport: "+ Bukkit.getServer().getSecondColor() +  Bukkit.getServer().isProtocolSupport());
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "ServerName:" + " " + Bukkit.getServer().getPSName());
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Dimension: " + Bukkit.getServer().getSecondColor() + player.getFakeEnvironment());
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + ChatColor.BOLD +"    ⚠ for this, please remove nether and end in the spigot config!");
                        sender.sendMessage(Bukkit.getServer().getFirstColor() + "Discord: " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getDiscordIP());
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
                }
                if(args[0].equalsIgnoreCase("permissions")){
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "SpookySpigot Permissions:");
                    sender.sendMessage("   ");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "SpookySpigot Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.use");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Dev Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.devcommand");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "DevLog Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.devlog");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Knockback Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.knockback.use");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Potions Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.potions.use");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Dimension Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.dimension");
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + ChatColor.BOLD +"    ⚠ for this, please remove nether and end in the spigot config!");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "ClearChat Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.clearchat");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "SetSlots Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.setslot");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Whois Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.whois");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Whois Command (All Informations): " + Bukkit.getServer().getSecondColor() + "spookyspigot.whois.admin");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Broadcast Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.broadcast");
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "IP Command: " + Bukkit.getServer().getSecondColor() + "spookyspigot.ipcommand");
                    sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");

                }
        }
        return false;
    }
}