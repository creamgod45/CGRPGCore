package cg.creamgod45.cgrpgcore.Storage;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import cg.creamgod45.cgrpgcore.RPG.Player.*;
import cg.creamgod45.cgrpgcore.RPG.Skill.Skill;
import cg.creamgod45.cgrpgcore.Utils.Utils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class RPGPlayerMemoryContainer {
    private final CGRPGCore plugin;
    private Map<UUID, RPGPlayer> rpgplayerMap = new HashMap<>();

    public RPGPlayerMemoryContainer(CGRPGCore plugin) {
        this.plugin = plugin;

        initRPGPlayers();
    }

    public Map<UUID, RPGPlayer> getRpgplayerMap() {
        return rpgplayerMap;
    }

    public void setRpgplayerMap(Map<UUID, RPGPlayer> rpgplayerMap) {
        this.rpgplayerMap = rpgplayerMap;
    }

    @Override
    public String toString() {
        return "RPGPlayerMemoryContainer{" + "plugin=" + plugin + ", rpgplayerMap=" + rpgplayerMap + '}';
    }

    public void updateRPGPlayer(Player player) {
        YamlConfiguration yml = getYml();
        File file = getFile();
        if (rpgplayerMap.containsKey(player.getUniqueId())) {
            RPGPlayer rpgPlayer = rpgplayerMap.get(player.getUniqueId());
            rpgPlayer.setHealth(player.getHealth());
            ymlset(player, yml, rpgPlayer, file);
        }
    }

    private void ymlset(Player player, YamlConfiguration yml, RPGPlayer rpgPlayer, File file) {
        rpgplayerMap.put(player.getUniqueId(), rpgPlayer);
        String uuid = player.getUniqueId().toString();
        yml.set(uuid + ".player", uuid);
        yml.set(uuid + ".Health", rpgPlayer.getHealth());
        yml.set(uuid + ".MaxHealth", rpgPlayer.getMaxHealth());
        yml.set(uuid + ".MANA", rpgPlayer.getMANA());
        yml.set(uuid + ".Stamina", rpgPlayer.getStamina());
        Level level = rpgPlayer.getLevel();
        yml.set(uuid + ".Level.Level", level.Level);
        yml.set(uuid + ".Level.lessExp", level.lessExp);
        yml.set(uuid + ".Level.maxExp", level.maxExp);
        yml.set(uuid + ".attributes.Evasion", rpgPlayer.getAttributes().Evasion);
        yml.set(uuid + ".attributes.Dexterity", rpgPlayer.getAttributes().Dexterity);
        yml.set(uuid + ".attributes.Agility", rpgPlayer.getAttributes().Agility);
        yml.set(uuid + ".attributes.Spirituality", rpgPlayer.getAttributes().Spirituality);
        yml.set(uuid + ".attributes.SpiritPower", rpgPlayer.getAttributes().SpiritPower);
        yml.set(uuid + ".attributes.SpiritualAwareness", rpgPlayer.getAttributes().SpiritualAwareness);
        yml.set(uuid + ".attributes.SpiritualResistance", rpgPlayer.getAttributes().SpiritualResistance);
        yml.set(uuid + ".attributes.Strength", rpgPlayer.getAttributes().Strength);
        yml.set(uuid + ".attributes.Attack", rpgPlayer.getAttributes().Attack);
        yml.set(uuid + ".attributes.Defense", rpgPlayer.getAttributes().Defense);
        yml.set(uuid + ".attributes.AttackResistance", rpgPlayer.getAttributes().AttackResistance);
        yml.set(uuid + ".attributes.Toughness", rpgPlayer.getAttributes().Toughness);
        yml.set(uuid + ".points", rpgPlayer.getPoints());
        yml.set(uuid + ".equipment", rpgPlayer.getEquipment());
        yml.set(uuid + ".pet", rpgPlayer.getPet());
        yml.set(uuid + ".team", rpgPlayer.getTeam());
        yml.set(uuid + ".RPGClass", rpgPlayer.getRPGClass());
        yml.set(uuid + ".rank", rpgPlayer.getRank());
        yml.set(uuid + ".CPI", rpgPlayer.getCPI());
        yml.set(uuid + ".unlockSkill", rpgPlayer.getUnlockSkill());
        try {
            yml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRPGPlayer(Player player) {
        YamlConfiguration yml = getYml();
        File file = getFile();
        Object o = plugin.getMemoryContainer().get(StorageNameField.RPGClasss);
        if (o instanceof RPGClassMemoryContainer RCMC) {
            final int[] k = {0};
            AtomicReference<RPGClass> rpgClass = new AtomicReference<>();
            RCMC.getRpgClassMap().forEach((string, r) -> {
                if (r.isDefaultClass() && k[0] < 1) {
                    rpgClass.set(r);
                    k[0] = k[0] + 1;
                }
            });
            RPGPlayer rpgPlayer = new RPGPlayer(player.getHealth(), rpgClass.get().getHealth(), rpgClass.get().getMANA(), rpgClass.get().getStamina(), new Level(1, rpgClass.get().getLevelCurve().base), new Attributes(), new Points(), new Equipment(), new Pet(), new Team(), rpgClass.get(), new Rank(), new CPI());
            rpgPlayer.setPlayer(player);
            ymlset(player, yml, rpgPlayer, file);
        }
    }

    private File getFile() {
        File file = new File(plugin.getDataFolder() + "/players.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    private YamlConfiguration getYml() {
        File file = getFile();
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
        return yamlConfiguration;
    }

    public boolean isVaildPlayerProfile(String player) {
        YamlConfiguration yml = getYml();
        if (yml.get(player) == null) return false;
        ConfigurationSection cs = yml.getConfigurationSection(player);
        if (cs != null && cs.isConfigurationSection(player)) {
            if (cs.get("player") == null) return false;
            if (cs.get("Health") == null) return false;
            if (cs.get("MaxHealth") == null) return false;
            if (cs.get("MANA") == null) return false;
            if (cs.get("Stamina") == null) return false;
            if (cs.get("Level.Level") == null) return false;
            if (cs.get("Level.lessExp") == null) return false;
            if (cs.get("Level.maxExp") == null) return false;
            if (cs.get("attributes") == null) return false;
            if (cs.get("attributes.Evasion") == null) return false;
            if (cs.get("attributes.Dexterity") == null) return false;
            if (cs.get("attributes.Agility") == null) return false;
            if (cs.get("attributes.Spirituality") == null) return false;
            if (cs.get("attributes.SpiritPower") == null) return false;
            if (cs.get("attributes.SpiritualAwareness") == null) return false;
            if (cs.get("attributes.SpiritualResistance") == null) return false;
            if (cs.get("attributes.Strength") == null) return false;
            if (cs.get("attributes.Attack") == null) return false;
            if (cs.get("attributes.Defense") == null) return false;
            if (cs.get("attributes.AttackResistance") == null) return false;
            if (cs.get("attributes.Toughness") == null) return false;
            if (cs.get("points") == null) return false;
            if (cs.get("pet") == null) return false;
            if (cs.get("team") == null) return false;
            if (cs.get("RPGClass") == null) return false;
            if (cs.get("rank") == null) return false;
            if (cs.get("CPI") == null) return false;
            return cs.get("unlockSkill") != null;
        }
        return true;
    }


    public void initRPGPlayers() {
        Object o = plugin.getMemoryContainer().get(StorageNameField.RPGPlayers);
        YamlConfiguration yml = getYml();
        Set<String> keys = yml.getKeys(false);
        for (String key : keys) {
            //Utils.MiniMessage(key);
            if (isVaildPlayerProfile(key)) {
                //Utils.MiniMessage(":true");
                ConfigurationSection cs = yml.getConfigurationSection(key);
                if (cs != null) {
                    String uuid = cs.getString("player");
                    double health = cs.getDouble("Health", 20);
                    double maxHealth = cs.getDouble("MaxHealth", 20);
                    double mana = cs.getDouble("MANA", 0);
                    double stamina = cs.getDouble("Stamina");
                    int level = cs.getInt("Level.Level");
                    int lessExp = cs.getInt("Level.lessExp");
                    int maxExp = cs.getInt("Level.maxExp");
                    double Evasion = cs.getDouble("attributes.Evasion", 0d);
                    double Dexterity = cs.getDouble("attributes.Dexterity", 0d);
                    double Agility = cs.getDouble("attributes.Agility", 0d);
                    double Spirituality = cs.getDouble("attributes.Spirituality", 0d);
                    double SpiritPower = cs.getDouble("attributes.SpiritPower", 0d);
                    double SpiritualAwareness = cs.getDouble("attributes.SpiritualAwareness", 0d);
                    double SpiritualResistance = cs.getDouble("attributes.SpiritualResistance", 0d);
                    double Strength = cs.getDouble("attributes.Strength", 0d);
                    double Attack = cs.getDouble("attributes.Attack", 0d);
                    double Defense = cs.getDouble("attributes.Defense", 0d);
                    double AttackResistance = cs.getDouble("attributes.AttackResistance", 0d);
                    double Toughness = cs.getDouble("attributes.Toughness", 0d);
                    Points points = cs.getObject("points", Points.class);
                    Equipment equipment = cs.getObject("equipment", Equipment.class);
                    Pet pet = cs.getObject("pet", Pet.class);
                    Team team = cs.getObject("team", Team.class);
                    RPGClass rpgClass = cs.getObject("RPGClass", RPGClass.class);
                    Rank rank = cs.getObject("rank", Rank.class);
                    CPI CPI = cs.getObject("CPI", CPI.class);
                    Skill[] unlockSkill = cs.getObject("unlockSkill", Skill[].class);
                    RPGPlayer rpgPlayer = new RPGPlayer(health, maxHealth, mana, stamina, new Level(level, lessExp, maxExp), new Attributes(Evasion, Dexterity, Agility, Spirituality, SpiritPower, SpiritualAwareness, SpiritualResistance, Strength, Attack, Defense, AttackResistance, Toughness), points, equipment, pet, team, rpgClass, rank, CPI, unlockSkill);
                    Player player1 = null;
                    if (uuid != null) {
                        player1 = (Player) Utils.getPlayer(UUID.fromString(uuid));
                    }
                    rpgPlayer.setPlayer(player1);
                    //Utils.MiniMessage(rpgPlayer.toString());
                    if (player1 != null) {
                        rpgplayerMap.put(player1.getUniqueId(), rpgPlayer);
                    }
                }
            }
        }
    }
}
