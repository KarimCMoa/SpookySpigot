package eu.poliks.pspigot.async;

import org.bukkit.event.player.*;
import org.bukkit.event.*;
import java.net.*;
import org.bukkit.entity.*;

public class AsyncPlayerLoginEvent extends PlayerEvent
{
    private static final HandlerList handlers;
    private String disconnectMessage;
    private final InetAddress address;
    private String payload;

    public AsyncPlayerLoginEvent(final Player who, final InetAddress address, final String payload) {
        super(who, true);
        this.disconnectMessage = null;
        this.address = address;
        this.payload = payload;
    }

    public String getPayload() {
        return this.payload;
    }

    @Override
    public HandlerList getHandlers() {
        return AsyncPlayerLoginEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return AsyncPlayerLoginEvent.handlers;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public String getDisconnectMessage() {
        return this.disconnectMessage;
    }

    public void setCancelled(final String message) {
        this.disconnectMessage = message;
    }

    static {
        handlers = new HandlerList();
    }
}
