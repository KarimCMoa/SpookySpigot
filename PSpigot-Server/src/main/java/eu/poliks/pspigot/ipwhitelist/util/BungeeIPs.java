package eu.poliks.pspigot.ipwhitelist.util;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import com.google.common.reflect.TypeToken;
import java.net.InetSocketAddress;
import java.net.InetAddress;

public class BungeeIPs {
    private CommentedConfigurationNode node;
    private List<String> bungeeips;
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    public BungeeIPs(ConfigurationLoader<CommentedConfigurationNode> loader, List<String> ips) {
        this.bungeeips = ips;
        this.loader = loader;
        this.reload();
    }

    public void reload() {
        try {
            this.node = loader.load();
            if (!bungeeips.isEmpty() && !this.node.getNode("whitelisted-ips").getList(TypeToken.of(String.class)).isEmpty()) {
                this.loader.save(this.node);
            }
        } catch (ObjectMappingException e) {
        } catch (IOException e) {
            // TODO: Log IOException
            this.node = SimpleCommentedConfigurationNode.root();
        }
    }

    public boolean allow(String ip) {
        try {
            return this.bungeeips.contains(ip)
                || this.node.getNode("whitelisted-ips").getList(TypeToken.of(String.class), new ArrayList()).contains(ip);
        } catch (ObjectMappingException e) { return false; }
    }

    public boolean allow(InetSocketAddress addr) {
        return allow(addr.getAddress().getHostAddress());
    }

    public boolean allow(InetAddress addr) {
        return allow(addr.getHostAddress());
    }

    public boolean whitelist(InetSocketAddress ip) {
        return whitelist(ip.getAddress().getHostAddress());
    }

    public boolean whitelist(String ip) {
        List<String> whitelist;
        try {
            whitelist = new ArrayList<>(this.node.getNode("whitelisted-ips").getList(TypeToken.of(String.class)));
        } catch (ObjectMappingException e) { whitelist = new ArrayList<>(); }
        if (whitelist.contains(ip))
            return false;
        whitelist.add(ip);
        this.node.getNode("whitelisted-ips").setValue(whitelist);
        try {
            this.loader.save(this.node);
        } catch (IOException e) {
            // TODO: log
        }
        return true;
    }

    public int unwhitelist(String ip) {
        List<String> whitelist;
        try {
            whitelist = new ArrayList<>(this.node.getNode("whitelisted-ips").getList(TypeToken.of(String.class)));
        } catch (ObjectMappingException e) { whitelist = new ArrayList<>(); }
        boolean removed = whitelist.remove(ip);
        this.node.getNode("whitelisted-ips").setValue(whitelist);
        try {
            this.loader.save(this.node);
        } catch (IOException e) {
            // TODO: log
        }
        if (removed)
            return 0;
        else if (bungeeips.contains(ip))
            return 1;
        else
            return 2;
    }
}
