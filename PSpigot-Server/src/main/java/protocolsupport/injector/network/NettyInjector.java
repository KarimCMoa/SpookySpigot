package protocolsupport.injector.network;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerConnection;
import protocolsupport.ProtocolSupport;
import protocolsupport.injector.network.BasicInjector;
import protocolsupport.injector.network.NonBlockingServerConnection;
import protocolsupport.utils.Utils;

public class NettyInjector {
    private static final boolean useNonBlockingServerConnection = Utils.getJavaPropertyValue("protocolsupport.nonblockingconection", false, Utils.Converter.STRING_TO_BOOLEAN);

    public static void inject() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        ServerConnection connection = MinecraftServer.getServer().getServerConnection();
        if (MinecraftServer.getServer().ai()) {
            ProtocolSupport.logWarning("Native transport is enabled, this may causes issues. Disable it by setting use-native-transport in server.properties to false.");
        }
        if (connection == null && useNonBlockingServerConnection) {
            NonBlockingServerConnection.inject();
            ProtocolSupport.logInfo("Using NonBlockingServerConnection");
        } else {
            BasicInjector.inject();
            ProtocolSupport.logInfo("Using injected ServerConnection");
        }
    }
}

