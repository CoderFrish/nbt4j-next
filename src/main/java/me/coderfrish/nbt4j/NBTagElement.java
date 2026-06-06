package me.coderfrish.nbt4j;

public abstract class NBTagElement {
    public String getAsString() {
        throw new IllegalArgumentException("Not a String.");
    }

    public short getAsShort() {
        throw new IllegalArgumentException("Not a Short.");
    }

    public int getAsInt() {
        throw new IllegalArgumentException("Not a Int.");
    }

    public byte getAsByte() {
        throw new IllegalArgumentException("Not a Byte.");
    }

    public long getAsLong() {
        throw new IllegalArgumentException("Not a Long.");
    }

    public float getAsFloat() {
        throw new IllegalArgumentException("Not a Float.");
    }

    public double getAsDouble() {
        throw new IllegalArgumentException("Not a Double.");
    }

    public NBTagCompound getAsCompound() {
        throw new NBTException("Not a compound.");
    }

    public abstract NBTagType type();
}
