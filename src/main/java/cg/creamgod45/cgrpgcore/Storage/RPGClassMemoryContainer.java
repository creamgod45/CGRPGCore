package cg.creamgod45.cgrpgcore.Storage;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.RPG.Player.LevelCurve;
import cg.creamgod45.cgrpgcore.RPG.Player.RPGClass;
import cg.creamgod45.cgrpgcore.RPG.Player.abstractRPGClass;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 記憶體儲存
public class RPGClassMemoryContainer {
    private final CGRPGCore plugin;
    private Map<String, RPGClass> rpgClassMap;

    public RPGClassMemoryContainer(CGRPGCore plugin) {
        this.plugin = plugin;
        this.rpgClassMap = new HashMap<>();
        initClassFile();
        saveClass();
    }

    public Map<String, RPGClass> getRpgClassMap() {
        return rpgClassMap;
    }

    @Override
    public String toString() {
        return "RPGClassMemoryContainer{" +
                "plugin=" + plugin +
                ", rpgClassMap=" + rpgClassMap +
                '}';
    }

    private YamlConfiguration getYml() {
        File file = new File(plugin.getDataFolder() + "/classs.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        return yamlConfiguration;
    }

    public boolean isVaild(String name) {
        if (getYml().get(name) == null) return false;
        ConfigurationSection configurationSection = getYml().getConfigurationSection(name);
        if (configurationSection == null) return false;
        if (configurationSection.get("defaultClass") == null) return false;
        if (configurationSection.get("Name") == null) return false;
        if (configurationSection.get("Description") == null) return false;
        if (configurationSection.get("maxLevel") == null) return false;
        if (configurationSection.get("levelCurve") == null) return false;
        if (configurationSection.get("levelCurve.maxlevelAddition") == null) return false;
        if (configurationSection.get("levelCurve.maxlevelAddition.type") == null) return false;
        if (configurationSection.get("levelCurve.maxlevelAddition.value") == null) return false;
        if (configurationSection.get("Health") == null) return false;
        if (configurationSection.get("HealthLevelUPAddition") == null) return false;
        if (configurationSection.get("MANA") == null) return false;
        if (configurationSection.get("MANALevelUPAddition") == null) return false;
        if (configurationSection.get("Stamina") == null) return false;
        return configurationSection.get("StaminaLevelUPAddition") != null;
    }

    public void getRpgClass1(Map<String, abstractRPGClass> abstractRPGClasses) {
        abstractRPGClasses.forEach((classname, abstractRPGClass) -> {
            RPGClass rpgClass;
            if (abstractRPGClass.getParentRPGClass() == null) {
                rpgClass = new RPGClass(abstractRPGClass);
            } else {
                RPGClass parentRpgClass = rpgClassMap.get(abstractRPGClass.getParentRPGClass());
                rpgClass = new RPGClass(abstractRPGClass, parentRpgClass);
            }
            rpgClassMap.put(classname, rpgClass);
        });
    }

    public void getrpgclass(Map<String, abstractRPGClass> abstractRPGClasses) {
        // 先建立沒有父系
        abstractRPGClasses.forEach((classname, abstractRPGClass) -> {
            if (abstractRPGClass.getParentRPGClass() == null) {
                RPGClass rpgClass = new RPGClass(abstractRPGClass);
                rpgClassMap.put(classname, rpgClass);
            }
        });
        abstractRPGClasses.forEach((classname, abstractRPGClass) -> {
            if (abstractRPGClass.getParentRPGClass() != null) {
                RPGClass parentRpgClass = rpgClassMap.get(abstractRPGClass.getParentRPGClass());
                RPGClass rpgClass = new RPGClass(abstractRPGClass, parentRpgClass);
                rpgClassMap.put(classname, rpgClass);
            }
        });
    }

    public void saveClass(){
        File file = new File(plugin.getDataFolder() + "/tmp.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        YamlConfiguration yml = getYml();
        rpgClassMap.forEach(yml::set);
        try {
            yml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initClassFile() {
        Map<String, abstractRPGClass> abstractRPGClasses = new HashMap<>();
        YamlConfiguration yml = getYml();
        Set<String> keys = yml.getKeys(false);
        for (String key : keys) {
            if (isVaild(key)) {
                ConfigurationSection cs = yml.getConfigurationSection(key);
                if (cs != null) {
                    String parentRPGClass = cs.getString("parentRPGClass", null);
                    String Name = cs.getString("Name");
                    List<String> description = cs.getStringList("Description");
                    int maxLevel = cs.getInt("maxLevel");
                    String levelCurve_maxlevelAddition_type = cs.getString("levelCurve.maxlevelAddition.type", "Addition");
                    double levelCurve_maxlevelAddition_value = cs.getDouble("levelCurve.maxlevelAddition.value");
                    double levelCurve_maxlevelAddition_base = cs.getDouble("levelCurve.maxlevelAddition.base");
                    double Health = cs.getDouble("Health");
                    double HealthLevelUPAddition = cs.getDouble("HealthLevelUPAddition");
                    double MANA = cs.getDouble("MANA");
                    double MANALevelUPAddition = cs.getDouble("MANALevelUPAddition");
                    double Stamina = cs.getDouble("Stamina");
                    double StaminaLevelUPAddition = cs.getDouble("StaminaLevelUPAddition");
                    boolean defaultClass = cs.getBoolean("defaultClass");
                    abstractRPGClass abstractRPGClass = new abstractRPGClass(parentRPGClass, Name, description, maxLevel,
                            new LevelCurve(levelCurve_maxlevelAddition_type,levelCurve_maxlevelAddition_value, levelCurve_maxlevelAddition_base),
                            Health, HealthLevelUPAddition, MANA, MANALevelUPAddition, Stamina, StaminaLevelUPAddition, defaultClass, key);
                    abstractRPGClasses.put(key, abstractRPGClass);
                }
            }
        }
        getrpgclass(abstractRPGClasses);
    }
}
