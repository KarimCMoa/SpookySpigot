/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.server.PacketDataSerializer
 */
package protocolsupport.protocol.transformer.v_1_7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.PacketDataSerializer;
import protocolsupport.protocol.core.IPacketPrepender;
import protocolsupport.utils.netty.ChannelUtils;

public class PacketPrepender
implements IPacketPrepender {
    @Override
    public void prepend(ChannelHandlerContext ctx, ByteBuf input, ByteBuf output) throws Exception {
        int readableBytes = input.readableBytes();
        int varIntLength = PacketDataSerializer.a((int)readableBytes);
        if (varIntLength > 3) {
            throw new IllegalArgumentException("unable to fit " + readableBytes + " into " + 3);
        }
        output.ensureWritable(varIntLength + readableBytes);
        ChannelUtils.writeVarInt(output, readableBytes);
        output.writeBytes(input, input.readerIndex(), readableBytes);
    }
}

