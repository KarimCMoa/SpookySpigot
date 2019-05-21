package eu.poliks.pspigot.player;

import net.minecraft.server.PacketPlayInFlying;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface MovementHandler {
    void handleUpdateLocation(Player player, Location to, Location from, PacketPlayInFlying packet);
    void handleUpdateRotation(Player player, Location to, Location from, PacketPlayInFlying packet);
}
