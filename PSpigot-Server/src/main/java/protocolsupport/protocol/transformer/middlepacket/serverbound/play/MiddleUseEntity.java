/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Packet
 *  net.minecraft.server.PacketPlayInUseEntity
 *  net.minecraft.server.PacketPlayInUseEntity$EnumEntityUseAction
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.play;

import net.minecraft.server.Packet;
import net.minecraft.server.PacketPlayInUseEntity;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleUseEntity
extends ServerBoundMiddlePacket {
    protected int entityId;
    protected int action;

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_USE_ENTITY.get());
        creator.writeVarInt(this.entityId);
        creator.writeVarInt(this.action % PacketPlayInUseEntity.EnumEntityUseAction.values().length);
        return RecyclableSingletonList.create(creator.create());
    }
}

