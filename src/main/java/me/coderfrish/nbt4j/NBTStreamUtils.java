package me.coderfrish.nbt4j;

import java.io.InputStream;
import java.io.OutputStream;

public class NBTStreamUtils {
    public NBTStreamUtils() {
        throw new RuntimeException("NBTStreamUtils is a static tools pack, can`t to instance.");
    }

    public static void write(OutputStream os, String name, NBTagCompound compound) throws Exception {
        try(NBTByteBuffer buffer = new NBTByteBuffer(os)) {
            buffer.writeByte((byte) 10);
            buffer.writeUTF(name);
            buffer.writeCompound(compound);
        }
    }

    public static void write(OutputStream os, NBTagCompound compound) throws Exception {
        write(os, "", compound);
    }

    public static NBTagCompound read(InputStream is) throws Exception {
        try(NBTByteBuffer buffer = new NBTByteBuffer(is)) {
            buffer.readByte(); /* skip */
            buffer.readUTF(); /* skip */
            return buffer.readCompound();
        }
    }
}
