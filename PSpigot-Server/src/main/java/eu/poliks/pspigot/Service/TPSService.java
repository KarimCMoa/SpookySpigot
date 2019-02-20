package eu.poliks.pspigot.Service;

import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;

import java.text.DecimalFormat;

public class TPSService {

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      All                                                        */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static Runtime runtime = Runtime.getRuntime();

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      Format                                                             */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static String format(double tps) {
        return ((tps > 18) ? ChatColor.GREEN : (tps > 16) ? ChatColor.YELLOW : ChatColor.RED).toString() + Math.round(tps * 100.0) / 100.0;
    }


    public static String formatMem(double mem) {
        return Bukkit.getServer().getFirstColor() + Math.round(mem / 1024 / 1024) + "MB";
    }

    public static DecimalFormat df = new DecimalFormat("#.#");

    public static String formatFullMilis(Long milis) {
        double seconds = (double) Math.max(0, milis) / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        double weeks = days / 7;
        double months = days / 31;
        double years = months / 12;


        if (years >= 1) {
            return df.format(years) + " year" + (years != 1 ? "s" : "");
        } else if (months >= 1) {
            return df.format(months) + " month" + (months != 1 ? "s" : "");
        } else if (weeks >= 1) {
            return df.format(weeks) + " week" + (weeks != 1 ? "s" : "");
        } else if (days >= 1) {
            return df.format(days) + " day" + (days != 1 ? "s" : "");
        } else if (hours >= 1) {
            return df.format(hours) + " hour" + (hours != 1 ? "s" : "");
        } else if (minutes >= 1) {
            return df.format(minutes) + " minute" + (minutes != 1 ? "s" : "");
        } else {
            return df.format(seconds) + " second" + (seconds != 1 ? "s" : "");
        }
    }

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      ProgressBar                                                        */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static String getProgressBar(final int current, final int max, final int totalBars, final String symbol, final String completedColor, final String notCompletedColor) {
        float percent = current / max;
        int progressBars = (int)(totalBars * percent);
        int leftOver = totalBars - progressBars;
        StringBuilder sb = new StringBuilder();
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

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      Chunks                                                             */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static int getLoadedChunks() {
        int chunks = 0;
        for (final World world : Bukkit.getWorlds()) {
            chunks += world.getLoadedChunks().length;
        }
        return chunks;
    }

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      Entities                                                           */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static int getTotalEntities() {
        int entities = 0;
        for (final World world : Bukkit.getWorlds()) {
            entities += world.getEntities().size();
        }
        return entities;
    }

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      TPS                                                                */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static double[] tps = Bukkit.spigot().getTPS();

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      Memory                                                             */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static double freeMemory = TPSService.runtime.maxMemory() - TPSService.usedMemory;
    public static double usedMemory = TPSService.runtime.totalMemory() - TPSService.runtime.freeMemory();

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      startTime                                                          */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static long startTime = System.currentTimeMillis();

    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      Full Tick                                                          */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static double fullTickMS = MinecraftServer.getServer().fullTick / 1000000.0;


    /*-------------------------------------------------------------------------------------------------------------------------*/
    /*                                                      RAM                                                                */
    /*-------------------------------------------------------------------------------------------------------------------------*/

    public static int lag = (int)Math.round(100.0 - Bukkit.spigot().getTPS()[0] * 5.0);
    public static String bar = TPSService.getProgressBar(lag, 100, 20, "|", "&c", "&a");
    public static String totalram = Runtime.getRuntime().totalMemory() / 1048576L + "MB";
    public static String freeram = Runtime.getRuntime().freeMemory() / 1048576L + "MB";
    public static String usedram = Runtime.getRuntime().totalMemory() / 1048576L - Runtime.getRuntime().freeMemory() / 1048576L + "MB";


}
