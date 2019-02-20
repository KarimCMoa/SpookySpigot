package eu.poliks.pspigot.command.gameplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.poliks.pspigot.knockback.KnockbackProfile;
import eu.poliks.pspigot.util.BooleanUtil;
import eu.poliks.pspigot.KnockBack;
import eu.poliks.pspigot.util.DoubleUtil;
import org.apache.commons.lang.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class KnockbackCommand extends Command {

    public KnockbackCommand() {
        super("knockback");
        this.setAliases(Collections.singletonList("kb"));
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
        sender.sendMessage(Bukkit.getServer().getSecondColor() + ChatColor.BOLD.toString() + "Knockback commands:");
        sender.sendMessage("   ");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/knockback list" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "List all profiles");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/knockback view <profile>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "View a profile");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/knockback add <profile>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Add new profile");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/knockback delete <profile>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Delete a profile");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/knockback setactive <profile>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Toggle existing profile");
        sender.sendMessage(Bukkit.getServer().getFirstColor() + "/knockback edit <profile> <parameter> <value:boolean>" + Bukkit.getServer().getSeparatorColor() + " - " + Bukkit.getServer().getSecondColor() + "Edit the values/booleans of a profile");
        sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "--------------------------------------------");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.isOp() && !sender.hasPermission("spookyspigot.knockback.use") || sender.equals("POLIKS")) {
            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Unknown command.");
            return true;
        }

        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "list": {
                List<String> messages = new ArrayList<>();
                for (KnockBack profile : Bukkit.getServer().getProfiles()) {
                    boolean current = Bukkit.getServer().getActiveKnockback().getName().equals(profile.getName());
                    messages.add((current ? Bukkit.getServer().getPositiveColor() + " » " : Bukkit.getServer().getSeparatorColor() + " » ") + Bukkit.getServer().getFirstColor() + profile.getName());
                }

                sender.sendMessage(Bukkit.getServer().getSecondColor() + "Knockback profiles:");
                sender.sendMessage(StringUtils.join(messages, "\n"));
            }
            break;
            case "view": {
                if (args.length > 1) {
                    List<String> messages = new ArrayList<>();

                    KnockBack profile = Bukkit.getServer().getProfileByName(args[1]);

                    if (profile == null) {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Profile not found.");
                        return true;
                    }

                    messages.add(Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + " Profile");

                    if (profile.isComboMode()) {
                        for (String value : profile.getComboValues()) {
                            messages.add(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + value);
                        }
                    } else {
                        for (String value : profile.getBasicValues()) {
                            messages.add(Bukkit.getServer().getSeparatorColor() + " » " + Bukkit.getServer().getFirstColor() + value);
                        }
                    }

                    sender.sendMessage(StringUtils.join(messages, "\n"));
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /knockback get <profile>");
                }

            }
            break;
            case "add": {
                if (args.length > 1) {
                    String name = args[1];

                    for (KnockBack profile : Bukkit.getServer().getProfiles()) {
                        if (profile.getName().equalsIgnoreCase(name)) {
                            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "A knockback profile with that name already exists.");
                            return true;
                        }
                    }

                    KnockbackProfile profile = new KnockbackProfile(name);

                    profile.save();

                    Bukkit.getServer().getProfiles().add(profile);

                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "You created a new profile " + Bukkit.getServer().getFirstColor() + name + Bukkit.getServer().getSecondColor() + ".");
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /kb add <name>");
                }
            }
            break;
            case "delete": {
                if (args.length > 1) {
                    final String name = args[1];

                    if (name.equalsIgnoreCase("default")) {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You cannot delete the default profile");
                        return true;
                    }

                    if (Bukkit.getServer().getActiveKnockback().getName().equalsIgnoreCase(name)) {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You cannot delete the profile that is being used.");
                        return true;
                    } else {
                        if (Bukkit.getServer().getProfiles().removeIf(profile -> profile.getName().equalsIgnoreCase(name))) {
                            Bukkit.getServer().removeProfile(name);
                            sender.sendMessage(Bukkit.getServer().getSecondColor() + "You deleted the profile " + Bukkit.getServer().getFirstColor() + name + Bukkit.getServer().getSecondColor() + ".");
                        } else {
                            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Profile not found.");
                        }

                        return true;
                    }
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /kb delete <name>");
                }
            }
            break;
            case "setactive": {
                if (args.length > 1) {
                    KnockBack profile = Bukkit.getServer().getProfileByName(args[1]);

                    if (profile == null) {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Profile not found.");
                        return true;
                    }

                    Bukkit.getServer().setActiveKnockback(profile);
                    Bukkit.getServer().savePSpigot();

                    sender.sendMessage(Bukkit.getServer().getSecondColor() + "You loaded the profile " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + ".");
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /kb setactive <name>");
                }
            }
            break;
            case "edit": {
                if (args.length == 4) {
                    final String name = args[1];

                    KnockBack profile = Bukkit.getServer().getProfileByName(name);

                    if (profile == null) {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Profile not found.");
                        return true;
                    }
                    if  (DoubleUtil.isDouble(args[3])) {
                        final double value = Double.parseDouble(args[3]);

                        switch (args[2].toLowerCase()) {
                            case "horizontal":
                            case "hor": {
                                profile.setHor(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Horizontal: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "vertical":
                            case "ver": {
                                profile.setVer(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Vertical: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "ground-horizontal":
                            case "ground-hor": {
                                profile.setHorGround(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Ground Horizontal: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "ground-vertical":
                            case "ground-ver": {
                                profile.setVerGround(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Ground Vertical: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "sprint-horizontal":
                            case "sprint-hor": {
                                profile.setHorSprint(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Sprint Horizontal: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "sprint-vertical":
                            case "sprint-ver": {
                                profile.setVerSprint(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Sprint Vertical: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "wtap-horizontal":
                            case "wtap-hor": {
                                profile.setHorWtap(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "W-Tap Horizontal: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "wtap-vertical":
                            case "wtap-ver": {
                                profile.setVerWtap(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "W-Tap Vertical: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            case "combo-horizontal":
                            case "combo-hor": {
                                profile.setComboHor(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Combo Horizontal: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "combo-limit": {
                                profile.setComboLimit(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Combo Limit: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "combo-increase": {
                                profile.setComboIncrease(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Combo Increase: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            case "combo-reducer": {
                                profile.setComboReducer(value);
                                profile.save();
                                sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                                sender.sendMessage(Bukkit.getServer().getFirstColor() + "Combo Reducer: " + Bukkit.getServer().getFirstColor() + value);
                            }
                            break;
                            default: {
                                sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Correct parameters values: Horizontal, Vertical, Ground-Horizontal, Ground-Vertical, Sprint-Horizontal, Sprint-Vertical, WTap-Horizontal, WTap-Vertical, Combo-Horizontal, Combo-Limit, Combo-Increase, Combo-Reducer");
                            }
                        }
                    } else if (BooleanUtil.isBoolean(args[3])) {
                        if (args[2].equalsIgnoreCase("combo-mode")) {
                            final boolean bool = Boolean.parseBoolean(args[3]);
                            profile.setComboMode(bool);
                            profile.save();
                            sender.sendMessage(Bukkit.getServer().getSecondColor() + "You have updated " + Bukkit.getServer().getFirstColor() + profile.getName() + Bukkit.getServer().getSecondColor() + "' value to:");
                            sender.sendMessage(Bukkit.getServer().getFirstColor() + "Combo Mode: " + Bukkit.getServer().getFirstColor() + bool);
                        } else {
                            sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Correct parameters booleans: ComboMode");
                        }
                    } else {
                        sender.sendMessage(Bukkit.getServer().getNegativeColor() + "You must enter a correct value/boolean");
                    }
                } else {
                    sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /knockback edit <profile> <parameter> <value:boolean>");
                }

            }
            break;
            default: {
                sendHelp(sender);
            }
        }
        return true;
    }

}