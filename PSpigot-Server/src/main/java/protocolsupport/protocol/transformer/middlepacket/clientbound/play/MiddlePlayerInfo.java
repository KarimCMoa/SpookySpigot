/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.properties.Property
 *  net.minecraft.server.IChatBaseComponent
 *  net.minecraft.server.IChatBaseComponent$ChatSerializer
 */
package protocolsupport.protocol.transformer.middlepacket.clientbound.play;

import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.server.IChatBaseComponent;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.storage.LocalStorage;
import protocolsupport.protocol.storage.LocalStorage.PlayerListEntry;
import protocolsupport.protocol.transformer.middlepacket.ClientBoundMiddlePacket;
import protocolsupport.protocol.transformer.utils.LegacyUtils;

public abstract class MiddlePlayerInfo<T>
extends ClientBoundMiddlePacket<T> {
    protected Action action;
    protected Info[] infos;

    @Override
    public void readFromServerData(PacketDataSerializer serializer) {
        this.action = Action.values()[serializer.readVarInt()];
        this.infos = new Info[serializer.readVarInt()];
        for (int i = 0; i < this.infos.length; ++i) {
            Info info = new Info();
            info.uuid = serializer.readUUID();
            switch (this.action) {
                case ADD: {
                    info.username = serializer.readString(16);
                    info.properties = new Property[serializer.readVarInt()];
                    for (int j = 0; j < info.properties.length; ++j) {
                        String name = serializer.readString(32767);
                        String value = serializer.readString(32767);
                        String signature = null;
                        if (serializer.readBoolean()) {
                            signature = serializer.readString(32767);
                        }
                        info.properties[j] = new Property(name, value, signature);
                    }
                    info.gamemode = serializer.readVarInt();
                    info.ping = serializer.readVarInt();
                    if (!serializer.readBoolean()) break;
                    info.displayNameJson = serializer.readString(32767);
                    break;
                }
                case GAMEMODE: {
                    info.gamemode = serializer.readVarInt();
                    break;
                }
                case PING: {
                    info.ping = serializer.readVarInt();
                    break;
                }
                case DISPLAY_NAME: {
                    if (!serializer.readBoolean()) break;
                    info.displayNameJson = serializer.readString(32767);
                    break;
                }
            }
            this.infos[i] = info;
        }
    }

    @Override
    public void handle()
    {
        for (Info info : this.infos)
        {
            info.previousinfo = this.storage.getPlayerListEntry(info.uuid);
            if (info.previousinfo != null) {
                info.previousinfo = info.previousinfo.clone();
            }
            switch (this.action)
            {
                case ADD:
                    LocalStorage.PlayerListEntry entry = new LocalStorage.PlayerListEntry(info.username);
                    entry.setDisplayNameJson(info.displayNameJson);
                    for (Property property : info.properties) {
                        entry.getProperties().add(property);
                    }
                    this.storage.addPlayerListEntry(info.uuid, entry);
                    break;
                case DISPLAY_NAME:
                    entry = this.storage.getPlayerListEntry(info.uuid);
                    if (entry != null) {
                        entry.setDisplayNameJson(info.displayNameJson);
                    }
                    break;
                case REMOVE:
                    this.storage.removePlayerListEntry(info.uuid);
            }
        }
    }

    protected static class Info {
        public UUID uuid;
        public PlayerListEntry previousinfo;
        public String username;
        public int ping;
        public int gamemode;
        public String displayNameJson;
        public Property[] properties;

        protected Info() {
        }

        public String getName() {
            return this.displayNameJson == null ? this.username : LegacyUtils.toText(IChatBaseComponent.ChatSerializer.a((String)this.displayNameJson));
        }
    }

    protected static enum Action {
        ADD,
        GAMEMODE,
        PING,
        DISPLAY_NAME,
        REMOVE;
        

        private Action() {
        }
    }

}

