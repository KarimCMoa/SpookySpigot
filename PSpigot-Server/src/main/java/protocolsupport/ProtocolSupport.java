package protocolsupport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import protocolsupport.injector.ServerInjector;
import protocolsupport.injector.network.NettyInjector;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;
import protocolsupport.utils.netty.Allocator;
import protocolsupport.utils.netty.Compressor;

public class ProtocolSupport  {
    public static void load() {
        if (!Bukkit.getServer().isProtocolSupport()) {
            return;
        }
        try {
            Allocator.init();
            Compressor.init();
            ServerBoundPacket.init();
            ClientBoundPacket.init();
            InitialPacketDecoder.init();
            AbstractLoginListener.init();
            NettyInjector.inject();
            ServerInjector.inject();
        }
        catch (Throwable t) {
            t.printStackTrace();
            Bukkit.shutdown();
        }
    }

    public static void disable() {
        Bukkit.shutdown();
    }

    public static void logWarning(String message) {
        Bukkit.getServer().getLogger().warning(message);
    }

    public static void logInfo(String message) {
        Bukkit.getServer().getLogger().info(message);
    }
}

