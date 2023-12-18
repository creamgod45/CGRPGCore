package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Updater {
    public static boolean changed_backupdone = false;

    public static void changed(YamlConfiguration yml) {
        Date now = new Date();

        // 定義時間格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        // 將時間格式化為字符串
        String formattedTime = formatter.format(now);
        try {
            File folder = new File(CGRPGCore.plugin.getDataFolder() + "/backups");
            folder.mkdirs();
            File file = new File(CGRPGCore.plugin.getDataFolder() + "/backups/config.yml.backup_" + formattedTime);
            yml.save(file);
            changed_backupdone = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean a(boolean TrueToBackupConfig, YamlConfiguration yml) {
        if (TrueToBackupConfig && !changed_backupdone) {
            changed(yml);
        }
        return TrueToBackupConfig;
    }

    public static void header(YamlConfiguration yml) {
        // 請加入修改後的備份文件
        Object o = yml.get("Config.Version");
        if (o != null) {
            String version = yml.getString("Config.Version", Config.version);
            if (a(!version.equalsIgnoreCase(CGRPGCore.plugin.getDescription().getVersion()), yml)) {
                List<String> l = new ArrayList<>();
                l.add("=============================================================================");
                l.add(" ▄████▄    ▄████  ██▀███   ██▓███    ▄████  ▄████▄   ▒█████   ██▀███  ▓█████ ");
                l.add("▒██▀ ▀█   ██▒ ▀█▒▓██ ▒ ██▒▓██░  ██▒ ██▒ ▀█▒▒██▀ ▀█  ▒██▒  ██▒▓██ ▒ ██▒▓█   ▀ ");
                l.add("▒▓█    ▄ ▒██░▄▄▄░▓██ ░▄█ ▒▓██░ ██▓▒▒██░▄▄▄░▒▓█    ▄ ▒██░  ██▒▓██ ░▄█ ▒▒███   ");
                l.add("▒▓▓▄ ▄██▒░▓█  ██▓▒██▀▀█▄  ▒██▄█▓▒ ▒░▓█  ██▓▒▓▓▄ ▄██▒▒██   ██░▒██▀▀█▄  ▒▓█  ▄ ");
                l.add("▒ ▓███▀ ░░▒▓███▀▒░██▓ ▒██▒▒██▒ ░  ░░▒▓███▀▒▒ ▓███▀ ░░ ████▓▒░░██▓ ▒██▒░▒████▒");
                l.add("░ ░▒ ▒  ░ ░▒   ▒ ░ ▒▓ ░▒▓░▒▓▒░ ░  ░ ░▒   ▒ ░ ░▒ ▒  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░░░ ▒░ ░");
                l.add("  ░  ▒     ░   ░   ░▒ ░ ▒░░▒ ░       ░   ░   ░  ▒     ░ ▒ ▒░   ░▒ ░ ▒░ ░ ░  ░");
                l.add("░        ░ ░   ░   ░░   ░ ░░       ░ ░   ░ ░        ░ ░ ░ ▒    ░░   ░    ░   ");
                l.add("░ ░            ░    ░                    ░ ░ ░          ░ ░     ░        ░  ░");
                l.add("░                                          ░");
                l.add("Author: CreamGod45 | Version: v" + CGRPGCore.plugin.getDescription().getVersion() + " | ");
                l.add("=============================================================================");
                yml.setComments("Config", l);
            }
        }
        if (a(yml.getComments("Config").isEmpty(), yml)) {
            List<String> l = new ArrayList<>();
            l.add("=============================================================================");
            l.add(" ▄████▄    ▄████  ██▀███   ██▓███    ▄████  ▄████▄   ▒█████   ██▀███  ▓█████ ");
            l.add("▒██▀ ▀█   ██▒ ▀█▒▓██ ▒ ██▒▓██░  ██▒ ██▒ ▀█▒▒██▀ ▀█  ▒██▒  ██▒▓██ ▒ ██▒▓█   ▀ ");
            l.add("▒▓█    ▄ ▒██░▄▄▄░▓██ ░▄█ ▒▓██░ ██▓▒▒██░▄▄▄░▒▓█    ▄ ▒██░  ██▒▓██ ░▄█ ▒▒███   ");
            l.add("▒▓▓▄ ▄██▒░▓█  ██▓▒██▀▀█▄  ▒██▄█▓▒ ▒░▓█  ██▓▒▓▓▄ ▄██▒▒██   ██░▒██▀▀█▄  ▒▓█  ▄ ");
            l.add("▒ ▓███▀ ░░▒▓███▀▒░██▓ ▒██▒▒██▒ ░  ░░▒▓███▀▒▒ ▓███▀ ░░ ████▓▒░░██▓ ▒██▒░▒████▒");
            l.add("░ ░▒ ▒  ░ ░▒   ▒ ░ ▒▓ ░▒▓░▒▓▒░ ░  ░ ░▒   ▒ ░ ░▒ ▒  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░░░ ▒░ ░");
            l.add("  ░  ▒     ░   ░   ░▒ ░ ▒░░▒ ░       ░   ░   ░  ▒     ░ ▒ ▒░   ░▒ ░ ▒░ ░ ░  ░");
            l.add("░        ░ ░   ░   ░░   ░ ░░       ░ ░   ░ ░        ░ ░ ░ ▒    ░░   ░    ░   ");
            l.add("░ ░            ░    ░                    ░ ░ ░          ░ ░     ░        ░  ░");
            l.add("░                                          ░");
            l.add("Author: CreamGod45 | Version: v" + CGRPGCore.plugin.getDescription().getVersion() + " | ");
            l.add("=============================================================================");
            yml.setComments("Config", l);
        }
    }

    public static void setNode(YamlConfiguration yml, String path, Object v, List<String> comment){
        yml.set(path,v);
        List<String> t = new ArrayList<>();
        t.add("新增版本: v"+Config.version);
        t.addAll(comment);
        yml.setComments(path, t);
    }

    private static void save(YamlConfiguration yml) {
        try {
            yml.save(new File(CGRPGCore.plugin.getDataFolder() + "/config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void v1(YamlConfiguration yml) {
        header(yml);
        save(yml);
    }

    public static void v2(YamlConfiguration y) {
        header(y);
        if (a(y.get("Config.test") == null, y)) {
            setNode(y, "Config.test","test", Collections.singletonList("測試用"));
        }
        if (a(y.get("Config.Version") == null, y)) {
            setNode(y, "Config.Version","2", Collections.singletonList("版本號識別用於對舊版本文件設定更新設定節點功能"));
        } else {
            if (a(y.getString("Config.Version").equals("2") == false, y)) {
                setNode(y, "Config.Version","2", Collections.singletonList("版本號識別用於對舊版本文件設定更新設定節點功能"));
            }
        }
        save(y);
    }

    public static void v3(YamlConfiguration yml) {
        header(yml);
    }

    public static void v4(YamlConfiguration yml) {
        header(yml);
    }

    public static void v5(YamlConfiguration yml) {
        header(yml);
    }
}

