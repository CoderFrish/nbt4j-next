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

    void writeBytes(byte[] value) throws IOException {
        this.ensureWriteableMode();
        os.write(value);
    }

    void writeByte(byte v) throws IOException {
        this.ensureWriteableMode();
        os.write(v);
    }

    int readBytes(byte[] b) throws IOException {
        this.ensureReadableMode();
        return is.read(b, 0, b.length);
    }

    byte readByte() throws IOException {
        this.ensureReadableMode();
        byte result = (byte) is.read();
        if (result == -1)
            throw new EOFException();

        return result;
    }

    short readShort() throws IOException {
        if (readBytes(readShortBuffer) == -1)
            throw new EOFException();
        return (short) ((readShortBuffer[0] << 8) +
                (readShortBuffer[1]));
    }

    int readInt() throws IOException {
        if (readBytes(readIntBuffer) == -1)
            throw new EOFException();
        return ((readIntBuffer[0] << 24) +
                (readIntBuffer[1] << 16) +
                (readIntBuffer[2] << 8) +
                (readIntBuffer[3]));
    }

    long readLong() throws IOException {
        if (readBytes(readLongBuffer) == -1)
            throw new EOFException();
        return (((long) readLongBuffer[0] << 56) +
                ((long) (readLongBuffer[1] & 255) << 48) +
                ((long) (readLongBuffer[2] & 255) << 40) +
                ((long) (readLongBuffer[3] & 255) << 32) +
                ((long) (readLongBuffer[4] & 255) << 24) +
                ((readLongBuffer[5] & 255) << 16) +
                ((readLongBuffer[6] & 255) << 8) +
                ((readLongBuffer[7] & 255)));
    }

    void writeShort(int v) throws IOException {
        writeByte((byte) ((v >>> 8) & 0xFF));
        writeByte((byte) (v & 0xFF));
    }

    void writeInt(int v) throws IOException {
        writeByte((byte) ((v >>> 24) & 0xFF));
        writeByte((byte) ((v >>> 16) & 0xFF));
        writeByte((byte) ((v >>> 8) & 0xFF));
        writeByte((byte) (v & 0xFF));
    }

    void writeLong(long v) throws IOException {
        writeByte((byte) ((int) (v >>> 56) & 0xFF));
        writeByte((byte) ((int) (v >>> 48) & 0xFF));
        writeByte((byte) ((int) (v >>> 40) & 0xFF));
        writeByte((byte) ((int) (v >>> 32) & 0xFF));
        writeByte((byte) ((int) (v >>> 24) & 0xFF));
        writeByte((byte) ((int) (v >>> 16) & 0xFF));
        writeByte((byte) ((int) (v >>> 8) & 0xFF));
        writeByte((byte) ((int) (v) & 0xFF));
    }

    float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }

    void writeFloat(float value) throws IOException {
        this.writeInt(Float.floatToIntBits(value));
    }

    double readDouble() throws IOException {
        return Double.doubleToLongBits(this.readLong());
    }

    void writeDouble(double value) throws IOException {
        this.writeLong(Double.doubleToLongBits(value));
    }

    private void ensureReadableMode() {
        if (!readable) {
            throw new NBTException("This ByteBuffer not is readable mode.");
        }
    }

    private void ensureWriteableMode() {
        if (readable) {
            throw new NBTException("This ByteBuffer not is writeable mode.");
        }
    }
}
