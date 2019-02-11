/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  gnu.trove.iterator.TIntObjectIterator
 *  gnu.trove.map.TIntObjectMap
 *  gnu.trove.map.hash.TIntObjectHashMap
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.ItemStack
 *  net.minecraft.server.Vector3f
 */
package protocolsupport.utils;

import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Vector3f;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.RecyclablePacketDataSerializer;
import protocolsupport.utils.DataWatcherObject;
import protocolsupport.utils.netty.ChannelUtils;

public class DataWatcherSerializer {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static TIntObjectMap<DataWatcherObject> decodeData(ProtocolVersion version, byte[] data) throws IOException {
        TIntObjectHashMap map = new TIntObjectHashMap(10, 0.5f, -1);
        RecyclablePacketDataSerializer serializer = RecyclablePacketDataSerializer.create(version, data);
        try {
            short b12;
            while ((b12 = serializer.readUnsignedByte()) != 127) {
                DataWatcherObject.ValueType type = DataWatcherObject.ValueType.fromId(version, (b12 & 224) >> 5);
                int key = b12 & 31;
                switch (type) {
                    case BYTE: {
                        map.put(key, (Object)new DataWatcherObject(type, serializer.readByte()));
                        break;
                    }
                    case SHORT: {
                        map.put(key, (Object)new DataWatcherObject(type, serializer.readShort()));
                        break;
                    }
                    case INT: {
                        map.put(key, (Object)new DataWatcherObject(type, serializer.readInt()));
                        break;
                    }
                    case FLOAT: {
                        map.put(key, (Object)new DataWatcherObject(type, Float.valueOf(serializer.readFloat())));
                        break;
                    }
                    case STRING: {
                        map.put(key, (Object)new DataWatcherObject(type, serializer.readString(32767)));
                        break;
                    }
                    case ITEMSTACK: {
                        map.put(key, (Object)new DataWatcherObject(type, (Object)serializer.readItemStack()));
                        break;
                    }
                    case VECTOR3I: {
                        int x = serializer.readInt();
                        int y = serializer.readInt();
                        int z = serializer.readInt();
                        map.put(key, (Object)new DataWatcherObject(type, (Object)new BlockPosition(x, y, z)));
                        break;
                    }
                    case VECTOR3F: {
                        float x = serializer.readFloat();
                        float y = serializer.readFloat();
                        float z = serializer.readFloat();
                        map.put(key, (Object)new DataWatcherObject(type, (Object)new Vector3f(x, y, z)));
                        break;
                    }
                }
            }
            TIntObjectHashMap b13 = map;
            return b13;
        }
        finally {
            serializer.release();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] encodeData(ProtocolVersion version, TIntObjectMap<DataWatcherObject> objects)
    {
        RecyclablePacketDataSerializer serializer = RecyclablePacketDataSerializer.create(version);
        try
        {
            TIntObjectIterator<DataWatcherObject> iterator = objects.iterator();
            DataWatcherObject object;
            while (iterator.hasNext())
            {
                iterator.advance();
                object = (DataWatcherObject)iterator.value();
                int tk = (object.type.getId(version) << 5 | iterator.key() & 0x1F) & 0xFF;
                serializer.writeByte(tk);
                switch (object.type)
                {
                    case BYTE:
                        serializer.writeByte(((Byte)object.value).byteValue());
                        break;
                    case SHORT:
                        serializer.writeShort(((Short)object.value).shortValue());
                        break;
                    case INT:
                        serializer.writeInt(((Integer)object.value).intValue());
                        break;
                    case FLOAT:
                        serializer.writeFloat(((Float)object.value).floatValue());
                        break;
                    case STRING:
                        serializer.writeString((String)object.value);
                        break;
                    case ITEMSTACK:
                        serializer.writeItemStack((ItemStack)object.value);
                        break;
                    case VECTOR3I:
                        BlockPosition blockPos = (BlockPosition)object.value;
                        serializer.writeInt(blockPos.getX());
                        serializer.writeInt(blockPos.getY());
                        serializer.writeInt(blockPos.getZ());
                        break;
                    case VECTOR3F:
                        Vector3f vector = (Vector3f)object.value;
                        serializer.writeFloat(vector.getX());
                        serializer.writeFloat(vector.getY());
                        serializer.writeFloat(vector.getZ());
                }
            }
            serializer.writeByte(127);
            return ChannelUtils.toArray(serializer);
        }
        finally
        {
            serializer.release();
        }
    }
}
