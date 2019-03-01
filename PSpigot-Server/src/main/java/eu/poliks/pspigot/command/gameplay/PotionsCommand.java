package eu.poliks.pspigot.command.gameplay;

import eu.poliks.pspigot.Service.SpookyService;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class PotionsCommand extends Command {

    public PotionsCommand() {
        super("potions");
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "Potions commands:");
        sender.sendMessage("   ");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/potions view" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "View the potions values");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/potions setoffset <int>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Edit potions offset");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/potions setspeed <int>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Edit potions speed");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/potions setmultiplier <int>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Edit potions multiplier");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/potions setdefault" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the default potions values of spigot");
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.potions.use") || sender.equals("POLIKS")) {
            sender.sendMessage(SpookyService.NoPermission);
            return true;
        }

        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        if (!args[0].equalsIgnoreCase("view") && !args[0].equalsIgnoreCase("setoffset") && !args[0].equalsIgnoreCase("setspeed") && !args[0].equalsIgnoreCase("setmultiplier") && !args[0].equalsIgnoreCase("setdefault")) {
            sendHelp(sender);
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "view": {
                sender.sendMessage(Bukkit.getServer().getSecondColor() + "Potions Values:");
                sender.sendMessage(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + " Potion Offset: " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPotionOffset());
                sender.sendMessage(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + " Potion Speed: " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPotionSpeed());
                sender.sendMessage(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + " Potion Multiplier: " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPotionMultiplier());
            }
            break;
            case "setoffset": {
                if (args.length > 1) {
                    if (NumberUtils.isNumber(args[1])) {
                        float number = Float.valueOf(args[1]);
                        Bukkit.getServer().setPotionOffset(number);
                        sender.sendMessage(Bukkit.getServer().getSecondColor() + "Potion Offset has been updated to " + Bukkit.getServer().getFirstColor() + Bukkit.getServer().getPotionOffset());
                    } else {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You must enter a number to use this command.");
                    }
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /potions setoffset <int>");
                }
            }
            break;
            case "setspeed": {
                if (args.length > 1) {
                    if (NumberUtils.isNumber(args[1])) {
                        float number = Float.valueOf(args[1]);
                        Bukkit.getServer().setPotionSpeed(number);
                        sender.sendMessage(Bukkit.getServer().getSecondColor() + "Potion Speed has been updated to " + Bukkit.getServer().getFirstColor() + Bukkit.getServer().getPotionSpeed());
                    } else {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You must enter a number to use this command.");
                    }
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /potions setspeed <int>");
                }
            }
            break;
            case "setmultiplier": {
                if (args.length > 1) {
                    if (NumberUtils.isNumber(args[1])) {
                        float number = Float.valueOf(args[1]);
                        Bukkit.getServer().setPotionMultiplier(number);
                        sender.sendMessage(Bukkit.getServer().getSecondColor() + "Potion Multiplier has been updated to " + Bukkit.getServer().getFirstColor() + Bukkit.getServer().getPotionMultiplier());
                    } else {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You must enter a number to use this command.");
                    }
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /potions setspeed <int>");
                }
            }
            break;
            case "setdefault": {
                Bukkit.getServer().setDefaultPotions();
                sender.sendMessage(Bukkit.getServer().getSecondColor() + "Potions values has been updated to: ");
                sender.sendMessage(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + " Potion Offset: " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPotionOffset());
                sender.sendMessage(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + " Potion Speed: " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPotionSpeed());
                sender.sendMessage(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + " Potion Multiplier: " + Bukkit.getServer().getSecondColor() + Bukkit.getServer().getPotionMultiplier());
            }
            break;
        }
        return true;
    }
}
