/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  net.minecraft.server.EnumProtocol
 *  net.minecraft.server.EnumProtocolDirection
 *  net.minecraft.server.Packet
 *  net.minecraft.server.PacketHandshakingInSetProtocol
 *  net.minecraft.server.PacketLoginInEncryptionBegin
 *  net.minecraft.server.PacketLoginInStart
 *  net.minecraft.server.PacketPlayInAbilities
 *  net.minecraft.server.PacketPlayInArmAnimation
 *  net.minecraft.server.PacketPlayInBlockDig
 *  net.minecraft.server.PacketPlayInBlockPlace
 *  net.minecraft.server.PacketPlayInChat
 *  net.minecraft.server.PacketPlayInClientCommand
 *  net.minecraft.server.PacketPlayInCloseWindow
 *  net.minecraft.server.PacketPlayInCustomPayload
 *  net.minecraft.server.PacketPlayInEnchantItem
 *  net.minecraft.server.PacketPlayInEntityAction
 *  net.minecraft.server.PacketPlayInFlying
 *  net.minecraft.server.PacketPlayInFlying$PacketPlayInLook
 *  net.minecraft.server.PacketPlayInFlying$PacketPlayInPosition
 *  net.minecraft.server.PacketPlayInFlying$PacketPlayInPositionLook
 *  net.minecraft.server.PacketPlayInHeldItemSlot
 *  net.minecraft.server.PacketPlayInKeepAlive
 *  net.minecraft.server.PacketPlayInSetCreativeSlot
 *  net.minecraft.server.PacketPlayInSettings
 *  net.minecraft.server.PacketPlayInSteerVehicle
 *  net.minecraft.server.PacketPlayInTabComplete
 *  net.minecraft.server.PacketPlayInTransaction
 *  net.minecraft.server.PacketPlayInUpdateSign
 *  net.minecraft.server.PacketPlayInUseEntity
 *  net.minecraft.server.PacketPlayInWindowClick
 *  net.minecraft.server.PacketStatusInPing
 *  net.minecraft.server.PacketStatusInStart
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.protocol;

import com.google.common.collect.BiMap;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.server.EnumProtocol;
import net.minecraft.server.EnumProtocolDirection;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketHandshakingInSetProtocol;
import net.minecraft.server.PacketLoginInEncryptionBegin;
import net.minecraft.server.PacketLoginInStart;
import net.minecraft.server.PacketPlayInAbilities;
import net.minecraft.server.PacketPlayInArmAnimation;
import net.minecraft.server.PacketPlayInBlockDig;
import net.minecraft.server.PacketPlayInBlockPlace;
import net.minecraft.server.PacketPlayInChat;
import net.minecraft.server.PacketPlayInClientCommand;
import net.minecraft.server.PacketPlayInCloseWindow;
import net.minecraft.server.PacketPlayInCustomPayload;
import net.minecraft.server.PacketPlayInEnchantItem;
import net.minecraft.server.PacketPlayInEntityAction;
import net.minecraft.server.PacketPlayInFlying;
import net.minecraft.server.PacketPlayInHeldItemSlot;
import net.minecraft.server.PacketPlayInKeepAlive;
import net.minecraft.server.PacketPlayInSetCreativeSlot;
import net.minecraft.server.PacketPlayInSettings;
import net.minecraft.server.PacketPlayInSteerVehicle;
import net.minecraft.server.PacketPlayInTabComplete;
import net.minecraft.server.PacketPlayInTransaction;
import net.minecraft.server.PacketPlayInUpdateSign;
import net.minecraft.server.PacketPlayInUseEntity;
import net.minecraft.server.PacketPlayInWindowClick;
import net.minecraft.server.PacketStatusInPing;
import net.minecraft.server.PacketStatusInStart;
import org.spigotmc.SneakyThrow;
import protocolsupport.utils.Utils;

public enum ServerBoundPacket {
    HANDSHAKE_START(PacketHandshakingInSetProtocol.class),
    STATUS_REQUEST(PacketStatusInStart.class),
    STATUS_PING(PacketStatusInPing.class),
    LOGIN_START(PacketLoginInStart.class),
    LOGIN_ENCRYPTION_BEGIN(PacketLoginInEncryptionBegin.class),
    PLAY_KEEP_ALIVE(PacketPlayInKeepAlive.class),
    PLAY_CHAT(PacketPlayInChat.class),
    PLAY_USE_ENTITY(PacketPlayInUseEntity.class),
    PLAY_PLAYER(PacketPlayInFlying.class),
    PLAY_POSITION(PacketPlayInFlying.PacketPlayInPosition.class),
    PLAY_LOOK(PacketPlayInFlying.PacketPlayInLook.class),
    PLAY_POSITION_LOOK(PacketPlayInFlying.PacketPlayInPositionLook.class),
    PLAY_BLOCK_DIG(PacketPlayInBlockDig.class),
    PLAY_BLOCK_PLACE(PacketPlayInBlockPlace.class),
    PLAY_HELD_SLOT(PacketPlayInHeldItemSlot.class),
    PLAY_ANIMATION(PacketPlayInArmAnimation.class),
    PLAY_ENTITY_ACTION(PacketPlayInEntityAction.class),
    PLAY_STEER_VEHICLE(PacketPlayInSteerVehicle.class),
    PLAY_WINDOW_CLOSE(PacketPlayInCloseWindow.class),
    PLAY_WINDOW_CLICK(PacketPlayInWindowClick.class),
    PLAY_WINDOW_TRANSACTION(PacketPlayInTransaction.class),
    PLAY_CREATIVE_SET_SLOT(PacketPlayInSetCreativeSlot.class),
    PLAY_ENCHANT_SELECT(PacketPlayInEnchantItem.class),
    PLAY_UPDATE_SIGN(PacketPlayInUpdateSign.class),
    PLAY_ABILITIES(PacketPlayInAbilities.class),
    PLAY_TAB_COMPLETE(PacketPlayInTabComplete.class),
    PLAY_SETTINGS(PacketPlayInSettings.class),
    PLAY_CLIENT_COMMAND(PacketPlayInClientCommand.class),
    PLAY_CUSTOM_PAYLOAD(PacketPlayInCustomPayload.class);
    
    private final int id;
    private final EnumProtocol protocol;

    public static void init() {
    }

    private ServerBoundPacket(Class<? extends Packet<?>> packetClass) {
        Map protocolMap = null;
        try {
            protocolMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("h")).get(null);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        this.protocol = (EnumProtocol)protocolMap.get(packetClass);
        Map idMap = null;
        try {
            idMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("j")).get((Object)this.protocol);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        this.id = (Integer)((BiMap)idMap.get((Object)EnumProtocolDirection.SERVERBOUND)).inverse().get(packetClass);
    }

    public Packet<?> get() throws IllegalAccessException, InstantiationException {
        return this.protocol.a(EnumProtocolDirection.SERVERBOUND, this.id);
    }

    public int getId() {
        return this.id;
    }
}

