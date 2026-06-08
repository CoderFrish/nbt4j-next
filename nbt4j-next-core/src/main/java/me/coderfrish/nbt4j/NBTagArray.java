package me.coderfrish.nbt4j;

abstract class NBTagArray extends NBTagElement {
    static final StreamCodec<NBTByteBuffer, NBTagIntArray> INT_ARRAY_CODEC = StreamCodec.createCodec(
            NBTByteBuffer::readIntArray,
            NBTByteBuffer::writeIntArray
    );

    static final StreamCodec<NBTByteBuffer, NBTagByteArray>BYTE_ARRAY_CODEC = StreamCodec.createCodec(
            NBTByteBuffer::readByteArray,
            NBTByteBuffer::writeByteArray
    );

    static final StreamCodec<NBTByteBuffer, NBTagLongArray> LONG_ARRAY_CODEC = StreamCodec.createCodec(
            NBTByteBuffer::readLongArray,
            NBTByteBuffer::writeLongArray
    );

    static final class NBTagIntArray extends NBTagArray {
        private final int[] array;

        NBTagIntArray(int[] array) {
            this.array = array;
        }

        @Override
        public NBTagType type() {
            return NBTagType.INT_ARRAY;
        }

        @Override
        public int[] getAsIntArray() {
            return this.array;
        }
    }

    static final class NBTagByteArray extends NBTagArray {
        private final byte[] array;

        NBTagByteArray(byte[] array) {
            this.array = array;
        }

        @Override
        public NBTagType type() {
            return NBTagType.BYTE_ARRAY;
        }

        @Override
        public byte[] getAsByteArray() {
            return this.array;
        }
    }

    static final class NBTagLongArray extends NBTagArray {
        private final long[] array;

        NBTagLongArray(long[] array) {
            this.array = array;
        }

        @Override
        public NBTagType type() {
            return NBTagType.LONG_ARRAY;
        }

        @Override
        public long[] getAsLongArray() {
            return this.array;
        }
    }
}
