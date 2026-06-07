package me.coderfrish.nbt4j;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static me.coderfrish.nbt4j.NBTagArray.*;
import static me.coderfrish.nbt4j.NBTagCompound.COMPOUND_CODEC;
import static me.coderfrish.nbt4j.NBTagPrimitive.*;

@SuppressWarnings("unchecked")
public final class NBTypeRegistry {
    private static final Map<NBTagType, StreamCodec<?, ?>> registry = new LinkedHashMap<>() {
        {
            put(NBTagType.COMPOUND, COMPOUND_CODEC);
            put(NBTagType.INT, INT_CODEC);
            put(NBTagType.STRING, STRING_CODEC);
            put(NBTagType.FLOAT, FLOAT_CODEC);
            put(NBTagType.SHORT, SHORT_CODEC);
            put(NBTagType.LONG, LONG_CODEC);
            put(NBTagType.DOUBLE, DOUBLE_CODEC);
            put(NBTagType.BYTE, BYTE_CODEC);
            put(NBTagType.INT_ARRAY, INT_ARRAY_CODEC);
            put(NBTagType.BYTE_ARRAY, BYTE_ARRAY_CODEC);
            put(NBTagType.LONG_ARRAY, LONG_ARRAY_CODEC);
        }
    };

    static <B, V> void encode(NBTagType type, B buffer, V value) throws IOException {
        StreamCodec<B, V> codec = (StreamCodec<B, V>) registry.get(type);
        codec.encode(buffer, value);
    }

    static <B, V> V decode(NBTagType type, B buffer) throws IOException {
        StreamCodec<B, V> codec = (StreamCodec<B, V>) registry.get(type);
        return codec.decode(buffer);
    }
}
