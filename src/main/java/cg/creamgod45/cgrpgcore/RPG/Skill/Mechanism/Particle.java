package cg.creamgod45.cgrpgcore.RPG.Skill.Mechanism;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Particle {
    // 圓球體
    public  static void spawnSphere(Player player){
        spawnSphere(player, 1, 1.0);
    }

    // 圓球體
    public static void spawnSphere(Player player, double radius) {
        spawnSphere(player, radius, 1.0);
    }

    // 圓球體
    public static void spawnSphere(Player player, double radius, double py) {
        int rings = 10; // 調整圈的數量
        int particlesPerRing = 36; // 每圈的粒子數量

        for (int i = 0; i < rings; i++) {
            double phi = Math.PI * (i + 0.5) / rings; // 計算圓環上的角度
            double y = radius * Math.cos(phi) + py;

            for (int j = 0; j < particlesPerRing; j++) {
                double theta = 2 * Math.PI * j / particlesPerRing;
                double sinPhi = Math.sin(phi);

                double x = radius * sinPhi * Math.sin(theta);
                double z = radius * sinPhi * Math.cos(theta);

                Vector particleLocation = new Vector(x, y, z);
                org.bukkit.Particle.DustOptions dust = new org.bukkit.Particle.DustOptions(Color.fromRGB(212, 146, 53), 0.6F);
                player.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, player.getLocation().clone().add(particleLocation), 0, dust);
            }
        }
    }

    // 圓柱體
    public static void spawnCylinder(Player player) {
        int rings = 10; // 調整圈的數量
        int particlesPerRing = 36; // 每圈的粒子數量
        double radius = 1.0; // 球體半徑

        for (int i = 0; i < rings; i++) {
            double y = radius * Math.cos(Math.PI * i / (rings - 1)) + 0.1; // 計算圓環上的y坐標

            for (int j = 0; j < particlesPerRing; j++) {
                double theta = 2 * Math.PI * j / particlesPerRing;
                double x = radius * Math.sin(theta);
                double z = radius * Math.cos(theta);

                Vector particleLocation = new Vector(x, y, z);
                org.bukkit.Particle.DustOptions dust = new org.bukkit.Particle.DustOptions(Color.fromRGB(212, 146, 53), 0.6F);
                player.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, player.getLocation().clone().add(particleLocation), 0, dust);
            }
        }
    }

    // 震波
    public static void spawnShockWave(Player player){
        for (double i = 0.1, y = 0; i < 2.0; i+=0.1) {
            spawnHalo(player, i, y);
            if(i<=1.0)
                y = i;
            else{
                y -= 0.1;
            }
        }
    }

    // 光環
    public static void spawnHalo(Player player, double size , double y){
        Location location = player.getLocation();

        location.add(location.getDirection().normalize().multiply(-0.3)); // move behind the player
        location.add(0, y,0);
        location.setPitch(0F); // stop vertical rotation, only make particles rotate to sides, not up and down

        for (double degree = 0; degree < 360; degree += 2 /* particle density */) {
            double radians = Math.toRadians(degree);

            double x = Math.sin(radians) * size;
            double z = Math.cos(radians) * size;

            Vector particleLocation = new Vector(x, 0, z);
            org.bukkit.Particle.DustOptions dust = new org.bukkit.Particle.DustOptions(Color.fromRGB(212, 146, 53), 0.6F);
            player.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, location.clone().add(particleLocation), 0, dust);
        }
    }

    // 光環
    public static void spawnHalo(Player player, double size){
        spawnHalo(player, size, 0);
    }

    // 光環
    public static void spawnHalo(Player player){
        spawnHalo(player, 1);
    }


    public static void spawnVibration(Player player) {
        Vibration vibration = new Vibration(player.getLocation(), new Vibration.Destination.BlockDestination(player.getLocation()), 40);
        player.getWorld().spawnParticle(org.bukkit.Particle.VIBRATION, player.getLocation(), 1, vibration);
    }

    public static void spawnButterflyWingEffect(Player player) {
        Location location = player.getLocation();

        location.add(location.getDirection().normalize().multiply(-0.3)); // move behind the player
        location.add(0, 0.85, 0); // push down to chest
        location.setPitch(0F); // stop vertical rotation, only make particles rotate to sides, not up and down

        double wingSize = 0.35;
        double circlesAmount = 4;

        for (double degree = 0; degree < 360; degree += 2 /* particle density */) {
            double radians = Math.toRadians(degree);

            double circle = wingSize * Math.pow(Math.E, Math.cos(radians));
            double radius = circle - Math.cos(circlesAmount * radians);

            double x = Math.sin(radians) * radius;
            double z = Math.cos(radians) * radius;

            Vector particleLocation = new Vector(x, 0, z);

            rotateAroundAxisX(particleLocation, -90);
            rotateAroundAxisY(particleLocation, location.getYaw());

            try {
                org.bukkit.Particle.DustOptions dust = new org.bukkit.Particle.DustOptions(Color.fromRGB(212, 146, 53), 0.6F);
                player.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, location.clone().add(particleLocation), 0, dust);

            } catch (Throwable t) {
                // Unsupported
            }
        }
    }

    private static void rotateAroundAxisX(Vector vector, double angle) {
        angle = Math.toRadians(angle);

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = vector.getY() * cos - vector.getZ() * sin;
        double z = vector.getY() * sin + vector.getZ() * cos;

        vector.setY(y).setZ(z);
    }

    private static void rotateAroundAxisY(Vector vector, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = vector.getX() * cos + vector.getZ() * sin;
        double z = vector.getX() * -sin + vector.getZ() * cos;

        vector.setX(x).setZ(z);
    }
}
