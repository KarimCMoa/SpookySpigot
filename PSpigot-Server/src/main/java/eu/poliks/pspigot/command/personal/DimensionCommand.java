package eu.poliks.pspigot.command.personal;

import eu.poliks.pspigot.Service.SpookyService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DimensionCommand extends Command {

    public DimensionCommand() {
        super("dimension");
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "Dimension commands:");
        sender.sendMessage("   ");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/dimension" + Bukkit.getServer().getSecondColor() +" normal");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/dimension" + Bukkit.getServer().getSecondColor() +" nether");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/dimension" + Bukkit.getServer().getSecondColor() +" end");
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "---------------------------------------------");
    }

    public boolean execute(CommandSender sender, String alias, String[] args) {
        Player player = (Player) sender;
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.dimension") || sender.equals("POLIKS")) {
            sender.sendMessage(SpookyService.NoPermission);
            return true;
        }
        if(args.length == 0){
            sendHelp(sender);
        }
        if(args.length == 1){
                if(args[0].equalsIgnoreCase("normal")){
                player.setFakeEnvironment(World.Environment.NORMAL);
                sender.sendMessage(Bukkit.getServer().getPositiveColor() + "You have successfully toggled" + Bukkit.getServer().getSecondColor() + " NORMAL" + Bukkit.getServer().getPositiveColor() + " dimension !");
            }
                if(args[0].equalsIgnoreCase("nether")){
                player.setFakeEnvironment(World.Environment.NETHER);
                sender.sendMessage(Bukkit.getServer().getPositiveColor() + "You have successfully toggled" + Bukkit.getServer().getSecondColor() + " NETHER" + Bukkit.getServer().getPositiveColor() + " dimension !");
        }
                if(args[0].equalsIgnoreCase("end")){
                player.setFakeEnvironment(World.Environment.THE_END);
                sender.sendMessage(Bukkit.getServer().getPositiveColor() + "You have successfully toggled" + Bukkit.getServer().getSecondColor() + " END" + Bukkit.getServer().getPositiveColor() + " dimension !");
            }
        }
        return false;
    }
}
