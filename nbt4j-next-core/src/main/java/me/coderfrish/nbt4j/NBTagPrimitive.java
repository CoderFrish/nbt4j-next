package me.coderfrish.nbt4j;

abstract class NBTagPrimitive extends NBTagElement {
    static final StreamCodec<BaseByteBuffer, TagInt> INT_CODEC = StreamCodec.createCodec(
            (b) -> new TagInt(b.readInt()),
            (b, v) -> b.writeInt(v.getAsInt())
    );

    static final StreamCodec<NBTByteBuffer, TagString> STRING_CODEC = StreamCodec.createCodec(
            (b) -> new TagString(b.readString()),
            (b, v) -> b.writeString(v.getAsString())
    );

    static final StreamCodec<BaseByteBuffer, TagByte> BYTE_CODEC = StreamCodec.createCodec(
            (b) -> new TagByte(b.readByte()),
            (b, v) -> b.writeByte(v.getAsByte())
    );

    static final StreamCodec<BaseByteBuffer, TagShort> SHORT_CODEC = StreamCodec.createCodec(
            (b) -> new TagShort(b.readShort()),
            (b, v) -> b.writeShort(v.getAsShort())
    );

    static final StreamCodec<NBTByteBuffer, TagFloat> FLOAT_CODEC = StreamCodec.createCodec(
            (b) -> new TagFloat(b.readFloat()),
            (b, v) -> b.writeFloat(v.getAsFloat())
    );

    static final StreamCodec<NBTByteBuffer, TagDouble> DOUBLE_CODEC = StreamCodec.createCodec(
            (b) -> new TagDouble(b.readDouble()),
            (b, v) -> b.writeDouble(v.getAsDouble())
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

    static abstract class TagNumber extends NBTagPrimitive {
        public TagNumber(Object object) {
            super(object);
        }

        @Override
        public Number getAsNumber() {
            return (Number) object;
        }

        @Override
        public int getAsInt() {
            ensureTypeValue(NBTagType.INT);
            return getAsNumber().intValue();
        }

        @Override
        public long getAsLong() {
            ensureTypeValue(NBTagType.LONG);
            return getAsNumber().longValue();
        }

        @Override
        public short getAsShort() {
            ensureTypeValue(NBTagType.SHORT);
            return getAsNumber().shortValue();
        }

        @Override
        public byte getAsByte() {
            ensureTypeValue(NBTagType.BYTE);
            return getAsNumber().byteValue();
        }

        @Override
        public float getAsFloat() {
            ensureTypeValue(NBTagType.FLOAT);
            return getAsNumber().floatValue();
        }

        @Override
        public double getAsDouble() {
            ensureTypeValue(NBTagType.DOUBLE);
            return getAsNumber().doubleValue();
        }

        private void ensureTypeValue(NBTagType target) {
            if (type() != target)
                throw new NBTException("Type is " + type() + " not to " + target);
        }
    }

    static class TagInt extends TagNumber {
        public TagInt(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.INT;
        }
    }

    static class TagShort extends TagNumber {
        public TagShort(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.SHORT;
        }
    }

    static class TagLong extends TagNumber {
        public TagLong(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.LONG;
        }
    }

    static class TagByte extends TagNumber {
        public TagByte(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.BYTE;
        }
    }

    static class TagDouble extends TagNumber {
        public TagDouble(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.DOUBLE;
        }
    }

    static class TagFloat extends TagNumber {
        public TagFloat(Object object) {
            super(object);
        }

        @Override
        public NBTagType type() {
            return NBTagType.FLOAT;
        }
    }
}
