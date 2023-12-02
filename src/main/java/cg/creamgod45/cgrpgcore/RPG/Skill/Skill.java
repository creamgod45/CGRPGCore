package cg.creamgod45.cgrpgcore.RPG.Skill;

import java.util.List;

public class Skill {
    private String Name;
    private List<String> Description;
    private int Level;
    private SkillLevelCurve LevelCurve;
    private long cooldown;
    private SkillModify[] modify;
}
