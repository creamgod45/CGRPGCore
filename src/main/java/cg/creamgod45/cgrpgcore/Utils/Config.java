package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Config {
    public static boolean ConfigLoad;
    private static CGRPGCore instance;
    private static YamlConfiguration config;

    public Config(CGRPGCore plugin) {
        instance = plugin;
        config = config();
    }

    public static void VersionControl(YamlConfiguration yml) {
        PluginDescriptionFile pdf = instance.getDescription();
        switch (pdf.getVersion()) {
            case "1" -> Updater.v1(yml);
            case "2" -> Updater.v2(yml);
            case "3" -> Updater.v3(yml);
            case "4" -> Updater.v4(yml);
            case "5" -> Updater.v5(yml);
            default -> Utils.ConsoleInfoPrint(" &c未知版本訊息，請重新下載新版本!!");
        }
    }

    public static <T> T getNode(String Path) {
        Object o = config.get(Path);
        if (o != null)
            return (T) o;
        else
            return null;
    }

    public YamlConfiguration config() {
        Resources resources = new Resources(instance);
        resources.load();

        File config = new File(instance.getDataFolder(), "config.yml");
        YamlConfiguration yml = new YamlConfiguration();
        if (!config.exists()) {
            instance.getConfig().options().copyDefaults(true);
            instance.saveDefaultConfig();
            ConfigLoad = true;
        } else {
            try {
                yml.load(config);
            } catch (InvalidConfigurationException | IOException var10) {
                var10.printStackTrace();
            }
            VersionControl(yml);

            try {
                instance.getConfig().load(instance.getDataFolder() + "/config.yml");
                ConfigLoad = true;
            } catch (IOException var8) {
                instance.getLogger().info("表示發生了某種 I/O 異常。此類是由失敗或中斷的 I/O 操作產生的一般異常類。");
                ConfigLoad = false;
            } catch (InvalidConfigurationException var9) {
                instance.getLogger().info("嘗試加載無效配置時拋出異常");
                ConfigLoad = false;
            }
        }

        if (instance.getConfig().getBoolean("Config.Refresh", true)) {
            Iterator<Resource> var11 = resources.getResource().iterator();

            while (var11.hasNext()) {
                Resource resource = var11.next();
                resource.copyParentFile();
            }

            instance.getConfig().options().copyDefaults(true);
            instance.saveDefaultConfig();
            ConfigLoad = true;
        }
        return yml;
    }

    public String prefix() {
        return config.getString("Config.Messages.prefix");
    }
}
