package cg.creamgod45.cgrpgcore.Utils;

@FunctionalInterface
public interface IPluginRunnableCallback {
    void onDependencyAdded(HookingPlugin hookingPlugin);

    // 你可以添加其他方法，根據需要定義接口
}

