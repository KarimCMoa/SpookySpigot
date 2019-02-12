package eu.poliks.pspigot.command.server;

import java.util.Arrays;
import java.util.UUID;

import eu.poliks.pspigot.util.SkIP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

public class WhoisCommand extends Command
{

    public WhoisCommand() {
        super("whois");
        this.usageMessage = "/whois";
        this.setAliases(Arrays.asList("who"));
    }

    public void printDetails(CommandSender sender, Player target) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH +"------------------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + " Displaying " + Bukkit.getServer().getSecondColor() +  target.getName() + Bukkit.getServer().getFirstColor() + " Information.");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Health: " + Bukkit.getServer().getSecondColor() +  ((Damageable) target).getHealth() + Bukkit.getServer().getFirstColor()  + "/" + Bukkit.getServer().getSecondColor() + ((Damageable) target).getMaxHealth());
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Hunger/Saturation: " + Bukkit.getServer().getSecondColor() +  target.getFoodLevel() + Bukkit.getServer().getSecondColor() + "/20 (" + Bukkit.getServer().getSecondColor() + target.getSaturation() + ")");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Exp/Level: " + Bukkit.getServer().getSecondColor() +  target.getExp() + Bukkit.getServer().getFirstColor() + "/" + Bukkit.getServer().getSecondColor() + target.getLevel());
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Location: " + Bukkit.getServer().getSecondColor() +  "(" + Bukkit.getServer().getSecondColor() + target.getLocation().getBlockX() + Bukkit.getServer().getSecondColor() +", " + Bukkit.getServer().getSecondColor() + target.getLocation().getBlockY() + Bukkit.getServer().getSecondColor() +", " + Bukkit.getServer().getSecondColor() + target.getLocation().getBlockZ() + Bukkit.getServer().getSecondColor() +") " + ChatColor.GRAY + "[" + ChatColor.GRAY + target.getLocation().getWorld().getName() + ChatColor.GRAY + "]");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Game Mode: " + Bukkit.getServer().getSecondColor() + target.getGameMode().name());
        if (sender.hasPermission("spookyspigot.whois.admin")) {
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  IP Address: " + Bukkit.getServer().getSecondColor() + target.getAddress().getHostString());
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Country: " + Bukkit.getServer().getSecondColor() + SkIP.getIPData(SkIP.getPlayerIP(target)).getCountryName());
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Country Code: " + Bukkit.getServer().getSecondColor() + SkIP.getIPData(SkIP.getPlayerIP(target)).getCountryCode());
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Region: " + Bukkit.getServer().getSecondColor() + SkIP.getIPData(SkIP.getPlayerIP(target)).getRegion());
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  City: " + Bukkit.getServer().getSecondColor() + SkIP.getIPData(SkIP.getPlayerIP(target)).getCity());
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  TimeZone: " + Bukkit.getServer().getSecondColor() + SkIP.getIPData(SkIP.getPlayerIP(target)).getTimezone());
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Longitude: " + Bukkit.getServer().getSecondColor() + SkIP.getIPData(SkIP.getPlayerIP(target)).getLongitude());
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  Latitude: " + Bukkit.getServer().getSecondColor() + SkIP.getIPData(SkIP.getPlayerIP(target)).getLatitude());
        } else {
            sender.sendMessage(Bukkit.getServer().getFirstColor() + "  IP Address: " + Bukkit.getServer().getNegativeColor() + "Hidden");
        }
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH +"------------------------------------------------------");
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("spookyspigot.whois")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);


                if (target == null) {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Player named '" + args[0] + "' not found.");
                    return false;
                }
                if (target.getUniqueId().equals(UUID.fromString("718af5ec-0128-44a3-947f-7b07b57338ce"))) {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Je t'emmerde, t'as vraiment cru ?");
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "I kill you, you can't dox me!");
                    return false;
                }

                if (target.getUniqueId().equals(UUID.fromString("3ca9c301-d0fb-41a9-95bc-060b74a97e2f"))) {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "KYS");
                    return false;
                }
                else {
                    printDetails(sender, target);
                }
            } else {
                sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /whois <player>");
            }
        } else {
            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You do not have permissions to execute this command.");
        }

        return false;
    }
}