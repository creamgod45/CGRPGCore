package cg.creamgod45.cgrpgcore.NMS;

import cg.creamgod45.cgrpgcore.Utils.BaseNMSDataSync;
import net.minecraft.server.MinecraftServer;

public class v1_18 extends BaseNMSDataSync {
    private String version;
    private MinecraftServer nmsServer;
    public v1_18(String version, MinecraftServer nmsServer) {
        this.version=version;
        this.nmsServer=nmsServer;
    }
}
