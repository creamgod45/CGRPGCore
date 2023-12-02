package cg.creamgod45.cgrpgcore.RPG.Player;

import java.util.List;

// 基本範本類(ClassLoader -> ClassList -> Map<String, Class>) 儲存類 (RPGPlayer)
public class RPGClass {
    private RPGClass parentRPGClass;
    private String Name;
    private List<String> Description;
    private int maxLevel;
    private LevelCurve levelCurve;
    private double Health;
    private double HealthLevelUPAddition;
    private double MANA;
    private double MANALevelUPAddition;
    private double Stamina;
    private double StaminaLevelUPAddition;
    public RPGClass(){}
    public RPGClass(abstractRPGClass abstractRPGClass){}
    public RPGClass(RPGClass parentRPGClass, String name, List<String> description, int maxLevel, LevelCurve levelCurve, double health, double healthLevelUPAddition, double MANA, double MANALevelUPAddition, double stamina, double staminaLevelUPAddition) {
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

    public RPGClass(String name, List<String> description, int maxLevel, LevelCurve levelCurve, double health, double healthLevelUPAddition, double MANA, double MANALevelUPAddition, double stamina, double staminaLevelUPAddition) {
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

}
