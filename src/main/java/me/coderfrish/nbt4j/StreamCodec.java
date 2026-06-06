package me.coderfrish.nbt4j;

import java.io.IOException;

public interface StreamCodec<B, V> extends StreamEncoder<B, V>, StreamDecoder<V, B> {
    static <B, V> StreamCodec<B, V> createCodec(StreamDecoder<V, B> decoder, StreamEncoder<B, V> encoder) {
        return new StreamCodec<B, V>() {
            @Override
            public V decode(B buffer) throws IOException {
                return decoder.decode(buffer);
            }

            @Override
            public void encode(B buffer, V value) throws IOException {
                encoder.encode(buffer, value);
            }
        };
    }
}
