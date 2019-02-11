package eu.poliks.pspigot.command.personal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
        this.description = "Get the ping between you and the server";
        this.usageMessage = "/ping <player>";
        this.setAliases(Arrays.asList("ms", "latence"));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (args.length >= 2) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usageMessage);
            return false;
        }

        Player p = (Player) sender;

        if(args.length == 0) {
            sender.sendMessage(Bukkit.getServer().getSecondColor() + "Your ping: " + Bukkit.getServer().getFirstColor() + p.getPing() + Bukkit.getServer().getSecondColor() + " ms. " + Bukkit.getServer().getSecondColor() + "(TPS: " + format(Bukkit.spigot().getTPS()[0]) + Bukkit.getServer().getSecondColor() + ")");

        } else if(args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);

            if(t == null){
                sender.sendMessage(ChatColor.RED + "This player is not online!");
                return false;
            }
            if(t == p){
                sender.sendMessage(Bukkit.getServer().getSecondColor() + "Your ping: " + Bukkit.getServer().getFirstColor() + p.getPing() + Bukkit.getServer().getSecondColor() + " ms. " + Bukkit.getServer().getSecondColor() + "(TPS: " + format(Bukkit.spigot().getTPS()[0]) + Bukkit.getServer().getSecondColor() + ")");
                return true;
            }
            sender.sendMessage(Bukkit.getServer().getFirstColor() + t.getDisplayName() + Bukkit.getServer().getSecondColor() + "'s ping: " + Bukkit.getServer().getFirstColor() + t.getPing() + " ms. " + Bukkit.getServer().getSecondColor() + "(TPS: " + format(Bukkit.spigot().getTPS()[0]) + Bukkit.getServer().getSecondColor() + ")");
            sender.sendMessage(Bukkit.getServer().getSecondColor() + "Ping difference: " + Bukkit.getServer().getFirstColor() + (Math.max(p.getPing(), t.getPing()) - Math.min(p.getPing(), t.getPing()) + " ms."));
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                return ImmutableList.of();
            }

            String lastWord = args[0];
            if (lastWord.length() == 0) {
                return ImmutableList.of();
            }

            ArrayList<String> matchedPlayers = new ArrayList<String>();
            for (Player player : sender.getServer().getOnlinePlayers()) {
                String name = player.getName();
                if (StringUtil.startsWithIgnoreCase(name, lastWord)) {
                    matchedPlayers.add(name);
                }
            }

            Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
            return matchedPlayers;
        }
        return ImmutableList.of();
    }

    private static String format(double tps){
        return (( tps > 18.0 ) ? ChatColor.GREEN : ( tps > 16.0 ) ? ChatColor.YELLOW : ChatColor.RED ).toString() + ( ( tps > 20.0 ) ? ChatColor.DARK_GREEN + "*" : "" ) + Math.min( Math.round( tps * 100.0 ) / 100.0, 20.0 );
    }
    // Spigot End
}