package cg.creamgod45.cgrpgcore.Storage;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.Utils.HookingPlugin;
import cg.creamgod45.cgrpgcore.Utils.PluginRunnable;
import cg.creamgod45.cgrpgcore.Utils.PluginRunnableCallback;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class HookingPluginsMemoryContainer {
    private Map<String, HookingPlugin> plugins = new HashMap<>();

    public void addPluginDepend(String name, PluginRunnableCallback callback) {
        if (!plugins.containsKey(name)) {
            HookingPlugin hookingPlugin = new HookingPlugin(name);
            plugins.put(name, hookingPlugin);

            // 創建PluginRunnable
            PluginRunnable pluginRunnable = new PluginRunnable(callback, hookingPlugin);

            // 在Bukkit主線程中執行PluginRunnable
            Bukkit.getScheduler().runTask(CGRPGCore.plugin, pluginRunnable);
        }
    }

}
