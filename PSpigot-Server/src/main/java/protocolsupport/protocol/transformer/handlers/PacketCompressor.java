/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.server.PacketCompressor
 */
package protocolsupport.protocol.transformer.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import protocolsupport.utils.netty.ChannelUtils;
import protocolsupport.utils.netty.Compressor;

public class PacketCompressor
extends net.minecraft.server.PacketCompressor {
    private final Compressor compressor = Compressor.create();
    private final int threshold;

    public PacketCompressor(int threshold) {
        super(threshold);
        this.threshold = threshold;
    }

    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        this.compressor.recycle();
    }

    protected void a(ChannelHandlerContext ctx, ByteBuf from, ByteBuf to) throws Exception {
        int readable = from.readableBytes();
        if (readable < this.threshold) {
            ChannelUtils.writeVarInt(to, 0);
            to.writeBytes(from);
        } else {
            ChannelUtils.writeVarInt(to, readable);
            to.writeBytes(this.compressor.compress(ChannelUtils.toArray(from.readSlice(readable))));
        }
    }
}

