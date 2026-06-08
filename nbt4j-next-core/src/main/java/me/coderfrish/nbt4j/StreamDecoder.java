package me.coderfrish.nbt4j;

import java.io.IOException;

@FunctionalInterface
interface StreamDecoder<V, B> {
    V decode(B buffer) throws IOException;
}
