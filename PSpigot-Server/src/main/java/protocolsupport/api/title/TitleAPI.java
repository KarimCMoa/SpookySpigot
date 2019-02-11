package protocolsupport.api.title;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketPlayOutTitle;
import net.minecraft.server.PlayerConnection;
import org.apache.commons.lang3.Validate;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;

public class TitleAPI {
    public static void sendSimpleTitle(Player player, BaseComponent title, BaseComponent subtitle, int fadeIn, int stay, int fadeOut) {
        TitleAPI.sendSimpleTitle(player, ChatAPI.toJSON(title), ChatAPI.toJSON(subtitle), fadeIn, stay, fadeOut);
    }

    public static void sendSimpleTitle(Player player, String titleJson, String subtitleJson, int fadeIn, int stay, int fadeOut) {
        Validate.notNull((Object)player, (String)"Player can't be null", (Object[])new Object[0]);
        if (titleJson == null && subtitleJson == null) {
            throw new IllegalArgumentException("Title and subtitle can't be both null");
        }
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        if (titleJson != null) {
            connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a((String)titleJson)));
        }
        if (subtitleJson != null) {
            connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a((String)subtitleJson)));
        }
        connection.sendPacket((Packet)new PacketPlayOutTitle(fadeIn, stay, fadeOut));
    }

    public static void removeSimpleTitle(Player player) {
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, null));
        connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null));
    }
}

