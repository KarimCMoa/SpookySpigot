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
 */
package protocolsupport.protocol.transformer.v_1_8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.io.IOException;
import java.util.List;
import net.minecraft.server.EnumProtocol;
import net.minecraft.server.EnumProtocolDirection;
import net.minecraft.server.NetworkManager;
import net.minecraft.server.Packet;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.core.IPacketDecoder;
import protocolsupport.utils.netty.WrappingBuffer;

public class PacketDecoder
implements IPacketDecoder {
    private static final AttributeKey<EnumProtocol> currentStateAttrKey = NetworkManager.c;
    private final WrappingBuffer buffer = new WrappingBuffer();
    private final PacketDataSerializer serializer = new PacketDataSerializer(this.buffer, ProtocolVersion.MINECRAFT_1_8);

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        if (!input.isReadable()) {
            return;
        }
        this.buffer.setBuf(input);
        EnumProtocol currentProtocol = (EnumProtocol)ctx.channel().attr(currentStateAttrKey).get();
        int packetId = this.serializer.readVarInt();
        Packet packet = currentProtocol.a(EnumProtocolDirection.SERVERBOUND, packetId);
        if (packet == null) {
            throw new IOException("Bad packet id " + packetId);
        }
        packet.a((net.minecraft.server.PacketDataSerializer)this.serializer);
        list.add((Object)packet);
    }
}

