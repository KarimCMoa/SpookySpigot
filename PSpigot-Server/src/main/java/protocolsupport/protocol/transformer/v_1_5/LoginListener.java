/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  net.minecraft.server.MinecraftEncryption
 *  net.minecraft.server.NetworkManager
 */
package protocolsupport.protocol.transformer.v_1_5;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import net.minecraft.server.MinecraftEncryption;
import net.minecraft.server.NetworkManager;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;
import protocolsupport.protocol.transformer.v_1_5.PacketDecrypter;

public class LoginListener
extends AbstractLoginListener {
    public LoginListener(NetworkManager networkmanager) {
        super(networkmanager);
    }

    @Override
    protected boolean hasCompression() {
        return false;
    }

    @Override
    protected void enableEncryption(SecretKey key) {
        this.networkManager.channel.pipeline().addBefore("splitter", "decrypt", (ChannelHandler)new PacketDecrypter(MinecraftEncryption.a((int)2, (Key)this.loginKey)));
    }
}

