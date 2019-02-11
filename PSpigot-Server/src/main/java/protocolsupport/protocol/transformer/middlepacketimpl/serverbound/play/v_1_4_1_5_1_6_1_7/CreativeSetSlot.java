/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.ItemStack
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import net.minecraft.server.ItemStack;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleCreativeSetSlot;

public class CreativeSetSlot
extends MiddleCreativeSetSlot {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.slot = serializer.readShort();
        this.itemstack = serializer.readItemStack();
    }
}

