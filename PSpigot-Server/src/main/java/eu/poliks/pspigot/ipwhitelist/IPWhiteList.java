package eu.poliks.pspigot.ipwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import protocolsupport.server.listeners.PlayerListener;

public class IPWhiteList {

    public static IPWhiteList instance;

    public static JavaPlugin plugin = new JavaPlugin("tamer") {};

    private IpHandler ipHandler;

    public void onLoad() {
        instance = this;

        ipHandler = new IpHandler();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);
    }

    public void onDisable() {
        ipHandler.save();
    }

    public IpHandler getIpHandler() {
        return ipHandler;
    }
}
