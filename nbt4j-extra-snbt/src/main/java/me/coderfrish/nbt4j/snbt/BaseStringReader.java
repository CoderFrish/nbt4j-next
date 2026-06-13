package me.coderfrish.nbt4j.snbt;

abstract class BaseStringReader {
    private final String str;
    private int position = 0;

    BaseStringReader(String str) {
        this.str = str;
    }

    void advance() {
        position++;
    }

    char peek() {
        return this.peek(0);
    }

    char peek(int offset) {
        return str.charAt(position + offset);
    }

    boolean canRead() {
        return position < str.length();
    }
}
