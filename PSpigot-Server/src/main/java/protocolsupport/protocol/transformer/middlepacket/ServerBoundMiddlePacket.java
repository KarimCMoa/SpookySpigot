/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket;

import java.io.IOException;
import net.minecraft.server.Packet;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.MiddlePacket;
import protocolsupport.utils.recyclable.RecyclableCollection;

public abstract class ServerBoundMiddlePacket
extends MiddlePacket {
    public abstract void readFromClientData(PacketDataSerializer var1) throws IOException;

    public abstract RecyclableCollection<? extends Packet<?>> toNative() throws Exception;
}

