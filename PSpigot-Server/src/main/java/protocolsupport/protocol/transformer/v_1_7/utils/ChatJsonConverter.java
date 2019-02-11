/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Item
 *  net.minecraft.server.MinecraftKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.MojangsonParseException
 *  net.minecraft.server.MojangsonParser
 *  net.minecraft.server.NBTTagCompound
 *  net.minecraft.server.RegistryMaterials
 */
package protocolsupport.protocol.transformer.v_1_7.utils;

import java.util.List;
import net.minecraft.server.Item;
import net.minecraft.server.MinecraftKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.MojangsonParseException;
import net.minecraft.server.MojangsonParser;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.RegistryMaterials;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.api.chat.modifiers.ClickAction;
import protocolsupport.api.chat.modifiers.HoverAction;

public class ChatJsonConverter {
    public static String convert(String message) {
        BaseComponent component = ChatAPI.fromJSON(message);
        ChatJsonConverter.walkComponent(component);
        return ChatAPI.toJSON(component);
    }

    private static void walkComponent(BaseComponent component) {
        ChatJsonConverter.fixComponent(component);
        for (BaseComponent sibling : component.getSiblings()) {
            ChatJsonConverter.walkComponent(sibling);
        }
    }

    private static void fixComponent(BaseComponent component) {
        ClickAction click;
        block6 : {
            HoverAction hover = component.getHoverAction();
            if (hover != null && hover.getType() == HoverAction.Type.SHOW_ITEM) {
                try {
                    NBTTagCompound compound = MojangsonParser.parse((String)hover.getValue());
                    String id = compound.getString("id");
                    Item item = (Item)Item.REGISTRY.get(new MinecraftKey(id));
                    if (item != null) {
                        compound.setInt("id", Item.getId((Item)item));
                    }
                    component.setHoverAction(new HoverAction(HoverAction.Type.SHOW_ITEM, compound.toString()));
                }
                catch (MojangsonParseException t) {
                    if (!MinecraftServer.getServer().isDebugging()) break block6;
                    t.printStackTrace();
                }
            }
        }
        if ((click = component.getClickAction()) != null && click.getType() == ClickAction.Type.OPEN_URL) {
            String url = click.getValue();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            component.setClickAction(new ClickAction(ClickAction.Type.OPEN_URL, url));
        }
    }
}

