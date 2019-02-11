package eu.poliks.pspigot.ipwhitelist.util;

import eu.poliks.pspigot.ipwhitelist.IPWhiteList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileConfig {

    public String fileName;
    public File file;
    private FileConfiguration configuration;

    public FileConfig(String fileName) {
        this.fileName = fileName;
        this.file = new File(IPWhiteList.plugin.getDataFolder(), fileName);

        if (!this.file.exists()) {
            this.file.getParentFile().mkdir();

            if (IPWhiteList.plugin.getResource(fileName) == null) {
                try {
                    this.file.createNewFile();
                } catch (IOException e) {
                    Bukkit.getLogger().severe("Failed to create new file " + fileName);
                }
            } else {
                IPWhiteList.plugin.saveResource(fileName, false);
            }
        }

        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfig(File file, String fileName) {
        this.file = new File(file, fileName);

        if (!this.file.exists()) {
            this.file.getParentFile().mkdir();

            if (IPWhiteList.plugin.getResource(fileName) == null) {
                try {
                    this.file.createNewFile();
                } catch (IOException e) {
                    Bukkit.getLogger().severe("Failed to load file " + fileName);
                }
            } else {
                IPWhiteList.plugin.saveResource(fileName, false);
            }
        }

        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return configuration;
    }

    public void save() {
        try {
            this.getConfig().save(this.file);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Could not save config file " + this.file.toString());
            e.printStackTrace();
        }
    }
}
