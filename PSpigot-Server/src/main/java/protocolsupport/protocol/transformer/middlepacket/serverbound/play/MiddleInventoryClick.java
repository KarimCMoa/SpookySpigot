/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.ItemStack
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.play;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleInventoryClick
extends ServerBoundMiddlePacket {
    protected int windowId;
    protected int slot;
    protected int button;
    protected int actionNumber;
    protected int mode;
    protected ItemStack itemstack;

    @Override
    public boolean needsPlayer() {
        return true;
    }

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_WINDOW_CLICK.get());
        creator.writeByte(this.windowId);
        creator.writeShort(this.slot);
        creator.writeByte(this.button);
        creator.writeShort(this.actionNumber);
        creator.writeByte(this.mode);
        creator.writeItemStack(this.itemstack);
        return RecyclableSingletonList.create(creator.create());
    }
}

