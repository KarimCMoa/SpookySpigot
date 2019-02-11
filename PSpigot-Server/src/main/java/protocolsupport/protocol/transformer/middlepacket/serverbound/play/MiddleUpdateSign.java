/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.ChatComponentText
 *  net.minecraft.server.IChatBaseComponent
 *  net.minecraft.server.IChatBaseComponent$ChatSerializer
 *  net.minecraft.server.Packet
 */
package protocolsupport.protocol.transformer.middlepacket.serverbound.play;

import net.minecraft.server.BlockPosition;
import net.minecraft.server.ChatComponentText;
import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.Packet;
import protocolsupport.protocol.ServerBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.ServerBoundMiddlePacket;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketCreator;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleUpdateSign
extends ServerBoundMiddlePacket {
    protected BlockPosition position;
    protected String[] lines = new String[4];

    @Override
    public RecyclableCollection<? extends Packet<?>> toNative() throws Exception {
        PacketCreator creator = PacketCreator.create(ServerBoundPacket.PLAY_UPDATE_SIGN.get());
        creator.writePosition(this.position);
        for (int i = 0; i < this.lines.length; ++i) {
            creator.writeString(IChatBaseComponent.ChatSerializer.a((IChatBaseComponent)new ChatComponentText(this.lines[i])));
        }
        return RecyclableSingletonList.create(creator.create());
    }
}

