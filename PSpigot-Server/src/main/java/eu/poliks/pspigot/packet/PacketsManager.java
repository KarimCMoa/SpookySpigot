package eu.poliks.pspigot.packet;

import net.minecraft.server.Packet;
import net.minecraft.server.PlayerConnection;

public abstract class PacketsManager {

    public abstract void handleReceivedPacket(PlayerConnection connection, Packet packet);

    public abstract void handleSentPacket(PlayerConnection connection, Packet packet);

}
