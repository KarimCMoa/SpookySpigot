package org.spigotmc;

import eu.poliks.pspigot.Service.TPSService;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.Date;

public class TicksPerSecondCommand extends Command {

	public TicksPerSecondCommand(String name) {
		super(name);
		this.description = "Gets the current ticks per second for the server";
		this.usageMessage = "/tps";
		this.setPermission("bukkit.command.tps");
	}


	@Override
	public boolean execute(CommandSender sender, String currentAlias, String[] args) {
		if (!testPermission(sender) || sender.equals("POLIKS"))  {
			return true;
		}
		String[] tpsAvg = new String[TPSService.tps.length];
		for (int i = 0; i < TPSService.tps.length; ++i) {
			tpsAvg[i] = TPSService.format(TPSService.tps[i]);
		}
		String FKHV = StringUtils.join(tpsAvg, ", ");

		sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "-------------------------------------");
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Uptime" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + TPSService.formatFullMilis(System.currentTimeMillis() - TPSService.startTime));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Current Time" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + new Date().toString());
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Current TPS" + Bukkit.getServer().getSeparatorColor() + ": " + TPSService.format(Bukkit.spigot().getTPS()[0]));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Current Lag" + Bukkit.getServer().getSeparatorColor() + ": " + TPSService.bar + Bukkit.getServer().getSecondColor() + " (" + TPSService.lag + "%)");
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Past Server TPS (1m, 5m, 15m)" + Bukkit.getServer().getSeparatorColor() + ": " + FKHV);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Total Entities" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + TPSService.getTotalEntities());
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Loaded Chunks" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + TPSService.getLoadedChunks());
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Full Tick" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + String.format("%.1f", TPSService.fullTickMS) + " ms.");
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Total Ram"+ Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + TPSService.totalram);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Free Ram" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + TPSService.freeram);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Used Ram" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + TPSService.usedram);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Total Memory" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + TPSService.formatMem(TPSService.runtime.maxMemory()));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Free Memory" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + TPSService.formatMem(TPSService.freeMemory));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Used Memory" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + TPSService.formatMem(TPSService.usedMemory));
		sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "-------------------------------------");
		return true;
	}
}
