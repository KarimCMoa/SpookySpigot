/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.LoginListener
 *  net.minecraft.server.NetworkManager
 */
package protocolsupport.protocol.transformer.v_1_8;

import net.minecraft.server.NetworkManager;
import protocolsupport.protocol.transformer.handlers.AbstractHandshakeListener;
import protocolsupport.protocol.transformer.v_1_8.LoginListener;

public class HandshakeListener
extends AbstractHandshakeListener {
    public HandshakeListener(NetworkManager networkmanager) {
        super(networkmanager);
    }

    @Override
    public LoginListener getLoginListener(NetworkManager networkManager) {
        return new LoginListener(networkManager);
    }
}

