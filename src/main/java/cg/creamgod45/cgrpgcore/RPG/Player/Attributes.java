package cg.creamgod45.cgrpgcore.RPG.Player;

public class Attributes {
    // 迴避
    public double Evasion;
    // 靈巧
    public double Dexterity;
    // 敏捷度
    public double Agility;
    // 靈性
    public double Spirituality;
    // 靈力
    public double SpiritPower;
    // 靈性感知
    public double SpiritualAwareness;
    // 靈性抗性
    public double SpiritualResistance;
    // 力量
    public double Strength;
    // 攻擊力
    public double Attack;
    // 防禦力
    public double Defense;
    // 攻擊力抗性 
    public double AttackResistance;
    // 強韌力
    public double Toughness;

    public Attributes(){
        Evasion             = 0;
        Dexterity           = 0;
        Agility             = 0;
        Spirituality        = 0;
        SpiritPower         = 0;
        SpiritualAwareness  = 0;
        SpiritualResistance = 0;
        Strength            = 0;
        Attack              = 0;
        Defense             = 0;
        AttackResistance    = 0;
        Toughness           = 0;
    }

    public Attributes(double evasion, double dexterity, double agility, double spirituality, double spiritPower, double spiritualAwareness, double spiritualResistance, double strength, double attack, double defense, double attackResistance, double toughness) {
        Evasion = evasion;
        Dexterity = dexterity;
        Agility = agility;
        Spirituality = spirituality;
        SpiritPower = spiritPower;
        SpiritualAwareness = spiritualAwareness;
        SpiritualResistance = spiritualResistance;
        Strength = strength;
        Attack = attack;
        Defense = defense;
        AttackResistance = attackResistance;
        Toughness = toughness;
    }
}
