package protocolsupport.protocol.core.timeout;

import net.minecraft.server.MinecraftServer;

public class IntervalReadTimeoutException
extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final IntervalReadTimeoutException notraceinstance = new IntervalReadTimeoutException(-1L){
        private static final long serialVersionUID = 1L;

        @Override
        public IntervalReadTimeoutException fillInStackTrace() {
            return this;
        }
    };

    public static IntervalReadTimeoutException getInstance(long lastReadTime) {
        return MinecraftServer.getServer().isDebugging() ? new IntervalReadTimeoutException(lastReadTime) : notraceinstance;
    }

    private IntervalReadTimeoutException(long lastReadTime) {
        super(lastReadTime == -1L ? "read timed out" : "read timed out: last message recv time: " + lastReadTime);
    }

}

