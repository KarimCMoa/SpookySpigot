/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6;

import net.minecraft.server.Packet;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;

public class KickDisconnect
extends ServerBoundMiddlePacket {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        serializer.readString(32767);
    }

    public RecyclableCollection<Packet<?>> toNative() throws Exception {
        return RecyclableEmptyList.get();
    }
}

