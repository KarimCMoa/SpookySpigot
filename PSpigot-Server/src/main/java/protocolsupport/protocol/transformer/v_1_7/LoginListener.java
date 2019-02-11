/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.NetworkManager
 */
package protocolsupport.protocol.transformer.v_1_7;

import javax.crypto.SecretKey;
import net.minecraft.server.NetworkManager;
import protocolsupport.protocol.transformer.handlers.AbstractLoginListener;

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
        this.networkManager.a(this.loginKey);
    }
}

