package org.spigotmc;

import net.minecraft.server.*;

public class AsyncCatcher {
    public static boolean enabled = true;

    public static boolean isAsync() {
        return Thread.currentThread() != MinecraftServer.getServer().primaryThread;
    }

    public static void catchOp(final String reason) {
        if (AsyncCatcher.enabled && MinecraftServer.currentTick > 0 && Thread.currentThread() != MinecraftServer.getServer().primaryThread) {
            throw new IllegalStateException("Asynchronous " + reason + "!");
        }
    }

}