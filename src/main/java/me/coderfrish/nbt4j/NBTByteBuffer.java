package me.coderfrish.nbt4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static me.coderfrish.nbt4j.NBTypeRegistry.decode;
import static me.coderfrish.nbt4j.NBTypeRegistry.encode;

final class NBTByteBuffer extends BaseByteBuffer {
    public NBTByteBuffer(OutputStream os) {
        super(os);
    }

    public NBTByteBuffer(InputStream is) {
        super(is);
    }

    public String readUTF() throws IOException {
        byte[] strBytes = new byte[readShort()];
        readBytes(strBytes);

        return new String(strBytes, StandardCharsets.UTF_8);
    }

    public void writeUTF(String value) throws IOException {
        if (value == null) {
            this.writeInt(0);
            return;
        }

        byte[] strBytes = value.getBytes(StandardCharsets.UTF_8);
        this.writeShort(strBytes.length);
        this.writeBytes(strBytes);
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }

    public void writeFloat(float value) throws IOException {
        this.writeInt(Float.floatToIntBits(value));
    }

    public double readDouble() throws IOException {
        return Double.doubleToLongBits(this.readLong());
    }

    public void writeDouble(double value) throws IOException {
        this.writeLong(Double.doubleToLongBits(value));
    }

    public void writeCompound(NBTagCompound compound) throws IOException {
        for (Map.Entry<String, NBTagElement> entry : compound.getDataEntries()) {
            NBTagType type = entry.getValue().type();
            this.writeByte((byte) (type.ordinal() + 1));
            this.writeUTF(entry.getKey());
            encode(type, this, entry.getValue());
        }

        writeByte((byte) 0);
    }

    public NBTagCompound readCompound() throws IOException {
        NBTagCompound compound = new NBTagCompound();
        for (;;) {
            int typeId = readByte();
            if (typeId == 0) break;

            NBTagType type = NBTagType.values()[typeId - 1];
            String key = readUTF();
            NBTagElement value = decode(type, this);
            compound.add(key, value);
        }

        return compound;
    }
}
