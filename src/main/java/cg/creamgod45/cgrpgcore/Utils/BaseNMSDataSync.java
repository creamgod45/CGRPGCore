package cg.creamgod45.cgrpgcore.Utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseNMSDataSync {
    public String version;
    public MinecraftServer nmsServer;

    public BaseNMSDataSync(){
        this.version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        this.nmsServer = null;
        try {
            Class<?> craftServerClass = getBukkitClass("CraftServer");
            // 获取 CraftServer 实例
            Object craftServer = craftServerClass.cast(Bukkit.getServer());

            Utils.ConsoleInfoPrint(craftServer.toString());

            // 获取 getServer 方法
            Method getServerMethod = craftServerClass.getMethod("getServer");

            // 调用 getServer 方法并得到返回值
            Object minecraftServer = getServerMethod.invoke(craftServer);
            this.nmsServer = (MinecraftServer) minecraftServer;
            Utils.ConsoleInfoPrint(this.nmsServer.toString());
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Class<?> getNMSClass(String nmsClassString) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        String name = "net.minecraft.server." + version + "." + nmsClassString;
        return Class.forName(name);
    }

    public Class<?> getBukkitClass(String nmsClassString) throws ClassNotFoundException {
        String name = "org.bukkit.craftbukkit." + this.version+ "." + nmsClassString;
        return Class.forName(name);
    }
}
