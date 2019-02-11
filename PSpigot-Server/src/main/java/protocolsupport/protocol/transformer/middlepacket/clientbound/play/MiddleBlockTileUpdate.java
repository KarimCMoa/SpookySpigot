/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.NBTTagCompound
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import net.minecraft.server.NBTTagCompound;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlock;

public abstract class MiddleBlockTileUpdate<T>
extends MiddleBlock<T> {
    protected int type;
    protected NBTTagCompound tag;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        super.readFromServerData(serializer);
        this.type = serializer.readUnsignedByte();
        this.tag = serializer.readTag();
    }
}

