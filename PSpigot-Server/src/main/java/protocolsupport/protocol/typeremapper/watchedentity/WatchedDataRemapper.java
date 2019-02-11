/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  gnu.trove.map.TIntObjectMap
 *  gnu.trove.map.hash.TIntObjectHashMap
 */
package protocolsupport.protocol.typeremapper.watchedentity;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import java.util.List;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.RemappingEntry;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.SpecificType;
import protocolsupport.protocol.typeremapper.watchedentity.remapper.value.ValueRemapper;
import protocolsupport.protocol.typeremapper.watchedentity.types.WatchedEntity;
import protocolsupport.utils.DataWatcherObject;

public class WatchedDataRemapper {
    private static final TIntObjectMap<DataWatcherObject> FAKE_EMPTY_MAP = new TIntObjectHashMap();

    public static TIntObjectMap<DataWatcherObject> transform(WatchedEntity entity, TIntObjectMap<DataWatcherObject> originaldata, ProtocolVersion to) {
        if (entity == null) {
            return FAKE_EMPTY_MAP;
        }
        TIntObjectHashMap transformed = new TIntObjectHashMap();
        SpecificType stype = entity.getType();
        for (RemappingEntry entry : stype.getRemaps(to)) {
            DataWatcherObject object = (DataWatcherObject)originaldata.get(entry.getIdFrom());
            if (object == null) continue;
            transformed.put(entry.getIdTo(), (Object)entry.getValueRemapper().remap(object));
        }
        if (transformed.isEmpty()) {
            return FAKE_EMPTY_MAP;
        }
        return transformed;
    }

    static {
        FAKE_EMPTY_MAP.put(31, new DataWatcherObject(DataWatcherObject.ValueType.BYTE, (byte)0));
    }
}

