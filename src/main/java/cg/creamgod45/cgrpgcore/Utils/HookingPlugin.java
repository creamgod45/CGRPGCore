package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import org.bukkit.plugin.Plugin;

public class HookingPlugin {
    private Plugin plugin = null;

    public HookingPlugin(String pluginName) {
        Plugin p = CGRPGCore.plugin.getServer().getPluginManager().getPlugin(pluginName);
        if (p != null && p.isEnabled()) {
            plugin = p;
        }
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getName() {
        return plugin.getName();
    }

    public boolean isEnabled() {
        return plugin.isEnabled();
    }

    public boolean isNaggable() {
        return plugin.isNaggable();
    }

    @Override
    public String toString() {
        return "HookingPlugin{" +
                "plugin=" + plugin +
                '}';
    }
}

