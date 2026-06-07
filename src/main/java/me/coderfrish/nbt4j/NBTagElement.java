package me.coderfrish.nbt4j;

public abstract class NBTagElement {
    public String getAsString() {
        throw new IllegalArgumentException("Not a string.");
    }

    public short getAsShort() {
        throw new IllegalArgumentException("Not a short.");
    }

    public int getAsInt() {
        throw new IllegalArgumentException("Not a int.");
    }

    public byte getAsByte() {
        throw new IllegalArgumentException("Not a byte.");
    }

    public long getAsLong() {
        throw new IllegalArgumentException("Not a long.");
    }

    public float getAsFloat() {
        throw new IllegalArgumentException("Not a flat.");
    }

    public double getAsDouble() {
        throw new IllegalArgumentException("Not a double.");
    }

    public NBTagCompound getAsCompound() {
        throw new NBTException("Not a compound.");
    }

    public byte[] getAsByteArray() {
        throw new NBTException("Not a byte array.");
    }

    public int[] getAsIntArray() {
        throw new NBTException("Not a int array");
    }

    public long[] getAsLongArray() {
        throw new NBTException("Not a long array");
    }

    public NBTagList getAsList() {
        throw new NBTException("Not a list.");
    }

    public abstract NBTagType type();
}
