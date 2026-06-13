package me.coderfrish.nbt4j;

import java.io.IOException;

import static me.coderfrish.nbt4j.NBTagCompound.COMPOUND_CODEC;
import static me.coderfrish.nbt4j.NBTagList.LIST_CODEC;
import static me.coderfrish.nbt4j.NBTagPrimitive.*;
import static me.coderfrish.nbt4j.NBTagArray.*;

@SuppressWarnings("unchecked")
public enum NBTagType {
    BYTE(BYTE_CODEC),
    SHORT(SHORT_CODEC),
    INT(INT_CODEC),
    LONG(LONG_CODEC),
    FLOAT(FLOAT_CODEC),
    DOUBLE(DOUBLE_CODEC),
    BYTE_ARRAY(BYTE_ARRAY_CODEC),
    STRING(STRING_CODEC),
    LIST(LIST_CODEC),
    COMPOUND(COMPOUND_CODEC),
    INT_ARRAY(INT_ARRAY_CODEC),
    LONG_ARRAY(LONG_ARRAY_CODEC);

    private final StreamCodec<?, ?> codec;

    NBTagType(StreamCodec<?, ?> codec) {
        this.codec = codec;
    }

    <B, V> void encode(B buffer, V value) throws IOException {
        ((StreamCodec<B, V>) codec).encode(buffer, value);
    }

    <B, V> V decode(B buffer) throws IOException {
        return ((StreamCodec<B, V>) codec).decode(buffer);
    }
}
