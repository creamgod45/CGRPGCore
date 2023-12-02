package cg.creamgod45.cgrpgcore.Utils;

public class PluginRunnable implements Runnable {
    private final PluginRunnableCallback callback;
    private final HookingPlugin plugin;

    public PluginRunnable(PluginRunnableCallback callback, HookingPlugin plugin) {
        this.callback = callback;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // 執行BukkitRunnable的任務
        // 此處你可以添加任何你希望在Bukkit主線程中運行的邏輯

        // 調用回調函數
        callback.onDependencyAdded(plugin);
    }
}
