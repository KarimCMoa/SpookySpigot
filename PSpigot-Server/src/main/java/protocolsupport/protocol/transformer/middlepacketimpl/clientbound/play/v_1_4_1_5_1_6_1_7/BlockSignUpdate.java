/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.IChatBaseComponent
 *  net.minecraft.server.IChatBaseComponent$ChatSerializer
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.IChatBaseComponent;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlockSignUpdate;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.transformer.utils.LegacyUtils;
import protocolsupport.utils.Utils;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockSignUpdate
extends MiddleBlockSignUpdate<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_UPDATE_SIGN_ID, version);
        serializer.writeInt(this.position.getX());
        serializer.writeShort(this.position.getY());
        serializer.writeInt(this.position.getZ());
        for (String lineJson : this.linesJson) {
            serializer.writeString(Utils.clampString(LegacyUtils.toText(IChatBaseComponent.ChatSerializer.a((String)lineJson)), 15));
        }
        return RecyclableSingletonList.create(serializer);
    }
}

