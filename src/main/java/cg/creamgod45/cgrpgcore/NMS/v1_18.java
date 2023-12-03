package cg.creamgod45.cgrpgcore.NMS;

import cg.creamgod45.cgrpgcore.Utils.BaseNMSDataSync;
import net.minecraft.server.MinecraftServer;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_18 extends BaseNMSDataSync {
    private String version;
    private MinecraftServer nmsServer;

    public v1_18(String version, MinecraftServer nmsServer) {
        this.version = version;
        this.nmsServer = nmsServer;
    }
    public Object getEntityPlayer(Player player){
        CraftPlayer craftPlayer = (CraftPlayer) player;
        return craftPlayer.getHandle();
    }

    public Object toCraftPlayer(Player player){
        return (CraftPlayer) player;
    }
}
