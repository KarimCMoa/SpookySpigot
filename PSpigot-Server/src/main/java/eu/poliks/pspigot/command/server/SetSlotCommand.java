package eu.poliks.pspigot.command.server;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSlotCommand extends Command
{
    public SetSlotCommand() {
        super("setslots");
    }


    private void setMaxPlayers(int amount)
            throws ReflectiveOperationException
    {
        Object playerlist = Class.forName("org.bukkit.craftbukkit.v1_8_R3.CraftServer").getDeclaredMethod("getHandle", (Class[])null).invoke(Bukkit.getServer(), (Object[])null);
        Field maxplayers = playerlist.getClass().getSuperclass().getDeclaredField("maxPlayers");
        maxplayers.setAccessible(true);
        maxplayers.set(playerlist, Integer.valueOf(amount));
    }

    public boolean execute(CommandSender sender, String currentAlias, String[] args)
    {
        if (sender.hasPermission("spookyspigot.setslots") ) {
            if (args.length < 1)
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/" + currentAlias + " <amount>"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Current: " + ChatColor.GREEN + Bukkit.getServer().getMaxPlayers()));
            }
            else
            {
                Integer amount = Integer.valueOf(Integer.parseInt(args[0]));
                if (amount.intValue() <= 0)
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c'" + amount + "' is not a valid number."));
                }
                else
                {
                    for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
                        if (staff.isOp()) {
                            staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Updated the player slots to &a" + amount));
                        }
                    }
                    try
                    {
                        setMaxPlayers(amount.intValue());
                    }
                    catch (ReflectiveOperationException expeption)
                    {
                        expeption.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
}
