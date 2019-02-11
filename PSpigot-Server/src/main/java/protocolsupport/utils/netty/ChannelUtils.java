/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  net.minecraft.server.EntityPlayer
 *  net.minecraft.server.NetworkManager
 *  net.minecraft.server.PacketListener
 *  net.minecraft.server.PlayerConnection
 *  org.bukkit.craftbukkit.entity.CraftPlayer
 *  org.bukkit.entity.Player
 */
package protocolsupport.utils.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import java.net.SocketAddress;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.NetworkManager;
import net.minecraft.server.PacketListener;
import net.minecraft.server.PlayerConnection;
import org.bukkit.entity.Player;

public class ChannelUtils {
    public static Player getBukkitPlayer(Channel channel) {
        return ChannelUtils.getPlayer(ChannelUtils.getNetworkManager(channel)).getBukkitEntity();
    }

    public static EntityPlayer getPlayer(NetworkManager networkManager) {
        return ((PlayerConnection)networkManager.getPacketListener()).player;
    }

    public static SocketAddress getNetworkManagerSocketAddress(Channel channel) {
        return ChannelUtils.getNetworkManager(channel).getSocketAddress();
    }

    public static NetworkManager getNetworkManager(Channel channel) {
        return (NetworkManager)channel.pipeline().get("packet_handler");
    }

    public static byte[] toArray(ByteBuf buf) {
        byte[] result = new byte[buf.readableBytes()];
        buf.readBytes(result);
        return result;
    }

    public static int readVarInt(ByteBuf from) {
        byte b0;
        int value = 0;
        int length = 0;
        do {
            b0 = from.readByte();
            value |= (b0 & 127) << length++ * 7;
            if (length <= 5) continue;
            throw new RuntimeException("VarInt too big");
        } while ((b0 & 128) == 128);
        return value;
    }

    public static void writeVarInt(ByteBuf to, int i) {
        while ((i & -128) != 0) {
            to.writeByte(i & 127 | 128);
            i >>>= 7;
        }
        to.writeByte(i);
    }
}

