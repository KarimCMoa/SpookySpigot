/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.BlockPosition
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import net.minecraft.server.BlockPosition;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleTabComplete;

public class TabComplete
extends MiddleTabComplete {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) {
        this.string = serializer.readString(32767);
        this.position = null;
    }
}

