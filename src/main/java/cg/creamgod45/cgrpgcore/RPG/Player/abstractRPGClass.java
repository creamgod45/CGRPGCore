package cg.creamgod45.cgrpgcore.RPG.Player;

import java.util.List;

// 基本範本類(ClassLoader -> ClassList -> Map<String, Class>) 儲存類 (RPGPlayer)
public class abstractRPGClass {
    public abstractRPGClass(String parentRPGClass, String name, List<String> description, int maxLevel, LevelCurve levelCurve, double health, double healthLevelUPAddition, double MANA, double MANALevelUPAddition, double stamina, double staminaLevelUPAddition) {
        this.parentRPGClass = parentRPGClass;
        Name = name;
        Description = description;
        this.maxLevel = maxLevel;
        this.levelCurve = levelCurve;
        Health = health;
        HealthLevelUPAddition = healthLevelUPAddition;
        this.MANA = MANA;
        this.MANALevelUPAddition = MANALevelUPAddition;
        Stamina = stamina;
        StaminaLevelUPAddition = staminaLevelUPAddition;
    }

    private String parentRPGClass;
    private String Name;
    private List<String> Description;
    private int maxLevel;

    public String getParentRPGClass() {
        return parentRPGClass;
    }

    public void setParentRPGClass(String parentRPGClass) {
        this.parentRPGClass = parentRPGClass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getDescription() {
        return Description;
    }

    public void setDescription(List<String> description) {
        Description = description;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public LevelCurve getLevelCurve() {
        return levelCurve;
    }

    public void setLevelCurve(LevelCurve levelCurve) {
        this.levelCurve = levelCurve;
    }

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        Health = health;
    }

    public double getHealthLevelUPAddition() {
        return HealthLevelUPAddition;
    }

    public void setHealthLevelUPAddition(double healthLevelUPAddition) {
        HealthLevelUPAddition = healthLevelUPAddition;
    }

    public double getMANA() {
        return MANA;
    }

    public void setMANA(double MANA) {
        this.MANA = MANA;
    }

    public double getMANALevelUPAddition() {
        return MANALevelUPAddition;
    }

    public void setMANALevelUPAddition(double MANALevelUPAddition) {
        this.MANALevelUPAddition = MANALevelUPAddition;
    }

    public double getStamina() {
        return Stamina;
    }

    public void setStamina(double stamina) {
        Stamina = stamina;
    }

    public double getStaminaLevelUPAddition() {
        return StaminaLevelUPAddition;
    }

    public void setStaminaLevelUPAddition(double staminaLevelUPAddition) {
        StaminaLevelUPAddition = staminaLevelUPAddition;
    }

    private LevelCurve levelCurve;
    private double Health;
    private double HealthLevelUPAddition;
    private double MANA;
    private double MANALevelUPAddition;
    private double Stamina;
    private double StaminaLevelUPAddition;

}
