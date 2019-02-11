package eu.poliks.pspigot.ipwhitelist;

import com.google.common.collect.Lists;
import eu.poliks.pspigot.ipwhitelist.util.BungeeIPs;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class IpHandler {

    public IpHandler() {
        load();
    }

    private BungeeIPs bungeeips;

    private void load() {
        IPWhiteList.plugin.saveResource("config.yml", false);
        ConfigurationLoader loader = YAMLConfigurationLoader.builder().setFile(new File(IPWhiteList.plugin.getDataFolder(), "config.yml")).build();
        this.bungeeips = new BungeeIPs(loader, getBukkitConfig());
    }

    public void save() {
        bungeeips = null;
    }

    private List<String> getBukkitConfig() {
        File spigotyml = new File(IPWhiteList.plugin.getDataFolder().getParentFile().getParentFile(), "spigot.yml");
        File bukkityml = new File(IPWhiteList.plugin.getDataFolder().getParentFile().getParentFile(), "bukkit.yml");
        if (spigotyml.exists()) {
            Configuration spigotcfg = YamlConfiguration.loadConfiguration(spigotyml);
            if (spigotcfg.getBoolean("settings.bungeecord")) {
                return spigotcfg.getStringList("settings.bungeecord-addresses");
            }
        } else if (bukkityml.exists()) {
            Configuration bukkitcfg = YamlConfiguration.loadConfiguration(new File(IPWhiteList.plugin.getDataFolder().getParentFile().getParentFile(), "bukkit.yml"));
            return bukkitcfg.getStringList("settings.bungee-proxies");
        }
        return Lists.newArrayList();
    }

    public BungeeIPs getBungeeips() {
        return bungeeips;
    }
}
