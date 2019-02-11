/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.server.Packet
 *  net.minecraft.server.PacketListener
 */
package protocolsupport.protocol.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketListener;

public interface IPacketEncoder {
    public void encode(ChannelHandlerContext var1, Packet<PacketListener> var2, ByteBuf var3) throws Exception;
}

