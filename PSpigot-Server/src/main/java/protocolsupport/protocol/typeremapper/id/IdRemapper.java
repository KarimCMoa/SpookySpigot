/*
 * Decompiled with CFR 0_132.
 */
package protocolsupport.protocol.typeremapper.id;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.typeremapper.id.RemappingRegistry;
import protocolsupport.protocol.typeremapper.id.RemappingTable;
import protocolsupport.utils.ProtocolVersionsHelper;

public class IdRemapper {
    public static final RemappingRegistry BLOCK = new RemappingRegistry(){
        {
            this.registerRemapEntry(165, 133, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(166, 20, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(167, 96, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(168, 48, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(169, 89, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(176, 63, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(177, 68, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(179, 24, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(180, 128, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(181, 43, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(182, 44, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(183, 107, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(184, 107, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(185, 107, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(186, 107, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(187, 107, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(188, 85, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(189, 85, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(190, 85, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(191, 85, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(192, 85, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(193, 64, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(194, 64, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(195, 64, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(196, 64, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(197, 64, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(178, 151, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(95, 20, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(160, 102, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(161, 18, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(162, 17, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(163, 53, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(164, 53, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(175, 38, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(174, 80, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(159, 82, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(170, 1, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(171, 70, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(172, 82, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(173, 1, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(178, 1, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(154, 1, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(155, 80, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(156, 109, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(156, 44, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(178, 1, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(151, 44, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(146, 54, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(146, 73, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(157, 28, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(153, 87, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(147, 72, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(148, 70, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(149, 93, ProtocolVersionsHelper.BEFORE_1_5);
        }

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(4096);
        }
    };
    public static final RemappingRegistry ITEM = new RemappingRegistry(){
        {
            this.copy(IdRemapper.BLOCK);
            this.registerRemapEntry(427, 324, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(428, 324, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(429, 324, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(430, 324, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(431, 324, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(411, 365, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(412, 366, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(413, 282, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(423, 365, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(424, 366, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(425, 323, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(409, 1, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(410, 1, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(414, 1, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(415, 1, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(416, 1, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(417, 1, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(418, 1, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(419, 1, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(420, 1, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(421, 1, ProtocolVersionsHelper.BEFORE_1_6);
            this.registerRemapEntry(407, 328, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(408, 328, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(404, 356, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(405, 336, ProtocolVersionsHelper.BEFORE_1_5);
            this.registerRemapEntry(406, 288, ProtocolVersionsHelper.BEFORE_1_5);
        }

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(4096);
        }
    };
    public static final RemappingRegistry ENTITY = new RemappingRegistry(){
        {
            this.registerRemapEntry(67, 60, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(68, 94, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(101, 93, ProtocolVersionsHelper.BEFORE_1_8);
            this.registerRemapEntry(100, 92, ProtocolVersionsHelper.BEFORE_1_6);
        }

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(256);
        }
    };
    public static final RemappingRegistry MAPCOLOR = new RemappingRegistry(){
        {
            this.registerRemapEntry(14, 8, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(15, 10, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(16, 5, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(17, 5, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(18, 2, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(19, 1, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(20, 4, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(21, 11, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(22, 11, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(23, 5, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(24, 5, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(25, 5, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(26, 10, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(27, 7, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(28, 4, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(29, 11, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(30, 2, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(31, 5, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(32, 5, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(33, 7, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(34, 10, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(35, 4, ProtocolVersionsHelper.BEFORE_1_7);
            this.registerRemapEntry(36, 10, ProtocolVersionsHelper.BEFORE_1_7);
        }

        @Override
        protected RemappingTable createTable() {
            return new RemappingTable(64){

                @Override
                public int getRemap(int id) {
                    int realColor = (id & 255) >> 2;
                    return (this.table[realColor] << 2) + (id & 3);
                }
            };
        }

    };

}

