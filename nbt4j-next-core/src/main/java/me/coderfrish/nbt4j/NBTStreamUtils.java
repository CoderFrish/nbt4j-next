package me.coderfrish.nbt4j;

import java.io.InputStream;
import java.io.OutputStream;

public class NBTStreamUtils {
    public NBTStreamUtils() {
        throw new RuntimeException("NBTStreamUtils is a static tools pack, can`t to instance.");
    }

    /**
     * @param name NBT File Header name
     * @param os NBT File Output Stream
     * @param compound NBT Compound Bean
     * @exception Exception throw Stream Exception
     */
    public static void write(OutputStream os, String name, NBTagCompound compound) throws Exception {
        try(NBTByteBuffer buffer = new NBTByteBuffer(os)) {
            buffer.writeByte((byte) 10);
            buffer.writeUTF(name);
            buffer.writeCompound(compound);
        }
    }

    /**
     * @param os NBT File Output Stream
     * @param compound NBT Compound Bean
     * @exception Exception throw Stream Exception
     */
    public static void write(OutputStream os, NBTagCompound compound) throws Exception {
        write(os, "", compound);
    }

    /**
     * @param is NBT File Input Stream
     * @exception Exception throw Stream Exception
     */
    public static NBTagCompound read(InputStream is) throws Exception {
        try(NBTByteBuffer buffer = new NBTByteBuffer(is)) {
            buffer.readByte(); /* skip */
            buffer.readUTF(); /* skip */
            return buffer.readCompound();
        }
    }
}
