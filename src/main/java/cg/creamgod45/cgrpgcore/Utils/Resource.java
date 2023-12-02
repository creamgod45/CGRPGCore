package cg.creamgod45.cgrpgcore.Utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Resource extends YamlConfiguration {
    private final File file;
    private final Plugin plugin;
    private final String name;

    public Resource(Plugin p, String n) {
        this.plugin = p;
        this.name = n;
        this.file = new File(this.plugin.getDataFolder(), this.name);
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }

        if (!this.file.exists()) {
            this.plugin.saveResource(this.name, true);
        }

    }

    public void copyParentFile() {
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }

        this.plugin.saveResource(this.name, true);
    }

    public void load() {
        try {
            super.load(this.file);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }

    }

    public void save() {
        try {
            super.save(this.file);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public File getFile() {
        return this.file;
    }
}
