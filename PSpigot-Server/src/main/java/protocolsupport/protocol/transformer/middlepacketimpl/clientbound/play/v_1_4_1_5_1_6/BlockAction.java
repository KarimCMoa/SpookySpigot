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
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlockAction;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockAction
extends MiddleBlockAction<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_BLOCK_ACTION_ID, version);
        serializer.writeInt(this.position.getX());
        serializer.writeShort(this.position.getY());
        serializer.writeInt(this.position.getZ());
        serializer.writeByte(this.info1);
        serializer.writeByte(this.info2);
        serializer.writeShort(this.type);
        return RecyclableSingletonList.create(serializer);
    }
}

