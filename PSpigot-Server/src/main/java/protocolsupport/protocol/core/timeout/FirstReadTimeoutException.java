package protocolsupport.protocol.core.timeout;

import net.minecraft.server.MinecraftServer;

public class FirstReadTimeoutException
extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final FirstReadTimeoutException notraceinstance = new FirstReadTimeoutException(-1L){
        private static final long serialVersionUID = 1L;

        @Override
        public FirstReadTimeoutException fillInStackTrace() {
            return this;
        }
    };

    public static FirstReadTimeoutException getInstance(long lastReadTime) {
        return MinecraftServer.getServer().isDebugging() ? new FirstReadTimeoutException(lastReadTime) : notraceinstance;
    }

    private FirstReadTimeoutException(long lastReadTime) {
        super(lastReadTime == -1L ? "read timed out" : "read timed out: last message recv time: " + lastReadTime);
    }

}

