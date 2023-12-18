package cg.creamgod45.cgrpgcore.RPG.Player;

public class Level {
    public int Level;
    public double lessExp;
    public double maxExp;

    public Level(int level, double maxExp) {
        Level = level;
        this.maxExp = maxExp;
    }

    public Level(int level, int lessExp, int maxExp) {
        Level = level;
        this.maxExp = maxExp;
        this.lessExp = lessExp;
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
