package me.coderfrish.nbt4j;

abstract class NBTagPrimitive extends NBTagElement {
    static final StreamCodec<BaseByteBuffer, TagInt> INT_CODEC = StreamCodec.createCodec(
            (b) -> new TagInt(b.readInt()),
            (b, v) -> b.writeInt(v.getAsInt())
    );

    static final StreamCodec<NBTByteBuffer, TagString> STRING_CODEC = StreamCodec.createCodec(
            (b) -> new TagString(b.readUTF()),
            (b, v) -> b.writeUTF(v.getAsString())
    );

    static final StreamCodec<BaseByteBuffer, TagByte> BYTE_CODEC = StreamCodec.createCodec(
            (b) -> new TagByte(b.readByte()),
            (b, v) -> b.writeByte(v.getAsByte())
    );

    static final StreamCodec<BaseByteBuffer, TagShort> SHORT_CODEC = StreamCodec.createCodec(
            (b) -> new TagShort(b.readShort()),
            (b, v) -> b.writeShort(v.getAsShort())
    );

    static final StreamCodec<BaseByteBuffer, TagFloat> FLOAT_CODEC = StreamCodec.createCodec(
            (b) -> new TagFloat(Float.intBitsToFloat(b.readInt())),
            (b, v) -> b.writeInt(Float.floatToIntBits(v.getAsFloat()))
    );

    static final StreamCodec<BaseByteBuffer, TagDouble> DOUBLE_CODEC = StreamCodec.createCodec(
            (b) -> new TagDouble(Double.longBitsToDouble(b.readLong())),
            (b, v) -> b.writeLong(Double.doubleToLongBits(v.getAsDouble()))
    );

    static final StreamCodec<BaseByteBuffer, TagLong> LONG_CODEC = StreamCodec.createCodec(
            (b) -> new TagLong(b.readLong()),
            (b, v) -> b.writeLong(v.getAsLong())
    );

    protected final Object object;

    public NBTagPrimitive(Object object) {
        this.object = object;
    }

    static class TagString extends NBTagPrimitive {
        public TagString(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.STRING;
        }

        @Override
        public String getAsString() {
            return (String) object;
        }
    }

    static class TagInt extends NBTagPrimitive {
        public TagInt(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.INT;
        }

        @Override
        public int getAsInt() {
            return (int) object;
        }
    }

    static class TagShort extends NBTagPrimitive {
        public TagShort(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.SHORT;
        }

        @Override
        public short getAsShort() {
            return (short) object;
        }
    }

    static class TagLong extends NBTagPrimitive {
        public TagLong(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.LONG;
        }

        @Override
        public long getAsLong() {
            return (long) object;
        }
    }

    static class TagByte extends NBTagPrimitive {
        public TagByte(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.BYTE;
        }

        @Override
        public byte getAsByte() {
            return (byte) object;
        }
    }

    static class TagDouble extends NBTagPrimitive {
        public TagDouble(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.DOUBLE;
        }

        @Override
        public double getAsDouble() {
            return (double) object;
        }
    }

    static class TagFloat extends NBTagPrimitive {
        public TagFloat(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.FLOAT;
        }

        @Override
        public float getAsFloat() {
            return (float) object;
        }
    }
}
