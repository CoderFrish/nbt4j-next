package me.coderfrish.nbt4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class NBTagCompound extends NBTagElement {
    static final StreamCodec<NBTByteBuffer, NBTagCompound> COMPOUND_CODEC = StreamCodec.createCodec(
            NBTByteBuffer::readCompound,
            NBTByteBuffer::writeCompound
    );

    private final Map<String, NBTagElement> data = new ConcurrentHashMap<>();

    public void add(String key, NBTagElement element)  {
        this.data.put(key, element);
    }

    public void addProperty(String key, String value) {
        this.add(key, new NBTagPrimitive.TagString(value));
    }

    public void addProperty(String key, int value) {
        this.add(key, new NBTagPrimitive.TagInt(value));
    }

    public void addProperty(String key, long value) {
        this.add(key, new NBTagPrimitive.TagLong(value));
    }

    public void addProperty(String key, byte value) {
        this.add(key, new NBTagPrimitive.TagByte(value));
    }

    public void addProperty(String key, short value) {
        this.add(key, new NBTagPrimitive.TagShort(value));
    }

    public void addProperty(String key, float value) {
        this.add(key, new NBTagPrimitive.TagFloat(value));
    }

    public void addProperty(String key, double value) {
        this.add(key, new NBTagPrimitive.TagDouble(value));
    }

    public NBTagElement get(String key) {
        return this.data.get(key);
    }

    @Override
    public NBTagCompound getAsCompound() {
        return this;
    }

    @Override
    public NBTagType type() {
        return NBTagType.COMPOUND;
    }

    Set<Map.Entry<String, NBTagElement>> getDataEntries() {
        return data.entrySet();
    }
}
