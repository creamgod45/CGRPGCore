package cg.creamgod45.cgrpgcore.RPG.Player;

import cg.creamgod45.cgrpgcore.RPG.Skill.Skill;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

// 用儲存玩家資訊檔案、或是被系統調用
public class RPGPlayer {
    // 儲存玩家資訊
    private Player player;
    // 儲存玩家血量
    private double Health;
    private double MaxHealth;
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

    public RPGPlayer(double health, double maxHealth, double MANA, double stamina, cg.creamgod45.cgrpgcore.RPG.Player.Level level, Attributes attributes, Points points, Equipment equipment, Pet pet, Team team, cg.creamgod45.cgrpgcore.RPG.Player.RPGClass RPGClass, Rank rank, cg.creamgod45.cgrpgcore.RPG.Player.CPI CPI, Skill[] unlockSkill) {
        Health = health;
        this.MaxHealth = maxHealth;
        this.MANA = MANA;
        Stamina = stamina;
        Level = level;
        this.attributes = attributes;
        this.points = points;
        this.equipment = equipment;
        this.pet = pet;
        this.team = team;
        this.RPGClass = RPGClass;
        this.rank = rank;
        this.CPI = CPI;
        this.unlockSkill = unlockSkill;
    }

    public RPGPlayer(double health, double maxHealth, double MANA, double stamina, cg.creamgod45.cgrpgcore.RPG.Player.Level level, Attributes attributes, Points points, Equipment equipment, Pet pet, Team team, cg.creamgod45.cgrpgcore.RPG.Player.RPGClass RPGClass, Rank rank, cg.creamgod45.cgrpgcore.RPG.Player.CPI CPI) {
        Health = health;
        this.MaxHealth = maxHealth;
        this.MANA = MANA;
        Stamina = stamina;
        Level = level;
        this.attributes = attributes;
        this.points = points;
        this.equipment = equipment;
        this.pet = pet;
        this.team = team;
        this.RPGClass = RPGClass;
        this.rank = rank;
        this.CPI = CPI;
        this.unlockSkill = new Skill[]{};
    }

    public double getMaxHealth() {
        return MaxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        MaxHealth = maxHealth;
    }

    @Override
    public String toString() {
        return "RPGPlayer{" +
                "player=" + player +
                ", Health=" + Health +
                ", MaxHealth=" + MaxHealth +
                ", MANA=" + MANA +
                ", Stamina=" + Stamina +
                ", Level=" + Level +
                ", attributes=" + attributes +
                ", points=" + points +
                ", equipment=" + equipment +
                ", pet=" + pet +
                ", team=" + team +
                ", RPGClass=" + RPGClass +
                ", rank=" + rank +
                ", CPI=" + CPI +
                ", unlockSkill=" + Arrays.toString(unlockSkill) +
                '}';
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player){ this.player = player;}

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        Health = health;
    }

    public double getMANA() {
        return MANA;
    }

    public void setMANA(double MANA) {
        this.MANA = MANA;
    }

    public double getStamina() {
        return Stamina;
    }

    public void setStamina(double stamina) {
        Stamina = stamina;
    }

    public cg.creamgod45.cgrpgcore.RPG.Player.Level getLevel() {
        return Level;
    }

    public void setLevel(cg.creamgod45.cgrpgcore.RPG.Player.Level level) {
        Level = level;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public RPGClass getRPGClass() {
        return RPGClass;
    }

    public void setRPGClass(cg.creamgod45.cgrpgcore.RPG.Player.RPGClass RPGClass) {
        this.RPGClass = RPGClass;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public cg.creamgod45.cgrpgcore.RPG.Player.CPI getCPI() {
        return CPI;
    }

    public void setCPI(cg.creamgod45.cgrpgcore.RPG.Player.CPI CPI) {
        this.CPI = CPI;
    }

    public Skill[] getUnlockSkill() {
        return unlockSkill;
    }

    public void setUnlockSkill(Skill[] unlockSkill) {
        this.unlockSkill = unlockSkill;
    }
}
