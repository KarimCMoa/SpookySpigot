/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.BlockAnvil
 *  net.minecraft.server.BlockAnvil$TileEntityContainerAnvil
 *  net.minecraft.server.BlockPosition
 *  net.minecraft.server.Container
 *  net.minecraft.server.EntityHuman
 *  net.minecraft.server.PlayerInventory
 *  net.minecraft.server.World
 */
package protocolsupport.server.tileentity;

import net.minecraft.server.*;
import protocolsupport.server.container.ContainerAnvil;

public class TileEntityContainerAnvil
extends BlockAnvil.TileEntityContainerAnvil {
    private final World world;
    private final BlockPosition position;

    public TileEntityContainerAnvil(World world, BlockPosition position) {
        super(world, position);
        this.world = world;
        this.position = position;
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        return new ContainerAnvil(playerinventory, this.world, this.position, entityhuman);
    }
}

