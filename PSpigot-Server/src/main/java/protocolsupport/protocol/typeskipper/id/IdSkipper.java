/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Enchantment
 *  net.minecraft.server.MobEffectList
 */
package protocolsupport.protocol.typeskipper.id;

import net.minecraft.server.Enchantment;
import net.minecraft.server.MobEffectList;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeskipper.id.SkippingRegistry;
import protocolsupport.protocol.typeskipper.id.SkippingTable;
import protocolsupport.utils.ProtocolVersionsHelper;

public class IdSkipper {
    public static final SkippingRegistry ENCHANT = new SkippingRegistry(){
        {
            this.registerSkipEntry(Enchantment.DEPTH_STRIDER.id, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerSkipEntry(Enchantment.LURE.id, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerSkipEntry(Enchantment.LUCK.id, ProtocolVersionsHelper.BEFORE_1_7);
        }

        @Override
        protected SkippingTable createTable() {
            return new SkippingTable(256);
        }
    };
    public static final SkippingRegistry EFFECT = new SkippingRegistry(){
        {
            this.registerSkipEntry(MobEffectList.HEALTH_BOOST.id, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerSkipEntry(MobEffectList.ABSORBTION.id, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerSkipEntry(MobEffectList.SATURATION.id, ProtocolVersionsHelper.BEFORE_1_6);
        }

        @Override
        protected SkippingTable createTable() {
            return new SkippingTable(32);
        }
    };
    public static final SkippingRegistry INVENTORY = new SkippingRegistry(){
        {
            this.registerSkipEntry(11, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerSkipEntry(9, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerSkipEntry(10, ProtocolVersionsHelper.BEFORE_1_5);
        }

        @Override
        protected SkippingTable createTable() {
            return new SkippingTable(16);
        }
    };

}

