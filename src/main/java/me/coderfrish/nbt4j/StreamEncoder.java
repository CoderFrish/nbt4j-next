package me.coderfrish.nbt4j;

import java.io.IOException;

@FunctionalInterface
public interface StreamEncoder<B, V> {
    void encode(B buffer, V value) throws IOException;
}
