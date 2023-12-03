package cg.creamgod45.cgrpgcore.Storage;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.Utils.ComponentMerge;
import cg.creamgod45.cgrpgcore.Utils.HookingPlugin;
import cg.creamgod45.cgrpgcore.Utils.IPluginRunnableCallback;
import cg.creamgod45.cgrpgcore.Utils.PluginRunnable;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class HookingPluginsMemoryContainer {
    private Map<String, HookingPlugin> plugins = new HashMap<>();

    public void addPluginDepend(String name) {
        if (!plugins.containsKey(name)) {
            HookingPlugin hookingPlugin = new HookingPlugin(name);
            plugins.put(name, hookingPlugin);
            new ComponentMerge(CGRPGCore.config.prefix())
                    .add("<color:#4287f5>橋接插件.... [" + name + "]<green>完成")
                    .output().print();
        }
    }

    public void addPluginDepend(String name, IPluginRunnableCallback callback) {
        if (!plugins.containsKey(name)) {
            new ComponentMerge(CGRPGCore.config.prefix())
                    .add("<color:#4287f5>橋接插件.... [" + name + "]")
                    .output().print();
            HookingPlugin hookingPlugin = new HookingPlugin(name);
            plugins.put(name, hookingPlugin);

            // 創建PluginRunnable
            PluginRunnable pluginRunnable = new PluginRunnable(callback, hookingPlugin);

            // 在Bukkit主線程中執行PluginRunnable
            Bukkit.getScheduler().runTask(CGRPGCore.plugin, pluginRunnable);

            new ComponentMerge(CGRPGCore.config.prefix())
                    .add("<color:#4287f5>橋接插件.... [" + name + "]<green>完成")
                    .output().print();
        }
    }

    @Override
    public String toString() {
        return "HookingPluginsMemoryContainer{" +
                "plugins=" + plugins +
                '}';
    }
}
