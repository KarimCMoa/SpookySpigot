package eu.poliks.pspigot.ipwhitelist.listener;

import eu.poliks.pspigot.ipwhitelist.IPWhiteList;
import eu.poliks.pspigot.ipwhitelist.util.ReflectUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener {

    IPWhiteList plugin = IPWhiteList.instance;
    ReflectUtils reflect = new ReflectUtils();
    Map<UUID, InetAddress> addresses = new HashMap();

    @EventHandler
    public void onPlayerChannelRegistered(PlayerRegisterChannelEvent ev) {
        if (ev.getChannel().equals("BungeeCord")) {
            this.plugin.getIpHandler().getBungeeips().whitelist(addresses.get(ev.getPlayer().getUniqueId()).getHostAddress());
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerLogin(final PlayerLoginEvent ev) {
        final InetAddress addr = ev.getRealAddress();
        if (!this.plugin.getIpHandler().getBungeeips().allow(addr)) {
            ev.setKickMessage("You can't use this ip to connect to the server");
            ev.setResult(PlayerLoginEvent.Result.KICK_WHITELIST);
        }
    }
}
