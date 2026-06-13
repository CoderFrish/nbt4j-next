package me.coderfrish.nbt4j;

import java.io.IOException;

@FunctionalInterface
interface StreamEncoder<B, V> {
    void encode(B buffer, V value) throws IOException;
}
