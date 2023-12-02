package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import org.bukkit.plugin.Plugin;

public class HookingPlugin {
    private Plugin plugin=null;

    public Plugin getPlugin() {
        return plugin;
    }

    public boolean isEnabled(){
        return plugin.isEnabled();
    }

    public boolean isNaggable(){
        return plugin.isNaggable();
    }

    public HookingPlugin(String pluginName) {
        Plugin p = CGRPGCore.plugin.getServer().getPluginManager().getPlugin(pluginName);
        if(p!=null && p.isEnabled()) {
            plugin = p;
        }
    }
}

