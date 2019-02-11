/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.ItemStack
 */
package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6_1_7;

import java.io.IOException;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.ItemStack;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleBlockPlace;

public class BlockPlace
extends MiddleBlockPlace {
    @Override
    public void readFromClientData(PacketDataSerializer serializer) throws IOException {
        this.position = new BlockPosition(serializer.readInt(), (int)serializer.readUnsignedByte(), serializer.readInt());
        this.face = serializer.readUnsignedByte();
        this.itemstack = serializer.readItemStack();
        this.cX = serializer.readUnsignedByte();
        this.cY = serializer.readUnsignedByte();
        this.cZ = serializer.readUnsignedByte();
    }
}

