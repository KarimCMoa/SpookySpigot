/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Block
 *  net.minecraft.server.Block$StepSound
 *  net.minecraft.server.BlockAnvil
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.EntityHuman
 *  net.minecraft.server.EnumDirection
 *  net.minecraft.server.IBlockData
 *  net.minecraft.server.ITileEntityContainer
 *  net.minecraft.server.World
 */
package protocolsupport.server.block;

import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.IBlockData;
import net.minecraft.server.ITileEntityContainer;
import net.minecraft.server.World;
import protocolsupport.server.tileentity.TileEntityContainerAnvil;

public class BlockAnvil
extends net.minecraft.server.BlockAnvil {
    public BlockAnvil() {
        this.c(5.0f);
        this.a(Block.p);
        this.b(2000.0f);
        this.c("anvil");
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        entityhuman.openTileEntity((ITileEntityContainer)new TileEntityContainerAnvil(world, blockposition));
        return true;
    }
}

