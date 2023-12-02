package cg.creamgod45.cgrpgcore;

import cg.creamgod45.cgrpgcore.Commands.CommandExecutor.cgrpgcoreCommandExecutor;
import cg.creamgod45.cgrpgcore.Commands.TabCompleter.cgrpgcoreTabCompleter;
import cg.creamgod45.cgrpgcore.Events.PlayerJoin;
import cg.creamgod45.cgrpgcore.Storage.HookingPluginsMemoryContainer;
import cg.creamgod45.cgrpgcore.Storage.MemoryContainer;
import cg.creamgod45.cgrpgcore.Storage.RPGClassMemoryContainer;
import cg.creamgod45.cgrpgcore.Storage.StorageNameField;
import cg.creamgod45.cgrpgcore.Utils.*;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;

public final class CGRPGCore extends JavaPlugin {
    public static BukkitAudiences adventure;
    public static CGRPGCore plugin;
    public static Config config;
    public static MemoryContainer<Object> memory;
    @Override
    public void onEnable() {
        // 記錄開始時間
        long startTime = System.currentTimeMillis();
        plugin = this;
        adventure = BukkitAudiences.create(this);
        config = new Config(this);
        Utils.MiniMessage("");
        Utils.MiniMessage("");
        Utils.MiniMessage("<color:#fcba03> ▄████▄    ▄████  ██▀███   ██▓███    ▄████  ▄████▄   ▒█████   ██▀███  ▓█████ ");
        Utils.MiniMessage("<color:#fcba03>▒██▀ ▀█   ██▒ ▀█▒▓██ ▒ ██▒▓██░  ██▒ ██▒ ▀█▒▒██▀ ▀█  ▒██▒  ██▒▓██ ▒ ██▒▓█   ▀ ");
        Utils.MiniMessage("<color:#fcba03>▒▓█    ▄ ▒██░▄▄▄░▓██ ░▄█ ▒▓██░ ██▓▒▒██░▄▄▄░▒▓█    ▄ ▒██░  ██▒▓██ ░▄█ ▒▒███   ");
        Utils.MiniMessage("<color:#fcba03>▒▓▓▄ ▄██▒░▓█  ██▓▒██▀▀█▄  ▒██▄█▓▒ ▒░▓█  ██▓▒▓▓▄ ▄██▒▒██   ██░▒██▀▀█▄  ▒▓█  ▄ ");
        Utils.MiniMessage("<color:#fcba03>▒ ▓███▀ ░░▒▓███▀▒░██▓ ▒██▒▒██▒ ░  ░░▒▓███▀▒▒ ▓███▀ ░░ ████▓▒░░██▓ ▒██▒░▒████▒");
        Utils.MiniMessage("<color:#fcba03>░ ░▒ ▒  ░ ░▒   ▒ ░ ▒▓ ░▒▓░▒▓▒░ ░  ░ ░▒   ▒ ░ ░▒ ▒  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░░░ ▒░ ░");
        Utils.MiniMessage("<color:#fcba03>  ░  ▒     ░   ░   ░▒ ░ ▒░░▒ ░       ░   ░   ░  ▒     ░ ▒ ▒░   ░▒ ░ ▒░ ░ ░  ░");
        Utils.MiniMessage("<color:#fcba03>░        ░ ░   ░   ░░   ░ ░░       ░ ░   ░ ░        ░ ░ ░ ▒    ░░   ░    ░   ");
        Utils.MiniMessage("<color:#fcba03>░ ░            ░    ░                    ░ ░ ░          ░ ░     ░        ░  ░");
        Utils.MiniMessage("<color:#fcba03>░                                          ░");
        Utils.MiniMessage("");
        Utils.MiniMessage("");
        new ComponentMerge(config.prefix())
                .add("<yellow>啟動....")
                .output().print();
        ReloadFunction();
        Utils.MiniMessage(memory.toString());
        new ComponentMerge(config.prefix())
                .add("<yellow>啟動....<green>完成")
                .output().print();
        // 記錄結束時間
        long endTime = System.currentTimeMillis();
        // 計算運算時間
        long elapsedTime = endTime - startTime;
        new ComponentMerge(config.prefix()).add("<yellow>運算時間: "+elapsedTime+" ms").output().print();

        // 獲取操作系統MXBean
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        // 獲取CPU使用量
        double cpuUsage = osBean.getSystemLoadAverage();
        new ComponentMerge(config.prefix()).add("<yellow>CPU 使用量: "+cpuUsage).output().print();

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        new ComponentMerge(config.prefix())
                .add("<yellow>記憶體使用量: "+ ByteUnitConverter.convert("b","mb",usedMemory)+" MB")
                .output().print();
    }

    @Override
    public void onDisable() {
        new ComponentMerge(config.prefix())
                .add("<color:#e61549>關閉....")
                .output().print();
        new ComponentMerge(config.prefix())
                .add("<color:#e61549>註銷監聽器....<green>完成")
                .output().print();
        HandlerList.unregisterAll(this);
        new ComponentMerge(config.prefix())
                .add("<color:#e61549>註銷排成....<green>完成")
                .output().print();
        this.getServer().getScheduler().cancelTasks(this);
        new ComponentMerge(config.prefix())
                .add("<color:#e61549>註銷記憶體....<green>完成")
                .output().print();
        new ComponentMerge(config.prefix())
                .add("<color:#e61549>關閉....<green>完成")
                .output().print();
        if(adventure != null) {
            adventure.close();
            adventure = null;
        }
        this.getServer().getPluginManager().disablePlugin(this);
    }

    private void ReloadFunction(){
        memory = new MemoryContainer<Object>(this);
        new ComponentMerge(config.prefix())
                .add("<color:#e3cb19>重新加載....")
                .output().print();
        MemoryContainerInit();
        CommandsRegister();
        EventsRegister();
        HookingPlugins();
        new ComponentMerge(config.prefix())
                .add("<color:#e3cb19>重新加載....<green>完成")
                .output().print();
    }

    private void MemoryContainerInit(){
        memory.set(StorageNameField.RPGClasss, new RPGClassMemoryContainer(this));
        memory.set(StorageNameField.HookingPlugins, new HookingPluginsMemoryContainer());
        Object o = memory.get(StorageNameField.RPGClasss);
        if(o instanceof RPGClassMemoryContainer){
            Utils.ConsoleInfoPrint(o.toString());
        }
    }

    private void HookingPlugins(){
        new ComponentMerge(config.prefix())
                .add("<color:#4287f5>插件....")
                .output().print();
        Object Ohpmc = memory.get(StorageNameField.HookingPlugins);
        if (Ohpmc instanceof HookingPluginsMemoryContainer hpmc) {
            hpmc.addPluginDepend("PlaceholderAPI", (HookingPlugin hookingPlugin)->{
                Plugin plugin1 = hookingPlugin.getPlugin();
                Utils.ConsoleInfoPrint(String.valueOf(plugin1));
            });
        }
        new ComponentMerge(config.prefix())
                .add("<color:#4287f5>插件....<green>完成")
                .output().print();
    }

    private void CommandsRegister(){
        new ComponentMerge(config.prefix())
                .add("<color:#4287f5>指令註冊....")
                .output().print();
        this.getCommand("cgrpgcore").setExecutor(new cgrpgcoreCommandExecutor(this));
        this.getCommand("cgrpgcore").setTabCompleter(new cgrpgcoreTabCompleter(this));
        new ComponentMerge(config.prefix())
                .add("<color:#4287f5>指令註冊....<green>完成")
                .output().print();
    }
    private void EventsRegister(){
        new ComponentMerge(config.prefix())
                .add("<color:#3246a8>監聽器註冊....")
                .output().print();
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        new ComponentMerge(config.prefix())
                .add("<color:#3246a8>監聽器註冊....<green>完成")
                .output().print();
    }

    public static BukkitAudiences adventure() {
        if (adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        } else {
            return adventure;
        }
    }


    public Config getConfigClass() {
        return config;
    }

    public MemoryContainer<Object> getMemoryContainer() {
        return memory;
    }
}
