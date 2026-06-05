package me.coderfrish.nbt4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NBTCompound extends NBTElement {
    private final Map<String, NBTElement> datas = new ConcurrentHashMap<>();

    public void add(String key, NBTElement element) {
        datas.put(key, element);
    }

    public NBTElement get(String key) {
        return datas.get(key);
    }
}
