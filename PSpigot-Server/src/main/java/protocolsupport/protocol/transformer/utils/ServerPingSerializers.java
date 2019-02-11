/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  com.google.gson.TypeAdapterFactory
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.server.ChatDeserializer
 *  net.minecraft.server.ChatModifier
 *  net.minecraft.server.ChatModifier$ChatModifierSerializer
 *  net.minecraft.server.ChatTypeAdapterFactory
 *  net.minecraft.server.IChatBaseComponent
 *  net.minecraft.server.IChatBaseComponent$ChatSerializer
 *  net.minecraft.server.ServerPing
 *  net.minecraft.server.ServerPing$Serializer
 *  net.minecraft.server.ServerPing$ServerData
 *  net.minecraft.server.ServerPing$ServerPingPlayerSample
 */
package protocolsupport.protocol.transformer.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapterFactory;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;
import net.minecraft.server.ChatDeserializer;
import net.minecraft.server.ChatModifier;
import net.minecraft.server.ChatTypeAdapterFactory;
import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.ServerPing;

public class ServerPingSerializers {
    public static final Gson PING_GSON = new GsonBuilder()
            .registerTypeAdapter(ServerPing.ServerData.class, new ServerDataSerializer())
            .registerTypeAdapter(ServerPing.ServerPingPlayerSample.class, new PlayerSampleSerializer())
            .registerTypeAdapter(ServerPing.class, new ServerPing.Serializer())
            .registerTypeHierarchyAdapter(IChatBaseComponent.class, new IChatBaseComponent.ChatSerializer())
            .registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifier.ChatModifierSerializer())
            .registerTypeAdapterFactory(new ChatTypeAdapterFactory()).create();

    public static class ServerDataSerializer implements JsonDeserializer<ServerPing.ServerData>, JsonSerializer<ServerPing.ServerData> {
        public ServerPing.ServerData deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
            JsonObject l = ChatDeserializer.l(element, "version");
            return new ServerPing.ServerData(ChatDeserializer.h(l, "name"), ChatDeserializer.m(l, "protocol"));
        }

        public JsonElement serialize(ServerPing.ServerData data, Type type, JsonSerializationContext ctx) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", data.a());
            jsonObject.addProperty("protocol", data.b());
            return jsonObject;
        }
    }

    public static class PlayerSampleSerializer
            implements JsonDeserializer<ServerPing.ServerPingPlayerSample>, JsonSerializer<ServerPing.ServerPingPlayerSample>
    {
        public ServerPing.ServerPingPlayerSample deserialize(JsonElement element, Type type, JsonDeserializationContext ctx)
                throws JsonParseException
        {
            JsonObject players = ChatDeserializer.l(element, "players");
            ServerPing.ServerPingPlayerSample serverPingPlayerSample = new ServerPing.ServerPingPlayerSample(ChatDeserializer.m(players, "max"), ChatDeserializer.m(players, "online"));
            if (ChatDeserializer.d(players, "sample"))
            {
                JsonArray sample = ChatDeserializer.t(players, "sample");
                if (sample.size() > 0)
                {
                    GameProfile[] array = new GameProfile[sample.size()];
                    for (int i = 0; i < array.length; i++)
                    {
                        JsonObject j = ChatDeserializer.l(sample.get(i), "player[" + i + "]");
                        String h = ChatDeserializer.h(j, "id");
                        array[i] = new GameProfile(UUID.fromString(h), ChatDeserializer.h(j, "name"));
                    }
                    serverPingPlayerSample.a(array);
                }
            }
            return serverPingPlayerSample;
        }

        public JsonElement serialize(ServerPing.ServerPingPlayerSample data, Type type, JsonSerializationContext ctx)
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("max", Integer.valueOf(data.a()));
            jsonObject.addProperty("online", Integer.valueOf(data.b()));
            if ((data.c() != null) && (data.c().length > 0))
            {
                JsonArray jsonArray = new JsonArray();
                for (int i = 0; i < data.c().length; i++)
                {
                    JsonObject jsonObject2 = new JsonObject();
                    UUID id = data.c()[i].getId();
                    jsonObject2.addProperty("id", id == null ? "" : id.toString());
                    jsonObject2.addProperty("name", data.c()[i].getName());
                    jsonArray.add(jsonObject2);
                }
                jsonObject.add("sample", jsonArray);
            }
            return jsonObject;
        }
    }
}


