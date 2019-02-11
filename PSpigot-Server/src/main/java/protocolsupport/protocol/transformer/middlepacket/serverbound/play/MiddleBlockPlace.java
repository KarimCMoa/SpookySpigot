/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.ItemStack
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.play;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleBlockPlace
extends ServerBoundMiddlePacket {
    protected BlockPosition position;
    protected int face;
    protected ItemStack itemstack;
    protected int cX;
    protected int cY;
    protected int cZ;

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_BLOCK_PLACE.get());
        creator.writePosition(this.position);
        creator.writeByte(this.face);
        creator.writeItemStack(this.itemstack);
        creator.writeByte(this.cX);
        creator.writeByte(this.cY);
        creator.writeByte(this.cZ);
        return RecyclableSingletonList.create(creator.create());
    }
}

