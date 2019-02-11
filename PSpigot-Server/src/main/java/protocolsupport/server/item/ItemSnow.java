/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.AxisAlignedBB
 *  net.minecraft.server.Block
 *  net.minecraft.server.Block$StepSound
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.BlockSnow
 *  net.minecraft.server.BlockStateInteger
 *  net.minecraft.server.EntityHuman
 *  net.minecraft.server.EnumDirection
 *  net.minecraft.server.IBlockData
 *  net.minecraft.server.IBlockState
 *  net.minecraft.server.ItemSnow
 *  net.minecraft.server.ItemStack
 *  net.minecraft.server.World
 */
package protocolsupport.server.item;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateInteger;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockState;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;
import protocolsupport.server.block.BlockSnow;

public class ItemSnow
extends net.minecraft.server.ItemSnow {
    private BlockSnow blockSnow;

    public ItemSnow(BlockSnow blockSnow) {
        super((Block)blockSnow);
        this.blockSnow = blockSnow;
    }

    public boolean interactWith(ItemStack itemStack, EntityHuman entityHuman, World world, BlockPosition blockPosition, EnumDirection enumDirection, float n, float n2, float n3) {
        AxisAlignedBB a;
        int intValue;
        IBlockData set;
        if (itemStack.count == 0) {
            return false;
        }
        if (!entityHuman.a(blockPosition, enumDirection, itemStack)) {
            return false;
        }
        IBlockData blockData = world.getType(blockPosition);
        Block block = blockData.getBlock();
        BlockPosition shift = blockPosition;
        if (!(enumDirection == EnumDirection.UP && block == this.a || block.a(world, blockPosition))) {
            shift = blockPosition.shift(enumDirection);
            blockData = world.getType(shift);
            block = blockData.getBlock();
        }
        if (block == this.a && (intValue = ((Integer)blockData.get((IBlockState)net.minecraft.server.BlockSnow.LAYERS)).intValue()) <= 7 && (a = this.blockSnow.getRealBB(world, shift, set = blockData.set((IBlockState)net.minecraft.server.BlockSnow.LAYERS, (Comparable)Integer.valueOf(intValue + 1)))) != null && world.b(a) && world.setTypeAndData(shift, set, 2)) {
            world.makeSound((double)((float)shift.getX() + 0.5f), (double)((float)shift.getY() + 0.5f), (double)((float)shift.getZ() + 0.5f), this.a.stepSound.getPlaceSound(), (this.a.stepSound.getVolume1() + 1.0f) / 2.0f, this.a.stepSound.getVolume2() * 0.8f);
            --itemStack.count;
            return true;
        }
        return super.interactWith(itemStack, entityHuman, world, shift, enumDirection, n, n2, n3);
    }
}

