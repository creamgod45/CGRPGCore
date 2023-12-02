package cg.creamgod45.cgrpgcore.Utils;

import cg.creamgod45.cgrpgcore.CGRPGCore;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Utils {
    public static HookingPlugin hasHookingPlugin(String pluginName){
        return new HookingPlugin(pluginName);
    }
    public static RayTraceResult getRayTrace(Player paramPlayer, double paramDouble, Predicate<Block> paramPredicate, Predicate<Entity> paramPredicate1) {
        Location location = paramPlayer.getEyeLocation();
        Vector vector = location.getDirection();
        RayTraceResult rayTraceResult = paramPlayer.getWorld().rayTraceBlocks(location, vector, paramDouble, FluidCollisionMode.NEVER, false);
        if (rayTraceResult == null || rayTraceResult.getHitBlock() == null || !paramPredicate.test((Block) rayTraceResult.getHitBlock()))
            return paramPlayer.getWorld().rayTraceEntities(location, vector, paramDouble, 0.0D, paramPredicate1);
        return rayTraceResult;
    }

    public static RayTraceResult getRayTraceResult(Player paramPlayer, double paramDouble) {
        return getRayTraceResult(paramPlayer, paramDouble, null);
    }

    public static RayTraceResult getRayTraceResult(Player paramPlayer, double paramDouble, List<EntityType> paramList) {
        Location location = paramPlayer.getEyeLocation();
        Vector vector = location.getDirection();
        return paramPlayer.getWorld().rayTrace(location, vector, paramDouble, FluidCollisionMode.NEVER, false, 0.0D, paramEntity -> paramEntity != null && (!paramEntity.equals(paramPlayer) && (
                paramList == null || paramList.isEmpty() || paramList.contains(paramEntity.getType()))));

    }

    public static long LongTounixtime(long lastModified) {
        long seconds = lastModified / 1000;
        return seconds - 11644473600L;
    }

    public static Component ComponentParser(String message) {
        MiniMessage miniMessage = MiniMessage.builder().tags(TagResolver.builder().resolver(StandardTags.defaults()).build()).build();
        return miniMessage.deserialize(message);
    }

    public static void MiniMessageComponent(Component component, Player player) {
        CGRPGCore.adventure.player(player).sendMessage(component);
    }

    public static void MiniMessageComponent(Component component, UUID uuid) {
        CGRPGCore.adventure.player(uuid).sendMessage(component);
    }

    public static void MiniMessageComponent(Component component, World world) {
        CGRPGCore.adventure.world(Key.key(world.getKey().toString())).sendMessage(component);
    }

    public static void MiniMessageComponent(Component component, boolean players) {
        CGRPGCore.adventure.players().sendMessage(component);
    }

    public static void MiniMessage(String message) {
        CGRPGCore.adventure.console().sendMessage(ComponentParser(message));
    }

    public static void MiniMessage(String message, Player player) {
        CGRPGCore.adventure.player(player).sendMessage(ComponentParser(message));
    }

    public static void MiniMessage(String message, UUID uuid) {
        CGRPGCore.adventure.player(uuid).sendMessage(ComponentParser(message));
    }

    public static void MiniMessage(String message, World world) {
        CGRPGCore.adventure.world(Key.key(world.getKey().toString())).sendMessage(ComponentParser(message));
    }

    public static void MiniMessage(String message, boolean players) {
        CGRPGCore.adventure.players().sendMessage(ComponentParser(message));
    }

    public static List<String> newlineString(String originalString, String insertString, int splitSize) {
        List<String> result = new ArrayList<>();
        int length = originalString.length();

        for (int i = 0; i < length; i += splitSize) {
            int endIndex = Math.min(i + splitSize, length); // 计算子字符串的结束索引
            String subString = originalString.substring(i, endIndex); // 获取子字符串
            result.add(subString);

            if (endIndex < length) {
                result.add(insertString); // 在每个子字符串之间插入指定字符串
            }
        }

        return result;
    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList(map.entrySet());
        list.sort(Entry.comparingByValue());
        Map<K, V> result = new LinkedHashMap();
        Iterator var3 = list.iterator();

        while (var3.hasNext()) {
            Entry<K, V> entry = (Entry) var3.next();
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static void sendmessagetoPlayer(Player player, String s) {
        player.sendMessage(format(s));
    }

    public static ItemStack setPlayerHead(Player player, String displayname) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(player);
        itemMeta.setDisplayName(format(displayname));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack setPlayerHead(Player player, String displayname, List<String> lore) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(player);
        itemMeta.setDisplayName(format(displayname));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static Boolean HasPermission(Player player, String Permission) {
        if (Objects.equals(Permission, "")) {
            Permission = "CGRPGCore.admin";
        }

        if (player.hasPermission("CGRPGCore.admin")) {
            return true;
        } else {
            return player.isOp() || player.hasPermission(Permission);
        }
    }

    public static String format(String string) {
        return string == null ? string : ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String decolor(String string) {
        return string.replaceAll("&1|&2|&3|&4|&5|&6|&7|&8|&9|&a|&b|&c|&d|&e|&f|&l|&m|&n|&o|&r", "");
    }

    public static ItemStack getCustomSkull(String url) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) {
            return head;
        } else {
            SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", url));

            try {
                Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
                mtd.setAccessible(true);
                mtd.invoke(skullMeta, profile);
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var5) {
                var5.printStackTrace();
            }

            head.setItemMeta(skullMeta);
            return head;
        }
    }

    public static List<String> getMaterialList() {
        List<String> enumNames = Stream.of(Material.values()).map(Enum::name).toList();
        List<String> enumNames1 = new ArrayList();
        Iterator var2 = enumNames.iterator();

        while (var2.hasNext()) {
            String enumName = (String) var2.next();
            Material material = Material.matchMaterial(enumName, false);
            if (material != null && !material.isAir() && material.isItem()) {
                ItemStack build = (new ItemBuilder(Material.getMaterial(enumName))).build();
                if (build != null) {
                    enumNames1.add(enumName);
                }
            }
        }

        return enumNames1;
    }

    public static OfflinePlayer getPlayer(String player_name) {
        List<Player> player_list = new ArrayList(Bukkit.getOnlinePlayers());
        Iterator var2 = player_list.iterator();

        Player player;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            player = (Player) var2.next();
        } while (!player.getName().equals(player_name));

        return player;
    }

    public static OfflinePlayer getPlayer(UUID uuid) {
        List<Player> player_list = new ArrayList(Bukkit.getOnlinePlayers());
        Iterator var2 = player_list.iterator();

        Player player;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            player = (Player) var2.next();
        } while (!player.getUniqueId().equals(uuid));

        return player;
    }

    public static int getPlayerNum() {
        int num = 0;
        List<Player> player_list = new ArrayList(Bukkit.getOnlinePlayers());

        for (Iterator var2 = player_list.iterator(); var2.hasNext(); ++num) {
            Player player = (Player) var2.next();
        }

        return num;
    }

    public static String arraytostring(String[] array, int start) {
        if (array == null) {
            return "null";
        } else if (start < 0) {
            return "null";
        } else {
            array = Arrays.copyOfRange(array, start, array.length);
            return String.join(" ", array);
        }
    }

    public static void removeOne(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();

        for (int i = 0; i < size; ++i) {
            ItemStack other = inventory.getItem(i);
            if (item.isSimilar(other)) {
                int amount = other.getAmount();
                if (amount > 1) {
                    other.setAmount(amount - 1);
                } else {
                    other.setAmount(other.getAmount() - 1);
                }

                inventory.setItem(i, other);
                break;
            }
        }

    }

    public static Boolean hasItem(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();

        for (int i = 0; i < size; ++i) {
            ItemStack other = inventory.getItem(i);
            if (item.isSimilar(other)) {
                return true;
            }
        }

        return false;
    }

    public static Boolean EnoughInventory(Player player) {
        int space = 0;
        ListIterator var2 = player.getInventory().iterator();

        while (var2.hasNext()) {
            ItemStack itemStack = (ItemStack) var2.next();

            try {
                String var4 = itemStack.toString();
            } catch (NullPointerException var10) {
                ++space;
            }
        }

        try {
            ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
            if (itemInOffHand.getType() == Material.AIR) {
                --space;
            }
        } catch (NullPointerException var9) {
            --space;
        }

        ItemStack[] armorContents = player.getInventory().getArmorContents();
        ItemStack[] var13 = armorContents;
        int var14 = armorContents.length;

        for (int var5 = 0; var5 < var14; ++var5) {
            ItemStack armorContent = var13[var5];

            try {
                armorContent.toString();
            } catch (NullPointerException var8) {
                --space;
            }
        }

        return space > 0;
    }

    public static void ConsoleInfoPrint(String msg) {
        CGRPGCore.plugin.getLogger().info(format(msg));
    }

    public static void ConsoleInfoPrint(String msg, boolean color) {
        if (color) {
            ConsoleInfoPrint(msg);
        } else {
            CGRPGCore.plugin.getLogger().info(msg);
        }

    }

    public static void ConsoleInfoPrint(String msg, int space) {
        String spacer = "";
        if (space > 0) {
            for (int i = 0; i < space; ++i) {
                spacer = spacer + " ";
            }

            ConsoleInfoPrint(spacer + format(msg));
        } else {
            ConsoleInfoPrint(format(msg));
        }

    }

    public static String getRandomString(int i) {
        byte[] bytearray = new byte[256];
        (new Random()).nextBytes(bytearray);
        String mystring = new String(bytearray, StandardCharsets.UTF_8);
        StringBuilder thebuffer = new StringBuilder();
        String theAlphaNumericS = mystring.replaceAll("[^A-Z0-9]", "");

        for (int m = 0; m < theAlphaNumericS.length(); ++m) {
            if (Character.isLetter(theAlphaNumericS.charAt(m)) && i > 0 || Character.isDigit(theAlphaNumericS.charAt(m)) && i > 0) {
                thebuffer.append(theAlphaNumericS.charAt(m));
                --i;
            }
        }

        return thebuffer.toString();
    }

    public static void setMetadata(Entity player, String key, Object value, Plugin plugin) {
        player.setMetadata(key, new FixedMetadataValue(plugin, value));
    }

}
