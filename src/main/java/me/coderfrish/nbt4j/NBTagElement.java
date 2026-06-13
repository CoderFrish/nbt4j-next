package me.coderfrish.nbt4j;

public abstract class NBTagElement {
    public abstract NBTagType type();

    public String getAsString() {
        throw new NBTException("Not a string.");
    }

    public short getAsShort() {
        throw new NBTException("Not a short.");
    }

    public int getAsInt() {
        throw new NBTException("Not a int.");
    }

    public byte getAsByte() {
        throw new NBTException("Not a byte.");
    }

    public long getAsLong() {
        throw new NBTException("Not a long.");
    }

    public float getAsFloat() {
        throw new NBTException("Not a flat.");
    }

    public double getAsDouble() {
        throw new NBTException("Not a double.");
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

    public Number getAsNumber() {
        throw new NBTException("Not a number.");
    }
}
