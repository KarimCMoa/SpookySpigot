/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.play;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddlePlayerAbilities
extends ServerBoundMiddlePacket {
    protected int flags;
    protected float flySpeed;
    protected float walkSpeed;

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_ABILITIES.get());
        creator.writeByte(this.flags);
        creator.writeFloat(this.flySpeed);
        creator.writeFloat(this.walkSpeed);
        return RecyclableSingletonList.create(creator.create());
    }
}

