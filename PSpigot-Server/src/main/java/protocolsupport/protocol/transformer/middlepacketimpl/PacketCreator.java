/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.util.Recycler
 *  io.netty.util.Recycler$Handle
 *  net.minecraft.server.Packet
 *  net.minecraft.server.PacketDataSerializer
 *  net.minecraft.server.PacketListener
 */
package protocolsupport.protocol.transformer.middlepacketimpl;

import io.netty.buffer.ByteBuf;
import io.netty.util.Recycler;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketListener;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.utils.netty.Allocator;

public class PacketCreator
extends PacketDataSerializer {
    private static final Recycler<PacketCreator> RECYCLER = new Recycler<PacketCreator>(){

        protected PacketCreator newObject(Handle handle) {
            return new PacketCreator(handle);
        }
    };
    private final Recycler.Handle handle;
    private Packet<?> packet;

    public static PacketCreator create(Packet<? extends PacketListener> packet) {
        PacketCreator packetdata = (PacketCreator)((Object)RECYCLER.get());
        packetdata.packet = packet;
        return packetdata;
    }

    private PacketCreator(Recycler.Handle handle) {
        super(Allocator.allocateUnpooledBuffer(), ProtocolVersion.getLatest());
        this.handle = handle;
    }

    public Packet<?> create() throws Exception {
        try {
            this.packet.a((net.minecraft.server.PacketDataSerializer)this);
            Packet<?> packet = this.packet;
            return packet;
        }
        finally {
            this.clear();
            this.packet = null;
            RECYCLER.recycle(this, this.handle);
        }
    }

    protected void finalize() {
        this.release();
    }

}

