/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import net.minecraft.server.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleSpawnPainting<T>
extends ClientBoundMiddlePacket<T> {
    protected int entityId;
    protected String type;
    protected BlockPosition position;
    protected int direction;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.entityId = serializer.readVarInt();
        this.type = serializer.readString(13);
        this.position = serializer.readPosition();
        this.direction = serializer.readByte();
    }
}

