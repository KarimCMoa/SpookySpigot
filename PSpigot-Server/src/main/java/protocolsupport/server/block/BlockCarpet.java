/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.AxisAlignedBB
 *  net.minecraft.server.Block
 *  net.minecraft.server.Block$StepSound
 *  net.minecraft.server.BlockCarpet
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.IBlockData
 *  net.minecraft.server.World
 */
package protocolsupport.server.block;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.IBlockData;
import net.minecraft.server.World;

public class BlockCarpet
extends net.minecraft.server.BlockCarpet {
    public BlockCarpet() {
        this.c(0.1f);
        this.a(Block.l);
        this.c("woolCarpet");
        this.e(0);
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }
}

