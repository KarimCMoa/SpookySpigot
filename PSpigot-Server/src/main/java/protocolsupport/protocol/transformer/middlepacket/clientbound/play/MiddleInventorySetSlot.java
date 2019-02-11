/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.ItemStack
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import java.io.IOException;
import net.minecraft.server.ItemStack;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleInventorySetSlot<T>
extends ClientBoundMiddlePacket<T> {
    protected int windowId;
    protected int slot;
    protected ItemStack itemstack;

    @Override
    public boolean needsPlayer() {
        return true;
    }

    @Override
    public void readFromServerData(PacketDataSerializer serializer) throws IOException {
        this.windowId = serializer.readUnsignedByte();
        this.slot = serializer.readShort();
        this.itemstack = serializer.readItemStack();
    }
}

