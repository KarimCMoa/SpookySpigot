package protocolsupport.server.container;

import net.minecraft.server.BlockPosition;
import net.minecraft.server.Container;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ICrafting;
import net.minecraft.server.PlayerInventory;
import net.minecraft.server.World;

public class ContainerAnvil
extends net.minecraft.server.ContainerAnvil {
    public ContainerAnvil(PlayerInventory playerinventory, World world, BlockPosition blockposition, EntityHuman entityhuman) {
        super(playerinventory, world, blockposition, entityhuman);
    }

    public void b() {
        super.b();
        for (ICrafting listener : this.listeners) {
            listener.setContainerData(this, 0, this.a);
        }
    }
}

