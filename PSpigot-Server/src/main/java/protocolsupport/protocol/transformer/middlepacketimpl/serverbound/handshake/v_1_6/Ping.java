/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.handshake.v_1_6;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.Packet;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class Ping
extends ServerBoundMiddlePacket {
    protected String hostname;
    protected int port;

    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        serializer.readUnsignedByte();
        serializer.readUnsignedByte();
        serializer.readString(32767);
        serializer.readUnsignedShort();
        serializer.readUnsignedByte();
        this.hostname = serializer.readString(32767);
        this.port = serializer.readInt();
    }

    public RecyclableCollection<Packet<?>> toNative() throws Exception {
        RecyclableArrayList<Packet<?>> packets = RecyclableArrayList.create();
        PacketCreator hsscreator = PacketCreator.create(ServerBoundPacket.HANDSHAKE_START.get());
        hsscreator.writeVarInt(ProtocolVersion.getLatest().getId());
        hsscreator.writeString(this.hostname);
        hsscreator.writeShort(this.port);
        hsscreator.writeVarInt(1);
        packets.add(hsscreator.create());
        packets.add(ServerBoundPacket.STATUS_REQUEST.get());
        return packets;
    }
}

