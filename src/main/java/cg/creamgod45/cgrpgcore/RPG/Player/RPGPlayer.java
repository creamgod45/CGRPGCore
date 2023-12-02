package cg.creamgod45.cgrpgcore.RPG.Player;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.RPG.Skill.Skill;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

// 用儲存玩家資訊檔案、或是被系統調用
public class RPGPlayer {
    // 儲存玩家資訊
    private final OfflinePlayer player;
    // 儲存玩家血量
    private double Health;
    // 儲存玩家魔力值
    private double MANA;
    // 儲存玩家耐力值
    private double Stamina;
    // 儲存現在的等級資訊
    private Level Level;
    // 儲存現在的屬性資訊 類型屬性點可以升級特定的玩家屬性(如基本攻擊、基本血量、基本耐力等)
    private Attributes attributes;
    // 儲存玩家的剩餘可用點數(屬性點數、轉值點數、技能點數、貢獻點數)
    private Points points;
    // 玩家穿著基本類 (GUI 管理玩家所裝備的物品)
    private Equipment equipment;
    // 玩家寵物
    private Pet pet;
    // 玩家組隊
    private Team team;
    // 玩家職業類別
    private RPGClass RPGClass;
    // 玩家排名
    private Rank rank;
    // 玩家戰力指數
    private CPI CPI;
    // 玩家擁有的技能
    private Skill[] unlockSkill;

    public RPGPlayer(Player player) {
        this.player = player;
        this.Level = new Level();
        this.RPGClass = new RPGClass();
        load();
    }

    private YamlConfiguration getYamlConfiguration(){
        File file = new File(CGRPGCore.plugin.getDataFolder()+"/"+player.getUniqueId()+".yml");

        YamlConfiguration yml = new YamlConfiguration();
        try {
            yml.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        return yml;
    }

    public void load(){
        YamlConfiguration yml = getYamlConfiguration();
        this.Health = yml.getDouble("Health", 20);
        this.MANA = yml.getDouble("MANA", 20);
        this.Stamina = yml.getDouble("Stamina", 20);
        this.Level.Level = yml.getInt("Level", 20);
        this.Level.lessExp = yml.getInt("lessExp", 0);
        this.Level.maxExp = yml.getInt("maxExp", 20);

    }
    public void save(){}
    public void get(){}
    public void remove(){}
}
