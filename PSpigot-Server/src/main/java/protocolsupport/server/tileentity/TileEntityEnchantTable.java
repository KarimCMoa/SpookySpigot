
package protocolsupport.server.tileentity;

import net.minecraft.server.BlockPosition;
import net.minecraft.server.Container;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.PlayerInventory;
import net.minecraft.server.World;
import protocolsupport.server.container.ContainerEnchantTable;

public class TileEntityEnchantTable
extends net.minecraft.server.TileEntityEnchantTable {
    public void c() {
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityHuman) {
        return new ContainerEnchantTable(playerinventory, this.world, this.position);
    }
}

