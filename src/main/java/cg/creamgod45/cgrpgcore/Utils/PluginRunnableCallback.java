package cg.creamgod45.cgrpgcore.Utils;

import org.bukkit.scheduler.BukkitRunnable;

@FunctionalInterface
public interface PluginRunnableCallback {
    void onDependencyAdded(HookingPlugin plugin);

    // 你可以添加其他方法，根據需要定義接口
}
