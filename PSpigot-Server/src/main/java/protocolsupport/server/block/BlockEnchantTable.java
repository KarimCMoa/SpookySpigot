/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.Block
 *  net.minecraft.server.BlockEnchantmentTable
 *  net.minecraft.server.TileEntity
 *  net.minecraft.server.World
 */
package protocolsupport.server.block;

import net.minecraft.server.Block;
import net.minecraft.server.BlockEnchantmentTable;
import net.minecraft.server.TileEntity;
import net.minecraft.server.World;
import protocolsupport.server.tileentity.TileEntityEnchantTable;

public class BlockEnchantTable
extends BlockEnchantmentTable {
    public BlockEnchantTable() {
        this.c(5.0f);
        this.b(2000.0f);
        this.c("enchantmentTable");
    }

    public TileEntity a(World world, int n) {
        return new TileEntityEnchantTable();
    }
}

