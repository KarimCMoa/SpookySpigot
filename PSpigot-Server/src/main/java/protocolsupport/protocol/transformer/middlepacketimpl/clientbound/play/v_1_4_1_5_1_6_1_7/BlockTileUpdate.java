/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.NBTTagCompound
 */
package protocolsupport.protocol.transformer.middlepacketimpl.clientbound.play.v_1_4_1_5_1_6_1_7;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.NBTTagCompound;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleBlockTileUpdate;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;
import protocolsupport.protocol.typeremapper.nbt.tileupdate.TileNBTTransformer;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class BlockTileUpdate
extends MiddleBlockTileUpdate<RecyclableCollection<PacketData>> {
    @Override
    public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
        PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_UPDATE_TILE_ID, version);
        serializer.writeInt(this.position.getX());
        serializer.writeShort(this.position.getY());
        serializer.writeInt(this.position.getZ());
        serializer.writeByte(this.type);
        serializer.writeTag(TileNBTTransformer.transform(this.type, version, this.tag));
        return RecyclableSingletonList.create(serializer);
    }
}

