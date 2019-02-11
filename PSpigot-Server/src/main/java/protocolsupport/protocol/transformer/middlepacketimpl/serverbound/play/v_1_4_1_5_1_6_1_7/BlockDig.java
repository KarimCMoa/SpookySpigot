/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import net.minecraft.server.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleBlockDig;

public class BlockDig
extends MiddleBlockDig {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.status = serializer.readUnsignedByte();
        this.position = new BlockPosition(serializer.readInt(), (int)serializer.readUnsignedByte(), serializer.readInt());
        this.face = serializer.readUnsignedByte();
    }
}

