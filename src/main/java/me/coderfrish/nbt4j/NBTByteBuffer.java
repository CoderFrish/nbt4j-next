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
            compound.addProperty(key, value);
        }

        return compound;
    }

    public void writeIntArray(NBTagArray.NBTagIntArray value) throws IOException {
        this.writeInt(value.getAsIntArray().length);
        for (int item : value.getAsIntArray()) {
            this.writeInt(item);
        }
    }

    public NBTagArray.NBTagIntArray readIntArray() throws IOException {
        int length = readInt();
        if (length < 0) {
            throw new RuntimeException("The prefix is of negative length ):");
        }

        byte[] bytes = new byte[length * 4];
        readBytes(bytes);
        int byteIndex = 0;

        int[] intArray = new int[length];
        for (int i = 0; i < length; i++, byteIndex += 4) {
            int element = 0;
            int bitOffset = 24;
            for (int b = 0; b < 4; b++, bitOffset -= 8) {
                element |= (bytes[byteIndex + b] & 0xFF) << bitOffset;
            }
            intArray[i] = element;
        }

        return new NBTagArray.NBTagIntArray(intArray);
    }

    public void writeByteArray(NBTagArray.NBTagByteArray value) throws IOException {
        this.writeInt(value.getAsByteArray().length);
        for (byte item : value.getAsByteArray()) {
            this.writeByte(item);
        }
    }

    public NBTagArray.NBTagByteArray readByteArray() throws IOException {
        int length = readInt();
        if (length < 0) {
            throw new RuntimeException("The prefix is of negative length ):");
        }

        byte[] bytes = new byte[length];
        readBytes(bytes);

        return new NBTagArray.NBTagByteArray(bytes);
    }

    public void writeLongArray(NBTagArray.NBTagLongArray value) throws IOException {
        this.writeInt(value.getAsLongArray().length);
        for (long item : value.getAsLongArray()) {
            this.writeLong(item);
        }
    }

    public NBTagArray.NBTagLongArray readLongArray() throws IOException {
        int length = readInt();
        if (length < 0) {
            throw new RuntimeException("The prefix is of negative length ):");
        } else if (length == 0) {
            return new NBTagArray.NBTagLongArray(new long[0]);
        }

        byte[] bytes = new byte[length * 8];
        readBytes(bytes);
        int byteIndex = 0;

        long[] longArray = new long[length];
        for (int i = 0; i < length; i++, byteIndex += 8) {
            long element = 0;
            int bitOffset = 56;
            for (int b = 0; b < 8; b++, bitOffset -= 8) {
                element |= ((long) (bytes[byteIndex + b] & 0xFF)) << bitOffset;
            }
            longArray[i] = element;
        }
        return new NBTagArray.NBTagLongArray(longArray);
    }

    public void writeList(NBTagList list) throws IOException {
        if (list.getData().isEmpty()) {
            this.writeByte((byte) 0);
            this.writeInt(0);
            return;
        }

        NBTagType type = list.getData().getFirst().type();
        this.writeByte((byte) (type.ordinal() + 1));
        this.writeInt(list.getData().size());
        for (NBTagElement tag : list.getData()) {
            encode(type, this, tag);
        }
    }

    public NBTagList readList() throws IOException {
        NBTagList list = new NBTagList();
        int typeId = readByte();
        if (typeId == 0) {
            return new NBTagList();
        }

        NBTagType type = NBTagType.values()[typeId - 1];
        int length = readInt();
        for (int i = 0; i < length; i++) {
            NBTagElement element = decode(type, this);
            list.addProperty(element);
        }

        return list;
    }
}
