/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.status;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleServerInfo<T>
extends ClientBoundMiddlePacket<T> {
    protected String pingJson;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.pingJson = serializer.readString(32767);
    }
}

