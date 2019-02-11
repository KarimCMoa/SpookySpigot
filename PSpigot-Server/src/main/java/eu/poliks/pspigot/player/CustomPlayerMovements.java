package eu.poliks.pspigot.player;

import net.minecraft.server.PacketPlayInFlying;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class CustomPlayerMovements {

	public abstract void handleUpdateLocation(Player player, Location to, Location from, PacketPlayInFlying packet);

	public abstract void handleUpdateRotation(Player player, Location to, Location from, PacketPlayInFlying packet);

}
