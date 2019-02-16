package org.spigotmc;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.Date;

public class TicksPerSecondCommand extends Command {
	long startTime = System.currentTimeMillis();

	public TicksPerSecondCommand(String name) {
		super(name);
		this.description = "Gets the current ticks per second for the server";
		this.usageMessage = "/tps";
		this.setPermission("bukkit.command.tps");
	}


	@Override
	public boolean execute(CommandSender sender, String currentAlias, String[] args) {
		if (!testPermission(sender)) {
			return true;
		}

		final Runtime runtime = Runtime.getRuntime();
		double usedMemory = runtime.totalMemory() - runtime.freeMemory();
		double freeMemory = runtime.maxMemory() - usedMemory;
		final double[] tps = Bukkit.spigot().getTPS();
		final String[] tpsAvg = new String[tps.length];
		for (int i = 0; i < tps.length; ++i) {
			tpsAvg[i] = format(tps[i]);
		}
		final String text = StringUtils.join(tpsAvg, ", ");
		final int lag = (int)Math.round(100.0 - Bukkit.spigot().getTPS()[0] * 5.0);
		final String bar = this.getProgressBar(lag, 100, 20, "|", "&c", "&a");
		final String totalram = String.valueOf(Runtime.getRuntime().totalMemory() / 1048576L) + "MB";
		final String freeram = String.valueOf(Runtime.getRuntime().freeMemory() / 1048576L) + "MB";
		final String usedram = String.valueOf(Runtime.getRuntime().totalMemory() / 1048576L - Runtime.getRuntime().freeMemory() / 1048576L) + "MB";







		sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "-------------------------------------");
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Current Time" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + new Date().toString());
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Current TPS" + Bukkit.getServer().getSeparatorColor() + ": " + format(Bukkit.spigot().getTPS()[0]));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Server Current Lag" + Bukkit.getServer().getSeparatorColor() + ": " + bar + Bukkit.getServer().getSecondColor() + " (" + lag + "%)");
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Past Server TPS (1m, 5m, 15m)" + Bukkit.getServer().getSeparatorColor() + ": " + text);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Total Entities" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + this.getTotalEntities());
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Loaded Chunks" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + this.getLoadedChunks());
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Total Ram"+ Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + totalram);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Free Ram" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + freeram);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Used Ram" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + usedram);
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Total Memory" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + formatMem(runtime.maxMemory()));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Free Memory" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + formatMem(freeMemory));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "Used Memory" + Bukkit.getServer().getSeparatorColor() +  ": " + Bukkit.getServer().getFirstColor() + formatMem(usedMemory));
		sender.sendMessage(Bukkit.getServer().getSecondColor() + "All players" + Bukkit.getServer().getSeparatorColor() + ": " + Bukkit.getServer().getFirstColor() + Bukkit.getOfflinePlayers().length);
		sender.sendMessage(Bukkit.getServer().getSeparatorColor() + ChatColor.STRIKETHROUGH + "-------------------------------------");
		return true;
	}







	private static String format(double tps) {
		return ((tps > 18) ? ChatColor.GREEN : (tps > 16) ? ChatColor.YELLOW : ChatColor.RED).toString() + Math.round(tps * 100.0) / 100.0;
	}


	private static String formatMem(double mem) {
		return Bukkit.getServer().getFirstColor() + Math.round(mem / 1024 / 1024) + "MB";
	}

	public String getProgressBar(final int current, final int max, final int totalBars, final String symbol, final String completedColor, final String notCompletedColor) {
		final float percent = current / max;
		final int progressBars = (int)(totalBars * percent);
		final int leftOver = totalBars - progressBars;
		final StringBuilder sb = new StringBuilder();
		sb.append(ChatColor.translateAlternateColorCodes('&', completedColor));
		for (int i = 0; i < progressBars; ++i) {
			sb.append(symbol);
		}
		sb.append(ChatColor.translateAlternateColorCodes('&', notCompletedColor));
		for (int i = 0; i < leftOver; ++i) {
			sb.append(symbol);
		}
		return sb.toString();
	}

	private int getLoadedChunks() {
		int chunks = 0;
		for (final World world : Bukkit.getWorlds()) {
			chunks += world.getLoadedChunks().length;
		}
		return chunks;
	}

	private int getTotalEntities() {
		int entities = 0;
		for (final World world : Bukkit.getWorlds()) {
			entities += world.getEntities().size();
		}
		return entities;
	}
}
