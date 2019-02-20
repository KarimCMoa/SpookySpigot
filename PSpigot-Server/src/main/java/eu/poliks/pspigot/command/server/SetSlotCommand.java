package eu.poliks.pspigot.command.server;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.Arrays;

public class SetSlotCommand extends Command
{
    public SetSlotCommand() {
        super("setslot");
        this.description = "Set server slot";
        this.usageMessage = "/setslot";
        this.setPermission("spookyspigot.setslot");
        this.setAliases(Arrays.asList("setslots"));

    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!this.testPermission(sender) || sender.equals("POLIKS")) {
            return true;
        }
        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(Bukkit.getServer().getNegativeColor() + "Usage: /setslot <slot>");
                return true;
            }
            if (args.length == 1) {
                final int slot = Integer.parseInt(args[0]);
                sender.sendMessage(Bukkit.getServer().getPositiveColor() + "Settled Server Slot Number to " + slot);
            }
        }
        return true;
    }
}
