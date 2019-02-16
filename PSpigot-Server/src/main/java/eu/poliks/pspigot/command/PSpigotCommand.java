package eu.poliks.pspigot.command;

import eu.poliks.pspigot.util.BooleanUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginLoadOrder;

public class PSpigotCommand extends Command {

    public PSpigotCommand() {
        super("spookyspigot");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.use")) {
            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Unknown command.");
            return true;
        }

        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        if (!args[0].equalsIgnoreCase("setfirstcolor") && !args[0].equalsIgnoreCase("setsecondcolor") && !args[0].equalsIgnoreCase("togglethirdcolor") && !args[0].equalsIgnoreCase("setthirdcolor") && !args[0].equalsIgnoreCase("setnegativecolor") && !args[0].equalsIgnoreCase("setpositivecolor") && !args[0].equalsIgnoreCase("setseparatorcolor") && !args[0].equalsIgnoreCase("setname") && !args[0].equalsIgnoreCase("setdiscordip") && !args[0].equalsIgnoreCase("sethitdetection") && !args[0].equalsIgnoreCase("setprotocolsupport") && !args[0].equalsIgnoreCase("reload")) {
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "setfirstcolor": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setFirstColor(color);
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "First color has been updated to " + Bukkit.getServer().getFirstColor() + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setfirstcolor <string>");
                }
            }
            break;
            case "setsecondcolor": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setSecondColor(color);
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "Second color has been updated to " + Bukkit.getServer().getFirstColor() + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setfirstcolor <string>");
                }
            }
            break;
            case "togglethirdcolor": {
                if (Bukkit.getServer().isThirdColor()) {
                    Bukkit.getServer().setThirdColor(false);
                    sender.sendMessage(Bukkit.getServer().getThirdColor() + "Third color has been toggled");
                } else {
                    Bukkit.getServer().setThirdColor(true);
                    sender.sendMessage(Bukkit.getServer().getFirstColor() + "Third color has been disabled");
                }
            }
            break;
            case "setthirdcolor": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setThirdColor(color);
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "Third color has been updated to " + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setthirdcolor <string>");
                }
            }
            break;
            case "setnegativecolor": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setNegativeColor(color);
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "Negative color has been updated to " + Bukkit.getServer().getNegativeColor() + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setnegativecolor <string>");
                }
            }
            break;
            case "positivecolor": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setPositiveColor(color);
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "Positive color has been updated to " + Bukkit.getServer().getPositiveColor() + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setnegativecolor <string>");
                }
            }
            case "setseparatorcolor": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setSeparatorColor(color);
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "Separator color has been updated to " + Bukkit.getServer().getSeparatorColor() + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setseparatorcolor <string>");
                }
            }
            break;
            case "setname": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setPSName(color);
                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Name has been updated to " + Bukkit.getServer().getSeparatorColor() + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setname <string>");
                }
            }
            break;
            case "setdiscordip": {
                if (args.length > 1) {
                    String color = args[1];

                    Bukkit.getServer().setDiscordIP(color);
                    sender.sendMessage(Bukkit.getServer().getPositiveColor() + "DiscordIP has been updated to " + color);
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setdiscordip <string>");
                }
            }
            break;
            case "sethitdetection": {
                if (args.length > 1) {
                    if (isInteger(args[1])) {
                        int number = Integer.parseInt(args[1]);

                        Bukkit.getServer().setHitDetection(number);
                        sender.sendMessage(Bukkit.getServer().getSecondColor() + "Hit Detection has been uptated to " + Bukkit.getServer().getFirstColor() + Bukkit.getServer().getHitDetection());
                    } else {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You must enter an integer.");
                    }
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot sethitdetection <int>");
                }
            }
            break;
            case "setprotocolsupport": {
                if (args.length > 1) {
                    if (BooleanUtil.isBoolean(args[1])) {
                        boolean bool = Boolean.parseBoolean(args[1]);
                        if (bool == Bukkit.getServer().isProtocolSupport()) {
                            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "ProtocolSupport is already toggled");
                            return true;
                        }

                        if (!bool == !Bukkit.getServer().isProtocolSupport()) {
                            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "ProtocolSupport is already disabled");
                            return true;
                        }

                        Bukkit.getServer().setProtocolSupport(bool);
                        sender.sendMessage(Bukkit.getServer().getSecondColor() + "Protocol Support has been set to " + Bukkit.getServer().getFirstColor() + Bukkit.getServer().isProtocolSupport());
                    } else {
                        sender.sendMessage("You must enter a boolean (true or false)");
                    }
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /spookyspigot setprotocolsupport <boolean>");
                }
            }
            break;
            case "reload": {
                Bukkit.getServer().initPSpigot(PluginLoadOrder.RELOAD);
                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Config has been reloaded.");
            }
            break;
        }

        return true;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "                  Classic Commands:");
        sender.sendMessage("   ");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setprotocolsupport <boolean>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the protocolsupport");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setname <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the Server Name");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setdiscordip <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the DiscordIP");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot sethitdetection <int>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the hit detection");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot reload" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Reload SpookySpigot");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/knockback" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Config the knockback");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/potions" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Config the potions");
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "---------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "                  Color Commands:");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setfirstcolor <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the first color");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setsecondcolor <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the second color");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot togglethirdcolor" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Toggle the third color");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setthirdcolor <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the third color");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setnegativecolor <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the negative color");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setpositivecolor <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the positive color");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/spookyspigot setseparatorcolor <string>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Set the separator color");
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "---------------------------------------------");
    }
}
