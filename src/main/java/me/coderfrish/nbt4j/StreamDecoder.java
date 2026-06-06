package me.coderfrish.nbt4j;

import java.io.IOException;

@FunctionalInterface
public interface StreamDecoder<V, B> {
    V decode(B buffer) throws IOException;
}
