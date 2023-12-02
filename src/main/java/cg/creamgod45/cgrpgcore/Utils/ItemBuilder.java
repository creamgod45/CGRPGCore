package cg.creamgod45.cgrpgcore.Utils;

import com.google.gson.Gson;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;
    private Material material;
    private int amount;
    private MaterialData data;
    private short damage;
    private Map<Enchantment, Integer> enchantments;
    private String displayname;
    private Integer customModelData;
    private List<String> lore;
    private List<ItemFlag> flags;
    private boolean andSymbol;
    private boolean unsafeStackSize;

    public ItemBuilder(Material material) {
        this.material = Material.STONE;
        this.amount = 1;
        this.damage = 0;
        this.enchantments = new HashMap();
        this.lore = new ArrayList();
        this.flags = new ArrayList();
        this.andSymbol = true;
        this.unsafeStackSize = false;
        if (material == null) {
            material = Material.AIR;
        }

        this.item = new ItemStack(material);
        this.material = material;
    }

    public ItemBuilder(Material material, int amount) {
        this.material = Material.STONE;
        this.amount = 1;
        this.damage = 0;
        this.enchantments = new HashMap();
        this.lore = new ArrayList();
        this.flags = new ArrayList();
        this.andSymbol = true;
        this.unsafeStackSize = false;
        if (material == null) {
            material = Material.AIR;
        }

        if ((amount > material.getMaxStackSize() || amount <= 0) && !this.unsafeStackSize) {
            amount = 1;
        }

        this.amount = amount;
        this.item = new ItemStack(material, amount);
        this.material = material;
    }

    public ItemBuilder(Material material, int amount, String displayname) {
        this.material = Material.STONE;
        this.amount = 1;
        this.damage = 0;
        this.enchantments = new HashMap();
        this.lore = new ArrayList();
        this.flags = new ArrayList();
        this.andSymbol = true;
        this.unsafeStackSize = false;
        if (material == null) {
            material = Material.AIR;
        }

        Validate.notNull(displayname, "The Displayname is null.");
        this.item = new ItemStack(material, amount);
        this.material = material;
        if ((amount > material.getMaxStackSize() || amount <= 0) && !this.unsafeStackSize) {
            amount = 1;
        }

        this.amount = amount;
        this.displayname = displayname;
    }

    public ItemBuilder(Material material, String displayname) {
        this.material = Material.STONE;
        this.amount = 1;
        this.damage = 0;
        this.enchantments = new HashMap();
        this.lore = new ArrayList();
        this.flags = new ArrayList();
        this.andSymbol = true;
        this.unsafeStackSize = false;
        if (material == null) {
            material = Material.AIR;
        }

        Validate.notNull(displayname, "The Displayname is null.");
        this.item = new ItemStack(material);
        this.material = material;
        this.displayname = displayname;
    }

    public ItemBuilder(ItemStack item) {
        this.material = Material.STONE;
        this.amount = 1;
        this.damage = 0;
        this.enchantments = new HashMap();
        this.lore = new ArrayList();
        this.flags = new ArrayList();
        this.andSymbol = true;
        this.unsafeStackSize = false;
        Validate.notNull(item, "The Item is null.");
        this.item = item;
        if (item.hasItemMeta()) {
            this.meta = item.getItemMeta();
        }

        this.material = item.getType();
        this.amount = item.getAmount();
        this.data = item.getData();
        this.damage = item.getDurability();
        this.enchantments = item.getEnchantments();
        if (item.hasItemMeta()) {
            this.displayname = item.getItemMeta().getDisplayName();
        }

        if (item.hasItemMeta()) {
            this.lore = item.getItemMeta().getLore();
        }

        if (item.hasItemMeta()) {
            Iterator var2 = item.getItemMeta().getItemFlags().iterator();

            while (var2.hasNext()) {
                ItemFlag f = (ItemFlag) var2.next();
                this.flags.add(f);
            }
        }

    }

    public ItemBuilder(FileConfiguration cfg, String path) {
        this(cfg.getItemStack(path));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public ItemBuilder(ItemBuilder builder) {
        this.material = Material.STONE;
        this.amount = 1;
        this.damage = 0;
        this.enchantments = new HashMap();
        this.lore = new ArrayList();
        this.flags = new ArrayList();
        this.andSymbol = true;
        this.unsafeStackSize = false;
        Validate.notNull(builder, "The ItemBuilder is null.");
        this.item = builder.item;
        this.meta = builder.meta;
        this.material = builder.material;
        this.amount = builder.amount;
        this.damage = builder.damage;
        this.data = builder.data;
        this.damage = builder.damage;
        this.enchantments = builder.enchantments;
        this.displayname = builder.displayname;
        this.lore = builder.lore;
        this.flags = builder.flags;
    }

    public static void toConfig(FileConfiguration cfg, String path, ItemBuilder builder) {
        cfg.set(path, builder.build());
    }

    public static String toJson(ItemBuilder builder) {
        return (new Gson()).toJson(builder);
    }

    public static ItemBuilder fromJson(String json) {
        return (new Gson()).fromJson(json, ItemBuilder.class);
    }

    public ItemBuilder customModelData(Integer id) {
        Validate.notNull(id, "The id is null.");
        this.customModelData = id;
        return this;
    }

    public ItemBuilder amount(int amount) {
        if ((amount > this.material.getMaxStackSize() || amount <= 0) && !this.unsafeStackSize) {
            amount = 1;
        }

        this.amount = amount;
        return this;
    }

    public ItemBuilder data(MaterialData data) {
        Validate.notNull(data, "The Data is null.");
        this.data = data;
        return this;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public ItemBuilder damage(short damage) {
        this.damage = damage;
        return this;
    }

    public ItemBuilder durability(short damage) {
        this.damage = damage;
        return this;
    }

    public ItemBuilder material(Material material) {
        Validate.notNull(material, "The Material is null.");
        this.material = material;
        return this;
    }

    public ItemBuilder meta(ItemMeta meta) {
        Validate.notNull(meta, "The Meta is null.");
        this.meta = meta;
        return this;
    }

    public ItemBuilder enchant(Enchantment enchant, int level) {
        Validate.notNull(enchant, "The Enchantment is null.");
        this.enchantments.put(enchant, level);
        return this;
    }

    public ItemBuilder enchant(Map<Enchantment, Integer> enchantments) {
        Validate.notNull(enchantments, "The Enchantments are null.");
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder displayname(String displayname) {
        Validate.notNull(displayname, "The Displayname is null.");
        this.displayname = this.andSymbol ? ChatColor.translateAlternateColorCodes('&', displayname) : displayname;
        return this;
    }

    public ItemBuilder lore(String line) {
        Validate.notNull(line, "The Line is null.");
        if (this.lore == null) this.lore = new ArrayList<>();
        this.lore.add(this.andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        Validate.notNull(lore, "The Lores are null.");
        this.lore = lore;
        return this;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public ItemBuilder lores(String... lines) {
        Validate.notNull(lines, "The Lines are null.");
        String[] var2 = lines;
        int var3 = lines.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String line = var2[var4];
            this.lore(this.andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
        }

        return this;
    }

    public ItemBuilder lore(String... lines) {
        Validate.notNull(lines, "The Lines are null.");
        String[] var2 = lines;
        int var3 = lines.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String line = var2[var4];
            this.lore(this.andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
        }

        return this;
    }

    public ItemBuilder lore(String line, int index) {
        Validate.notNull(line, "The Line is null.");
        this.lore.set(index, this.andSymbol ? ChatColor.translateAlternateColorCodes('&', line) : line);
        return this;
    }

    public ItemBuilder flag(ItemFlag flag) {
        Validate.notNull(flag, "The Flag is null.");
        this.flags.add(flag);
        return this;
    }

    public ItemBuilder flag(List<ItemFlag> flags) {
        Validate.notNull(flags, "The Flags are null.");
        this.flags = flags;
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        this.meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder glow() {
        this.enchant(this.material != Material.BOW ? Enchantment.ARROW_INFINITE : Enchantment.LUCK, 10);
        this.flag(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public ItemBuilder owner(String user) {
        Validate.notNull(user, "The Username is null.");
        if (this.material == Material.PLAYER_HEAD) {
            SkullMeta smeta = (SkullMeta) this.meta;
            smeta.setOwner(user);
            this.meta = smeta;
        }

        return this;
    }

    public Unsafe unsafe() {
        return new Unsafe(this);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public ItemBuilder replaceAndSymbol() {
        this.replaceAndSymbol(!this.andSymbol);
        return this;
    }

    public ItemBuilder replaceAndSymbol(boolean replace) {
        this.andSymbol = replace;
        return this;
    }

    public ItemBuilder toggleReplaceAndSymbol() {
        this.replaceAndSymbol(!this.andSymbol);
        return this;
    }

    public ItemBuilder unsafeStackSize(boolean allow) {
        this.unsafeStackSize = allow;
        return this;
    }

    public ItemBuilder toggleUnsafeStackSize() {
        this.unsafeStackSize(!this.unsafeStackSize);
        return this;
    }

    public String getDisplayname() {
        return this.displayname;
    }

    public int getAmount() {
        return this.amount;
    }

    public Map<Enchantment, Integer> getEnchantments() {
        return this.enchantments;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public short getDamage() {
        return this.damage;
    }

    public short getDurability() {
        return this.damage;
    }

    public List<String> getLores() {
        return this.lore;
    }

    public boolean getAndSymbol() {
        return this.andSymbol;
    }

    public List<ItemFlag> getFlags() {
        return this.flags;
    }

    public Material getMaterial() {
        return this.material;
    }

    public ItemMeta getMeta() {
        return this.meta;
    }

    public MaterialData getData() {
        return this.data;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public List<String> getLore() {
        return this.lore;
    }

    public ItemBuilder toConfig(FileConfiguration cfg, String path) {
        cfg.set(path, this.build());
        return this;
    }

    public ItemBuilder fromConfig(FileConfiguration cfg, String path) {
        return new ItemBuilder(cfg, path);
    }

    public String toJson() {
        return (new Gson()).toJson(this);
    }

    public ItemBuilder applyJson(String json, boolean overwrite) {
        ItemBuilder b = (new Gson()).fromJson(json, ItemBuilder.class);
        if (overwrite) {
            return b;
        } else {
            if (b.displayname != null) {
                this.displayname = b.displayname;
            }

            if (b.data != null) {
                this.data = b.data;
            }

            if (b.material != null) {
                this.material = b.material;
            }

            if (b.lore != null) {
                this.lore = b.lore;
            }

            if (b.enchantments != null) {
                this.enchantments = b.enchantments;
            }

            if (b.item != null) {
                this.item = b.item;
            }

            if (b.flags != null) {
                this.flags = b.flags;
            }

            this.damage = b.damage;
            this.amount = b.amount;
            return this;
        }
    }

    public ItemStack build() {
        this.item.setType(this.material);
        this.item.setAmount(this.amount);
        this.item.setDurability(this.damage);
        this.meta = this.item.getItemMeta();
        if (this.data != null) {
            this.item.setData(this.data);
        }

        if (this.enchantments.size() > 0) {
            this.item.addUnsafeEnchantments(this.enchantments);
        }

        if (this.displayname != null) {
            this.meta.setDisplayName(this.displayname);
        }

        if (this.customModelData != null) {
            this.meta.setCustomModelData(this.customModelData);
        }

        if (this.lore.size() > 0) {
            this.meta.setLore(this.lore);
        }

        if (this.flags.size() > 0) {
            Iterator var1 = this.flags.iterator();

            while (var1.hasNext()) {
                ItemFlag f = (ItemFlag) var1.next();
                this.meta.addItemFlags(f);
            }
        }

        this.item.setItemMeta(this.meta);
        return this.item;
    }

    public class Unsafe {
        protected final ReflectionUtils utils = new ReflectionUtils();
        protected final ItemBuilder builder;

        public Unsafe(ItemBuilder builder) {
            this.builder = builder;
        }

        public Unsafe setString(String key, String value) {
            this.builder.item = this.utils.setString(this.builder.item, key, value);
            return this;
        }

        public String getString(String key) {
            return this.utils.getString(this.builder.item, key);
        }

        public Unsafe setInt(String key, int value) {
            this.builder.item = this.utils.setInt(this.builder.item, key, value);
            return this;
        }

        public int getInt(String key) {
            return this.utils.getInt(this.builder.item, key);
        }

        public Unsafe setDouble(String key, double value) {
            this.builder.item = this.utils.setDouble(this.builder.item, key, value);
            return this;
        }

        public double getDouble(String key) {
            return this.utils.getDouble(this.builder.item, key);
        }

        public Unsafe setBoolean(String key, boolean value) {
            this.builder.item = this.utils.setBoolean(this.builder.item, key, value);
            return this;
        }

        public boolean getBoolean(String key) {
            return this.utils.getBoolean(this.builder.item, key);
        }

        public boolean containsKey(String key) {
            return this.utils.hasKey(this.builder.item, key);
        }

        public ItemBuilder builder() {
            return this.builder;
        }

        public class ReflectionUtils {
            public String getString(ItemStack item, String key) {
                Object compound = this.getNBTTagCompound(this.getItemAsNMSStack(item));
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    return (String) compound.getClass().getMethod("getString", String.class).invoke(compound, key);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var5) {
                    var5.printStackTrace();
                    return null;
                }
            }

            public ItemStack setString(ItemStack item, String key, String value) {
                Object nmsItem = this.getItemAsNMSStack(item);
                Object compound = this.getNBTTagCompound(nmsItem);
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    compound.getClass().getMethod("setString", String.class, String.class).invoke(compound, key, value);
                    nmsItem = this.setNBTTag(compound, nmsItem);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var7) {
                    var7.printStackTrace();
                }

                return this.getItemAsBukkitStack(nmsItem);
            }

            public int getInt(ItemStack item, String key) {
                Object compound = this.getNBTTagCompound(this.getItemAsNMSStack(item));
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    return (Integer) compound.getClass().getMethod("getInt", String.class).invoke(compound, key);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var5) {
                    var5.printStackTrace();
                    return -1;
                }
            }

            public ItemStack setInt(ItemStack item, String key, int value) {
                Object nmsItem = this.getItemAsNMSStack(item);
                Object compound = this.getNBTTagCompound(nmsItem);
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    compound.getClass().getMethod("setInt", String.class, Integer.class).invoke(compound, key, value);
                    nmsItem = this.setNBTTag(compound, nmsItem);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var7) {
                    var7.printStackTrace();
                }

                return this.getItemAsBukkitStack(nmsItem);
            }

            public double getDouble(ItemStack item, String key) {
                Object compound = this.getNBTTagCompound(this.getItemAsNMSStack(item));
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    return (Double) compound.getClass().getMethod("getDouble", String.class).invoke(compound, key);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var5) {
                    var5.printStackTrace();
                    return Double.NaN;
                }
            }

            public ItemStack setDouble(ItemStack item, String key, double value) {
                Object nmsItem = this.getItemAsNMSStack(item);
                Object compound = this.getNBTTagCompound(nmsItem);
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    compound.getClass().getMethod("setDouble", String.class, Double.class).invoke(compound, key, value);
                    nmsItem = this.setNBTTag(compound, nmsItem);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var8) {
                    var8.printStackTrace();
                }

                return this.getItemAsBukkitStack(nmsItem);
            }

            public boolean getBoolean(ItemStack item, String key) {
                Object compound = this.getNBTTagCompound(this.getItemAsNMSStack(item));
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    return (Boolean) compound.getClass().getMethod("getBoolean", String.class).invoke(compound, key);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var5) {
                    var5.printStackTrace();
                    return false;
                }
            }

            public ItemStack setBoolean(ItemStack item, String key, boolean value) {
                Object nmsItem = this.getItemAsNMSStack(item);
                Object compound = this.getNBTTagCompound(nmsItem);
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    compound.getClass().getMethod("setBoolean", String.class, Boolean.class).invoke(compound, key, value);
                    nmsItem = this.setNBTTag(compound, nmsItem);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var7) {
                    var7.printStackTrace();
                }

                return this.getItemAsBukkitStack(nmsItem);
            }

            public boolean hasKey(ItemStack item, String key) {
                Object compound = this.getNBTTagCompound(this.getItemAsNMSStack(item));
                if (compound == null) {
                    compound = this.getNewNBTTagCompound();
                }

                try {
                    return (Boolean) compound.getClass().getMethod("hasKey", String.class).invoke(compound, key);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var5) {
                    var5.printStackTrace();
                    return false;
                }
            }

            public Object getNewNBTTagCompound() {
                String ver = Bukkit.getServer().getClass().getPackage().getName().split(".")[3];

                try {
                    return Class.forName("net.minecraft.server." + ver + ".NBTTagCompound").newInstance();
                } catch (IllegalAccessException | InstantiationException | ClassNotFoundException var3) {
                    var3.printStackTrace();
                    return null;
                }
            }

            public Object setNBTTag(Object tag, Object item) {
                try {
                    item.getClass().getMethod("setTag", item.getClass()).invoke(item, tag);
                    return item;
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var4) {
                    var4.printStackTrace();
                    return null;
                }
            }

            public Object getNBTTagCompound(Object nmsStack) {
                try {
                    return nmsStack.getClass().getMethod("getTag").invoke(nmsStack);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var3) {
                    var3.printStackTrace();
                    return null;
                }
            }

            public Object getItemAsNMSStack(ItemStack item) {
                try {
                    Method m = this.getCraftItemStackClass().getMethod("asNMSCopy", ItemStack.class);
                    return m.invoke(this.getCraftItemStackClass(), item);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var3) {
                    var3.printStackTrace();
                    return null;
                }
            }

            public ItemStack getItemAsBukkitStack(Object nmsStack) {
                try {
                    Method m = this.getCraftItemStackClass().getMethod("asCraftMirror", nmsStack.getClass());
                    return (ItemStack) m.invoke(this.getCraftItemStackClass(), nmsStack);
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var3) {
                    var3.printStackTrace();
                    return null;
                }
            }

            public Class<?> getCraftItemStackClass() {
                String ver = Bukkit.getServer().getClass().getPackage().getName().split(".")[3];

                try {
                    return Class.forName("org.bukkit.craftbukkit." + ver + ".inventory.CraftItemStack");
                } catch (ClassNotFoundException var3) {
                    var3.printStackTrace();
                    return null;
                }
            }
        }
    }
}
