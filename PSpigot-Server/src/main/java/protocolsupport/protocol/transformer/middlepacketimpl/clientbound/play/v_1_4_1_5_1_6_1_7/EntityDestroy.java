/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntityDestroy;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.utils.Utils;
import protocolsupport.utils.recyclable.RecyclableArrayList;
import protocolsupport.utils.recyclable.RecyclableCollection;

public class EntityDestroy
extends MiddleEntityDestroy<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
        RecyclableArrayList<PacketData> datas = RecyclableArrayList.create();
        for (int[] part : Utils.splitArray(this.entityIds, 120)) {
            PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_ENTITY_DESTROY_ID, version);
            serializer.writeByte(part.length);
            for (int i = 0; i < part.length; ++i) {
                serializer.writeInt(part[i]);
            }
            datas.add(serializer);
        }
        return datas;
    }
}

