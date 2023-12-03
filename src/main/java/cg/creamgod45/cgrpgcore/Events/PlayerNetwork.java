package cg.creamgod45.cgrpgcore.Events;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.RPG.Player.RPGPlayer;
import cg.creamgod45.cgrpgcore.Storage.RPGPlayerMemoryContainer;
import cg.creamgod45.cgrpgcore.Storage.StorageNameField;
import cg.creamgod45.cgrpgcore.Utils.ComponentMerge;
import cg.creamgod45.cgrpgcore.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class PlayerNetwork implements Listener {
    private CGRPGCore plugin;

    public PlayerNetwork(CGRPGCore plugin) {
        this.plugin = plugin;
        new ComponentMerge(plugin.getConfigClass().prefix())
                .add("<color:#3246a8>監聽器註冊.... -> [PlayerJoin]")
                .output().print();
    }

    @EventHandler(ignoreCancelled = true)
    public void PlayerResourcePackStatusEventAsyncData(PlayerResourcePackStatusEvent e) {
        if (e.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
            Player player = e.getPlayer();
            Object o = plugin.getMemoryContainer().get(StorageNameField.RPGPlayers);
            if (o instanceof RPGPlayerMemoryContainer RPMC) {
                if (!RPMC.getRpgplayerMap().containsKey(player.getUniqueId())) {
                    RPMC.addRPGPlayer(player);
                }
                RPMC.initRPGPlayers();
                RPGPlayer rpgPlayer = RPMC.getRpgplayerMap().get(player.getUniqueId());
                player.setHealth(rpgPlayer.getHealth());
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void PlayerQuitAsyncSave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Object o = plugin.getMemoryContainer().get(StorageNameField.RPGPlayers);
        if (o instanceof RPGPlayerMemoryContainer RPMC) {
            RPMC.updateRPGPlayer(player);
        }
    }
}
