package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.NMS.v1_18;
import cg.creamgod45.cgrpgcore.NMS.v1_19_R1;
import cg.creamgod45.cgrpgcore.NMS.v1_19_R2;
import cg.creamgod45.cgrpgcore.NMS.v1_19_R3;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSMatcher extends BaseNMSDataSync {

    public Object instance;

    public NMSMatcher() {
        super();

        switch (this.version) {
            case "v1_18_R1", "v1_18_R2" -> {
                instance = new v1_18(this.version, this.nmsServer);
            }
            case "v1_19_R1" -> {
                instance = new v1_19_R1(this.version, this.nmsServer);
            }
            case "v1_19_R2" -> {
                instance = new v1_19_R2(this.version, this.nmsServer);
            }
            case "v1_19_R3" -> {
                instance = new v1_19_R3(this.version, this.nmsServer);
            }
            default -> {
                Utils.ConsoleInfoPrint("NOT SELECT NMS provider");
            }
        }
    }

    public Object toCraftPlayer(Player player){
        switch (this.version) {
            case "v1_18_R1", "v1_18_R2" -> {
                return v1_18().toCraftPlayer(player);
            }
            case "v1_19_R1" -> {
            }
            case "v1_19_R2" -> {
            }
            case "v1_19_R3" -> {
            }
            default -> {
                Utils.ConsoleInfoPrint("NOT SELECT NMS provider");
            }
        }
        return null;
    }

    public Object getEntityPlayer(Player player){
        switch (this.version) {
            case "v1_18_R1", "v1_18_R2" -> {
                return v1_18().getEntityPlayer(player);
            }
            case "v1_19_R1" -> {
            }
            case "v1_19_R2" -> {
            }
            case "v1_19_R3" -> {
            }
            default -> {
                Utils.ConsoleInfoPrint("NOT SELECT NMS provider");
            }
        }
        return null;
    }

    public v1_18 v1_18() {
        return (v1_18) instance;
    }

    private v1_19_R1 v1_19_R1() {
        return (v1_19_R1) instance;
    }

    public v1_19_R2 v1_19_R2() {
        return (v1_19_R2) instance;
    }

    public v1_19_R3 v1_19_R3() {
        return (v1_19_R3) instance;
    }
}
