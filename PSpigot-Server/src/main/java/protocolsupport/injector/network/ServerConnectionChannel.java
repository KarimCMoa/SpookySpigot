package protocolsupport.injector.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import java.io.PrintStream;
import java.util.List;
import net.minecraft.server.EnumProtocolDirection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.NetworkManager;
import net.minecraft.server.PacketListener;
import protocolsupport.protocol.core.FakePacketListener;
import protocolsupport.protocol.core.initial.InitialPacketDecoder;
import protocolsupport.protocol.core.timeout.SimpleReadTimeoutHandler;
import protocolsupport.protocol.core.wrapped.WrappedDecoder;
import protocolsupport.protocol.core.wrapped.WrappedEncoder;
import protocolsupport.protocol.core.wrapped.WrappedPrepender;
import protocolsupport.protocol.core.wrapped.WrappedSplitter;

public class ServerConnectionChannel
extends ChannelInitializer<Channel> {
    private final List<NetworkManager> networkManagers;
    private static final int IPTOS_THROUGHPUT = 8;
    private static final int IPTOS_LOWDELAY = 16;

    public ServerConnectionChannel(List<NetworkManager> networkManagers) {
        this.networkManagers = networkManagers;
    }

    protected void initChannel(Channel channel) {
        try {
            channel.config().setOption(ChannelOption.IP_TOS, Integer.valueOf(24));
        } catch (ChannelException channelexception) {
            if (MinecraftServer.getServer().isDebugging()) {
                System.err.println("Unable to set IP_TOS option: " + channelexception.getMessage());
            }
        } try {
            channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
        } catch (ChannelException channelexception) {
            if (MinecraftServer.getServer().isDebugging()) {
                System.err.println("Unable to set TCP_NODELAY option: " + channelexception.getMessage());
            }
        }
        channel.pipeline().addLast("timeout", new SimpleReadTimeoutHandler(30)).addLast("initial_decoder", new InitialPacketDecoder()).addLast("splitter", new WrappedSplitter()).addLast("decoder", new WrappedDecoder()).addLast("prepender", new WrappedPrepender()).addLast("encoder", new WrappedEncoder());
        NetworkManager networkmanager = new NetworkManager(EnumProtocolDirection.SERVERBOUND);
        networkmanager.a(new FakePacketListener());
        this.networkManagers.add(networkmanager);
        channel.pipeline().addLast("packet_handler", networkmanager);
    }
}

