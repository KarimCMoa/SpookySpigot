/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.BlockPosition;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleUseBed;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class UseBed
extends MiddleUseBed<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_BED_ID, version);
        serializer.writeInt(this.entityId);
        serializer.writeByte(0);
        serializer.writeInt(this.bed.getX());
        serializer.writeByte(this.bed.getY());
        serializer.writeInt(this.bed.getZ());
        return RecyclableSingletonList.create(serializer);
    }
}

