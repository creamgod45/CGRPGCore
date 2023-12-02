package cg.creamgod45.cgrpgcore.RPG.Player;

public class LevelCurve {
    public enum LevelCurveTypeEnum{
        Addition("Addition"),
        Subtraction("Subtraction"),
        Multiplication("Multiplication"),
        Division("Division");

        LevelCurveTypeEnum(String type) {
        }
    }
    public LevelCurveTypeEnum maxlevelAddition_type;
    public double maxlevelAddition_value;

    public LevelCurve() {
    }

    public LevelCurve(LevelCurveTypeEnum maxlevelAddition_type, double maxlevelAddition_value) {
        this.maxlevelAddition_type = maxlevelAddition_type;
        this.maxlevelAddition_value = maxlevelAddition_value;
    }
}
