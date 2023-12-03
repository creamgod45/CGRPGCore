package cg.creamgod45.cgrpgcore.RPG.Player;

public class Level {
    public int Level;
    public double lessExp;
    public double maxExp;

    public Level(int level, double maxExp) {
        Level = level;
        this.maxExp = maxExp;
    }

    @Override
    public String toString() {
        return "Level{" +
                "Level=" + Level +
                ", lessExp=" + lessExp +
                ", maxExp=" + maxExp +
                '}';
    }
}
