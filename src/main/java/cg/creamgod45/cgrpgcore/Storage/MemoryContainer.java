package cg.creamgod45.cgrpgcore.Storage;

import cg.creamgod45.cgrpgcore.CGRPGCore;

import java.util.HashMap;
import java.util.Map;

public class MemoryContainer <T>
{
    private CGRPGCore plugin;
    private Map<StorageNameField, T> MemoryList;
    public MemoryContainer(CGRPGCore plugin){
        this.plugin = plugin;
        this.MemoryList = new HashMap<>();
    }

    @Override
    public String toString() {
        return "MemoryContainer{" +
                "plugin=" + plugin +
                ", MemoryList=" + MemoryList +
                '}';
    }

    public T get(StorageNameField key){
        Object o = MemoryList.get(key);
        if (o == null) {
            return null; // 或者根據需要返回默認值
        }
        return (T) o;
    }

    public void set(StorageNameField key, T value){
        MemoryList.put(key, value);
    }
}
