/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.util.Attribute
 *  io.netty.util.AttributeKey
 *  net.minecraft.server.EnumProtocol
 *  net.minecraft.server.EnumProtocolDirection
 *  net.minecraft.server.NetworkManager
 *  net.minecraft.server.Packet
 *  net.minecraft.server.PacketDataSerializer
 *  net.minecraft.server.PacketListener
 */
package protocolsupport.protocol.transformer.v_1_8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.io.IOException;
import net.minecraft.server.EnumProtocol;
import net.minecraft.server.EnumProtocolDirection;
import net.minecraft.server.NetworkManager;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListener;
import protocolsupport.protocol.core.IPacketEncoder;

public class PacketEncoder
implements IPacketEncoder {
    private static final EnumProtocolDirection direction = EnumProtocolDirection.CLIENTBOUND;
    private static final AttributeKey<EnumProtocol> currentStateAttrKey = NetworkManager.c;

    @Override
    public void encode(ChannelHandlerContext ctx, Packet<PacketListener> packet, ByteBuf output) throws Exception {
        Channel channel = ctx.channel();
        EnumProtocol currentProtocol = (EnumProtocol)channel.attr(currentStateAttrKey).get();
        Integer packetId = currentProtocol.a(direction, packet);
        if (packetId == null) {
            throw new IOException("Can't serialize unregistered packet");
        }
        PacketDataSerializer serializer = new PacketDataSerializer(output);
        serializer.b(packetId.intValue());
        packet.b(serializer);
    }
}

