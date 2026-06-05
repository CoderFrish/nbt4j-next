package me.coderfrish.nbt4j;

public abstract class NBTElement {
    public String getAsString() {
        throw new IllegalArgumentException("Not a String.");
    }

    public short getAsShort() {
        throw new IllegalArgumentException("Not a Short.");
    }

    public int getAsInteger() {
        throw new IllegalArgumentException("Not a Integer.");
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

    public NBTCompound getAsCompound() {
        throw new IllegalArgumentException("Not a Compound.");
    }

    public Number getAsNumber() {
        throw new IllegalArgumentException("Not a Number.");
    }
}
