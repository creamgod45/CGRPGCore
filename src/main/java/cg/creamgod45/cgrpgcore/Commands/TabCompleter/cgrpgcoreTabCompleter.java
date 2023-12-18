package cg.creamgod45.cgrpgcore.Commands.TabCompleter;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.Utils.ComponentMerge;
import cg.creamgod45.cgrpgcore.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class cgrpgcoreTabCompleter implements TabCompleter {
    private CGRPGCore plugin;

    public cgrpgcoreTabCompleter(CGRPGCore cgrpgCore) {
        plugin = cgrpgCore;
        new ComponentMerge(cgrpgCore.getConfigClass().prefix())
                .add("<color:#4287f5>指令註冊.... -> [cgrpgcore] => TabCompleter")
                .output().print();
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> tab = new ArrayList<>();
        if (commandSender instanceof Player player) {
            if (Utils.HasPermission(player, "cgrpgcore.use")) {
                if (args.length == 1) {
                    tab = new ArrayList<String>();
                    tab.add("!說明: 請選擇你要的服務。");
                    tab.add("help");
                    if (Utils.HasPermission(player, "cgrpgcore.admin")) {
                        tab.add("debug");
                    }
                    return tab;
                }
                if (args.length > 0) {
                    if (args[0].equals("help") && args.length == 2) {
                        tab = new ArrayList<String>();
                        tab.add("!說明:頁數");
                        tab.add("0");
                        tab.add("1");
                        tab.add("2");
                        tab.add("3");
                        return tab;
                    }
                    if (Utils.HasPermission(player, "cgrpgcore.admin")) {
                        if (args[0].equals("debug") && args[1].equals("spawnHalo") && args.length == 3) {
                            tab = new ArrayList<String>();
                            tab.add("!說明:圓直徑大小");
                            return tab;
                        }
                        if (args[0].equals("debug") && args.length == 2) {
                            tab = new ArrayList<String>();
                            tab.add("!說明:服務");
                            tab.add("spawnCylinder");
                            tab.add("spawnShockWave");
                            tab.add("spawnSphere");
                            tab.add("spawnHalo");
                            tab.add("spawnVibration");
                            tab.add("spawnButterflyWingEffect");
                            return tab;
                        }
                    }
                }
            }
        } else {
            if (args.length == 1) {
                tab = new ArrayList<String>();
                tab.add("!說明: 請選擇你要的服務。");
                tab.add("help");
                return tab;
            }
            if (args.length > 0) {
                if (args[0].equals("help") && args.length == 2) {
                    tab = new ArrayList<String>();
                    tab.add("!說明:頁數");
                    tab.add("0");
                    tab.add("1");
                    tab.add("2");
                    tab.add("3");
                    return tab;
                }
            }
        }
        return tab;
    }
}
