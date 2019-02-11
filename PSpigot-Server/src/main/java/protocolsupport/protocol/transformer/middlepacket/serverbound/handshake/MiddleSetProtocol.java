/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.handshake;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.Packet;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleSetProtocol
extends ServerBoundMiddlePacket {
    protected String hostname;
    protected int port;
    protected int nextState;

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.HANDSHAKE_START.get());
        creator.writeVarInt(ProtocolVersion.getLatest().getId());
        creator.writeString(this.hostname);
        creator.writeShort(this.port);
        creator.writeVarInt(this.nextState);
        return RecyclableSingletonList.create(creator.create());
    }
}

