/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  net.minecraft.server.EnumProtocol
 *  net.minecraft.server.EnumProtocolDirection
 *  net.minecraft.server.PacketLoginOutDisconnect
 *  net.minecraft.server.PacketLoginOutEncryptionBegin
 *  net.minecraft.server.PacketLoginOutSuccess
 *  net.minecraft.server.PacketPlayOutAbilities
 *  net.minecraft.server.PacketPlayOutAnimation
 *  net.minecraft.server.PacketPlayOutAttachEntity
 *  net.minecraft.server.PacketPlayOutBed
 *  net.minecraft.server.PacketPlayOutBlockAction
 *  net.minecraft.server.PacketPlayOutBlockBreakAnimation
 *  net.minecraft.server.PacketPlayOutBlockChange
 *  net.minecraft.server.PacketPlayOutChat
 *  net.minecraft.server.PacketPlayOutCloseWindow
 *  net.minecraft.server.PacketPlayOutCollect
 *  net.minecraft.server.PacketPlayOutCustomPayload
 *  net.minecraft.server.PacketPlayOutEntity
 *  net.minecraft.server.PacketPlayOutEntity$PacketPlayOutEntityLook
 *  net.minecraft.server.PacketPlayOutEntity$PacketPlayOutRelEntityMove
 *  net.minecraft.server.PacketPlayOutEntity$PacketPlayOutRelEntityMoveLook
 *  net.minecraft.server.PacketPlayOutEntityDestroy
 *  net.minecraft.server.PacketPlayOutEntityEffect
 *  net.minecraft.server.PacketPlayOutEntityEquipment
 *  net.minecraft.server.PacketPlayOutEntityHeadRotation
 *  net.minecraft.server.PacketPlayOutEntityMetadata
 *  net.minecraft.server.PacketPlayOutEntityStatus
 *  net.minecraft.server.PacketPlayOutEntityTeleport
 *  net.minecraft.server.PacketPlayOutEntityVelocity
 *  net.minecraft.server.PacketPlayOutExperience
 *  net.minecraft.server.PacketPlayOutExplosion
 *  net.minecraft.server.PacketPlayOutGameStateChange
 *  net.minecraft.server.PacketPlayOutHeldItemSlot
 *  net.minecraft.server.PacketPlayOutKeepAlive
 *  net.minecraft.server.PacketPlayOutKickDisconnect
 *  net.minecraft.server.PacketPlayOutLogin
 *  net.minecraft.server.PacketPlayOutMap
 *  net.minecraft.server.PacketPlayOutMapChunk
 *  net.minecraft.server.PacketPlayOutMapChunkBulk
 *  net.minecraft.server.PacketPlayOutMultiBlockChange
 *  net.minecraft.server.PacketPlayOutNamedEntitySpawn
 *  net.minecraft.server.PacketPlayOutNamedSoundEffect
 *  net.minecraft.server.PacketPlayOutOpenSignEditor
 *  net.minecraft.server.PacketPlayOutOpenWindow
 *  net.minecraft.server.PacketPlayOutPlayerInfo
 *  net.minecraft.server.PacketPlayOutPosition
 *  net.minecraft.server.PacketPlayOutRemoveEntityEffect
 *  net.minecraft.server.PacketPlayOutResourcePackSend
 *  net.minecraft.server.PacketPlayOutRespawn
 *  net.minecraft.server.PacketPlayOutScoreboardDisplayObjective
 *  net.minecraft.server.PacketPlayOutScoreboardObjective
 *  net.minecraft.server.PacketPlayOutScoreboardScore
 *  net.minecraft.server.PacketPlayOutScoreboardTeam
 *  net.minecraft.server.PacketPlayOutSetSlot
 *  net.minecraft.server.PacketPlayOutSpawnEntity
 *  net.minecraft.server.PacketPlayOutSpawnEntityExperienceOrb
 *  net.minecraft.server.PacketPlayOutSpawnEntityLiving
 *  net.minecraft.server.PacketPlayOutSpawnEntityPainting
 *  net.minecraft.server.PacketPlayOutSpawnEntityWeather
 *  net.minecraft.server.PacketPlayOutSpawnPosition
 *  net.minecraft.server.PacketPlayOutStatistic
 *  net.minecraft.server.PacketPlayOutTabComplete
 *  net.minecraft.server.PacketPlayOutTileEntityData
 *  net.minecraft.server.PacketPlayOutTransaction
 *  net.minecraft.server.PacketPlayOutUpdateAttributes
 *  net.minecraft.server.PacketPlayOutUpdateHealth
 *  net.minecraft.server.PacketPlayOutUpdateSign
 *  net.minecraft.server.PacketPlayOutUpdateTime
 *  net.minecraft.server.PacketPlayOutWindowData
 *  net.minecraft.server.PacketPlayOutWindowItems
 *  net.minecraft.server.PacketPlayOutWorldEvent
 *  net.minecraft.server.PacketPlayOutWorldParticles
 *  net.minecraft.server.PacketStatusOutPong
 *  net.minecraft.server.PacketStatusOutServerInfo
 *  org.spigotmc.SneakyThrow
 */
package protocolsupport.protocol;

import com.google.common.collect.BiMap;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.server.EnumProtocol;
import net.minecraft.server.EnumProtocolDirection;
import net.minecraft.server.PacketLoginOutDisconnect;
import net.minecraft.server.PacketLoginOutEncryptionBegin;
import net.minecraft.server.PacketLoginOutSuccess;
import net.minecraft.server.PacketPlayOutAbilities;
import net.minecraft.server.PacketPlayOutAnimation;
import net.minecraft.server.PacketPlayOutAttachEntity;
import net.minecraft.server.PacketPlayOutBed;
import net.minecraft.server.PacketPlayOutBlockAction;
import net.minecraft.server.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.PacketPlayOutBlockChange;
import net.minecraft.server.PacketPlayOutChat;
import net.minecraft.server.PacketPlayOutCloseWindow;
import net.minecraft.server.PacketPlayOutCollect;
import net.minecraft.server.PacketPlayOutCustomPayload;
import net.minecraft.server.PacketPlayOutEntity;
import net.minecraft.server.PacketPlayOutEntityDestroy;
import net.minecraft.server.PacketPlayOutEntityEffect;
import net.minecraft.server.PacketPlayOutEntityEquipment;
import net.minecraft.server.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.PacketPlayOutEntityMetadata;
import net.minecraft.server.PacketPlayOutEntityStatus;
import net.minecraft.server.PacketPlayOutEntityTeleport;
import net.minecraft.server.PacketPlayOutEntityVelocity;
import net.minecraft.server.PacketPlayOutExperience;
import net.minecraft.server.PacketPlayOutExplosion;
import net.minecraft.server.PacketPlayOutGameStateChange;
import net.minecraft.server.PacketPlayOutHeldItemSlot;
import net.minecraft.server.PacketPlayOutKeepAlive;
import net.minecraft.server.PacketPlayOutKickDisconnect;
import net.minecraft.server.PacketPlayOutLogin;
import net.minecraft.server.PacketPlayOutMap;
import net.minecraft.server.PacketPlayOutMapChunk;
import net.minecraft.server.PacketPlayOutMapChunkBulk;
import net.minecraft.server.PacketPlayOutMultiBlockChange;
import net.minecraft.server.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.PacketPlayOutOpenSignEditor;
import net.minecraft.server.PacketPlayOutOpenWindow;
import net.minecraft.server.PacketPlayOutPlayerInfo;
import net.minecraft.server.PacketPlayOutPosition;
import net.minecraft.server.PacketPlayOutRemoveEntityEffect;
import net.minecraft.server.PacketPlayOutResourcePackSend;
import net.minecraft.server.PacketPlayOutRespawn;
import net.minecraft.server.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.PacketPlayOutScoreboardObjective;
import net.minecraft.server.PacketPlayOutScoreboardScore;
import net.minecraft.server.PacketPlayOutScoreboardTeam;
import net.minecraft.server.PacketPlayOutSetSlot;
import net.minecraft.server.PacketPlayOutSpawnEntity;
import net.minecraft.server.PacketPlayOutSpawnEntityExperienceOrb;
import net.minecraft.server.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.PacketPlayOutSpawnEntityPainting;
import net.minecraft.server.PacketPlayOutSpawnEntityWeather;
import net.minecraft.server.PacketPlayOutSpawnPosition;
import net.minecraft.server.PacketPlayOutStatistic;
import net.minecraft.server.PacketPlayOutTabComplete;
import net.minecraft.server.PacketPlayOutTileEntityData;
import net.minecraft.server.PacketPlayOutTransaction;
import net.minecraft.server.PacketPlayOutUpdateAttributes;
import net.minecraft.server.PacketPlayOutUpdateHealth;
import net.minecraft.server.PacketPlayOutUpdateSign;
import net.minecraft.server.PacketPlayOutUpdateTime;
import net.minecraft.server.PacketPlayOutWindowData;
import net.minecraft.server.PacketPlayOutWindowItems;
import net.minecraft.server.PacketPlayOutWorldEvent;
import net.minecraft.server.PacketPlayOutWorldParticles;
import net.minecraft.server.PacketStatusOutPong;
import net.minecraft.server.PacketStatusOutServerInfo;
import org.spigotmc.SneakyThrow;
import protocolsupport.utils.Utils;

public class ClientBoundPacket {
    public static final int LOGIN_DISCONNECT_ID = ClientBoundPacket.getId(PacketLoginOutDisconnect.class);
    public static final int LOGIN_ENCRYPTION_BEGIN_ID = ClientBoundPacket.getId(PacketLoginOutEncryptionBegin.class);
    public static final int LOGIN_SUCCESS_ID = ClientBoundPacket.getId(PacketLoginOutSuccess.class);
    public static final int STATUS_SERVER_INFO_ID = ClientBoundPacket.getId(PacketStatusOutServerInfo.class);
    public static final int STATUS_PONG_ID = ClientBoundPacket.getId(PacketStatusOutPong.class);
    public static final int PLAY_KEEP_ALIVE_ID = ClientBoundPacket.getId(PacketPlayOutKeepAlive.class);
    public static final int PLAY_LOGIN_ID = ClientBoundPacket.getId(PacketPlayOutLogin.class);
    public static final int PLAY_CHAT_ID = ClientBoundPacket.getId(PacketPlayOutChat.class);
    public static final int PLAY_UPDATE_TIME_ID = ClientBoundPacket.getId(PacketPlayOutUpdateTime.class);
    public static final int PLAY_ENTITY_EQUIPMENT_ID = ClientBoundPacket.getId(PacketPlayOutEntityEquipment.class);
    public static final int PLAY_SPAWN_POSITION_ID = ClientBoundPacket.getId(PacketPlayOutSpawnPosition.class);
    public static final int PLAY_UPDATE_HEALTH_ID = ClientBoundPacket.getId(PacketPlayOutUpdateHealth.class);
    public static final int PLAY_RESPAWN_ID = ClientBoundPacket.getId(PacketPlayOutRespawn.class);
    public static final int PLAY_POSITION_ID = ClientBoundPacket.getId(PacketPlayOutPosition.class);
    public static final int PLAY_HELD_SLOT_ID = ClientBoundPacket.getId(PacketPlayOutHeldItemSlot.class);
    public static final int PLAY_BED_ID = ClientBoundPacket.getId(PacketPlayOutBed.class);
    public static final int PLAY_ANIMATION_ID = ClientBoundPacket.getId(PacketPlayOutAnimation.class);
    public static final int PLAY_SPAWN_NAMED_ID = ClientBoundPacket.getId(PacketPlayOutNamedEntitySpawn.class);
    public static final int PLAY_COLLECT_EFFECT_ID = ClientBoundPacket.getId(PacketPlayOutCollect.class);
    public static final int PLAY_SPAWN_OBJECT_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntity.class);
    public static final int PLAY_SPAWN_LIVING_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityLiving.class);
    public static final int PLAY_SPAWN_PAINTING_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityPainting.class);
    public static final int PLAY_SPAWN_EXP_ORB_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityExperienceOrb.class);
    public static final int PLAY_ENTITY_VELOCITY_ID = ClientBoundPacket.getId(PacketPlayOutEntityVelocity.class);
    public static final int PLAY_ENTITY_DESTROY_ID = ClientBoundPacket.getId(PacketPlayOutEntityDestroy.class);
    public static final int PLAY_ENTITY_ID = ClientBoundPacket.getId(PacketPlayOutEntity.class);
    public static final int PLAY_ENTITY_REL_MOVE_ID = ClientBoundPacket.getId(PacketPlayOutEntity.PacketPlayOutRelEntityMove.class);
    public static final int PLAY_ENTITY_LOOK_ID = ClientBoundPacket.getId(PacketPlayOutEntity.PacketPlayOutEntityLook.class);
    public static final int PLAY_ENTITY_REL_MOVE_LOOK_ID = ClientBoundPacket.getId(PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook.class);
    public static final int PLAY_ENTITY_TELEPORT_ID = ClientBoundPacket.getId(PacketPlayOutEntityTeleport.class);
    public static final int PLAY_ENTITY_HEAD_ROTATION_ID = ClientBoundPacket.getId(PacketPlayOutEntityHeadRotation.class);
    public static final int PLAY_ENTITY_STATUS_ID = ClientBoundPacket.getId(PacketPlayOutEntityStatus.class);
    public static final int PLAY_ENTITY_ATTACH_ID = ClientBoundPacket.getId(PacketPlayOutAttachEntity.class);
    public static final int PLAY_ENTITY_METADATA_ID = ClientBoundPacket.getId(PacketPlayOutEntityMetadata.class);
    public static final int PLAY_ENTITY_EFFECT_ADD_ID = ClientBoundPacket.getId(PacketPlayOutEntityEffect.class);
    public static final int PLAY_ENTITY_EFFECT_REMOVE_ID = ClientBoundPacket.getId(PacketPlayOutRemoveEntityEffect.class);
    public static final int PLAY_EXPERIENCE_ID = ClientBoundPacket.getId(PacketPlayOutExperience.class);
    public static final int PLAY_ENTITY_ATTRIBUTES_ID = ClientBoundPacket.getId(PacketPlayOutUpdateAttributes.class);
    public static final int PLAY_CHUNK_SINGLE_ID = ClientBoundPacket.getId(PacketPlayOutMapChunk.class);
    public static final int PLAY_BLOCK_CHANGE_MULTI_ID = ClientBoundPacket.getId(PacketPlayOutMultiBlockChange.class);
    public static final int PLAY_BLOCK_CHANGE_SINGLE_ID = ClientBoundPacket.getId(PacketPlayOutBlockChange.class);
    public static final int PLAY_BLOCK_ACTION_ID = ClientBoundPacket.getId(PacketPlayOutBlockAction.class);
    public static final int PLAY_BLOCK_BREAK_ANIMATION_ID = ClientBoundPacket.getId(PacketPlayOutBlockBreakAnimation.class);
    public static final int PLAY_CHUNK_MULTI_ID = ClientBoundPacket.getId(PacketPlayOutMapChunkBulk.class);
    public static final int PLAY_EXPLOSION_ID = ClientBoundPacket.getId(PacketPlayOutExplosion.class);
    public static final int PLAY_WORLD_EVENT_ID = ClientBoundPacket.getId(PacketPlayOutWorldEvent.class);
    public static final int PLAY_WORLD_SOUND_ID = ClientBoundPacket.getId(PacketPlayOutNamedSoundEffect.class);
    public static final int PLAY_WORLD_PARTICLES_ID = ClientBoundPacket.getId(PacketPlayOutWorldParticles.class);
    public static final int PLAY_GAME_STATE_CHANGE_ID = ClientBoundPacket.getId(PacketPlayOutGameStateChange.class);
    public static final int PLAY_SPAWN_WEATHER_ID = ClientBoundPacket.getId(PacketPlayOutSpawnEntityWeather.class);
    public static final int PLAY_WINDOW_OPEN_ID = ClientBoundPacket.getId(PacketPlayOutOpenWindow.class);
    public static final int PLAY_WINDOW_CLOSE_ID = ClientBoundPacket.getId(PacketPlayOutCloseWindow.class);
    public static final int PLAY_WINDOW_SET_SLOT_ID = ClientBoundPacket.getId(PacketPlayOutSetSlot.class);
    public static final int PLAY_WINDOW_SET_ITEMS_ID = ClientBoundPacket.getId(PacketPlayOutWindowItems.class);
    public static final int PLAY_WINDOW_DATA_ID = ClientBoundPacket.getId(PacketPlayOutWindowData.class);
    public static final int PLAY_WINDOW_TRANSACTION_ID = ClientBoundPacket.getId(PacketPlayOutTransaction.class);
    public static final int PLAY_UPDATE_SIGN_ID = ClientBoundPacket.getId(PacketPlayOutUpdateSign.class);
    public static final int PLAY_MAP_ID = ClientBoundPacket.getId(PacketPlayOutMap.class);
    public static final int PLAY_UPDATE_TILE_ID = ClientBoundPacket.getId(PacketPlayOutTileEntityData.class);
    public static final int PLAY_SIGN_EDITOR_ID = ClientBoundPacket.getId(PacketPlayOutOpenSignEditor.class);
    public static final int PLAY_STATISTICS = ClientBoundPacket.getId(PacketPlayOutStatistic.class);
    public static final int PLAY_PLAYER_INFO_ID = ClientBoundPacket.getId(PacketPlayOutPlayerInfo.class);
    public static final int PLAY_ABILITIES_ID = ClientBoundPacket.getId(PacketPlayOutAbilities.class);
    public static final int PLAY_TAB_COMPLETE_ID = ClientBoundPacket.getId(PacketPlayOutTabComplete.class);
    public static final int PLAY_SCOREBOARD_OBJECTIVE_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardObjective.class);
    public static final int PLAY_SCOREBOARD_SCORE_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardScore.class);
    public static final int PLAY_SCOREBOARD_DISPLAY_SLOT_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardDisplayObjective.class);
    public static final int PLAY_SCOREBOARD_TEAM_ID = ClientBoundPacket.getId(PacketPlayOutScoreboardTeam.class);
    public static final int PLAY_CUSTOM_PAYLOAD_ID = ClientBoundPacket.getId(PacketPlayOutCustomPayload.class);
    public static final int PLAY_KICK_DISCONNECT_ID = ClientBoundPacket.getId(PacketPlayOutKickDisconnect.class);
    public static final int PLAY_RESOURCE_PACK_ID = ClientBoundPacket.getId(PacketPlayOutResourcePackSend.class);

    public static void init() {
    }

    private static final int getId(Class<?> packetClass) {
        Map protocolMap = null;
        try {
            protocolMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("h")).get(null);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        EnumProtocol protocol = (EnumProtocol)protocolMap.get(packetClass);
        Map idMap = null;
        try {
            idMap = (Map)Utils.setAccessible(EnumProtocol.class.getDeclaredField("j")).get((Object)protocol);
        }
        catch (Throwable t) {
            SneakyThrow.sneaky((Throwable)t);
        }
        return (Integer)((BiMap)idMap.get((Object)EnumProtocolDirection.CLIENTBOUND)).inverse().get(packetClass);
    }
}

