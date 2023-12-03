package cg.creamgod45.cgrpgcore.RPG.Player;

public class LevelCurve {
    public String type;
    public double value;
    public double base;
    public LevelCurve() {
    }

    public LevelCurve(String a, double b, double c) {
        this.type = a;
        this.value = b;
        this.base = c;
    }

    @Override
    public String toString() {
        return "LevelCurve{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", base=" + base +
                '}';
    }
}
