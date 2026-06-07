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

    public void addProperty(String key, NBTagElement element)  {
        this.data.put(key, element);
    }

    public void addProperty(String key, String value) {
        this.addProperty(key, new NBTagPrimitive.TagString(value));
    }

    public void addProperty(String key, int value) {
        this.addProperty(key, new NBTagPrimitive.TagInt(value));
    }

    public void addProperty(String key, long value) {
        this.addProperty(key, new NBTagPrimitive.TagLong(value));
    }

    public void addProperty(String key, byte value) {
        this.addProperty(key, new NBTagPrimitive.TagByte(value));
    }

    public void addProperty(String key, short value) {
        this.addProperty(key, new NBTagPrimitive.TagShort(value));
    }

    public void addProperty(String key, float value) {
        this.addProperty(key, new NBTagPrimitive.TagFloat(value));
    }

    public void addProperty(String key, double value) {
        this.addProperty(key, new NBTagPrimitive.TagDouble(value));
    }

    public void addProperty(String key, int[] array) {
        this.addProperty(key, new NBTagArray.NBTagIntArray(array));
    }

    public void addProperty(String key, byte[] array) {
        this.addProperty(key, new NBTagArray.NBTagByteArray(array));
    }

    public void addProperty(String key, long[] array) {
        this.addProperty(key, new NBTagArray.NBTagLongArray(array));
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
