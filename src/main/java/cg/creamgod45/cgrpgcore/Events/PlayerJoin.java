package cg.creamgod45.cgrpgcore.Events;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.Utils.ComponentMerge;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private CGRPGCore plugin;
    public PlayerJoin(CGRPGCore plugin){
        this.plugin=plugin;
        new ComponentMerge(plugin.getConfigClass().prefix())
                .add("<color:#3246a8>監聽器註冊.... -> [PlayerJoin]")
                .output().print();
    }

    @EventHandler
    public void PlayerJoinAsyncData(PlayerJoinEvent e){
        Player player = e.getPlayer();
    }
}
