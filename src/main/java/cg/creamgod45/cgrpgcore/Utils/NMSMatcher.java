package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.NMS.v1_18;
import cg.creamgod45.cgrpgcore.NMS.v1_19_R1;
import cg.creamgod45.cgrpgcore.NMS.v1_19_R2;
import cg.creamgod45.cgrpgcore.NMS.v1_19_R3;

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
                Utils.ConsoleInfoPrint("NOT SELECT provider");
            }
        }
    }

    public v1_18 websocket_1_18() {
        return (v1_18) instance;
    }

    private v1_19_R1 websocket_1_19_1() {
        return (v1_19_R1) instance;
    }

    public v1_19_R2 websocket_1_19_2() {
        return (v1_19_R2) instance;
    }

    public v1_19_R3 websocket_1_19_3() {
        return (v1_19_R3) instance;
    }
}
