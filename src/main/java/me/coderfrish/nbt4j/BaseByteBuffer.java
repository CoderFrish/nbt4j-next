package me.coderfrish.nbt4j;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

abstract class BaseByteBuffer implements AutoCloseable {
    private final byte[] readLongBuffer = new byte[8];
    private final byte[] readIntBuffer = new byte[4];
    private final byte[] readShortBuffer = new byte[2];

    private final OutputStream os;
    private final InputStream is;

    private volatile boolean readable = false;

    private BaseByteBuffer(OutputStream os, InputStream is) {
        this.os = os;
        this.is = is;
    }

    public BaseByteBuffer(OutputStream os) {
        this(os, InputStream.nullInputStream());
        this.readable = false;
    }

    public BaseByteBuffer(InputStream is) {
        this(OutputStream.nullOutputStream(), is);
        this.readable = true;
    }

    @Override
    public void close() throws Exception {
        if (readable)
            is.close();
        else
            os.close();
    }

    public void writeBytes(byte[] value) throws IOException {
        os.write(value);
    }

    public int readBytes(byte[] b) throws IOException {
        return is.read(b, 0, b.length);
    }

    public byte readByte() throws IOException {
        byte result = (byte) is.read();
        if (result == -1)
            throw new EOFException();

        return result;
    }

    public short readShort() throws IOException {
        if (readBytes(readShortBuffer) == -1)
            throw new EOFException();
        return (short) ((readShortBuffer[0] << 8) +
                (readShortBuffer[1]));
    }

    public int readInt() throws IOException {
        if (readBytes(readIntBuffer) == -1)
            throw new EOFException();
        return ((readIntBuffer[0] << 24) +
                (readIntBuffer[1] << 16) +
                (readIntBuffer[2] << 8) +
                (readIntBuffer[3]));
    }

    public long readLong() throws IOException {
        if (readBytes(readLongBuffer) == -1)
            throw new EOFException();
        return (((long)readLongBuffer[0] << 56) +
                ((long)(readLongBuffer[1] & 255) << 48) +
                ((long)(readLongBuffer[2] & 255) << 40) +
                ((long)(readLongBuffer[3] & 255) << 32) +
                ((long)(readLongBuffer[4] & 255) << 24) +
                ((readLongBuffer[5] & 255) << 16) +
                ((readLongBuffer[6] & 255) <<  8) +
                ((readLongBuffer[7] & 255)));
    }

    public void writeByte(byte v) throws IOException {
        os.write(v);
    }

    public void writeShort(int v) throws IOException {
        writeByte((byte) ((v >>> 8) & 0xFF));
        writeByte((byte) (v & 0xFF));
    }

    public void writeInt(int v) throws IOException {
        writeByte((byte) ((v >>> 24) & 0xFF));
        writeByte((byte) ((v >>> 16) & 0xFF));
        writeByte((byte) ((v >>> 8) & 0xFF));
        writeByte((byte) (v & 0xFF));
    }

    public void writeLong(long v) throws IOException {
        writeByte((byte) ((int) (v >>> 56) & 0xFF));
        writeByte((byte) ((int) (v >>> 48) & 0xFF));
        writeByte((byte) ((int) (v >>> 40) & 0xFF));
        writeByte((byte) ((int) (v >>> 32) & 0xFF));
        writeByte((byte) ((int) (v >>> 24) & 0xFF));
        writeByte((byte) ((int) (v >>> 16) & 0xFF));
        writeByte((byte) ((int) (v >>> 8) & 0xFF));
        writeByte((byte) ((int) (v) & 0xFF));
    }
}
