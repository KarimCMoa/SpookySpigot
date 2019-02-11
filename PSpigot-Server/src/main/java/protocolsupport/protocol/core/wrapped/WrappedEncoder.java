/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToByteEncoder
 *  net.minecraft.server.Packet
 *  net.minecraft.server.PacketListener
 */
package protocolsupport.protocol.core.wrapped;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketListener;
import protocolsupport.protocol.core.IPacketEncoder;

public class WrappedEncoder
extends MessageToByteEncoder<Packet<PacketListener>> {
    private IPacketEncoder realEncoder = new IPacketEncoder(){

        @Override
        public void encode(ChannelHandlerContext ctx, Packet<PacketListener> packet, ByteBuf output) throws Exception {
        }
    };

    public WrappedEncoder() {
        super(true);
    }

    public void setRealEncoder(IPacketEncoder realEncoder) {
        this.realEncoder = realEncoder;
    }

    protected void encode(ChannelHandlerContext ctx, Packet<PacketListener> packet, ByteBuf output) throws Exception {
        this.realEncoder.encode(ctx, packet, output);
    }

}

