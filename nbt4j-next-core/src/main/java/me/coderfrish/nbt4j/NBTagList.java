package me.coderfrish.nbt4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class NBTagList extends NBTagElement {
    static final StreamCodec<NBTByteBuffer, NBTagList> LIST_CODEC = StreamCodec.createCodec(
            NBTByteBuffer::readList,
            NBTByteBuffer::writeList
    );

    private final List<NBTagElement> data = new CopyOnWriteArrayList<>();

    public void addProperty(NBTagElement element)  {
        if (!data.isEmpty() && !(element.type() == data.getFirst().type()))
            throw new NBTException("List can`t add more type value.");

        this.data.add(element);
    }

    public void addProperty(String value) {
        this.addProperty(new NBTagPrimitive.TagString(value));
    }

    public void addProperty(int value) {
        this.addProperty(new NBTagPrimitive.TagInt(value));
    }

    public void addProperty(long value) {
        this.addProperty(new NBTagPrimitive.TagLong(value));
    }

    public void addProperty(byte value) {
        this.addProperty(new NBTagPrimitive.TagByte(value));
    }

    public void addProperty(short value) {
        this.addProperty(new NBTagPrimitive.TagShort(value));
    }

    public void addProperty(float value) {
        this.addProperty(new NBTagPrimitive.TagFloat(value));
    }

    public void addProperty(double value) {
        this.addProperty(new NBTagPrimitive.TagDouble(value));
    }

    public void addProperty(int[] array) {
        this.addProperty(new NBTagArray.NBTagIntArray(array));
    }

    public void addProperty(byte[] array) {
        this.addProperty(new NBTagArray.NBTagByteArray(array));
    }

    public void addProperty(long[] array) {
        this.addProperty(new NBTagArray.NBTagLongArray(array));
    }

    public NBTagElement get(int index) {
        return this.data.get(index);
    }

    @Override
    public NBTagType type() {
        return NBTagType.LIST;
    }

    @Override
    public NBTagList getAsList() {
        return this;
    }

    List<NBTagElement> getData() {
        return data;
    }
}
